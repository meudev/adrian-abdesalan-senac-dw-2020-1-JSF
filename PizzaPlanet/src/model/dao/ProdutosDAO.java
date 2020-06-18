package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.FornecedoresDOMAIN;
import domain.ProdutosDOMAIN;
import factory.ConnectionFactory;

public class ProdutosDAO {

	public void salvar(ProdutosDOMAIN produtos) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedoresproduto ");
		sql.append("(descricao, valor, quantidade, codigoFornecedor) ");
		sql.append("VALUES (?,?,?,?) ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());
		stmt.setString(1, produtos.getDescricao());
		stmt.setDouble(2, produtos.getValor());
		stmt.setLong(3, produtos.getQuantidade());
		stmt.setInt(4, produtos.getFornecedor().getCodigo());

		stmt.executeUpdate();

	}
	
	public void excluir(ProdutosDOMAIN produtos) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedoresproduto ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		stmt.setInt(1, produtos.getCodigo());

		stmt.executeUpdate();

	}
	
	public void editar(ProdutosDOMAIN produtos) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedoresproduto ");
		sql.append("SET descricao = ?,  valor = ? , quantidade = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		stmt.setString(1, produtos.getDescricao());
		stmt.setDouble(2, produtos.getValor());
		stmt.setLong(3, produtos.getQuantidade());
		
		stmt.setInt(4, produtos.getCodigo());

		stmt.executeUpdate();

	}
	
	public ProdutosDOMAIN buscaporcodigo(ProdutosDOMAIN produtos) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, valor, quantidade, codigoFornecedor ");
		sql.append("FROM fornecedoresproduto ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		stmt.setInt(1, produtos.getCodigo());

		ResultSet resultado = stmt.executeQuery();
		ProdutosDOMAIN retorno = null;

		if (resultado.next()) {

			retorno = new ProdutosDOMAIN();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
			retorno.setValor(resultado.getDouble("valor"));
			retorno.setQuantidade(resultado.getLong("quantidade"));
			
			FornecedoresDAO fornecdoresDAO = new FornecedoresDAO();
			FornecedoresDOMAIN fornecedoresDOMAIN = fornecdoresDAO.buscaPorCodigoFornecedor(resultado.getInt("codigoFornecedor"));
			retorno.setFornecedor(fornecedoresDOMAIN);

		}

		return retorno;

	}
	
	public ArrayList<ProdutosDOMAIN> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, valor, quantidade, codigoFornecedor ");
		sql.append("FROM fornecedoresproduto ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConnectionFactory.conectar();

		PreparedStatement stmt = conexao.prepareStatement(sql.toString());

		ResultSet resultado = stmt.executeQuery();

		ArrayList<ProdutosDOMAIN> lista = new ArrayList<ProdutosDOMAIN>();

		while (resultado.next()) {
			ProdutosDOMAIN retorno = new ProdutosDOMAIN();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
			retorno.setValor(resultado.getDouble("valor"));
			retorno.setQuantidade(resultado.getLong("quantidade"));
			
			FornecedoresDAO fornecedoresDAO = new FornecedoresDAO();
			FornecedoresDOMAIN fornecedoresDOMAIN = fornecedoresDAO.buscaPorCodigoFornecedor(resultado.getInt("codigoFornecedor"));
			retorno.setFornecedor(fornecedoresDOMAIN);
		
			lista.add(retorno);
		}
		return lista;

	}
	
}
