//Consulta não existe
package br.ufrpe.clinica_medica.negocio.exceptions;

public class CNEException extends Exception {

	public CNEException() {
		super("Consulta não encontrada no sistema");
	}
}