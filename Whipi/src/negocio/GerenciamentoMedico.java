package negocio;

import repositorio.*;

import java.time.LocalDate;

import negocio.beans.*;

public class GerenciamentoMedico {
	private RepositorioPessoas pessoas;
	private RepositorioLogin logins;

	public GerenciamentoMedico() {
		this.pessoas = RepositorioPessoas.getInstance();
		this.logins = RepositorioLogin.getInstance();
	}

	public void cadastrarMedico(String nome, String cpf, String rg, String telefone, String celular, char sexo, Endereco endereco,
			LocalDate dataDeNascimento, int numCRM, String senha) {
		Medico novo = new Medico(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM, senha);
		if(!pessoas.existe(novo)){
			pessoas.cadastrar(novo);
			logins.cadastrarLogin(novo.getLogin());
		}
	}
	
	public void removerMedico(String cpf) {
		Pessoa p = this.pessoas.pesquisar(cpf);
		if(p != null) {
			this.pessoas.remover(p);
		} else {
			//Usar exceções no futuro
		}
	}
	public void alterarMedico(String cpf, String nome, String novocpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, String senha){
		Medico aux = pesquisarMedico(cpf);
		pessoas.atualizar(nome, novocpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM, senha, aux);
		Login doMedico = logins.pesquisarLogin(aux.getCpf());
		doMedico.setCpf(novocpf);
		doMedico.setSenha(senha);
	}
	
	public Medico pesquisarMedico(String cpf){
		Pessoa aux = pessoas.pesquisar(cpf);
		if(aux instanceof Medico){
			return (Medico)aux;
		}
		return null;
	}
}
