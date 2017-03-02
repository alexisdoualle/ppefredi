 <%@ page import="java.util.*" %>
 <%@ page import="fr.servlets.ConnexionJdbc" %>
 <%@ page import="javax.sql.*" %>
 <%@ page import="java.sql.ResultSet" %>
 <%@ page import="javax.swing.JOptionPane" %>

<%
	String email=request.getParameter("email");
	String mdp=request.getParameter("mdp");

	session.setAttribute("email", email);
	session.setAttribute("mdp", mdp);
	
	ConnexionJdbc connect = new ConnexionJdbc("localhost/fredi","root","");
	connect.connection();
	
	String verifierMail = new String("SELECT * FROM utilisateur WHERE email_ut = '"+email+"' AND mdp_ut ='"+mdp+"'");
	ResultSet rs = connect.executionRequete(verifierMail);
	if(!rs.next()){
		%>
		<jsp:forward page='connexion.jsp' >
		<jsp:param name='erreur' value='ERREUR' />
		</jsp:forward>
		<%
	}
	connect.closeConnection();
%>
<jsp:forward page='validationConn' />