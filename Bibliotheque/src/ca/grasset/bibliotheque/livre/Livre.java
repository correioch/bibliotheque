package ca.grasset.bibliotheque.livre;

import java.sql.Date;

public class Livre {
	
	ISBN isbn;
	String situation;	
	String typeLivre;
	String status;
	
	String dateAcquisition;
	int id;
	
	public Livre() {
		// TODO Auto-generated constructor stub
	}
	
	public Livre(ISBN isbn, String situation, String typeLivre, String status, String dateAcquisition, int id) {
		super();
		this.isbn = isbn;
		this.situation = situation;
		this.typeLivre = typeLivre;
		this.status = status;
		this.dateAcquisition = dateAcquisition;
		this.id = id;
	}
	public ISBN getIsbn() {
		return isbn;
	}
	public void setIsbn(ISBN isbn) {
		this.isbn = isbn;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getTypeLivre() {
		return typeLivre;
	}
	public void setTypeLivre(String typeLivre) {
		this.typeLivre = typeLivre;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDateAcquisition() {
		return dateAcquisition;
	}
	public void setDateAcquisition(String date) {
		this.dateAcquisition = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
