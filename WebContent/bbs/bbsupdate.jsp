<%@page import="Dto.BbsDto"%>
<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
MemberDto mem = (MemberDto)session.getAttribute("login");
Object obj = request.getAttribute("bbs");
BbsDto bbs = (BbsDto)obj;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bbsUpdate.css">
</head>

<body>

<div align="center">
<br><br>
   <h3>우리 멍냥이 자랑글 수정하기</h3>
<br>   
</div>


<div class ="_center" align="center">
<form action="fileCon?param=update"  method="post" accept-charset="utf-8" enctype="multipart/form-data">
<input type="hidden" name="seq" value="<%=bbs.getSeq() %>">
<input type="hidden" name="oldfile" value="<%=bbs.getFilename() %>">


	<div id="_contents" >
	   <div class="_line">
	      <div class="in_line">
	         <strong>아이디</strong>
	      </div>
 		<div class="in_content">
         <input type="text" name="id" class="form-control" value="<%=bbs.getMyid() %>" readonly="readonly">
      	</div>
   	</div>
	
	<div class="_line">
      <div class="in_line">
         <strong>제목</strong>
      </div>
      <div class="in_content">
         <input type="text" name="title" class="form-control" value="<%=bbs.getTitle() %>">
         
      </div>
   </div>

	<div class="_lineD">
      <div class="in_line">
         <strong>이미지파일</strong>
      </div>
      <div class="in_content">
         <img src="./upload/<%=bbs.getFilename() %>" style="width: 100%; max-height: 400px;">
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
      <div class="_content">
         <textarea rows="20" cols="80" name="content"><%=bbs.getMycontent() %></textarea>
      </div>
   </div>


      <div align="right">
         <button type="submit" id="btnSubmit" class="btn btn-outline-warning">완료</button>&nbsp;&nbsp;&nbsp;&nbsp;
         <button type="button" OnClick="javascript:history.back(-1)" class="btn btn-outline-secondary">취소</button>
      </div>   
   
</div>
</form>
</div>

<div class="_bottom" style="height: 400px;"></div>


</body>
</html>