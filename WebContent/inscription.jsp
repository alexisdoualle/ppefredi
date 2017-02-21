<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Insert title here</title>
</head>
<body>

<%
String nom = (String)session.getAttribute("nom");
if (nom==null) nom="";
String prenom = (String)session.getAttribute("prenom");
if (prenom==null) prenom="";
String adresse = (String)session.getAttribute("adresse");
if (adresse==null) adresse="";
String ville = (String)session.getAttribute("ville");
if (ville==null) ville="";
%>

<div>
   <form method="post" action="traitement.jsp">
   <fieldset>
     <legend>Inscription</legend>
       <label for="nom">Nom : <span class="requis"></span></label>
       <input type="text" name="nom" placeholder="ex: Dupont"/><br />

       <label for="prenom">Prénom : <span class="requis"></span></label>
       <input type="text" name="prenom" placeholder="ex: Jean"/><br />
       
       <label for="adresse">Adresse : <span class="requis"></span></label>
       <input type="text" name="adresse" placeholder="ex: 1 rue Machin"/><br />
       
       <label for="ville">Ville : <span class="requis"></span></label>
       <input type="text" name="ville" placeholder="ex: Lyon" /><br />
       
       <label for="cp">CP : <span class="requis"></span></label>
       <input type="text" name="cp" placeholder="ex: 23076" /><br />
      
       <label for="tel">Téléphone : <span class="requis"></span></label>
       <input type="text" name="tel" placeholder="ex: 0123456789" /><br />
       
		<% 
			try {
		      	if(request.getParameter("erreur").length()>0) {
		      		out.println("<strong>Cette adresse mail est déjà utilisée.</strong><br>");
		      	}
			}
		catch(NullPointerException e){}
	
 		 %>
       <label for="email">Email : <span class="requis"></span></label>
       <input type="text" name="email" placeholder="ex: jdupont@gmail.com" /><br />
       
       <label for="ddn">Date de naissance : <span class="requis"></span></label>
       <input type="text" name="ddn" size="20" maxlength="20" placeholder="YYYY-MM-DD" /><br />
       
       <label for="mdp">Mot de passe : <span class="requis"></span></label>
       <input type="password" name="mdp" placeholder="********" /><br />
       
       <label for="ligue">Ligue : <span class="requis"></span></label>
       <input type="text" name="ligue" placeholder="ex: Foot" /><br />
       

       <input type="submit" value="Valider l'inscription" class="sansLabel" />
       <br />
    </fieldset>
  </form>
      
 </div>
      

</body>
</html>