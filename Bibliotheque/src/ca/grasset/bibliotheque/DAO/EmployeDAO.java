package ca.grasset.bibliotheque.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.grasset.bibliotheque.connection.ConnectionPool;
import ca.grasset.bibliotheque.utilisateurs.Employe;

public class EmployeDAO {
	
	private final Connection connection;
	
	public EmployeDAO() throws SQLException {
		try (Connection con = new ConnectionPool().getConnection()) {
			this.connection = con;
		}
	}

	public EmployeDAO(Connection connection) {
		this.connection = connection;
	}

	public int sauver(Employe employe) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"insert into employe(nom, prenom, code_utilisateur, mot_cle, id_type_employe) values (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, employe.getNom());
			stmt.setString(2, employe.getPrenom());
			stmt.setString(3, employe.getLogin());
			stmt.setString(4, "1234");
			stmt.setInt(5, 2);


			stmt.execute();
			try (ResultSet keys = stmt.getGeneratedKeys()) {
				keys.next();
				int id = keys.getInt("id");
				return 1;
			}catch(SQLException e) {
				return 2;
			}
		}
	}

	public List<Employe> liste() throws SQLException {
		String sql = "select * from employe where id_type_employe = 2" ;
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();
			ArrayList<Employe> employes = new ArrayList<>();
			while(resultSet.next()) {
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String code = resultSet.getString("code_utilisateur");
				String password = resultSet.getString("mot_cle");

				int id = resultSet.getInt("id_employe");
				Employe e = new Employe(code, nom, prenom, password);
				e.setId(id);
				employes.add(e);
			}
			return employes;
		}
	}
	
	public int effacer(int id) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"DELETE FROM employe where id_employe = ?")) {
			stmt.setInt(1, id);
			stmt.execute();
			return 1;
			
		}catch (Exception e) {
			return 2;		}
	}
	
	public int modifier(Employe employe) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"UPDATE employe SET  nom = ?, prenom = ?, code_utilisateur = ? where id_employe = ?")) {
			stmt.setString(1, employe.getNom());
			stmt.setString(2, employe.getPrenom());
			stmt.setString(3, employe.getLogin());
			stmt.setInt(4,	 employe.getId());
			stmt.execute();
			return 1;
		}catch(SQLException e) {
			return 2;
		}
		
	}
	
	

}
