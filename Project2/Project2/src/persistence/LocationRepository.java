package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.Location;
import dtos.DTORentedCar;

import java.sql.PreparedStatement;

public class LocationRepository {

	public Location toSave(Location location) {

		String scriptSQL = "INSERT INTO carlocation" + "(carId, clienteId, dataDevPrev, usuarioId, dataLoc) "
				+ "VALUES (?,?,?,?,?)";

		int idRetorno = -1;

		try {
			Connection conn = DbConnect.establishConnection();
			PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(scriptSQL,
					Statement.RETURN_GENERATED_KEYS);

			prepStmt.setInt(1, location.getCarId());
			prepStmt.setInt(2, location.getClientId());
			prepStmt.setDate(3, new java.sql.Date(location.getDataDevPrev().getTime()));
			prepStmt.setInt(4, location.getUserId());
			prepStmt.setDate(5, new java.sql.Date(location.getDataLoc().getTime()));

			prepStmt.execute();

			ResultSet rs = prepStmt.getGeneratedKeys();

			if (rs.next()) {
				idRetorno = rs.getInt(1);
			}
			prepStmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		location.setId(idRetorno);
		return location;
	}

	public ArrayList<DTORentedCar> getRentedCars() {

		ArrayList<DTORentedCar> carsLoc = new ArrayList<DTORentedCar>();

		String scriptSQL = "SELECT a.id as Id,b.modelo,e.nome as Marca,c.nome as Cliente,c.celular as Celular,"
				+ "DATE_FORMAT(a.dataLoc, '%d/%m/%Y') as DataLoc,DATE_FORMAT(a.dataDevPrev, '%d/%m/%Y') as DataDevPrev,"
				+ "d.nome as UsuarioLog FROM carlocation a INNER JOIN carrent b on b.id = a.carId "
				+ "INNER JOIN carclient c on c.id = a.clienteId INNER JOIN caruser d on d.id = a.usuarioId "
				+ "INNER JOIN carbrand e on e.id = b.marcaId where a.dataDev is null";

		try {
			Connection conn = DbConnect.establishConnection();
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(scriptSQL);
			ResultSetToCarsLoc(resultSet, carsLoc);
			resultSet.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carsLoc;
	}

	private void ResultSetToCarsLoc(ResultSet resultSet, ArrayList<DTORentedCar> carsLoc) throws SQLException {
		while (resultSet.next()) {

			DTORentedCar carsLocTemp = new DTORentedCar();

			carsLocTemp.Id = resultSet.getInt("Id");
			carsLocTemp.Modelo = resultSet.getString("Modelo");
			carsLocTemp.Marca = resultSet.getString("Marca");
			carsLocTemp.Cliente = resultSet.getString("Cliente");
			carsLocTemp.Celular = resultSet.getString("Celular");
			carsLocTemp.DataLoc = resultSet.getString("DataLoc");
			carsLocTemp.DataDevPrev = resultSet.getString("DataDevPrev");
			carsLocTemp.UsuarioLog = resultSet.getString("UsuarioLog");

			carsLoc.add(carsLocTemp);
		}
	}

    public boolean update(Location location) {
    	String sql = "UPDATE `carlocation` SET `carId`=?,"
    			+ "`clienteId`=?,"
    			+ "`dataDevPrev`=?,"
    			+ "`usuarioId`=?,"
    			+ "`dataLoc`=?,"
    			+ "`dataDev`=?"
    			+ " WHERE id=?";
    	try {
    		Connection conn = DbConnect.establishConnection();
			PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(sql);
			prepStmt.setInt(1, location.getCarId());
			prepStmt.setInt(2, location.getClientId());
			prepStmt.setDate(3, new java.sql.Date(location.getDataDevPrev().getTime()));
			prepStmt.setInt(4, location.getUserId());
			prepStmt.setDate(5, new java.sql.Date(location.getDataLoc().getTime()));
			prepStmt.setDate(6, new java.sql.Date(location.getDataDev().getTime()));
			prepStmt.setInt(7, location.getId());
			prepStmt.execute();
			prepStmt.close();
			conn.close();
			return true;
   
    	}catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }

    public Location getRentalById(int id) {	
    	String sql = "SELECT * FROM carlocation WHERE id = ?";
    	ArrayList<Location> leases = new ArrayList<Location>();
    	try {
    		Connection conn = DbConnect.establishConnection();
			PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement(sql);
			prepStmt.setInt(1, id);
			ResultSet resultSet = prepStmt.executeQuery();
			ResultSetToRentalList(resultSet, leases);
			resultSet.close();
			conn.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	if (leases.size() > 0)
			return leases.get(0);
		return null;	
    }

	private void ResultSetToRentalList(ResultSet resultSet, ArrayList<Location> leases) throws SQLException{
		while (resultSet.next()) {
			Location location = new Location(resultSet.getInt("id"),
					resultSet.getInt("carId"),
					resultSet.getInt("clienteId"),
					resultSet.getDate("dataDevPrev"),
					resultSet.getInt("usuarioId"),
					resultSet.getDate("dataLoc"),
					resultSet.getDate("dataDev"));
			leases.add(location);
		}	
	}
}
