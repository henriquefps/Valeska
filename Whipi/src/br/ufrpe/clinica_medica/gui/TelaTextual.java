package br.ufrpe.clinica_medica.gui;

import java.util.Scanner;
import br.ufrpe.clinica_medica.negocio.*;
import br.ufrpe.clinica_medica.negocio.beans.*;
import br.ufrpe.clinica_medica.repositorio.*;

public class TelaTextual {

	private static Scanner scan = new Scanner(System.in);

	public void inicio() {
		String cpf;
		String senha;

		while (true) {
			System.out.println("===== Clinica Boladona =====");
			System.out.printf("CPF: ");
			cpf = scan.nextLine();
			System.out.printf("Senha: ");
			senha = scan.nextLine();

			RepositorioPessoas pessoas = RepositorioPessoas.getInstance();

			Pessoa individuo = pessoas.pesquisar(cpf);
			if (individuo instanceof Medico && individuo != null) {
				if (((Medico) individuo).getSenha() != senha) {
					System.out.println("Senha incorreta.");
				} else {
					medico((Medico) individuo);
				}
			} else if (individuo instanceof Recepcionista && individuo != null) {
				if (((Recepcionista) individuo).getSenha() != senha) {
					System.out.println("Senha incorreta.");
				} else {
					recepcionista((Recepcionista) individuo);
				}
			} else {
				System.out.println("CPF invalido.");
			}
		}
	}

	public void medico(Medico medico) {

	}

	public void recepcionista(Recepcionista recepcionista) {

	}
	
	public void adm(){
		
	}
}
