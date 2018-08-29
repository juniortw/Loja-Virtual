package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {

		try (Connection connection = new DataBase().getConnection()) {
			connection.setAutoCommit(false);
			String sql = "insert into Produto (nome, descricao) values(?, ?)";

			// createStatement para executar comandos sql
			// Statement statement = con.createStatement();
			
			// prepareStatement evita de executar sql inválido e os caracteres especiais 
			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				adiciona("TV LCD", "32 polegadas", statement);
				adiciona("Blueray", "Full HDMI", statement);
				
				connection.commit();
			} catch (Exception ex) {
				ex.printStackTrace();
				connection.rollback();
				System.out.println("Rollback efetuado");
			}

		}
	}

	private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		statement.setString(1, nome);
		statement.setString(2, descricao);

		boolean resultado = statement.execute();

		System.out.println("Sucesso na consulta : " + resultado);
		
		// método getGeneratedKeys nos retorna um ResultSet com todas as chaves geradas
		ResultSet resultSet = statement.getGeneratedKeys();

		while (resultSet.next()) {
			String id = resultSet.getString("id");
			System.out.println("Id Gerado : " + id);
		}

		resultSet.close();
	}

}
