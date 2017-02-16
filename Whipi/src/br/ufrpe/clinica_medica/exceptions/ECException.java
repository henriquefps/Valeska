//Excesso de Consultas para um médico
package br.ufrpe.clinica_medica.exceptions;

public class ECException extends Exception {

	public ECException() {
		super("Quantidade Máxima de Consultas Atingido");
	}
}