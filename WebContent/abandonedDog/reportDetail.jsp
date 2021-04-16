<%@page import="Dto.MemberDto"%>
<%@page import="Dto.abandonedDogDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ServletConfig mConfig = null;
final int BUFFER_SIZE = 8192;
mConfig = config;      
String fupload = mConfig.getServletContext().getRealPath("/upload");

request.setCharacterEncoding("utf-8");
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");
abandonedDogDto dto  = (abandonedDogDto)request.getAttribute("detail");
int seq = dto.getSeq();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" />
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<link rel="stylesheet" href="./css/templatemo-style.css" />

</head>

<body>
<br><br>

<div class="tm-page-container mx-auto">
        <header class="tm-header text-center">
            <h1 class="tm-title text-uppercase">유기견 제보</h1>
            <p class="tm-primary-color"><i>change a pet's life</i></p>
        </header>

    <section class="tm-section">
<% 
// 글쓴이와 로그인 한 사람이 같거나 매니저라면
if(dto.getMyid().equals(mem.getMyid()) || (mem.getMymanager()==1)){
%>
                <nav class="tm-nav">
                   <ul>
                      <li>
                           <a href="javascript:void(0)" onclick="updateBtn(<%=dto.getSeq() %>)"><span class="tm-nav-deco"></span>수정</a>
                       </li>
                       <li>
                           <a href="javascript:void(0)" data-toggle="modal" data-target="#myModal2" onclick="seqfunc(<%=seq %>)"><span class="tm-nav-deco"></span>삭제</a>
                       </li> 
                       
                       <li class="active">
                           <a href="javascript:history.back(-1)"><span class="tm-nav-deco"></span>목록</a>
                       </li>
<%
}
%>
<% 
//매니저이고 구조완료되지 않을 때만 버튼 보이기
if(mem.getMymanager()==1){
	if(!(dto.getAddress().contains("[구조완료]"))){
%>						
           <li>
               <a href="javascript:void(0)" onclick="authorityBtn(<%=dto.getSeq() %>)"><span class="tm-nav-deco"></span>구조완료</a>
           </li> 
<%
	}
}
%>                       
                   </ul>
            </nav>
            <div class="tm-content-container">
                <div class="mb-0 tm-img-overlay-wrap">
                
                  <div class="tm-img-overlay">
                     <img class="uploadImage" alt="" src="./upload/<%=dto.getNewfilename()%>">
                  </div>
                    <div class="tm-img-overlay-text text-white p-5">
                   
                        
                     </div>
                </div>
                 <div class="tm-content">
                        <div class="form-group">
                            <input type="text" id="contact_name" name="contact_name"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="작  성  자 : <%=dto.getMyid() %> " readonly style="resize: none; background-color: #fff" required="" />
                        </div>
                        <div class="form-group">
                            <input type="email" id="contact_email" name="contact_email"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="발견장소 : <%=dto.getAddress() %>" readonly style="resize: none; background-color: #fff" required="" />
                        </div>
                        
                        <div class="form-group">
                            <input type="email" id="contact_email" name="contact_email"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="작성일자 : <%=dto.getWdate() %> " readonly style="resize: none; background-color: #fff" required="" />
                        </div>
                        
                        <div class="form-group">
                            <input type="email" id="contact_email" name="contact_email"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="위험정도 : <%=dto.getDanger() %> " readonly style="resize: none; background-color: #fff"required="" />
                        </div>
                        
                        <div class="form-group">
                            <input type="email" id="contact_email" name="contact_email"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="연  락  처 : <%=dto.getPhonenum() %> " readonly style="resize: none; background-color: #fff"required="" />
                        </div>

                        <div class="form-group">
                        
                            <textarea rows="6" id="contact_message" name="contact_message"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                 required="" readonly style="resize: none; background-color: #fff"><%=dto.getMycontent()%></textarea>
                        </div>
             </div>
            </div>
   </section>
</div>

            
<!-- 삭제 버튼 Modal -->
<div class="modal fade" id="myModal2">
<div class="modal-dialog modal-md">
      <div class="modal-content">
       <div class="modal-header">  	
       	  <br>
          <h4 class="modal-title">정말로 삭제하시겠습니까</h4>
          <br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" onclick='deletes()'>예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" >아니오</button>
        </div>
      </div>
</div>
</div>



<script type="text/javascript">

let seq = 0
function seqfunc(seqs){
	seq = seqs	
}


function updateBtn( seq ) {
   location.href = "abandog?param=updateReport&seq=" + seq;
}

function authorityBtn( seq ) {
   
   location.href = "abandog?param=authorizeAf&seq=" + seq;
}

function deletes(){
   location.href = "abandog?param=deleteReport&seq=" + seq;
}

</script>


</body>



</html>