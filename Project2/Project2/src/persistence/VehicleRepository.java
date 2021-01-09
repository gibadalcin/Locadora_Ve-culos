package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import domain.Vehicle;
import dtos.DTOInfoCars;

public class VehicleRepository {
	
public Vehicle toSave(Vehicle car) {
		
		String scriptSQL = "INSERT INTO `carrent`(`modelo`, `marcaId`, `ano`, `placa`, `opcionais`, `status`) "
				+ "VALUES (?,?,?,?,?,?)";
		
		int idReturn = -1;
		
		try {
			
		    Connection conn = DbConnect.establishConnection();
			PreparedStatement Stmt = (PreparedStatement) conn.prepareStatement(scriptSQL,Statement.RETURN_GENERATED_KEYS);			
			Stmt.setString(1, car.getModelo());
			Stmt.setInt(2,car.getMarcaId()); 
			Stmt.setInt(3,car.getAno());
			Stmt.setString(4,car.getPlaca());
			Stmt.setString(5,car.getOpcionais());
			Stmt.setInt(6,car.getStatus());
			
			
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
			
		car.setId(idReturn);
		return car;	
	}

	public boolean refreshCars(Vehicle carsInEditing) {
		String sql = "UPDATE `carrent` SET `modelo`=?,`marcaId`=?,`ano`=?,`placa`=?,`opcionais`=?, `status`=? WHERE id = ? ";
		try {
			
			Connection conn = DbConnect.establishConnection();
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, carsInEditing.getModelo());
			stmt.setInt(2,carsInEditing.getMarcaId()); 
			stmt.setInt(3,carsInEditing.getAno());
			stmt.setString(4,carsInEditing.getPlaca());
			stmt.setString(5,carsInEditing.getOpcionais());
			stmt.setInt(6,carsInEditing.getStatus());
			stmt.setInt(7,carsInEditing.getId());
		    
		    stmt.execute();
			stmt.close();
			conn.close();
			return true;
			
		}catch(Exception e) {		
		e.printStackTrace();
		return false;
	    }
	 }	

	public boolean delete(int id) {
		String sql = "delete FROM carrent where id = '" +id+ "' ";
		
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
	
	public Vehicle getCarById(int carId) {      
		
		String sql = "SELECT*FROM carrent where id = '" +carId+ "' ";
		ArrayList <Vehicle> carsById = new ArrayList <Vehicle> ();
		
		try {
			Connection conn = DbConnect.establishConnection();
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			resultSetToCars(resultSet, carsById);
			resultSet.close();
			conn.close();
			
		}catch(Exception e) {
			
		e.printStackTrace();
	}		
		if(carsById.size() > 0) 
			return carsById.get(0);	
		    return null;
	}
	
	private static void resultSetToCars(ResultSet resultSet, ArrayList<Vehicle> cars) throws SQLException {
		 while(resultSet.next()){
			 Vehicle carTemp = new Vehicle(
					 resultSet.getString("modelo"),
					 resultSet.getInt("marcaId"),
					 resultSet.getInt("ano"),
					 resultSet.getString("placa"), 
					 resultSet.getString("opcionais")
					 );
			 
			 carTemp.setId(resultSet.getInt("id"));
			 cars.add(carTemp); 
			 }
		 }
	

	public ArrayList<DTOInfoCars>getAllInfoCarsByModel(String model) {
		
		String sql = "SELECT a.id, a.modelo, a.marcaId, b.nome as Marca, a.ano, a.placa, a.opcionais, b.path FROM carrent a inner JOIN carbrand b on b.id = a.marcaId WHERE modelo like ?  ";
		ArrayList<DTOInfoCars>carsList = new ArrayList<DTOInfoCars>();
		try {
			
			Connection conn = DbConnect.establishConnection();
			
			PreparedStatement prepStat = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			prepStat.setString(1, "" + model + "%" );
			ResultSet resultSet = prepStat.executeQuery();
			resultSetToInfoCars(carsList, resultSet);
			resultSet.close();
			conn.close();
			if(carsList.isEmpty()) {
				
				return null;
			}
		  }catch(Exception e) {
			e.printStackTrace();
		}	
		return carsList;		
	}
	
	
	
public ArrayList<DTOInfoCars>getAllInfoCarsAvailable(String model) {
		
		String sql = "SELECT a.id, a.modelo, a.marcaId, b.nome as Marca, a.ano, a.placa, a.opcionais, b.path FROM carrent a inner JOIN carbrand b on b.id = a.marcaId WHERE modelo like ? AND status = 0";
		ArrayList<DTOInfoCars>carsList = new ArrayList<DTOInfoCars>();
		try {
			
			Connection conn = DbConnect.establishConnection();
			
			PreparedStatement prepStat = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			prepStat.setString(1, "" + model + "%" );
			ResultSet resultSet = prepStat.executeQuery();
			resultSetToInfoCars(carsList, resultSet);
			resultSet.close();
			conn.close();
		  }catch(Exception e) {
			e.printStackTrace();
		}	
		return carsList;		
	}

	private void resultSetToInfoCars(ArrayList<DTOInfoCars> carsList, ResultSet resultSet) throws 	SQLException {
		while(resultSet.next()) {
			
			DTOInfoCars carsTemp = new DTOInfoCars();		
			
			carsTemp.Id = resultSet.getInt("id");
			carsTemp.Modelo = resultSet.getString("modelo");
			carsTemp.MarcaId = resultSet.getInt("marcaId");
			carsTemp.Marca = resultSet.getString("marca");
			carsTemp.Ano = resultSet.getInt("ano");
			carsTemp.Placa = resultSet.getString("placa");
			carsTemp.Opcionais = resultSet.getString("opcionais");
			carsTemp.Path = resultSet.getString("path");
			
			carsList.add(carsTemp);
		}
		
	}
}
