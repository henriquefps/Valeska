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
		Pessoa pesquisa = null;
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i).equals(cpf)) {
				pesquisa = repositorio.get(i);
				break;
			}
		}
		return pesquisa;
	}

	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int id, int numCRM, Login login, Medico medico) {
		medico.setNome(nome);
		medico.setCpf(cpf);
		medico.setRg(rg);
		medico.setTelefone(telefone);
		medico.setCelular(celular);
		medico.setSexo(sexo);
		medico.setEndereco(endereco);
		medico.setDataDeNascimento(dataDeNascimento);
		medico.setId(id);
		medico.setNumCRM(numCRM);
		medico.setLogin(login);
	}

	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int id, Paciente paciente) {
		paciente.setNome(nome);
		paciente.setCpf(cpf);
		paciente.setRg(rg);
		paciente.setTelefone(telefone);
		paciente.setCelular(celular);
		paciente.setSexo(sexo);
		paciente.setEndereco(endereco);
		paciente.setDataDeNascimento(dataDeNascimento);
		paciente.setId(id);
	}

	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, Login login, Recepcionista recepcionista) {
		recepcionista.setNome(nome);
		recepcionista.setCpf(cpf);
		recepcionista.setRg(rg);
		recepcionista.setTelefone(telefone);
		recepcionista.setCelular(celular);
		recepcionista.setSexo(sexo);
		recepcionista.setEndereco(endereco);
		recepcionista.setDataDeNascimento(dataDeNascimento);
		recepcionista.setLogin(login);
	}

}
