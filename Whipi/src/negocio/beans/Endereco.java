package negocio.beans;

public class Endereco {
	private String rua;
	private String cidade;
	private String bairro;
	private String estado;
	private String cep;
	private String complemento;
	
	public Endereco(String rua, String cidade, String bairro, String cep, String complemento){
		this.rua = rua;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.complemento = complemento;
	}

	public boolean equals(Endereco b){
		if(cep == b.getCep() && complemento == b.getComplemento()){
			return true;
		}
	return false;
	}

	public String toString() {
		return "Endereco.\nRua: " + rua + "\nCidade: " + cidade + "\nBairro: " + bairro + "\nCep: " + cep + "\nComplemento: "
				+ complemento + "\n";
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
