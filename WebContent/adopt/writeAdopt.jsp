<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="Dto.AdoptDogDto"%>
<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%
request.setCharacterEncoding("utf-8");
Object ologin = session.getAttribute("login");
MemberDto mem = null;
if(ologin == null){
   %>   
   <script>
   alert('로그인 해 주십시오');
   location.href = "login.jsp";
   </script>   
   <%
}
mem = (MemberDto)ologin;

String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

Calendar cal = Calendar.getInstance();
SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
String today = format1.format(cal.getTime());
%>


<meta charset="UTF-8">

<title>writeAdopt</title>
<link rel="stylesheet" href="./css/writeAdopt.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<br><br>
<div align="center">
   <br><br>
   <h2>유기동물 입양 신청서</h2><br>
</div>   <!-- top -->


<div class="_center" align="center">

<form action="adopt?param=writeAdoptAf" method="post">
 <input type='hidden' value=${seq } name='seq'>
   <br><br>   

   <div id="_contents" >
   <div class="_line">
      <div class="in_line">
         <strong>아이디</strong>
      </div>
      <div class="in_content">
         <input type="text" name="myid" class="form-control" value="<%=mem.getMyid() %>" readonly="readonly">
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>입양희망일</strong>
      </div>
      <div class="in_content">
         <input type="date" name="rdate" value=<%=today%> >
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>연락처</strong>
      </div>
      <div class="in_content">
         <input type="text" class="form-control" name="phoneNum">
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>제목</strong>
      </div>
      <div class="in_content">
         <input type="text" name="title" class="form-control">
         
      </div>
   </div>
   
   <div class="_lineC">
      <div class="in_line">
         <strong>내용</strong>
      </div>
      <div class="_content">
         <textarea rows="19" cols="47" name="mycontent" style="resize: none; border-color: #D3D2D2;">입양동기 및 과거 키웠던 경험 등 자유롭게 적어주세요.</textarea>
      </div>
   </div>
   
   <div style="height: 60px;">
      
   </div>
   
   <div id="_bottom" align="right" style="padding: 10px;">
      <button type="submit" id="btnSubmit" class="btn btn-outline-warning">신청</button>&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" onclick="goDetail()" class="btn btn-outline-secondary">취소</button>
   </div>
   
   
   
</div>   

</form>

</div>

<br><br><br><br>

<script type="text/javascript">
function goDetail() {
    location.href = "rescued?param=list" 
}

$(document).ready(function(){
	
	//빈칸체크
	$("#btnSubmit").click(function() {
		$("*").prop('required',true);
	});
	
	
	
})

</script>


</body>
</html>