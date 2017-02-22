/* Endere�o 
 * 
 * Versão 0.1
 * 
 * 6/7/2016
 * 
 * Copyright Whipi® Inc.
 * Todos os direitos reservados.
 * 
 * Classe basica que guarda a definicao do que e um Endereco
 */
package br.ufrpe.clinica_medica.negocio.beans;

import java.io.Serializable;

public class Endereco implements Serializable {

	private String rua;
	private String cidade;
	private String bairro;
	private String estado;
	private String cep;
	private String complemento;

	public Endereco(String rua, String cidade, String bairro, String estado, String cep, String complemento) {
		this.rua = rua;
		this.cidade = cidade;
		this.bairro = bairro;
		this.estado = estado;
		this.cep = cep;
		this.complemento = complemento;
	}

	public boolean equals(Endereco b) {
		boolean achou = false;
		if (b != null) {
			if (this.rua != null && this.cidade != null && this.bairro != null && this.estado != null
					&& this.cep != null)
				if (this.rua.equals(b.getRua()) && this.cidade.equals(b.getCidade())
						&& this.bairro.equals(b.getBairro()) && this.cep.equals(b.getCep())) {
					if (this.complemento == null && b.getComplemento() == null
							|| this.complemento.equals(b.getComplemento()))
						achou = true;
				}
		}
		return achou;
	}

	public String toString() {
		return "Endereco:\nRua: " + rua + "\nCidade: " + cidade + "\nBairro: " + bairro + "\nEstado: " + estado
				+ "\nCep: " + cep + "\nComplemento: " + complemento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
