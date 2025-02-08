<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<link rel="stylesheet" href="/insert.css">
</head>
<body>
	<h1>게시글 등록</h1>
	<hr>
	<form action="/insert" method="post" style="display:inline-block;">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left"><input type="text" class="boardTitle" name="title" /></td>
			</tr>
			<c:if test="${not empty sessionScope.username}">
				<td bgcolor="orange">작성자</td>
				<td align="left">${sessionScope.username}</td>
			</c:if>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left"><textarea class="reply-form-textarea" name="content" cols="40" rows="10"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="게시글 등록" /></td>
			</tr>
		</table>	
	</form>
	<hr>
	<button type="button" onclick="location.href='/boardList';" class="boardList">게시글 목록</button>
</body>
</html>