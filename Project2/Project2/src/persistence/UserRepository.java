package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import domain.User;

public class UserRepository {

	public User toSave(User user) {	
		String scriptSQL = "INSERT INTO caruser (nome, email, senha, admin)"
				+ "VALUES (?,?,?,?)";	
		int idReturn = -1;	
		try {
			Connection conn = DbConnect.establishConnection();
			PreparedStatement prepStmt = (PreparedStatement)conn.prepareStatement(scriptSQL,
					Statement.RETURN_GENERATED_KEYS);		
			prepStmt.setString(1, user.getNome());
			prepStmt.setString(2, user.getEmail());
			prepStmt.setString(3, user.getSenha());
			prepStmt.setBoolean(4, user.getAdmin());			
			prepStmt.execute();	
			ResultSet rs = prepStmt.getGeneratedKeys();		
			if(rs.next()) {
				idReturn = rs.getInt(1);
			}		
			prepStmt.close();
			conn.close();		
		}catch(Exception exception) {
			exception.printStackTrace();		
		}	
		user.setId(idReturn);
		return user;		
	}
	
	
	public ArrayList<User> getAllUsers(){
		
		ArrayList<User> users = new ArrayList<User>();	
		String scriptSQL = "SELECT * FROM caruser";		
		try {
			Connection conn = DbConnect.establishConnection();
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(scriptSQL);
			ResultSetToListUsers(resultSet,users);
			resultSet.close();
			conn.close();		
		}catch(Exception e) {
			e.printStackTrace();
		}
	return users;	
	}

	
	private void ResultSetToListUsers(ResultSet resultSet, ArrayList<User> users) throws SQLException{
		while(resultSet.next()) {
			 users.add(new User(
			 resultSet.getInt("Id"),
			 resultSet.getString("Nome"),
			 resultSet.getString("Email"),
			 resultSet.getString("Senha"),
			 resultSet.getBoolean("Admin")));				  
		}
	}
	
	
	public User getUserById(int userId) {
		String scriptSQL = "SELECT * FROM caruser WHERE id = ?";
		ArrayList<User> users = new ArrayList<User>();
		try {
			Connection conn = DbConnect.establishConnection();	
			PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(scriptSQL);
			prepStmt.setInt(1, userId);
			ResultSet resultSet = prepStmt.executeQuery();
			ResultSetToListUsers(resultSet, users);
			resultSet.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}
	
	
	public boolean updateUser(User user) {	
		String scriptSQL = "UPDATE `caruser` SET `nome`=?,`email`=?,`senha`=?,`admin`=? WHERE id=?";	
		try {		
			Connection conn = DbConnect.establishConnection();
			PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(scriptSQL);		
			prepStmt.setString(1, user.getNome());
			prepStmt.setString(2, user.getEmail());
			prepStmt.setString(3, user.getSenha());
			prepStmt.setBoolean(4, user.getAdmin());
			prepStmt.setInt(5, user.getId());		
			prepStmt.execute();
			prepStmt.close();
			conn.close();		
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean delete(int userId) {	
		String scriptSQL = "DELETE FROM caruser WHERE id = ?";	
		try {
			Connection conn = DbConnect.establishConnection();
			PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(scriptSQL);		
			prepStmt.setInt(1,  userId);
			prepStmt.execute();
			prepStmt.close();
			conn.close();	
			return true;	
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public User getUserByEmailOrPassword(String email, String senha) {
		String scriptSQL = "SELECT * FROM caruser WHERE email = ? AND senha = ?";
		ArrayList<User> users = new ArrayList<User>();
		try {
			Connection conn = DbConnect.establishConnection();	
			PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(scriptSQL);
			prepStmt.setString(1, email);
			prepStmt.setString(2, senha);
			ResultSet resultSet = prepStmt.executeQuery();
			ResultSetToListUsers(resultSet, users);
			resultSet.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}


public ArrayList<User> getAllUsersAdmin(){
		
		ArrayList<User> users = new ArrayList<User>();	
		String scriptSQL = "SELECT * FROM caruser WHERE admin = 1";		
		try {
			Connection conn = DbConnect.establishConnection();
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(scriptSQL);
			ResultSetToListUsers(resultSet,users);
			resultSet.close();
			conn.close();		
		}catch(Exception e) {
			e.printStackTrace();
		}
	return users;	
	}
}
