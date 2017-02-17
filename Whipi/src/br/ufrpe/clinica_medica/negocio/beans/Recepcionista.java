/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Classe basica que herda atributos e metodos de pessoa e guarda a definicao de recepcionista
 */
package br.ufrpe.clinica_medica.negocio.beans;

import java.time.LocalDate;

public class Recepcionista extends Pessoa {

	private String senha;

	public Recepcionista(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, String senha) {
		super(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
		this.senha = senha;
	}

	public Recepcionista() {
		super(null, null, null, null, null, ' ', null, null);
		this.senha = null;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		String resultado = "ID: " + this.getId() + "\n" + "Nome: " + this.getNome() + "\n";
		resultado += "CPF: " + this.getCpf() + "\n";
		resultado += "RG: " + this.getRg() + "\n";
		resultado += "Sexo: " + this.getSexo() + "\n";
		resultado += this.getEndereco() + "\n";
		resultado += "Telefone: " + this.getTelefone() + "\n";
		resultado += "Celular: " + this.getCelular();
		return resultado;
	}

}
