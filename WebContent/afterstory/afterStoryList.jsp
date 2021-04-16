<%@page import="java.lang.annotation.AnnotationFormatError"%>
<%@page import="java.util.List"%>
<%@page import="Dao.afterStoryDao"%>
<%@page import="Dto.afterStoryDto"%>
<%@page import="Dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
MemberDto mem = (MemberDto) session.getAttribute("login");
%>


<!DOCTYPE html>
<html>
<head>

<style>
.search_input{
	resize: none;
	border-left: none;
	border-right: none;
	border-top: none;
	text-align: center;
}

body{
	background-color:white;
}


</style>



<meta charset="utf-8">
<title>afterStoryList</title>
</head>
<body>

<%
String choice = (String)request.getAttribute("choice");
String search = (String)request.getAttribute("search");
if(choice == null){
	choice = "";
}
if(search == null){
	search = "";
}

afterStoryDao dao = afterStoryDao.getInstance();

List<afterStoryDto> list = (List<afterStoryDto>)request.getAttribute("list");

int afPage = Integer.parseInt((String)request.getAttribute("afPage"));
System.out.println("afPage:" +afPage);

int pageNum = Integer.parseInt((String)request.getAttribute("pageNum"));
System.out.println("pageNum:" + pageNum);

int len = Integer.parseInt((String)request.getAttribute("len"));
System.out.println("len:"+ len);

 %>
 
 
<script type="text/javascript" >
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

 
<br><br><br><br>
 <div class="container gallery-container">

    <h1> AFTER STORY</h1>
 	<p class="page-description text-center">입양한 아이와 함께한 추억을 자랑하세요</p>
 
   <div class="tz-gallery">

        <div class="row">
 	   	
<% 	
	if(list ==null || list.size() ==0){
%>
		              
       <p style=" margin: 30px auto">작성된 글이 없습니다</p>
																												   																																		   
<% 		
} else{
	for(int i = 0; i<list.size(); i++){
	afterStoryDto dto = list.get(i);
%>		
	 <%
	 	if(dto.getDel() == 0){
	 %>
		  <div class="col-sm-6 col-md-4">
					 <div class="thumbnail">
	                    <a class="lightbox" href="after?param=afterStoryDetail&seq=<%=dto.getSeq() %>">   <!-- 디테일  -->
	                        <img  src="./upload/<%=dto.getFilename()%>" style='width:323px;height: 400px'>  	<!-- 리스트 -->
	                    </a>
	                    <div class="caption">
	                        <h3><%=dto.getTitle() %></h3>
	                        <p><%=dto.getWdate().substring(0,11) %></p>
	                    </div>
	               </div>
	      </div>
 <%
} else{
%> 			
<%			 
	}
		}
}
%>    
	<!-- </ul> -->	
			</div>
	</div>
</div>


<!-- 페이지  -->
<div align="center">
<%
for(int i=0; i<afPage; i++){
	if(pageNum == i){	// 현재 페이지
		%>
		<span style="font-size: 15pt; color: black; font-weight: bold;">
		<%=i+1 %>
		</span>&nbsp;
<%} else{ %>
		 <a href="#none" title="<%=i+1 %>페이지" onclick="afterPage(<%=i %>)"
			style="font-size: 15pt; color: gray; font-weight: bold; text-decoration: none">
		 	[<%=i + 1 %>]
		 </a>&nbsp;
		 
		 <%
	}
}
%> 
</div>




<!-- 검색  -->
<br><br>
<div align="center">
<table class="utill2">
<tr>
	<th>
	<select id="choice" class="form-control" name="sellist1"> 
		<option value="title">제목</option>
		<option value="content">내용</option>
		<option value="writer">작성자</option>
	</select>
	</th>
	
	<th>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="text" id="search" class="search_input" value="<%=search%>" placeholder="검색어를 입력하세요.">
	</th>
	<th>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" class="btn btn-link" onclick="afterSearch()">
	<i class="fa fa-search" style="font-size:24px; color:gray"></i>
	</button>
	</th>
</tr>				
</table>
</div>
<br>

<!-- 글쓰기  -->
<div align="center" >
<%if(mem != null ) {%>
<input type='button' class='btn btn-light' onclick="Write()" value="글쓰기">&nbsp;
&nbsp;
<%} %>
<!-- 그 외 버튼 기능  -->
<input type='button' class='btn btn-light' value='목록'  onclick="reList()">&nbsp;
&nbsp;
<input type='button' class='btn btn-light' value='HOME' onclick="goHome()">
</div>
<br><br>




<script type="text/javascript">
function afterSearch() {
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;
	location.href = "after?param=afterstoryList&choice=" + choice + "&search=" + search;

}

function afterPage( pageNum ) {
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;
	
	location.href = "after?param=afterstoryList&choice=" + choice + "&search=" + search + "&pageNum=" + pageNum;	
}


//목록으로 돌아가기
function reList(){
	location.href = "after?param=afterstoryList";
};

//홈으로 돌아가기
function goHome(){
	location.href = "rescued?param=home";
};

//글쓰기
function Write(){
	location.href = "after?param=write";
}; 



</script>



</body>
</html>