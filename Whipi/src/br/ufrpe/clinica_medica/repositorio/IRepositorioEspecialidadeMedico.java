package br.ufrpe.clinica_medica.repositorio;

import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.EspecialidadeMedico;

public interface IRepositorioEspecialidadeMedico {
	
	/**
	 * Cadastra uma Especialidade de Médico
	 * @param em
	 * 		Instância de EspecialidadeMedico
	 */
	public void cadastrar(EspecialidadeMedico em);
	
	/**
	 * Remove uma Especialidade de Médico
	 * @param em
	 * 		Instância de EspecialidadeMedico
	 */		
	public void remover(EspecialidadeMedico em);
	
	/**
	 * procura uma Especialidade de Médico baseado no id
	 * @param id
	 * 		inteiro de identificação de uma EspecialidadeMedico
	 * @return
	 * 		Uma instância de EspecialidadeMedico
	 */
	public EspecialidadeMedico pesquisar(int id);
	
	/**
	 * procura uma Especialidade de Médico baseado no nome
	 * @param nome
	 * 		uma String do nome da especialidade 
	 * @return
	 * 		um ArrayList de especialidades
	 */
	public ArrayList<EspecialidadeMedico> pesquisar(String nome);

	/**
	 * lista todas as especialidades de médico cadastradas
	 * @return
	 * 		Um ArrayList com todas as especialidades
	 */
	public ArrayList<EspecialidadeMedico> listarTodos();
	
	/**
	 * Salva a instância do repositório em arquivo
	 */
	public void salvarEmArquivo();
	
}
