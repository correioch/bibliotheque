package ca.grasset.bibliotheque.facade;

import java.sql.SQLException;
import java.util.List;

import ca.grasset.bibliotheque.DAO.AuteurDAO;
import ca.grasset.bibliotheque.livre.Auteur;


public class AuteurFacade {
	
	private AuteurDAO dao;
	
	public AuteurFacade() throws SQLException {
		this.dao = new AuteurDAO();
	}
	
	public int save(Auteur auteur) throws SQLException {
			return dao.sauver(auteur);
	}
	
	public int update(Auteur auteur) throws SQLException {
		return dao.modifier(auteur);
	}
	
	public int delete(int id) throws SQLException {
		return dao.effacer(id);
	}
	
	public List <Auteur> liste() throws SQLException{
		return dao.liste();
	}
	
}
