package beans;

import java.time.*;

public class Consulta {
	private Cliente paciente;
	private LocalDateTime horario;
	private boolean realizada;
	private String descricao;
	private Medico medico;
	
	public Consulta(Cliente paciente, LocalDateTime horario, String descricao, Medico medico) {
		super();
		this.paciente = paciente;
		this.horario = horario;
		this.realizada = false;
		this.descricao = descricao;
		this.medico = medico;
	}
	
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Cliente getPaciente() {
		return paciente;
	}
	public void setPaciente(Cliente paciente) {
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
}
