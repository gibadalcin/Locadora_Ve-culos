package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import domain.Brand;
import dtos.DTOComboBox;

public class BrandRepository {
		
		public Brand toSave(Brand brand) {
				
				String scriptSQL = "INSERT INTO `carbrand`(`nome`, `site`, `pais`, `path`) "
						+ "VALUES (?,?,?,?)";
				
				int idReturn = -1;
				
				try {				
				    Connection conn = DbConnect.establishConnection();
					PreparedStatement Stmt = (PreparedStatement) conn.prepareStatement(scriptSQL,Statement.RETURN_GENERATED_KEYS);			
					Stmt.setString(1,brand.getNome());
					Stmt.setString(2,brand.getSite()); 
					Stmt.setString(3,brand.getPais());
					Stmt.setString(4,brand.getPath());
					Stmt.execute();			
					ResultSet rs = Stmt.getGeneratedKeys();
					
					if(rs.next()) {
						idReturn = rs.getInt(1);
						
						Stmt.close();
						conn.close();
					}
					
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
					
				brand.setId(idReturn);
				return brand;	
			}
		
		 public ArrayList <Brand> allBrands() {
			 ArrayList <Brand> brands = new ArrayList <Brand> ();
			 		
			 		String scriptSQL = "SELECT * FROM carbrand ";
			 		
			 		try {
			 			
			 			Connection conn = DbConnect.establishConnection();
			 			Statement stmt = conn.createStatement();
			 			ResultSet resultSet = stmt.executeQuery(scriptSQL);
			 			resultSetToBrands(resultSet, brands);
			 			resultSet.close();
			 			conn.close();
			 			
			 		}catch(Exception e) {
			 			
			 		e.printStackTrace();
			 	}		
			 	return brands;
			 	}
		
		public ArrayList<Brand>allBrandsByName(String nome){
		
			ArrayList <Brand> brandsByName = new ArrayList <Brand> ();			
			String scriptSQL = "SELECT * FROM carbrand where nome like '"+nome+"%'";			
			try {
				Connection conn = DbConnect.establishConnection();
				PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(scriptSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = stmt.executeQuery(scriptSQL);
				resultSetToBrands(resultSet, brandsByName);
				if(brandsByName.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Nenhuma marca corresponde à sua pesquisa!\n");
					brandsByName = allBrands();
				}
				resultSet.close();
				conn.close();
				
			}catch(Exception e) {
				
			e.printStackTrace();
		  }		
	  return brandsByName;
	  }	
		
		 private static void resultSetToBrands(ResultSet resultSet, ArrayList<Brand> brands) throws SQLException {
			 while(resultSet.next()){
				 Brand brandTemp = new Brand(
						 resultSet.getString("nome"),
						 resultSet.getString("site"),
						 resultSet.getString("pais"),
						 resultSet.getString("path") 
						 );
				 
				 brandTemp.setId(resultSet.getInt("id"));
				 brands.add(brandTemp);			 
				 }
			 }
		 
		 public  Brand getBrandById(int brandId) {      			
				String scriptSQL = "SELECT * FROM carbrand where id = '"+brandId+"' ";
				ArrayList <Brand> brandsById = new ArrayList <Brand> ();			
				try {
					Connection conn = DbConnect.establishConnection();
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(scriptSQL);
					ResultSet resultSet = stmt.executeQuery(scriptSQL);
					resultSetToBrands(resultSet, brandsById);
					resultSet.close();
					conn.close();
					
				}catch(Exception e) {
					
				e.printStackTrace();
			}		
				if(brandsById.size() > 0) 
					return brandsById.get(0);	
				
				    return null;
			}
		 
		 public boolean refreshBrands(Brand brandsInEditing) {
				String sql = "UPDATE `carbrand` SET `nome`=?,`site`=?,`pais`=?,`path`=? WHERE id=?";
				try {				
					Connection conn = DbConnect.establishConnection();
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setString(1,brandsInEditing.getNome());
					stmt.setString(2,brandsInEditing.getSite()); 
					stmt.setString(3,brandsInEditing.getPais());
					stmt.setString(4,brandsInEditing.getPath());
					stmt.setInt(5,brandsInEditing.getId());
				    
				    stmt.execute();
					stmt.close();
					conn.close();
					return true;
					
				}catch(Exception e) {		
				e.printStackTrace();
				return false;
			    }
			 }
		
		 public ArrayList<Brand> getEditedBrandId(int id) {
				
		        ArrayList <Brand> editedBrandId = new ArrayList <Brand> ();
				
				String scriptSQL = "SELECT * FROM carbrand where id = '" +id+ "' ";
				
				try {
					Connection conn = DbConnect.establishConnection();
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(scriptSQL);
					ResultSet resultSet = stmt.executeQuery(scriptSQL);
					resultSetToBrands(resultSet, editedBrandId);
					if(editedBrandId.isEmpty()) {
						JOptionPane.showMessageDialog(null,"Nenhuma marca corresponde à sua pesquisa!\n");
						
					}
					resultSet.close();
					conn.close();
					
				}catch(Exception e) {
					
				e.printStackTrace();
				}
			return editedBrandId;
			}
		 
		 public boolean delete(int id) {
				String sql = "delete FROM carbrand where id = '" +id+ "' ";			
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

		public Vector<DTOComboBox> getMarkForComboBox() {
			Vector<DTOComboBox> vector = new Vector<DTOComboBox>();
			
			vector.addElement(new DTOComboBox(-1,"Selecione"));
			try {
				Connection conn = DbConnect.establishConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM carbrand");
				
				while(rs.next()) {
					vector.addElement(new DTOComboBox(rs.getInt("id"), rs.getString("nome")));
				}
				rs.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return vector;
		}

}
