/* Consulta 
 * 
 * VersÃ£o 0.1
 * 
 * 6/7/2016
 * 
 * Copyright WhipiÂ® Inc.
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
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.exceptions.CNEException;
import br.ufrpe.clinica_medica.negocio.exceptions.ECException;
import br.ufrpe.clinica_medica.negocio.exceptions.NCDException;
import br.ufrpe.clinica_medica.negocio.exceptions.PNEException;
import br.ufrpe.clinica_medica.repositorio.IRepositorioConsultas;
import br.ufrpe.clinica_medica.repositorio.RepositorioConsultas;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class GerenciamentoConsulta {

	private IRepositorioConsultas consultas;

	public GerenciamentoConsulta() {
		consultas = RepositorioConsultas.getInstance();
	}

	public ArrayList<Consulta> consultasDoDia(Medico medico) throws NCDException {
		ArrayList<Consulta> consultas = this.consultas.getConsultasComMedicoNoDia(medico, LocalDate.now());
		if (!consultas.isEmpty()) {
			return consultas;
		} else {
			throw new NCDException();
		}
	}

	public void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime horario) throws PNEException, ECException {
		if (medico != null) {
			if (paciente != null) {
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
					throw new ECException();
				}
			} else {
				throw new PNEException("Paciente não encontrado no sistema");
			}
		} else {
			throw new PNEException("Medico não encontrado no sistema");
		}
	}

	public void removerConsulta(String cpfDoPaciente, LocalDate dia) throws PNEException {
		Consulta con = consultas.pesquisar(cpfDoPaciente, dia);
		if (con != null) {
			consultas.excluir(con);
		} else {
			throw new PNEException("Paciente nao encontrado no sistema");
		}
	}

	public void alterarConsulta(Consulta consulta, Medico novoMedico, LocalDateTime novoHorario) throws PNEException, CNEException {
		if (consulta != null) {
			if (novoMedico != null) {
				consultas.modificar(consulta, novoHorario);
				consultas.modificar(consulta, novoMedico);
			} else {
				throw new PNEException("Medico nao encontrado no sistema");
			}
		} else {
			throw new CNEException();
		}
	}

	public Consulta pesquisarConsulta(String cpfDoPaciente, LocalDate dia) {
		return consultas.pesquisar(cpfDoPaciente, dia);
	}

	public void realizarConsulta(Consulta consulta, String descricao) throws CNEException {
		if (consulta != null) {
			consulta.setRealizada(true);
			consulta.setDescricao(descricao);
		} else {
			throw new CNEException();
		}
	}

	public String consultasComMedicoNoDia(Medico medico, LocalDate dia) throws PNEException, NCDException {
		if (medico != null) {
			String resp = "";
			ArrayList<Consulta> comMedico = consultas.getConsultasComMedicoNoDia(medico, dia);
			if (!comMedico.isEmpty()) {
				for (int i = 0; i < comMedico.size(); i++) {
					resp += comMedico.get(i).toString();
				}
				return resp;
			} else {
				throw new NCDException(dia);
			}
		} else {
			throw new PNEException("Medico nao encontrado no sistema");
		}
	}
}
