<%@page import="java.util.ArrayList"%>
<%@page import="net.Admin.db.AdminDao"%>
<%@page import="net.investRequest.db.InvestRequestDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript" ></script>
<script type="text/javascript">

$(function(){
	$(".table1").each(function(){
		$(".table1").hide();
	});
    $(".ViewMoreBtn").click(function(event){
    	event.preventDefault();
    	/*테이블 선택자 (다음 테이블을 선택하여 토글 작업)*/
		var $table = $(this).parent().parent().parent().parent().next();
		$table.toggle();
       
    });
});
		
		
	

</script>
<style type="text/css">
h2{
	text-align: center;
}
img{
	max-width: 600px;
}
#needChk{
	width: 48%;
	background-color: #aaa;
	display: inline-block;
}
#doneChk{
	width: 48%;
	background-color: #aaa;
	display: inline-block;
}
.table{
	display: block;
}
</style>
</head>
<body>
<input type="button" value="메인으로" onclick="location.href='AdminMain.ad'">
<h1>투자요청 현황 심사페이지입니다.</h1>
<div id="needChk">
<c:if test="${InvestRequestList.isEmpty() }">
	<h2 >요청현황이 없습니다.</h2>
</c:if>

<c:if test="${!InvestRequestList.isEmpty() }">

	<h2 >요청 현황이 있네요!!</h2>
			<c:forEach var="ir" items="${InvestRequestList }">
				<table border="1">
					<tr>
						<td>투자요청번호</td>
						<td>${ir.investRequestNum }<button class="ViewMoreBtn">더보기</button></td>
					</tr>
				</table>
				<table border="1" class="table1">
					<tr>
						<td>이름</td>
						<td>${ir.DName }</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td>${ir.memberEmail }</td>
					</tr>
					<tr>
						<td>범주</td>
						<td>${ir.category}</td>
					</tr>
					<tr>
						<td>간략소개</td>
						<td>${ir.introduce }</td>
					</tr>
					<tr>
						<td>프로그램</td>
						<td>${ir.program }</td>
					</tr>
					<tr>
						<td>썸네일 이미지</td>
						<td><img src="../image/${ir.memberEmail }/${ir.thumbImage }"></td>
					</tr>
					<tr>
						<td>메인썸네일</td>
						<td>
						<c:set var ="mainThumb" value="${fn:split(ir.mainThumb,'$')}"/>
							<c:forEach var="mt" items="${mainThumb }">
								<img src="../image/${ir.memberEmail }/${mt }">
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>목표금액</td>
						<td>${ir.successMoney }</td>
					</tr>
					<tr>
						<td>모집시작일</td>
						<td>${ir.startDay }</td>
					</tr>
					<tr>
						<td>모집 마감일</td>
						<td>${ir.endDay }</td>
					</tr>
					<tr>
						<td>교부일</td>
						<td>${ir.payDay }</td>
					</tr>

					<tr>
						<td>진행상황</td>
						<td>${ir.fundSituation }</td>
					</tr>
					<tr>
						<td>해쉬태그</td>
						<td>${ir.hashTag }</td>
					</tr>
					<tr>
						<td>메인사진</td>
						<td>
							<c:set var ="mainImgs" value="${fn:split(ir.mainImage,'$')}"/>
						
							<c:forEach var="mi" items="${mainImgs }">
								<img src="../image/${ir.memberEmail }/${mi }">
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>메인글</td>
						<td>${ir.mainText }</td>
					</tr>
					<tr>
						<td>현재금액</td>
						<td>${ir.nowMoney }</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td><!-- 업로드된 파일명을 $로 잘라서 하나씩 뿌림 -->
							<c:set var ="files" value="${fn:split(ir.file,'$')}"/>
							<c:forEach var="file" items="${files }">
							<c:if test="${file ne 'null' }">
								<a href="../image/${ir.memberEmail }/${file }">
									${file }
									<img src="../image/${ir.memberEmail }/${file }">
								</a>
							</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>재등록허용</td>
						<td class="upchk">
						<!-- 값이 false일때 붉은색, true 일때 파란색 -->
							<c:if test="${ir.updateChk==false }">
								<span style="color:red;">${ir.updateChk }</span>
							</c:if>
							<c:if test="${ir.updateChk==true }">
								<span style="color:blue;">${ir.updateChk }</span>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>수락</td>
						<td class="perchk">
						<!-- 값이 false일때 붉은색, true 일때 요청 완료로 넘어가기 때문에 비움 -->
							<c:if test="${ir.permitChk==false }">
								<span style ="color:red;">${ir.permitChk}</span>			
							</c:if>
							
						</td>
					</tr>
					<tr>
						<td>
							<form action="AdminPermitChk.ad" method="post">
								<input type="submit" value="요청수락"> <input type="hidden"
									name="investRequestNum" value="${ir.investRequestNum }">
							</form>
						</td>

						<td>
							<form action="AdminUpdateChk.ad" method="post">
								<input type="submit" value="재심사"> <input type="hidden"
									name="investRequestNum" value="${ir.investRequestNum }">

							</form>
						</td>
					</tr>
				</table>

			</c:forEach>


		</c:if>
