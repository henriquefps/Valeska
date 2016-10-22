//Pessoa ja cadastrada no sistema
package br.ufrpe.clinica_medica.negocio.exceptions;

public class PJCException extends Exception {
	private String cpf;
	
	public PJCException(String cpf) {
		super("Ja existe uma pessoa com esse cpf cadastrada no sistema");
		this.cpf = cpf;
	}
	
	public String getcpf() {
		return this.cpf;
	}
}
