<%@page import="net.board.db.BoardDao"%>
<%@page import="net.board.db.BoardDto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/board1.css" type="text/css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("UTF-8");
	String memberEmail = (String) session.getAttribute("memberEmail");
	String nickName = (String) session.getAttribute("nickName");
%>
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous">
	
</script>
<script type="text/javascript">
var BoardList = "";
var count = "";
var nowPage = "";
var startPage = "";
var endPage = "";
var pageBlock = "";
var pageCount = "";

	$(function() {
		setBack();	
		
		$('.check').on("click", function() {
			var $num = $(this).children(".num").text();
			if (memberEmail == 'null' || memberEmail == "") {
				$('a.login-window').trigger("click");
			} else {
				location.href = "content.bo?num=" + $num;
			}
		});
		
	});
	function setBack(){
		$(".tableAllchat").find(".check").each(function(i, d){
			if(i==0){
				d.style.backgroundImage = "url('board/boardBack/ba1.jpg')";
			}else if(i==1){
				d.style.backgroundImage = "url('board/boardBack/ba4.jpg')";
			}else if(i==2){
				d.style.backgroundImage = "url('board/boardBack/ba3.jpg')";
			}else if(i==3){
				d.style.backgroundImage = "url('board/boardBack/ba2.jpg')";
			}else if(i==4){
				d.style.backgroundImage = "url('board/boardBack/ba5.jpg')";
			}else if(i==5){
				d.style.backgroundImage = "url('board/boardBack/ba6.jpg')";
			}
		});
	}
	function paging(num){
		$.ajax({
			type : 'POST',
	        url : 'board/getBoardList.jsp',
	        data : {pageNum:num},
	        dataType : 'json',
	        success : function(data) {
	        	BoardList = data.BoardList;
	        	count = data.count;
	        	nowPage = data.nowPage;
	        	startPage = data.startPage;
	        	endPage = data.endPage;
	        	pageBlock = data.pageBlock;
	        	pageCount = data.pageCount;
// 	        	alert(
// 	        		"BoardList:"+BoardList.toString()+
// 	        		"\ncount:"+count+
// 	        		"\nnowPage:"+nowPage+
// 	        		"\nstartPage:"+startPage+
// 	        		"\nendPage:"+endPage+
// 	        		"\npageBlock:"+pageBlock+
// 	        		"\npageCount:"+pageCount
// 	        	);
// 				alert(BoardList.length);
// 				var date = new Date(BoardList[0].date);
// 				alert(date);
				var blist = "";
				if(BoardList !=null || BoardList.length!= 0){
					blist = blist
						+'<h2 class="chath2">자유게시판</h2>'
			        	+'<div class="tableAllchat">'
			        	;
					for(var i = 0;i<BoardList.length;i++){
						var date = new Date(BoardList[i].date);
						var y = date.getFullYear();
						var m = date.getMonth()+1;
						if(m<10) m = "0"+m;
						var d = date.getDate();
						if(d<10) d = "0"+d;
						var setdate = y+"-"+m+"-"+d;
						blist = blist
				        	+'<div class="check" style="width: 350px; height: 300px; border: 1px solid #444; display: inline-block; margin-top: 25px; margin-right: 32px;">'
				        	+'<p class="num">'+BoardList[i].num+'</p>'
				        	+'<br />'
				        	+'<h1>'+BoardList[i].title+'</h1>'
				        	+'<b class="chh1po">'+BoardList[i].nickName+'</b>'
				        	+'<p class="chppo">'
				        	+setdate
				        	+'</p>'
				        	+'</div>'
							;
					}
					
					blist = blist
					+'</div>'
					+'<div class="pagenums">'
		        	+'<div class="pagenums1">'
		        	;
		        	if(startPage > pageBlock){
		        		blist = blist
		        		+'<a href="#" onclick="paging('+(pageBlock-startPage)+')">[이전]</a>'
		        		;
		        	}
					for(var s = startPage;s<=endPage;s++){
						if(s==num){
							blist = blist
							+'<b onclick="paging('+s+')" class="sack">'+s+'&nbsp;&nbsp;&nbsp;&nbsp;</b>'
							;
						}else{
							blist = blist
							+'<b onclick="paging('+s+')">'+s+'&nbsp;&nbsp;&nbsp;&nbsp;</b>'
							;	
						}
					}
					if(endPage < pageCount){
						blist = blist
						+'<a href="#" onclick="paging('+(startPage+pageBlock)+')">[다음]</a>'
						;
					}
					blist = blist
					+'</div>'
					;
					if(memberEmail!='null'||memberEmail!=''){
						blist = blist +'<input class="writebt1" type="button" value="글쓰기" onclick="location.href=\'write.bo\'">';
					}
					blist = blist
					+'</div>'
					;
				}else{
					blist = 
						'<h2 class="chath2">자유게시판</h2>'
						+'<div class="tableAllchat">'
						+'<div><p>글이 없습니다.</p></div>'
						+'</div>'
						;
				}
				
	        	var $div = $(".wrapBo");
	        	$div.html(blist);
				setBack();
				
				$('.check').on("click", function() {
					var $num = $(this).children(".num").text();
					if (memberEmail == 'null' || memberEmail == "") {
						$('a.login-window').trigger("click");
					} else {
						location.href = "content.bo?num=" + $num;
					}
				});
	        },
	        error : function(xhr, status, error){
	        	alert(error);
	        }
		});
	}
