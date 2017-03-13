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
		this.getServletContext().getRequestDispatcher( "/WEB-INF/connexionMembre.jsp" ).forward( request, response );
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
				System.out.println("Mot de passe correct");
				String getPrenom = new String("SELECT prenom_util FROM utilisateurs WHERE email_util = '"+email+"'");
				ResultSet rs2 = connect.executionRequete(getPrenom);
				rs2.next();
				String prenomUtilisateur = rs2.getString("prenom_util");
				HttpSession session = request.getSession();
				session.setAttribute(ATT_SESSION_USER, prenomUtilisateur);
			} else {
				System.out.println("Mauvaise combinaison email/mot de passe");
				erreurs.put(email, "Mauvaise combinaison email/mot de passe");
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
        this.getServletContext().getRequestDispatcher( ESPACE_MEMBRES ).forward( request, response );
		
		
	}

}
