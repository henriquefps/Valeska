package negocio;

import java.time.LocalDate;

import negocio.beans.Endereco;
import negocio.beans.Paciente;
import negocio.beans.Pessoa;
import repositorio.RepositorioPessoas;

public class GerenciamentoPaciente {
	private RepositorioPessoas pessoas;
	private static GerenciamentoPaciente instance;
	private GerenciamentoPaciente() {
		pessoas = RepositorioPessoas.getInstance();
	}

	public void cadastrarPaciente(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento) {
		if (!pessoas.existe(cpf)) {
			pessoas.cadastrar(new Paciente(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento));
		}
	}

	public Paciente pesquisarPaciente(String cpf) {
		Pessoa a = pessoas.pesquisar(cpf);
		if (a instanceof Paciente) {
			return (Paciente) a;
		}
		return null;
	}

	public void removerPaciente(Paciente a){
		if(a != null){
			pessoas.remover(a);
		}
	}

	public void alterarPaciente(Paciente a, String nome, String novoCpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento){
		if(a != null){
			pessoas.atualizar(nome, novoCpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, (Paciente) a);
		}
	}
	
	public static GerenciamentoPaciente getInstance(){
		if(instance == null){
			instance = new GerenciamentoPaciente();
		}
		return instance;
	}
}
