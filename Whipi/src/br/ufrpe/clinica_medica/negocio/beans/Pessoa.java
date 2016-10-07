/* Consulta 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Classe basica que guarda a definicao do que é uma pessoa
 */
package br.ufrpe.clinica_medica.negocio.beans;

import java.time.LocalDate;

public class Pessoa {

	private String nome;
	private String cpf;
	private String rg;
	private String telefone;
	private String celular;
	private char sexo;
	private Endereco endereco;
	private LocalDate dataDeNascimento;
	private static int proximoId = 1;
	private int id;

	public Pessoa(String nome, String cpf, String rg, String telefone, String celular, char sexo, Endereco endereco,
			LocalDate dataDeNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
		this.celular = celular;
		this.sexo = sexo;
		this.endereco = endereco;
		this.dataDeNascimento = dataDeNascimento;
		this.id = proximoId;
		proximoId++;
	}

	public int getId() {
		return id;
	}

	public static int getProximoId() {
		return proximoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		
		if(obj != null)	{
			if(obj.getClass() == this.getClass()){
				if(this.cpf != null && this.cpf.equals(((Pessoa)obj).getCpf())){
					res = true;
				}
			}
		}		
		return res;
	}
	
}
