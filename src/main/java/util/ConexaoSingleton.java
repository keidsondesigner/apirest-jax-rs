package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSingleton {
    private static Connection conexao = null;
    
    // Informações da conexão ao banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/base";
    private static final String USER = "root";
    private static final String PASSWORD = "3648Kk..";

    public static Connection getConnection() throws SQLException {
    if (conexao == null || conexao.isClosed()) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Tentando conectar ao banco de dados: " + URL);
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão bem-sucedida!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }
    return conexao;
}
}