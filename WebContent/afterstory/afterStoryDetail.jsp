<%@page import="Dto.CommentDto"%>
<%@page import="java.util.List"%>
<%@page import="Dao.afterStoryDao"%>
<%@page import="Dto.abandonedDogDto"%>
<%@page import="Dto.afterStoryDto"%>
<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");
afterStoryDto dto  = (afterStoryDto)request.getAttribute("detailAf");
int seq = dto.getSeq();
String id = "";
int managerNum = 0;

if(mem != null){
	id = mem.getMyid();
	managerNum = mem.getMymanager();
}


//댓글 관련 필요 요소
List<CommentDto> list = (List<CommentDto>)request.getAttribute("list");
int pages = Integer.parseInt((String)request.getAttribute("page"));
int viewPage = Integer.parseInt((String)request.getAttribute("viewPage"));
%>


<!DOCTYPE html>
<html>
<head>
<style type="text/css">

.vnum{
	float: right;
}

.im{
	width: 700ppx;
	height: 900px;
}
.con{
	font-size: 30px

}

</style>
<meta charset="UTF-8">
<title>afterStoryDetail</title>

</head>
<body>


<br><br>
<div align="center">
<div class="container">
<h2>입양 후기</h2>
<br>
<table class='table'>

	<tr>
		<th height="50px" >NAME </th>
		<td>
			<%=dto.getMyid() %>
		<td>		
	</tr>
	<tr>
		<th height="50px">TITLE </th>
		<td>
			<%=dto.getTitle() %>
		<td>		
	</tr>
	<tr>
		<th height="50px">DATE </th>
		<td>
			<%=dto.getWdate() %>
	<td>		
	</tr>
	<tr>
		<th>CONTENT </th>
		<td>
			<img class="im" alt="" src="./upload/<%=dto.getFilename()%>" style="max-height: 600px;">
			<br><br>
			<%=dto.getMycontent() %>
		<td>		
	</tr>
</table>




<!-- 댓글 쓰기 -->
<form action="./comment" method='post'>
<input type='hidden' name='param' value='write'>
<input type='hidden' name='urlParam' value='afterStoryDetail'>
<input type='hidden' name='seq' value=<%=seq %>>
<input type='hidden' name='url' value='after'>
<input type='hidden' name='myId' value=<%=id %>>
<input type='hidden' name='free' value=2>
<%
if(!(id.equals(""))){
%>
<table class='table' width='100%'>
    <tr>
		<td>
			<div class='row'>
				<div class='col-10'>
					<input type='text' class='form-control' name='content' placeholder='댓글을 입력하세요'>
				</div>
				<div class='col=2'>
					<input type='submit' class='btn btn-light' value='입력완료'>
				</div>
			</div>
		</td>
	</tr>
</table>
</form>
<%
}
%>

<!-- 댓글 불러오기 -->
<%
if(list != null){
%>
<table class='table' width='100%'>
	<tr>
	<td><br></td><td><br></td><td><br></td>
	</tr>
<%
for(int i=0; i<list.size(); i++){
	CommentDto cDto = list.get(i);
%>
	
	<tr>
		<td><%=cDto.getMyID() %></td>
		<td>
		<input type='text'  readonly='readonly' class='form-control' name='content' value='<%=cDto.getContent() %>'>
		</td>
		<!-- 로그인 아이디와 글쓴이가 같거나 매니저라면 수정/삭제 버튼 보이기 -->
		<%
		if(managerNum==1 || cDto.getMyID().equals(id)){
		%>
		<td>	
		<input type='button' class='btn btn-light' data-toggle="modal" data-target="#myModal1" value='수정' onclick="commentSeq('<%=cDto.getSeq()%>')">
		<input type='button' class='btn btn-light' data-toggle="modal" data-target="#myModal2" value='삭제' onclick="commentSeq('<%=cDto.getSeq()%>')">
		</td>
	</tr>
	<%
		}
	%>
<%
} // for
%>
</table>	
	

<!-- 댓글 페이징  -->
<div align="center">
<%                //출력되는 페이지 수 
for(int i = 0;i < viewPage; i++){
	if(pages == i){	// 현재 페이지 [1] 2 [3] 
		%>
		<span style="font-size: 15pt; color: black; font-weight: bold;">
			<%=i + 1 %>
		</span>&nbsp;
		<%
	}
	else{
		%>		
		<a href="#none" onclick="goPage('<%=i %>')"
			style="font-size: 15pt; color: gray; font-weight: bold; text-decoration: none">
			[<%=i + 1 %>]
		</a>&nbsp;	
		<%
	}	
}
%>
</div>

<%
} // if
%>	


<!-- 댓글 수정 Modal창 -->
  <div class="modal" id="myModal1">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">수정할 내용을 입력하세요</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <input type='text' id='newContent'>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-light" onclick='updateFinish()'>수정 완료</button>        
          <button type="button" class="btn btn-light" data-dismiss="modal">수정 취소</button>
        </div>
        
      </div>
    </div>
  </div>
  
  
  
  <!-- 댓글 삭제 Modal -->
  <div class="modal" id="myModal2">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">정말 삭제하시겠습니까</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
     
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="commentDelete()">예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">아니오</button>
        </div>
      </div>
    </div>
  </div> 
</div>


  <!-- 메인글 삭제 Modal -->
  <div class="modal" id="myModal3">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">정말 삭제하시겠습니까</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
     
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="deleteAf(<%=dto.getSeq()%>)">예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">아니오</button>
        </div>
      </div>
    </div>
  </div> 
</div>



<!-- 그 외 버튼들 -->
<br>
<div align="center" style="padding-bottom: 100px">
<%
if(dto.getMyid().equals(id) || managerNum == 1){
	%>	
	<button type="button" class='btn btn-light' data-toggle="modal"  data-target="#myModal3">삭제 </button>
	&nbsp;
	<button type="button" class='btn btn-light' onclick="updateAf(<%=dto.getSeq() %>)">수정  </button>
	&nbsp;
	<%
	}
%>
	<button type="button" class='btn btn-light' onclick="goList(<%=dto.getSeq() %>)">목록 </button>
</div>






<script type="text/javascript">
function updateAf(seq) {
	location.href = "after?param=updateStory&seq=" + seq;
}
function deleteAf(seq) {
	location.href = "after?param=afterDelete&seq=" + seq;
}
function goList(seq) {
	location.href = "after?param=afterstoryList&seq=" + seq;
}

let commSeq = 0;
//댓글의 seq 값
function commentSeq(num){
	commSeq = num;
}

function updateFinish(){
	let content = $('#newContent').val();      
	location.href = "comment?param=update&commSeq="+commSeq+"&content="+content+"&seq="+<%=seq %>+
			        "&url=after&urlParam=afterStoryDetail"
}

function commentDelete(){
	location.href = "comment?param=delete&commSeq="+commSeq+"&seq="+<%=seq %>+
	                "&url=after&urlParam=afterStoryDetail"
}

function goPage(pageNumber){
	location.href = "after?param=afterStoryDetail&pageNumber=" +pageNumber+"&seq="+<%=seq %>
}



</script>

</body>

</html>