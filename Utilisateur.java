package diaw_projet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Utilisateur {
	private int idUser;
	private String nomUser;
	private String password;
	private String nom;
	private String prenom;
	private Depense [] depenses;
	
	public Utilisateur() {};
	
	public Utilisateur(int idUser, String nomUser, String password, String nom,  String prenom, Depense[] depenses) {
		this.idUser = idUser;
		this.nomUser = nomUser;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.depenses = depenses;
	}
	

	public  int getIdUser() {return idUser;}
	public String getNomUser() {return nomUser;}
	public String getPassword() {return password;}
	public String getNom() {return nom;}
	public String getPrenom() {return prenom;}
	public Depense [] getDepenses() {
		return depenses;
	}
	
	public void setId(int idUser) {this.idUser = idUser ;}
	public void setNomUser(String nomUser) {this.nomUser = nomUser;}
	public void setPassword(String password) {this.password = password;}
	public void setNom(String nom) {this.nom = nom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public void setDepenses(Depense [] depenses) {this.depenses = depenses;}

	public static void ajouterUtilisateur(String nom, String prenom, String nomUtilisateur, String password) {
	    try {
	        Connection connexion = Main.getConnexion();
	        String requete = "INSERT INTO Utilisateurs(nom, prenom, nom_utilisateur, password) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = connexion.prepareStatement(requete);
	        statement.setString(1, nom);
	        statement.setString(2, prenom);
	        statement.setString(3, nomUtilisateur);
	        statement.setString(4, password);
	        int lignesAffectees = statement.executeUpdate();
	        if (lignesAffectees > 0) {
	            System.out.println("L'utilisateur a été ajouté avec succès !");
	        } else {
	            System.out.println("Une erreur est survenue lors de l'ajout de l'utilisateur.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Erreur : " + e.getMessage());
	    }
	}

}
