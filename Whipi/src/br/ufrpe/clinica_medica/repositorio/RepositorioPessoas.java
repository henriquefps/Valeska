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

import java.time.LocalDate;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;

public class RepositorioPessoas implements IRepositorioPessoas {

	private ArrayList<Pessoa> repositorio;

	private static RepositorioPessoas instance;

	private RepositorioPessoas() {
		this.repositorio = new ArrayList<Pessoa>();
		Pessoa admin = new Pessoa("admin", "admin", null, null, null, 'm', null, null);
		repositorio.add(admin);
	}

	public static IRepositorioPessoas getInstance() {
		if (instance == null) {
			instance = new RepositorioPessoas();
		}
		return instance;
	}
	
	public ArrayList<Medico> getListaMedicos() {		
		ArrayList<Medico> m = new ArrayList<>();
		
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Medico) {
				m.add((Medico)repositorio.get(i));
			}
		}
		return m;
	}
	
	public ArrayList<Paciente> getListaPacientes() {		
		ArrayList<Paciente> p = new ArrayList<>();
		
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Paciente) {
				p.add((Paciente)repositorio.get(i));
			}
		}
		return p;
	}
	
	public ArrayList<Recepcionista> getListaRecepcionista() {		
		ArrayList<Recepcionista> r = new ArrayList<>();
		
		for (int i = 0; i < repositorio.size(); i++) {
			if (repositorio.get(i) instanceof Recepcionista) {
				r.add((Recepcionista)repositorio.get(i));
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
		if (existe == true){
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
		if(ant != null && novo != null)
			ant = novo;
		else
			throw new IllegalArgumentException();
	}
}
