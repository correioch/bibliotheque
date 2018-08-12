package ca.grasset.bibliotheque.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.grasset.bibliotheque.connection.ConnectionPool;
import ca.grasset.bibliotheque.livre.Livre;
import ca.grasset.bibliotheque.utilisateurs.Adresse;
import ca.grasset.bibliotheque.utilisateurs.Client;


public class LivreDAO {
	
//	private final Connection connection;
//	
//	public LivreDAO() throws SQLException {
//		try (Connection con = new ConnectionPool().getConnection()) {
//			this.connection = con;
//		}
//	}
//
//	public LivreDAO(Connection connection) {
//		this.connection = connection;
//	}
//
//	public int sauver(Livre livre) throws SQLException {
//		try (PreparedStatement stmt = connection.prepareStatement(
//				"insert into exemplaire(situation, type_livre, status_disponibilite,  date_acquisition, id_edition"
//				+ "values (?, ?, ?, ?, ?)",
//				Statement.RETURN_GENERATED_KEYS)) {
//			stmt.setString(1, livre.getSituation());
//			stmt.setString(2, livre.getTypeLivre());
//			stmt.setString(3, livre.getStatus());
//			stmt.setDate(4, (Date) livre.getDateAcquisition());
//			stmt.setInt(5, livre.getIsbn().getId());
//
//			stmt.execute();
//			
//			try (ResultSet keys = stmt.getGeneratedKeys()) {
//				keys.next();
//				int id = keys.getInt("id");
//				livre.setId(id);
//				return 1;
//			}catch(SQLException e) {
//				return 2;
//			}
//		}
//	}
//
//	
//	public List<Livre> liste() throws SQLException {
//		String sql = "SELECT * FROM `exemplaire` JOIN  ON client.idUsager = adresse.idUsager" ;
//		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//			stmt.execute();
//			ResultSet resultSet = stmt.getResultSet();
//			ArrayList<Livre> livres = new ArrayList<>();
//			while(resultSet.next()) {
//				String nom = resultSet.getString("nom");
//				String prenom = resultSet.getString("prenom");
//				String code = resultSet.getString("code_utilisateur");
//				String password = resultSet.getString("mot_cle");
//				String courriel = resultSet.getString("courriel");
//				String telephone = resultSet.getString("telephone");
//				String attestationResidentiel = resultSet.getString("attestation_residentiel");
//				String situation = resultSet.getString("situation");
//				String pieceIdentite = resultSet.getString("piece_identite");
//				String adresse = resultSet.getString("adresse");
//				String ville = resultSet.getString("ville");
//				String province = resultSet.getString("province");
//				String codePostal = resultSet.getString("code_postal");
//
//				int id = resultSet.getInt("idUsager");
//				Adresse adresseClient = new Adresse(adresse, ville, province, codePostal);
//				Livre e = new Client(code, nom, prenom, password, pieceIdentite, telephone, courriel, situation, attestationResidentiel, adresseClient);
//				e.setId(id);
//				e.getAdresse().setIdClient(id);
//				livres.add(e);
//			}
//			return livres;
//		}
//	}
//	
//	public int effacer(int id) throws SQLException {
//		try (PreparedStatement stmt = connection.prepareStatement(
//				"DELETE FROM client where idUsager = ?")) {
//			stmt.setInt(1, id);
//			stmt.execute();
//			return 1;
//			
//		}catch (Exception e) {
//			return 2;		}
//	}
//	
//	public int modifier(Livre livre) throws SQLException {
//		try (PreparedStatement stmt = connection.prepareStatement(
//				"UPDATE     client c " + 
//				"INNER JOIN adresse a ON c.idUsager = a.idUsager " + 
//				" SET  c.nom = ?, c.prenom = ?, c.code_utilisateur = ?, c.courriel = ?, "
//				+ " c.telephone = ?, c.situation = ?, c.attestation_residentiel = ?, c.piece_identite = ?, "
//				+ "a.adresse = ?, a.ville = ?, a.province = ?, a.code_postal = ? "
//				+ " where c.idUsager = ?")) {
//			stmt.setString(1, livre.getNom());
//			stmt.setString(2, livre.getPrenom());
//			stmt.setString(3, livre.getLogin());
//			stmt.setString(4, livre.getCourriel());
//			stmt.setString(5, livre.getTelephone());
//			stmt.setString(6, livre.getSituation());
//			stmt.setString(7, livre.getAttestationResidentiel());
//			stmt.setString(8, livre.getPieceIdentite());
//			stmt.setString(9, livre.getAdresse().getAdresse());
//			stmt.setString(10, livre.getAdresse().getVille());
//			stmt.setString(11, livre.getAdresse().getProvince());
//			stmt.setString(12, livre.getAdresse().getCodePostal());
//			stmt.setInt(13,	 livre.getId());
//			System.out.println("aki");
//			stmt.execute();
//			return 1;
//		}catch(SQLException e) {
//			return 2;
//		}
//		
//	}
	
	

}
