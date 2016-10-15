package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;

public interface IRepositorioConsultas {
	
	/*
	 * cadastra uma consulta no repositorio apos ser criada pelo controlador
	 */
	public void cadastrar(Consulta consulta);
	
	/*
	 * pesquisa uma consulta salva para um paciente em um determinado dia
	 */
	public Consulta pesquisar(String cpfDoPaciente, LocalDate dia);
	
	/*
	 * retorna todas as consultas de um determinado medico em uma data informada
	 */
	public ArrayList<Consulta> getConsultasComMedicoNoDia(Medico medico, LocalDate dia);
	
	/*
	 * retorna todas as consultas com um paciente em um determinado dia
	 */
	public ArrayList<Consulta> getConsultasComPacienteNoDia(Paciente paciente, LocalDate dia);
	
	/*
	 * exlclui uma consulta informada do sistema
	 */
	public void excluir(Consulta con);
	
	/*
	 * altera o medico da consulta
	 */
	public void modificar(Consulta con, Medico medico);
	
	/*
	 * altera a descricao da consulta
	 */
	public void modificar(Consulta con, String descricao);
	
	/*
	 * altera o horario da consulta
	 */
	public void modificar(Consulta con, LocalDateTime horario);
	
	/*
	 * modifica o paciente da consulta
	 */
	public void modificar(Consulta con, Paciente paciente);
}
