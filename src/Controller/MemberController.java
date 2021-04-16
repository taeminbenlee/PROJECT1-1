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

import Dao.MemberDao;
import Dto.MemberDto;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns = "/mem")		// namespace
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		System.out.println("Member Controller doProcess!");
		req.setCharacterEncoding("utf-8");
		String param = req.getParameter("param");

// TODO 메인페이지로 이동
		if (param.equals("toIndex")) {

			resp.sendRedirect("index.jsp");

		} // end toIndex
		
// TODO 정보로 이동
		if (param.equals("Info")) {

			resp.sendRedirect("index.jsp#info");

		} // end Info	
		
// TODO 유용한 정보로 이동
		if (param.equals("useful")) {

			resp.sendRedirect("index.jsp#useful");

		} // end Info		
		
				
		//TODO 회원가입 작성완료 후 작업 (DB등록)
	      if(param.equals("addMemberAf")) {
	         System.out.println("addMember process!");
	         MemberDao dao = MemberDao.getInstance();
	         String myid = req.getParameter("myid");
	         String pwd = req.getParameter("pwd");
	         String email = req.getParameter("email");
	         String myname = req.getParameter("myname");
	         String phonenum = req.getParameter("phonenum");
	         
	         MemberDto dto = new MemberDto(0, myid, pwd, email, myname, phonenum, 0);
	         boolean b = dao.addMember(dto);
	         
	         if(b) {
	            System.out.println("회원가입 성공");
	            resp.sendRedirect("index.jsp");
	         }else {
	            System.out.println("회원가입 실패");
	            resp.sendRedirect("index.jsp?content=./login/regi");
	         }
	         
	      }//end addMemberAf

		
		
//TODO 아이디 중복 확인
		if(param.equals("idCheck")) {
			System.out.println("MemberController idCheck");
			
			String Id = req.getParameter("id");
			System.out.println("id = "+Id);
			
			MemberDao dao = MemberDao.getInstance();
			boolean b = dao.getId(Id);
			
			String str = "NO";
			if(b == false) {
				str = "YES";
			}
			
			JSONObject jObj = new JSONObject();
			jObj.put("msg", str);
			
			resp.setContentType("application/x-json; charset=UTF-8");
			resp.getWriter().print(jObj);
		}//end getId

		

//TODO 로그인 시 아이디 찾기 
		if(param.equals("findId")) {
			System.out.println("in Find Id ");
			String email = req.getParameter("email");
			System.out.println("email : " + email);
			
			MemberDao dao = MemberDao.getInstance();
			String findId = dao.getLogId(email);
			
			System.out.println("findId : "+findId);
			
			JSONObject jObj = new JSONObject();
			jObj.put("findId", findId);
			resp.setContentType("applictaion/x-json; charset=UTF-8"); //타입 맞춰주기
			resp.getWriter().print(jObj); // 날리기
		}
		

	
		
		
		
		
// TODO 일반 로그인 
		if (param.equals("loginAf")) {
			System.out.println("MemberController loginAf");
			
			String Id = req.getParameter("id");
			String pwd = req.getParameter("password");
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto mem = dao.login(Id, pwd);
			
			req.getSession().setAttribute("login", mem);
			
			if(mem == null) {
				req.setCharacterEncoding("utf-8");
				resp.setContentType("text/html; charset=utf-8");
				PrintWriter pw = resp.getWriter();		
				pw.println("<html>");		
				pw.println("<head>");
				pw.println("<title>제목</title>");		
				pw.println("</head>");				
				pw.println("<body>");
				
				pw.println("<script>");
				pw.println("alert('아이디 혹은 비밀번호를 확인하세요')");
				pw.println("location.href = './mem?param=toIndex'");
				pw.println("</script>");
				pw.println("</body>");
				pw.println("</html>");		
				pw.close();		
			} else {
				resp.sendRedirect("mem?param=toIndex");
			}

		} // end loginAf
		
		
		
//TODO 카카오 로그인 서비스
			if(param.equals("kakaoLog")) {
				System.out.println("카카오로 로그인 프로세스 실행");
				
				String email = req.getParameter("email");
				String name = req.getParameter("name");

				int recommendIdNum = email.indexOf("@");
				String recommendId = email.substring(0, recommendIdNum);
		 
				System.out.println("name :"+name);
				System.out.println("email :"+email);
				System.out.println("recommendId :"+recommendId);
				MemberDao dao = MemberDao.getInstance();

				boolean b = dao.getId(recommendId);
				
				//가입한 아이디가 있다면 바로 로그인 
				if(b) {
					MemberDto mem = dao.kakaoLogin(recommendId);
					req.getSession().setAttribute("login", mem);
					resp.sendRedirect("mem?param=toIndex");
				} 
				
				else {
					//가입한 아이디가 없다면 받아온 정보를 가지고 회원가입 시켜주기
					//임의의 패스워드 만들어주기
					int ipwd = (int)(Math.floor(Math.random()*1000000)+100000);
			    		if(ipwd>1000000){
			    			ipwd = ipwd-100000;
			    		}			
			    		
			    	String pwd = ipwd+"";
					System.out.println("생성된 비밀번호 : "+pwd);
			    		
					MemberDto dto = new MemberDto(0, recommendId, pwd, email, name, "0", 0);
					boolean isS = dao.addMember(dto);
	
					if(isS) {
						MemberDto mem = dao.kakaoLogin(recommendId);
						req.getSession().setAttribute("login", mem);
						resp.sendRedirect("mem?param=toIndex");
					}
				}

			}//end kakaoLogin

		//TODO 이메일 가져오기
		if(param.equals("getEmail")) {
		
		}//end getEmail
		
		
