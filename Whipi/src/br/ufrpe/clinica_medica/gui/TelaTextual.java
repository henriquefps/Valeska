package br.ufrpe.clinica_medica.gui;

import java.time.LocalDate;
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
				if (individuo instanceof Medico && ((Medico) individuo).getSenha().equals(senha)) {
					return individuo;
				} else if (individuo instanceof Recepcionista && ((Recepcionista) individuo).getSenha().equals(senha)) {
					return individuo;
				} else {
					System.out.println("Usuario ou senha incorreta");
				}
			} else if (senha.equals("admin") && cpf.equals("admin")) {
				return null;
			}
			System.out.println("CPF inexistente");
		}
	}

	public void medico(Medico medico) {
		GerenciamentoConsulta consultas = new GerenciamentoConsulta();
		GerenciamentoPaciente paciente = GerenciamentoPaciente.getInstance();
		GerenciamentoMedico doutor = new GerenciamentoMedico();
		while (true) {
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
				System.out.println(consultas.consultasComMedicoNoDia(medico, LocalDate.now()));
			} else if (opcao == 2) {
				int dia = 0;
				int mes = 0;
				int ano = 0;
				System.out.print("Dia: ");
				dia = scan.nextInt();
				System.out.print("Mes: ");
				mes = scan.nextInt();
				System.out.print("Ano: ");
				ano = scan.nextInt();
				if (dia <= 31 && mes <= 12) {
					System.out.println(consultas.consultasComMedicoNoDia(medico, LocalDate.of(ano, mes, dia)));
				} else {
					System.out.println("Data invalida");
				}
			} else if(opcao == 3){
				String cpfDoPaciente = scan.nextLine();
				int dia = 0;
				int mes = 0;
				int ano = 0;
				System.out.print("Dia: ");
				dia = scan.nextInt();
				System.out.print("Mes: ");
				mes = scan.nextInt();
				System.out.print("Ano: ");
				ano = scan.nextInt();
				if (dia <= 31 && mes <= 12) {
					consultas.pesquisarConsulta(cpfDoPaciente, LocalDate.of(ano, mes, dia));
				} else {
					System.out.println("Data invalida");
				}
			} else if(opcao == 4){
				String cpf = medico.getCpf();
			}
		}
	}

	public void recepcionista(Recepcionista recepcionista) {

	}

	public void adm() {

	}
}
