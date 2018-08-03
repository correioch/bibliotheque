package ca.grasset.bibliotheque.utilisateurs;

public class Client extends Utilisateur{

	private String pieceIdentite;
	private String courriel;
	private String telephone;
	private String Situation;
	private String attestationResidentiel;
	private Adresse adresse;
	
	

	public Client() {
		
	}
	
	public Client(String login, String nom, String prenom, String password, String pieceIdentite, 
			String telephone, String courriel, String situation, String attestation_residentiel) {
		super(login, nom, prenom, "client", password);
		this.pieceIdentite = pieceIdentite;
		this.telephone = telephone;
		this.courriel = courriel;
		this.attestationResidentiel = attestation_residentiel;
		this.Situation = situation;
	}
	
	public Client(String login, String nom, String prenom, String password, String pieceIdentite, 
			String telephone, String courriel, String situation, String attestation_residentiel, Adresse adresse) {
		super(login, nom, prenom, "client", password);
		this.pieceIdentite = pieceIdentite;
		this.telephone = telephone;
		this.courriel = courriel;
		this.attestationResidentiel = attestation_residentiel;
		this.Situation = situation;
		this.adresse = adresse;
	}

	public String getPieceIdentite() {
		return pieceIdentite;
	}

	public void setPieceIdentite(String pieceIdentite) {
		this.pieceIdentite = pieceIdentite;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSituation() {
		return Situation;
	}

	public void setSituation(String situation) {
		Situation = situation;
	}

	public String getAttestationResidentiel() {
		return attestationResidentiel;
	}

	public void setAttestationResidenciel(String attestationResidentiel) {
		this.attestationResidentiel = attestationResidentiel;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

}
