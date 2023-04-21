package diaw_projet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestionCategorie {
	public static void ajouterCategorie(String nomCategorie) {
	    Connection conn = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        conn = Main.getConnexion();
	        String query = "INSERT INTO categories(nom_cat) VALUES (?)";
	        preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setString(1, nomCategorie);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void modifierCategorie(String ancienNomCategorie, String nouveauNomCategorie) {
	    Connection conn =  Main.getConnexion();
	    PreparedStatement ps = null;
	    try {
	        ps = conn.prepareStatement("UPDATE categories SET nom_cat=? WHERE nom_cat=?");
	        ps.setString(1, nouveauNomCategorie);
	        ps.setString(2, ancienNomCategorie);
	        int rowsUpdated = ps.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("La catégorie a été modifiée avec succès.");
	        } else {
	            System.out.println("Aucune catégorie n'a été modifiée.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Erreur lors de la modification de la catégorie : " + e.getMessage());
	    }
	}

	public static void supprimerCategorie(String nomCategorieASupprimer) {
	    Connection conn = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        conn = Main.getConnexion();
	        String query = "DELETE FROM categories WHERE nom_cat = ?";
	        preparedStatement = conn.prepareStatement(query);
	        preparedStatement.setString(1, nomCategorieASupprimer);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
