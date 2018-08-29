package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class CategoriasDAO {

	private final Connection con;

	public CategoriasDAO(Connection con) {
		this.con = con;
	}

	public List<Categoria> lista() throws SQLException {

		List<Categoria> categorias = new ArrayList<>();

		String sql = "select * from Categoria";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.execute();

			try (ResultSet resultSet = statement.getResultSet()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nome = resultSet.getString("nome");
					Categoria categoria = new Categoria(id, nome);
					categorias.add(categoria);
				}
			}
		}

		return categorias;
	}

	public List<Categoria> listaComProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;

		String sql = "select c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao "
				+ "from Categoria as c join Produto as p on p.categoria_id = c.id";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.execute();

			try (ResultSet resultSet = statement.getResultSet()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("c_id");
					String nome = resultSet.getString("c_nome");

					if (ultima == null || !ultima.getNome().equals(nome)) {
						Categoria categoria = new Categoria(id, nome);
						categorias.add(categoria);
						ultima = categoria;
					}
					
					int idDoProduto = resultSet.getInt("p_id");
					String nomeDoProduto = resultSet.getString("p_nome");
					String descricaoDoProduto = resultSet.getString("p_descricao");
					
					Produto p = new Produto(nomeDoProduto, descricaoDoProduto);
					p.setId(idDoProduto);
					ultima.adiciona(p);

				}
			}
		}

		return categorias;
	}

}
