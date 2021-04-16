package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Dto.BbsDto;
import Dto.CommentDto;
import Dao.BbsDao;
import Dao.CommentDao;
import DB.DBClose;
import DB.DBConnection;
import util.UtilEx;

@WebServlet(urlPatterns = "/bbs")	
public class BbsController extends HttpServlet {
	
	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess( req,  resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess( req,  resp);
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		mConfig = config;	// 업로드한 경로를 취득하기 위해서
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		System.out.println("BbsController doProcess!");
		
		String param = req.getParameter("param");


// TODO 커뮤니티 페이지로 이동
				if (param.equals("toBbslist")) {
				
					resp.sendRedirect("bbs?param=getPagingBbsList");
					
				} // end toBbslist
				
// TODO 글쓰기 탭으로 이동 
				if (param.equals("bbsWrite")) {
					System.out.println("bbsWrite 실행");
					
					resp.sendRedirect("index.jsp?content=./bbs/bbswrite");
					
					
				} // end bbsWrite
		
// TODO 디테일 페이지로 이동 
				if (param.equals("bbsDetail")) {
					System.out.println("bbsDetail 실행");
					
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
					List<CommentDto> list = dao.CommentList(page, seq, 1);
					
					// 작성한 글의 총 갯수 가져오기
					int len = dao.getAllComment(seq, 1);
					
					//출력될 페이지 수 구하기 
					int viewPage = len / 5;
					if(len % 5 > 0 )
						viewPage += 1;
					
					///////// 디테일 정보 출력 ////////
					
					BbsDao bbsdao = BbsDao.getInstance();
					BbsDto bbs = bbsdao.getBbs(seq);
					
					//조회수 증가 
					bbsdao.readcount(seq);
					
					//확인
					System.out.println("현재 댓글 Index: " + page);
					System.out.println("댓글 총 갯수 : " + len);
					System.out.println("출력될 댓글 페이지 수: " + viewPage);
					
					// 짐싸기
					req.setAttribute("list", list);
					req.setAttribute("page", page+"");
					req.setAttribute("viewPage", viewPage+"");
					req.setAttribute("bbs", bbs);
					req.setAttribute("content", "./bbs/bbsdetail");
					forward("index.jsp", req, resp);	
					
				} // end bbsDetail
				
// TODO 글삭제
				if (param.equals("deleteBbs")) {
					System.out.println("deleteBbs 실행");
					
					int seq = Integer.parseInt(req.getParameter("seq"));
					
					BbsDao dao = BbsDao.getInstance();
					
					boolean b = dao.deleteBbs(seq);
					
					if(b) {
						System.out.println("삭제성공");
					}
					
					resp.sendRedirect("bbs?param=getPagingBbsList");
					
				} // end deleteBbs
				
	
// TODO 조회수
		if (param.equals("readcount")) {
			 System.out.println("readcount 실행");
			 
			 int seq = Integer.parseInt(req.getParameter("seq"));
			  
			  BbsDao dao = BbsDao.getInstance(); 
			  dao.readcount(seq);
			  System.out.println("증가완료");
			  
			  req.getRequestDispatcher("index.jsp?content=./bbs/bbsdetail").forward(req, resp);
		  
		} // end readcount
		 
				
				
// TODO 업데이트 탭으로 이동
				if (param.equals("updateBbs")) {
					System.out.println("updateBbs 실행");
					
					int seq = Integer.parseInt(req.getParameter("seq"));
					
					BbsDao dao = BbsDao.getInstance();
					
					BbsDto bbs = dao.getBbs(seq);
					
					req.setAttribute("bbs", bbs);
					
					req.getRequestDispatcher("index.jsp?content=./bbs/bbsupdate").forward(req, resp);
					
				} // end updateBbs
				
				
				
//TODO 이미지 파일을 하지 않았다면
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
						pw.println("location.href = 'bbs?param=getPagingBbsList'");
						pw.println("</script>");
								
						pw.println("</body>");
						pw.println("</html>");		
						pw.close();		
				}
						
				

		/*
		 * if (param.equals("searchBbsList")) { System.out.println("searchBbsList 실행");
		 * 
		 * String choice = req.getParameter("choice"); String keword =
		 * req.getParameter("search");
		 * 
		 * 
		 * } end searchBbsList
		 */ 
				
// TODO 페이징으로 리스트 가져가기  
		if (param.equals("getPagingBbsList")) {
			System.out.println("getPagingBbsList 실행");
			System.out.println("getPagingBbsList 1/7");
			//BbsDao 싱글턴 인스턴스 생성 
			BbsDao dao = BbsDao.getInstance();
			
			//choice와 search 넘어옴 
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			
			//만약에 choice와 search가 값이 넘어오지 않는다면 
			if(choice == null){
				choice = "";
			}
			if(search == null){
				search = "";
			}
			
			System.out.println("choice : "+ choice);
			System.out.println("search : "+ search);
			
			//pageNumber 넘어옴  (spageNumber에 일단 받아둠)
			String spageNumber =req.getParameter("pageNumber");
			System.out.println("getPagingBbsList 2/7");
			
			//pageNumber는 0으로 설정, 만약에 spageNumber에 값이 들어가 있다면 pageNumber는 spageNumber로 변경 
			int pageNumber = 0;
			if(spageNumber != null && !spageNumber.equals("")){
				pageNumber = Integer.parseInt(spageNumber);
			}
			System.out.println("pageNumber : "+ pageNumber);
			System.out.println("getPagingBbsList 3/7");
			
			//list 설정 
			List<BbsDto> list = dao.getBbsPagingList(choice, search, pageNumber);
			System.out.println("listsize : "+ list.size());
			System.out.println("getPagingBbsList 4/7");
			
			//가져올 데이터의 길이 len을 설정함 
			int len = dao.getAllBbs(choice, search);
			System.out.println("len : "+ len);
			System.out.println("getPagingBbsList 5/7");
			
			//bbspage 설정 
			int bbspage = len/10;
			if((len%10) > 0){
				bbspage = bbspage +1;
			}
			System.out.println("bbspage : "+ bbspage);
			System.out.println("getPagingBbsList 6/7");
			
			//모든 데이터 싸서 보내기 
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
			req.setAttribute("pageNumber", pageNumber+"");
			req.setAttribute("len", len+"");
			req.setAttribute("bbspage", bbspage+"");
			req.setAttribute("list", list);
			
			System.out.println("getPagingBbsList 7/7");
			req.getRequestDispatcher("index.jsp?content=./bbs/bbslist").forward(req, resp);
			
		}//end getPagingBbsList

	}//end doProcess
	
	
	//TODO 값을 가지고 이동하기 위한 forward 함수
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);			
	}
}//end class

























