<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="Dto.AdoptDogDto"%>
<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
request.setCharacterEncoding("utf-8");
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");
%>

<%
AdoptDogDto dto = (AdoptDogDto)request.getAttribute("adopt");

String sseq = (String)request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

Calendar cal = Calendar.getInstance();
SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd");
String today = format1.format(cal.getTime());

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/adaptUpdate.css">
<title>adoptUpdate</title>
</head>
<body>

<div id="s_top" align="center">
<br><br>
<h3>입양신청서 수정</h3>
</div>   
<div align="center">

<form action="./adopt?param=adoptUpdateAf" method="post">
   <input type="hidden" name="seq" id="updateSeq" value="<%=seq %>">
   <input type="hidden" name="manager" value="<%=mem.getMymanager() %>">
   
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
         <input type="text" class="form-control" name="phoneNum" value="<%=dto.getPhoneNum() %>">
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>제목</strong>
      </div>
      <div class="in_content">
         <input type="text" name="title" class="form-control" value="<%=dto.getTitle() %>">
         
      </div>
   </div>
   
   <div class="_lineC">
      <div class="in_line">
         <strong>내용</strong>
      </div>
      <div class="_content">
         <textarea rows="19" cols="47" name="mycontent" style="resize: none; border-color: #D3D2D2;"><%=dto.getMycontent() %></textarea>
      </div>
   </div>
   
   <div style="height: 60px;">
      
   </div>
   
   <div id="_bottom" align="right" style="padding: 10px;">
      <button type="submit" id="btnSubmit" class="btn btn-outline-warning">수정</button>&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" onclick="javascript:history.back(-1)" class="btn btn-outline-secondary">취소</button>
   </div>
   
   
   
</div>   
   
   
   
</form>

</div>
<br><br>


</body>
</html>