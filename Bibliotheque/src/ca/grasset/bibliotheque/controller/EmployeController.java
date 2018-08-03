package ca.grasset.bibliotheque.controller;

import java.sql.SQLException;
import java.util.List;

import ca.grasset.bibliotheque.facade.EmployeFacade;
import ca.grasset.bibliotheque.utilisateurs.Employe;

public class EmployeController {

	private EmployeFacade facade;
	
	public EmployeController() throws SQLException {
		this.facade = new EmployeFacade();
	}
	
	public int addEmploye(Employe employe) throws SQLException {
		return facade.save(employe);
	}
	
	public int updateEmploye(Employe employe) throws SQLException {
		return facade.update(employe);
	}
	
	public int removeEmploye(int id) throws SQLException {
		return facade.delete(id);
	}
	
	public List<Employe> findEmploye() throws SQLException{
		return facade.listeEmploye();
	}
	
	
	
}
