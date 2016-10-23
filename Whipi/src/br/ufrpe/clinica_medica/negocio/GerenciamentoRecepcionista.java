/* Consulta 
 * 
 * VersÃ£o 0.1
 * 
 * 6/7/2016
 * 
 * Copyright WhipiÂ® Inc.
 * Todos os direitos reservados.
 * 
 * Gerencia o cadastro de recepcionistas de forma a evitar que o cpf 
 * dessa nova pessoa seja igual a de alguma pessoa que ja esteja cadastrada no sistema.
 */
package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;
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
		}
		return (Recepcionista) a;
	}

	public void removerRecepcionista(Recepcionista a) throws PNEException {
		if (a != null) {
			pessoas.remover(a);
		} else {
			throw new PNEException("CPF do recepcionista nao encontrado no sistema");
		}
	}
	
	public void alterarRecepcionista(String cpf, Recepcionista novoRec) throws PNEException{
		Pessoa p = null;
		if(cpf != null){
			p = pessoas.pesquisar(cpf);
		}
		else{
			throw new IllegalArgumentException("CPF inválido");
		}
		if(p != null){
			if(p instanceof Recepcionista){
				pessoas.atualizar(p, novoRec);
			}
			else{
				//Depois mudar a mensagem, estou sem criatividade
				throw new IllegalArgumentException("CPF não é de um recepcionista");
			}
		}
		else{
			throw new PNEException("Recepcionista não encontrado");
		}
		
	}

}
