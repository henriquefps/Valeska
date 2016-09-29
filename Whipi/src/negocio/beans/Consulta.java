package negocio.beans;

import java.time.*;

public class Consulta {

	private static int proximoID = 1;
	private int id;
	private Paciente paciente;
	private LocalDateTime horario;
	private boolean realizada;
	private String descricao;
	private Medico medico;

	public Consulta(Paciente paciente, LocalDateTime horario, String descricao, Medico medico) {
		this.id = Consulta.proximoID;
		this.paciente = paciente;
		this.horario = horario;
		this.realizada = false;
		this.descricao = descricao;
		this.medico = medico;
		Consulta.proximoID++;
	}

	public boolean foiRealizada() {
		return realizada;
	}

	public void trocarStatus() {
		this.realizada = !this.realizada;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean equals(Consulta outra) {
		if (this.medico == outra.medico && this.paciente == outra.paciente && this.horario == outra.horario) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String resultado = "ID:" + this.id + "\n";
		resultado += "Paciente: " + this.paciente.getNome() + "\n";
		resultado += "Medico: " + this.medico.getNome() + "\n";
		resultado += "Horario: " + this.horario + "\n";
		resultado += "Descricao: " + this.descricao + "\n";
		resultado += "Realizada: " + this.realizada + "\n";
		return resultado;
	}
}
