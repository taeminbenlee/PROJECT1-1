<%@page import="Dao.MemberDao"%>
<%@page import="Dto.MemberDto"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모든 회원 목록</title>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
.* {
     margin: 0;
     padding: 0;
     box-sizing: border-box;
     font-family: 'Poppins', sans-serif;
 }

 body {
     min-height: 100vh;
     /* background: linear-gradient(to bottom, #000428, #004683) */
 }

 .container {
     margin-top: 100px;
     
 }

 .container .row .col-lg-4 {
     /* display: flex;
     justify-content: center; */
 }

.col-lg-4{
   /* width: 300px; */
   height: 380px;
   float: left;
    padding: 10px;
    text-align: center;
}
 
 .card {
     position: relative;
     padding: 0;
     margin: 0 !important;
     /* border-radius: 20px; */
     overflow: hidden;
     max-width: 280px;
     max-height: 340px;
     cursor: pointer;
     border: none;
     box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2)
 }

 .card .card-image .card-image h5 h6 {
     width: 100%;
     max-height: 340px;
     position: absolute;
     bottom: -180px;
     color: black;
     background: rgba(255, 255, 255, 0.2);
     backdrop-filter: blur(15px);
     min-height: 140px;
     width: 100%;
     transition: bottom .4s ease-in;
     box-shadow: 0 -10px 10px rgba(255, 255, 255, 0.1);
     border-top: 1px solid rgba(255, 255, 255, 0.2)
 }

 .card .card-image img {
     width: 100%;
     max-height: 340px;
     object-fit: cover;
     
 }


 .card .card-content {
     position: absolute;
     bottom: -180px;
     color: black;
     background: rgba(255, 255, 255, 0.2);
     backdrop-filter: blur(15px);
     min-height: 140px;
     width: 100%;
     transition: bottom .4s ease-in;
     box-shadow: 0 -10px 10px rgba(255, 255, 255, 0.1);
     border-top: 1px solid rgba(255, 255, 255, 0.2)
 }

 .card:hover .card-content {
     bottom: 0px
 }

 .card:hover .card-content h4,
 .card:hover .card-content h5 {
     transform: translateY(10px);
     opacity: 1
 }

 .card .card-content h4,
 .card .card-content h5 {
     font-size: 1.1rem;
     text-transform: uppercase;
     letter-spacing: 3px;
     text-align: center;
     transition: 0.8s;
     font-weight: 500;
     opacity: 0;
     transform: translateY(-40px);
     transition-delay: 0.2s
 }

 .card .card-content h5 {
     transition: 0.5s;
     font-weight: 200;
     font-size: 0.8rem;
     letter-spacing: 2px
 }

 .card .card-content .social-icons {
     list-style: none;
     padding: 0
 }

 .card .card-content .social-icons li {
     margin: 10px;
     transition: 0.5s;
     transition-delay: calc(0.15s * var(--i));
     transform: translateY(50px)
 }

 .card:hover .card-content .social-icons li {
     transform: translateY(20px)
 }

 .card .card-content .social-icons li a {
     color: black
 }

 .card .card-content .social-icons li a span {
     font-size: 1.3rem
 }

 @media(max-width: 991.5px) {
     .container {
         margin-top: 20px
     }

     .container .row .col-lg-4 {
         margin: 20px 0px
     }
 }


 
#search_btn{
   color: gray;
}

.search_input{
   resize: none;
   border-left: none;
   border-right: none;
   border-top: none;
   text-align: center;
}

.search_choice{
   width: 80px;
   height: 30px;
   text-align: center;
}

.mem_id{
   margin-top: 3px;
   font-size: 15px;
}


</style>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
MemberDto mem = null;
String nowLog = "";
if(session.getAttribute("login") == null){
   nowLog = "로그인 해주세요";
}else{
   Object ologin = session.getAttribute("login");
   mem = (MemberDto)ologin;
   nowLog = mem.getMyid()+" 님 환영합니다";
}
%>
<%
String choice = (String)request.getAttribute("choice");
String search = (String)request.getAttribute("search");

if(choice == null){
   choice = "";
}
if(search == null){
   search = "";
}
%>

<%
MemberDao dao = MemberDao.getInstance();
List<MemberDto> list = (List<MemberDto>)request.getAttribute("list");

%>

<%
int memPage = Integer.parseInt((String)request.getAttribute("memPage"));
System.out.println("memPage:" + memPage);

int pageNumber = Integer.parseInt((String)request.getAttribute("pageNumber"));
System.out.println("pageNumber:" + pageNumber);

int len = Integer.parseInt((String)request.getAttribute("len"));

System.out.println("len:" + len); 
%>
<script type="text/javascript">
$(document).ready(function() {
   randomImg();
   
   // 검색어 있는 경우
   let search = "<%=search %>";
   if(search == "") return;
   
   // select
   let obj = document.getElementById("choice");
   obj.value = "<%=choice %>";
   obj.setAttribute("selected", "selected");
   
});

