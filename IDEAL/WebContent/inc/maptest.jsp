<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
      #map {
		/*  기본  맵 크기 */
        height: 700px;
        width: 100%;
       }
    </style>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCRmOmyEDwd8d8nQOXXQG67nPzftYit1Y&callback=initMap">
    </script>
</head>
<body>
<h3>업체 위치</h3>
    <div id="map"></div>
    <script>
      function initMap() {
        var itwill = {lat: 35.158506, lng: 129.062053};//위도와 경도 위치값
        //구글맵 을 'map(div)' 에 담음 
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 16,//확대값(줌인)
          center: itwill//중앙지점
        });
        //마커 생성
        var marker = new google.maps.Marker({
          position: itwill,//위치
          map: map,//맵 위에
          title:"헥사펀드",//마우스 오버시 "업체명" 뜸
          draggable: false,//마커 고정
          animation: google.maps.Animation.DROP//마커가 위에서 내려오는 애니메이션
        });
        /*marker.addListener('click',함수)
        를 통해 클릭시 함수 호출*/
        marker.addListener('click', toggleBounce);
       
        //마커가 점프하는 함수
        /*getAnimation() null 과 1을 리턴함
        null일 경우 애니메이션 없음
        1 일경우 계속 BOUNCE
        */
      function toggleBounce() {
    	  
    	  if (marker.getAnimation() != null) {
    	    marker.setAnimation(null);
    	  } else {
    	    marker.setAnimation(google.maps.Animation.BOUNCE);
    	  }
    	} //toggleBounce()end
      }//initmap()end
      
    </script>
    
</body>
</html>