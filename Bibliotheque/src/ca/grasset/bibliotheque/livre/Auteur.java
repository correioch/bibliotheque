package ca.grasset.bibliotheque.livre;

public class Auteur {
	
	String nom;
	String Prenom;
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Auteur() {
		// TODO Auto-generated constructor stub
	}
	
	public Auteur(String nom, String prenom) {
		super();
		this.nom = nom;
		Prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	
	
	
	

}
