package negocio.beans;

public class Recepcionista {
	
	private String nome;
	private String cpf;
	private String telefone;
	private String rg;
	private char sexo;
	private int idade;
	private Login login;

	public Recepcionista(String nome, String cpf, String rg, String telefone, char sexo, int idade, Login login) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
		this.sexo = sexo;
		this.idade = idade;
		this.login = login;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public boolean equals(Recepcionista a) {
		boolean resultado =  false;
		String nome = a.getNome();
		String cpf = a.getCpf();
		String rg = a.getRg();

		if ( (nome != null) && (cpf != null) && (rg != null) && (this.nome == nome) && (this.cpf == cpf) || (this.rg == rg))
		{
			resultado = true;
		}

		return resultado;
	}
	
	public String toString() {
		String resultado;
		String quebraLinha = System.getProperty("line.separator"); 
		
		resultado = "Nome - " + this.nome + quebraLinha + "cpf - " + this.cpf + quebraLinha
				+ "Rg - " + this.rg + quebraLinha + "Telefone - " + this.telefone + quebraLinha
				+ "Sexo - " + this.sexo + quebraLinha + "Idade - " + this.idade + quebraLinha;
				
		
		return resultado;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}



}
