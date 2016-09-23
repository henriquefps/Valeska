package negocio.beans;

public class Medico {
	
	private int id;
	private String nome;
	private int numCRM;
	private String cpf;
	private String rg;
	private Endereco end;
	private String telefone;
	private String celular;
	
	public Medico(int id, String nome, int numCRM, String cpf, String rg, Endereco end, 
			String telefone, String celular) {
		this.id = id;
		this.nome = nome;
		this.numCRM = numCRM;
		this.cpf = cpf;
		this.rg = rg;
		this.end = end;
		this.telefone = telefone;
		this.celular = celular;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumCRM() {
		return numCRM;
	}

	public void setNumCRM(int numCRM) {
		this.numCRM = numCRM;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cPF) {
		cpf = cPF;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
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
	
	public String toString()
	{
		String resultado = "ID:" + this.id + "\n";
		resultado += "Nome: " + this.nome + "\n";
		resultado += "CPF: " + this.cpf + "\n";
		resultado += "CRM: " + this.numCRM + "\n";
		resultado += "RG: " + this.rg + "\n";
		resultado += "Endereco: " + this.end + "\n";
		resultado += "Telefone: " + this.telefone + "\n";
		resultado += "Celular: " + this.celular;

		return resultado;
	}
	
	
	public boolean equals(Medico m)
	{
		boolean resultado = false;
		
		if(m != null)
		{
			if(this.id == m.getId() && this.numCRM == m.getNumCRM() && this.nome != null && m.getNome() != null &&
					this.nome.equals(m.getNome()) && this.cpf != null && m.getCPF() != null && this.cpf.equals(m.getCPF()))
			{
				resultado = true;
			}
		}
		
		return resultado;
	}
}
