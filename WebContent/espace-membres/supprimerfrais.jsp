<%@ page pageEncoding="UTF-8" %>
<%@ page import="fr.beans.*" %>
<%@ page import="java.util.ArrayList;
import java.util.List;" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>
  <title>Maison des ligues</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" type="text/css" href="style/style.css" />
</head>

<body>
  <div id="main">

  <div id="header">

      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index"><span class="logo_colour">Maison des</span><span class="logo_colour2">Ligues</span></a></h1>
          <h2> </h2>
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li><a href="espacemembre">Espace Perso</a></li>
          <li><a href="frais">Frais</a></li>
          <li class="selected"><a href="#">Supprimer frais</a></li>
        </ul>
      </div>
    </div>
  </div>

    <div id="content_header"></div>
    <div id="site_content">
		<div id="sidebar_container">
        <div class="sidebar">
          <div class="sidebar_top"></div>
          <div class="sidebar_item">
            <!-- insert your sidebar items here -->
            <h3>Bonjour, ${sessionScope.prenomUtilisateur}</h3>
            <p>Pour supprimer un frais, sélectionnez le dans la liste déroulante, puis cliquez sur "Supprimer"</p>
            <h5>Espace personnel</h5>
            <p>Se <a href="deconnexion">déconnecter</a></p>
          </div>
          <div class="sidebar_base"></div>
        </div>

        <div class="sidebar">
          <div class="sidebar_top"></div>
          <div class="sidebar_item">
            <h3>Bulle d'aide</h3>
            <form method="post" action="#" id="search_form">
              <p>
                <input class="search" type="text" name="search_field" value="Mots Clés" />
                <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="style/search.png" alt="Search" title="Search" />
              </p>
            </form>
          </div>
          <div class="sidebar_base"></div>
        </div>
      </div>
      <div id="content">
        	<h2>Supprimer un frais</h2>


			<% String selectedId = (String) request.getAttribute("selectedId"); %>
			<form name="suppr" method="post" action="#">
				<select name="listeSuppr">
				    <c:forEach var="item" items="${listeFrais}">
				        <option value="${item.id}" ${item.id == selectedId ? 'selected="selected"' : ''}>${item.trajet}, ${item.date} </option>
				    </c:forEach>
				</select>
				<input type="submit" value="Supprimer"/>
			</form>

			<table>
			<tr>

			<th>date</th>
			<th>motif</th>
			<th>trajet</th>
			<th>kms</th>
			<th>coup</th>
			<th>peage</th>
			<th>repas</th>
			<th>hotel</th>
			</tr>
        	<%
			  List<FraisUnique> liste = (List<FraisUnique>) request.getAttribute("listeFrais");
			    for(FraisUnique frais: liste){
			       out.println(
			    		   "</td><td> " + frais.getDate() +
			    		   "</td><td> " + frais.getMotif() +
			    		   "</td><td> " + frais.getTrajet() +
			    		   "</td><td> " + frais.getKms() +
			    		   "</td><td> " + frais.getCout() + "€" +
			    		   "</td><td> " + frais.getPeage() + "€" +
			    		   "</td><td> " + frais.getRepas() + "€" +
			    		   "</td><td> " + frais.getHebergement() + "€" +
			    		   "</td></tr>");
			     }

			%>
			</table>

			

	        <p>Utilisateur: ${sessionScope.prenomUtilisateur}. Se <a href="deconnexion">déconnecter</a></p>
	</div>
</div>

   <div id="content_footer"></div>
    <div id="footer">
      <p><a href="">page de CONTACT</a></p>
      <p>Maison des ligues</p>
    </div>


  </div>
</body>
</html>
