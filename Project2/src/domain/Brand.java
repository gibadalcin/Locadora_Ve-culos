package domain;

public class Brand {
	
	private int Id;
	private String Nome;
	private String Site;
	private String Pais;
	private String Path;
	
	public Brand(String nome,
			String site,
			String pais,
			String path) {
		super();
		Nome = nome;
		Site = site;
		Pais = pais;
		Path = path;
	}
	public Brand(int id,
			String nome,
			String site,
			String pais,
			String path)
	{
		super();
		Id = id;
		Nome = nome;
		Site = site;
		Pais = pais;
		Path = path;
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

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}
	
	public String getPais() {
		return Pais;
	}

	public void setPais(String pais) {
		Pais = pais;
	}

	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}	

}
