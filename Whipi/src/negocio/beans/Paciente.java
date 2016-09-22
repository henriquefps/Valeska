package negocio.beans;

import java.time.LocalDate;

public class Paciente {
	private String nomeDoPaciente;
	private LocalDate dataDeNascimento;
	private String cpf;
	private String telefone;
	private int id;
	private Endereco endereco;
	
	public Paciente(String nome, String cpf, int id, LocalDate dataDeNascimento, String telefone, Endereco endereco){
		this.nomeDoPaciente = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.telefone = telefone;
		this.id = id;
		this.endereco = endereco;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "Nome do Paciente: " + nomeDoPaciente + ", Data de Nascimento: " + dataDeNascimento + ", CPF: " + cpf
				+ ", telefone=" + telefone + "\n";
	}

	public String getNomeDoPaciente() {
		return nomeDoPaciente;
	}

	public void setNomeDoPaciente(String nomeDoPaciente) {
		this.nomeDoPaciente = nomeDoPaciente;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
