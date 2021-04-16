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

@WebServlet("/manager") 
public class ManagerController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}




	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		System.out.println("Page Controller doProcess!");
		String param = req.getParameter("param");


		//TODO 메니저 rescuedlist 
		if (param.equals("managerRescuedList")) {	
			System.out.println("In managerRescuedList Param");
			
			HttpSession session = req.getSession(); 
			MemberDto mem = (MemberDto)session.getAttribute("login");
			
			//매니저 아이디로 로그인 할 때만 접근 가능 
			if(mem.getMymanager() == 1) {
			
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
			req.setAttribute("content", "./Manager/managerRescued");
			
			//보내기
			forward("index.jsp", req, resp);
			}
		}
		
		
		//TODO 입양진행중
				else if(param.equals("managerRescuedAdoptGo")) {
					System.out.println("In managerRescuedAdoptGo Param!");
					int seq = Integer.parseInt(req.getParameter("seq"));

					RescuedDogDao dao = RescuedDogDao.getInstance();
					RescuedDogDto dto = dao.rescuedDetail(seq);
					String title = dto.getTitle();
					
					dao.conditionChange1(seq, title); 
					resp.sendRedirect("manager?param=managerRescuedList");
				}


		//TODO 매니저 페이지 글 삭제
		else if(param.equals("managerRescuedDel")) {
			System.out.println("In managerRescuedDel Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));
			String title = req.getParameter("title");

			RescuedDogDao dao = RescuedDogDao.getInstance();

			dao.deleteRescued(seq); 
			resp.sendRedirect("manager?param=managerRescuedList");
		}


		//TODO 매니저 페이지 입양완료
		else if(param.equals("managerRescuedAdoptOk")) {
			System.out.println("In managerRescuedAdoptOk Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));

			RescuedDogDao dao = RescuedDogDao.getInstance();
			RescuedDogDto dto = dao.rescuedDetail(seq);
			String title = dto.getTitle();

			dao.conditionChange2(seq, title); 
			resp.sendRedirect("manager?param=managerRescuedList");
		}



		//TODO 매니저 입양신청 취소
		else if(param.equals("managerRescuedAdoptCan")) {
			System.out.println("In managerRescuedAdoptCan Param!");
			int seq = Integer.parseInt(req.getParameter("seq"));

			RescuedDogDao dao = RescuedDogDao.getInstance();
			RescuedDogDto dto = dao.rescuedDetail(seq);
			String title = dto.getTitle();

			dao.conditionChange0(seq, title); 
			resp.sendRedirect("manager?param=managerRescuedList");
		}






	} // doProcess

	//TODO 값을 가지고 이동하기 위한 forward 함수
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);			
	}
}


