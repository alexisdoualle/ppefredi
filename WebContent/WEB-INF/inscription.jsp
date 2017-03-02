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
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	
	

      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.php"><span class="logo_colour">Maison des</span><span class="logo_colour2">Ligues</span></a></h1>
          <h2> </h2>
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li><a href="Accueil">Accueil</a></li>
          <li class="selected"><a href="ConnexionMembre">Espace Membre</a></li>
          <li><a href="ConnexionAdmin">Espace admin</a></li>
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
            <h3>Fonctionalités disponnibles</h3>
            <h5>Copyright 2016</h5>
            <p>A travers La maison des ligues vous pouvez :
			<li>Vous connecter a votre éspace membre</li>
			<li>Compléter et ajouter des cours au format texte dans la base de donnée du site</li>
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
   <form method="post" action="traitement">
   <fieldset><center>
     <legend>Inscription</legend>
     <br>
       <label for="nom">Nom :   <br><span class="requis"></span></label>
       <input type="text" name="nom" placeholder="ex: Dupont"/><br />

       <label for="prenom">Prénom :   <br><span class="requis"></span></label>
       <input type="text" name="prenom" placeholder="ex: Jean"/><br />
       
       <label for="adresse">Adresse :   <br><span class="requis"></span></label>
       <input type="text" name="adresse" placeholder="ex: 1 rue Machin"/><br />
       
       <label for="ville">Ville :   <br><span class="requis"></span></label>
       <input type="text" name="ville" placeholder="ex: Lyon" /><br />
       
       <label for="cp">CP :   <br><span class="requis"></span></label>
       <input type="text" name="cp" placeholder="ex: 23076" /><br />
      
       <label for="tel">Téléphone :   <br><span class="requis"></span></label>
       <input type="text" name="tel" placeholder="ex: 0123456789" /><br />
       
		<% 
			try {
		      	if(request.getParameter("erreur").length()>0) {
		      		out.println("<strong>Cette adresse mail est déjà utilisée.</strong><br>");
		      	}
			}
		catch(NullPointerException e){}
	
 		 %>
       <label for="email">Email :   <br><span class="requis"></span></label>
       <input type="text" name="email" placeholder="ex: jdupont@gmail.com" /><br />
       
       <label for="ddn">Date de naissance :   <br><span class="requis"></span></label>
       <input type="text" name="ddn" size="20" maxlength="20" placeholder="YYYY-MM-DD" /><br />
       
       <label for="mdp">Mot de passe :   <br><span class="requis"></span></label>
       <input type="password" name="mdp" placeholder="********" /><br />
       
       <label for="ligue">Ligue :   <br><span class="requis"></span></label>
       <input type="text" name="ligue" placeholder="ex: Foot" /><br />
       <br>

       <input type="submit" value="Valider l'inscription" class="sansLabel" />
       </center>
       <br />
    </fieldset>
  </form>
      
 </div>
      




</div>

		
</div>


   <div id="content_footer"></div>
    <div id="footer">
      <p><a href="">page de CONTACT</a></p>
      <p>Copyright by Maison des ligues</p>
    </div>
	

  </div>
</body>
</html>
