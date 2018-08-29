package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {
	
	public static void main(String[] args) {
		Connection con;
		try {
			con = new DataBase().getConnection();
			
			Statement statement = con.createStatement();
			boolean resultado = statement.execute("select * from Produto");
			ResultSet resultSet = statement.getResultSet();		
			System.out.println("--------------------------------------");
			System.out.println("|           BANCO DE DADOS           |");
			System.out.println("--------------------------------------");
			while(resultSet.next()) {
			    int id = resultSet.getInt("id");
			    String nome = resultSet.getString("nome");
			    String descricao = resultSet.getString("descricao");
			    System.out.println("--------------------------------------");
			    System.out.println("| Id : "+id);
			    System.out.println("| Nome : "+nome);
			    System.out.println("| Descricao : "+descricao);
			}
			
			System.out.println("--------------------------------------");
			System.out.println("Sucesso na consulta : "+resultado);
			
			
			statement.close();
			resultSet.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		
	}
}
