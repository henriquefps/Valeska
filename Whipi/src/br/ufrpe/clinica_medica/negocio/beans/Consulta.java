/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Classe basica que guarda a definicao do que e uma consulta
 */
package br.ufrpe.clinica_medica.negocio.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Consulta implements Serializable {

	private static int proximoId = 1;
	private int id;
	private Paciente paciente;
	private LocalDateTime horario;
	private boolean realizada;
	private String descricao;
	private Medico medico;

	public Consulta(Paciente paciente, LocalDateTime horario, Medico medico) {
		this.id = Consulta.proximoId;
		this.paciente = paciente;
		this.horario = horario;
		this.realizada = false;
		this.descricao = "";
		this.medico = medico;
		Consulta.proximoId++;
	}

	public boolean foiRealizada() {
		return realizada;
	}

	public void setRealizada(boolean a) {
		this.realizada = a;
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
		boolean achou = false;
		if (outra != null) {
			if (this.medico != null && this.paciente != null && this.horario != null && outra.getMedico() != null
					&& outra.paciente != null && outra.getHorario() != null) {
				if (this.medico.equals(outra.getMedico()) && this.paciente.equals(outra.getPaciente())
						&& this.horario.equals(outra.getHorario())) {
					achou = true;
				}
			}
		}

		return achou;
	}

	@Override
	public String toString() {
		String resultado = "ID:" + this.id + "\n";
		resultado += "Paciente: " + this.paciente.getNome() + "\n";
		resultado += "CPF do paciente: " + this.paciente.getCpf() + "\n";
		resultado += "Medico: " + this.medico.getNome() + "\n";
		resultado += "Horario: " + this.horario + "\n";
		resultado += "Descricao: " + this.descricao + "\n";
		resultado += "Realizada: " + this.realizada + "\n";
		return resultado;
	}
}
