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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.CNEException;
import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;

public class RepositorioConsultas implements IRepositorioConsultas, Serializable {
	private ArrayList<Consulta> lista;
	private static RepositorioConsultas instance;

	private RepositorioConsultas() {
		lista = new ArrayList<Consulta>();
	}

	private static RepositorioConsultas lerDoArquivo() {
		File f = new File("Consultas.dat");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RepositorioConsultas rc = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			rc = (RepositorioConsultas) o;
		} catch (Exception e) {
			rc = new RepositorioConsultas();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return rc;
	}

	public void salvarConsultaEmArquivo() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(new File("Consultas.dat"));
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public static IRepositorioConsultas getInstance() {
		if (instance == null) {
			instance = lerDoArquivo();
		}
		return instance;
	}

	public void cadastrar(Consulta consulta) throws IllegalArgumentException {
		if (consulta != null) {
			lista.add(consulta);
			System.out.println("ok");
		} else {
			System.out.println("Deu bronca no repositorio");
			throw new IllegalArgumentException("Consulta Inv�lida!");
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

	public void excluir(int id) throws CNEException {
		Consulta con = pesquisar(id);
		if (con != null) {
			lista.remove(con);
		} else {
			throw new CNEException();
		}
	}

	public void excluir(Consulta con) throws CNEException {
		if (con != null) {
			lista.remove(con);
		} else {
			throw new CNEException();
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
		}
	}

	public void modificar(Consulta con, String descricao) {
		if (con != null) {
			con.setDescricao(descricao);
		}
	}

	public void modificar(Consulta con, Medico medico) {
		if (con != null && medico != null) {
			con.setMedico(medico);
			;
		}
	}

	public Consulta pesquisar(String cpfDoPaciente, LocalDate data) {
		Consulta achou = null;
		for (int i = 0; i < lista.size(); i++) {

			if (cpfDoPaciente != null && lista.get(i).getPaciente().getCpf().equals(cpfDoPaciente)) {

				if (data != null && lista.get(i).getHorario().toLocalDate().getDayOfMonth() == data.getDayOfMonth()
						&& lista.get(i).getHorario().toLocalDate().getMonthValue() == data.getMonthValue()
						&& lista.get(i).getHorario().getYear() == data.getYear()) {

					achou = lista.get(i);
				}
			}
		}
		return achou;
	}

	/**
	 * Retorna um ArrayList com todas as consultas com um determinado medico.
	 */
	public ArrayList<Consulta> getConsultasComMedicoNoDia(Medico medico, LocalDate dia) {
		ArrayList<Consulta> comMedico = new ArrayList<Consulta>();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getMedico().equals(medico)
					&& lista.get(i).getHorario().toLocalDate().getDayOfMonth() == dia.getDayOfMonth()
					&& lista.get(i).getHorario().toLocalDate().getMonthValue() == dia.getMonthValue()
					&& lista.get(i).getHorario().toLocalDate().getYear() == dia.getYear()) {
				comMedico.add(lista.get(i));
			}
		}
		return comMedico;
	}

	/**
	 * Retorna um ArrayList com todas as consultas de um determinado paciente.
	 */
	public ArrayList<Consulta> getConsultasComPacienteNoDia(Paciente paciente, LocalDate dia) {
		ArrayList<Consulta> comPaciente = new ArrayList<Consulta>();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getPaciente() == paciente && lista.get(i).getPaciente() == paciente) {
				comPaciente.add(lista.get(i));
			}
		}
		return comPaciente;
	}

	public ArrayList<Consulta> getLista() {
		return lista;
	}
}