package ca.grasset.bibliotheque.facade;

import java.sql.SQLException;
import java.util.List;

import ca.grasset.bibliotheque.DAO.EmployeDAO;
import ca.grasset.bibliotheque.utilisateurs.Employe;

public class EmployeFacade {
	
	private EmployeDAO dao;
	
	public EmployeFacade() throws SQLException {
		this.dao = new EmployeDAO();
	}
	
	public int save(Employe employe) throws SQLException {
			return dao.sauver(employe);
	}
	
	public int update(Employe employe) throws SQLException {
		return dao.modifier(employe);
	}
	
	public int delete(int id) throws SQLException {
		return dao.effacer(id);
	}
	
	public List <Employe> listeEmploye() throws SQLException{
		return dao.liste();
	}
	
	
	

}
