package diaw_projet;

public class Categorie {
	private int idCat;
	private String nomCat;
	
	public Categorie() {}
	
	public Categorie(int idCat, String nomCat) {
		this.idCat = idCat;
		this.nomCat = nomCat;
	}
	
	public int getIdCat() {return idCat;}
	public String getNomCat() {return nomCat;}
	
	public void setIdCat(int idCat, String nomCat) {
		this.idCat = idCat;
		this.nomCat = nomCat;
	}
}
