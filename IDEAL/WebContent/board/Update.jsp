<%@page import="net.board.action.BoardContent"%>
<%@page import="net.board.db.BoardDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.board.action.BoardAddPost"%>
<%@page import="net.board.db.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.wrap{
		padding-top: 100px;
	}
	img#fileimg{
		width:150px;
		height:150px;
	}
	#updateFile{
		display:none;
	}
</style>
<script type="text/javascript">
function check(){

	var size = document.getElementById("file").files[0].size;
	var file = document.getElementById("file");
	var filevalue = document.getElementById("file").value;//acb.png
	var name = filevalue.slice(filevalue.indexOf(".") +1).toLowerCase(); //파일 확장자를 잘라내고, 비교를 위해 소문자로 만듬
	if(name != "jpg" && name != "png" &&  name != "gif"){
		alert("jpg,gif,png만 지원합니다.");
		document.getElementById('fileck').innerHTML='jpg,gif,png만 지원합니다.';
		file.value ="";
	}else if(size>10*1024*1024){
		alert("파일이 10MB가 넘습니다");
		document.getElementById('fileck').innerHTML='파일이 10MB가 넘습니다.';
		file.value = "";
	}else{
		document.getElementById('fileck').innerHTML='';
	}
}
$(function(){
	if($("#originalFineName").val()==null || $("#originalFineName").val()==""){//originalFileName에 값이 있을 경우 -> 즉, 디비에 업로드된 파일이 있을 경우
		$("#updateFile").css("display", "inline");//업로드할 태그부분을 보이게 처리
	}
	
	$("#deleteFileChk").on("change", function(){//삭제할 checkbox의 상태 체크
		alert($("#fileimg").attr("src"));
		if($("#deleteFileChk").is(":checked")){//만약 삭제할 checkbox가 체크되었다면
// 			alert("체크됨");
			var src = $("#fileimg").attr("src");//파일삭제에 이용하기 위해 <img>태그의 src값 가져오기
// 			alert(src);
			$("#deleteFileName").val(src);//지울파일의 이름값으로 src값을 넘겨주기
			$("#updateFile").css("display", "inline");//삭제할 파일을 선택하였다면 새로 추가할 수 있게 input태그 보이게 하기
// 			alert("deleteFileName:"+$("#deleteFileName").val());
		}else{//삭제하지 않겠다고 checkbox의 체크를 해제할 경우
// 			alert("해제됨");
			$("#updateFile").css("display", "none");//다시 추가입력할 파일부분을 보이지 않게 처리
			$("#deleteFileName").val("");//삭제할 파일이름값을 제거
		}
	})
})	
	
</script>
<%
	String memberEmail = (String)request.getAttribute("memberEmail");
	String nickName = (String)request.getAttribute("nickName");
%>
</head>
<body>

<%
	request.setCharacterEncoding("utf-8");
	
%>

<form action="updatePro.bo" enctype="multipart/form-data"  method="post">

	<table>
		<tr>
			<td>글번호</td>
			<td><input type="text" name="num" value="${bdto.num }" readonly="readonly"></td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td><input type="text" name="nickName" readonly="readonly" value="${nickName}"></td>
		</tr>
		<tr>
			<td>글제목</td>
			<td><input type="text" name="title" value="${bdto.title }"></td>
		</tr>
		<tr>
			<td>파일</td>
				<td>
					<c:if test="${bdto.file!=null }">
						<label>삭제<input type="checkbox" id="deleteFileChk" name="deleteFileChk"><img id="fileimg" src="image/${bdto.nickName }/${bdto.file }"></label><br>
					</c:if>
					<div id="updateFile"><input type="file" name="file" id="file" onchange="check()"><span id="fileck"></span></div>
				</td>
		</tr>
		<tr>
			<td>글내용</td>
			<td colspan="3"><input type="text" name="content" id="content" value="${bdto.content }"></td>
		</tr>
		<tr>
			<td><input type="submit" value="수정하기"></td>
			<td><input type="button" value="목록보기" onclick="location.href='boardList.bo'"></td>
			<td><input type="reset" value="다시입력"></td>
		</tr>
	</table>
</form>

</body>
</html>