package ca.grasset.bibliotheque.livre;

public class Auteur {
	
	String nom;
	String prenom;
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
		this.prenom = prenom;
	}
	
	public Auteur(String nom, String prenom, int id) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getPrenom() + " " + getNom();
	}
	

}
