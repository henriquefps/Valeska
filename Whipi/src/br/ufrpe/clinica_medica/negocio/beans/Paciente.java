/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Classe basica que herda atributos e metodos de pessoa e guarda a definicao de Paciente
 */
package br.ufrpe.clinica_medica.negocio.beans;

import java.time.LocalDate;

public class Paciente extends Pessoa {

	public Paciente(String nome, String cpf, String rg, String telefone, String celular, char sexo, Endereco endereco,
			LocalDate dataDeNascimento) {
		super(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);

	}

	public Paciente() {
		super();
	}

	public String toString() {
		String resultado = "ID: " + this.getId() + "\n";
		resultado += "Nome: " + this.getNome() + "\n";
		resultado += "CPF: " + this.getCpf() + "\n";
		resultado += "RG: " + this.getRg() + "\n";
		resultado += "Sexo: " + this.getSexo() + "\n";
		resultado += this.getEndereco() + "\n";
		resultado += "Telefone: " + this.getTelefone() + "\n";
		resultado += "Celular: " + this.getCelular() + "\n";
		return resultado;
	}

}