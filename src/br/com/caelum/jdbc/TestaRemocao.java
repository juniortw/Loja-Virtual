package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
	
	public static void main(String[] args) {
		Connection con;
		try {
			con = new DataBase().getConnection();
			
			Statement statement = con.createStatement();
			statement.execute("DELETE from Produto where id>3");
			int count = statement.getUpdateCount(); // Descobrir quantas linhas foram removidas
			
			System.out.println("Número de linhas atualizadas : "+count);
			
			statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
	}	
}
