package negocio.beans;

import java.time.LocalDate;

public class Paciente extends Pessoa {
	
	private int id;
	
	public Paciente(String nome, String cpf, String rg, String telefone, String celular, char sexo, Endereco endereco, LocalDate dataDeNascimento, int id){
		super(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		String resultado = "ID: " + this.id + "\n";
		resultado += "Nome: " + this.getNome() + "\n";
		resultado += "CPF: " + this.getCpf() + "\n";
		resultado += "RG: " + this.getRg() + "\n";
		resultado += "Sexo: " + this.getSexo() + "\n";
		resultado += "Endereco: " + this.getEndereco() + "\n";
		resultado += "Telefone: " + this.getTelefone() + "\n";
		resultado += "Celular: " + this.getCelular();
		return resultado;
	}
	
}