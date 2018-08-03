package ca.grasset.bibliotheque.controller;

import java.sql.SQLException;
import java.util.List;

import ca.grasset.bibliotheque.facade.ClientFacade;
import ca.grasset.bibliotheque.utilisateurs.Adresse;
import ca.grasset.bibliotheque.utilisateurs.Client;

public class ClientController {

	private ClientFacade facade;
	
	public ClientController() throws SQLException {
		this.facade = new ClientFacade();
	}
	
	public int add(Client client) throws SQLException {
		return facade.save(client);
	}
	
	public int update(Client client) throws SQLException {
		return facade.update(client);
	}
	
	public int remove(int id) throws SQLException {
		return facade.delete(id);
	}
	
	public List<Client> find() throws SQLException{
		return facade.listeClient();
	}
	
	
	
}
