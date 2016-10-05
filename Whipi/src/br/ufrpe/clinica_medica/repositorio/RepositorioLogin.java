package br.ufrpe.clinica_medica.repositorio;

import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.Login;

public class RepositorioLogin {
	
	private ArrayList<Login> todosOsLogins;
	private static RepositorioLogin instance;

	private RepositorioLogin() {
		todosOsLogins = new ArrayList<Login>();
	}

	public void cadastrarLogin(Login login) {
		todosOsLogins.add(login);
	}

	public void removerLogin(Login login) {
		todosOsLogins.remove(login);
	}
	
	public Login pesquisarLogin(String cpf) {
		Login achou = null;
		for (int i = 0; i < todosOsLogins.size() && achou == null; i++) {
			if (todosOsLogins.get(i).getCpf() == cpf) {
				achou = todosOsLogins.get(i);
			}
		}
		return achou;
	}

	public void atualizarLogin(Login login, String cpf, String senha) {
		login.setCpf(cpf);
		login.setSenha(senha);
	}

	public static RepositorioLogin getInstance() {
		if (instance == null) {
			instance = new RepositorioLogin();
		}
		return instance;
	}
}
