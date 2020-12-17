package domain;


public class Client {
	
	private int Id;
	private String Nome;
	private String Cpf;
	private String Nascimento;
	private String Cnh;
	private String Rg;
	private String Celular;
	private String Email;
	private String Endereco;

	public Client(String nome,
			String cpf, 
			String nascimento, 
			String cnh, 
			String rg, 
			String celular, 
			String email,
			String endereco) {
		super();
		Nome = nome;
		Cpf = cpf;
		Nascimento = nascimento;
		Cnh = cnh;
		Rg = rg;
		Celular = celular;
		Email = email;
		Endereco = endereco;	
	}
		public Client(int id,
				String nome, 
				String cpf, 
				String nascimento, 
				String cnh, 
				String rg, 
				String celular, 
				String email,
				String endereco
				) {
			super();
			Id = id;
			Nome = nome;
			Cpf = cpf;
			Nascimento = nascimento;
			Cnh = cnh;
			Rg = rg;
			Celular = celular;
			Email = email;
			Endereco = endereco;
		}
		
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		
		public String getNome() {
			return Nome;
		}
		public void setNome(String nome) {
			Nome = nome;
		}
		
		public String getCpf() {
			return Cpf;
		}
		public void setCpf(String cpf) {
			Cpf = cpf;
		}
		
		public String getNascimento() {
			return Nascimento;
		}
		public void setNascimento(String nascimento) {
			Nascimento = nascimento;
		}
		
		public String getCnh() {
			return Cnh;
		}
		public void setCnh(String cnh) {
			Cnh = cnh;
		}
		
		public String getRg() {
			return Rg;
		}
		public void setRg(String rg) {
			Rg = rg;
		}
		
		public String getCelular() {
			return Celular;
		}
		public void setCelular(String celular) {
			Celular = celular;
		}
		
		
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		
		public String getEndereco() {
			return Endereco;
		}
		public void setEndereco(String endereco) {
			Endereco = endereco;
		}
}
