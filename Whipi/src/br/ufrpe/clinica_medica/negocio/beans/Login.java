package br.ufrpe.clinica_medica.negocio.beans;

public class Login {
	
	private String cpf;
	private String senha;
	private int tipoDeLogin;// 1 para médico, 2 para recepcionista, 3 para
							// administrador

	public Login(String cpf, String senha, int tipoDeLogin) {
		this.cpf = cpf;
		this.senha = senha;
		this.tipoDeLogin = tipoDeLogin;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTipoDeLogin() {
		return tipoDeLogin;
	}

	public void setTipoDeLogin(int tipoDeLogin) {
		this.tipoDeLogin = tipoDeLogin;
	}
}
