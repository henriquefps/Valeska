package br.ufrpe.clinica_medica.negocio.beans;

import java.io.Serializable;

public class DiasDeAtendimento implements Serializable {

	private boolean dias[];

	public DiasDeAtendimento() {
		dias = new boolean[6];
		for (int i = 0; i < 6; i++) {
			dias[i] = true;
		}
	}

	public boolean[] getDias() {
		return dias;
	}
	
	public void setDia(int dia, boolean set) {
		if (set) {
			dias[dia] = true;
		} else {
			dias[dia] = false;
		}
	}
	
	public boolean isDia(int dia) {
		boolean resultado = false;
		if (dias[dia]) {
			resultado = true;
		}
		return resultado;
	}
}
