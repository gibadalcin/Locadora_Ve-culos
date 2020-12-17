package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import domain.Client;
import javax.swing.JOptionPane;

public class ClientRepository {
	
	public int empty;



	public Client toSave(Client client) {
		
		String scripSql = "INSERT INTO `carclient`(`nome`, `cpf`, `nascimento`, `cnh`, `rg`, `celular`, `email`, `endereco`) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
				
				int idReturn = -1;
			
				try {
					Connection conn = DbConnect.establishConnection();
					PreparedStatement Stmt = (PreparedStatement) conn.prepareStatement(scripSql,Statement.RETURN_GENERATED_KEYS);
					Stmt.setString(1,client.getNome());
					Stmt.setString(2,client.getCpf());
					Stmt.setString(3,client.getNascimento());
					Stmt.setString(4,client.getCnh());
					Stmt.setString(5,client.getRg());
					Stmt.setString(6,client.getCelular());
					Stmt.setString(7,client.getEmail());
					Stmt.setString(8,client.getEndereco());
					Stmt.execute();
					ResultSet rs = Stmt.getGeneratedKeys();
					
					if(rs.next()) {
						idReturn = rs.getInt(1);
						
						Stmt.close();
						conn.close();
					}
				}catch(ClassNotFoundException e) {
					e.printStackTrace();			
				}catch(SQLException e) {
					e.printStackTrace();
				}
		     client.setId(idReturn);
		return client;
	}
	
	public ArrayList <Client> allCustomers(){
		ArrayList<Client> customers = new ArrayList<Client>();
		String scriptSQL = "SELECT *FROM  carclient";
		try {
			Connection conn = DbConnect.establishConnection();
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(scriptSQL);
			resultSetToCustomers(resultSet, customers);
			resultSet.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	private static void resultSetToCustomers(ResultSet resultSet, ArrayList<Client> customers) throws SQLException {
		while(resultSet.next()) {
			Client clientTemp = new Client(
					resultSet.getString("nome"),
					resultSet.getString("cpf"),
					resultSet.getString("nascimento"),
					resultSet.getString("cnh"),
					resultSet.getString("rg"),
					resultSet.getString("celular"),
					resultSet.getString("email"),
					resultSet.getString("endereco")
					);
			
			clientTemp.setId(resultSet.getInt("id"));
			customers.add(clientTemp);			
		}
	}
	
	
	public Client getClientById(int clientId) {
		String scriptSQL = " SELECT * FROM carclient WHERE id = '"+clientId+"'";
		ArrayList<Client> customersById = new ArrayList<Client>();
		try {
			Connection conn = DbConnect.establishConnection();
			PreparedStatement stmt = (PreparedStatement)conn.prepareStatement(scriptSQL);
			ResultSet resultSet = stmt.executeQuery(scriptSQL);
			resultSetToCustomers(resultSet, customersById);
			resultSet.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(customersById.size() > 0) 
			return customersById.get(0);		
		
		return null;
	}
	
	public boolean refreshCustomers(Client customersInEditing) {
	String sql = "UPDATE `carclient` SET `nome`=?,`cpf`=?,`nascimento`=?,`cnh`=?,`rg`=?,`celular`=?,`email`=?,`endereco`=? WHERE id=?";
	
	try {
		Connection conn = DbConnect.establishConnection();
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1, customersInEditing.getNome());
		stmt.setString(2, customersInEditing.getCpf());
		stmt.setString(3, customersInEditing.getNascimento());
		stmt.setString(4, customersInEditing.getCnh());
		stmt.setString(5, customersInEditing.getRg());
		stmt.setString(6, customersInEditing.getCelular());
		stmt.setString(7, customersInEditing.getEmail());
		stmt.setString(8, customersInEditing.getEndereco());
		stmt.setInt(9, customersInEditing.getId());
		
		stmt.execute();
		stmt.close();
		conn.close();
		return true;
		
	}catch(Exception e) {
		e.printStackTrace();
	  }	
	return false;
	}
	
	
	public ArrayList<Client> getEditedClientId(int id){
		ArrayList<Client> editedClientId = new ArrayList<Client>();
		
		String sql = "SELECT * FROM carclient WHERE id = '"+id+"' "; 
		
		try {
			Connection conn = DbConnect.establishConnection();
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			resultSetToCustomers(resultSet, editedClientId);
			if(editedClientId.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nenhum registro corresponde à sua pesquisa");		
			}
			resultSet.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return editedClientId;
	}
	
	public ArrayList<Client> allCustomersByCpf(String cpf){
		ArrayList<Client> customersByCpf = new ArrayList<Client>();
		
		String sql = "SELECT * FROM carclient WHERE cpf LIKE '"+cpf+"'";
		
		try {
			Connection conn = DbConnect.establishConnection();
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			resultSetToCustomers(resultSet, customersByCpf);
			if(customersByCpf.isEmpty()) {
				empty = 1;
				customersByCpf = allCustomers();
			}
			resultSet.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customersByCpf;
	}

	

	public boolean delete(int id) {
		 
				String sql = "delete FROM carclient where id = '" +id+ "' ";			
				try {		
					Connection conn =DbConnect.establishConnection();
					PreparedStatement Stmt = (PreparedStatement) conn.prepareStatement(sql);
					Stmt.execute();
					Stmt.close();
					conn.close();
					return true;
					
				}catch(Exception e) {
				  e.printStackTrace();
			return false;
		}
	}
	
}
