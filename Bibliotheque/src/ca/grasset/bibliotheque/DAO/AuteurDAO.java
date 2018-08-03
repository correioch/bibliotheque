package ca.grasset.bibliotheque.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.grasset.bibliotheque.connection.ConnectionPool;
import ca.grasset.bibliotheque.livre.Auteur;
import ca.grasset.bibliotheque.utilisateurs.Adresse;
import ca.grasset.bibliotheque.utilisateurs.Client;


public class AuteurDAO {
	
	private final Connection connection;
	
	public AuteurDAO() throws SQLException {
		try (Connection con = new ConnectionPool().getConnection()) {
			this.connection = con;
		}
	}

	public AuteurDAO(Connection connection) {
		this.connection = connection;
	}

	public int sauver(Auteur auteur) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"insert into auteur(nom, prenom) "
				+ "values (?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, auteur.getNom());
			stmt.setString(2, auteur.getPrenom());
	
			stmt.execute();
			
			try (ResultSet keys = stmt.getGeneratedKeys()) {
				keys.next();
				int id = keys.getInt("id");
				auteur.setId(id);
				return 1;
			}catch(SQLException e) {
				return 2;
			}
		}
	}


	public List<Auteur> liste() throws SQLException {
		String sql = "SELECT * FROM `auteur`" ;
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();
			ArrayList<Auteur> auteurs = new ArrayList<>();
			while(resultSet.next()) {
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");

				int id = resultSet.getInt("id_auteur");
				Auteur e = new Auteur(nom, prenom);
				e.setId(id);
				auteurs.add(e);
			}
			return auteurs;
		}
	}
	
	public int effacer(int id) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"DELETE FROM auteur where id_auteur = ?")) {
			stmt.setInt(1, id);
			stmt.execute();
			return 1;
			
		}catch (Exception e) {
			return 2;		}
	}
	
	public int modifier(Auteur auteur) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"UPDATE auteur" +  
				" SET  nom = ?, prenom = ?"
				+ " where id_auteur = ?")) {
			stmt.setString(1, auteur.getNom());
			stmt.setString(2, auteur.getPrenom());
			stmt.setInt(3,	 auteur.getId());
			stmt.execute();
			return 1;
		}catch(SQLException e) {
			return 2;
		}
		
	}
	
	

}
