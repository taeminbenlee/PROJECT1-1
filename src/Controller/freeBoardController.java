package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import Dao.CommentDao;
import Dto.BbsDto;
import Dto.CommentDto;
import Dto.MemberDto;
import Dao.BbsDao;
import Dao.freeBoardDao;
@WebServlet("/freeb")
public class freeBoardController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doProcess(req, resp);
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//확인용문자열
		System.out.println("자유게시판 doProcess");
		
		String param = req.getParameter("param");
		
		if (param.equals("toFreeboardlist")) {
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			
			int page = 0;
			if(spage != null && !spage.equals("")) {	
				page = Integer.parseInt(spage);
			}
			if(choice == null) {
				choice = "";
			}
			if(search == null) {
				search = "";	
			}		
			
			freeBoardDao dao = freeBoardDao.getInstance();
			List<BbsDto> list = dao.getFreeBoardPagingList(choice, search, page);
			req.setAttribute("list", list);
			
			int len = dao.getAllReport(choice, search);	
			int adPage = len / 10;		// 23 -> 2
			if((len % 10) > 0){
				adPage = adPage +1;
			}
			req.setAttribute("len", len + "");
			req.setAttribute("adPage", adPage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("search", search);
			req.setAttribute("choice", choice);
			req.setAttribute("content", "./freeboard/freeBoardList");
			forward("index.jsp", req, resp);	
		}
		
		else if (param.equals("writeFree")) {
			req.setAttribute("content", "./freeboard/writeFree");
			
			forward("index.jsp", req, resp);	
		}
		
		else if (param.equals("openDetail")) {
			System.out.println("openDetail 실행");
			
			
					////////////댓글 관련 //////////
					//부모글의 seq값 
					int seq = Integer.parseInt(req.getParameter("seq"));
					//댓글의 pageNumber
					String pageNumber = req.getParameter("pageNumber");
					int page = 0;
					
					if(pageNumber != null) {
						page = Integer.parseInt(pageNumber);
					}
					
					CommentDao cmt = CommentDao.getInstance();
					List<CommentDto> list = cmt.CommentList(page, seq, 1);
					
					// 작성한 글의 총 갯수 가져오기
					int len = cmt.getAllComment(seq, 1);
					
					//출력될 페이지 수 구하기 
					int viewPage = len / 5;
					if(len % 5 > 0 )
						viewPage += 1;
					
					
			// 디테일 정보 출		
			freeBoardDao dao = freeBoardDao.getInstance();
			BbsDto dto = dao.getFree(seq);
			req.setAttribute("list", list);
			req.setAttribute("page", page+"");
			req.setAttribute("viewPage", viewPage+"");
			req.setAttribute("detail", dto);
			req.setAttribute("content", "./freeboard/detailView");
			
			forward("index.jsp", req, resp);
		}
		
		else if (param.equals("updateFree")) {
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			req.setAttribute("content", "./freeboard/updateFree");
			forward("index.jsp", req, resp);	
		}
		
		else if (param.equals("deleteFree")) {
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			freeBoardDao dao = freeBoardDao.getInstance();
			boolean isS	= dao.deleteFree(seq);
			if(isS) {
				System.out.println("삭제성공함");
				resp.sendRedirect("freeb?param=toFreeboardlist");
			} else {
				System.out.println("삭제 실패함....");
				resp.sendRedirect("freeb?param=toFreeboardlist");
			}
			
		}
		
		
}
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
	RequestDispatcher dispatch = req.getRequestDispatcher(arg);
	dispatch.forward(req, resp);			
}
		
		
		
		
		
		
}