//TODO 로그아웃
		if(param.equals("logout")) {
			boolean b = false;
			
			HttpSession session = req.getSession();
			MemberDto mem = (MemberDto) session.getAttribute("login");
			System.out.println("아이디1 : "+ mem.getMyid());
			
			session.invalidate();
			session = req.getSession();
			
			if((MemberDto)session.getAttribute("login") != mem) {
				System.out.println("로그아웃완료");
				b = true;
			}
			
			req.setAttribute("outb", b);
			req.getRequestDispatcher("index.jsp").forward(req, resp);;
			
		}//end logout
		
		
		
		

//===================================회원관리================================================//
//TODO 관리자가 맴버 목록을 볼수있는 콘트롤러~~~~~~
		if(param.equals("allMemberList")) {
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
			
			MemberDao dao = MemberDao.getInstance();
				List<MemberDto> list = dao.getAllMemberList(choice, search, page);
				
				req.setAttribute("list", list);
				
				int len = dao.getAllMemberCount(choice, search);
				int memPage = len/10;
				if((len%10)>0) {
					memPage = memPage+1;
				}
				
			req.setAttribute("memPage", memPage+"");
			req.setAttribute("pageNumber", page + "");
			req.setAttribute("search", search);
			req.setAttribute("choice", choice);
			req.setAttribute("len", len + "");
			req.setAttribute("content", "./Manager/memberListPage");
			
			forward("index.jsp", req, resp);
			
		}
		//맴버정보 디테일...!!
		if(param.equals("openMemberDetail")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto dto = dao.getMemberDetail(seq);
			
			req.setAttribute("detail", dto);
			req.setAttribute("content", "./Manager/memberDetail");
			forward("index.jsp", req, resp);
		}
		//맴버정보 수정 페이지로 이동~~
		if(param.equals("updateMember")) {
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			req.setAttribute("content", "./Manager/memberUpdate");
			forward("index.jsp", req, resp);
		}
		//정보 수정후~~~
		if(param.equals("updateAf")) {
			System.out.println("회원정보수정실행중...");
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			String Myid = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String email = req.getParameter("email");
			String myname= req.getParameter("name");
			String phonenum = req.getParameter("phonenum");
			MemberDao dao = MemberDao.getInstance();
			boolean isS = dao.updateMember(seq, pwd, email, myname, phonenum);
			if (isS) {
				if(Myid.equals("admin")) {
				resp.sendRedirect("mem?param=allMemberList");
				} 
				if(!Myid.equals("admin")){
					resp.sendRedirect("mem?param=myMemberInfo&Myid="+ Myid);
				}
			}
		}
		//맴버를 아예 걍 삭제해버려~!
		if(param.equals("deleteMember")) {
			System.out.println("회원정보삭제실행중...");
			int seq = Integer.parseInt(req.getParameter("seq").trim());
			MemberDao dao = MemberDao.getInstance();
			
			boolean isS = dao.deleteMember(seq);
			if(isS) {
				resp.sendRedirect("mem?param=allMemberList");
			}	
		}
		
		//개인 회원 탈퇴
		if(param.equals("deleteMe")) {
			System.out.println("회원정보삭제실행중...");
			int seq = Integer.parseInt(req.getParameter("seq").trim());

			MemberDao dao = MemberDao.getInstance();
			boolean isS = dao.deleteMember(seq);
			if(isS) {
				
				boolean b = false;
							
				HttpSession session = req.getSession();
				MemberDto mem = (MemberDto) session.getAttribute("login");
				session.invalidate();
				session = req.getSession();
							
				if((MemberDto)session.getAttribute("login") != mem) {
						System.out.println("로그아웃완료");
						b = true;
					}
							
				req.setAttribute("outb", b);
				req.getRequestDispatcher("index.jsp").forward(req, resp);;
			}	
		}
		
		
		//회원이 자기 정보 보는곳~
		if(param.equals("myMemberInfo")) {
		
			String Myid = req.getParameter("Myid");
			
			System.out.println("Myid:"+Myid);
			MemberDao dao = MemberDao.getInstance();
			
			MemberDto dto = dao.getMyDetail(Myid);
			
			req.setAttribute("detail", dto);
			req.setAttribute("content", "./Manager/memberDetail");
			forward("index.jsp", req, resp);
			
		}
		

			
	}
	
	public void forward(String arg, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(arg);
		dispatch.forward(req, resp);			
	}

}
