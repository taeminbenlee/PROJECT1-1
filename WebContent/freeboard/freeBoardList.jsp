<%@page import="Dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@page import="Dao.BbsDao"%>
<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 리스트</title>

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
</head>


<body>
<%
BbsDao dao = BbsDao.getInstance();
List<BbsDto> list = (List<BbsDto>)request.getAttribute("list");

int adPage = Integer.parseInt((String)request.getAttribute("adPage"));
System.out.println("adPage:" +adPage);

int pageNumber = Integer.parseInt((String)request.getAttribute("pageNumber"));
System.out.println("pageNumber:" + pageNumber);

int len = Integer.parseInt((String)request.getAttribute("len"));
System.out.println("len:" + len);


request.setCharacterEncoding("utf-8");
Object ologin = request.getSession().getAttribute("login");
MemberDto mem = null;
String id = "";
if(ologin == null){
   response.sendRedirect("login.jsp");
} else {
mem = (MemberDto)ologin;   
id = mem.getMyid();   
}

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
          <img src="./images/fdfd.png" style="width: 100%"> 
      </div>
   </div>
</div>

<div class="title">   <!-- 글씨 -->
      <br><br>
      <h1 align="center" style="font-size: 26px;">자유게시판</h1><br>
<br>


<div id="text1" align="center">

   <table class="table table-hover" style="width: 1000px;">
   <thead>
      <tr align="center">
        <th>번호</th>
        <th>아이디</th>
        <th>제목</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
   <tbody>
      <%
if(list == null || list.size() == 0){
   %>
   <tr>
      <td colspan="3">게시글이 없습니다</td>
   </tr>
<%

} else{
for(int i = 0;i < list.size(); i++){
   BbsDto dto = list.get(i);
   %>
         <tr align="center" height="5">
         <th><%=i+1+pageNumber*10 %></th>
         <td>
            <%if(dto.getDel() == 0){
               %>   

               <%=dto.getMyid() %>
            
            <%
            }else{
            %>
            <font color="#ff0000">이글은 삭제되었습니다.</font>
            <%
            }
            %>
         </td>
         <td><a class="location" href="freeb?param=openDetail&seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a></td>
         <td><%=dto.getWdate().substring(0,10) %></td>
         <td><%=dto.getVcount() %></td>
      </tr>
      <%
   }
}
%>

   </tbody>
</table>
</div>
<br><br>



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
</div><br><br>
   
<div align='center'>   
<table class="utill2">
<tr>
   <th>
   <select id="choice" class="form-control" name="sellist1"> 
      <option value="id">아이디</option>
      <option value="title">제목</option>
   </select>
   </th>
   
   <th>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <input type="text" id="search" class="search_input" value="<%=search%>"  placeholder="검색어를 입력하세요.">
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
            
<div align="center">
<%if(!(id.equals(""))){ %>
<button type="button" class="btn btn-light" onclick="writeBtn()">글쓰기</button>&nbsp;
<%} %>
<input type='button' class='btn btn-light' value='목록'  onclick="reList()">&nbsp;
<input type='button' class='btn btn-light' value='HOME' onclick="goHome()">
<br><br><br><br>      
</div>
</div>
</body>


<script type="text/javascript">
function writeBtn(){
   location.href = "freeb?param=writeFree";
}

function goPage( pageNum ) {

   let choice = document.getElementById("choice").value;
    let search = document.getElementById("search").value;
   location.href = "freeb?param=toFreeboardlist&choice=" + choice + "&search=" + search + "&pageNumber=" + pageNum;   
}

function reList(){
   location.href = "freeb?param=toFreeboardlist";
};

function goHome(){
   location.href = "rescued?param=home";
};


</script>
</html>