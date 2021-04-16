<%@page import="Dto.BbsDto"%>
<%@page import="Dto.MemberDto"%>
<%@page import="Dao.freeBoardDao"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%
 ServletConfig mConfig = null;
 final int BUFFER_SIZE = 8192;
 mConfig = config;	
 String fupload = mConfig.getServletContext().getRealPath("/upload");
 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 수정</title>
<link rel="stylesheet" href="./css/freeboardUpdate.css"> 

</head>
<%
request.setCharacterEncoding("utf-8");
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");

String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

freeBoardDao dao = freeBoardDao.getInstance();
BbsDto dto = dao.getFree(seq);
%>

<body>

<div align="center">
<br><br>
<h3>자유게시판 수정</h3>
<br>
</div>


<div class ="_center" align="center">

<form action="freeFile" method="post" enctype="multipart/form-data">
<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
<input type="hidden" name="param" value="updateFreeAf">

	<div id="_contents" >

   <div class="_line">
      <div class="in_line">
         <strong>아이디</strong>
      </div>
      <div class="in_content">
         <input type="text" name="id" class="form-control" value="<%=mem.getMyid() %>" readonly="readonly">
      </div>
   </div>

	<div class="_line">
      <div class="in_line">
         <strong>제목</strong>
      </div>
      <div class="in_content">
         <input type="text" class="form-control" name="title" id='title' value="<%=dto.getTitle() %>">
      </div>
   </div>

	 <div class="_lineD">
      <div class="in_line">
         <strong>이미지파일</strong>
      </div>
      <div class="in_content">
         <img src="./upload/<%=dto.getFilename()%>" style="width: 100%; max-height: 400px;">
          <input type="hidden" name="oldfile" size="50" value="<%=dto.getFilename() %>">
         
      </div>
   </div>

	<div class="_line">
      <div class="in_line">
         <strong>변경할 파일 업로드</strong>
      </div>
          <input type="file" name="fileload" size="50" accept="image/bmp,image/rle,image/dib,image/jpg,image/jpeg,
                                        image/gif,image/png,image/tif,image/tiff">
   </div>
   
	<div class="_lineC">
      <div class="in_line">
         <strong>내용</strong>
      </div>
      <div class="content">
         <textarea rows="10" cols="47" name="content" id='content' style="resize: none; border-color: #D3D2D2;"><%=dto.getMycontent() %></textarea>
      </div>
   </div>

      <div align="right" style="margin-bottom: 200px;">
         <button type="submit" id="btnSubmit" class="btn btn-outline-warning">완료</button>&nbsp;&nbsp;&nbsp;&nbsp;
         <button type="button" OnClick="javascript:history.back(-1)" class="btn btn-outline-secondary">취소</button>
      </div>   

  
</div>   
   
   
   
</form>

</div>
<br><br>

<script type="text/javascript">
$(document).ready(function () { 
	$(function() {

	    $("#btnSubmit").click(function() {
	    	//빈칸체크
	    	$("#title").prop('required',true);
	    	$("#coctent").prop('required',true);
	    	
	    });
	});

});
</script>


</body>



</html>