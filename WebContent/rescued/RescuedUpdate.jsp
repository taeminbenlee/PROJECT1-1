
<%@page import="Dto.MemberDto"%>
<%@page import="Dto.RescuedDogDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<%
RescuedDogDto dto = (RescuedDogDto)request.getAttribute("dto");
System.out.println("RescuedUpdate.jsp:" + dto.toString());
%>
   
    
<!DOCTYPE html>
<html>
<head>
 
<meta charset="UTF-8">
<title>견생역전</title>
<link rel="stylesheet" href="./css/rescuedUpdate.css"> 
</head>
<body>



<div id="s_top"align="center">
<br><br>
<h4>입양 가능 리스트 수정</h4>
</div>
<br><br>

<div class ="_center" align="center">
<form action='./rcdFile' method="post" enctype="multipart/form-data">
<input type='hidden' name='seq' value=${dto.seq }>
<input type='hidden' name='oldfile' value=${dto.filename }>


<div id="_contents" >
   <div class="_line">
      <div class="in_line">
         <strong>제목</strong>
      </div>
      <div class="in_content">
         <input type="text" name="title" class="form-control" size=50 value='${dto.title }'>
      </div>
   </div>

   <div class="_lineD">
      <div class="in_line">
      <strong>업로드한 파일</strong>
      </div>

      <div class="in_content">
      	<img src="./upload/${dto.filename }" style='width:100%'>
     	 <input type='hidden' name='oldfile' value='${dto.filename }'>
      </div>
   </div>

   <div class="_line">
      <div class="in_line">
         <strong>변경할 파일 업로드</strong>
      </div>
      <div class="in_content">
          <input type="file" name="fileload" size="50" accept="image/bmp,image/rle,image/dib,image/jpg,image/jpeg,
                                        image/gif,image/png,image/tif,image/tiff">
      </div>
   </div>

   <div class="_lineC">
      <div class="in_line">
         <strong>내용</strong>
      </div>

      <div class="_content">
         <textarea rows="15" cols="60" name="content" style="resize: none; border-color: #D3D2D2;">${dto.myContent }</textarea>
         
      </div>
   </div>


   <div id="_bottom" align="right" style="padding: 10px;">
      <button type="submit" id="btnSubmit" class="btn btn-outline-warning">완료</button>&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" onclick='goList()' class="btn btn-outline-secondary">취소</button>

   </div>
</div>

</form> 

</div>





<div class="_bottom2" style="height: 350px;"></div> 


<script>
   function goList(){
      location.href = "rescued?param=list"
   }
</script>



</body>
</html>