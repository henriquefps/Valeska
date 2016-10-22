//Excesso de Consultas para um médico
package br.ufrpe.clinica_medica.negocio.exceptions;

public class ECException extends Exception {

	public ECException() {
		super("Excesso de consultas atingido");
	}
}