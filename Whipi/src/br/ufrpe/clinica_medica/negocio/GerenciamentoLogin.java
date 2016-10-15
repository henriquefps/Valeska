package br.ufrpe.clinica_medica.negocio;

import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;
import br.ufrpe.clinica_medica.repositorio.RepositorioPessoas;

public class GerenciamentoLogin {

	private IRepositorioPessoas pessoas;

	public GerenciamentoLogin() {
		this.pessoas = RepositorioPessoas.getInstance();
	}
	
	public Pessoa efetuarLogin(String cpf, String senha) {
		Pessoa individuo = pessoas.pesquisar(cpf);
		boolean senhaCorreta = false;
		if (individuo != null) {
			if (individuo instanceof Medico && ((Medico) individuo).getSenha().equals(senha)) {
				senhaCorreta = true;
			} else if (individuo instanceof Recepcionista && ((Recepcionista) individuo).getSenha().equals(senha)) {
				senhaCorreta = true;
			} else if (senha.equals("admin") && cpf.equals("admin")) {
				senhaCorreta = true;
			}
		}
		if (!senhaCorreta) {
			individuo = null;
		}
		return individuo;
	}
	
}
