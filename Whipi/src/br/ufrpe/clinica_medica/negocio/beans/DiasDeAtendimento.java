package br.ufrpe.clinica_medica.negocio.beans;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class DiasDeAtendimento implements Serializable {

	private boolean dia;
	private LocalTime diaInicio;
	private LocalTime diaFim;

	public DiasDeAtendimento() {
		dia = true;
		diaInicio = LocalTime.of(9, 0);
		diaFim = LocalTime.of(18, 0);
	}

	public boolean isDia() {
		return dia;
	}

	public void setDiaTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.dia = true;
		this.diaInicio = LocalTime.of(horaI, minutoI);
		this.diaFim = LocalTime.of(horaF, minutoF);
	}

	public void setDia(boolean dia) {
		this.dia = dia;
	}

	public void setDiaFalse() {
		this.dia = false;
		this.diaInicio = null;
		this.diaFim = null;
	}

	public LocalTime getDiaInicio() {
		return diaInicio;
	}

	public LocalTime getDiaFim() {
		return diaFim;
	}
	
	public ArrayList<LocalTime> getConsultasPossiveis() {
		ArrayList<LocalTime> resultado = new ArrayList<LocalTime>();
		for (int i = 9; i >= 0; i--) {
			resultado.add(LocalTime.of(18 - i, 0));
		}
		return resultado;
	}

}
