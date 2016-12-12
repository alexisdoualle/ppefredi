<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="traitementConn.jsp">
	<fieldset>
		<legend>Connexion</legend>
		
		<label>Email:</label>
		<input type="text" name="email" /><br />
					<% 
						try {
					      	if(request.getParameter("erreur").length()>0) {
					      		out.println("<br><strong>Mauvais email/mot de passe.</strong><br>");
					      	}
						}
						catch(NullPointerException e){}
						
	     			 %>
		
		<label>Mot de passe:</label>
		<input type="password" name="mdp" /> <br>
		
		<input type="submit" value="valider"/>
		
	</fieldset>
</form>

</body>
</html>