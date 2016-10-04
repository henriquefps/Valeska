package negocio;

import java.time.LocalDate;

import negocio.beans.Endereco;
import negocio.beans.Pessoa;
import negocio.beans.Recepcionista;
import repositorio.RepositorioPessoas;

public class GerenciamentoRecepcionista {
	private RepositorioPessoas pessoas;
	private static GerenciamentoRecepcionista instance;
	
	private GerenciamentoRecepcionista(){
		pessoas = RepositorioPessoas.getInstance();
	}
	
	public void cadastrarRecepcionista(String nome, String cpf, String rg, String telefone, 
			String celular, char sexo, Endereco endereco, 
			LocalDate dataDeNascimento, String senha) {
		if (!pessoas.existe(cpf)) {
			pessoas.cadastrar(new Recepcionista(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, senha));
		}
	}
	
	public Recepcionista pesquisarRecepcionista(String cpf){
		Pessoa a = pessoas.pesquisar(cpf);
		if(a instanceof Recepcionista){
			return (Recepcionista) a;
		}
		return null;
	}
	
	public void removerRecepcionista(Recepcionista a){
		if (a != null) {
			pessoas.remover(a);
		}
	}
	public void alterarRecepcionista(Recepcionista a, String nome, String novoCpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, String senha){
		if (a != null) {
			pessoas.atualizar(nome, novoCpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, senha, a);
		}
	}
	public static GerenciamentoRecepcionista getInstance(){
		if(instance == null){
			instance = new GerenciamentoRecepcionista();
		}
		return instance;
	}
}
