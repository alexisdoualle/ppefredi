package fr.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.beans.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Frais
 */
public class Frais extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Frais() {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    /* Récupération de la session depuis la requête */
	    HttpSession session = request.getSession();
	    //obtient l'idUtil depuis la session
	    String idUtil = (String) session.getAttribute("idUtilisateur");
	    //crée un liste de FraisUnique en appelant la fonction getFrais()
		List<FraisUnique> list = getFrais(idUtil);
		
		request.setAttribute("listeFrais", list);
		request.setAttribute("selectedId", "");
		this.getServletContext().getRequestDispatcher( "/espace-membres/frais.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Le doPost() crée un nouveau frais et le rajoute à la BDD
		
	    HttpSession session = request.getSession();
	    String idUtil = (String) session.getAttribute("idUtilisateur");
		
		String newDate=request.getParameter("newDate");
		String newMotif=request.getParameter("newMotif");
		String newTrajet=request.getParameter("newTrajet");
		String newKilometrage=request.getParameter("newKilometrage");
		String newCoup=request.getParameter("newCoup");
		String newPeage=request.getParameter("newPeage");
		String newRepas=request.getParameter("newRepas");
		String newHebergement=request.getParameter("newHebergement");

		ConnexionJdbc connect = new ConnexionJdbc();
		//ConnexionJdbc connect = new ConnexionJdbc("localhost:8889/FrediDB","root","root");
		//ConnexionJdbc connect = new ConnexionJdbc("localhost/fredi","root","");
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = new String("INSERT INTO `frais`(`date_frais`, `motif_frais`, `trajet_frais`, `kms_frais`, `cout_frais`, `peage_frais`, `repas_frais`, `hebergement_frais`,`id_util`) "
				   						  + "VALUES ('"+newDate+"', '"+newMotif+"', '"+newTrajet+"', '"+newKilometrage+"', '"+newCoup+"', '"+newPeage+"', '"+newRepas+"', '"+newHebergement+ "',"+ idUtil +")");
		try {
			connect.executionUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
		
	}
}
