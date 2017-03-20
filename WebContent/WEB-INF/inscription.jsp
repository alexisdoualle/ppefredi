<%@ page pageEncoding="UTF-8" %>
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
                            <a href="index">Accueil</a>
                        </li>
                        <li class="selected">
                            <a href="ConnexionMembre">Espace Membre</a>
                        </li>
                        <li>
                            <a href="ConnexionAdmin">Espace admin</a>
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
                        <!-- insert your sidebar items here -->
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

                    <div class="sidebar">
                        <div class="sidebar_top"></div>
                        <div class="sidebar_item">
                            <h3>Bulle d'aide</h3>
                            <form method="post" action="#" id="search_form">
                                <p>
                                    <input class="search" type="text" name="search_field" value="Mots Clés"/>
                                    <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="style/search.png" alt="Search" title="Search"/>
                                </p>
                            </form>
                        </div>
                        <div class="sidebar_base"></div>
                    </div>
                </div>
                <div id="content">

                    <% String nom = (String)session.getAttribute("nom"); if (nom==null) nom=""; String prenom = (String)session.getAttribute("prenom"); if (prenom==null) prenom=""; String adresse = (String)session.getAttribute("adresse"); if (adresse==null) adresse="";
                    String ville = (String)session.getAttribute("ville"); if (ville==null) ville=""; %>

                    <div>
                        <form method="post" action="inscription">
                            <fieldset>
                                <center>
                                    <legend>Inscription</legend>
                                    <br>
                                        <label for="nom">Nom :
                                            <br>
                                                <span class="requis"></span>
                                            </label>
                                            <input type="text" name="nom" value="Dupont"/><br/>

                                            <label for="prenom">Prénom :
                                                <br>
                                                    <span class="requis"></span>
                                                </label>
                                                <input type="text" name="prenom" value="Jean"/><br/>

                                                <label for="adresse">Adresse :
                                                    <br>
                                                        <span class="requis"></span>
                                                    </label>
                                                    <input type="text" name="adresse" value="1 rue Machin"/><br/>

                                                    <label for="ville">Ville :
                                                        <br>
                                                            <span class="requis"></span>
                                                        </label>
                                                        <input type="text" name="ville" value="Lyon"/><br/>

                                                        <label for="cp">CP :
                                                            <br>
                                                                <span class="requis"></span>
                                                            </label>
                                                            <input type="text" name="cp" value="23076"/><br/>

                                                            <label for="tel">Téléphone :
                                                                <br>
                                                                    <span class="requis"></span>
                                                                </label>
                                                                <input type="text" name="tel" value="0123456789"/><br/>

                                                                <label for="ddn">Date de naissance :
                                                                    <br>
                                                                        <span class="requis"></span>
                                                                    </label>
                                                                    <input type="text" name="ddn" size="20" maxlength="20" value="1999-01-01"/><br/>

                                                                    <%
			try {
		      	if(request.getParameter("erreur").length()>0) {
		      		out.println("<strong>Cette adresse mail est déjà utilisée.</strong><br>");
		      	}
			}
		catch(NullPointerException e){}

 		 %>
                                                                    <label for="email">Email :
                                                                        <br>
                                                                            <span class="requis"></span>
                                                                        </label>
                                                                        <input type="text" name="email" value="jdupont@gmail.com"/><br/>
                                                                        <span class="erreur" style="color:red">${erreurs['email']}</span>
                                                                        <br>

                                                                            <label for="mdp">Mot de passe :
                                                                                <br>
                                                                                    <span class="requis"></span>
                                                                                </label>
                                                                                <input type="password" name="mdp" placeholder="********"/><br/>

                                                                                <input type="submit" value="Valider l'inscription" class="sansLabel"/>
                                                                            </center>
                                                                            <br/>
                                                                            <p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
                                                                        </fieldset>
                                                                    </form>

                                                                </div>

                                                            </div>

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
