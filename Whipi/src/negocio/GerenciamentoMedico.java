package negocio;

import repositorio.*;

import java.time.LocalDate;

import negocio.beans.*;

public class GerenciamentoMedico {
	private RepositorioPessoas pessoas;

	public GerenciamentoMedico() {
		this.pessoas = RepositorioPessoas.getInstance();
	}

	public void cadastrarMedico(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, String senha) {
		if (!pessoas.existe(cpf)) {
			pessoas.cadastrar(new Medico(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM, senha));
		}
	}

	public void removerMedico(Medico medico) {
		if (medico != null) {
			this.pessoas.remover(medico);
		} else {
			// Usar exceções no futuro
		}
	}

	public void alterarMedico(Medico medico, String nome, String novocpf, String rg, String telefone, String celular,
			char sexo, Endereco endereco, LocalDate dataDeNascimento, int numCRM, String senha) {
		if (medico != null) {
			pessoas.atualizar(nome, novocpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM, senha,
					medico);
		}
	}

	public Medico pesquisarMedico(String cpf) {
		Pessoa aux = pessoas.pesquisar(cpf);
		if (aux instanceof Medico) {
			return (Medico) aux;
		}
		return null;
	}
}
