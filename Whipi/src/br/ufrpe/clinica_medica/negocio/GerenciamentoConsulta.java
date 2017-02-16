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
import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.CNEException;
import br.ufrpe.clinica_medica.exceptions.ECException;
import br.ufrpe.clinica_medica.exceptions.NCDException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
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

	public ArrayList<Consulta> consultasDoDia(Medico medico) throws NCDException {
		ArrayList<Consulta> consultas = this.consultas.getConsultasComMedicoNoDia(medico, LocalDate.now());
		if (!consultas.isEmpty()) {
			return consultas;
		} else {
			throw new NCDException();
		}
	}

	public void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime horario) throws ECException {
		if (medico != null) {
			if (paciente != null) {
				int diaDaSemana = horario.getDayOfWeek().getValue() - 1;
				if (medico.getDiasDeTrabalho()[diaDaSemana].isDia()
						&& medico.getDiasDeTrabalho()[diaDaSemana].getDiaInicio().getHour() <= horario.getHour()
						&& medico.getDiasDeTrabalho()[diaDaSemana].getDiaInicio().getMinute() <= horario.getMinute()
						&& medico.getDiasDeTrabalho()[diaDaSemana].getDiaFim().getHour() > horario.getHour()) {
					consultas.cadastrar(new Consulta(paciente, horario, medico));
					consultas.salvarConsultaEmArquivo();
				} else {
					throw new ECException();
				}
			} else {
				throw new IllegalArgumentException("Medico Invalido");
			}
		} else {
			throw new IllegalArgumentException("Paciente Invalido");
		}
	}

	public void removerConsulta(String cpfDoPaciente, LocalDate dia) throws PNEException, CNEException {
		Consulta con = consultas.pesquisar(cpfDoPaciente, dia);
		if (con != null) {
			consultas.excluir(con);
			consultas.salvarConsultaEmArquivo();
		} else {
			throw new PNEException("CPF do paciente nao encontrado no sistema");
		}
	}

	public void alterarConsulta(Consulta consulta, Medico novoMedico, LocalDateTime novoHorario)
			throws PNEException, CNEException {
		if (consulta != null) {
			if (novoMedico != null) {
				consultas.modificar(consulta, novoHorario);
				consultas.modificar(consulta, novoMedico);
				consultas.salvarConsultaEmArquivo();
			} else {
				throw new PNEException("CPF do medico n�o encontrado no sistema");
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
			consultas.salvarConsultaEmArquivo();
		} else {
			throw new CNEException();
		}
	}

	public ArrayList<Consulta> consultasComMedicoNoDia(Medico medico, LocalDate dia) throws PNEException, NCDException {
		if (medico != null) {
			ArrayList<Consulta> comMedico = consultas.getConsultasComMedicoNoDia(medico, dia);
			return comMedico;

		} else {
			throw new PNEException("CPF do medico nao encontrado no sistema");
		}
		
		
	}
	
	public void removerConsulta(Consulta c) throws CNEException {
		if(c != null){
			consultas.excluir(c);
		}
	}
}
