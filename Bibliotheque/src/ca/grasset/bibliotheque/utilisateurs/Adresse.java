package ca.grasset.bibliotheque.utilisateurs;

public class Adresse {

	protected int idClient;
	protected String adresse;
	protected String ville;
	protected String province;
	protected String codePostal;
	
	public Adresse() {
		
	}
	
	public Adresse(String adresse, String ville, String province, String codePostal) {
		super();
		this.adresse = adresse;
		this.ville = ville;
		this.province = province;
		this.codePostal = codePostal;
	}
	
	

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int id) {
		this.idClient = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	
	
}
