package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dto.MemberDto;
import Dto.abandonedDogDto;
import Dao.abandonedDogDao;	


@WebServlet(urlPatterns = "/abandog")
public class abandonedDogController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doProcess(req, resp);
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//한글깨짐방지
		req.setCharacterEncoding("utf-8");
		//확인용문자열
		System.out.println("abandonedController doProcess");
		
		String param = req.getParameter("param");
		// 관리자가 모든 유기견 제보글 확인목록으로~
		if(param.equals("getAllAbandonedDogPagingList")) {
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
			
			abandonedDogDao dao = abandonedDogDao.getInstance();
			List<abandonedDogDto> list = dao.getAbandonedDogPagingList(choice, search, page);
			
			
			/*
			 * for (abandonedDogDto ab : list) { System.out.println(ab.toString()); }
			 */
			
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
			req.setAttribute("content", "./abandonedDog/abandonedDogList");
			
			forward("index.jsp", req, resp);	
			
		}
		//나 자신의 유기견 신고글만 확인하는 페이지
		else if(param.equals("getMyAbandonedDogPagingList")) {
			Object ologin = req.getSession().getAttribute("login");
			if(ologin == null){
				
				resp.sendRedirect("login.jsp");
			}
			MemberDto mem = null;
			mem = (MemberDto)ologin;
			
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spage = req.getParameter("pageNumber");
			String myid = mem.getMyid();
			System.out.println("myid: "+myid);
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
			
			abandonedDogDao dao = abandonedDogDao.getInstance();
			List<abandonedDogDto> list = dao.getMyAbandonedDogPagingList(myid, choice, search, page);
			
			
			/*
			 * for (abandonedDogDto ab : list) { System.out.println(ab.toString()); }
			 */
			
			req.setAttribute("list", list);
			
			int len = dao.getMyReport(myid, choice, search);			
			int adPage = len / 10;		// 23 -> 2
			if((len % 10) > 0){
				adPage = adPage +1;
			}
			/* req.setAttribute(name, o); */
			req.setAttribute("adPage", adPage + "");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("search", search);
			req.setAttribute("len", len+ "");
			req.setAttribute("choice", choice);
			req.setAttribute("content", "./abandonedDog/abandonedDogList");
			
			forward("index.jsp", req, resp);	
		}
		//유기견 제보 글 작성페이지로~
		else if(param.equals("writeReport")) {
			req.setAttribute("content", "./abandonedDog/writeReport");
			
			forward("index.jsp", req, resp);	
		}
		
		//메인에서 클릭시 유기견 제보 메인으로~
		else if(param.equals("reportADPage")) {
			req.setAttribute("content", "./abandonedDog/reportAbandonedDogPage");
			
			forward("index.jsp", req, resp);	
		}
		//제보글 클릭시~
		else if(param.equals("openReportDetail")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			
			abandonedDogDao dao = abandonedDogDao.getInstance();
			abandonedDogDto dto = dao.reportDetail(seq);
			
			req.setAttribute("detail", dto);
			
			req.setAttribute("content", "./abandonedDog/reportDetail");
			
			forward("index.jsp", req, resp);	
			
		}
		//수정
		else if(param.equals("updateReport")) {
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			req.setAttribute("content", "./abandonedDog/reportUpdate");
			forward("index.jsp", req, resp);	
		}
		//삭제
		else if(param.equals("deleteReport")) {
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			abandonedDogDao dao = abandonedDogDao.getInstance();
			boolean isS = dao.deleteReport(seq);
			
			if (isS) {
				resp.sendRedirect("abandog?param=getMyAbandonedDogPagingList");
			}
			
		}
		
		//매니저 창에서 삭제
		else if(param.equals("ManagerDeleteReport")) {
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			abandonedDogDao dao = abandonedDogDao.getInstance();
			boolean isS = dao.deleteReport(seq);
			
			if (isS) {
				resp.sendRedirect("abandog?param=getAllAbandonedDogPagingList");
			}
			
		}
		
	
		else if(param.equals("authorizeAf")) {
			System.out.println("넘어옴");
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			abandonedDogDao dao = abandonedDogDao.getInstance();
			abandonedDogDto dto = dao.getReport(seq);
			String Newfilename = dto.getNewfilename();
			
			boolean isS = dao.authorizeUpdate(seq);
			if (isS) {
				resp.sendRedirect("rescued?param=RescuedWrite&Newfilename="+Newfilename);

			}
		}
	
		 
		
		
		
		
	}
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);			
	}
}
