/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Controla o cadastro de consultas com as seguintes condicoes
 * 		nao pode cadastrar consultas aos sabados e domingos
 * 		nao pode pcadastrar consultas antes das 8h e depois das 18h
 * 		nao pode cadastrar consultas para um medico que ja atingiu o maximo de consultas por dia
 * 
 */
package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.repositorio.IRepositorioConsultas;
import br.ufrpe.clinica_medica.repositorio.RepositorioConsultas;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class GerenciamentoConsulta {

	private IRepositorioConsultas consultas;

	public GerenciamentoConsulta() {
		consultas = RepositorioConsultas.getInstance();
	}

	public ArrayList<Consulta> consultasDoDia(Medico medico) {
		return this.consultas.getConsultasComMedicoNoDia(medico, LocalDate.now());
	}

	public void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime horario) {
		int numConsultas = consultas.getConsultasComMedicoNoDia(medico, horario.toLocalDate()).size();
		if (numConsultas < medico.getConsultasPorDia()) {
			if (horario.toLocalTime().getHour() >= 8 && horario.toLocalTime().getMinute() >= 0
					&& horario.toLocalDate().getDayOfWeek() != DayOfWeek.SATURDAY
					&& horario.toLocalDate().getDayOfWeek() != DayOfWeek.SUNDAY 
					&& horario.toLocalTime().getHour() < 18) {
				consultas.cadastrar(new Consulta(paciente, horario, medico));
			} else {
				System.out.println("erro");
			}
		} else {
			System.out.println("erro2");
		}
	}

	public boolean removerConsulta(String cpfDoPaciente, LocalDate dia) {
		boolean result = false;
		Consulta aux = consultas.pesquisar(cpfDoPaciente, dia);
		if (aux != null) {
			consultas.excluir(aux);
			result = true;
		}
		return result;
	}

	public boolean alterarConsulta(Consulta consulta, Medico novoMedico, LocalDateTime novoHorario) {
		boolean result = false;
		if (consulta != null) {
			consultas.modificar(consulta, novoHorario);
			consultas.modificar(consulta, novoMedico);
			result = true;
		}
		return result;
	}

	public Consulta pesquisarConsulta(String cpfDoPaciente, LocalDate dia) {
		return consultas.pesquisar(cpfDoPaciente, dia);
	}

	public void realizarConsulta(Consulta consulta, String descricao) {
		if (consulta != null) {
			consulta.setRealizada(true);
			consulta.setDescricao(descricao);
		}
	}

	public String consultasComMedicoNoDia(Medico medico, LocalDate dia) {
		String resp = "";
		ArrayList<Consulta> comMedico = consultas.getConsultasComMedicoNoDia(medico, dia);
		for (int i = 0; i < comMedico.size(); i++) {
			resp += comMedico.get(i).toString();
		}
		return resp;
	}
}
