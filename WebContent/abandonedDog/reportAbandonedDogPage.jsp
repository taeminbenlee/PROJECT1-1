<%@page import="Dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/reportAbandonedDogPage.css">

<%
request.setCharacterEncoding("utf-8");
Object ologin = request.getSession().getAttribute("login");
MemberDto mem = null;

if(ologin == null){
   
   response.sendRedirect("login.jsp");
}
mem = (MemberDto)ologin;
%>

</head>
<body>
<div id="text1" align="center">
</div>
<br>

<script type="text/javascript">
function reportBtn(){
   location.href = "abandog?param=writeReport";
}



</script>




<section class="container">
<div align="left">
   <h2>유기동물 발견시</h2>
   <h2>상황에 맞게 조치하세요. &nbsp;&nbsp;
      <button type="button" id="btn_report" class="btn btn-outline-secondary" onclick="reportBtn()">유기동물 제보하러 가기</button>
   </h2> <br>
      <ul class="list_inline">
         <li class="list_inline_item">
            <a href="#content-1" class="g-color-gray-dark-v1 g-px-5">01. 긴급동물 발견 시 대처요령</a>
         </li>
         
         <li class="list_inline_item">
            <a href="#content-2" class="g-color-gray-dark-v1 g-px-5">02. 이런 동물은 구조를 미뤄보세요.</a>
         </li>
         
         <li class="list_inline_item">
            <a href="#content-3" class="g-color-gray-dark-v1 g-px-5">03. 긴급 동물 보호조치 접수 방법</a>
         </li>
         
         <li class="list_inline_item">
            <a href="#content-4" class="g-color-gray-dark-v1 g-px-5">04. 야생동물 구조관련 단체 및 기관</a>
         </li>
            
      </ul>
   
      <hr class="g-my-0"> 
      <br><br>
      
      <div class="g-mt-50" id="content-1">
         <h3 class="g-font-size-24 g-color-gray-dark-v1 font-weight-bold">01.긴급동물 발견 시 대처요령</h3><br>
         <h4 class="g-font-size-20 g-color-gray-dark-v1 font-weight-bold">차량사고 등으로 인해 긴급 치료를 요할 시</h4><br>
         <p class="font-weight-bold g-color-primary g-my-20">동물을 인근 동물병원으로 옮기셔야합니다.</p>
            <img alt="" src="./images/report1.png" style="height: 70%; width: 100%"><br><br>
         <p class="font-weight-bold g-color-primary g-my-20">가장 빠른 방법은 목격자만이 할 수 있기 때문에 다음 방법에 의해 대처해 주시기 바랍니다.</p>
            <p>
            이때 주인이 없는 동물일 경우 다소 난감하게 대하는 동물의사도 있습니다.
            치료비 때문이라기보다는 동물의 사후 처리에 대해 곤혹스러운 마음이 들어서일 것입니다.
            먼저 동물을 병원에 버리고 가지 않는다는 신뢰를 줄 수 있도록 잘 설득하시고, 치료비 일부라도 지불해 응급처치를 받을 수 있게 해주십시오.
            </p>
            <br><br>
      
         <p class="font-weight-bold g-color-primary g-my-20">동물이송</p>
            <p>
            대부분의 목격자들이 동물 이송을 난감해 하는데 이는 그리 어려운 일이 아닙니다. 마음을 진정시키고 다음과 같이 침착하게 대처해 주십시오.
            작은 동물일 경우 그대로 품에 안아서 택시를 이용하는 방법도 있으며, 조금 큰 동물은 주변의 상가나 주변인에게 도움을 요청, 종이 박스 같은 것에 담아서 이송하는 방법도 있습니다.
            이때 동물이 구토, 설사, 출혈 등의 경우도 있으므로 반드시 타월이나 신문지, 비닐봉투 등을 준비하시기 바랍니다.
            </p>
            <br><br>
         <p class="font-weight-bold g-color-primary g-my-20">중대형의 개일 경우</p>
            <p>
            통증 때문에 난폭한 행동을 할 수 있으므로 119에 도움을 요청하십시오. 지역에 따라 119가 출동하기를 난감해 하는 경우도 있습니다.
            그럴 경우, 난폭한 개를 보통의 사람이 이송할 경우 인재로 이어지게 되면 어차피 119가 출동해야 한다는 것을 주지시키고, 사전 예방을 위한 조치라는 것을 잘 설득하셔서 도움을 받으십시오.
            </p>
            <br><br>
         <h4 class="g-font-size-18 g-color-gray-dark-v1 font-weight-bold">긴급한 동물의 응급처치를 끝낸후의 조치</h4>
         <br>

         <p class="font-weight-bold g-color-primary g-my-20">긴급한 동물은 가능한 구조자가 임시보호해 주시면서 동물단체를 통해 입양될 수 있도록 해주시기 바랍니다.</p>
         
            <p>
            모든 동물 단체는 늘어만 가는 집 없는 동물로 인해 한계상황에 처해 있고, 누구에게나 임시보호는 힘에 겨운 일입니다. 가여운 생명을 외면하지 말고 조금만 더 적극적인 입장을 취해 주시기 바랍니다.
            그러나 도저히 그럴 입장이 안 되시면 동물 구조 단체에 도움을 요청하십시오. 동물단체가 성의껏 도와줄 것입니다.
            </p>
            <br><br>
         <p class="font-weight-bold g-color-primary g-my-20">주인을 찾아보는 일은 꼭 해주셔야 합니다.</p>
            <p>
            구조자는 구조 동물에 대해 주인을 찾을 수 있도록 노력해 주시기 바랍니다. 동물이 버려진 것이 아니라 집을 나왔다가 길을 잃은 것이라면 주인이 애타게 찾고 있을 겁니다.
            동물 입장에서도 입양보다는 주인을 찾는 것이 우선이며 더 좋은 일입니다. 인근지역에 동물의 특성을 표기한 후 사진을 첨부해서 전단지를 붙이고, 파출소, 구청, 동물병운, 애견샵 등에 배포, 부착해 주시기바랍니다.
            </p>
            <br><br>


   </div>

      <div class="g-mt-50" id="content-2">
         <h3 class="g-font-size-24 g-color-gray-dark-v1 font-weight-bold">02. 잠깐! 이런동물의 구조는 미뤄보세요.</h3>
         <br>
         <p class="font-weight-bold g-color-primary g-my-20">육안으로 보아 건강상태가 비교적 양호한, 그러나 다소 초췌한 발바리들을 만날지라도 모두 구조의 대상으로 보지 말아주십시오.</p>
            <p>
            대다수의 발바리들은 주인의 관리로부터 자유롭게 키워지는 경우가 많아 행동 반경이 넓습니다. 인근지역 어딘가에 집이 있는 경우가 많으므로 떠돌이 개라고 추정될 경우엔 즉각적으로 잡는 것보다는 시간을 두고 지켜보는 것이 좋습니다.
            </p>
            <br><br>
         <p class="font-weight-bold g-color-primary g-my-20">떠돌이 생활에 적응력을 보이는 개를 포획할 경우, 오히려 불행한 결과를 초래할 수도 있습니다.</p>
            <p>
            이들은 최선의 환경을 제공할 수 있는 입양의 기회가 매우 적은 현실이므로 전문 구조단체에서 보호하다가 수용능력 한계로 인해 일정기간이 지나면 안락사될 수도 있습니다.
            사설보육원들은 모두 한계상황이기 때문에 이들을 다 받아줄 수도 없고 재정 부족으로 확장도 어려운 상황에 있습니다.
            그리고 운좋게 사설보육시설에 수용된다고 하더라도 수백 마리의 틈에서 살아가야 하기 때문에 이들은 반려동물로서 좋은 환경과 사랑을 얻기 어렵고, 사육의 개념으로 목숨을 이어갈 수밖에 없습니다.
            부상, 질병 등의 긴급을 요하는 사안이 아닐 경우엔 그대로 지켜보며 동물들의 자유로운 행동을 인정해 주며 적당하고 간헐적인 관리를 하는 것도 동물을 돌보는 방법 중 하나라 할 수 있습니다.
            </p>
            <br><br>
         <p class="font-weight-bold g-color-primary g-my-20">어린 아기 고양이</p>
            <p>
            엄마 고양이로부터 떨어진 아기 고양이를 섣불리 만지셔서는 안되며 데려오는 일도 신중하셔야 합니다. 엄마 고양이와 함께 이동 중에 떨어져 있을 수도 있기 때문입니다. 이럴 경우에는 시간을 두고 엄마 고양이가 찾으러 올 때까지 기다려야 합니다.
            그럼에도 불구하고 섣불리 데려오면 어디에선가 엄마 고양이가 오히려 안타깝게 울부짖을 겁니다.
            <br><br>
            혹은 이제부터 혼자서 살아가는 방법을 터득해야 할 시기일 수도 있습니다. 동물은 주어진 환경의 생태 속에서 스스로 조절되는 것만이 오히려 대량 학살을 피할 수 있습니다. 또한 야생성이 강한 모습을 보이는 어린 고양이들도 억지로 잡으려 애쓰지 마십시오.
            나름대로 도심 속에서 야생 형태로 살아가는 고양이들의 삶을 인정하여야 합니다. 다만, 할 수만 있다면 성장한 고양이는 불임 수술 후 재방사할 수 있는 방법을 취해 주시면 도심 속의 고양이 대량 증가 문제 해소에 큰 역할을 하게 될 것입니다.
            </p>
      </div>
      
      <br><br>
      
      
      <div class="g-mt-50" id="content-3">
         <h3 class="g-font-size-24 g-color-gray-dark-v1 font-weight-bold">03.긴급동물 보호조치 접수방법</h3>
         <br>
         
         <div class="g-mt-10">
            <p class="font-weight-bold g-color-primary g-my-20">서울시내 구별 유기동물 구조·보호 담당 기관</p>
            <div class="table-responsive">
               <table class="table table-bordered text-center">
                  <colgroup>
                     <col width="20%">
                     <col width="20%">
                     <col width="20%">
                     <col width="20%">
                     <col width="20%">
                  </colgroup>
                  
                  <thead>
                     <tr>
                        <th rowspan="2" class="g-valign-middle">구분</th>
                        <th colspan="2" class="g-valign-middle">주관부서</th>
                        <th rowspan="2" colspan="2" class="g-valign-middle">동물보호센터</th>
                        
                     <tr>
                        <th>부서명</th>
                        <th>전화번호</th>
                     </tr>
                  </thead>
                  
                  <tbody>
                     <tr>
                        <th rowspan="2">강남구</th>
                        <td rowspan="2">지역경제과</td>
                        <td rowspan="2">2104-1637</td>
                        <td>강남 25시 동물병원</td>
                        <td>02-545-8575</td>
                     </tr>
                        
                     <tr>
                        <td>동물구조관리협회   </td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th rowspan="2">강동구</th>
                        <td rowspan="2">지역경제과</td>
                        <td rowspan="2">480-1784</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <td>서울동물메디컬센터</td>
                        <td>02-457-0075</td>
                     </tr>
                     
                     <tr>
                        <th>강북구</th>
                        <td>지역경제과</td>
                        <td>901-6455</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>강서구</th>
                        <td>지역경제과</td>
                        <td>2600-6283</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>관악구</th>
                        <td>지역경제과</td>
                        <td>880-3379</td>
                        <td>러브펫종합동물병원</td>
                        <td>02-837-8875</td>
                     </tr>
                     
                     <tr>
                        <th>광진구</th>
                        <td>지역경제과</td>
                        <td>450-7322</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>구로구</th>
                        <td>지역보건과</td>
                        <td>860-2428</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th rowspan="2">금천구</th>
                        <td rowspan="2">지역경제과</td>
                        <td rowspan="2">2627-1312</td>
                        <td>강현림동물병원</td>
                        <td>02-2642-8886</td>
                     </tr>
                     
                     <tr>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>노원구</th>
                        <td>일자리경제과</td>
                        <td>330-1918</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th rowspan="2">도봉구</th>
                        <td rowspan="2">일자리경제과</td>
                        <td rowspan="2">2289-1577</td>
                        <td>현대종합동물병원</td>
                        <td>02-3491-2851</td>
                     </tr>
                     
                     <tr>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>동대문구</th>
                        <td>경제진흥과</td>
                        <td>2127-4272</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th rowspan="2">동작구</th>
                        <td rowspan="2">일자리경제과</td>
                        <td rowspan="2">820-1184</td>
                        <td>디아크동물병원</td>
                        <td>02-816-7582</td>
                     </tr>
                     
                     <tr>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>마포구</th>
                        <td>지역경제과</td>
                        <td>3153-8563</td>
                        <td>수의사회 마포구분회</td>
                        <td>02-3141-8274</td>
                     </tr>
                     
                     <tr>
                        <th>서대문구</th>
                        <td>일자리경제과</td>
                        <td>330-1918</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th rowspan="2">서초구</th>
                        <td rowspan="2">기업환경과</td>
                        <td rowspan="2">2155-6449</td>
                        <td>2014길고양이TNR</td>
                        <td>02-2155-6454</td>
                     </tr>
                     
                     <tr>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>성동구</th>
                        <td>지역경제과</td>
                        <td>920-3365</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>성북구</th>
                        <td>지역경제과</td>
                        <td>920-3365</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>송파구</th>
                        <td>경제진흥과</td>
                        <td>2147-2516</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>양천구</th>
                        <td>보건위생과</td>
                        <td>2620-4918</td>
                        <td>우신동물병원</td>
                        <td>02-2693-8883</td>
                     </tr>
                     
                     <tr>
                        <th rowspan="2">영등포구</th>
                        <td rowspan="2">지역경제과</td>
                        <td rowspan="2">22670-3419</td>
                        <td>대림동물병원</td>
                        <td>02-845-3368</td>
                     </tr>
                     
                     <tr>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>용산구</th>
                        <td>지역경제과</td>
                        <td>2199-6810</td>
                        <td>서울수의사용산구분회</td>
                        <td>02-778-7582</td>
                     </tr>
                     
                     <tr>
                        <th>은평구</th>
                        <td>생활경제과</td>
                        <td>351-6842</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th>종로구</th>
                        <td>산업환경과</td>
                        <td>2148-2423</td>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th rowspan="2">중구</th>
                        <td rowspan="2">지역경제과</td>
                        <td rowspan="2">3396-5073</td>
                        <td>미래지동물병원</td>
                        <td>02-2233-7574</td>
                     </tr>
                     
                     <tr>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                     
                     <tr>
                        <th rowspan="2">중랑구</th>
                        <td rowspan="2">지역경제과</td>
                        <td rowspan="2">2094-1286</td>
                        <td>강남25시동물병원</td>
                        <td>02-545-8575</td>
                     </tr>
                     
                     <tr>
                        <td>동물구조관리협회</td>
                        <td>031-867-9119</td>
                     </tr>
                  </tbody>
               </table>
            </div>
         </div>   
      </div> 
