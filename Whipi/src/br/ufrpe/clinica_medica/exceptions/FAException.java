//Falha na Autenticação
package br.ufrpe.clinica_medica.exceptions;

public class FAException extends Exception {

	public FAException() {
		super("CPF ou senha invalido(s)");
	}

}