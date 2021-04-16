<%@page import="java.util.List"%>
<%@page import="Dto.AdoptDogDto"%>
<%@page import="Dto.MemberDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
ServletConfig mConfig = null;
final int BUFFER_SIZE = 8192;
mConfig = config;   
String fupload = mConfig.getServletContext().getRealPath("/upload");
%> 
      
<%
request.setCharacterEncoding("utf-8");
MemberDto mem = (MemberDto)request.getSession().getAttribute("login");
%>

<%
AdoptDogDto dto = (AdoptDogDto)request.getAttribute("detail");
int seq = dto.getSeq();
System.out.println("~~~~~~-------seq:" + seq);
%>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adoptDetail</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" />
<link rel="stylesheet" href="./css/bootstrap.min.css" />
<link rel="stylesheet" href="./css/templatemo-style.css" />

</head>
<body>


<br><br><br><br>
<div class="tm-page-container mx-auto">
   <header class="text-center">
       <h1 class="tm-title text-uppercase" id="parent_Detail">Adoptable Dog</h1>
       <p class="tm-primary-color"><i>change a pet's life</i></p>
        </header>

<section class="tm-section">

<!-- 네비바 -->  
<nav class="tm-nav">
<ul>
<%
if(dto.getMyid().equals(mem.getMyid()) || (mem.getMymanager()==1)){
   System.out.println("~~~~~~seq:" + dto.getSeq());
%>             
       <li>
          <a href="javascript:void(0)" onclick="updateAdopt(<%=dto.getSeq() %>)"><span class="tm-nav-deco"></span>수정</a>
       </li>
       <li>
          <a href="javascript:void(0)"  data-toggle="modal"  data-target="#myModal2" >
            <span class="tm-nav-deco"></span>삭제
          </a>
      </li> 
      <li class="active">
         <a href="javascript:history.back(-1)"><span class="tm-nav-deco"></span>목록</a>
      </li>
</ul>
<%
}
%>
</nav>

            <div class="tm-content-container">
                <div class="mb-0 tm-img-overlay-wrap">
                  <div class="tm-img-overlay">
                     <img alt="" src="./upload/<%=dto.getRdfilename() %>">
                  </div>
                    <div class="tm-img-overlay-text text-white p-5"></div>
                </div>
                
            <div class="tm-content">
                  <div class="form-group">
                      <input type="text" id="contact_email" name="contact_email"
                              class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                              placeholder="<%=dto.getRdtitle() %>" required="" />
                  </div>
                  <div class="form-group">
                      <textarea rows="6" id="contact_message" name="contact_message"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                style="resize: none;" placeholder="<%=dto.getRdmycontent() %>"></textarea>
                  </div>
           </div>
           </div>
           
</section>
</div>

<input type="hidden" name="seq" value="<%=dto.getSeq()%>">                
<div class="tm-page-container mx-auto">
        <header class="text-center">
            <h1 class="text-uppercase" id="adopt_Detail">입양 신청서</h1>
            <p class="tm-primary-color"><i>change a pet's life</i></p>
        </header> 

    <section class="tm-section">
       
            <div class="tm-content-container2" id="adopt_Detail">
                <div class="tm-content">
                        <div class="form-group">
                            <input type="text" id="contact_email" name="contact_email"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="작성자   : <%=dto.getMyid() %>"  readonly style="resize: none; background-color: #fff" required="" />
                        </div>
                        
                        <div class="form-group">
                            <input type="text" id="contact_email" name="contact_email"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="제목  :  <%=dto.getTitle() %> "  readonly style="resize: none; background-color: #fff" required="" />
                        </div>
                        
                        <div class="form-group">
                            <input type="text" id="contact_email" name="contact_email"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="입양희망일  : <%=dto.getRdate() %> "  readonly style="resize: none; background-color: #fff" required="" />
                        </div>
                        
                        <div class="form-group">
                            <input type="text" id="contact_email" name="contact_email"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                placeholder="연락처  : <%=dto.getPhoneNum() %>"  readonly style="resize: none; background-color: #fff" required="" />
                        </div>

                        <div class="form-group">
                            <textarea rows="6" id="contact_message" name="contact_message"
                                class="form-control rounded-0 border-top-0 border-right-0 border-left-0"
                                readonly style="resize: none; background-color: #fff" ><%=dto.getMycontent() %></textarea>
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
          <button type="button" class="btn btn-secondary" onclick='deleteAdopt(<%=dto.getSeq() %>)'>예</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" >아니오</button>
        </div>
      </div>
</div>
</div>



<script type="text/javascript">

function updateAdopt(seq) {
   
      location.href = "adopt?param=adoptUpdate&seq=" + seq;

}

 function deleteAdopt(seq){
   location.href = "adopt?param=AdoptDelete&seq=" + seq;
}


</SCRIPT>

</body>
</html>

