<%@page import="Dto.afterStoryDto"%>
<%@page import="Dao.afterStoryDao"%>
<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("utf-8");
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");

String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());
%>

<%
afterStoryDao dao = afterStoryDao.getInstance();
afterStoryDto dto = dao.storyDetail(seq);
%>



<html>
<head>
<meta charset="UTF-8">
<title>afterStoryUpdate</title>
<link rel="stylesheet" href="css/reportUpdate.css">

</head>
<body>

  
<div align="center">
<br><br>
   <h3>나의 입양후기 수정</h3>
<br>
</div>

<div class ="_center" align="center">
<form action="./afterfile?param=afterUpdate" method = "post" enctype="multipart/form-data">
<input type="hidden" name="seq" value="<%=dto.getSeq() %>">

<div id="_contents" >
   <div class="_line">
      <div class="in_line">
         <strong>아이디</strong>
      </div>
      <div class="in_content">
			<input type="text" name="id" size="90px" value="<%=mem.getMyid() %>" readonly="readonly">
      </div>
   </div>

   <div class="_line">
      <div class="in_line">
         <strong>제목</strong>
      </div>
      <div class="in_content">
         <input type="text" class="form-control" name="title" value="<%=dto.getTitle() %>">
      </div>
   </div>
   
   <div class="_lineC">
      <div class="in_line">
         <strong>내용</strong>
      </div>
      <div class="_content">
		<textarea  name="content" rows="19" cols="60" ><%=dto.getMycontent() %></textarea>
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
   
   <div align="right" style=padding-bottom:70px>
        <button type="submit" id="btnSubmit" class="btn btn-outline-warning">수정완료</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" OnClick="javascript:history.back(-1)" class="btn btn-outline-secondary">수정취소</button>
   </div>   

</div>
</form>
</div>

<div class="_bottom" style="height: 400px;"></div>

</body>


</html>