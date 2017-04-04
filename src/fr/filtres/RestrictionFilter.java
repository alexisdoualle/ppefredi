package fr.filtres;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilter implements Filter {
    public static final String ACCES_PUBLIC     = "/index.html";
    public static final String ATT_SESSION_USER = "prenomUtilisateur";
    public static final String ATT_SESSION_ADMIN = "prenomAdmin";

    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
  
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        //laisse passer l'utilisateur sur les pages des Connexion, d'inscription, ainsi que la feuille de style
        String chemin = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin.startsWith( "/Conn" ) || chemin.startsWith( "/insc" ) || chemin.startsWith( "/sty" ) ) {
            chain.doFilter( request, response );
            return;
        }

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur ET administateur n'existent pas dans la session en cours, alors
         * personne n'est connecté.
         */
        if ( session.getAttribute( ATT_SESSION_USER ) == null && session.getAttribute( ATT_SESSION_ADMIN ) == null ) {
            //redirection vers accueil
        	request.getRequestDispatcher( ACCES_PUBLIC ).forward( request, response );
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );
        }
    }

    public void destroy() {
    }
}