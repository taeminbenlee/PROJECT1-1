package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CommentDao;
import Dto.CommentDto;
import Dto.RescuedDogDto;


@WebServlet("/comment") 
public class CommentController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In CommentController");
		req.setCharacterEncoding("utf-8");

		// 컨트롤러로 받아오는 값을 저장
		String param = req.getParameter("param");
		
		//!!!!!!!!보내줄 URL과 urlParam(Detail)값을 받아온다!!!!!!!!
		
		//TODO 댓글 쓰기 
		if(param.equals("write")) {
			System.out.println("In write param");
			String myId = req.getParameter("myId");
			String content = req.getParameter("content");
			int seq = Integer.parseInt(req.getParameter("seq")); 
			int free = Integer.parseInt(req.getParameter("free"));
			
			String url = req.getParameter("url");
			String urlParam = req.getParameter("urlParam");

			
			//확인하기
			System.out.println("아이디 : "+myId);
			System.out.println("내용 : "+content);
			System.out.println("부모글 시퀀스 : "+seq);
			System.out.println("Free값 : "+free);
			
			//부모글 seq 값을 댓글의 ref에 넣는다
			CommentDto dto = new CommentDto(myId, content, seq, free);
			CommentDao dao = CommentDao.getInstance();
			dao.writeComment(dto);
			resp.sendRedirect(url+"?param="+urlParam+"&seq="+seq);
		}
		
		
		//TODO 댓글 삭제 
		else if(param.equals("delete")) {
			System.out.println("In delete Param!");
			int commSeq = Integer.parseInt(req.getParameter("commSeq")); 
			int seq = Integer.parseInt(req.getParameter("seq")); 
			String url = req.getParameter("url");
			String urlParam = req.getParameter("urlParam");
			
			//확인하기
			System.out.println("댓글의 시퀀스 : "+commSeq);
			System.out.println("부모글 시퀀스 : "+seq);
						
			CommentDao dao = CommentDao.getInstance();
			dao.deleteComment(commSeq);
			resp.sendRedirect(url+"?param="+urlParam+"&seq="+seq);
		}
		
		
		//TODO 댓글 수정 
		else if(param.equals("update")) {
			System.out.println("In update Param!");
			int commSeq = Integer.parseInt(req.getParameter("commSeq")); 
			int seq = Integer.parseInt(req.getParameter("seq")); 
			String content = req.getParameter("content"); //수정내용
			
			String url = req.getParameter("url");
			String urlParam = req.getParameter("urlParam");
			CommentDao dao = CommentDao.getInstance();
			dao.updateComment(content, commSeq);
			
			resp.sendRedirect(url+"?param="+urlParam+"&seq="+seq);
		}
		
		
	

	} // doProcess
	
	//TODO 값을 가지고 이동하기 위한 forward 함수
		public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
			RequestDispatcher dispatch = req.getRequestDispatcher(arg);
			dispatch.forward(req, resp);			
		}
} // class
