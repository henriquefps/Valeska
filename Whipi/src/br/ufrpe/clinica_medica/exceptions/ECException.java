//Excesso de Consultas para um m�dico
package br.ufrpe.clinica_medica.exceptions;

public class ECException extends Exception {

	public ECException() {
		super("Quantidade M�xima de Consultas Atingido");
	}
}