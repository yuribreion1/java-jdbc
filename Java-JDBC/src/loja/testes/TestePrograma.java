package loja.testes;

import java.sql.Connection;
import java.sql.SQLException;

import loja.dao.ProdutoDAO;
import loja.jdbc.ConnectionPool;
import loja.model.Produto;

public class TestePrograma {

	public static void main(String[] args) throws SQLException {
		Produto produto = new Produto("Mesa azul", "Mesa com 4 pés");
		
		try (Connection con = new ConnectionPool().getConnection()) {
			
			ProdutoDAO dao = new ProdutoDAO(con);
			
			dao.insereProduto(produto);
			//new ProdutoDAO(con).listaProduto(con);
			//new ProdutoDAO(con).removeProduto(con);
		}
	}
}
