<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>役割画面</title>
</head>
<body>
	<p>ようこそ<%= request.getAttribute("user_name")%>さん</p>
	<p>あなたの役割は<%= request.getAttribute("role_name") %>です。</p>
	<form action="Main" method="post">
	    <input type="hidden" name="user_name" value="<%= request.getAttribute("user_name")%>">
		<input type="submit" value="ゲームスタート"/>
	</form>
</body>
</html>