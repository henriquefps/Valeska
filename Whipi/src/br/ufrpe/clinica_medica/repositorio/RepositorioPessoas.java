/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Repositorio que armazena todas as pessoas cadastradas no sistema, Paciente, Medicos e Recepcionistas
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
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;

public class RepositorioPessoas implements IRepositorioPessoas, Serializable {

	private ArrayList<Pessoa> repositorio;

	private static RepositorioPessoas instance;

	private RepositorioPessoas() {
		this.repositorio = new ArrayList<Pessoa>();
		Pessoa admin = new Pessoa("admin", "admin", null, null, null, 'm', null, null);
		repositorio.add(admin);
	}

	private static RepositorioPessoas lerDoArquivo() {

		File f = new File("Pessoas.dat");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RepositorioPessoas rp = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			rp = (RepositorioPessoas) o;
		} catch (Exception e) {
			rp = new RepositorioPessoas();
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

		return rp;
	}

	public void salvarPessoaEmArquivo() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(new File("Pessoas.dat"));
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

	public static IRepositorioPessoas getInstance() {
		if (instance == null) {
			instance = lerDoArquivo();
		}
		return instance;
	}

	public ArrayList<Medico> listarTodosMedicos() {
		ArrayList<Medico> m = new ArrayList<>();

		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Medico) {
				m.add((Medico) repositorio.get(i));
			}
		}
		return m;
	}
	
	public ArrayList<Medico> pesquisarMedicoCPF(String inicial) {
		ArrayList<Medico> m = new ArrayList<>();
		
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Medico) {
				if (repositorio.get(i).getNome().startsWith(inicial)) {
					m.add((Medico) repositorio.get(i));					
				}
			}
		}
		return m;
	}
	
	public ArrayList<Medico> pesquisarMedicoNome(String nome) {
		ArrayList<Medico> m = new ArrayList<>();
		
		for (Pessoa pessoa : repositorio) {
			if(pessoa instanceof Medico){
				Medico medico = (Medico) pessoa;
				if(medico.getNome().startsWith(nome)){
					m.add(medico);
				}
			}
		}
		
		return m;
		
	}

	public ArrayList<Paciente> listaTodosPacientes() {
		ArrayList<Paciente> p = new ArrayList<>();

		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Paciente) {
				p.add((Paciente) repositorio.get(i));
			}
		}
		return p;
	}
	
	public ArrayList<Paciente> pesquisarPacienteCPF(String inicial) {
		ArrayList<Paciente> p = new ArrayList<>();
		
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Paciente) {
				if (repositorio.get(i).getCpf().startsWith(inicial)) {
					p.add((Paciente) repositorio.get(i));					
				}
			}
		}
		return p;
	}
	
	public ArrayList<Paciente> pesquisarPacienteNome(String nome) {
		ArrayList<Paciente> p = new ArrayList<>();
		
		for (Pessoa pessoa : repositorio) {
			if(pessoa instanceof Paciente){
				Paciente paciente = (Paciente) pessoa;
				if(paciente.getNome().startsWith(nome)){
					p.add(paciente);
				}
			}
		}
		
		return p;
		
	}
	

	public ArrayList<Recepcionista> listarTodosRecepcionistas() {
		ArrayList<Recepcionista> r = new ArrayList<>();

		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Recepcionista) {
				r.add((Recepcionista) repositorio.get(i));
			}
		}
		return r;
	}

	public void cadastrar(Pessoa cadastro) {
		repositorio.add(cadastro);
	}

	public boolean remover(Pessoa remover) {
		boolean result = false;
		boolean existe = this.existe(remover);
		if (existe == true) {
			repositorio.remove(remover);
			result = true;
		}
		return result;

	}

	public Pessoa pesquisar(String cpf) {
		Pessoa achou = null;
		for (int i = 0; i < repositorio.size() && achou == null; i++) {
			if (repositorio.get(i).getCpf().equals(cpf)) {
				achou = repositorio.get(i);
			}
		}
		return achou;
	}

	public boolean existe(Pessoa pessoa) {
		boolean achou = false;
		if (pesquisar(pessoa.getCpf()) != null) {
			achou = true;
		}
		return achou;
	}

	public boolean existe(String cpf) {
		boolean achou = false;
		if (pesquisar(cpf) != null) {
			achou = true;
		}
		return achou;
	}

	public void atualizar(Pessoa ant, Pessoa novo) {
		if (ant != null && novo != null){
			repositorio.set(repositorio.indexOf(ant), novo);
		}
		else
			throw new IllegalArgumentException();
	}

}
