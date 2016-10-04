package negocio.beans;

import java.time.LocalDate;

public class Medico extends Pessoa {

	private int numCRM;
	private int consultasPorDia;
	private String senha;

	public Medico(String nome, String cpf, String rg, String telefone, String celular, char sexo, Endereco endereco,
			LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha) {
		super(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
		this.numCRM = numCRM;
		this.consultasPorDia = consultasPorDia;
		this.senha = senha;
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
		resultado += "Celular: " + this.getCelular();
		return resultado;
	}

}
