package fr.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class interfaceutilisateur
 */
public class ConnexionMembre extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/connexionMembre.jsp";
    public static final String ESPACE_MEMBRES = "/espace-membres/index.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    public static final String ATT_SESSION_USER = "prenomUtilisateur";
    public static final String ATT_SESSION_IDUSER = "idUtilisateur";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionMembre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pren = (String) session.getAttribute("prenomUtilisateur");
		if (pren == null) {
			this.getServletContext().getRequestDispatcher( "/WEB-INF/connexionMembre.jsp" ).forward( request, response );
		} else {
			this.getServletContext().getRequestDispatcher( "/espace-membres/index.jsp" ).forward( request, response );	
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();
		String email=request.getParameter("email");
		String mdp=request.getParameter("mdp");
		
		ConnexionJdbc connect = new ConnexionJdbc();
		//ConnexionJdbc connect = new ConnexionJdbc("localhost/fredi","root","");
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String verifierMail = new String("SELECT * FROM utilisateurs WHERE email_util = '"+email+"' AND mdp_util = '"+mdp+"'");
		ResultSet rs = connect.executionRequete(verifierMail);
		try {
			if(rs.next()){
				//sélectionne l'id et le nom et prénom de l'utilisateur à partir de l'adresse mail entrée (qui est unique)
				String getPrenom = new String("SELECT id_util, prenom_util, nom_util FROM utilisateurs WHERE email_util = '"+email+"'");
				ResultSet rs2 = connect.executionRequete(getPrenom);
				rs2.next();
				String idUtilisateur = rs2.getString("id_util");
				String prenomUtilisateur = rs2.getString("prenom_util");
				String nomUtilisateur = rs2.getString("nom_util");
				HttpSession session = request.getSession();
				session.setAttribute(ATT_SESSION_USER, prenomUtilisateur + " " + nomUtilisateur);
				session.setAttribute(ATT_SESSION_IDUSER, idUtilisateur);
				session.setMaxInactiveInterval(10);
				this.getServletContext().getRequestDispatcher( ESPACE_MEMBRES ).forward( request, response );
			} else {
				System.out.println("Mauvaise combinaison email/mot de passe");
				erreurs.put(email, "Mauvaise combinaison email/mot de passe");
		        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			erreurs.put(email, "Echec de connexion");
		}
		
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );
        
        connect.closeConnection();

        /* Transmission de la paire d'objets request/response à notre JSP */

		
		
	}

}
