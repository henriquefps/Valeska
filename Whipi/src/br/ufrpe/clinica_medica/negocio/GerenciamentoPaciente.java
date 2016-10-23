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

import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.exceptions.PJCException;
import br.ufrpe.clinica_medica.negocio.exceptions.PNEException;
import br.ufrpe.clinica_medica.repositorio.IRepositorioPessoas;
import br.ufrpe.clinica_medica.repositorio.RepositorioPessoas;

public class GerenciamentoPaciente {
	private IRepositorioPessoas pessoas;

	public GerenciamentoPaciente() {
		pessoas = RepositorioPessoas.getInstance();
	}

	public void cadastrarPaciente(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento) throws PJCException{
		if (!pessoas.existe(cpf)) {
			pessoas.cadastrar(new Paciente(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento));
		} else{
			throw new PJCException("CPF " + cpf + " ja cadastrado");
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
		if (a != null) {
			pessoas.remover(a);
		} else {
			throw new PNEException("CPF do paciente nao encontrado no sistema");
		}
	}

	public void alterarPaciente(Paciente a, String nome, String novoCpf, String rg, String telefone, String celular,
			char sexo, Endereco endereco, LocalDate dataDeNascimento) throws PNEException {
		if (a != null) {
			pessoas.atualizar(nome, novoCpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, (Paciente) a);
		} else {
			throw new PNEException("CPF do paciente nao encontrado no sistema");
		}
	}
}
