/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Classe basica que herda atributos e metodos de pessoa e guarda a definicao de medico
 */
package br.ufrpe.clinica_medica.negocio.beans;

import java.time.LocalDate;
import java.util.ArrayList;

public class Medico extends Pessoa {

	private int numCRM;
	private int consultasPorDia;
	private String senha;
	private DiasDeAtendimento diasDeTrabalho[];
	private ArrayList<EspecialidadeMedico> especialidade;

	
	public Medico(String nome, String cpf, String rg, String telefone, String celular, char sexo, Endereco endereco,
			LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha, ArrayList<EspecialidadeMedico> especialidade) {
		super(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
		this.numCRM = numCRM;
		this.consultasPorDia = consultasPorDia;
		this.senha = senha;
		this.diasDeTrabalho = new DiasDeAtendimento[6];
		for(int i = 0; i < diasDeTrabalho.length; i++)
			diasDeTrabalho[i] = new DiasDeAtendimento();
		this.especialidade = especialidade;
	}

	public Medico() {
		super();
	}

	public int getNumCRM() {
		return numCRM;
	}

	public void setNumCRM(int numCRM) {
		this.numCRM = numCRM;
	}

	public int getConsultasPorDia() {
		return consultasPorDia;
	}

	public void setConsultasPorDia(int consultasPorDia) {
		this.consultasPorDia = consultasPorDia;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public DiasDeAtendimento[] getDiasDeTrabalho() {
		return diasDeTrabalho;
	}

	public ArrayList<EspecialidadeMedico> getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(ArrayList<EspecialidadeMedico> especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		String resultado = "ID: " + this.getId() + "\n";
		resultado += "Nome: " + this.getNome() + "\n";
		resultado += "CPF: " + this.getCpf() + "\n";
		resultado += "CRM: " + this.numCRM + "\n";
		resultado += "RG: " + this.getRg() + "\n";
		resultado += "Sexo: " + this.getSexo() + "\n";
		resultado += this.getEndereco() + "\n";
		resultado += "Telefone: " + this.getTelefone() + "\n";
		resultado += "Celular: " + this.getCelular() + "\n";
		resultado += "Especialidade:\n";
		for (EspecialidadeMedico esp : especialidade) {
			resultado += esp;
		}
		
		return resultado;
	}
}
