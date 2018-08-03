package ca.grasset.bibliotheque.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bibliotheque", "root", "");
		return con;
	}
	
}
