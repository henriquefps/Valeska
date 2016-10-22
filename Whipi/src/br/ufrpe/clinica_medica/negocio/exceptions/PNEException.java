//Pessoa não existe no sistema
package br.ufrpe.clinica_medica.negocio.exceptions;

public class PNEException extends Exception {
	
	public PNEException(String message) {
		super(message);
	}
}