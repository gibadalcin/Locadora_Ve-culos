package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	
	public static Connection establishConnection()
			throws ClassNotFoundException, SQLException{
				
				Connection connection = null;
				
				try {
					//chamando a classe em tempo de execução
					Class.forName("com.mysql.jdbc.Driver");
					
					//string de conexão
					String bancoURL = "jdbc:mysql://localhost:3306/project_two";
					
					//realiza a conexão
					connection = (Connection)
							DriverManager.getConnection
							(bancoURL, "root","");
				}catch (SQLException exception) {
					exception.printStackTrace();
				}
				return connection;
			}
}
