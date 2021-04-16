<%@page import="Dto.MemberDto"%>
<%@page import="Dto.abandonedDogDto"%>
<%@page import="Dao.abandonedDogDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/reportUpdate.css">
</head>


<%
request.setCharacterEncoding("utf-8");
Object ologin = request.getSession().getAttribute("login");
MemberDto mem = null;

if(ologin == null){
   response.sendRedirect("login.jsp");
}
mem = (MemberDto)ologin;   

String sseq = request.getParameter("seq");
int seq = Integer.parseInt(sseq.trim());

abandonedDogDao dao = abandonedDogDao.getInstance();
abandonedDogDto dto = dao.getReport(seq);
%>

<body>

<div id="s_top" align="center">
<br><br>
   <h4>유기동물 제보 수정</h4><br>
   <p style="font-size: 20px">소중한 아이들의 보호에 참여해 주세요.</p><br>
   

</div>   <!-- top -->
<div class ="_center" align="center">
<form action="filedd?param=updateAf" method="post" enctype="multipart/form-data">
<input type="hidden" name="seq" value="<%=seq %>">
 
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
         <strong>위험등급<br>
         (매우위급5 ~ 건강함1)</strong>
      </div>
      <div class="in_content">
         <select id="UrgencyLevel" name="danger">
            <option value="<%=dto.getDanger() %>" selected="selected"><%=dto.getDanger() %></option>
              <option value="1">1</option>
              <option value="2">2 </option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>발견장소</strong>
      </div>
      <div class="in_content">
         <input type="text" class="form-control" name="address" value="<%=dto.getAddress() %>">
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>연락처</strong>
      </div>
      <div class="in_content">
         <input type="text" class="form-control" name="phonenum"  value="<%=dto.getPhonenum() %>">
      </div>
   </div>
   
   <div class="_lineC">
      <div class="in_line">
         <strong>내용</strong>
      </div>
      <div class="_content">
         <textarea rows="19" cols="60" name="mycontent" style="resize: none; border-color: #D3D2D2;"><%=dto.getMycontent() %></textarea>
      </div>
   </div>
   
   <div class="_lineD">
      <div class="in_line">
         <strong>이미지파일</strong>
      </div>
      <div class="in_content">
         <img src="./upload/<%=dto.getNewfilename() %>" style="width: 100%;  max-height: 400px;">
          <input type="hidden" name="oldfile" size="50" value="<%=dto.getFilename() %>">
         <input type="hidden" name="oldnewfile" size="50" value="<%=dto.getNewfilename() %>">
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
        <button type="submit" id="btnSubmit" class="btn btn-outline-warning">완료</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" OnClick="javascript:history.back(-1)" class="btn btn-outline-secondary">취소</button>
   </div>   
   
</div>
</form>
</div>

<div class="_bottom" style="height: 400px;"></div>
</body>


<script type="text/javascript">
$(document).ready(function () { 
   $(function() {

       $("*").blur(function () { 
           $(this).css("background-color", "white");
       });

   });

});
</script>
</html>