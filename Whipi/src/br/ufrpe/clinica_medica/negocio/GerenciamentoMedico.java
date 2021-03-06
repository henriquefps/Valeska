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
import java.time.LocalTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.EspecialidadeMedico;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.repositorio.IRepositorioPessoas;
import br.ufrpe.clinica_medica.repositorio.RepositorioPessoas;

public class GerenciamentoMedico {

	private IRepositorioPessoas pessoas;

	public GerenciamentoMedico() {
		this.pessoas = RepositorioPessoas.getInstance();
	}

	public void cadastrarMedico(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha,
			ArrayList<EspecialidadeMedico> especialidade) throws PJCException {
		if (!pessoas.existe(cpf)) {
			pessoas.cadastrar(new Medico(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM,
					consultasPorDia, senha, especialidade));
			pessoas.salvarPessoaEmArquivo();
		} else {
			throw new PJCException("CPF " + cpf + " já cadastrado no sistema");
		}
	}

	public void removerMedico(Medico medico) throws PNEException {
		if (medico != null) {
			this.pessoas.remover(medico);
			pessoas.salvarPessoaEmArquivo();
		} else {
			throw new PNEException("CPF não encontrado no sistema");
		}
	}

	public void alterarMedico(String cpf, Medico novoMedico) throws PNEException {
		Pessoa p = null;
		if (cpf != null) {
			p = pessoas.pesquisar(cpf);
		} else {
			throw new IllegalArgumentException("CPF inválido");
		}
		if (p != null) {
			if (p instanceof Medico) {
				pessoas.atualizar(p, novoMedico);
				pessoas.salvarPessoaEmArquivo();
			} else {
				// Depois mudar a mensagem, estou sem criatividade
				throw new IllegalArgumentException("CPF não é de um médico");
			}
		} else {
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

	public ArrayList<Medico> pesquisarMedicoNome(String nome) {
		return pessoas.pesquisarMedicoNome(nome);
	}

	public ArrayList<Medico> listarMedicos() {
		return pessoas.listarTodosMedicos();
	}

	public void trabalharDiaX(Medico medico, int diaDaSemana) {
		medico.setDia(diaDaSemana, true);
	}

	public void cancelarDiaDeTrabalhoX(Medico medico, int diaDaSemana) {
		medico.setDia(diaDaSemana, false);
	}

	public ArrayList<LocalTime> getConsultasPossiveis() {
		ArrayList<LocalTime> resultado = null;
		// TODO Fazer um metodo que retorna um ArrayList de hora possivel de um
		// medico!
		return resultado;
	}
}
