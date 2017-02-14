package br.ufrpe.clinica_medica.repositorio;

import java.time.LocalDate;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;

public interface IRepositorioPessoas {
	/**
	 * Cadastra uma pessoa criada no controlador.
	 */
	public void cadastrar(Pessoa cadastro);
	
	/**
	 * Retorna o toString de todos os recepcionistas cadastrados.
	 */
	public ArrayList<Recepcionista> getListaRecepcionista();
	
	/**
	 * Retorna o toString de todos os pacientes cadastrados no sistema.
	 */
	public ArrayList<Paciente> getListaPacientes();
	
	/**
	 * Retorna o toString de todos os medicos cadastrados no sistema.
	 */
	public ArrayList<Medico> getListaMedicos();
	
	/**
	 * Remove uma pessoa indicada pelo controlador.
	 */
	public boolean remover(Pessoa remover);
	
	/**
	 * Pesquisa uma pessoa atraves do cpf.
	 */
	public Pessoa pesquisar(String cpf);
	
	/**
	 * Verifica se um cpf ja esta cadastrado no sitema.
	 */
	public boolean existe(String cpf);
	
	/**
	 * Atualiza as informacoes de uma pessoa.
	 */
	public void atualizar(Pessoa ant, Pessoa novo);
	
	/**
	 * Salva a instância do Repositório de Pessoas em Arquivo
	 */
	public void salvarPessoaEmArquivo();
}
