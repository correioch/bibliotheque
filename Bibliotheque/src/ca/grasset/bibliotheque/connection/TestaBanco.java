package ca.grasset.bibliotheque.connection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaBanco {
	
	public static void main(String[] args) throws SQLException {
		Connection con = Database.getConnection();
		Statement statement = con.createStatement();
		
		boolean resultado = statement.execute("SELECT * FROM teste ");
		
		ResultSet resultSet = statement.getResultSet();
		
		while(resultSet.next()) {
			System.out.println(resultSet.getString("nome"));
		}
		
		
		System.out.println(resultado);
		resultSet.close();
		statement.close();
		con.close();
	}

}
