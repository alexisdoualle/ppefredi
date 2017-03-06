 <%@ page import="java.util.*" %>
 <%@ page import="fr.servlets.ConnexionJdbc" %>
 <%@ page import="javax.sql.*" %>
 <%@ page import="java.sql.ResultSet" %>
 <%@ page import="javax.swing.JOptionPane" %>

<%
	
	String nom=request.getParameter("nom");
	String prenom=request.getParameter("prenom");
	String adresse=request.getParameter("adresse");
	String ville=request.getParameter("ville");
	String cp=request.getParameter("cp");
	String tel=request.getParameter("tel");
	String email=request.getParameter("email");
	String ddn=request.getParameter("ddn");
	String mdp=request.getParameter("mdp");
	String ligue=request.getParameter("ligue");
	
	session.setAttribute("nom", nom);
	session.setAttribute("prenom", prenom);
	session.setAttribute("adresse", adresse);
	session.setAttribute("ville", ville);
	session.setAttribute("cp", cp);
	session.setAttribute("tel", tel);
	session.setAttribute("email", email);
	session.setAttribute("ddn", ddn);
	session.setAttribute("mdp", mdp);
	session.setAttribute("ligue", ligue);
	
	ConnexionJdbc connect = new ConnexionJdbc("localhost:8889/FrediDB","root","root");
	//ConnexionJdbc connect = new ConnexionJdbc("localhost/fredi","root","");
	connect.connection();
	
	String verifierMail = new String("SELECT * FROM utilisateur WHERE email_ut = '"+email+"'");
	ResultSet rs = connect.executionRequete(verifierMail);
	if(rs.next()){
		
		// JOptionPane.showConfirmDialog(null, "SALUT");
		%>
			<jsp:forward page='inscription.jsp' >
			<jsp:param name='erreur' value='ERREUR' />
			</jsp:forward>
		<%
		
	}
	
	String sql = new String("INSERT INTO `Utilisateur` (`Nom_ut`, `Prenom_ut`, `Adresse_ut`, `Ville_ut`, `CP_ut`, `Tel_ut`, `Ddn_ut`, `Ligue_ut`, `Email_ut`, `Mdp_ut`) VALUES ('"+nom+"', '"+prenom+"', '"+adresse+"', '"+ville+"', '"+cp+"', '"+tel+"', '"+ddn+"', '"+ligue+"', '"+email+"', '"+mdp+"')");
	System.out.printf(rs.next()+"\n");
	int eu = connect.executionUpdate(sql);
	connect.closeConnection();
%>
<jsp:forward page='inscription.jsp' />
<jsp:forward page='validation.jsp'/>