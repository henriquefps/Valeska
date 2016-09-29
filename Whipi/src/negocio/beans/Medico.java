package negocio.beans;

import java.time.LocalDate;

public class Medico extends Pessoa {

	private int numCRM;
	private Login login;

	public Medico(String nome, String cpf, String rg, String telefone, String celular, char sexo, Endereco endereco,
			LocalDate dataDeNascimento, int numCRM, String senha) {
		super(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
		this.numCRM = numCRM;
		this.login = new Login(cpf, senha, 1);
	}


	public int getNumCRM() {
		return numCRM;
	}

	public void setNumCRM(int numCRM) {
		this.numCRM = numCRM;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String toString() {
		String resultado = "ID: " + this.getId() + "\n";
		resultado += "Nome: " + this.getNome() + "\n";
		resultado += "CPF: " + this.getCpf() + "\n";
		resultado += "CRM: " + this.numCRM + "\n";
		resultado += "RG: " + this.getRg() + "\n";
		resultado += "Sexo: " + this.getSexo() + "\n";
		resultado += "Endereco: " + this.getEndereco() + "\n";
		resultado += "Telefone: " + this.getTelefone() + "\n";
		resultado += "Celular: " + this.getCelular();
		return resultado;
	}

}
