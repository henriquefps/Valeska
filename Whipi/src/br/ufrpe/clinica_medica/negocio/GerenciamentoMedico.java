/* Consulta 
 * 
 * VersÃ£o 0.1
 * 
 * 6/7/2016
 * 
 * Copyright WhipiÂ® Inc.
 * Todos os direitos reservados.
 * 
 * Gerencia o cadastro e funcoes que um medico pode fazer
 * 		ele impede que sejam cadastradas pessoas com o mesmo cpf
 */
package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.beans.*;
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

	public void removerMedico(Medico medico) throws PNEException {
		if (medico != null) {
			this.pessoas.remover(medico);
		} else {
			throw new PNEException("CPF do medico não encontrado no sistema");
		}
	}

	public void alterarMedico(String cpf, Medico novoMedico) throws PNEException {
		Pessoa p = null;
		if(cpf != null){
			p = pessoas.pesquisar(cpf);
		}
		else{
			throw new IllegalArgumentException("CPF inválido");
		}
		if(p != null){
			if(p instanceof Medico){
				pessoas.atualizar(p, novoMedico);
			}
			else{
				//Depois mudar a mensagem, estou sem criatividade
				throw new IllegalArgumentException("CPF não é de um médico");
			}
		}
		else{
			throw new PNEException("Médico não encontrado");
		}
	}
	

	public Medico pesquisarMedico(String cpf) {
		Pessoa aux = pessoas.pesquisar(cpf);
		if (!(aux instanceof Medico)) {
			aux = null;
		}
		return (Medico) aux;
	}
	
	public ArrayList<Medico> ListarMedicos(){
		return pessoas.getListaMedicos();
	}
}
