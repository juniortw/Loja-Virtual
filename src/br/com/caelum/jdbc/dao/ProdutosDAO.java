package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class ProdutosDAO {

	private final Connection con;

	public ProdutosDAO(Connection con) {
		this.con = con;

	}

	public void salva(Produto produto) throws SQLException {
		String sql = "insert into Produto (nome, descricao) values (?,?)";

		try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.execute();

			try (ResultSet resultset = statement.getGeneratedKeys()) {
				if (resultset.next()) {
					int id = resultset.getInt("id");
					produto.setId(id);
				}
			}
		}

	}

	public List<Produto> lista() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from Produto";

		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.execute();

			transformaResultadosEmProdutos(produtos, statement);
		}

		return produtos;
	}

	
	public List<Produto> busca(Categoria categoria) throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from Produto where categoria_id = ?";

		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setInt(1, categoria.getId());
			statement.execute();

			transformaResultadosEmProdutos(produtos, statement);
		}

		return produtos;
	}
	
	private void transformaResultadosEmProdutos(List<Produto> produtos, PreparedStatement statement) throws SQLException {
		try (ResultSet resultSet = statement.getResultSet()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String descricao = resultSet.getString("descricao");

				Produto produto = new Produto(nome, descricao);
				produto.setId(id);
				produtos.add(produto);
			}
		}
	}
}
