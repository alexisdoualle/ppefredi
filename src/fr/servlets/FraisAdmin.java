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
    
  //fonction pour obtenir les frais d'un utilisateur (idUtil) depuis la BDD
  	public List<FraisUnique> getFrais(String idUtil) {
  		ConnexionJdbc connect = new ConnexionJdbc();
  		try {
  			connect.connection();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		//crée une liste de FraisUnique
  	    List<FraisUnique> list=new ArrayList<FraisUnique>();
  	    //requète SQL pour obtenir tous les frais correspondants à l'utilisateur de la session (idUtil)
  		String fraisSQL = new String("SELECT * FROM frais WHERE `id_util` = "+ idUtil);
  		ResultSet rs = connect.executionRequete(fraisSQL);
  	      try {
  	    	 //Pour chaque frais obtenu de la BDD, crée un FraisUnique avec les setters, et le rajoute à la liste
  			while (rs.next()) {
  			     FraisUnique fraisU =new FraisUnique();
  			     fraisU.setId(rs.getString("id_frais"));
  			     fraisU.setDate(rs.getString("date_frais"));
  			     fraisU.setMotif(rs.getString("motif_frais"));
  			     fraisU.setTrajet(rs.getString("trajet_frais"));
  			     fraisU.setKms(rs.getString("kms_frais"));
  			     fraisU.setCout(rs.getString("cout_frais"));
  			     fraisU.setPeage(rs.getString("peage_frais"));
  			     fraisU.setRepas(rs.getString("repas_frais"));
  			     fraisU.setHebergement(rs.getString("hebergement_frais"));
  			     list.add(fraisU);
  			  }
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  	    connect.closeConnection();
  	    return list;
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
		/* Récupération de la session depuis la requête */
	    HttpSession session = request.getSession();
		//le paramètre "" correspond à l'option sélectionnée par l'utilisateur dans le select
		String utilisateurSelectione=request.getParameter("listeUtil");

		ConnexionJdbc connect = new ConnexionJdbc();
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = new String("SELECT * FROM utilisateurs WHERE id_util = " +utilisateurSelectione);
		
		ResultSet rs = connect.executionRequete(sql);
		try {
			rs.next();
			String idUtil = rs.getString("id_util");
			request.setAttribute("idUtilSelectionne",idUtil);
			session.setAttribute("idUtilSelectionne", idUtil);
			List<FraisUnique> list = getFrais(idUtil);
			request.setAttribute("listeFrais", list);
			session.setAttribute("listeFrais", list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connect.executionRequete(sql); 
		
		doGet(request, response);
	}

}
