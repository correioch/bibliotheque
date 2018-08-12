package ca.grasset.bibliotheque;

import java.sql.SQLException;

import ca.grasset.bibliotheque.swing.form.AuteurForm;
import ca.grasset.bibliotheque.swing.form.ClientForm;
import ca.grasset.bibliotheque.swing.form.EmployeForm;
import ca.grasset.bibliotheque.swing.form.ExemplaireForm;
import ca.grasset.bibliotheque.swing.form.ISBNForm;
import ca.grasset.bibliotheque.swing.form.LivreForm;
import ca.grasset.bibliotheque.utilisateurs.Administrateur;
import ca.grasset.bibliotheque.utilisateurs.Client;
import ca.grasset.bibliotheque.utilisateurs.Employe;
import ca.grasset.bibliotheque.utilisateurs.Utilisateur;

public class TesteApp {
	public static void main(String[] args) throws SQLException {
		new ExemplaireForm();
		
	}
	
	
}
