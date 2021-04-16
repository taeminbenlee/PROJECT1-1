<%@page import="Dto.MemberDto"%>
<%@page import="Dto.BbsDto"%>
<%@page import="java.util.List"%>
<%@page import="Dao.BbsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
MemberDto mem = (MemberDto)session.getAttribute("login");
String choice = (String)request.getAttribute("choice");
String search = (String)request.getAttribute("search");
int pageNumber =Integer.parseInt((String)request.getAttribute("pageNumber"));
int len = Integer.parseInt((String)request.getAttribute("len"));
int bbspage = Integer.parseInt((String)request.getAttribute("bbspage"));
List<BbsDto> list = (List<BbsDto>)request.getAttribute("list");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>




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
</head>
<body>
<br><br><br><br>
 <div class="container gallery-container">

    <h1> 우리 멍냥이는요</h1>
 	<p class="page-description text-center">사랑스러운 반려동물을 자랑하세요</p>
 
   <div class="tz-gallery">

        <div class="row">

<%
if(list.size() == 0){
	%>
	   <p style=" margin: 30px auto">작성된 글이 없습니다</p>
	<%
}
	
	for(int i = 0; i< list.size(); i++){
		BbsDto bbs = list.get(i);	
		%>
		  <div class="col-sm-6 col-md-4">
				 <div class="thumbnail">
                    <a class="lightbox" href="bbs?param=bbsDetail&seq=<%=bbs.getSeq() %>">   <!-- 디테일  -->
                        <img  src="./upload/<%=bbs.getFilename()%>" style='width:323px;height: 400px'>  	<!-- 리스트 -->
                    </a>
                    <div class="caption">
                        <h3><%=bbs.getTitle() %></h3>
                        <h3><%=bbs.getMyid() %></h3>
                        <p><%=bbs.getWdate().substring(0,11) %></p>
                    </div>
               </div>
      </div>

		
	<% 
	}
 %>


</div>
</div>
</div>

<!-- 페이징 -->
<div align="center">
<% 
	for(int i =0; i< bbspage; i++ ){
		if(pageNumber == i){
			%>
			<span style="font-size: 15pt; color: black; font-weight: bold;">
			<%=i + 1 %>
			</span>&nbsp;
			<%
		}
		else{
		%>
		<a href="#none" title="<%=i+1 %>페이지" onclick="goPage(<%=i %>)"
			style="font-size: 15pt; color:gray; font-weight: bold; text-decoration: none">
			[<%=i + 1 %>]
			</a>&nbsp;
			<%
	}
}
%>
</div>

<!-- 검색 -->
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
	<button type="button" class="btn btn-link" onclick="searchBbs()">
	<i class="fa fa-search" style="font-size:24px; color:gray"></i>
	</button>
	</th>	
	</tr>				
</table>
</div>
<br>
	
	
<div align='center'>
<%if(mem != null ) {%>
<input id="_bbsSbnBtn" type="button" class="btn btn-light" value="글쓰기" >&nbsp;
&nbsp;
<%} %>
<!-- 그 외 버튼 기능  -->
<input type='button' class='btn btn-light' value='목록'  onclick="reList()">&nbsp;
&nbsp;
<input type='button' class='btn btn-light' value='HOME' onclick="goHome()">
</div>
<br><br>


<script type="text/javascript">
$("#_bbsSbnBtn").click(function() {
	location.href="bbs?param=bbsWrite";
});

function searchBbs(pageNum) {
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;
	location.href = "bbs?param=getPagingBbsList&choice=" + choice + "&search=" + search;
}

function goPage( pageNum ) {
	let choice = document.getElementById("choice").value;
	let search = document.getElementById("search").value;
	location.href = "bbs?param=getPagingBbsList&choice=" + choice + "&search=" + search + "&pageNumber=" + pageNum;	
}
//목록으로 돌아가기
function reList(){
	location.href = "bbs?param=toBbslist";
};

//홈으로 돌아가기
function goHome(){
	location.href = "rescued?param=home";
};


</script>
</body>
</html>