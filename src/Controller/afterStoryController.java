package Controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CommentDao;
import Dao.afterStoryDao;
import Dto.CommentDto;
import Dto.afterStoryDto;

@WebServlet(urlPatterns = "/after")
public class afterStoryController extends HttpServlet {

	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);			
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("afterStoryController doProcess");	
	
		String param = req.getParameter("param");
		
		
		if(param.equals("afterstoryList")) {
		
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String lpage = req.getParameter("pageNum");

			int page = 0;
			if(lpage != null && !lpage.equals("")) {	
				page = Integer.parseInt(lpage);
			}
			if(choice == null) {
				choice = "";
	
			}
			if(search == null) {
				search = "";
			}
			afterStoryDao dao = afterStoryDao.getInstance();
			List<afterStoryDto> list = dao.storyList(choice, search, page);
			req.setAttribute("list", list);
	
			
			int len = dao.allAfterStory(choice, search);
			int afPage = len / 9;	
			if((len % 9) > 0){
				afPage = afPage +1;
			}
			
			
			System.out.println("총 글 : " + len);
			System.out.println("총 글 : " + len);
			System.out.println("출력되는 총 페이지 : " + afPage);
			
			req.setAttribute("afPage", afPage + "");
			req.setAttribute("pageNum", page + "");
			req.setAttribute("len", len + "");
			req.setAttribute("search", search );
			req.setAttribute("choice", choice );
			req.setAttribute("content", "./afterstory/afterStoryList");
			
			forward("index.jsp", req, resp);	
			
		} 
		else if(param.equals("afterStoryDetail")) {
			//////////// 댓글 관련 //////////
			//부모글의 seq값 
			int seq = Integer.parseInt(req.getParameter("seq"));
			//댓글의 pageNumber
			String pageNumber = req.getParameter("pageNumber");
			int page = 0;
			
			if(pageNumber != null) {
				page = Integer.parseInt(pageNumber);
			}
			
			CommentDao dao = CommentDao.getInstance();
			List<CommentDto> list = dao.CommentList(page, seq, 2);
			
			// 작성한 글의 총 갯수 가져오기
			int len = dao.getAllComment(seq, 2);
			
			//출력될 페이지 수 구하기 
			int viewPage = len / 5;
			if(len % 5 > 0 )
				viewPage += 1;
			
			///////// 디테일 정보 출력 ////////

			afterStoryDao afterDao = afterStoryDao.getInstance();
			afterStoryDto dto = afterDao.storyDetail(seq);
			
			req.setAttribute("list", list);
			req.setAttribute("page", page+"");
			req.setAttribute("viewPage", viewPage+"");
			req.setAttribute("detailAf", dto);
			req.setAttribute("content", "./afterstory/afterStoryDetail");
			forward("index.jsp", req, resp);	
		}
		
		else if(param.equals("updateStory")) {

			int seq = Integer.parseInt(req.getParameter("seq").trim());
			req.setAttribute("content", "./afterstory/afterStoryUpdate");
			forward("index.jsp", req, resp);	
		} 
		
		else if(param.equals("write")) {

			req.setAttribute("content", "./afterstory/afterStoryWrite");
			forward("index.jsp", req, resp);	
		} 
		
		else if(param.equals("afterDelete")) {
			int seq = Integer.parseInt( req.getParameter("seq") );
			System.out.println("seq:" + seq);

			afterStoryDao dao = afterStoryDao.getInstance();
			dao.deleteStory(seq);
			
			resp.sendRedirect("after?param=afterstoryList");
		}
		
		
		else if(param.equals("fail")) {
			System.out.println("In Fail Param!");
			
			//한글 깨지지 않게 인코딩
			req.setCharacterEncoding("utf-8");
	
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter pw = resp.getWriter();		
			pw.println("<html>");		
			pw.println("<head>");
			pw.println("<title>견생역전</title>");		
			pw.println("</head>");				
			pw.println("<body>");
			
			pw.println("<script>");
			pw.println("alert('이미지파일만 업로드 가능합니다')");
			pw.println("location.href = 'after?param=afterstoryList'");
			pw.println("</script>");
			
			pw.println("</body>");
			pw.println("</html>");		
			pw.close();		
		}
	

		
		}
		
	

	

}

