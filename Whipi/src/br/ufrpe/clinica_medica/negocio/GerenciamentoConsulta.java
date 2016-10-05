package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDateTime;
import java.time.LocalTime;

import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.repositorio.RepositorioConsultas;

import java.time.DayOfWeek;
import java.time.LocalDate;

@SuppressWarnings("unused")
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
					&& horario.toLocalTime().getHour() < 18 && consultas.getConsultasComPacienteNoDia(paciente, horario.toLocalDate()) == null) {
				consultas.cadastrar(new Consulta(paciente, horario, medico));
			}
		}
	}

	public void removerConsulta(String cpfDoPaciente, LocalDate dia){
		Consulta aux = consultas.pesquisar(cpfDoPaciente, dia);
		if(aux != null){
			consultas.excluir(aux);
		}
	}
	public void alterarConsulta(Consulta consulta, Medico novoMedico, LocalDateTime novoHorario){
		if (consulta != null) {
			consultas.modificar(consulta, novoHorario);
			consultas.modificar(consulta, novoMedico);
		}
	}
	public Consulta pesquisarConsulta(String cpfDoPaciente, LocalDate dia){
		return consultas.pesquisar(cpfDoPaciente, dia);
	}
	public void realizarConsulta(Consulta consulta, String descricao){
		consulta.setRealizada(true);
		consulta.setDescricao(descricao);
	}
}