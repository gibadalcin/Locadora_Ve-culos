package domain;

public class User {

	private int Id;
	private String Nome;
	private String Email;
	private String Senha;
	private boolean Admin;	
	
	public User(
			int id,
			String nome,
			String email,
			String senha,
			boolean admin) {
		super();
		Id = id;
		Nome = nome;
		Email = email;
		Senha = senha;
		Admin = admin;
	}
	
	public User(
			String nome,
			String email,
			String senha,
			boolean admin) {
		super();
		Nome = nome;
		Email = email;
		Senha = senha;
		Admin = admin;
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public boolean getAdmin() {
		return Admin;
	}

	public void setAdmin(boolean admin) {
		Admin = admin;
	}	
}
