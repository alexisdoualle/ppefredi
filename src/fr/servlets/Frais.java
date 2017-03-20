package fr.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
	private static final int max = 5;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Frais() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public List<FraisUnique> getFrais(String idUtil) {
		ConnexionJdbc connect = new ConnexionJdbc();
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    List<FraisUnique> list=new ArrayList();
		String fraisSQL = new String("SELECT * FROM frais WHERE `id_util` = "+ idUtil);
		ResultSet rs = connect.executionRequete(fraisSQL);
	      try {
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
	    String idUtil = (String) session.getAttribute("idUtilisateur");
		List<FraisUnique> list = getFrais(idUtil);
		request.setAttribute("listeFrais", list);
		request.setAttribute("selectedId", "");
		this.getServletContext().getRequestDispatcher( "/espace-membres/frais.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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

		ConnexionJdbc connect = new ConnexionJdbc("localhost:8889/FrediDB","root","root");
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
			int eu = connect.executionUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
		
	}
}
