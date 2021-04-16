<%@page import="Dao.abandonedDogDao"%>
<%@page import="Dto.abandonedDogDto"%>
<%@page import="Dto.MemberDto"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Abandoned Dog Report List</title>


<style type="text/css">

#search_btn{
   color: gray;
}

.search_choice{
   width: 80px;
   height: 30px;
   text-align: center;
}

.search_input{
   resize: none;
   border-left: none;
   border-right: none;
   border-top: none;
   text-align: center;
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
abandonedDogDao dao = abandonedDogDao.getInstance();
List<abandonedDogDto> list = (List<abandonedDogDto>)request.getAttribute("list");

int adPage = Integer.parseInt((String)request.getAttribute("adPage"));
System.out.println("adPage:" +adPage);

int pageNumber = Integer.parseInt((String)request.getAttribute("pageNumber"));
System.out.println("pageNumber:" + pageNumber);

int len = Integer.parseInt((String)request.getAttribute("len"));

System.out.println("len:" + len);
%>
<script type="text/javascript">
$(document).ready(function() {
   // 검색어 있는 경우
   let search = "<%=search %>";
   if(search == "") return;
   
   // select
   let obj = document.getElementById("choice");
   obj.value = "<%=choice %>";
   obj.setAttribute("selected", "selected");
});
</script>


<div class="additon_zone">         <!-- top전체 -->
   <div class="pageTop">         <!-- 사진 -->
      <div class="pic">
          <img alt="" src="./images/rtrt.png" style="width: 100%"> 
      </div>
   </div>
</div>
   
<div class="center_" align="center">   <!-- 글씨 -->
<br><br>
      <%
      if(mem.getMymanager() != 1){
      %>
      <h2 class="welc" align="center" style="font-size: 22px;"><%=mem.getMyid() %> 님의</h2>
      <%} %>
      
      <h1 align="center" style="font-size: 26px;">유기동물 제보 현황</h1><br>
<br>

<div align="center">
   <table class="table table-hover" style="width: 1000px">
    <thead>
      <tr align="center">
        <th>번호</th>
        <th>장소</th>
        <th>작성자</th>
        <th>위험등급</th>
        <th>날짜</th>
        <th>삭제</th>
      </tr>
    </thead>
   <tbody>
      <%
if(list == null || list.size() == 0){
   %>
   <tr>
      <td colspan="3">유기견 제보가 없습니다</td>
   </tr>
<%

} else{
for(int i = 0;i < list.size(); i++){
   abandonedDogDto dto = list.get(i);
   %>
         <tr align="center" height="5">
         <th><%=dto.getRnum() %></th>
         <td>
            <%if(dto.getDel() == 0){
               %>   
         
            <a class="location" href="abandog?param=openReportDetail&seq=<%=dto.getSeq() %>">
               <%=dto.getAddress() %>
            </a>
            <%
            }else{
            %>
            <font color="#ff0000">이글은 삭제되었습니다.</font>
            <%
            }
            %>
         </td>
         <td><%=dto.getMyid() %></td>
         <td><%=dto.getDanger() %></td>
         <td><%=dto.getWdate().substring(0,10) %></td>
         <td><input type='button' class='btn btn-light' data-toggle="modal" value = '삭제' onclick="seqfunc('<%=dto.getSeq() %>')" data-target="#myModal2"></td>
      </tr>
      <%
   }
}
%>

   </tbody>
</table>   

   <br><br>
   
   
<% 
if(mem.getMyid().equals("admin")){
%>


<div align="center">
<!-- 페이징    [1] 2 [3] -->
<%

for(int i = 0;i < adPage; i++){
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
</div><br>
   
   <br>
   
   <table class="utill2">
      
      <tr>
          
            <th>
            <select id="choice" class="form-control" name="sellist1"> 
               <option value="id">아이디</option>
               <option value="address">장소</option>
            </select>
            </th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" id="search" class="search_input" value="<%=search%>" placeholder="검색어를 입력하세요.">
            </th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-link" id="search_btn" onclick="searchBbs()"><i class="fa fa-search" style="font-size:24px"></i></button>
            </th>
      
      </tr>
            
   </table>
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
          <button type="button" class="btn btn-secondary" onclick='deleteAbandoned()'>예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" >아니오</button>
        </div>
      </div>
</div>
</div>
<%
}
%>
   
   <% 
   if(!mem.getMyid().equals("admin")){
   %>

</div>

<div align="center">
<!-- 페이징    [1] 2 [3] -->
<%

for(int i = 0;i < adPage; i++){
   if(pageNumber == i){   // 현재 페이지      [1] 2 [3] 
      %>
      <span style="font-size: 15pt; color: black; font-weight: bold;">
         <%=i + 1 %>
      </span>&nbsp;
      <%
   }
   else{
      %>      
      <a href="#none" title="<%=i+1 %>페이지" onclick="userGoPage(<%=i %>)"
         style="font-size: 15pt; color: gray; text-decoration: none">
         [<%=i + 1 %>]
      </a>&nbsp;   
      <%
   }   
   
} 
%>
</div><br>
   
   <br>
   
   <table class="utill2">
      
      <tr>
            <th>
            <select id="choice" class="form-control" name="sellist1"> 
               <option value="id">아이디</option>
               <option value="address">장소</option>
            </select>
            </th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="text" id="search" class="search_input" value="<%=search%>" placeholder="검색어를 입력하세요.">
            </th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-link" id="search_btn" onclick="userSearchBbs()"><i class="fa fa-search" style="font-size:24px"></i></button>
            </th>
            
            
            
      
      </tr>
            
   </table>
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
          <button type="button" class="btn btn-secondary" onclick='deleteMyAbandoned()'>예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" >아니오</button>
        </div>
      </div>
</div>
</div>
   <%
}
%>
   <br>


<!-- 그 외 버튼 기능  -->
<div align='center'>
<input type='button' class='btn btn-light' value='목록'  onclick="reList()">
&nbsp;
<input type='button' class='btn btn-light' value='HOME' onclick="goHome()">
</div>
</div>
<br><br>

<script type="text/javascript">
let seq = 0;

//삭제버튼 기능
function seqfunc(num) {
   seq = num;
}

function deleteAbandoned() {
   location.href = "abandog?param=ManagerDeleteReport&seq=" + seq
}

function deleteMyAbandoned() {
   location.href = "abandog?param=deleteReport&seq=" + seq
}

function searchBbs() {
   let choice = document.getElementById("choice").value;
   let search = document.getElementById("search").value;
   
   
   location.href = "abandog?param=getAllAbandonedDogPagingList&choice=" + choice + "&search=" + search;
}
function userSearchBbs() {
   let choice = document.getElementById("choice").value;
   let search = document.getElementById("search").value;
   
   
   location.href = "abandog?param=getMyAbandonedDogPagingList&choice=" + choice + "&search=" + search;
}

function goPage( pageNum ) {
   
   
   let choice = document.getElementById("choice").value;
   let search = document.getElementById("search").value;
   
   location.href = "abandog?param=getAllAbandonedDogPagingList&choice=" + choice + "&search=" + search + "&pageNumber=" + pageNum;   
}

function userGoPage( pageNum ) {
   
   
   let choice = document.getElementById("choice").value;
   let search = document.getElementById("search").value;
   
   location.href = "abandog?param=getMyAbandonedDogPagingList&choice=" + choice + "&search=" + search + "&pageNumber=" + pageNum;   
}

function writeReport() {
   
   location.href = "abandog?param=writeReport";
   
}

//목록으로 돌아가기
function reList(){
   location.href = "abandog?param=getAllAbandonedDogPagingList";
};


//홈으로 돌아가기
function goHome(){
   location.href = "rescued?param=home";
};


</script>
         
</body>
</html>