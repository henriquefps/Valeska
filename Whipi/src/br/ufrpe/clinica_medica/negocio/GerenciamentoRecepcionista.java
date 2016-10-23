/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Gerencia o cadastro de recepcionistas de forma a evitar que o cpf 
 * dessa nova pessoa seja igual a de alguma pessoa que ja esteja cadastrada no sistema.
 */
package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;

import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;
import br.ufrpe.clinica_medica.negocio.exceptions.PJCException;
import br.ufrpe.clinica_medica.repositorio.IRepositorioPessoas;
import br.ufrpe.clinica_medica.repositorio.RepositorioPessoas;

public class GerenciamentoRecepcionista {

	private IRepositorioPessoas pessoas;

	public GerenciamentoRecepcionista() {
		pessoas = RepositorioPessoas.getInstance();
	}

	public void cadastrarRecepcionista(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, String senha) throws PJCException{
		if (!pessoas.existe(cpf)) {
			pessoas.cadastrar(
					new Recepcionista(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, senha));
		} else{
			throw new PJCException("CPF " + cpf + " ja cadastrado");
		}
	}

	public Recepcionista pesquisarRecepcionista(String cpf) {
		Pessoa a = pessoas.pesquisar(cpf);
		if (!(a instanceof Recepcionista)) {
			a = null;
			;
		}
		return (Recepcionista) a;
	}

	public void removerRecepcionista(Recepcionista a) {
		if (a != null) {
			pessoas.remover(a);
		}
	}
	
	public void alterarRecepcionista(Recepcionista a, String nome, String novoCpf, String rg, String telefone,
			String celular, char sexo, Endereco endereco, LocalDate dataDeNascimento, String senha) {
		if (a != null) {
			pessoas.atualizar(nome, novoCpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, senha, a);
		}
	}

}
