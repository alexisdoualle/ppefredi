<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>

  <head>
    <title>Maison des ligues</title>
    <meta name="description" content="website description"/>
    <meta name="keywords" content="website keywords, website keywords"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="style/style.css"/>
  </head>

  <body>
    <div id="main">

      <div id="header">
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <div id="logo">
          <div id="logo_text">
            <!-- class="logo_colour", allows you to change the colour of the text -->
            <h1>
              <a href="index.html">
                <span class="logo_colour">Maison des</span>
                <span class="logo_colour2">Ligues</span>
              </a>
            </h1>
            <h2></h2>
          </div>
        </div>
        <div id="menubar">
          <ul id="menu">
            <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
            <li>
              <a href="index.html">Accueil</a>
            </li>
            <li class="selected">
              <a href="ConnexionMembre">Espace Membre</a>
            </li>
            <li>
              <a href="ConnexionAdmin">Espace Trésorier</a>
            </li>
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
            <!--  -->
            <h3>Fonctionalités disponnibles</h3>
            <ul>
              <li>
                <a href="ConnexionMembre">Se connecter</a>
              </li>
              <li>
                <a href="inscription">S'inscrire</a>
              </li>
            </ul>
          </div>
          <div class="sidebar_base"></div>
        </div>


      </div>
      <form method="post" action="ConnexionMembre">
        <fieldset>
          <center>
            <legend>Connexion a votre interface adhérent</legend>
            <br>

              <label>Email:
                <br></label>
                <input type="text" name="email"/><br/>
				
                <label>Mot de passe:
                  <br></label>
                  <input type="password" name="mdp"/>
                  <br>
                   <span class="erreur">${erreurs['email']}</span>
                    <br>

                      <input type="submit" value="valider"/>
                      <br></fieldset>

                    </form>
                  </center>
                  <p>
                    <center>
                      Vous n'avez pas de compte ? Merci de bien vouloir cliquer
                      <a href="inscription">ICI</a>
                    </center>

                  </div>

                  <div id="content_footer"></div>
                  <div id="footer">
                    <p>
                      <a href="">page de CONTACT</a>
                    </p>
                    <p>Copyright by Maison des ligues</p>
                  </div>

                </div>
              </body>
            </html>
