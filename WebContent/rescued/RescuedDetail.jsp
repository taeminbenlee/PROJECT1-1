<%@page import="Dto.MemberDto"%>
<%@page import="Dto.RescuedDogDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>견생역전</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" />
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<link rel="stylesheet" href="./css/templatemo-style.css" />


</head>
<body>

<br><br>
<div class="tm-page-container mx-auto">
        <header class="tm-header text-center">
            <h3 class="tm-title text-uppercase">아이들의 새로운 가족이 되어주세요</h3>
            <p class="tm-primary-color"><i>change a pet's life</i></p>
        </header>

    <section class="tm-section">
         <nav class="tm-nav" style="right: -35%; !important" >
             <ul class="rd-list">
                 <li class="active">
                    <a href="javascript:void(0)" onclick="goList()"><span class="tm-nav-deco"></span>목록</a>
                 </li>
                <!-- 로그인 한 경우만 보이기 -->   
             <c:if test='${id != ""}'>
                  <!-- 입양진행을 안하고 있는 경우만 보이기 -->
                  <c:if test='${dto.condition == 0 }'>       
                        <li>
                           <a href="javascript:void(0)" onclick='adopt()'><span class="tm-nav-deco"></span>입양신청</a>
                       </li>
                        </c:if>
                        
                    <!-- 매니저만 보이게 하기 -->
                  <c:if test='${manager == 1 }'>     
                         <li>
                           <a href="javascript:void(0)" onclick='update()'><span class="tm-nav-deco"></span>수정</a>
                        </li>
                       
                    <!-- 입양 진행중일 때만 보이게 하기-->
                      <c:if test='${dto.condition == 1}'>      
                         <li>
                             <a href="javascript:void(0)" data-toggle="modal" data-target="#myModal1"><span class="tm-nav-deco"></span>입양취소</a>
                         </li> 
                          <li>
                             <a href="javascript:void(0)" onclick='adoptOk()'><span class="tm-nav-deco"></span>입양완료</a>
                         </li> 
                	</c:if> 
                     <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#myModal2"><span class="tm-nav-deco"></span>삭제</a>
                     </li> 
                     <li>
                        <a href="javascript:void(0)"  data-toggle="modal" onclick='manager()'><span class="tm-nav-deco"></span>관리자페이지</a>
                     </li> 
      			</c:if>
  			 </c:if>                       
            </ul>
         </nav>
         
            
         <div class="tm-content-container">
             <div class="mb-0 tm-img-overlay-wrap">
             
                <div class="tm-img-overlay">
                       <img class="uploadImage" src="./upload/${dto.filename }" style="min-width: 100%; max-height: 400px; border-top-left-radius: 20px; border-top-right-radius: 20px;">      
                </div>
                    <div class="tm-img-overlay-text text-white p-5"></div>
             </div>
         <div class="tm-content" style="height: 600px">
                 <div class="form-group">
                      <input type="email" id="contact_email" name="contact_email"
                             class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                             placeholder="TITLE : ${dto.title }" readonly style="resize: none; background-color: #fff" required="" />
                 </div>
                 <div class="form-group">
                       <textarea rows="6" id="contact_message" name="contact_message"
                                 class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                 required="" readonly style="resize: none; background-color: #fff">${dto.myContent }</textarea>
                </div>
        </div>
     </div>
   </section>
</div>


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






<script>

function goList(){
   location.href = "rescued?param=list";
}

// 글 수정 (관리자만)
function update(){
   location.href = "rescued?param=update&seq=${dto.seq }";
}

//입양신청서 작성
function adopt(){
   location.href = "adopt?param=writeAdopt&seq=${dto.seq }";
} 

//입양 취소
function cancle(){
   location.href = "rescued?param=cancle&seq=${dto.seq }";
} 

//입양 완료
function adoptOk(){
   location.href = "rescued?param=adoptOk&seq=${dto.seq }";
} 

//삭제
function deleteRescued(){
   location.href = "rescued?param=deleteRescued&seq=${dto.seq }";
} 

//관리자 페이지로 이동
function manager(){
   location.href = "manager?param=managerRescuedList";
} 
</script>



</body>
</html>