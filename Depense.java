package diaw_projet;
import java.sql.Date;
public class Depense {
	private int idDepense;
	private int montant;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Date date;
	
	public Depense() {}
	
	public Depense(int idDepense, int montant, Utilisateur utilisateur, Categorie categorie, Date date) {
		this.idDepense = idDepense;
		this.montant = montant;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.date = date;
	}
	
	public int getIdDepense() {return idDepense;}
	public int getMontant() {return montant;}
	public Utilisateur getUtilisateur() {return utilisateur;}
	public Categorie getCategorie() {return categorie;}
	public Date getDate() {return date;}
	
	public void setIdDepense(int idDepense) {this.idDepense = idDepense;}
	public void setMontant(int montant) {this.montant = montant;}
	public void setUtilisateur(Utilisateur utilisateur) {this.utilisateur = utilisateur;}
	public void setCategorie(Categorie categorie) {this.categorie = categorie;}
	public void setDate(Date date) {this.date = date;}
}
