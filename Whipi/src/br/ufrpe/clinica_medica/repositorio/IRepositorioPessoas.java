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
	public ArrayList<Recepcionista> listarTodosRecepcionistas();

	/**
	 * Retorna o toString de todos os pacientes cadastrados no sistema.
	 */
	public ArrayList<Paciente> listaTodosPacientes();

	/**
	 * Retorna um ArrayList de pacientes com as iniciais "inicial"
	 */
	public ArrayList<Paciente> pesquisarPacienteCPF(String inicial);

	public ArrayList<Paciente> pesquisarPacienteNome(String nome);

	/**
	 * Retorna o toString de todos os medicos cadastrados no sistema.
	 */
	public ArrayList<Medico> listarTodosMedicos();

	/**
	 * Lista de medicos com a String inicial.
	 * 
	 * @param inicial
	 *            String para busca.
	 * @return Retorna um ArrayList de medicos com a string "inicial".
	 */
	public ArrayList<Medico> pesquisarMedicoCPF(String inicial);

	public ArrayList<Medico> pesquisarMedicoNome(String nome);

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
	 * Salva a instância do Repositârio de Pessoas em Arquivo
	 */
	public void salvarPessoaEmArquivo();

}
