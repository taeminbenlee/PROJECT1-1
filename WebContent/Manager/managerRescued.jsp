<%@page import="util.UtilEx"%>
<%@page import="Dto.RescuedDogDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// 값 받아오기
List <RescuedDogDto> list = (List<RescuedDogDto>)request.getAttribute("list"); //테이블에 뿌려줄 List 
int pages = Integer.parseInt((String)request.getAttribute("page"));            //현재 페이지 
int viewPage = Integer.parseInt((String)request.getAttribute("viewPage"));     //보이는 페이지 수
String choice = (String)request.getAttribute("choice");
String search = (String)request.getAttribute("search");
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
#search_btn{
   color: gray;
}

.search_choice{
   width: 80px;
   height: 30px;
   text-align: center;
}

.search_input{
   resize: none;
   border-left: none;
   border-right: none;
   border-top: none;
   text-align: center;
}
</style>


<title>견생역전</title>

</head>
<body>

<div class="additon_zone">         <!-- top전체 -->
   <div class="pageTop">         <!-- 사진 -->
      <div class="pic">
          <img alt="" src="./images/uiui.png" style="width: 100%"> 
      </div>
   </div>
</div>
   
   <div align="center">   <!-- 글씨 -->
      <br><br>
      <h1 align="center" style="font-size: 26px;">입양 리스트 관리</h1><br>
<br>

<div align="center">
   
   <table class="table table-hover" style="width: 1000px">
   <thead>
   <tr align="center">
      <th>번호</th>
      <th>제목</th>
      <th>관리</th>
   </tr>
   </thead>
   <tbody>
   
   <%
   if(list == null || list.size() == 0){ 
   %>
      <tr>
        <td colspan="3">작성된 글이 없습니다</td>
      </tr>
   <%
   }
   else{
      for(int i = 0;i < list.size(); i++){
         RescuedDogDto dto = list.get(i);
         %>
         <tr align="center"  height="5">
            <th><%=i + 1 %></th>
            <td>
               <a href='rescued?param=detail&seq=<%=dto.getSeq() %>'> 
               <%=dto.getTitle() %> 
               </a>
            </td>
            <td>
            <input type='button' class='btn btn-light' data-toggle="modal" value='입양진행중' onclick="adoptGo('<%=dto.getSeq() %>')"> 
            <input type='button' class='btn btn-light' data-toggle="modal" data-target="#myModal1" value='입양취소' onclick="seqfunc('<%=dto.getSeq() %>')"> 
            <input type='button' class='btn btn-light' data-toggle="modal" value='입양완료' onclick='adoptOk(<%=dto.getSeq() %>)'> 
            <input type='button' class='btn btn-light' data-toggle="modal" value = '삭제' onclick="seqfunc('<%=dto.getSeq() %>')" data-target="#myModal2">
            </td>
         </tr>
         <%
         }
      }
%>
         
   </tbody>
</table>
</div>
<br><br>




<!-- 페이징  -->
<div align="center">
<%                //출력되는 페이지 수 
for(int i = 0;i < viewPage; i++){
   if(pages == i){   // 현재 페이지 [1] 2 [3] 
      %>
      <span style="font-size: 15pt; color: black; font-weight: bold;">
         <%=i + 1 %>
      </span>&nbsp;
      <%
   }
   else{
      %>      
      <a href="#none" onclick="goPage(<%=i %>)"
         style="font-size: 15pt; color: gray; text-decoration: none">
         [<%=i + 1 %>]
      </a>&nbsp;   
      <%
   }   
}
%>
</div><br><br>

<!-- 검색기능  -->
<div align=center>
<table class="utill2">
<tr>
   <th>
   <select id="choice" class="form-control" name="sellist1"> 
      <option value="title">제목</option>
      <option value="content">내용</option>
   </select>
   </th>
   
   <th>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" id="search" class="search_input" value='<%=search %>' placeholder="검색어를 입력하세요.">
   </th>
   <th>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <button type='button' class='btn btn-link' onclick="search()">
   <i class="fa fa-search" style="font-size:24px; color:gray"></i>
   </button>
   </th>
</tr>
</table>
<br>

<!-- 그 외 버튼 기능  -->
<div align='center'>
<input type='button' class='btn btn-light' value='목록'  onclick="reList()">
<input type='button' class='btn btn-light' value='HOME' onclick="goHome()">
</div>
<br><br>







<!-- 삭제 버튼 -->
<div class="modal fade" id="myModal2">
<div class="modal-dialog modal-md">
      <div class="modal-content">
       <div class="modal-header">     
            <br>
          <h4 class="modal-title">정말로 삭제하시겠습니까</h4>
          <br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" onclick='deleteRescued()'>예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" >아니오</button>
        </div>
      </div>
</div>
</div>
  
  
<!-- 입양취소 버튼 -->
<div class="modal fade" id="myModal1">
<div class="modal-dialog modal-md">
      <div class="modal-content">
       <div class="modal-header">     
            <br>
          <h4 class="modal-title">정말로 취소하시겠습니까</h4>
          <br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" onclick='cancle()'>예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" >아니오</button>
        </div>
      </div>
</div>
</div>
<br>
</div>
</div>



<script>
let seqnum;

function seqfunc(seq) {
   seqnum = seq;
}

// 검색 기능 
function search(){
   let choice = document.getElementById("choice").value;
   let search = document.getElementById("search").value;
   location.href = "rescued?param=list&choice=" + choice + "&search=" + search;
};

// 페이징 기능
function goPage(page){
   let choice = document.getElementById("choice").value;
   let search = document.getElementById("search").value;
   location.href = "rescued?param=list&choice=" + choice + "&search=" + search + "&pageNumber=" + page;
};

// 목록으로 돌아가기
function reList(){
   location.href = "manager?param=managerRescuedList";
};


//홈으로 돌아가기
function goHome(){
   location.href = "rescued?param=home";
};


//입양 진행중
function adoptGo(seq){
   location.href = "manager?param=managerRescuedAdoptGo&seq="+seq;
};


//입양 취소
function cancle(){
   location.href = "manager?param=managerRescuedAdoptCan&seq="+seqnum;
}; 


//입양 완료
function adoptOk(seq){
   location.href = "manager?param=managerRescuedAdoptOk&seq="+seq;
}; 

//삭제
function deleteRescued(){
   location.href="manager?param=managerRescuedDel&seq="+seqnum;
};


</script>


</body>
</html>