//Nenhuma consulta marcada para o dia
package br.ufrpe.clinica_medica.negocio.exceptions;

import java.time.LocalDate;

public class NCDException extends Exception {
	private LocalDate data;
	
	public NCDException() {
		super("Nenhuma consulta marcada para hoje");
		this.data = LocalDate.now();
	}
	
	public NCDException(LocalDate data) {
		super("Nenhuma consulta marcada para o dia " + data.getDayOfMonth() + " do " + data.getMonthValue());
		this.data = data;
	}
	
	public LocalDate getData() {
		return this.data;
	}
}
