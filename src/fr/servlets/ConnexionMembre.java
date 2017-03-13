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

/**
 * Servlet implementation class interfaceutilisateur
 */
public class ConnexionMembre extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/connexionMembre.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
       
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
		
		ConnexionJdbc connect = new ConnexionJdbc("localhost:8889/FrediDB","root","root");
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
			} else {
				System.out.println("Mauvaise combinaison email/mot de passe");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

        /* Transmission de la paire d'objets request/response à notre JSP */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        
		connect.closeConnection();
		
		
	}

}
