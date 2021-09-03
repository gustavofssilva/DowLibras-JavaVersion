/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	// criar atributos
	public static String bancoDados, usuario, senha, servidor;
	public static int porta;

	// variavel de conexao
	public static java.sql.Connection conexao = null;

	// define valores padrão
	static {
		// mysql
		bancoDados = "tradutor_bd";
		usuario = "root";
		senha = "senha";
		servidor = "localhost";
		porta = 3306;

	}

	// métodos
	public static void conectar() throws SQLException {
		// mysql
		String url = "jdbc:mysql://" + servidor + ":" + porta + "/" + bancoDados;

		/*
		 * sqlServer String url = "jdbc:sqlserver://" + servidor + ":" + porta +
		 * ";databaseName=" + bancoDados;
		 */
		conexao = DriverManager.getConnection(url, usuario, senha);
	}

	public static void desconectar() throws SQLException {
		// throws manda o erro para o main para ser tratado com try catch lÃ¡
		conexao.close(); // metodo para desconectar o banco
	}

	public static java.sql.Connection obterConexao() throws SQLException { // tratamento de excecao
		if (conexao == null) // nao abriu a conexao
			throw new SQLException("Conexão está fechada..");
		else
			return conexao;
	}
}
