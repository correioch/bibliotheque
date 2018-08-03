package ca.grasset.bibliotheque.utilisateurs;

public class Utilisateur {
	
	protected String login;
	protected String password;
	protected String nom;
	protected String prenom;
	protected String type;
	protected int id;
	
	
	public Utilisateur() {
		
	}
	
	public Utilisateur(String login, String nom, String prenom, String type, String password) {
		super();
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.type = type;
		
	}


	public String getNom() {
		return nom;
	}
	public String getType() {
		return type;
	}
	public String getPrenom() {
		return prenom;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	

}
