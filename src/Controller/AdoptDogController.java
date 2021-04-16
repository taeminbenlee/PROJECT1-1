package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AdoptDogDao;
import Dto.AdoptDogDto;
import Dto.MemberDto;

@WebServlet(urlPatterns = "/adopt")
public class AdoptDogController extends HttpServlet{

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doProcess(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doProcess(req, resp);
   }

   public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
      
      System.out.println("AdoptController doProcess");
      
      req.setCharacterEncoding("utf-8");
      
      String param = req.getParameter("param");
      
      // 관리자 신청서 확인목록
      if(param.equals("getAdoptPagingList")){
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
         
         
         AdoptDogDao dao = AdoptDogDao.getInstance();
         List<AdoptDogDto> list = dao.adoptPagingList(choice, search, page);
         req.setAttribute("list", list);
         
         int len = dao.getAllAdoptDog(choice, search);
         int addPage = len / 10;
            if((len % 10) > 0) {
               
               addPage = addPage +1;
            }
         
         req.setAttribute("len", len + "");
         req.setAttribute("addPage", addPage + "");
         req.setAttribute("pageNumber", page + "");
         req.setAttribute("search", search);
         req.setAttribute("choice", choice);
         req.setAttribute("content", "./adopt/adoptList");
            
            forward("index.jsp", req, resp);      
         
         System.out.println("컨트롤러 총 글자 길이 : "+len);
         System.out.println("컨트롤러 표시되는 페이지 수  : " + addPage);

      
      //자신의 입양신청서 확인목록
      }else if(param.contentEquals("myAdoptPagingList")){
         System.out.println("In myAdoptPagingList");
         Object ologin = req.getSession().getAttribute("login");
         if(ologin == null){
            
            resp.sendRedirect("login.jsp");
         }
         MemberDto mem = null;
         mem = (MemberDto)ologin;
         System.out.println("myAdoptPagingList 1");
         
         String choice = req.getParameter("choice");
         String search = req.getParameter("search");
         String spage = req.getParameter("pageNumber");
         String myid = mem.getMyid();

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
         
         AdoptDogDao dao = AdoptDogDao.getInstance();
         List<AdoptDogDto> list = dao.myAdoptPagingList(myid, choice, search, page);
      
         req.setAttribute("list", list);
         
         int len = dao.getMyAdoptDog(myid, choice, search);
         int addPage = len / 10;
            if((len % 10) > 0) {
               addPage = addPage +1;
            }
         
         req.setAttribute("len", len + "");
         req.setAttribute("addPage", addPage + "");
         req.setAttribute("pageNumber", page + "");
         req.setAttribute("search", search);
         req.setAttribute("choice", choice);
         req.setAttribute("content", "./adopt/adoptList");
         forward("index.jsp", req, resp);      
      
      
      // 입양신청입력 폼
      }else if(param.equals("writeAdopt")) {
         System.out.println("In WriteAdopt Param!");
         String seq = req.getParameter("seq");
         
         req.setAttribute("seq", seq);
         req.setAttribute("content", "./adopt/writeAdopt");
         forward("index.jsp", req, resp);
      }
      // 입양신청 폼 입력 후 리스트로 이동
      else if(param.equals("writeAdoptAf")) {
         
         int ref = Integer.parseInt(req.getParameter("seq"));
         String myid = req.getParameter("myid");
         String rdate = req.getParameter("rdate");
         String mycontent = req.getParameter("mycontent");
         String phoneNum = req.getParameter("phoneNum");
         String title = req.getParameter("title");
         AdoptDogDao dao = AdoptDogDao.getInstance();
         AdoptDogDto dto = new AdoptDogDto(myid, rdate, mycontent, phoneNum, title, ref);
         boolean isS = dao.writeAdopt(dto);
         
         if(isS) {
            resp.sendRedirect("rescued?param=adoptfinish&seq="+ref);            
         }
         
   
      }
      // 입양신청 리스트에서 글 클릭시 Detail 화면
      else if(param.equals("adoptDetail")) {
         String sseq = req.getParameter("seq");
         int seq = Integer.parseInt(sseq.trim());
         
         AdoptDogDao dao = AdoptDogDao.getInstance();
         
         System.out.println("seq:-------" + seq);
         AdoptDogDto dto = dao.adoptDetail(seq);
         //System.out.println("dao:-------" + dto.toString());
         
         req.setAttribute("detail", dto);   
         req.setAttribute("content", "./adopt/adoptDetail");         
         
         forward("index.jsp", req, resp);
         
      }
      // 매니저가 신청글을 수정하는 경우 
      else if(param.equals("adoptUpdate")) {
         System.out.println("In adoptUpdate");

         int seq = Integer.parseInt(req.getParameter("seq").trim());
         AdoptDogDao dao = AdoptDogDao.getInstance();
         
         AdoptDogDto dto = dao.getAdopt(seq);
         
         req.setAttribute("adopt", dto);
         System.out.println(dto.toString());
         
         req.setAttribute("seq", seq);
         req.setAttribute("content", "./adopt/adoptUpdate");   
         
         forward("index.jsp", req, resp);
      }
      

      
      // 수정 후 등록 -> 리스트 이동
      else if(param.equals("adoptUpdateAf")) {
         String sseq = req.getParameter("seq");
         
         int seq = Integer.parseInt(req.getParameter("seq").trim());
         int manager = Integer.parseInt(req.getParameter("manager"));
         String title = req.getParameter("title");
         String mycontent = req.getParameter("mycontent");
         String rdate = req.getParameter("rdate");
         String phoneNum = req.getParameter("phoneNum");
         
         AdoptDogDao dao = AdoptDogDao.getInstance();
         boolean isS = dao.updateAdopt(seq, title, mycontent, rdate, phoneNum);
         
         
         
         if(!isS) {
            resp.sendRedirect("adopt?param=adoptUpdate&seq=" + sseq);
         } else {
            
            if(manager == 1) 
               resp.sendRedirect("adopt?param=getAdoptPagingList");               
             else 
               resp.sendRedirect("adopt?param=myAdoptPagingList");               
         }
         
      }
      // 삭제
      else if(param.equals("myAdoptDelete")){
         int seq = Integer.parseInt(req.getParameter("seq"));
         System.out.println("seq: " + seq);
         AdoptDogDao dao = AdoptDogDao.getInstance();
         boolean isS = dao.deleteAdopt(seq);
         
         if(isS) {dao.deleteAdopt(seq);
            resp.sendRedirect("adopt?param=myAdoptPagingList");   
         }
         
      }
   
      // 매니저 글 삭제 버튼 누를 시
      else if(param.equals("AdoptDelete")) {
         int seq = Integer.parseInt(req.getParameter("seq"));
         System.out.println("seq: " + seq);

         AdoptDogDao dao = AdoptDogDao.getInstance();
         boolean isS = dao.deleteAdopt(seq);
         
         if(isS) {dao.deleteAdopt(seq);
         
            resp.sendRedirect("adopt?param=getAdoptPagingList");   
         }
         
      }
      
   
   }
      
   

   private void forward(String arg, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
      RequestDispatcher dispatch = req.getRequestDispatcher(arg);
      dispatch.forward(req, resp);      
   }
}