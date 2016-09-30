package negocio;

import repositorio.*;
import negocio.beans.*;

public class CadastroMedico {
	private RepositorioPessoas medicos;

	public CadastroMedico() {
		this.medicos = RepositorioPessoas.getInstance();
	}

	public void cadastrar(Medico medico) {
		if (medico != null && !this.medicos.existe(medico)) {
			this.medicos.cadastrar(medico);
		} else {
			//Usar exceções no futuro
		}
	}
	
	public void remover(String cpf) {
		Pessoa p = this.medicos.pesquisar(cpf);
		if(p != null) {
			this.medicos.remover(p);
		} else {
			//Usar exceções no futuro
		}
	}
}
