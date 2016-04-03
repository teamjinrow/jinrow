<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>役割画面</title>
</head>
<body>
	<p>ようこそ、<c:out value="${user_name}"/>さん</p>
	<p>あなたの役割は<c:out value="${role_name}"/>です。</p>
	<form action="Main" method="post">
		<input type="submit" value="ゲームスタート"/>
	</form>
</body>
</html>