// 	paging();


</script>


</head>
<body>
	<div class="wrapBo">
		<!-- 게시판 -->
		<h2 class="chath2">자유게시판</h2>
		<div class="tableAllchat">
			<!-- 		<tr class="boardtrno1"> -->
			<!-- 			<th class="noth0">글번호</th> -->
			<!-- 			<th width="570px" class="noth1">글제목</th> -->
			<!-- 			<th class="noth0">글작성자</th> -->
			<!-- 			<th class="noth0">글올린날짜</th> -->
			<!-- 		</tr> -->
			<c:if test="${BoardList.isEmpty() }">
				<div>
					<p>글이 없습니다.</p>
				</div>
			</c:if>
			<c:if test="${!BoardList.isEmpty() }">
				<c:forEach var="a" items="${BoardList }">
					<!-- 해당하는 게시물의 Num값을 get방식으로 넘겨준다.  -->
					<div class="check"
						style="width: 350px; height: 300px; border: 1px solid #e0e0e0; display: inline-block; margin-top: 25px; margin-right: 32px;">
						<p class="num">${a.num }</p>
						<br />
						<h1>${a.title }</h1>
						<b class="chh1po">${a.nickName }</b>
						<p class="chppo">
							<f:formatDate value="${a.date }" pattern="yyyy-MM-dd" />
						</p>
					</div>

				</c:forEach>
			</c:if>
		</div>

		<div class="pagenums">
			<div class="pagenums1">
				<c:if test="${startPage > pageBlock }">
					<a href="boardList.bo?pageNum=${startPage-pageBlock }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1">
					<c:if test="${nowPage == i }"><%--
 						<a href="boardList.bo?pageNum=${i }" class="sack"><b>${i }&nbsp;&nbsp;&nbsp;&nbsp;</b></a> 
					--%><b onclick="paging(${i })" class="sack">${i }&nbsp;&nbsp;&nbsp;&nbsp;</b>
					</c:if><!-- 
				 --><c:if test="${nowPage != i }"><%--
 						<a href="boardList.bo?pageNum=${i }">${i }&nbsp;&nbsp;&nbsp;&nbsp;</a> 
					--%><b onclick="paging(${i })">${i }&nbsp;&nbsp;&nbsp;&nbsp;</b>
					</c:if>
				</c:forEach>
				<c:if test="${endPage < pageCount }">
					<a href="boardList.bo?pageNum=${startPage+pageBlock }">[다음]</a>
				</c:if>
			</div>
			<!-- 	<div class="pagenums2"> -->
			<c:if test="${memberEmail != null }">
				<input class="writebt1" type="button" value="글쓰기"
					onclick="location.href='write.bo'">
			</c:if>
			<!-- 	</div> -->
		</div>


	</div>
	<div style="clear: both;"></div>
</body>
</head>


<%-- 
<body>
<%
	request.setCharacterEncoding("utf-8");

	
%>
<div class="wrap">
<!-- 게시판 -->
<h1>Notice</h1>
	<table>	
		<tr>
			<th class="tno">글번호</th>
			<th class="title">글제목</th>
			<th class="twrite">글작성자</th>
			<th class="tdate">글올린날짜</th>
		</tr>
		<c:if test="${BoardList.isEmpty() }">
			<tr>
				<td>글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!BoardList.isEmpty() }">
			<c:forEach var="a" items="${BoardList }">
	
	
	<!-- 해당하는 게시물의 Num값을 get방식으로 넘겨준다.  -->
			<tr onclick="location.href='content.bo?num=${a.num}'">
				<td>${a.num }</td>
				<td>${a.title }</td>
				<td>${a.nickName }</td>
				<td><f:formatDate value="${a.date }" pattern="yyyy-MM-dd"/></td>
			</c:forEach>
		</c:if>
		
	<!-- <form action="write.bo" method="post">
	
		<input type="submit" value="글쓰기">
	</form> -->
	
	
	<input type="button" value="글쓰기" onclick="location.href='write.bo'">
	</table>
</div>
</body> --%>
</html>