/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Repositorio que armazena todas as pessoas cadastradas no sistema, Paciente, Medicos e Recepcionistas
 * Permite e a remocao e alteracao dos objetos salvos
 */
package br.ufrpe.clinica_medica.repositorio;

import java.time.LocalDate;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.*;

public class RepositorioPessoas {

	private ArrayList<Pessoa> repositorio;

	private static RepositorioPessoas instance;

	private RepositorioPessoas() {
		this.repositorio = new ArrayList<Pessoa>();
		Pessoa admin = new Pessoa("admin", "admin", null, null, null, 'm', null, null);
		repositorio.add(admin);
	}

	public static RepositorioPessoas getInstance() {
		if (instance == null) {
			instance = new RepositorioPessoas();
		}
		return instance;
	}

	public String getListaMedicos() {
		String resp = "";
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Medico) {
				resp += repositorio.get(i).toString() + "\n";
			}
		}
		return resp;
	}
	
	public String getListaPacientes() {
		String resp = "";
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Paciente) {
				resp += repositorio.get(i).toString() + "\n\n";
			}
		}
		return resp;
	}
	
	public String getListaRecepcionista(){
		String resp = "";
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Recepcionista) {
				resp += repositorio.get(i).toString() + "\n";
			}
		}
		return resp;
	}

	public void cadastrar(Pessoa cadastro) {
		repositorio.add(cadastro);
	}

	public boolean remover(Pessoa remover) {
		boolean result = false;
		boolean existe = this.existe(remover);
		if (existe == true){
			repositorio.remove(remover);
			result = true;
		}
		return result;
		
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
		medico.setConsultasPorDia(consultasPorDia);
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