</div>

<!--  ***************************************  요청완료된 항목 뿌려주기2화면 오른쪽으로 넘김  ******************** -->
<div id="doneChk">
<c:if test="${InvestRequestList2.isEmpty() }">
	<h2 >요청완료된 현황이 없습니다.</h2>
</c:if>
<c:if test="${!InvestRequestList2.isEmpty() }">

	<h2 align="right">요청완료된 현황</h2>
	<c:forEach var="ir2" items="${InvestRequestList2 }">
	<table border="1">
			<tr>
				<td>투자요청번호</td>
				<td>${ir2.investRequestNum }<button class="ViewMoreBtn">더보기</button></td>
			</tr>
	</table>
	<table  border="1"  class="table1">
	
		<tr>
			<td>범주</td>
			<td>${ir2.category}</td>
		</tr>
		
		<tr>
			<td>프로그램</td>
			<td>${ir2.program }</td>
		</tr>
		<tr>
			<td>썸네일 이미지</td>
			<td><img src="../image/${ir.memberEmail }/${ir.thumbImage }"></td>
		</tr>
		
		<tr>
			<td>목표금액</td>
			<td>${ir2.successMoney }</td>
		</tr>
		<tr>
			<td>모집시작일</td>
			<td>${ir2.startDay }</td>
		</tr>
		<tr>
			<td>모집 마감일</td>
			<td>${ir2.endDay }</td>
		</tr>
		<tr>
			<td>교부일</td>
			<td>${ir2.payDay }</td>
		</tr>
		
		<tr>
			<td>진행상황</td>
			<td>${ir2.fundSituation }</td>
		</tr>
		
		<tr>
			<td>현재금액</td>
			<td>${ir2.nowMoney }</td>
		</tr>
		
		</table>
		
			</c:forEach>
		
	
</c:if>
</div>
</body>
</html>

<%-- <%@page import="java.util.ArrayList"%>
<%@page import="net.Admin.db.AdminDao"%>
<%@page import="net.investRequest.db.InvestRequestDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" value="메인으로" onclick="location.href='AdminMain.ad'">
<h1>투자요청 현황 심사페이지입니다.</h1>
<c:if test="${InvestRequestList.isEmpty() }">
	<h2 align="center">요청현황이 없습니다.</h2>
</c:if>
<c:if test="${!InvestRequestList.isEmpty() }">

	<h2>요청 현황이 있네요!!</h2>
	<table align="center">
		<tr>
		</tr>
		<c:forEach var="ir" items="${InvestRequestList }">
		<tr>
			<td>${ir.memberEmail }</td>
		</tr>
			</c:forEach>
		
	</table>
</c:if>
</body>
</html> --%>