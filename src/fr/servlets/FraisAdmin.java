package fr.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.beans.FraisUnique;
import fr.beans.Utilisateur;

/**
 * Servlet implementation class FraisAdmin
 */
public class FraisAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FraisAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public List<Utilisateur> getUtilisateurs() {
		ConnexionJdbc connect = new ConnexionJdbc();
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//crée une liste d'utilisateurs
	    List<Utilisateur> list=new ArrayList<Utilisateur>();
	    //requète SQL 
		String utilSQL = new String("SELECT * FROM utilisateurs");
		ResultSet rs = connect.executionRequete(utilSQL);
	      try {
	    	 //Pour chaque utilisateur obtenu de la BDD, crée un objet Utilisateur avec les setters, et le rajoute à la liste
			while (rs.next()) {
			     Utilisateur util =new Utilisateur();
			     util.setId(rs.getString("id_util"));
			     util.setNom(rs.getString("nom_util"));
			     util.setPrenom(rs.getString("prenom_util"));
			     util.setEmail(rs.getString("email_util"));
			     util.setMotDePasse(rs.getString("mdp_util"));
			     list.add(util);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    connect.closeConnection();
	    return list;
	  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    /* Récupération de la session depuis la requête */
	    HttpSession session = request.getSession();
	    //crée un liste de Utilisateur en appelant la fonction getUtilisateurs()
		List<Utilisateur> list = getUtilisateurs();
		
		request.setAttribute("listeUtil", list);
		
		this.getServletContext().getRequestDispatcher( "/espace-admin/fraisadmin.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
