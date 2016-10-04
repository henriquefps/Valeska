package repositorio;

import negocio.beans.*;

import java.time.LocalDate;
import java.util.ArrayList;

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

	public void cadastrar(Pessoa cadastro) {
		repositorio.add(cadastro);
	}

	public void remover(Pessoa remover) {
		repositorio.remove(remover);
	}

	public Pessoa pesquisar(String cpf) {
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i).getCpf() == cpf) {
				return repositorio.get(i);
			}
		}
		return null;
	}

	public boolean existe(String cpf) {
		if (pesquisar(cpf) != null) {
			return true;
		} else {
			return false;
		}
	}

	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha, Medico medico) {
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
