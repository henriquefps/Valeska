package beans;

import java.time.*;

public class Consulta {
	private int id;
	private Paciente paciente;
	private LocalDateTime horario;
	private boolean realizada;
	private String descricao;
	private Medico medico;
	
	public Consulta(Paciente paciente, LocalDateTime horario, String descricao, Medico medico) {
		super();
		this.paciente = paciente;
		this.horario = horario;
		this.realizada = false;
		this.descricao = descricao;
		this.medico = medico;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public LocalDateTime getHorario() {
		return horario;
	}
	public void setHorario(LocalDateTime horario) {
		this.horario = horario;
	}
	public boolean isRealizada() {
		return realizada;
	}
	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean equals(Consulta outra){
		if(this.id == outra.id && this.medico == outra.medico && this.paciente == outra.paciente 
				&& this.horario == outra.horario && this.descricao.equals(outra.descricao)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public String toString() {
		return "Consulta " + id + " - paciente= " + paciente + ", horario= " + horario + ", realizada= " + realizada
				+ ", descricao= " + descricao + ", medico= " + medico;
	}
}
