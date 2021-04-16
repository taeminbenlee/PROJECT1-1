<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
      String content = request.getParameter("content");
      if(request.getParameter("content")==null) {
            content = (String)request.getAttribute("content");   
            if((String)request.getAttribute("content") == null){
               content = request.getParameter("content");
               if(content == null){
                   content = "main";
                   
                } 
           }
      }      
%>

<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8>

<!-- 커스텀 css -->
<link rel="stylesheet" href="./css/taemin.css?after">
<script src="https://kit.fontawesome.com/84e12194d6.js" crossorigin="anonymous"></script>
<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- 제이쿼리 -->
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- AJax -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 쿠키 -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- 카카오 서비스 js -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<!-- Sweet Alert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<!-- 아이콘 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- list (Afterstroy, bbs) -->
<link rel="stylesheet" href="css/thumbnail-gallery.css">

<style>
.swal2-shown{
   padding-right: 0px !important;
} 

body.swal2-shown:not(.swal2-no-backdrop):not(.swal2-toast-shown){
   overflow: auto !important;
}

</style>

<title>CHANGE A PET'S LIFE</title>
</head>

<body>
<table style="width: 100%">
<tr>
	<td>
		 <jsp:include page="menu.jsp" flush="false"/>
	</td>
</tr>

<tr>
	<td class="body">
		<jsp:include page='<%=content + ".jsp" %>' flush="false"/> 
	</td>
</tr>

<tr>
	<td class="footer">
		<jsp:include page="bottom.jsp" flush="false"/>
	</td>
</tr>
</table>

</body>
</html>