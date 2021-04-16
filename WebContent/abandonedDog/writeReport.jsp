<%@page import="Dto.abandonedDogDto"%>
<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>

<%
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");
String id = "";
if(mem == null){
%>
   <script>
	alert("로그인이 필요합니다");
	location.href="mem?param=toIndex"

   </script>
<%   
} 
else 
id = mem.getMyid();
%>   

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/abandonReport.css">

</head>


<body>


<div id="s_top" align="center">
<br><br>
   <h4>유기동물을 제보 합니다.</h4><br>
   <p style="font-size: 20px">소중한 아이들의 보호에 참여해 주세요.</p><br>
   

</div>   <!-- top -->

<div class="_center" align="center">


<form action="filedd?param=writeAf" method="post" enctype="multipart/form-data">
   
<div id="_contents" >
   <div class="_line">
      <div class="in_line">
         <strong>아이디</strong>
      </div>
      <div class="in_content">
         <input type="text" name="id" class="form-control" value="<%=id %>" readonly="readonly">
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>위험등급<br>
         (매우위급5 ~ 건강함1)</strong>
      </div>
      <div class="in_content">
         <select id="UrgencyLevel" name="danger">
            <option value="0">--- 위험도 선택 ---</option>
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
         <input type="text" class="form-control" name="address">
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>연락처</strong>
      </div>
      <div class="in_content">
         <input type="text" name="phonenum" class="form-control">
      </div>
   </div>
   
   <div class="_lineC">
      <div class="in_line">
         <strong>내용</strong>
      </div>
      <div class="_content">
         <textarea rows="19" cols="60" name="content" style="resize: none; border-color: #D3D2D2;">- 구체적 상황 설명
- 피해동물 설명(마리수, 피해정도와현재상태, 마지막 발견 등)
- 학대 도중 발견 시 학대자 설명 (인적사항, 인원 수 등)
</textarea>
      </div>
   </div>
   
   <div class="_line">
      <div class="in_line">
         <strong>이미지파일</strong>
      </div>
      <div class="in_content">
         <input id="imageFile" type="file" name="fileload" accept="image/bmp,image/rle,image/dib,image/jpg,image/jpeg,
                                        image/gif,image/png,image/tif,image/tiff">
      </div>
   </div>
   
   <div style="height: 60px;">
      
   </div>
   
   <div id="_bottom" align="right" style="padding: 10px;">
      <button type="submit" id="btnSubmit" class="btn btn-outline-warning">제보</button>&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" OnClick="javascript:history.back(-1)" class="btn btn-outline-secondary">취소</button>
   </div>
   
   
   
</div>   

   

   
</form>

</div>

   <div id="alert" class="alert alert-warning" role="alert" style="display:none;">
     이미지 파일을 업로드 해주세요!
   </div>
   
   <div align="right" style="float: right;"></div>
</body>



<script type="text/javascript">
$(document).ready(function () { 
   $(function() {
       $("*").focus(function () {
           $(this).css("background-color", "white");
       });
       $("*").blur(function () { 
           $(this).css("background-color", "white");
       });
       
       $("#btnSubmit").click(function() {
          //빈칸체크
          $("*").prop('required',true);
          
       });
       
   });
   

});

</script>
<script type="text/javascript">
$(function fileTypeCheck(obj) {
   pathpoint = obj.value.lastIndexOf('.');
   filepoint=obj.value.substring(pathpoint+1,obj.length);
   filetype=filepoint.toLowerCase();
   if(filetype=='bmp'||filetype=='jpg'||filetype=='gif'||filetype=='png'||filetype=='tif'){
      $('#alert').hide();
   } else {
      $('#alert').show();
   parent Obj = obj.parentNode
   node=parentObj.replaceChild(obj.cloneNode(true),obj);
   return false;
   }
   
});


$("#btnSubmit").click(function() {
	   //빈칸체크
	   $("*").prop('required',true);

});


</script>
</html>