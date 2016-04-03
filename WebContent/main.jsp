<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>ゲームメイン画面</title>
</head>
<body>
<c:forEach var="user_map" items="${status_map}">
   <p>ユーザ名：<c:out value="${user_map.key}"/>さん</p>
   <c:forEach var="user_status" items="${user_map.value}">
	   <p>ステータス：<c:out value="${user_status.value}"/></p>
   </c:forEach>
</c:forEach>
	
</body>
</html>