<%@ page pageEncoding="UTF-8" %>
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
          <li><a href="ConnexionMembre">Espace Membre</a></li>
          <li class="selected"><a href="ConnexionAdmin">Espace admin</a></li>
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
      <form method="post" action="traitementConn.jsp">
	<fieldset>
		<center><legend>Connexion a votre interface Administrateur</legend>
		<br>
		
		<label>Email: <br></label>
		<input type="text" name="email" /><br />
					<% 
						try {
					      	if(request.getParameter("erreur").length()>0) {
					      		out.println("<br><strong>Mauvais email/mot de passe.</strong><br>");
					      	}
						}
						catch(NullPointerException e){}
						
	     			 %>
		
		<label>Mot de passe: <br></label>
		<input type="password" name="mdp" /> <br>
		<br>
		
		<input type="submit" value="valider"/>
		<br>
		<br>
	</fieldset>
</form>
		</center>

</div>


   <div id="content_footer"></div>
    <div id="footer">
      <p><a href="">page de CONTACT</a></p>
      <p>Copyright by Maison des ligues</p>
    </div>
	

  </div>
</body>
</html>
