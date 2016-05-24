<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>ゲームメイン画面</title>
</head>
<body>
<h1><c:out value="${turn}"/>日目 
	<c:if test="${day_or_night == false}">　昼</c:if>
	<c:if test="${day_or_night == true}">　夜</c:if>
</h1>
<c:forEach var="player" items="${player_list}">
<form action="Main" method="post">
   <p>
   ユーザ名：<c:out value="${player.playerName}"/>
   ステータス：<c:out value="${player.deadFlag}"/>
   <c:if test="${player.deadFlag == false}">
	   <c:if test="${day_or_night == false}">
	   　　<input type="hidden" name="voted_player" value="${player.playerName}"/>
	   </c:if>
	   <c:if test="${day_or_night == true}">
	   　　<input type="hidden" name="target_player" value="${player.playerName}"/>
	   </c:if>
	   <input type="hidden" name="player_name" value="${player_name}"/>
	   <c:if test="${player.playerName != player_name}">
	   　　<c:if test="${day_or_night == false}">
	       　<input type="submit" value="この人物に投票する"/>
	   　　</c:if>
	      <c:if test="${day_or_night == true}">
	       　<input type="submit" value="アクション"/>
	   　　</c:if>
	   </c:if>
   </c:if>
   </p>
</form>
</c:forEach>
<c:out value="${message}"/>
<c:out value="${result}"/>
	
</body>
</html>