package br.com.caelum.jdbc;


import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
	
	public static void main(String[] args) {		
		
		try {
			
			Connection con = new DataBase().getConnection();	
			
			System.out.println("Abrindo uma conexão de suceso");
			
			
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}

}
