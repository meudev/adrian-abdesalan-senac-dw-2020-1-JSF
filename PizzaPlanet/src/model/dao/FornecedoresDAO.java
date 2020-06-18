package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import domain.FornecedoresDOMAIN;
import factory.ConnectionFactory;

public class FornecedoresDAO {

	public void salvar(FornecedoresDOMAIN fornecedor) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setString(1, fornecedor.getDescricao());

		stmt.executeUpdate();

	}
	
	public void excluir(FornecedoresDOMAIN f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		stmt.setInt(1, f.getCodigo());

		stmt.executeUpdate();

	}
	
	public void editar(FornecedoresDOMAIN f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		stmt.setString(1, f.getDescricao());

		stmt.setInt(2, f.getCodigo());

		stmt.executeUpdate();

	}
	
	public FornecedoresDOMAIN buscaporcodigo(FornecedoresDOMAIN f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		stmt.setInt(1, f.getCodigo());

		ResultSet resultado = stmt.executeQuery();
		FornecedoresDOMAIN retorno = null;

		if (resultado.next()) {

			retorno = new FornecedoresDOMAIN();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));

		}

		return retorno;

	}
	
	public ArrayList<FornecedoresDOMAIN> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		ResultSet resultado = stmt.executeQuery();

		ArrayList<FornecedoresDOMAIN> lista = new ArrayList<FornecedoresDOMAIN>();

		while (resultado.next()) {
			FornecedoresDOMAIN f = new FornecedoresDOMAIN();
			f.setCodigo(resultado.getInt("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}
		return lista;

	}
	
	public ArrayList<FornecedoresDOMAIN> buscarPorDescricao(FornecedoresDOMAIN f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		stmt.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = stmt.executeQuery();

		ArrayList<FornecedoresDOMAIN> lista = new ArrayList<FornecedoresDOMAIN>();

		while (resultado.next()) {
			FornecedoresDOMAIN item = new FornecedoresDOMAIN();
			item.setCodigo(resultado.getInt("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}
		return lista;

	}

	public FornecedoresDOMAIN buscaPorCodigoFornecedor(int codigoFornecedor) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();
		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		FornecedoresDOMAIN retorno = null;
		
		try {
			stmt.setInt(1, codigoFornecedor);
	
			ResultSet resultado = stmt.executeQuery();
			
			
	
			if (resultado.next()) {
	
				retorno = new FornecedoresDOMAIN();
				retorno.setCodigo(resultado.getInt("codigo"));
				retorno.setDescricao(resultado.getString("descricao"));
				
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar fornecedor. Codigo: " + codigoFornecedor + " .Causa: " + ex.getMessage());
		}

		return retorno;
	}
	
}