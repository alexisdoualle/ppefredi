<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<div class="cellule1">

            <form method="post" action="traitement.jsp">
            <fieldset>
              <legend>Inscription</legend>
                <label for="nom">Nom : <span class="requis"></span></label>
                <input type="text" name="nom" size="20" maxlength="60" /><br />

                <label for="prenom">Prénom : <span class="requis"></span></label>
                <input type="text" name="prenom" size="20" maxlength="20" /><br />
                
                <label for="adresse">Adresse : <span class="requis"></span></label>
                <input type="text" name="adresse" size="20" maxlength="20" /><br />
                
                <label for="ville">Ville : <span class="requis"></span></label>
                <input type="text" name="ville" size="20" maxlength="20" /><br />
                
                <label for="cp">CP : <span class="requis"></span></label>
                <input type="text" name="cp" size="20" maxlength="20" /><br />
               
                <label for="tel">Téléphone : <span class="requis"></span></label>
                <input type="text" name="tel" size="20" maxlength="20" /><br />
                
				<% 
					try {
				      	if(request.getParameter("erreur").length()>0) {
				      		out.println("<br><strong>Cette adresse mail est déjà utilisée.</strong><br>");
				      	}
					}
				catch(NullPointerException e){}
					
     			 %>
                <label for="email">Email : <span class="requis"></span></label>
                <input type="text" name="email" size="20" maxlength="20" /><br />
                
                <label for="ddn">Date de naissance : <span class="requis"></span></label>
                <input type="text" name="ddn" size="20" maxlength="20" /><br />
                
                <label for="mdp">Mot de passe : <span class="requis"></span></label>
                <input type="password" name="mdp" size="20" maxlength="20" /><br />
                
                <label for="ligue">Ligue : <span class="requis"></span></label>
                <input type="text" name="ligue" size="20" maxlength="20" /><br />
                

                <input type="submit" value="Valider l'inscription" class="sansLabel" />
                <br />
            </fieldset>
        </form>
      
      </div>
      

</body>
</html>