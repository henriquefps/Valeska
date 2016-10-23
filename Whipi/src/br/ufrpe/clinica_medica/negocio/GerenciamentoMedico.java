/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Gerencia o cadastro e funcoes que um medico pode fazer
 * 		ele impede que sejam cadastradas pessoas com o mesmo cpf
 */
package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;

import br.ufrpe.clinica_medica.negocio.beans.*;
import br.ufrpe.clinica_medica.negocio.exceptions.PJCException;
import br.ufrpe.clinica_medica.negocio.exceptions.PNEException;
import br.ufrpe.clinica_medica.repositorio.*;

public class GerenciamentoMedico {
	
	private IRepositorioPessoas pessoas;

	public GerenciamentoMedico() {
		this.pessoas = RepositorioPessoas.getInstance();
	}

	public void cadastrarMedico(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha) throws PJCException {
		if (!pessoas.existe(cpf)) {
			pessoas.cadastrar(new Medico(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM,
					consultasPorDia, senha));
		} else{
			throw new PJCException("CPF " + cpf+ " ja cadastrado no sistema");
		}
	}

	public void removerMedico(Medico medico) {
		if (medico != null) {
			this.pessoas.remover(medico);
		}
	}

	public void alterarMedico(Medico medico, String nome, String novocpf, String rg, String telefone, String celular,
			char sexo, Endereco endereco, LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha) throws PNEException{
		if (medico != null) {
			pessoas.atualizar(nome, novocpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM, consultasPorDia, senha,
					medico);
		} else{
			throw new PNEException("Resultado nao encontrado");
		}
	}

	public Medico pesquisarMedico(String cpf) {
		Pessoa aux = pessoas.pesquisar(cpf);
		if (!(aux instanceof Medico)) {
			aux = null;
		}
		return (Medico) aux;
	}
}
