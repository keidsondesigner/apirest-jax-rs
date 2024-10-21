package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSingleton {
    private static Connection conexao = null;
    
    // Informações da conexão ao banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/slq_impressionador";
    private static final String USER = "root";
    private static final String PASSWORD = "3648Kk..";

    public static Connection getConnection() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver do MySQL
                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new SQLException("Erro ao conectar ao banco de dados");
            }
        }
        return conexao;
    }
}