package ca.grasset.bibliotheque.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.grasset.bibliotheque.connection.ConnectionPool;
import ca.grasset.bibliotheque.utilisateurs.Adresse;
import ca.grasset.bibliotheque.utilisateurs.Client;


public class LivreDAO {
	
	private final Connection connection;
	
	public LivreDAO() throws SQLException {
		try (Connection con = new ConnectionPool().getConnection()) {
			this.connection = con;
		}
	}

	public LivreDAO(Connection connection) {
		this.connection = connection;
	}

	public int sauver(Client client) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"insert into client(nom, prenom, code_utilisateur, mot_cle, "
				+ "telephone, piece_identite, courriel, situation, attestation_residentiel) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, client.getNom());
			stmt.setString(2, client.getPrenom());
			stmt.setString(3, client.getLogin());
			stmt.setString(4, "1234");
			stmt.setString(5, client.getTelephone());
			stmt.setString(6, client.getPieceIdentite());
			stmt.setString(7, client.getCourriel());
			stmt.setString(8, client.getSituation());
			stmt.setString(9, client.getAttestationResidentiel());

			stmt.execute();
			
			try (ResultSet keys = stmt.getGeneratedKeys()) {
				keys.next();
				int id = keys.getInt("id");
				client.setId(id);
				sauverAdresse(client);
				return 1;
			}catch(SQLException e) {
				return 2;
			}
		}
	}

	private void sauverAdresse(Client client) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"INSERT INTO adresse(adresse, ville, province, code_postal, idUsager) VALUES (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, client.getAdresse().getAdresse());
			stmt.setString(2, client.getAdresse().getVille());
			stmt.setString(3, client.getAdresse().getProvince());
			stmt.setString(4, client.getAdresse().getCodePostal());
			stmt.setInt(5, client.getId());
			stmt.execute();
			
			
			
		}catch(SQLException e) {
				System.out.println("errorrrr");
				
		}
		
	}

	public List<Client> liste() throws SQLException {
		String sql = "SELECT * FROM `` JOIN adresse ON client.idUsager = adresse.idUsager" ;
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();
			ArrayList<Client> clients = new ArrayList<>();
			while(resultSet.next()) {
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String code = resultSet.getString("code_utilisateur");
				String password = resultSet.getString("mot_cle");
				String courriel = resultSet.getString("courriel");
				String telephone = resultSet.getString("telephone");
				String attestationResidentiel = resultSet.getString("attestation_residentiel");
				String situation = resultSet.getString("situation");
				String pieceIdentite = resultSet.getString("piece_identite");
				String adresse = resultSet.getString("adresse");
				String ville = resultSet.getString("ville");
				String province = resultSet.getString("province");
				String codePostal = resultSet.getString("code_postal");

				int id = resultSet.getInt("idUsager");
				Adresse adresseClient = new Adresse(adresse, ville, province, codePostal);
				Client e = new Client(code, nom, prenom, password, pieceIdentite, telephone, courriel, situation, attestationResidentiel, adresseClient);
				e.setId(id);
				e.getAdresse().setIdClient(id);
				clients.add(e);
			}
			return clients;
		}
	}
	
	public int effacer(int id) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"DELETE FROM client where idUsager = ?")) {
			stmt.setInt(1, id);
			stmt.execute();
			return 1;
			
		}catch (Exception e) {
			return 2;		}
	}
	
	public int modifier(Client client) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"UPDATE     client c " + 
				"INNER JOIN adresse a ON c.idUsager = a.idUsager " + 
				" SET  c.nom = ?, c.prenom = ?, c.code_utilisateur = ?, c.courriel = ?, "
				+ " c.telephone = ?, c.situation = ?, c.attestation_residentiel = ?, c.piece_identite = ?, "
				+ "a.adresse = ?, a.ville = ?, a.province = ?, a.code_postal = ? "
				+ " where c.idUsager = ?")) {
			stmt.setString(1, client.getNom());
			stmt.setString(2, client.getPrenom());
			stmt.setString(3, client.getLogin());
			stmt.setString(4, client.getCourriel());
			stmt.setString(5, client.getTelephone());
			stmt.setString(6, client.getSituation());
			stmt.setString(7, client.getAttestationResidentiel());
			stmt.setString(8, client.getPieceIdentite());
			stmt.setString(9, client.getAdresse().getAdresse());
			stmt.setString(10, client.getAdresse().getVille());
			stmt.setString(11, client.getAdresse().getProvince());
			stmt.setString(12, client.getAdresse().getCodePostal());
			stmt.setInt(13,	 client.getId());
			System.out.println("aki");
			stmt.execute();
			return 1;
		}catch(SQLException e) {
			return 2;
		}
		
	}
	
	

}
