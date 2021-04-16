<%@page import="Dto.MemberDto"%>
<%@page import="Dto.RescuedDogDto"%>
<%@page import="java.util.List"%>
<%@page import="Dao.RescuedDogDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// 값 받아오기
List <RescuedDogDto> list = (List<RescuedDogDto>)request.getAttribute("list"); //테이블에 뿌려줄 List 
int pages = Integer.parseInt((String)request.getAttribute("page"));            //현재 페이지 
int viewPage = Integer.parseInt((String)request.getAttribute("viewPage")); //보이는 페이지 수
String choice = (String)request.getAttribute("choice");
String search = (String)request.getAttribute("search");
%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>견생역전</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

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




</head>
<body>

<div class="additon_zone">       <!-- top전체 -->
   <div class="pageTop">         <!-- 사진 -->
      <div class="pic">
          <img src="./images/afaf.png" style="width: 100%;"> 
      </div>
   </div>
   <br><br><br>
</div>

<div class="center_" align="center">

<p style="font-size: 22px">동물 친구들의</p>
<h4>새로운 가족이 되어주세요.</h4>
<br><br><br>


<div align="center">
<table class="table table-hover" style="width: 1000px">
   <thead>
   <tr>   
      <th>NO</th><th>TITLE</th><th>COUNT</th><th>DATE</th>
   </tr>
   </thead>
   
   <tbody>
<%
   if(list == null || list.size() == 0){
      %>
      <tr><td colspan="3">작성된 글이 없습니다</td>   </tr>
      <%
   }
   else{
      for(int i = 0;i < list.size(); i++){
         RescuedDogDto dto = list.get(i);
         %>
         <tr>
            <th><%=i + 1 %></th>
            <td><a style=color:black href='rescued?param=detail&seq=<%=dto.getSeq() %>'>
               <%=dto.getTitle() %>
            </a></td>
            <td><%=dto.getVcount() %></td>
            <td><%=dto.getWdate().substring(0,10) %></td>
         </tr>
         <%
         }
      }
%>
         
   </tbody>
</table>
</div>



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
<table class="utill2">
<tr>
<th>
   <select id="choice" class="search_choice" name="sellist1">
      <option value="title">제목</option>
      <option value="content">내용</option>
   </select>
   </th>
   <th>&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="text" id="search" class="search_input" value='<%=search %>' placeholder="검색어를 입력하세요.">&nbsp;&nbsp;&nbsp;
   </th>
   <th>
   <button type='button' class='btn btn-link' id="search_btn" onclick="search()">
         <i class="fa fa-search" style="font-size:24px"></i>
   </button>
   </th>
</tr>
</table>


<!-- 그 외 버튼 기능  -->
<div align='center'>
<input type='button' class='btn btn-light' value='목록'  onclick="reList()">
&nbsp;
<input type='button' class='btn btn-light' value='HOME' onclick="goHome()">
</div>
<br><br>

</div>

<script>
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
   location.href = "rescued?param=list";
};


//홈으로 돌아가기
function goHome(){
   location.href = "rescued?param=home";
};



</script>




</body>
</html>