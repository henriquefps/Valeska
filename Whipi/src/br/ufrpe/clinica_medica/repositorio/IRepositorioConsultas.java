package br.ufrpe.clinica_medica.repositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.CNEException;
import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;

public interface IRepositorioConsultas {

	/**
	 * Cadastra uma consulta no repositorio apos ser criada pelo controlador.
	 */
	public void cadastrar(Consulta consulta);

	/**
	 * Pesquisa uma consulta salva para um paciente em um determinado dia.
	 */
	public Consulta pesquisar(String cpfDoPaciente, LocalDate dia);

	/**
	 * Retorna todas as consultas de um determinado medico em uma data
	 * informada.
	 */
	public ArrayList<Consulta> getConsultasComMedicoNoDia(Medico medico, LocalDate dia);

	/**
	 * Retorna todas as consultas com um paciente em um determinado dia.
	 */
	public ArrayList<Consulta> getConsultasComPacienteNoDia(Paciente paciente, LocalDate dia);

	/**
	 * Exlclui uma consulta informada do sistema.
	 */
	public void excluir(int id) throws CNEException;

	/**
	 * Exlclui uma consulta informada do sistema.
	 */
	public void excluir(Consulta con) throws CNEException;

	/**
	 * Altera o medico da consulta.
	 */
	public void modificar(Consulta con, Medico medico);

	/**
	 * Altera a descricao da consulta.
	 */
	public void modificar(Consulta con, String descricao);

	/**
	 * Altera o horario da consulta.
	 */
	public void modificar(Consulta con, LocalDateTime horario);

	/**
	 * Modifica o paciente da consulta.
	 */
	public void modificar(Consulta con, Paciente paciente);

	/**
	 * Salva a inst�ncia do Reposit�rio de Consultas em Arquivo
	 */
	public void salvarConsultaEmArquivo();

	public ArrayList<Consulta> getLista();
}
