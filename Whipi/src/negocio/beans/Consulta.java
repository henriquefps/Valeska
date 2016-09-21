package negocio.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Consulta {
	private Cliente paciente;
	private LocalDateTime horario;
	boolean realizada = false;
	private Medico medico;
	private String descricaoPosConsulta;// novo
	public Consulta(Cliente paciente, LocalDateTime hora, Medico medico){
		this.paciente = paciente;
		this.horario = hora;
		this.medico = medico;
	}
	// removi o realizarConsulta
	public void setRealizada(boolean a){
		this.realizada = a;
	}
	// get e set descricao da consulta
	public void setDescricaoDaConsulta(String descricao){
		this.descricaoPosConsulta = descricao;
	}
	public String getDescricaoPosConsulta() {
		return descricaoPosConsulta;
	}
	public Cliente getCliente(){
		return this.paciente;
	}
	public boolean isRealizada() {
		return realizada;
	}
	public LocalDate getDia(){
		LocalDate data = LocalDate.of(this.horario.getYear(), this.horario.getMonth(), this.horario.getDayOfMonth());
		return data;
	}
	
	public String toStringLocalDateTime(){
		String a = this.horario.getDayOfMonth() + "/" + this.horario.getMonth() + "/" + this.horario.getYear();
		String b = this.horario.getHour() + ":" + this.horario.getMinute();
		return "\nData: " + a + "\nHora: " + b;
	}
	public String toString(){
		return this.paciente.toString() + this.toStringLocalDateTime() + "\n";
	}
	public Medico getMedico(){
		return this.medico;
	}
}
