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

import fr.beans.FraisUnique;

/**
 * Servlet implementation class SupprimerFrais
 */
public class SupprimerFrais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerFrais() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public List<FraisUnique> getFrais() {
		ConnexionJdbc connect = new ConnexionJdbc();
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String Query = "select * from ABC";
	    List<FraisUnique> list=new ArrayList();
		String frais = new String("SELECT * FROM frais");
		ResultSet rs = connect.executionRequete(frais);
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
		List<FraisUnique> list = getFrais();
		request.setAttribute("listeFrais", list);
		request.setAttribute("selectedId", "");
		this.getServletContext().getRequestDispatcher( "/espace-membres/supprimerfrais.jsp" ).forward( request, response );
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fraisASupprimer=request.getParameter("listeSuppr");

		ConnexionJdbc connect = new ConnexionJdbc("localhost:8889/FrediDB","root","root");
		//ConnexionJdbc connect = new ConnexionJdbc("localhost/fredi","root","");
		try {
			connect.connection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = new String("DELETE FROM `frais` WHERE `id_frais`=" +fraisASupprimer);
		
		try {
			int eu = connect.executionUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		doGet(request, response);
		
	}

}
