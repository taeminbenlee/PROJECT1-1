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
import javax.servlet.http.HttpSession;

import Dao.RescuedDogDao;
import Dto.MemberDto;
import Dto.RescuedDogDto;



@WebServlet("/rescued") 
public class RescuedController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In RescuedController");
		req.setCharacterEncoding("utf-8");
		
		// 컨트롤러로 받아오는 값을 저장
		String param = req.getParameter("param"); 
		
		
		
		
		
		
		
//////////// param값에 따른 작업 수행 ////////////////
		
// TODO 메인페이지로 이동
		if(param.equals("home")) {
			resp.sendRedirect("index.jsp");
		} 
		
		
// TODO 입양 리스트 작성
		if(param.equals("RescuedWrite")) {
			System.out.println("In RescuedWrite Param!");
			String Newfilename = req.getParameter("Newfilename");
			req.setAttribute("fileName", Newfilename);
			req.setAttribute("content", "./rescued/RescuedWrite");
			forward("index.jsp", req, resp);
		} 		
		
		
// TODO RescuedList로 이동
		else if(param.equals("list")) {
			System.out.println("In List Param!");
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String pageNumber = req.getParameter("pageNumber");
			
			int page = 0;

			if(choice == null){
				choice = "";
			} 
			if(search == null){
				search = "";
			}
			if(pageNumber != null) {
				page = Integer.parseInt(pageNumber);
			}
			

			// 객채 불러오기
			RescuedDogDao dao = RescuedDogDao.getInstance();
			List<RescuedDogDto> list = dao.RescuedList(choice, search, page);
			
			// 작성한 글의 총 갯수 가져오기
			int len = dao.getAllList(choice, search);
			
			//출력될 페이지 수 구하기 
			int viewPage = len / 10;
			if(len % 10 > 0 )
				viewPage += 1;
			
			// 확인용 출력
			System.out.println("검색목록: " + choice);
			System.out.println("검색명: " + search);
			System.out.println("현재 페이지 Index: " + page);
			System.out.println("글의 총 갯수 : " + len);
			System.out.println("출력될 페이지 수: " + viewPage);
			
			// 짐싸기
			req.setAttribute("list", list);
			req.setAttribute("page", page+"");
			req.setAttribute("viewPage", viewPage+"");
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
			req.setAttribute("content", "./rescued/RescuedList");
			
			//보내기
			forward("index.jsp", req, resp);
		}
		
		
		
		
		
// TODO 디테일 정보 보기
		else if(param.equals("detail")) {
			System.out.println("In Detail Param!");
			
			int seq = Integer.parseInt(req.getParameter("seq"));

			HttpSession session = req.getSession();  
			MemberDto mem = (MemberDto)session.getAttribute("login");
			RescuedDogDao dao = RescuedDogDao.getInstance();
			int manager = 0;
			String id = "";
			
			//로그인했다면
			if(mem != null) {
				 manager = mem.getMymanager();
				 id = mem.getMyid();
			}
			
			RescuedDogDto dto = dao.rescuedDetail(seq);		
		
			//확인하기
			System.out.println("seq : " + seq);
			System.out.println("login Id : " + id);
			System.out.println("manager(1) : " + manager);
			System.out.println("입양진행상태 : " + dto.getCondition());
			System.out.println("파일 명 : " + dto.getFilename());
			
			//조회수 올리기
			dao.vcountUp(seq);
			
			req.setAttribute("dto", dto);
			req.setAttribute("manager", manager);
			req.setAttribute("id", id);
			req.setAttribute("content", "./rescued/RescuedDetail");

			forward("index.jsp", req, resp);
		}
		
		
		
// TODO 글 삭제
		else if(param.equals("deleteRescued")) {
			System.out.println("In Adoptcancle Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");
			
			RescuedDogDao dao = RescuedDogDao.getInstance();
			
			dao.deleteRescued(seq); 
			resp.sendRedirect("rescued?param=list");
		}
	
		
		
// TODO 글 수정
		else if(param.equals("update")) {
			System.out.println("In Update Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));
			req.setAttribute("seq", seq);
			
			
			RescuedDogDao dao = RescuedDogDao.getInstance();
			RescuedDogDto dto = dao.rescuedDetail(seq);
			req.setAttribute("dto", dto);
			req.setAttribute("content", "./rescued/RescuedUpdate");
			
			forward("index.jsp", req, resp);
		}		
		
		
		
		
//TODO 입양신청서로 이동		
		else if(param.equals("adopt")){
			System.out.println("In Adapt Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			RescuedDogDao dao = RescuedDogDao.getInstance();
			RescuedDogDto dto = dao.rescuedDetail(seq);
			String title = dto.getTitle();
			
			req.setAttribute("seq", seq);
			//req.setAttribute("title", title); 
			forward("adopt.jsp", req, resp);
		}
		
		
		
		
//TODO 입양신청 완료
		else if(param.equals("adoptfinish")) {
			System.out.println("In Adoptfinish Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));

			RescuedDogDao dao = RescuedDogDao.getInstance();
			RescuedDogDto dto = dao.rescuedDetail(seq);
			String title = dto.getTitle();
			
			dao.conditionChange1(seq, title); 
			resp.sendRedirect("rescued?param=list");
		}
		
		
//TODO 입양신청 취소
		else if(param.equals("cancle")) {
			System.out.println("In Cancle Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));
	
			RescuedDogDao dao = RescuedDogDao.getInstance();
			RescuedDogDto dto = dao.rescuedDetail(seq);
			String title = dto.getTitle();
			
			dao.conditionChange0(seq, title); 
			resp.sendRedirect("rescued?param=list");
		}

		
		
//TODO 입양 완료
		else if(param.equals("adoptOk")) {
			System.out.println("In AdoptOk Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));

			RescuedDogDao dao = RescuedDogDao.getInstance();
			RescuedDogDto dto = dao.rescuedDetail(seq);
			String title = dto.getTitle();
			
			dao.conditionChange2(seq, title); 
			resp.sendRedirect("rescued?param=list");
		}
		
		
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
				pw.println("location.href = 'rescued?param=list'");
				pw.println("</script>");
				
				pw.println("</body>");
				pw.println("</html>");		
				pw.close();		
			}
		
		

	} // doProcess
	
//TODO 값을 가지고 이동하기 위한 forward 함수
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);			
	}
	
	
} // class
