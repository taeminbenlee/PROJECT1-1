<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<!-- 카카오 맵스 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=92928f9e601c2b81c37ec8882d4901ac&libraries=services"></script>
<link rel="stylesheet" href="./css/mainPage.css">


<style>
/* 이건 무조건 이자리*/
.ad_s {
	 background: url("./images/qqq.png") no-repeat center; 
	 background-size: 100%;
	 height: 1000px;
	 padding: 380px 0px;	
}
</style>
<meta content=charset=utf-8>
<title>견생역전</title>
</head>

<body>
<!-- 메인 이미지 -->
<div id="slider">
<figure>
<img src="./images/main.png" alt="">
<img src="./images/main_2.png" alt="">
<img src="./images/main3.png" alt="">
<img src="./images/main4.png" alt="">
<img src="./images/main.png" alt="">
</figure>
</div>
<br><br>



<!-- count -->
<table align="center" class="countname countText">
<col width="600"><col width="600"><col width="600">
<col width="600"><col width="600"><col width="600"><col width="600"><col width="600">
	<tr></tr>
	<tr align="center">
		<th></th>
		<th class="memberCountCon1" style="color: #000; " ></th>
		<th></th>
		<th class="memberCountCon2" style="color: #000"></th>
		<th></th>
		<th class="memberCountCon3" style="color: #000" ></th>
		<th></th>
	</tr>
</table>

<table align="center"  class="countname"  style="color: #000;font-size: 20px;">
<col width="600" ><col width="600"><col width="600">
<col width="600"><col width="600"><col width="600"><col width="600"><col width="600">
	<tr></tr>
	<tr align="center">
		<th></th>
			<th >Rescued animal</th>
		<th></th>
			<th > Today adopted animal</th>
		<th></th>
		<th >Total adopted animal</th>
		<th></th>
	</tr>
</table>
<br><br>


	<!--입양시스템 -->
	<div class="ad_s"><img alt="" src="" ></div>


	<!-- INFO -->
	<div id='info'></div>
	<iframe src="info.jsp" border="0" framespacing="0" marginheight="0" 
		marginwidth="0" scrolling="no"  vspace="0" style="width: 100%; " ></iframe>


	<!-- 유용한 정보 -->
	<br><br>
	<div id=useful></div>
	<iframe src="useful.jsp" border="0" framespacing="0" marginheight="0" 
		marginwidth="0" scrolling="no"  vspace="0" style="width: 100%; " ></iframe>
	<br><br><br><br><br><br><br><br><br>
	   
	   
<!-- 동물병원 검색 -->   
  <div>
      <div style="text-align: center;">
         <h2 style='font-weight:530'>우리동네 동물병원</h2>
      </div>
      <div style="text-align: center;">
         <hr width = "280px" color = "#DEE2E6" > 
      </div>     
  </div>
  
  <form class = 'form-inline' onsubmit="searchHospital(); return false;" style="margin-top: 5px; margin-left:77%; " >   
       <input type="text" id="keyword" class="form-control" placeholder="우리 동네">
       <button class='btn' type="submit">검색</button>  
  </form>
  <br>
   <div align='center'>
	  <div id="map" style="width:80%;height:450px; border-radius:20px"> </div>
   </div>
<br><br><br><br><br><br><br><br><br>    
	    

<script>

////////////////////// Count 관련 함수 //////////////////////

var memberCountConTxt= 21;

$({ val : 0 }).animate({ val : memberCountConTxt }, {
 duration: 2000,
step: function() {
  var num = numberWithCommas(Math.floor(this.val));
  $(".memberCountCon1").text(num);
},
complete: function() {
  var num = numberWithCommas(Math.floor(this.val));
  $(".memberCountCon1").text(num);
}
});

function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


var memberCountConTxt= 80;

$({ val : 0 }).animate({ val : memberCountConTxt }, {
 duration: 2000,
step: function() {
  var num = numberWithCommas(Math.floor(this.val));
  $(".memberCountCon2").text(num);
},
complete: function() {
  var num = numberWithCommas(Math.floor(this.val));
  $(".memberCountCon2").text(num);
}
});

function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}




var memberCountConTxt= 170;

$({ val : 0 }).animate({ val : memberCountConTxt }, {
 duration: 2000,
step: function() {
  var num = numberWithCommas(Math.floor(this.val));
  $(".memberCountCon3").text(num);
},
complete: function() {
  var num = numberWithCommas(Math.floor(this.val));
  $(".memberCountCon3").text(num);
}
});

function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
</script>




<script>
////////////////////// 카카오 맵스  ////////////////////////

//마커를 클릭하면 장소명을 표출할 인포윈도우
var infowindow = new kakao.maps.InfoWindow({zIndex:1});
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.503131786164694, 127.02430725714143), // 지도의 중심좌표
        level: 2 // 지도의 확대 레벨
    };  
    

//지도 생성
var map = new kakao.maps.Map(mapContainer, mapOption); 

//장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();  

//검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

//키워드로 장소를 검색
searchPlaces();


//장소검색 값을 넣어주는 함수
function searchHospital() {

    var keyword = document.getElementById('keyword').value;
    if (keyword == null || keyword == "") {
		Swal.fire({
			  icon: 'error',
			  title: '키워드를 입력해주세요!',
			})
        return false;
    }
    keyword = keyword + " 동물병원";
    ps.keywordSearch( keyword, placesSearchCB); 
}


// 키워드 검색 완료 시 호출되는 콜백함수
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
        var bounds = new kakao.maps.LatLngBounds();

        for (var i=0; i<data.length; i++) {
            displayMarker(data[i]);    
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }       
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정
        map.setBounds(bounds);
    } 
}


// 지도에 마커를 표시하는 함수
function displayMarker(place) {    
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x) 
    });
    // 마커에 클릭이벤트를 등록
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
        infowindow.open(map, marker);
    });
}

</script>



</body>
</html>
