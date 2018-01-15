package loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import loja.model.Produto;

public class ProdutoDAO {
	Connection con;

	public ProdutoDAO(Connection con) {
		this.con = con;
	}

	public void insereProduto(Produto produto) throws SQLException {
		String sql = "insert into Produto (nome, descricao) values (? , ?)";

		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDesc());
			stmt.execute();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {

					int id = rs.getInt("ID");
					produto.setId(id);
					System.out.println("Produto " + produto.getNome() + " foi adicionado com sucesso!");
				}
			}

		} catch (SQLSyntaxErrorException e) {
			System.err.println("Há um erro em seu SQL, favor verificar...");
		}
	}
	public void listaProduto(Connection con) throws SQLException {
		String sql = "select * from produto";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String desc = rs.getString("descricao");

					System.out.println("Produto: \nID: " + id + " \nNome: " + nome + " \nDescrição: " + desc
							+ "\n============================================");

				}
			}
		}
	}
	
	public void removeProduto(Connection con) throws SQLException {
		String sql = "delete from produto where id > 2";
		
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.execute();
			int count = stmt.getUpdateCount();
			System.out.println("Total de linhas afetadas: " + count);
		}
	}
}
