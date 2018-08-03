package ca.grasset.bibliotheque.facade;

import java.sql.SQLException;
import java.util.List;

import ca.grasset.bibliotheque.DAO.LivreDAO;
import ca.grasset.bibliotheque.utilisateurs.Client;

public class LivreFacade {
	
	private LivreDAO dao;
	
	public LivreFacade() throws SQLException {
		this.dao = new LivreDAO();
	}
	
	public int save(Client livre) throws SQLException {
			return dao.sauver(livre);
	}
	
	public int update(Client livre) throws SQLException {
		return dao.modifier(livre);
	}
	
	public int delete(int id) throws SQLException {
		return dao.effacer(id);
	}
	
	public List <Client> listeClient() throws SQLException{
		return dao.liste();
	}
	
}
