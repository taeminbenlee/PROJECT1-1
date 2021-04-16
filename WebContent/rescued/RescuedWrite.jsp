<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>견생역전</title>
<link rel="stylesheet" href="./css/rescuedWrite.css" />
</head>
<body>

<div id="s_top" align="center">
   <br><br>
   <h2>반려동물 등록</h2>
   <br>
</div>   <!-- top -->

<div class="_center" align="center">

<form action='./rcdFile' method="post" enctype="multipart/form-data">
<input type="hidden" name='oldfile' size=50 value=${fileName }>

<div id="_contents" >
   <div class="_line">
      <div class="in_line">
         <strong>제목</strong>
      </div>
      <div class="in_content">
         <input type="text" name="title" id='title' class="form-control">
      </div>
   </div>
   
   <div class="_lineFile">
      <div class="in_line">
         <strong>제보자가 업로드한 파일</strong>
      </div>
      <div class="in_content">
         <img src="./upload/${fileName }" style='width:100%; height: 200px;'>
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>변경할 파일 업로드</strong>
      </div>
      <div class="in_content">
         <input type="file" name="fileload" class="form-control" style="width: 400px">
      </div>
   </div>
   
   <div class="_lineC">
      <div class="in_line">
         <strong>내용</strong>
      </div>
      <div class="_content">
         <textarea rows="19" cols="75" name="content" id='content' style="resize: none; border-color: #D3D2D2;">***** 견종을 반드시 작성해주세요! *****</textarea>
      </div>
   </div>
   
   <div id="_bottom" align="right" style="padding: 10px;">
      <button type="submit" id="btnSubmit" class="btn btn-outline-warning">업로드</button>&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" onclick="javascript:history.back(-1)"  class="btn btn-outline-secondary">취소</button>
   </div>
   
</div>   

</form> 
</div>
<script>
$(document).ready(function(){
	
	//빈칸체크
	$("#btnSubmit").click(function() {
		$("title").prop('required',true);
		$("content").prop('required',true);
	});
	
	
	
})
</script>
</body>
</html>