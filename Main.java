package diaw_projet;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main {
	public static void main(String[] args) {
		MenuPrincipale();
		}

////////////////             INSCRIPTION              //////////// 

public static void inscription() {
    Scanner sc = new Scanner(System.in);
    System.out.println("=== Inscription ===");
    System.out.println("Veuillez entrer votre nom : ");
    String nom = sc.nextLine();
    System.out.println("Veuillez entrer votre prenom : ");
    String prenom = sc.nextLine();
    System.out.println("Veuillez entrer votre nomUser : ");
    String nomUser = sc.nextLine();
    System.out.println("Veuillez entrer votre mot de passe : ");
    String password = sc.nextLine();
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setNom(nom);
    utilisateur.setPrenom(prenom);
    utilisateur.setNomUser(nomUser);
    utilisateur.setPassword(password);
    boolean resultat = ajouterUser(nom, prenom, nomUser, password);
    if (resultat) {
        System.out.println("Inscription réussie !");
    } else {
        System.out.println("Erreur lors de l'inscription.");
    }
    sc.close();
}    

////////////             AJOUTER USER                 ///////////////

    public static boolean ajouterUser(String nom, String prenom, String nomUser, String password) {
        try {
            Connection connexion = getConnexion();
            PreparedStatement preparedStatement = connexion.prepareStatement(
                "INSERT INTO utilisateurs (nom, prenom, nom_user, password) VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, nomUser);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
            System.out.println("Inscription réussie !");
            preparedStatement.close();
            connexion.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
            return false;
        }
    }
   
    public static Connection getConnexion() {
  	  Connection connexion = null;
  	  try {
  	        Class.forName("com.mysql.jdbc.Driver");
  	        String url = "jdbc:mysql://localhost:3306/sofamous";
  	        String utilisateur = "root";
  	        String motDePasse = "";
  	        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
  	  } catch (ClassNotFoundException | SQLException e) {
  	        e.printStackTrace();
  	    }
  	    return connexion;
  }

//////////////////CONNEXION          /////////////////
public static void connexion() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Veuillez entrer votre nom d'utilisateur :");
    String nomUser = sc.nextLine();
    System.out.println("Veuillez entrer votre mot de passe :");
    String password = sc.nextLine();
    if (nomUser.equals("admin") && password.equals("admin2023")) {
    	MenuAdmin();
    }
    else {
	    if (!getUtilisateur(nomUser,password)) {
	    	System.out.println("Le nom d'utilisateur ou le mot de passe est incorrect.");
	    	MenuPrincipale();
	    	sc.close();
	    	return;
    	}
	    System.out.println("Bienvenue " + nomUser + " !");
	    GestionDepense.MenuUser(nomUser,password);
	    sc.close();
	    return;
    }
	    
       sc.close();
    }
  
    
    //////////////		MENU ADMIN		///////////////
    

    public static void MenuAdmin() {
        Scanner sc = new Scanner(System.in);
    	int choix;
    	do {
    		System.out.println("=====Menu Administrateur=====");
    		System.out.println("\t1.Ajouter categorie");
    		System.out.println("\t2.Supprimer categorie");
    		System.out.println("\t3.Modifier categorie");
    		System.out.println("\t4.Menu Principale");
    		System.out.println("\t5.Quitter");
    		choix = sc.nextInt();
    		switch (choix) {
    			case 1 : 
    				System.out.println("Veuillez entrer le nom de la nouvelle catégorie : ");
                    String nomCategorie = sc.next();
                    GestionCategorie.ajouterCategorie(nomCategorie);
                    System.out.println("Catégorie ajoutée avec succès !");
    				break;
    			case 2 : 
    				System.out.println("Veuillez entrer le nom de la catégorie à supprimer : ");
                    String nomCategorieASupprimer = sc.next();
                    GestionCategorie.supprimerCategorie(nomCategorieASupprimer);
                    System.out.println("Catégorie supprimée avec succès !"); 
    				break;
    			case 3 :
    				System.out.println("Veuillez entrer le nom de la catégorie à modifier : ");
                    String ancienNomCategorie = sc.next();
                    System.out.println("Veuillez entrer le nouveau nom de la catégorie : ");
                    String nouveauNomCategorie = sc.next();
                    GestionCategorie.modifierCategorie(ancienNomCategorie, nouveauNomCategorie);
    				break;
    			case 4 : 
    				Main.MenuPrincipale();
    				break;
    			case 5 : 
    				return;
    			default : System.out.println("Choix incorrecte!");
    		}
    	}while(choix != 5);
    	sc.close();
    }

    
//////////   		MENU PRINCIPALE			//////////////////
    public static void MenuPrincipale() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue dans notre systeme de gestion de budget:\n"); 
		System.out.println("voulez vous vous inscrire ou vous connecte?\n");
		System.out.println("tapez 1 pour l'inscription ou 2 pour la connexion\n");
		int choix = sc.nextInt();
		switch (choix){
			case 1 :
				inscription();
				MenuPrincipale();
				break;
			case 2 :
				connexion();
				break;
			default : 
				System.out.println("Choix invalide");}
		sc.close();
    }
    
   
   
   /////////		CHERCHER UTILISATEUR 		/////////////
   public static boolean getUtilisateur(String nomUser, String passwd) {
       Connection conn = getConnexion();
       PreparedStatement ps = null;
       ResultSet rs = null;
       boolean trouve = false;

       try {
           String query = "SELECT * FROM utilisateurs WHERE nom_user=? AND password=?";
           ps = conn.prepareStatement(query);
           ps.setString(1, nomUser);
           ps.setString(2, passwd);
           rs = ps.executeQuery();

           if (rs.next()) {
               trouve = true;
           }

       } catch (SQLException e) {
           System.out.println("Erreur lors de la récupération de l'utilisateur dans la base de données : " + e.getMessage());
       } 

       return trouve;
   }


}