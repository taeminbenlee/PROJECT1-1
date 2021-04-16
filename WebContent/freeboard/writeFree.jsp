<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유글 작성</title>

<style>


#s_top{
	height: 200px;
	fon
}

._center{
	width: 100%;
	height: 1100px;
}

 
#_contents {
	width: 800px;
	height: 1100px;
	float:center;
	border-radius: 3px;
	margin-top: 0;
	
}

._line{
	width: 740px;
	height: 70px;
	margin: 20px;
	border-bottom: 1px solid #E4E3E2;
	text-align: left;
}

._line2{
	width: 740px;
	height: 500px;
	margin: 20px;
	border-bottom: 1px solid #E4E3E2;
	text-align: left;
}


.in_line{
	width: 200px;
	height: 60px;
	margin: 5px;
	float: left;
	font-weight: 16px;
	font-family: "Nanum Gothic",sans-serif;
	word-break: keep-all;
	text-align: left;
}

.in_content{
	width: 400px;
	height: 60px;
	margin: 5px;
	float: left;
}

input{
	width: 390px;
	height: 40px;
	margin-top: 5px;
	margin-bottom: 5px;
}

.form-control{
	width: 390px;
	height: 45px;
	margin-top: 5px;
	margin-bottom: 5px;
	border-color: #D3D2D2;
}

select{
	width: 200px;
	height: 40px;
	margin-top: 5px;
	margin-bottom: 5px;
	border-color: #D3D2D2;
}

._lineC{				/* 내용 */
	width: 740px;
	height: 480px;
	margin: 20px;
	text-align: left;

}

.in_lineC{				/* 내용 */
	width: 400px;
	height: 60px;
	margin: 5px;
	float: left;
	border-top: 1px solid gray;
	text-align: left;
}

._content{
	width: 450px;
	height: 450px;
	margin: 5px;
	float: left;
}

</style>


</head>
<%
request.setCharacterEncoding("utf-8");
Object ologin = request.getSession().getAttribute("login");
MemberDto mem = null;
String id = "";
if(ologin == null){
	response.sendRedirect("login.jsp");
} else {
mem = (MemberDto)ologin;	
id = mem.getMyid();	
}
%>
<body>


<div id="s_top" align="center">

<br><br>
	<h4 style="margin-top: 40px;">자유게시판</h4><br>
	
	
</div>	<!-- top -->
<div class="_center" align="center">
	
<form action="freeFile" method="post" enctype="multipart/form-data">
<input type="hidden" name="param" value="writeFreeAf">
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
			<strong>제목</strong>
		</div>
		<div class="in_content">			
			<input type="text" name="title" id="title" class="form-control">
		</div>
	</div>	
	
	<div class="_line2" >
		<div class="in_line">
			<strong>내용</strong>
		</div>
		<div class="in_content" >
		<textarea  name="content" id="content" rows="19" cols="52" style="resize:none"></textarea>
		</div>
	</div>	
	
	<div class="_line">
		<div class="in_line">
			<strong>이미지파일</strong>
		</div>
		<div class="in_content">
				<input type="file" name="fileload" id="imageFile" accept="image/bmp,image/rle,image/dib,image/jpg,image/jpeg,
		 											image/gif,image/png,image/tif,image/tiff">
		</div>
	</div>	
			
	<div style="height: 60px;">
	
	</div>
		<div id="_bottom" align="right" style="padding: 10px;">
			<button type="submit" id="btnSubmit" class="btn btn-outline-secondary">작성 완료</button>	
			<button type="button" OnClick="javascript:history.back(-1)" class="btn btn-outline-secondary">취소</button>
		</div>

</div>
</form>
</div>	
	
<script type="text/javascript">
$(document).ready(function () { 
	$(function() {
	  
	    $("#btnSubmit").click(function() {
	    	//빈칸체크
	    	$("#title").prop('required',true);
	    	$("#content").prop('required',true);
	    });
	    
	});
	

});

</script>
	
	
	
	
</body>


</html>