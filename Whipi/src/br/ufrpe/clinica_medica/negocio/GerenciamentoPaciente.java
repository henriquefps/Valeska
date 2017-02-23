/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Gerencia o cadastro de novos pacientes de forma a evitar que o cpf 
 * dessa nova pessoa seja igual a de alguma pessoa que ja esteja cadastrada no sistema.
 */
package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.repositorio.IRepositorioPessoas;
import br.ufrpe.clinica_medica.repositorio.RepositorioPessoas;

public class GerenciamentoPaciente {
	private IRepositorioPessoas pessoas;

	public GerenciamentoPaciente() {
		pessoas = RepositorioPessoas.getInstance();
	}

	public void cadastrarPaciente(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento) throws PJCException {
		if (!pessoas.existe(cpf)) {
			pessoas.cadastrar(new Paciente(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento));
			pessoas.salvarPessoaEmArquivo();
		} else {
			throw new PJCException("CPF " + cpf + " já cadastrado");
		}
	}

	public Paciente pesquisarPaciente(String cpf) {
		Pessoa a = pessoas.pesquisar(cpf);
		if (!(a instanceof Paciente)) {
			a = null;
		}
		return (Paciente) a;
	}

	public void removerPaciente(Paciente a) throws PNEException {
		if (a == null)
			throw new IllegalArgumentException("Parâmetro inválido");

		if (pessoas.existe(a.getCpf())) {
			pessoas.remover(a);
			pessoas.salvarPessoaEmArquivo();
		} else {
			throw new PNEException("CPF do paciente não encontrado no sistema");
		}
	}

	public void alterarPaciente(String cpf, Paciente novoPaciente) throws PNEException {
		Pessoa p = null;
		if (cpf != null) {
			p = pessoas.pesquisar(cpf);
		} else {
			throw new IllegalArgumentException("CPF inválido");
		}
		if (p != null) {
			if (p instanceof Paciente) {
				pessoas.atualizar(p, novoPaciente);
				pessoas.salvarPessoaEmArquivo();
			} else {
				// TODO Depois mudar a mensagem, estou sem criatividade
				throw new IllegalArgumentException("CPF não é de um paciente");
			}
		} else {
			throw new PNEException("Paciente não encontrado");
		}
	}

	public ArrayList<Paciente> pesquisarPacienteNome(String nome) {
		return pessoas.pesquisarPacienteNome(nome);
	}

	public ArrayList<Paciente> listarPacientes() {
		return pessoas.listaTodosPacientes();
	}
}
