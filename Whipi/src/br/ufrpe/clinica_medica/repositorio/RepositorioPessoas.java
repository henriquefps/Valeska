package br.ufrpe.clinica_medica.repositorio;

import java.time.LocalDate;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.*;

public class RepositorioPessoas {

	private ArrayList<Pessoa> repositorio;

	private static RepositorioPessoas instance;

	private RepositorioPessoas() {
		this.repositorio = new ArrayList<Pessoa>();
	}

	public static RepositorioPessoas getInstance() {
		if (instance == null) {
			instance = new RepositorioPessoas();
		}
		return instance;
	}

	public ArrayList<Medico> getListaMedicos() {
		ArrayList<Medico> lista = new ArrayList<Medico>();
		for (int i = 0; i < repositorio.size(); i++) {
			if (this.repositorio.get(i) instanceof Medico) {
				lista.add((Medico) this.repositorio.get(i));
			}
		}
		if (lista.size() > 0) {
			return lista;
		}
		return null;
	}

	public void cadastrar(Pessoa cadastro) {
		repositorio.add(cadastro);
	}

	public void remover(Pessoa remover) {
		repositorio.remove(remover);
	}

	public Pessoa pesquisar(String cpf) {
		Pessoa achou = null;
		for (int i = 0; i < repositorio.size() && achou == null; i++) {
			if (repositorio.get(i).getCpf().equals(cpf)) {
				achou = repositorio.get(i);
			}
		}
		return achou;
	}

	public boolean existe(Pessoa pessoa) {
		boolean achou = false;
		if (pesquisar(pessoa.getCpf()) != null) {
			achou = true;
		}
		return achou;
	}

	public boolean existe(String cpf) {
		boolean achou = false;
		if (pesquisar(cpf) != null) {
			achou = true;
		}
		return achou;
	}

	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, String senha, Medico medico) {
		medico.setNome(nome);
		medico.setCpf(cpf);
		medico.setRg(rg);
		medico.setTelefone(telefone);
		medico.setCelular(celular);
		medico.setSexo(sexo);
		medico.setEndereco(endereco);
		medico.setDataDeNascimento(dataDeNascimento);
		medico.setNumCRM(numCRM);
		medico.setSenha(senha);
	}

	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, Paciente paciente) {
		paciente.setNome(nome);
		paciente.setCpf(cpf);
		paciente.setRg(rg);
		paciente.setTelefone(telefone);
		paciente.setCelular(celular);
		paciente.setSexo(sexo);
		paciente.setEndereco(endereco);
		paciente.setDataDeNascimento(dataDeNascimento);
	}

	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, String senha, Recepcionista recepcionista) {
		recepcionista.setNome(nome);
		recepcionista.setCpf(cpf);
		recepcionista.setRg(rg);
		recepcionista.setTelefone(telefone);
		recepcionista.setCelular(celular);
		recepcionista.setSexo(sexo);
		recepcionista.setEndereco(endereco);
		recepcionista.setDataDeNascimento(dataDeNascimento);
		recepcionista.setSenha(senha);
	}

}
