package negocio.beans;

import java.time.LocalDate;

public class Paciente {
	private String nomeDoPaciente;
	private LocalDate dataDeNascimento;
	private String cpf;
	private String rg;
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}

	private String telefone;
	private int id;
	private Endereco endereco;
	
	public Paciente(String nome, String cpf, String rg, int id, LocalDate dataDeNascimento, String telefone, Endereco endereco){
		this.nomeDoPaciente = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.telefone = telefone;
		this.id = id;
		this.endereco = endereco;
		this.rg = rg;
	}
	public boolean equals(Paciente b){
		if(b != null){
			if(cpf == b.getCpf() || rg == b.getRg()){
				return true;
			}
			return false;
		}
		return false;
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
		return "Nome do Paciente: " + nomeDoPaciente + "\nData de Nascimento: " + dataDeNascimento + "\nCPF: " + cpf
				+ "\ntelefone: " + telefone + "\n";
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