//Consulta n�o existe
package br.ufrpe.clinica_medica.exceptions;

public class CNEException extends Exception {

	public CNEException() {
		super("Consulta n�o encontrada no sistema");
	}
}