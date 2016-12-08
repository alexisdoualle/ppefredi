<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Validation</title>
</head>
<body>
<h1>Bienvenue</h1>
<p>Bienvenue, 
<%
String prenom = (String)session.getAttribute("prenom");
out.println(prenom);
%>. Vous êtes maintenant inscrit.</p>
</body>
</html>