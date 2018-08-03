package ca.grasset.bibliotheque.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mariadb.jdbc.*;

public class ConnectionPool {
	
	private final DataSource dataSource;
	
	public ConnectionPool() throws SQLException {
		System.out.println("conection pool...");
		MariaDbPoolDataSource pool = new MariaDbPoolDataSource();
		pool.setUrl("jdbc:mariadb://localhost:3306/bibliotheque");
		pool.setUser("root");
		pool.setPassword("");
		dataSource = pool;
		System.out.println("ok");
		
	}
	
	public Connection getConnection() throws SQLException {
		System.out.println("new connection...");
		Connection connection = dataSource.getConnection();
		System.out.println(" ok");
		return connection;
	}
	
}
