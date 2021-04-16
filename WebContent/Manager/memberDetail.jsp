<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="description">
  <meta content="" name="keywords">
<title>회원 디테일</title>
  <link href="assets_2/img/favicon.png" rel="icon">
  <link href="assets_2/img/apple-touch-icon.png" rel="apple-touch-icon">


  <!-- Vendor CSS Files -->
  <link href="assets_2/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets_2/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="assets_2/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets_2/vendor/venobox/venobox.css" rel="stylesheet">
  <link href="assets_2/vendor/owl.carousel/assets_2/owl.carousel.min.css" rel="stylesheet">
  <link href="assets_2/vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets_2/css/style.css" rel="stylesheet">




</head>
<%
request.setCharacterEncoding("utf-8");
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");

%>
<%
MemberDto dto = (MemberDto)request.getAttribute("detail");
int seq = dto.getSeq();
%>
<body >

  <!-- ======= Mobile nav toggle button ======= -->
  <button type="button" class="mobile-nav-toggle d-xl-none"><i class="icofont-navigation-menu"></i></button>

  <!-- ======= Header ======= -->
  <header id="header" class="d-flex flex-column justify-content-center">

    <nav class="nav-menu">
      <ul>
        <li class="active"><a href="#hero"><i class="bx bx-home"></i> <span>Home</span></a></li>
        <br>
        <li><a href="#about"><i class="bx bx-user"></i> <span>About</span></a></li>
      </ul>
    </nav><!-- .nav-menu -->

  </header><!-- End Header -->

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex flex-column justify-content-center">
    <div class="container" data-aos="zoom-in" data-aos-delay="100">
      <h1>Welcome</h1>
       <p>환영합니다.&nbsp;&nbsp;<b><span class="typed" data-typed-items=<%=mem.getMyid() %>  ></span></b>님</p>
     <!--  <div class="social-links">
        <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
        <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
        <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
        <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
        <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
      </div> -->
    </div>
  </section><!-- End Hero -->

  <main id="main">

    <!-- ======= About Section ======= -->
    <section id="about" class="about" >
      <div class="container" data-aos="fade-up">

        <div class="section-title" style="padding-top: 150px">
          <h2>About</h2>
        </div>

        <div class="row">
          <div class="col-lg-4" >
                   <img src="assets_2/img/p.png" class="img-fluid" alt="" >
          </div>
          <div class="col-lg-8 pt-4 pt-lg-0 content">
            <h3></h3>
            <p class="font-italic">
                
            </p>
            <div class="row" style="padding-top: 45px">
              <div class="col-lg-6" style="font-size: 20px;">
                <ul>
                  <li><i class="icofont-rounded-right"></i> <strong>ID :&nbsp; </strong><%=dto.getMyid() %></li>
                  <br>
                  <li><i class="icofont-rounded-right"></i> <strong>Password :&nbsp;</strong><%=dto.getPwd() %></li>
                  <br>
                  <li><i class="icofont-rounded-right"></i> <strong>Name :&nbsp;</strong><%=dto.getMyname() %></li>
                  <br>
                
                </ul>
              </div>
              <div class="col-lg-6" style="font-size: 20px;">
                <ul>
                  <li><i class="icofont-rounded-right"></i> <strong>Phone :&nbsp;</strong><%=dto.getPhonenum() %></li>
                  <br>
                  <li><i class="icofont-rounded-right"></i> <strong>E-mail :&nbsp;</strong> <%=dto.getEmail() %></li>
              </ul>
              </div>
              
              <%if(mem.getMymanager() == 1){
            %>
            
              <div class="col-lg-6" style="font-size: 20px;">
                <ul>
                  <li><i class="icofont-rounded-right"></i> <strong>맴버SEQ :&nbsp;</strong><%=dto.getSeq() %></li>
                  <br>
                  <li><i class="icofont-rounded-right"></i> <strong>관리자번호 :&nbsp;</strong><%=dto.getMymanager() %></li>
              </ul>
              </div>
         <%
         }
         %>
            </div>
          </div>
        </div>
      </div>
    </section><!-- End About Section -->
<div align="center" style="padding-bottom: 300px">
<% 
if(mem.getMymanager() == 1 || mem.getMyid().equals(dto.getMyid())){
%>
<button type="button" class="btn btn-light"  data-toggle="modal" data-target="#updateMemModal" >수정</button>&nbsp;&nbsp;
<%
}
%>

