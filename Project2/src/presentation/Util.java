package presentation;

import java.util.ArrayList;
import javax.swing.JTable;

import domain.Client;
import dtos.DTOInfoCars;
import dtos.DTORentedCar;

public class Util {

	public static JTable carsListToJTable(ArrayList<DTOInfoCars> carsList) {
		String[] columns = new String[] { "Id", "Modelo", "MarcaId", "Marca", "Ano", "Placa", "Opcionais", "Path" };
		Object[][] data = new Object[carsList.size()][columns.length];

		for (int i = 0; i < carsList.size(); i++) {
			data[i][0] = carsList.get(i).Id;
			data[i][1] = carsList.get(i).Modelo;
			data[i][2] = carsList.get(i).MarcaId;
			data[i][3] = carsList.get(i).Marca;
			data[i][4] = carsList.get(i).Ano;
			data[i][5] = carsList.get(i).Placa;
			data[i][6] = carsList.get(i).Opcionais;
			data[i][7] = carsList.get(i).Path;
		}
		return new JTable(data, columns);
	}

	public static JTable customersListToJTable(ArrayList<Client> customersList) {
		String[] columns = new String[] { "Id", "Nome", "Cpf", "Nascimento", "Cnh", "Rg", "Celular", "Email",
				"Endereco" };
		Object[][] data = new Object[customersList.size()][columns.length];
		for (int i = 0; i < customersList.size(); i++) {
			data[i][0] = customersList.get(i).getId();
			data[i][1] = customersList.get(i).getNome();
			data[i][2] = customersList.get(i).getCpf();
			data[i][3] = customersList.get(i).getNascimento();
			data[i][4] = customersList.get(i).getCnh();
			data[i][5] = customersList.get(i).getRg();
			data[i][6] = customersList.get(i).getCelular();
			data[i][7] = customersList.get(i).getEmail();
			data[i][8] = customersList.get(i).getEndereco();
		}
		return new JTable(data, columns);
	}

	public static JTable carsLeasedListToJTable(ArrayList<DTORentedCar> rentCarsList) {
		String[] columns = new String[] { "Id", "Modelo", "Marca", "Cliente", "Celular", "DataLoc", "DataDevPrev",
				"UsuarioLog" };
		Object[][] data = new Object[rentCarsList.size()][columns.length];
		for (int i = 0; i < rentCarsList.size(); i++) {
			data[i][0] = rentCarsList.get(i).Id;
			data[i][1] = rentCarsList.get(i).Modelo;
			data[i][2] = rentCarsList.get(i).Marca;
			data[i][3] = rentCarsList.get(i).Cliente;
			data[i][4] = rentCarsList.get(i).Celular;
			data[i][5] = rentCarsList.get(i).DataLoc;
			data[i][6] = rentCarsList.get(i).DataDevPrev;
			data[i][7] = rentCarsList.get(i).UsuarioLog;
		}
		return new JTable(data, columns);
	}
}
