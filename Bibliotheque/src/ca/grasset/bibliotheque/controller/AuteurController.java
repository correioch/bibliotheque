package ca.grasset.bibliotheque.controller;

import java.sql.SQLException;
import java.util.List;

import ca.grasset.bibliotheque.facade.AuteurFacade;
import ca.grasset.bibliotheque.livre.Auteur;
public class AuteurController {

	private AuteurFacade facade;
	
	public AuteurController() throws SQLException {
		this.facade = new AuteurFacade();
	}
	
	public int add(Auteur auteur) throws SQLException {
		return facade.save(auteur);
	}
	
	public int update(Auteur auteur) throws SQLException {
		return facade.update(auteur);
	}
	
	public int remove(int id) throws SQLException {
		return facade.delete(id);
	}
	
	public List<Auteur> find() throws SQLException{
		return facade.liste();
	}
	
}
