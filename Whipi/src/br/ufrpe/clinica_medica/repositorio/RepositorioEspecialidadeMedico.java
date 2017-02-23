package br.ufrpe.clinica_medica.repositorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.EspecialidadeMedico;

public class RepositorioEspecialidadeMedico implements Serializable, IRepositorioEspecialidadeMedico {

	private ArrayList<EspecialidadeMedico> lista;

	private static RepositorioEspecialidadeMedico instance;

	private RepositorioEspecialidadeMedico() {
		lista = new ArrayList<>();
	}

	public static IRepositorioEspecialidadeMedico getInstance() {
		if (instance == null) {
			instance = lerDoArquivo();
		}

		return instance;

	}

	private static RepositorioEspecialidadeMedico lerDoArquivo() {
		File f = new File("EspecialidadeMedico.dat");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RepositorioEspecialidadeMedico rem = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			rem = (RepositorioEspecialidadeMedico) o;
		} catch (Exception e) {
			rem = new RepositorioEspecialidadeMedico();
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

		return rem;
	}

	@Override
	public void cadastrar(EspecialidadeMedico em) {
		lista.add(em);

	}

	@Override
	public void remover(EspecialidadeMedico em) {
		lista.remove(em);
	}

	@Override
	public ArrayList<EspecialidadeMedico> pesquisar(String nome) {
		ArrayList<EspecialidadeMedico> em = new ArrayList<>();

		for (EspecialidadeMedico especialidade : lista) {
			if (especialidade.getNome().startsWith(nome)) {
				em.add(especialidade);
			}

		}
		return em;
	}

	@Override
	public ArrayList<EspecialidadeMedico> listarTodos() {
		return lista;
	}

	@Override
	public void salvarEmArquivo() {
		FileOutputStream fos = null;
		ObjectOutputStream ous = null;
		try {
			fos = new FileOutputStream("EspecialidadeMedico.dat");
			ous = new ObjectOutputStream(fos);
			ous.writeObject(instance);

		} catch (Exception e) {
			// TODO
			e.printStackTrace();

		} finally {
			if (ous != null) {
				try {
					ous.close();

				} catch (IOException e) {
					// TODO
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public boolean existe(String nome) {
		boolean achou = false;
		if (!pesquisar(nome).isEmpty()) {
			achou = true;
		}
		return achou;
	}

	@Override
	public void atualizar(EspecialidadeMedico ant, EspecialidadeMedico novo) {
		if (ant == null || novo == null)
			throw new IllegalArgumentException();

		lista.set(lista.indexOf(ant), novo);
	}

}
