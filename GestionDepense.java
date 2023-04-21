package diaw_projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestionDepense {
	public static void MenuUser(String login, String pwd) {
        Scanner sc = new Scanner(System.in);
    	int choix;
    	int id = 0;
    	Connection conn = Main.getConnexion();
	    PreparedStatement ps = null;
	    ResultSet rs;
		
    	do {
    		System.out.println("\t=====Menu=====");
    		System.out.println("\t1.Ajouter depense");
    		System.out.println("\t2.Supprimer depense");
    		System.out.println("\t3.Modifier depense");
    		System.out.println("\t4.Consulter depense");
    		System.out.println("\t5.Menu Principale");
    		choix = sc.nextInt();
    		switch (choix) {
    			case 1 : 
    			       
    			    	   System.out.println("=====Categories=====");
       		    		System.out.println("\t1.Nourriture");
       		    		System.out.println("\t2.Electricite");
       		    		System.out.println("\t3.Location");
       		    		System.out.println("\t4.Eau");
       		    		System.out.print("\t1/2/3/4:");
       		    		int cat = sc.nextInt();
       				System.out.println("Veuillez entrer le montant ");
       				int montant = sc.nextInt();
       				try {
    			           ps = conn.prepareStatement("SELECT id FROM utilisateurs WHERE nom_user=? AND password=?");
    			           ps.setString(1, login);
    			           ps.setString(2, pwd);
    			           rs = ps.executeQuery();
    			           if (rs.next()) {
    			               id = rs.getInt("id");
    			           } else {
    			               System.out.println("Aucun résultat trouvé pour l'utilisateur.");
    			           }
       				}catch (SQLException e) {
 			           System.out.println("Erreur lors de la creation de depense : " + e.getMessage());
 			       }  
    			      
                    try {
                        String query = "INSERT INTO depenses (montant, iduser, idcategorie) VALUES ( ?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        pstmt.setDouble(1, montant);
                        pstmt.setInt(2, id);
                        pstmt.setInt(3, cat);
                        pstmt.executeUpdate();
                        System.out.println("La dépense a été ajoutée avec succès.");
                    } catch (SQLException e) {
                        System.out.println("Erreur lors de l'ajout de la dépense : " + e.getMessage());
                    }
    			       
    				break;
    			case 2 :     				
    				afficheDepense(conn);
    				System.out.println("Veuillez saisir l'id de la depense a supprimer :");
    				int idsupp = sc.nextInt();
				try {
					ps = conn.prepareStatement("DELETE FROM depenses WHERE iddepense=?");
					ps.setInt(1,idsupp);
					int rowsDeleted = ps.executeUpdate();
				    if (rowsDeleted > 0) {
				        System.out.println("La dépense a été supprimée avec succès.");
				    } else {
				        System.out.println("Aucune dépense n'a été supprimée.");
				    }
				} catch (SQLException e) {
					System.out.println("La dépense a été supprimée avec succès.");
					e.printStackTrace();
				}
					System.out.print("Suppression reussie!");
    				break;
    			case 3 :
    				
    				afficheDepense(conn);

        				System.out.println("Veuillez saisir l'id de la depense a modifier :");
        				int idDepense = sc.nextInt();
        				System.out.println("Veuillez saisir le nouveau montant :");
        				int newMontant = sc.nextInt();
        				try {
        				    ps = conn.prepareStatement("UPDATE depenses SET montant=? WHERE iddepense=?");
        				    ps.setInt(1, newMontant);
        				    ps.setInt(2, idDepense);
        				    int rowsUpdated = ps.executeUpdate();
        				    if (rowsUpdated > 0) {
        				        System.out.println("La dépense a été mise à jour avec succès.");
        				    } else {
        				        System.out.println("Aucune dépense n'a été mise à jour.");
        				    }
        				} catch (SQLException e) {
        				    System.out.println("Erreur lors de la mise à jour de la dépense : " + e.getMessage());
        				}
    				break;
    			case 4:
    				afficheDepense(conn);
    				break;
    			case 5 : 
    				Main.MenuPrincipale();
    				break;
    			default :
    	            System.out.println("La catégorie spécifiée n'existe pas !");
    		}
    	}while(choix != 5);
    	sc.close();
    }
	
	
	public static void afficheDepense( Connection conn) {
		ResultSet res=null; 
		try {
			Statement ps1 = conn.createStatement();
			res = ps1.executeQuery("SELECT * FROM depenses");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Liste depenses");
			System.out.println("Id \t\t idCategorie \t\t Montant");
			try {
				while(res.next()) {
				System.out.println(res.getInt(1)+"\t\t"+res.getInt(4)+"\t\t"+res.getInt(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
