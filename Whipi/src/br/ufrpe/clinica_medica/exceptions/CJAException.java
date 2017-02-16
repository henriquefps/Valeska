package br.ufrpe.clinica_medica.exceptions;

import br.ufrpe.clinica_medica.negocio.beans.Consulta;

/*
 * Consulta ja agendada exception
 */
public class CJAException extends Exception {
	private Consulta aSerTratada;
	
	public CJAException(Consulta a){
		super("Esta Consulta esta cadastrada no dia a ser cancelado");
		aSerTratada = a;
	}
	
	public Consulta getASerTratada(){
		return aSerTratada;
	}
}
