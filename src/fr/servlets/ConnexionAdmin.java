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
public class ConnexionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String ESPACE_ADMIN = "/espace-admin/index.jsp";
    public static final String CHAMP_EMAIL  = "email";
    public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
    public static final String ATT_SESSION_ADMIN = "prenomAdmin";
    public static final String ATT_SESSION_IDADMIN = "idAdmin";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pren = (String) session.getAttribute("prenomAdmin");
		if (pren == null) {
			this.getServletContext().getRequestDispatcher( "/WEB-INF/connexionAdmin.jsp" ).forward( request, response );
		} else {
			this.getServletContext().getRequestDispatcher( "/espace-admin/index.jsp" ).forward( request, response );	
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
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String verifierMail = new String("SELECT * FROM administrateurs WHERE email_admin  = '"+email+"' AND mdp_admin = '"+mdp+"'");
		ResultSet rs = connect.executionRequete(verifierMail);
		try {
			if(rs.next()){
				//sélectionne l'id et le nom et prénom de l'utilisateur à partir de l'adresse mail entrée (qui est unique)
				String getPrenom = new String("SELECT id_admin, prenom_admin, nom_admin FROM administrateurs WHERE email_admin = '"+email+"'");
				ResultSet rs2 = connect.executionRequete(getPrenom);
				rs2.next();
				String idAdmin = rs2.getString("id_admin");
				String prenomAdmin = rs2.getString("prenom_admin");
				String nomAdmin = rs2.getString("nom_admin");
				HttpSession session = request.getSession();
				session.setAttribute(ATT_SESSION_ADMIN, prenomAdmin + " " + nomAdmin);
				session.setAttribute(ATT_SESSION_IDADMIN, idAdmin);
				//session.setMaxInactiveInterval(10);
				this.getServletContext().getRequestDispatcher( ESPACE_ADMIN ).forward( request, response );
			} else {
				System.out.println("Mauvaise combinaison email/mot de passe");
				erreurs.put("email", "Mauvaise combinaison email/mot de passe");
		        if ( erreurs.isEmpty() ) {
		            resultat = "Succès de la connexion.";
		        } else {
		            resultat = "Échec de la connexion.";
		        }
		        request.setAttribute( ATT_ERREURS, erreurs );
		        request.setAttribute( ATT_RESULTAT, resultat );

		        this.getServletContext().getRequestDispatcher( "/WEB-INF/connexionAdmin.jsp" ).forward( request, response );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			erreurs.put(email, "Echec de connexion");
		}
		
        connect.closeConnection();

	}

}
