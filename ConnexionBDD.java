package diaw_projet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
	   public static void main(String[] args) {
	      Connection conn = null;
	      try {
	         String url = "jdbc:mysql://localhost:3306/sofamous";
	         String user = "root";
	         String password = "";
	         conn = DriverManager.getConnection(url, user, password);
	         System.out.println("Connexion établie avec succès !");
	      } catch (SQLException e) {
	         System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
	      } finally {
	         try {
	            if (conn != null) {
	               conn.close();
	               System.out.println("Connexion fermée avec succès !");
	            }
	         } catch (SQLException ex) {
	            System.out.println("Erreur lors de la fermeture de la connexion : " + ex.getMessage());
	         }
	      }
	   }
}

