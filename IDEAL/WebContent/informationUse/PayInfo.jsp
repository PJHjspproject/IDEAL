<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
	margin:0; padding:0;
	width: 100%;
}
article{ 
	margin:0 auto;
	width: 600px;
}
#textarea {
	resize:none;
	overflow: auto;
	
}
#btnClose{
	height: 30px;
	background-color: gray;
}
</style>
</head>
<body>
	<article>
<h1>수수료 안내</h1>
	<textarea id="textarea" rows="40" cols="100" readonly="readonly" >
		
		Type A. 100% 달성 시 최종 모금액을 수령받는 경우 

1. 고정 수수료 : 7% (VAT별도) 

2. 변동 수수료 : 지지서명 수에 따라 수수료가 달라집니다. 




		Type B. 달성여부와 상관없이 모인만큼 수령 받는 경우
		 
					- 성공 시 : 10% (VAT별도) 
					- 실패 시 : 15% (VAT 별도)
					
		Type C. 80%이하 금액으로 종료			
					- 고정 수수료 절반 3.5%(VAT 별도)

* 위 수수료에 부가세와 PG수수료 (3.3%)가 추가로 부과 됩니다. 

* PG 수수료란?
   신용카드 결제 대행 서비스를 제공하고 수취하는 수수료를 의미합니다.
   와디즈는 나이스정보통신의 서비스를 이용하고 있습니다. 
		
	</textarea>
		<input id="btnClose" type="button" value="닫기" onclick="self.close()">
	</article>
</body>
</html>