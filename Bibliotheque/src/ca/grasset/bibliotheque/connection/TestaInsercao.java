package ca.grasset.bibliotheque.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		Connection con = Database.getConnection();
		Statement statement = con.createStatement();
		
		boolean resultado = statement.execute("insert into teste (nome) values ('Larica')");
		
		System.out.println(resultado);		
		
		//resultSet.close();
		statement.close();
		con.close();
	}
	
}
