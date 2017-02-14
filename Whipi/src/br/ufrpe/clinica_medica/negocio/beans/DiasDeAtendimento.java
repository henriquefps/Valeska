package br.ufrpe.clinica_medica.negocio.beans;

import java.io.Serializable;
import java.time.LocalTime;

public class DiasDeAtendimento implements Serializable{
	private boolean segunda;
	private LocalTime segundaInicio;
	private LocalTime segundaFim;
	
	private boolean terca;
	private LocalTime tercaInicio;
	private LocalTime tercaFim;
	
	private boolean quarta;
	private LocalTime quartaInicio;
	private LocalTime quartaFim;
	
	private boolean quinta;
	private LocalTime quintaInicio;
	private LocalTime quintaFim;
	
	private boolean sexta;
	private LocalTime sextaInicio;
	private LocalTime sextaFim;
	
	private boolean sabado;
	private LocalTime sabadoInicio;
	private LocalTime sabadoFim;
	
	private boolean domingo;
	private LocalTime domingoInicio;
	private LocalTime domingoFim;
	
	
	
	public DiasDeAtendimento(){
		segunda = false;
		terca = false;
		quarta = false;
		quinta = false;
		sexta = false;
		sabado = false;
		domingo = false;
	}



	public boolean isSegunda() {
		return segunda;
	}



	public void setSegundaTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.segunda = true;
		this.segundaInicio = LocalTime.of(horaI, minutoI);
		this.segundaFim = LocalTime.of(horaF, minutoF);
	}
	
	public void setSegundaFalse(){
		this.segunda = false;
		this.segundaInicio = null;
		this.segundaFim = null;
	}


	public boolean isTerca() {
		return terca;
	}



	public void setTercaTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.terca = true;
		this.tercaInicio = LocalTime.of(horaI, minutoI);
		this.tercaFim = LocalTime.of(horaF, minutoF);
	}
	
	public void setTercaFalse(){
		this.terca = false;
		this.tercaInicio = null;
		this.tercaFim = null;
	}


	public boolean isQuarta() {
		return quarta;
	}



	public void setQuartaTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.quarta = true;
		this.quartaInicio = LocalTime.of(horaI, minutoI);
		this.quartaFim = LocalTime.of(horaF, minutoF);
	}

	public void setQuartaFalse(){
		this.quarta = false;
		this.quartaInicio = null;
		this.quartaFim = null;
	}


	public boolean isQuinta() {
		return quinta;
	}

	public void setQuintaTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.quinta = true;
		this.quintaInicio = LocalTime.of(horaI, minutoI);
		this.quintaFim = LocalTime.of(horaF, minutoF);
	}

	public void setQuintaFalse(){
		this.quinta = false;
		this.quintaInicio = null;
		this.quintaFim = null;
	}

	public boolean isSexta() {
		return sexta;
	}

	public void setSextaTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.sexta = true;
		this.sextaInicio = LocalTime.of(horaI, minutoI);
		this.sextaFim = LocalTime.of(horaF, minutoF);
	}

	

	public void setSextaFalse(){
		this.sexta = false;
		this.sextaInicio = null;
		this.sextaFim = null;
	}

	public boolean isSabado() {
		return sabado;
	}

	public void setSabadoTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.sabado = true;
		this.sabadoInicio = LocalTime.of(horaI, minutoI);
		this.sabadoFim = LocalTime.of(horaF, minutoF);
	}
	
	public void setSabadoFalse(){
		this.sabado = false;
		this.sabadoInicio = null;
		this.sabadoFim = null;
	}



	public boolean isDomingo() {
		return domingo;
	}

	public void setDomingoTrue(int horaI, int minutoI, int horaF, int minutoF) {
		this.domingo = true;
		this.domingoInicio = LocalTime.of(horaI, minutoI);
		this.domingoFim = LocalTime.of(horaF, minutoF);
	}
	
	public void setDomingoFalse(){
		this.domingo = false;
		this.domingoInicio = null;
		this.domingoFim = null;
	}

	
	
}
