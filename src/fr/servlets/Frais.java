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
	    
		this.getServletContext().getRequestDispatcher( "/espace-membres/frais.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String nom=request.getParameter("nom");
		String prenom=request.getParameter("prenom");
		String adresse=request.getParameter("adresse");
		String ville=request.getParameter("ville");
		String cp=request.getParameter("cp");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
		String ddn=request.getParameter("ddn");
		String mdp=request.getParameter("mdp");
		
		
	}
}
