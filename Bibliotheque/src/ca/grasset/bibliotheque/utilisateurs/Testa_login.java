package ca.grasset.bibliotheque.utilisateurs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.grasset.bibliotheque.connection.ConnectionPool;

public class Testa_login {
	public static void main(String[] args) throws SQLException {
		
		
		Connection connection = new ConnectionPool().getConnection();
		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("select * from employe");
		ResultSet resultSet = statement.getResultSet();
		
		while (resultSet.next()) {
			//int id = resultSet.getInt("id");
			String nome = resultSet.getString("nom");
			String descricao = resultSet.getString("prenom");
			System.out.println( "nome=" + nome + ", descricao=" + descricao);
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		
		
		
	}
}
