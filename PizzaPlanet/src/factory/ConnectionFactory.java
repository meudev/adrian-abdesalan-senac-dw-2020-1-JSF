package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String BANCODADOS = "dbpizza";
	private static final String CONEXAO = "jdbc:mysql://localhost:3306/" + BANCODADOS
			+ "?useTimezone=true&serverTimezone=UTC&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection conectar() throws SQLException {
		
		try {
			Connection conn = null;
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONEXAO, USER, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do Driver não foi encontrada. Causa: " + e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("Erro ao obter a Connection. Causa: " + e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			Connection conexao = ConnectionFactory.conectar();
			System.out.println("Conectado com Sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro:"+e);
			
		}
	}
	

}