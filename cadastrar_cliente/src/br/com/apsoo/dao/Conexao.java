package br.com.apsoo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3310/seu_banco";
    private static final String USUARIO = "root";
    private static final String SENHA = "123";

    private Conexao() {
    }

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static void fechar(Connection conexao) throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
