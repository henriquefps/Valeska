package repositorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import negocio.beans.*;

public class RepositorioConsultas {

	private ArrayList<Consulta> lista;
	
	private static RepositorioConsultas instance;

	private RepositorioConsultas() {
		lista = new ArrayList<Consulta>();
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
		}
	}

	public Consulta pesquisar(int id) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId() == id) {
				return lista.get(i);
			}
		}
		return null;
	}

	public void remover(Consulta con) {
		if (con != null) {
			lista.remove(con);
		}
	}

	public void atualizar(Consulta consulta, LocalDateTime horario,Medico medico, Paciente paciente){
		consulta.setHorario(horario);
		consulta.setMedico(medico);
		consulta.setPaciente(paciente);
	}

/*
	public ArrayList<Consulta> getConsultasComMedico(Medico medico) {
		ArrayList<Consulta> comMedico = new ArrayList<Consulta>();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getMedico() == medico) {
				comMedico.add(lista.get(i));
			}
		}
		if (comMedico.isEmpty()) {
			return null;
		} else {
			return comMedico;
		}
	}
	// retorna um ArrayList com todas as consultas com um determinado medico

	public ArrayList<Consulta> getConsultasComPaciente(Paciente paciente) {
		ArrayList<Consulta> comPaciente = new ArrayList<Consulta>();

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getPaciente() == paciente) {
				comPaciente.add(lista.get(i));
			}
		}
		if (comPaciente.isEmpty()) {
			return null;
		} else {
			return comPaciente;
		}
	}
	// retorna um ArrayList com todas as consultas de um determinado paciente

	public ArrayList<Consulta> getLista() {
		return lista;
	}
 */
	
}