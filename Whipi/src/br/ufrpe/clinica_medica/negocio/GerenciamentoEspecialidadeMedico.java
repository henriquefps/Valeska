package br.ufrpe.clinica_medica.negocio;

import br.ufrpe.clinica_medica.repositorio.IRepositorioEspecialidadeMedico;
import br.ufrpe.clinica_medica.repositorio.RepositorioEspecialidadeMedico;

public class GerenciamentoEspecialidadeMedico {
	
	private IRepositorioEspecialidadeMedico especialidades;
	
	public GerenciamentoEspecialidadeMedico(){
		especialidades = RepositorioEspecialidadeMedico.getInstance();
	}
	
	//TODO implementar esta classe
}
