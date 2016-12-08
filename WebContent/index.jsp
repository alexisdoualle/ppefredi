<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.*" %>
 <%@ page import="fr.servlets.ConnexionJdbc" %>
 <%@ page import="javax.sql.*" %>
 <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css2">
<%@ include file="style.css" %>
</style>
<title>M2L</title>
</head>
<body>

<div class="header"> 
<h2>Bienvue sur le site Maison des Ligues</h2>
</div>

<div class="corps">
	<div class="cellule1"><p><a href="connexion.jsp">Connexion</a></p></div>
	<div class="cellule2"><p><a href="inscription.jsp">Inscription</a></p></div>
	<div class="cellule3"><p><a href="#">Administrateur</a></p></div>
	<% 
		ConnexionJdbc connect = new ConnexionJdbc("localhost:3307/frediDB","root","root");
	 	connect.connection();
	 	String reqSQL = new String("Insert into `Utilisateur` (`Nom_ut`, `Prenom_ut`) Values('Doualle', 'Alexis')");
	 	String reqSQL2 = new String("Select * from `Utilisateur`");
	 	ResultSet rs = connect.executionRequete(reqSQL2);
	 	int eu = connect.executionUpdate(reqSQL);
	 	
	 
	   // while (rs.next()) {
	   //     System.out.printf("%s %s, %s, %s, %s\n", //
	   //           rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(5),rs.getString(6));
	   //  }
	 	

	 %>
</div>


</body>
</html>