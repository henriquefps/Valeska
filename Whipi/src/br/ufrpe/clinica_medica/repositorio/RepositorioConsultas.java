/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Repositorio que guarda todas as cunsultas salvas no sistema, tambem informa uma lista de consultas
 * com determinado medico em um determinado dia
 * Permite e a remocao e alteracao dos objetos salvos
 */
package br.ufrpe.clinica_medica.repositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.*;

public class RepositorioConsultas {
	private ArrayList<Consulta> lista = new ArrayList<Consulta>();
	private static RepositorioConsultas instance;

	private RepositorioConsultas() {

	}

	public static RepositorioConsultas getInstance() {
		if (instance == null) {
			instance = new RepositorioConsultas();
		}
		return instance;
	}

	public void cadastrar(Consulta consulta) {
		if (consulta != null) {
			lista.add(consulta);
		} else {
			System.out.println("erro3");
		}
	}

	public Consulta pesquisar(int id) {
		Consulta achou = null;
		for (int i = 0; i < lista.size() && achou == null; i++) {
			if (lista.get(i).getId() == id) {
				achou = lista.get(i);
			}
		}
		return achou;
	}

	public void excluir(int id) {
		Consulta con = pesquisar(id);
		if (con != null) {
			lista.remove(con);
		}
	}

	public void excluir(Consulta con) {
		if (con != null) {
			lista.remove(con);
		}
	}

	public void modificar(Consulta con, Paciente paciente) {
		if (con != null && paciente != null) {
			con.setPaciente(paciente);
		}
	}

	public void modificar(Consulta con, LocalDateTime horario) {
		if (con != null) {
			con.setHorario(horario);
			;
		}
	}

	public void modificar(Consulta con, String descricao) {
		if (con != null) {
			con.setDescricao(descricao);
			;
		}
	}

	public void modificar(Consulta con, Medico medico) {
		if (con != null && medico != null) {
			con.setMedico(medico);
			;
		}
	}

	public Consulta pesquisar(String cpfDoPaciente, LocalDate dia) {
		Consulta achou = null;
		for (int i = 0; i < lista.size() && achou == null; i++) {
			if (lista.get(i).getPaciente().getCpf() == cpfDoPaciente
					&& lista.get(i).getHorario().toLocalDate() == dia) {
				achou = lista.get(i);
			}
		}
		return achou;
	}

	public ArrayList<Consulta> getConsultasComMedicoNoDia(Medico medico, LocalDate dia) {
		ArrayList<Consulta> comMedico = new ArrayList<Consulta>();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getMedico().equals(medico) && !lista.get(i).foiRealizada()
					&& lista.get(i).getHorario().toLocalDate().getDayOfMonth() == dia.getDayOfMonth()
					&& lista.get(i).getHorario().toLocalDate().getMonthValue() == dia.getMonthValue()
					&& lista.get(i).getHorario().toLocalDate().getYear() == dia.getYear()) {
				comMedico.add(lista.get(i));
			}
		}
		return comMedico;
	}
	// retorna um ArrayList com todas as consultas com um determinado medico

	public ArrayList<Consulta> getConsultasComPacienteNoDia(Paciente paciente, LocalDate dia) {
		ArrayList<Consulta> comPaciente = new ArrayList<Consulta>();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getPaciente() == paciente && lista.get(i).getPaciente() == paciente) {
				comPaciente.add(lista.get(i));
			}
		}
		return comPaciente;
	}
	// retorna um ArrayList com todas as consultas de um determinado paciente

	public ArrayList<Consulta> getLista() {
		return lista;
	}
}