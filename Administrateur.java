package diaw_projet;

public class Administrateur {
	private int idAdm;
	private String passwd;
	private String loginAdm;
	private String nom;
	private String prenom;
	
	public Administrateur() {}
	
	public Administrateur(int idAdm, String passwd, String loginAdm, String nom,  String prenom) {
		this.idAdm = idAdm;
		this.passwd = passwd;
		this.loginAdm = loginAdm;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public int getIdAdm() {return idAdm;}
	public String getPasswd() {return passwd;}
	public String getLoginAdm() {return loginAdm;}
	public String getNom() {return nom;}
	public String getPrenom() {return prenom;}
	
	public void setIdAdm(int idAdm) {this.idAdm = idAdm;}
	public void setPasswd(String passwd) {this.passwd = passwd;}
	public void setLoginAdm(String loginAdm) {this.loginAdm = loginAdm;}
	public void setNom(String nom) {this.nom = nom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
}
