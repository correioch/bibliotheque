package ca.grasset.bibliotheque.facade;

import java.sql.SQLException;
import java.util.List;

import ca.grasset.bibliotheque.DAO.ClientDAO;
import ca.grasset.bibliotheque.utilisateurs.Client;

public class ClientFacade {
	
	private ClientDAO dao;
	
	public ClientFacade() throws SQLException {
		this.dao = new ClientDAO();
	}
	
	public int save(Client client) throws SQLException {
			return dao.sauver(client);
	}
	
	public int update(Client client) throws SQLException {
		return dao.modifier(client);
	}
	
	public int delete(int id) throws SQLException {
		return dao.effacer(id);
	}
	
	public List <Client> listeClient() throws SQLException{
		return dao.liste();
	}
	
	
	

}
