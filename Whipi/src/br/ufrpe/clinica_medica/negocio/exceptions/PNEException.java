//Pessoa não existe no sistema
package br.ufrpe.clinica_medica.negocio.exceptions;

public class PNEException extends Exception {
	private String cpf;
	
	public PNEException(String cpf) {
		super("Nao existem pessoas com esse cpf no sistema");
		this.cpf = cpf;
	}
	
	public String getcpf() {
		return this.cpf;
	}
}