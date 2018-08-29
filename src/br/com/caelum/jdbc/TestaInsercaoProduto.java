package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaInsercaoProduto {

	public static void main(String[] args) throws SQLException {

		Produto mesa = new Produto("Mesa Vermelha", "Mesa com 4 pés");

		try (Connection con = new DataBase().getConnection()) {
			ProdutosDAO dao = new ProdutosDAO(con);
			dao.salva(mesa);
			
			List<Produto> produtos = dao.lista();
			for(Produto produto : produtos) {
				System.out.println("Existe o produto : "+produto);
			}
		}
		
	}
	
	



}	
