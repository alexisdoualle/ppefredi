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
 * Servlet implementation class inscription
 */
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/inscription.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher( "/WEB-INF/inscription.jsp" ).forward( request, response );
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();

		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String adresse=request.getParameter("adresse");
		String ville=request.getParameter("ville");
		String cp=request.getParameter("cp");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
		String ddn=request.getParameter("ddn");
		String mdp=request.getParameter("mdp");
		
		
		ConnexionJdbc connect = new ConnexionJdbc("localhost:8889/FrediDB","root","root");
		//ConnexionJdbc connect = new ConnexionJdbc("localhost/fredi","root","");
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String verifierMail = new String("SELECT * FROM utilisateurs WHERE email_util = '"+email+"'");
		ResultSet rs = connect.executionRequete(verifierMail);
		try {
			if(rs.next()){
	            erreurs.put( CHAMP_EMAIL, "Ce mail existe Déjà");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_EMAIL, e.getMessage() );
        }
		
		String sql = new String("INSERT INTO `utilisateurs` (`nom_util`, `prenom_util`, `adresse_util`, `ville_util`, `cp_util`, `tel_util`, `ddn_util`, `email_util`, `mdp_util`) VALUES ('"+nom+"', '"+prenom+"', '"+adresse+"', '"+ville+"', '"+cp+"', '"+tel+"', '"+ddn+"', '"+email+"', '"+mdp+"')");


		
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
    		try {
    			connect.executionUpdate(sql);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
                erreurs.put( CHAMP_EMAIL, e.getMessage() );
    		}
            resultat = "Succès de l'inscription.";
        } else {
            resultat = "Échec de l'inscription.";
        }

        /* Stockage du résultat et des messages d'erreur dans l'objet request */
        request.setAttribute( ATT_ERREURS, erreurs );
        request.setAttribute( ATT_RESULTAT, resultat );

        /* Transmission de la paire d'objets request/response à notre JSP */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
        
		connect.closeConnection();
    
	}


	/**
	 * Valide l'adresse mail saisie.
	 */
	private void validationEmail( String email ) throws Exception {
	    if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}



}
