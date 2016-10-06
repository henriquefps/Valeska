package br.ufrpe.clinica_medica.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
		
		GerenciamentoConsulta consultas = new GerenciamentoConsulta();
		GerenciamentoPaciente paciente = GerenciamentoPaciente.getInstance();
		GerenciamentoMedico doutor = new GerenciamentoMedico();
		GerenciamentoRecepcionista recep = new GerenciamentoRecepcionista();
		int op = 0;
		do
		{
			System.out.println("Bem Vindo " + recepcionista.getNome());
			System.out.println("1 - Cadastrar consultas");
			System.out.println("2 - Atualizar consultas");
			System.out.println("3 - Cancelar consultas");
			System.out.println("4 - Alterar perfil");
			System.out.println("5 - Sair");
			System.out.print("Selecionou: ");
			op = scan.nextInt();
			
	//Marcar consultas - nessa op��o o usu�rio(recepcionista) interage com o paciente e o m�dico.
			if(op == 1){
				System.out.println(recepcionista.getNome() + "voc� escolheu a op��o de cadastrar consultas.");
				System.out.println("Digite o cpf do m�dico : ");
				String cpfMedico = scan.next();
				System.out.println("Digite o cpf do paciente : ");
				String cpfPaciente = scan.next();
				System.out.println("Digite o ano : ");
				int anoC = scan.nextInt();
				System.out.println("Digite o m�s : ");
				int mesC = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC = scan.nextInt();
				System.out.println("Digite a hora : ");
				int horaC = scan.nextInt();
				System.out.println("Digite o minuto : ");
				int minC = scan.nextInt();
				
				consultas.cadastrarConsulta(doutor.pesquisarMedico(cpfMedico), paciente.pesquisarPaciente(cpfPaciente),LocalDateTime.of(anoC, mesC, diaC, horaC, minC));
			}else if(op == 2){
				System.out.println(recepcionista.getNome() + "voc� escolheu a op��o de alterar consultas.");
			    System.out.println("Digite o cpf do paciente : ");
				String cpfPaciente = scan.next();
				System.out.println("Digite o ano : ");
				int anoC = scan.nextInt();
				System.out.println("Digite o m�s : ");
				int mesC = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC = scan.nextInt();
				System.out.println("-----Novos Dados-----");
				//colocar op��o para checar se esse m�dico j� tem algo marcado nesse hor�rio
				System.out.println("Digite o cpf do novo m�dico : ");
				String cpfMedico = scan.next();
				System.out.println("Digite o ano : ");
				int anoC2 = scan.nextInt();
				System.out.println("Digite o m�s : ");
				int mesC2 = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC2 = scan.nextInt();
				System.out.println("Digite a hora : ");
				int horaC2 = scan.nextInt();
				System.out.println("Digite o minuto : ");
				int minC2 = scan.nextInt();
				
				//Problema Caso a consulta n�o existir dar� em merda!!			
				//consultas.pesquisarConsulta(cpfPaciente,LocalDate.of(anoC, mesC, diaC));
				//LocalDateTime.of(anoC2, mesC2, diaC2, horaC2, minC2);
				
				
				boolean result = consultas.alterarConsulta(consultas.pesquisarConsulta(cpfPaciente,LocalDate.of(anoC, mesC, diaC)),doutor.pesquisarMedico(cpfMedico),LocalDateTime.of(anoC2, mesC2, diaC2, horaC2, minC2));
                
				if(result == false){
					System.out.println("Problema ao atualizar!!");
				}else{
					System.out.println("Atualiza��o feita com sucesso!!");
				}
	
			}else if (op == 3){
				System.out.println(recepcionista.getNome() + "voc� escolheu a op��o de cancelar consultas.");
				System.out.println("Digite o cpf do paciente : ");
				String cpfPaciente = scan.next();
				System.out.println("Digite o ano : ");
				int anoC = scan.nextInt();
				System.out.println("Digite o m�s : ");
				int mesC = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC = scan.nextInt();
				
				boolean result = consultas.removerConsulta(cpfPaciente, LocalDate.of(anoC, mesC, diaC));
				
				if(result == false){
					System.out.println("Problema ao remover");
				}else{
					System.out.println("Remor��o feita com sucesso!!");
				}
				
			
			
			}else if(op == 4){
				
			}else{
				break;
			}
				
			
		}while(true);
		
		
		

	}

	public void adm() {

	}
}
