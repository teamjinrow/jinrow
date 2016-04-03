<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>ゲームメイン画面</title>
</head>
<body>
	ユーザ名：<%=request.getAttribute("user_name") %>さん
	役職：<%=request.getAttribute("role_name") %>
	状態：<%=request.getAttribute("status") %>
</body>
</html>