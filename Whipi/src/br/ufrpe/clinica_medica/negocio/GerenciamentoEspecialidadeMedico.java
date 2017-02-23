package br.ufrpe.clinica_medica.negocio;

import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.EMEException;
import br.ufrpe.clinica_medica.exceptions.EMNEException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.beans.EspecialidadeMedico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.repositorio.IRepositorioEspecialidadeMedico;
import br.ufrpe.clinica_medica.repositorio.RepositorioEspecialidadeMedico;

public class GerenciamentoEspecialidadeMedico {

	private IRepositorioEspecialidadeMedico especialidades;

	public GerenciamentoEspecialidadeMedico() {
		especialidades = RepositorioEspecialidadeMedico.getInstance();
	}

	public void cadastrar(String nome) throws EMEException {
		if (nome == null)
			throw new IllegalArgumentException("Nome de Especialidade inválido");
		if (!especialidades.existe(nome)) {
			especialidades.cadastrar(new EspecialidadeMedico(nome));
			especialidades.salvarEmArquivo();
		} else
			throw new EMEException("Especialidade " + nome + " já cadastrada!");

	}

	public ArrayList<EspecialidadeMedico> pesquisar(String nome) {
		if (nome == null)
			throw new IllegalArgumentException("Nome de Especialidade inválido");

		return especialidades.pesquisar(nome);
	}

	public void remover(EspecialidadeMedico especialidade) throws EMNEException {
		if (especialidade != null) {
			especialidades.remover(especialidade);
			especialidades.salvarEmArquivo();
		} else {
			throw new EMNEException("Especialidade não encontrada no sistema");
		}
	}

	public ArrayList<EspecialidadeMedico> listarTodos() {
		return especialidades.listarTodos();
	}

	public void atualizar(String nome, EspecialidadeMedico e) throws EMNEException {
		EspecialidadeMedico em = null;
		if (nome != null) {
			em = especialidades.pesquisar(nome).get(0);
		} else {
			throw new IllegalArgumentException(" inválido");
		}
		if (em != null) {
			if (em instanceof EspecialidadeMedico) {
				especialidades.atualizar(em, e);
				especialidades.salvarEmArquivo();
			} else {
				// Depois mudar a mensagem, estou sem criatividade
				throw new IllegalArgumentException("Nome não é de uma EspecialidadeMedico");
			}
		} else {
			throw new EMNEException("Especialidade não encontrada");
		}
	}
}
