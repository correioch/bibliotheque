package ca.grasset.bibliotheque.controller;

import java.sql.SQLException;
import java.util.List;

import ca.grasset.bibliotheque.facade.LivreFacade;
import ca.grasset.bibliotheque.utilisateurs.Client;

public class LivreController {

	private LivreFacade facade;
	
	public LivreController() throws SQLException {
		this.facade = new LivreFacade();
	}
	
	public int add(Client livre) throws SQLException {
		return facade.save(livre);
	}
	
	public int update(Client livre) throws SQLException {
		return facade.update(livre);
	}
	
	public int remove(int id) throws SQLException {
		return facade.delete(id);
	}
	
	public List<Client> find() throws SQLException{
		return facade.listeClient();
	}
	
}
