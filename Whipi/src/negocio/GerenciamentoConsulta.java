package negocio;

import negocio.beans.Medico;
import negocio.beans.Paciente;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.LocalDate;

import negocio.beans.Consulta;
import repositorio.RepositorioConsultas;

public class GerenciamentoConsulta {
	private RepositorioConsultas consultas;

	public GerenciamentoConsulta() {
		consultas = RepositorioConsultas.getInstance();
	}

	public void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime horario) {
		int numConsultas = consultas.getConsultasComMedicoNoDia(medico, horario.toLocalDate()).size();
		if (numConsultas < medico.getConsultasPorDia()) {
			if (horario.toLocalTime().getHour() >= 8 && horario.toLocalTime().getMinute() >= 0
					&& horario.toLocalDate().getDayOfWeek() != DayOfWeek.SATURDAY
					&& horario.toLocalDate().getDayOfWeek() != DayOfWeek.SUNDAY
					&& horario.toLocalTime().getHour() < 18) {
				consultas.cadastrar(new Consulta(paciente, horario, medico));
			}
		}
	}
}
