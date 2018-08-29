package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class DataBase {

	private static DataSource dataSource;

	DataBase(){
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/loja-virtual");
		pool.setUser("SA");
		pool.setPassword("");
		DataBase.dataSource = pool;
	}
	
	public Connection getConnection() throws SQLException {
		//Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		
		Connection con = dataSource.getConnection();
		
		return con;
	}
}
