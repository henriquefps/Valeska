import java.time.LocalDate;

public class Cliente {
	private String nome;
	private LocalDate dataDeNascimento;
	private String sexo;
	private String cpf;
	//acho que o cliente não precisa acessar o sistema, então não precisa de senha
	
	public Cliente(String nome, String cpf, LocalDate nascimento, String sexo){
		this.nome = nome;
		this.dataDeNascimento = nascimento;
		this.cpf = cpf;
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public String getCpf() {
		return cpf;
	}
	public String toStringLocalDate(){
		return this.dataDeNascimento.getDayOfMonth() + "/" + this.dataDeNascimento.getMonth() + "/" + this.dataDeNascimento.getYear();
	}
	
	public String toString(){
		return "Cliente: " + this.nome + "Data de nascimento: " + this.toStringLocalDate() + "Sexo: " + this.sexo;
	}
}
