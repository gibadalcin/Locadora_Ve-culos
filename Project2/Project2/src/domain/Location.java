package domain;

import java.util.Date;

public class Location {
	
	private int Id;
	private int CarId; //fk
	private int ClienteId; //fk
	private Date DataDevPrev;
	private int UsuarioId; //fk
	private Date DataLoc;
	private Date DataDev;

	public Location(int id, int carId, int clientId, Date dataDevPrev, int userId, Date dataLoc, Date dataDev) {
		super();
		Id = id;
		CarId = carId;
		ClienteId = clientId;
		DataDevPrev = dataDevPrev;
		UsuarioId = userId;
		DataLoc = dataLoc;
		DataDev = dataDev;
	}
	
	public Location(int carId, int clientId, Date dataDevPrev, int userId, Date dataLoc) {
		super();
		CarId = carId;
		ClienteId = clientId;
		DataDevPrev = dataDevPrev;
		UsuarioId = userId;
		DataLoc = dataLoc;
	}


	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getCarId() {
		return CarId;
	}

	public void setCarId(int carId) {
		CarId = carId;
	}

	public int getClientId() {
		return ClienteId;
	}

	public void setClientId(int clientId) {
		ClienteId = clientId;
	}

	public Date getDataDevPrev() {
		return DataDevPrev;
	}

	public void setDataDevPrev(Date dataDevPrev) {
		DataDevPrev = dataDevPrev;
	}

	public int getUserId() {
		return UsuarioId;
	}

	public void setUserId(int userId) {
		UsuarioId = userId;
	}

	public Date getDataLoc() {
		return DataLoc;
	}

	public void setDataLoc(Date dataLoc) {
		DataLoc = dataLoc;
	}

	public Date getDataDev() {
		return DataDev;
	}

	public void setDataDev(Date dataDev) {
		DataDev = dataDev;
	}
	
	public void finish() {
		DataDev = new Date();
	}
}