<br><br>
      <div class="g-my-50" id="content-4">
         <h3 class="g-font-size-24 g-color-gray-dark-v1 font-weight-bold">04.야생동물 구조 관련 단체 및 기관</h3>
         <br>
         <div class="table-responsive">
            <table class="table table-bordered text-center">
               <thead>
                  <tr>
                     <th>센터명</th>
                     <th>소재지</th>
                     <th>연락처</th>
                     <th>운영기관</th>
                     <th>비고</th>
                  </tr>
               </thead>
               
               <tbody>
                  <tr>
                     <th>서울</th>
                     <td>서울시 관악구 관악로 1</td>
                     <td>02-880-8659</td>
                     <td>
                        <a href="http://www.seoulwildlifecenter.or.kr/" target="_blank" class="aTag">
                           서울대학교 수의과대학
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>위탁</td>
                  </tr>
                  
                  <tr>
                     <th>부산</th>
                     <td>부산시 사하구 낙동남로 1240-2</td>
                     <td>051-209-2091</td>
                     <td>
                        <a href="http://www.busan.go.kr/wetland/wildanimalinfo01" target="_blank" class="aTag">
                           낙동강 에코센터
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>직영</td>
                  </tr>
                  
                  <tr>
                     <th>대전</th>
                     <td>대전시 유성구 궁동 대학로 99</td>
                     <td>042-821-7930</td>
                     <td>
                        <a href="http://dwrc.or.kr/" target="_blank" class="aTag">
                           충남대학교 수의과대학
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>위탁</td>
                  </tr>
                  
                  <tr>
                     <th>울산</th>
                     <td>울산시 남구 옥동 506-3</td>
                     <td>052-256-5322</td>
                     <td>
                        <a href="http://www.ulsanpark.com/institution/institution01_8.php" target="_blank" class="aTag">
                           울산대공원
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>직영</td>
                  </tr>
               
                  <tr>
                     <th>강원</th>
                     <td>강원도 춘천시 강원대학길 1</td>
                     <td>033-250-7504</td>
                     <td>
                        <a href="http://gvs.gg.go.kr/" target="_blank" class="aTag">
                           강원대학교 수의과대학
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>위탁</td>
                  </tr>
                  
                  <tr>
                     <th>충북</th>
                     <td>충북 청주시 청원구 양청4길 45</td>
                     <td>043-216-3328</td>
                     <td>
                        <a href="http://wildlife-center.kr/" target="_blank" class="aTag">
                           충북대학교 수의과대학
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>위탁</td>
                  </tr>
                  
                  <tr>
                     <th>충남</th>
                     <td>충남 예산군 예산읍 대학로 54 (공주대학교 산업과학대학 내)</td>
                     <td>041-334-1666</td>
                     <td>
                        <a href="https://cnwarc.modoo.at/" target="_blank" class="aTag">
                           공주대학교 산학협력단
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>위탁</td>
                  </tr>
                  
                  <tr>
                     <th>전북</th>
                     <td>전북 익산시 고봉로 79번지</td>
                     <td>063-850-0983</td>
                     <td>
                        <a href="http://wildlife.chonbuk.ac.kr/" target="_blank" class="aTag">
                           전북대학교 수의과대학
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>위탁</td>
                  </tr>
               
                  <tr>
                     <th>전남</th>
                     <td>전남 순천시 순천만길 922-15</td>
                     <td>061-749-4800</td>
                     <td>순천시 환경보호과학</td>
                     <td>직영</td>
                  </tr>
                  
                  <tr>
                     <th>경북</th>
                     <td>경북 안동시 도산면 퇴계로 2150-44</td>
                     <td>054-840-8250</td>
                     <td>
                        <a href="https://www.gb.go.kr/Main/open_contents/section/gbforest/index.html" target="_blank" class="aTag">
                           경상북도 산림자원개발원
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>직영</td>
                  </tr>
                  
                  <tr>
                     <th>경남</th>
                     <td>경남 진주시 진주대로 501(가좌동 900)</td>
                     <td>055-754-9575</td>
                     <td>
                        <a href="http://gamc.gnu.ac.kr/" target="_blank" class="aTag">
                           경상대학교 수의과대학
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>위탁</td>
                  </tr>
                  
                  <tr>
                     <th>제주</th>
                     <td>제주도 제주시 산천단남길 42</td>
                     <td>064-752-9982</td>
                     <td>제주대학교 수의과대학</td>
                     <td>위탁</td>
                  </tr>
               
                  <tr>
                     <th>인천</th>
                     <td>인천시 연수구 송도동 13-20 (솔찬공원내)</td>
                     <td>032-858-9702</td>
                     <td>
                        <a href="http://ecopia.incheon.go.kr/" target="_blank" class="aTag">
                           인천시 보건환경연구원
                           <i class="fa fa-external-link">
                           </i>
                        </a>
                     </td>
                     <td>직영</td>
                  </tr>
                  
                  <tr>
                     <th>광주</th>
                     <td>광주시 서구 유촌동 719-2일원</td>
                     <td>062-613-6651</td>
                     <td>광주시 보건환경연구원</td>
                     <td>직영</td>
                  </tr>
               </tbody>
            </table>
         </div>
      </div>


</div>
</section>





</body>
</html>