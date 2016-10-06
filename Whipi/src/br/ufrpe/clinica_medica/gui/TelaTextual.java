package br.ufrpe.clinica_medica.gui;

import java.util.Scanner;
import br.ufrpe.clinica_medica.negocio.*;
import br.ufrpe.clinica_medica.negocio.beans.*;
import br.ufrpe.clinica_medica.repositorio.*;

public class TelaTextual {

	private static Scanner scan = new Scanner(System.in);

	public Pessoa inicio() {
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
			if (individuo != null) {
				if(individuo instanceof Medico && ((Medico)individuo).getSenha().equals(senha)){
					return individuo;
				}else if(individuo instanceof Recepcionista && ((Recepcionista)individuo).getSenha().equals(senha)){
					return individuo;
				} else {
					System.out.println("Usuario ou senha incorreta");
				}
			} else if(senha.equals("admin") && cpf.equals("admin")){
				return null;
			}
			System.out.println("CPF inexistente");
		}
	}

	public void medico(Medico medico) {
		GerenciamentoConsulta consultas = new GerenciamentoConsulta();
		GerenciamentoPaciente paciente = GerenciamentoPaciente.getInstance();
		GerenciamentoMedico doutor = new GerenciamentoMedico();
		
		System.out.println("Bem Vindo " + medico.getNome());
		System.out.println("1 - Ver consultas de hoje");
		System.out.println("2 - Ver consultas de outro dia");
		System.out.println("3 - Realizar consulta");
		System.out.println("4 - Alterar perfil");
		System.out.println("5 - Sair");
		System.out.print("Selecionou: ");
		int opcao = 0;
		opcao = scan.nextInt();
		if (opcao == 1) {
			consultas.consultasDoDia(medico);
		} else if(opcao == 2){
			
		}
	}

	public void recepcionista(Recepcionista recepcionista) {

	}
	
	public void adm(){
		
	}
}