</script>


<!-- <div class="container"> -->
<div class="additon_zone">         <!-- top전체 -->
   <div class="pageTop">         <!-- 사진 -->
      <div class="pic">
          <img src="./images/778899.png" style="width: 100%"> 
      </div>
   </div>
</div>
   
<div class="container">
<%
if(list == null || list.size() == 0){
   %>
      <h4 colspan="3">회원정보가 없습니다</h4>
<%

} else{
   for(int i = 0;i < list.size(); i++){
   MemberDto dto = list.get(i);
   %>

  <div class="col-lg-4">
    <input type="hidden" value="<%=i+1 + pageNumber*9 %>">
            <div class="card p-0">
                <div class="card-image">
                <img src="./images/rt06.png" alt="">
            <h5 class="mem_id"><%=dto.getMyid() %> 님</h5> 
            </div>
                <div class="card-content d-flex flex-column align-items-center">
                    <h4 class="pt-2">회원정보 보기</h4>
                    <a class="location" href="mem?param=openMemberDetail&seq=<%=dto.getSeq() %>">Click</a>
                    <ul class="social-icons d-flex justify-content-center">
                        <li style="--i:3"> <a href="javascript:void(0)" data-toggle="modal" onclick="seqfunc('<%=dto.getSeq() %>')" data-target="#myModal2"> <span class="far fa-trash-alt"></span> </a> </li>
                    </ul>
                </div>
            </div>
           <%--  <div>
           <input type='button' class='btn btn-light' data-toggle="modal" value = '삭제' onclick="seqfunc('<%=dto.getSeq() %>')" data-target="#myModal2">
        </div> --%>
            
        </div> 
       <%
   }
}
%>       

 
   
<div align="center">
<!-- 페이징    [1] 2 [3] -->  

<%

for(int i = 0;i < memPage; i++){
   if(pageNumber == i){   // 현재 페이지      [1] 2 [3] 
      %>
      <span style="font-size: 15pt; color: black; font-weight: bold;">
         <%=i + 1 %>
      </span>&nbsp;
      <%
   }
   else{
      %>      
      <a href="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)"
         style="font-size: 15pt; color: gray; font-weight: bold; text-decoration: none">
         [<%=i + 1 %>]
      </a>&nbsp;   
      <%
   }   
   
} 
%>
</div><br><br>  
   
<!-- 검색 -->
<div align=center style="margin-top: 30px;">   
<table class="utill2">
<tr>
   <th>
   <select id="choice" class="form-control" name="sellist1"> 
      <option value="id">아이디</option>
      <option value="name">이름</option>
   </select>
   </th>
   
   <th>
    &nbsp;&nbsp;&nbsp;&nbsp;
   <input type="text" id="search" class="search_input" value="<%=search%>" placeholder="검색어를 입력하세요.">
   </th>
   <th>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <button type="button" class="btn btn-link" onclick="searchBbs()">
   <i class="fa fa-search" style="font-size:24px; color:gray"></i>
   </button>
   </th>
</tr>            
</table>
</div>
<br>   
 
<div align='center'>
<input type='button' class='btn btn-secondary' value='목록'  onclick="reList()">&nbsp;
<input type='button' class='btn btn-secondary' value='HOME' onclick="goHome()">
<br><br>
</div>
      
 </div>       


<!-- 삭제 버튼 -->
<div class="modal fade" id="myModal2">
<div class="modal-dialog modal-md">
      <div class="modal-content">
       <div class="modal-header">     
            <br>
          <h4 class="modal-title">정말로 삭제하시겠습니까</h4>
          <br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" onclick='deleteMem()'>예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" >아니오</button>
        </div>
      </div>
</div>
</div>


<script type="text/javascript">

let seqnum = 0;
function seqfunc(seq) {
   seqnum = seq;
}

function deleteMem(){
   location.href="mem?param=deleteMember&seq="+seqnum;
};


function searchBbs() {
   let choice = document.getElementById("choice").value;
   let search = document.getElementById("search").value;
   location.href = "mem?param=allMemberList&choice=" + choice + "&search=" + search;
};


function goPage( pageNum ) {
   
   let choice = document.getElementById("choice").value;
   let search = document.getElementById("search").value;
   location.href = "mem?param=allMemberList&choice=" + choice + "&search=" + search + "&pageNumber=" + pageNum;   
};


//목록으로 돌아가기
function reList(){
   location.href = "mem?param=allMemberList";
};

//홈으로 돌아가기
function goHome(){
   location.href = "rescued?param=home";
};
    
/* 
function randomImg() {
   console.log('zz');
   for(var i=0; i<8; i++) {
   let randomNumber = Math.floor(Math.random() * 9) + 1;
      $("#"+i+"_img").attr('src', './images/rt0' + randomNumber + '.png');      
   };
}; */
</script>
         
</body>
</html>