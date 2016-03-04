<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" 
	import="java.io.*,java.util.*,java.text.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>
			JSP/サーブレット
		</title>
	</head>
	<body>
		<%
			BufferedWriter objBw = new BufferedWriter(new FileWriter(application.getRealPath("access.log"),true),10);
			
			StringBuffer objSb=new StringBuffer();
			objSb.append(request.getParameter("name"));
			objSb.append("\t");
			
			objBw.write(objSb.toString() + System.getProperty("line.separator"));
			
			objBw.close();
		%>
	</body>
</html>
