<%@page import="Dao.MemberDao"%>
<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
request.setCharacterEncoding("utf-8");
Object ologin = request.getSession().getAttribute("login");
MemberDto mem = null;

if(ologin == null){
	
	response.sendRedirect("login.jsp");
}
mem = (MemberDto)ologin;	
%>
<%
String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());
MemberDao dao = MemberDao.getInstance();
MemberDto dto = dao.getMemberDetail(seq);
%>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link rel="stylesheet" href="css/reportUpdate.css">

</head>
<body>

<div align="center">
<br><br>
   <h3>회원정보 수정</h3>
<br>
</div>

<div class ="_center" align="center">
<form action="mem?param=updateAf" id="frm1" method="post" onSubmit="formChk();return false">
<input type="hidden" name="seq" value=<%=seq %>>
<input type="hidden" name="currentpwd"value="<%=dto.getPwd() %>">		

<div id="_contents" >
   <div class="_line">
      <div class="in_line">
         <strong>아이디</strong>
      </div>
      <div class="in_content">
		<input type="text" name="id" readonly="readonly" size="60" value="<%=dto.getMyid() %>"> 		
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>변경할 패스워드</strong>
      </div>
      <div class="in_content">
		<input type="password" id="pwd" name="pwd" size="60" value="">		
      </div>
   </div> 
   
   <div class="_line">
      <div class="in_line">
         <strong>패스워드 확인</strong>
      </div>
      <div class="in_content">
		<input type="password" id="pwd2" name="pwd2" size="60" value="">		
      </div>
   </div>   
 
   <div class="_line">
      <div class="in_line">
         <strong>이름</strong>
      </div>
      <div class="in_content">
		<input type="text" id="name" name="name" size="60" value="<%=dto.getMyname() %>"> 		
      </div>
   </div>      

   <div class="_line">
      <div class="in_line">
         <strong>연락처</strong>
      </div>
      <div class="in_content">
		<input type="text" id="phonenum" name="phonenum" size="60" value="<%=dto.getPhonenum() %>">		
      </div>
   </div>    
   
   <div class="_line">
      <div class="in_line">
         <strong>이메일</strong>
      </div>
      <div class="in_content">
		<input type="text" id="_email" name="email" size="60" value="<%=dto.getEmail() %>">		
      </div>
   </div>      

   <div id="right" align="right" style="padding: 70px;">
      <button type="submit" id="btnSubmit" class="btn btn-outline-warning">수정완료</button>&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" OnClick="javascript:history.back(-1)" class="btn btn-outline-secondary">취소</button>
   </div>

</form>
</div>



<script type="text/javascript">
/* 유효성검사 */
function formChk() {
	
	var check = document.frm;
	
	if($("#pwd").val()== ""){
		Swal.fire({
			  icon: 'error', 
			  text: '패스워드 입력창이 비어있습니다',
			})
		$("#pwd").focus();
		return false;
	}else if($("#pwd2").val()== ""){
		Swal.fire({
			  icon: 'error', 
			  text: '패스워드 확인창이 비어있습니다',
			})		
		$("#pwd2").focus();
		return false;
	}else if($("#name").val()== ""){
		Swal.fire({
			  icon: 'error',
			  text: '이름을 입력해주세요',
			})		
			$("#name").focus();
		return false;
	}else if($("#_email").val()== ""){
		Swal.fire({
			  icon: 'error',
			  text: '이메일을 입력해주세요',
			})		
			$("#_email").focus();
		return false;
	}else if($("#phonenum").val() == ""){
		Swal.fire({
			  icon: 'error',
			  text: '연락처를 입력해주세요',
			})		
		
			$("#phonenum").focus();
		return false;
	}

/* 패스워드 재확인 기능구현 */
	if( $("#pwd").val() != $("#pwd2").val() ){
		Swal.fire({
			  icon: 'error', //SUCCESS
			  title: '패스워드가 서로 다릅니다',
			})
		
		$("#pwd").val(""); 
		$("#pwd2").val("");
		$("#pwd").focus();
		return false;
	}
/* 유효성검사 모두 확인후 서브및 실행 */
	check.submit();
}


</script>
</body>
</html>