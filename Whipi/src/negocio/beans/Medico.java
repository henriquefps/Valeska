package negocio.beans;

public class Medico {
	private int id;
	private String nome;
	private int numCRM;
	private String CPF;
	private String rg;
	private Endereco end;
	private String telefone;
	private String celular;
	
	public Medico(int id, String nome, int numCRM, String cPF, String rg, Endereco end, 
			String telefone, String celular) {
		this.id = id;
		this.nome = nome;
		this.numCRM = numCRM;
		CPF = cPF;
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
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
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
		resultado += "CPF: " + this.CPF + "\n";
		resultado += "CRM: " + this.numCRM + "\n";
		resultado += "RG: " + this.rg + "\n";
		resultado += "Endereço: " + this.end + "\n";
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
					this.nome.equals(m.getNome()) && this.CPF != null && m.getCPF() != null && this.CPF.equals(m.getCPF()))
			{
				resultado = true;
			}
		}
		
		return resultado;
	}
}