<% 
if(mem.getMymanager() == 1){
%>
<button type="button" class="btn btn-light" data-toggle="modal" data-target="#deleteMemModal">삭제</button>&nbsp;&nbsp;
<button type="button" class="btn btn-light" OnClick="location.href='mem?param=allMemberList'">목록</button>&nbsp;&nbsp;
<%
}
%>
<% 
if(mem.getMyid().equals(dto.getMyid())){
%>
<button type="button" class="btn btn-light" data-toggle="modal" data-target="#deleteMemModal">회원탈퇴</button>&nbsp;&nbsp;
<%
}
%>
<button type="button"  class="btn btn-light" OnClick="location.href='index.jsp'">HOME</button>&nbsp;&nbsp;
</div>    
  
  
  
 
 <!-- /////// 회원 삭제 Modal /////////-->  
 
 <div class="utill4" style="display: inline-block;">
<div class="modal fade" id="deleteMemModal">
         <div class="modal-dialog">
               <div class="modal-content">
                  <div class="modal-header">
                      <h4 class="modal-title">삭제</h4>
                      <button type="button" class="close" data-dismiss="modal">×</button>
                    </div>
                    
                          <!-- Modal body 내용 -->
                    <div class="modal-body">
                         회원정보를 삭제 하시겠습니까?
                    </div>
                    
                          <!-- Modal footer 닫기버튼 붙여주기-->
                    <div class="modal-footer">
                      <button type="button" class="btn btn-light" data-dismiss="modal" onclick="deleteBtn(<%=dto.getSeq() %>)">삭제</button>
                      <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
               </div>
            </div>
</div>
</div>
 
 
  
  
  
  
  
<!-- /////// 회원정보 수정 Modal /////////-->  
<div class="utill4" style="display: inline-block;" >
<div class="modal fade" id="updateMemModal">
         <div class="modal-dialog">
               <div class="modal-content">
                  <div class="modal-header">
                      <h4 class="modal-title">수정</h4>
                      <button type="button" class="close" data-dismiss="modal">×</button>
                    </div>
                    
                      <!-- Modal body 내용 -->
                    <div class="modal-body">
                         회원정보를 수정하시겠습니까?
                    </div>
                    
                   <!-- Modal footer 닫기버튼 붙여주기-->
                    <div class="modal-footer">
                      <button type="button" class="btn btn-light" data-dismiss="modal" onclick="updateBtn(<%=dto.getSeq() %>)">수정</button>
                      <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                    </div>
               </div>
            </div>
</div>
</div>



<!-- ////////회원 탈퇴 Modal ////////// -->
 <div class="modal fade" id="deleteMemModal">
         <div class="modal-dialog">
               <div class="modal-content">
                  <div class="modal-header">
                      <h4 class="modal-title">탈퇴</h4>
                      <button type="button" class="close" data-dismiss="modal">×</button>
                    </div>
                    
                     <!-- Modal body 내용 -->
                    <div class="modal-body">
                         회원탈퇴를 진행합니다.
                    </div>
                    
                      <!-- Modal footer 닫기버튼 붙여주기-->
                    <div class="modal-footer">
                      <button type="button" class="btn btn-light" data-dismiss="modal" onclick="deleteBtn(<%=dto.getSeq() %>)">탈퇴</button>
                      <button type="button" class="btn btn-dark" data-dismiss="modal">취소</button>
                    </div>
               </div>
            </div>
</div>
  
 
  
  
  
  
  
  
  
  
  
  
      
  <a href="#" class="back-to-top"><i class="bx bx-up-arrow-alt"></i></a>
  <div id="preloader"></div>

  <!-- Vendor JS Files -->
  <script src="assets_2/vendor/jquery/jquery.min.js"></script>
  <script src="assets_2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets_2/vendor/jquery.easing/jquery.easing.min.js"></script>
  <script src="assets_2/vendor/php-email-form/validate.js"></script>
  <script src="assets_2/vendor/waypoints/jquery.waypoints.min.js"></script>
  <script src="assets_2/vendor/counterup/counterup.min.js"></script>
  <script src="assets_2/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets_2/vendor/venobox/venobox.min.js"></script>
  <script src="assets_2/vendor/owl.carousel/owl.carousel.min.js"></script>
  <script src="assets_2/vendor/typed.js/typed.min.js"></script>
  <script src="assets_2/vendor/aos/aos.js"></script>

  <!-- Template Main JS File -->
  <script src="assets_2/js/main.js"></script>

</body>

<script type="text/javascript">
function updateBtn( seq ) {
   
   location.href = "mem?param=updateMember&seq=" + seq;
}
function deleteBtn(seq){
   location.href = "mem?param=deleteMe&seq=" + seq;
}
</script>
</html>