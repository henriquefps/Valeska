//Excesso de Consultas para um m�dico
package br.ufrpe.clinica_medica.negocio.exceptions;

public class ECException extends Exception {

	public ECException() {
		super("Excesso de consultas atingido");
	}
}