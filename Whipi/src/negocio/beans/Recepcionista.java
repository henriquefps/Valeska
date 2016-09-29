package negocio.beans;

import java.time.LocalDate;

public class Recepcionista extends Pessoa {
	
	private Login login;

	public Recepcionista(String nome, String cpf, String rg, String telefone, String celular, char sexo, Endereco endereco, LocalDate dataDeNascimento, Login login) {
		super(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
		this.login = login;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	@Override
	public String toString() {
		String resultado = "Nome: " + this.getNome() + "\n";
		resultado += "CPF: " + this.getCpf() + "\n";
		resultado += "RG: " + this.getRg() + "\n";
		resultado += "Sexo: " + this.getSexo() + "\n";
		resultado += "Endereco: " + this.getEndereco() + "\n";
		resultado += "Telefone: " + this.getTelefone() + "\n";
		resultado += "Celular: " + this.getCelular();
		return resultado;
	}

}
