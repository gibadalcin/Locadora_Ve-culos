package domain;


public class Vehicle {
		private int Id;
		private String Modelo;
		private int MarcaId;
		private int Ano;
		private String Placa;
		private String Opcionais;
		private int Status;
		
		public Vehicle(
				String modelo,
				int marcaId,
				int ano,
				String placa,
				String opcionais) {
			super();
			Modelo = modelo;
			MarcaId = marcaId;
			Ano = ano;
			Placa = placa;
			Opcionais = opcionais;
		}
		public Vehicle(
				int id,
				String modelo,
				int marcaId,
				int ano,
				String placa,
				String opcionais)
		{
			super();
			Id = id;
			Modelo = modelo;
			MarcaId = marcaId;
			Ano = ano;
			Placa = placa;
			Opcionais = opcionais;
			
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getModelo() {
			return Modelo;
		}

		public void setModelo(String modelo) {
			Modelo = modelo;
		}

		public int getMarcaId() {
			return MarcaId;
		}

		public void setMarcaId(int marcaId) {
			MarcaId = marcaId;
		}
		
		public int getAno() {
			return Ano;
		}

		public void setAno(int ano) {
			Ano = ano;
		}

		public String getPlaca() {
			return Placa;
		}

		public void setPlaca(String placa) {
			Placa = placa;
		}

		public String getOpcionais() {
			return Opcionais;
		}

		public void setOpcionais(String opcionais) {
			Opcionais = opcionais;
		}
		
		public void leased() {
			setStatus(1);
		}
		
		public void returned() {
			setStatus(0);
		}
		public int getStatus() {
			return Status;
		}
		public void setStatus(int status) {
			Status = status;
		}
		
		

}
