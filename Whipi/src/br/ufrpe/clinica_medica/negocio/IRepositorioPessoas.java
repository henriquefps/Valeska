package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;

import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;

public interface IRepositorioPessoas {
	/*
	 * cadastra uma pessoa criada no controlador
	 */
	public void cadastrar(Pessoa cadastro);
	
	/*
	 * retorna o toString de todos os recepcionistas cadastrados
	 */
	public String getListaRecepcionista();
	
	/*
	 * retorna o toString de todos os pacientes cadastrados no sistema
	 */
	public String getListaPacientes();
	
	/*
	 * retorna o toString de todos os medicos cadastrados no sistema
	 */
	public String getListaMedicos();
	
	/*
	 * remove uma pessoa indicada pelo controlador
	 */
	public boolean remover(Pessoa remover);
	
	/*
	 * pesquisa uma pessoa atraves do cpf
	 */
	public Pessoa pesquisar(String cpf);
	
	/*
	 * verifica se um cpf ja esta cadastrado no sitema
	 */
	public boolean existe(String cpf);
	
	/*
	 * atualiza as informacoes de um medico
	 */
	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha, Medico medico);
	/*
	 * atualiza as informacoes de um paciente
	 */
	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, Paciente paciente);
	
	/*
	 * atualiza as informacoes de um recepcionista
	 */
	public void atualizar(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, String senha, Recepcionista recepcionista);
}
