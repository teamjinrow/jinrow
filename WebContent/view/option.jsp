<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<script src="resouce/js/jquery-1.12.4.min.js"></script>
<script>

	// 役職数を合計して、テキストを出力
	function sumRole(){
		var numVillager = Number(document.getElementById("countVillager").value);
		var numWerewolf = Number(document.getElementById("countWerewolf").value);
		document.getElementById("sumRole").innerText = "参加人数：" + (numVillager + numWerewolf);
	}
	
	// ＋ボタン押下時の処理
    function increment(element_id){
    	var element = document.getElementById(element_id);
    	var element_value = element.value;
    	if (element_value == 20) {
    		alert("これ以上増やせません。");
    		return
    	}
    	element_value ++;
    	element.setAttribute("value",element_value);
    	sumRole();
    		
    }
    
	// −ボタン押下時の処理
    function decrement(element_id){
    	var element = document.getElementById(element_id);
    	var element_value = element.value;
    	if (element_value == 0) {
    		alert("これ以上減らせません。");
    		return
    	}
    	element_value --;
    	element.setAttribute("value",element_value);
    	sumRole();
    		
    }
</script>
<meta charset=UTF-8>
<title>オプション画面</title>
</head>
<body>
	<div>
		<p id="sumRole">参加人数：<c:out value="${size_role}"/></p>
	</div>
	<form action="Option" method="POST">
		<p>村人：<input id="countVillager" name="sizeVillager" type="text" value="${size_villager}" />
		   <input type="button" onclick="increment('countVillager')" value="+"/>
		   <input type="button" onclick="decrement('countVillager')" value="-"/>
		</p>
		<p>人狼：<input id="countWerewolf" name="sizeWerewolf" type="text" value="${size_werewolf}" />
		   <input type="button" onclick="increment('countWerewolf')" value="+"/>
		   <input type="button" onclick="decrement('countWerewolf')" value="-"/>
		</p>
		<input type="submit" value="決定"/>				
	</form>
	<input type="button" onclick="location.href='index.html'" value="ログイン画面に戻る"/>
</body>
</html>