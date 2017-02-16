package br.ufrpe.clinica_medica.negocio.beans;

import java.io.Serializable;
import java.time.LocalTime;

public class DiasDeAtendimento implements Serializable{
	
	private boolean dia;
	private LocalTime diaInicio;
	private LocalTime diaFim;
	
	public DiasDeAtendimento(){
		dia = false;
		diaInicio = null;
		diaFim = null;
	}

	public boolean isDia() {
		return dia;
	}

	public void setDiaTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.dia = true;
		this.diaInicio = LocalTime.of(horaI, minutoI);
		this.diaFim = LocalTime.of(horaF, minutoF);
	}
	
	public void setDiaFalse(){
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
	
}
