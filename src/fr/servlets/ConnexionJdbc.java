package fr.servlets;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Exemple de requete sql qui va pouvoir etre traitée par la methode
 * public ResultSet executionRequete() de cette classe 
 * 
 * String REQUETE01 = "SELECT nom,prenom FROM utilisateurs ORDER BY age";
 * 
 * conn = connection();  // methode pour créer une nouvelle connexion
 * Statement st = conn.createStatement();
 * ResultSet rs = st.executeRequete(REQUETE01);
 * 
 * 
    while (rs.next()) {
       System.out.printf("%-20s | %-20s | %3d\n", //
             rs.getString(1), rs.getString("prenom"), rs.getInt(3));
    }
 * 
 */

public class ConnexionJdbc {
	
	// parametres connexion à la BD de Mysql
	
	//private String login = "root";
	private String mdp = "root";
	private String dburl = "localhost:8889/FrediDB";
	private String login = "root";
	//private String mdp = "ricola";
	//private String dburl = "localhost:3306/FrediDB"; //pour l'installation raspberry
	//private String dburl = "176.158.180.214:3306/FrediDB";
	
	// L'objet dbConnect de la classe Connection permet de se connecter à la DB
	private java.sql.Connection dbConnect = null;
	// L'objet Statement permet d'exécuter des instructions SQL
	// CREATE ;INSERT ;UPDATE ;SELECT ;DELETE.
	private java.sql.Statement dbStatement = null;
	
 	// constructeur de la classe
	public ConnexionJdbc() {

	}
	
 	// constructeur de la classe
	public ConnexionJdbc(String url, String user, String password) {
	        this.dburl = url;  // "//localhost:3306/fredi" 
	        this.login = user; // "admin"
	        this.mdp = password; // "root"
	}
	
	//méthode de connexion à la base fredi
	public Boolean connection() throws SQLException {
		
	     try {
	    	 
	    	// Pour se connecter à une base de données via JDBC, 
	    	// il faut tout d'abord charger le pilote JDBC qui fait le lien entre les deux.
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			/*
			 * forName declanche le chargement dynamique du pilote/Driver de la 
			 * base de données avec laquelle ou lesquees on veut travailler
			 * CE pilote doit être accessible à partir de la variable d'environnement CLASSPATH
			 * Depuis la version JDBC 4 le chargement explicite du pilote est inutile
			 */
			
			
			// Pour se connecter à une base de données, il faut instancier un objet de la classe Connection en lui 
			// précisant sous forme d'URL la base à laquelle accéder.
			this.dbConnect = DriverManager.getConnection("jdbc:mysql://"+ this.dburl, this.login, this.mdp);
			
			// L'objet Statement est fourni par l'objet Connection grâce à l'instruction this.dbConnect.createStatement()
			this.dbStatement = this.dbConnect.createStatement();	
			
			return true;			
		} 
	     
	     // 'API java.util.logging contient les classes pour gérer simplement des ...des fichiers journaux (fichiers logs) 
	     // dans lesquels les applications laissent une trace de leur exécution 
	     // Un logger est obtenu par un appel d'une des méthodes getLogger de la classe Logger.
	     catch (SQLException ex) {
	            Logger.getLogger(ConnexionJdbc.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	     catch (ClassNotFoundException ex) {
	            Logger.getLogger(ConnexionJdbc.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	     catch (InstantiationException ex) {
	            Logger.getLogger(ConnexionJdbc.class.getName()).log(Level.SEVERE, null, ex);
	        } 
	     catch (IllegalAccessException ex) {
	            Logger.getLogger(ConnexionJdbc.class.getName()).log(Level.SEVERE, null, ex);
	        }
	
	     return false;
	     
	} // fin de connection()
	
	// methode d'execution d'une requete SQL
	// l'argument "sql" contient le script de la requete
	public ResultSet executionRequete(String sql) {
		try {
			ResultSet rs = this.dbStatement.executeQuery(sql);
			return rs;
		} 		
		catch(SQLException ex) {
			Logger.getLogger(ConnexionJdbc.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	
	} // fin de executionRequete(String sql)
	
	public int executionUpdate(String sql) throws SQLException {
		try {
			int eu = this.dbStatement.executeUpdate(sql);
			return eu;
		} 		
		catch(SQLException ex) {
			Logger.getLogger(ConnexionJdbc.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}
	
	/*
	 * ResultSet permet de definir 4 types de connexion à la base de données
	 * 	Scroll-insensitive : vision figée du resultat de la requete au moment de l'evaluation
	 * 	Scroll-sensitive : Le ResultSet montre l'etat courant des données (modifiées/detruites)
	 * Reglages de mise à jour
	 * 	Read-only : pas de modification possible 
	 * 	Updatable : possibilité de modification 
	 * 
	 * Exemple : 
	 * 			Statement stmt = con.createStatement(
	 * 				ResultSet.TYPE_SCROLL_INSENSITIVE,
    				ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = stmt.executeQuery("SELECT * FROM Personne");
					
		Ce ResulSet est modifiable mais il ne refète pas les modification faites
		par d'autres transaction
	 */
	
	
	
	// fermer la connection au serveur 
    public void closeConnection() {
        try {
            this.dbStatement.close();
            this.dbConnect.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
} // fin de la classe ConnexionJdbc01
