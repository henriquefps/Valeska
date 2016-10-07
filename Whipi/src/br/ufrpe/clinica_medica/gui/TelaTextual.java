package br.ufrpe.clinica_medica.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import br.ufrpe.clinica_medica.negocio.*;
import br.ufrpe.clinica_medica.negocio.beans.*;
import br.ufrpe.clinica_medica.repositorio.*;

public class TelaTextual {
	private static Scanner scan = new Scanner(System.in);
	private GerenciamentoConsulta consultas = new GerenciamentoConsulta();
	private GerenciamentoPaciente paciente = new GerenciamentoPaciente();
	private GerenciamentoMedico doutor = new GerenciamentoMedico();
	private GerenciamentoRecepcionista recep = new GerenciamentoRecepcionista();

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
		while (true) {
			System.out.println("Bem Vindo " + medico.getNome());
			System.out.println("1 - Ver consultas de hoje");
			System.out.println("2 - Ver consultas de outro dia");
			System.out.println("3 - Realizar consulta");
			System.out.println("4 - Atualizar perfil");
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
			} else if (opcao == 3) {
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
			} else if (opcao == 4) {
				System.out.print("Digite seu nome: ");
				String nome = scan.nextLine();
				System.out.println("Digite seu telefone fixo: ");
				String telefone = scan.nextLine();
				System.out.print("Digite seu celular: ");
				String celular = scan.nextLine();
				System.out.print("Digite seu Sexo: ");
				String sexomf = scan.nextLine();
				Character sexo = sexomf.charAt(0);
				sexo = Character.toUpperCase(sexo);
				System.out.print("Digite o nome da sua rua: ");
				String rua = scan.nextLine();
				System.out.print("Digite o CEP da sua residencia");
				String cep = scan.nextLine();
				System.out.println("Digite seu bairro: ");
				String bairro = scan.nextLine();
				System.out.println("Digite sua cidade: ");
				String cidade = scan.nextLine();
				System.out.println("Digite seu estado(PE, SP, AL): ");
				String estado = scan.nextLine();
				System.out.println("Digite o complemento da residencia: ");
				String complemento = scan.nextLine();
				int dia = 0;
				int mes = 0;
				int ano = 0;
				System.out.println("Digite sua data de aniversario.");
				System.out.print("Dia: ");
				dia = scan.nextInt();
				System.out.print("Mes: ");
				mes = scan.nextInt();
				System.out.print("Ano: ");
				ano = scan.nextInt();
				System.out.println("Digite o numero do CRM");
				int numCrm = scan.nextInt();
				System.out.println("Digite o numero de consultas por dia: ");
				int consultasPorDia = scan.nextInt();
				System.out.println("Digite sua nova senha: ");
				String senha = scan.nextLine();
				if (dia <= 31 && mes <= 12) {
					doutor.alterarMedico(medico, nome, medico.getCpf(), medico.getRg(), telefone, celular, sexo,
							new Endereco(rua, cidade, bairro, estado, cep, complemento), LocalDate.of(ano, mes, dia),
							numCrm, consultasPorDia, senha);
				}
			} else if (opcao == 5) {
				System.out.println("Tem certeza que deseja sair?");
				System.out.println("1 - Sim");
				System.out.println("outro - Nao");
				int op2 = scan.nextInt();
				scan.nextLine();
				if (op2 == 1) {
					break;
				}
			}
		}
	}

	public void recepcionista(Recepcionista recepcionista) {
		int op = 0;
		do {
			System.out.println("Bem Vindo " + recepcionista.getNome());
			System.out.println("1 - Cadastrar consultas");
			System.out.println("2 - Atualizar consultas");
			System.out.println("3 - Cancelar consultas");
			System.out.println("4 - Atualizar perfil");
			System.out.println("5 - Sair");
			System.out.print("Selecionou: ");
			op = scan.nextInt();

			// Marcar consultas - nessa opção o usuário(recepcionista)
			// interage
			// com o paciente e o médico.
			if (op == 1) {
				System.out.println(recepcionista.getNome() + " voce escolheu a opcao de cadastrar consultas.");
				System.out.println("Digite o cpf do medico : ");
				String cpfMedico = scan.next();
				System.out.println("Digite o cpf do paciente : ");
				String cpfPaciente = scan.next();
				System.out.println("Digite o ano : ");
				int anoC = scan.nextInt();
				System.out.println("Digite o mês : ");
				int mesC = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC = scan.nextInt();
				System.out.println("Digite a hora : ");
				int horaC = scan.nextInt();
				System.out.println("Digite o minuto : ");
				int minC = scan.nextInt();
				Medico a = doutor.pesquisarMedico(cpfMedico);
				Paciente b = paciente.pesquisarPaciente(cpfPaciente);
				if (a != null && b != null) {
					consultas.cadastrarConsulta(a, b, LocalDateTime.of(anoC, mesC, diaC, horaC, minC));
				}
			} else if (op == 2) {
				System.out.println(recepcionista.getNome() + "voce escolheu a opcao de alterar consultas.");
				System.out.println("Digite o cpf do paciente : ");
				String cpfPaciente = scan.next();
				System.out.println("Digite o ano : ");
				int anoC = scan.nextInt();
				System.out.println("Digite o mes : ");
				int mesC = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC = scan.nextInt();
				System.out.println("-----Novos Dados-----");
				// colocar opção para checar se esse médico já tem algo
				// marcado
				// nesse horário
				System.out.println("Digite o cpf do novo medico : ");
				String cpfMedico = scan.next();
				System.out.println("Digite o ano : ");
				int anoC2 = scan.nextInt();
				System.out.println("Digite o mes : ");
				int mesC2 = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC2 = scan.nextInt();
				System.out.println("Digite a hora : ");
				int horaC2 = scan.nextInt();
				System.out.println("Digite o minuto : ");
				int minC2 = scan.nextInt();

				// Problema Caso a consulta não existir dará em merda!!
				// consultas.pesquisarConsulta(cpfPaciente,LocalDate.of(anoC,
				// mesC, diaC));
				// LocalDateTime.of(anoC2, mesC2, diaC2, horaC2, minC2);

				boolean result = consultas.alterarConsulta(
						consultas.pesquisarConsulta(cpfPaciente, LocalDate.of(anoC, mesC, diaC)),
						doutor.pesquisarMedico(cpfMedico), LocalDateTime.of(anoC2, mesC2, diaC2, horaC2, minC2));

				if (result == false) {
					System.out.println("Problema ao atualizar!!");
				} else {
					System.out.println("Atualização feita com sucesso!!");
				}

			} else if (op == 3) {
				System.out.println(recepcionista.getNome() + "você escolheu a opção de cancelar consultas.");
				System.out.println("Digite o cpf do paciente : ");
				String cpfPaciente = scan.next();
				System.out.println("Digite o ano : ");
				int anoC = scan.nextInt();
				System.out.println("Digite o mês : ");
				int mesC = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC = scan.nextInt();

				boolean result = consultas.removerConsulta(cpfPaciente, LocalDate.of(anoC, mesC, diaC));

				if (result == false) {
					System.out.println("Problema ao remover");
				} else {
					System.out.println("Remorção feita com sucesso!!");
				}

			} else if (op == 4) {
				System.out.print("Digite seu nome: ");
				String nome = scan.nextLine();
				System.out.println("Digite seu telefone fixo: ");
				String telefone = scan.nextLine();
				System.out.print("Digite seu celular: ");
				String celular = scan.nextLine();
				System.out.print("Digite seu Sexo: ");
				String sexomf = scan.nextLine();
				Character sexo = sexomf.charAt(0);
				sexo = Character.toUpperCase(sexo);
				System.out.print("Digite o nome da sua rua: ");
				String rua = scan.nextLine();
				System.out.print("Digite o CEP da sua residencia");
				String cep = scan.nextLine();
				System.out.println("Digite seu bairro: ");
				String bairro = scan.nextLine();
				System.out.println("Digite sua cidade: ");
				String cidade = scan.nextLine();
				System.out.println("Digite seu estado(PE, SP, AL): ");
				String estado = scan.nextLine();
				System.out.println("Digite o complemento da residencia: ");
				String complemento = scan.nextLine();
				int dia = 0;
				int mes = 0;
				int ano = 0;
				System.out.print("Dia: ");
				dia = scan.nextInt();
				System.out.print("Mes: ");
				mes = scan.nextInt();
				System.out.print("Ano: ");
				ano = scan.nextInt();
				System.out.print("");
				String senha = scan.nextLine();
				if (dia <= 31 && mes <= 12) {
					recep.alterarRecepcionista(recepcionista, nome, recepcionista.getCpf(), recepcionista.getRg(), telefone, celular, sexo, new Endereco(rua, cidade, bairro, estado, cep, complemento), LocalDate.of(ano, mes, dia), senha);
				} else {
					System.out.println("Data invalida.");
				}

			} else if (op == 5) {
				System.out.println("Tem certeza que deseja sair?");
				System.out.println("1 - Sim");
				System.out.println("outro - Nao");
				int op2 = scan.nextInt();
				if (op2 == 1) {
					break;

				}
			}

		} while (true);

	}

	public void adm() {
		while (true) {
			System.out.println("======= ADM =======");
			System.out.println("1 - Medicos");
			System.out.println("2 - Recepcionistas");
			System.out.println("3 - Consultas");
			System.out.println("4 - Pacientes");
			System.out.println("5 - Sair");
			System.out.print("Selecionou: ");
			int opcao = 0;
			opcao = scan.nextInt();

			switch (opcao) {

			case 1:
				admMed();
				break;

			case 2:
				admRec();
				break;

			case 5:
				System.out.println("Tem certeza que deseja sair?");
				System.out.println("1 - Sim");
				System.out.println("outro - Nao");
				int op2 = scan.nextInt();
				if (op2 == 1) {
					return;
				}
				break;

			default:
				System.out.println("Opcao invalida.");
			}
		}
	}

	public void admMed() {
		RepositorioPessoas pessoas = RepositorioPessoas.getInstance();
		while (true) {
			System.out.println("======= ADM-Medicos =======");
			System.out.println("1 - Listar medicos");
			System.out.println("2 - Cadastrar medico");
			System.out.println("3 - Editar medico");
			System.out.println("4 - Deletar medico");
			System.out.println("5 - Voltar");
			System.out.print("Selecionou: ");
			int opcao = 0;
			opcao = scan.nextInt();
			scan.nextLine();

			if (opcao == 1) {
				if (pessoas.getListaMedicos() != null) {
					System.out.println(pessoas.getListaMedicos());
				} else {
					System.out.println("Nao ha medicos cadastrados no sistema.");
				}
				
			} else if (opcao == 2) {
				System.out.print("Digite o CPF: ");
				String cpf = scan.nextLine();
				System.out.print("Digite o RG: ");
				String rg = scan.nextLine();
				System.out.print("Digite o nome: ");
				String nome = scan.nextLine();
				System.out.println("Digite o telefone fixo: ");
				String telefone = scan.nextLine();
				System.out.print("Digite o celular: ");
				String celular = scan.nextLine();
				System.out.print("Digite o Sexo: ");
				String sexomf = scan.nextLine();
				Character sexo = sexomf.charAt(0);
				sexo = Character.toUpperCase(sexo);
				System.out.print("Digite o nome da sua rua: ");
				String rua = scan.nextLine();
				System.out.print("Digite o CEP da sua residencia");
				String cep = scan.nextLine();
				System.out.println("Digite o bairro: ");
				String bairro = scan.nextLine();
				System.out.println("Digite a cidade: ");
				String cidade = scan.nextLine();
				System.out.println("Digite o estado(PE, SP, AL): ");
				String estado = scan.nextLine();
				System.out.println("Digite o complemento da residencia: ");
				String complemento = scan.nextLine();
				int dia = 0;
				int mes = 0;
				int ano = 0;
				System.out.println("Digite a data de aniversario.");
				System.out.print("Dia: ");
				dia = scan.nextInt();
				System.out.print("Mes: ");
				mes = scan.nextInt();
				System.out.print("Ano: ");
				ano = scan.nextInt();
				System.out.println("Digite o numero do CRM");
				int numCrm = scan.nextInt();
				System.out.println("Digite o numero de consultas por dia: ");
				int consultasPorDia = scan.nextInt();
				scan.nextLine();
				System.out.println("Digite a senha: ");
				String senha = scan.nextLine();
				doutor.cadastrarMedico(nome, cpf, rg, telefone, celular, sexo,
						new Endereco(rua, cep, bairro, cidade, estado, complemento), LocalDate.of(ano, mes, dia),
						numCrm, consultasPorDia, senha);
				
			} else if (opcao == 3) {
				System.out.println("Digite o CPF do medico: ");
				String cpf = scan.nextLine();
				Medico medico = this.doutor.pesquisarMedico(cpf);
				if (medico == null) {
					System.out.println("CPF invalido.");
				} else {
					System.out.print("Digite seu nome: ");
					String nome = scan.nextLine();
					System.out.println("Digite seu telefone fixo: ");
					String telefone = scan.nextLine();
					System.out.print("Digite seu celular: ");
					String celular = scan.nextLine();
					System.out.print("Digite seu Sexo: ");
					String sexomf = scan.nextLine();
					Character sexo = sexomf.charAt(0);
					sexo = Character.toUpperCase(sexo);
					System.out.print("Digite o nome da sua rua: ");
					String rua = scan.nextLine();
					System.out.print("Digite o CEP da sua residencia");
					String cep = scan.nextLine();
					System.out.println("Digite seu bairro: ");
					String bairro = scan.nextLine();
					System.out.println("Digite sua cidade: ");
					String cidade = scan.nextLine();
					System.out.println("Digite seu estado(PE, SP, AL): ");
					String estado = scan.nextLine();
					System.out.println("Digite o complemento da residencia: ");
					String complemento = scan.nextLine();
					int dia = 0;
					int mes = 0;
					int ano = 0;
					System.out.println("Digite sua data de aniversario.");
					System.out.print("Dia: ");
					dia = scan.nextInt();
					System.out.print("Mes: ");
					mes = scan.nextInt();
					System.out.print("Ano: ");
					ano = scan.nextInt();
					System.out.println("Digite o numero do CRM");
					int numCrm = scan.nextInt();
					System.out.println("Digite o numero de consultas por dia: ");
					int consultasPorDia = scan.nextInt();
					System.out.println("Digite sua nova senha: ");
					String senha = scan.nextLine();
					if (dia <= 31 && mes <= 12) {
						doutor.alterarMedico(medico, nome, medico.getCpf(), medico.getRg(), telefone, celular, sexo,
								new Endereco(rua, cidade, bairro, estado, cep, complemento), LocalDate.of(ano, mes, dia),
								numCrm, consultasPorDia, senha);
					}
				}
				
			} else if (opcao == 4) {
				System.out.print("Digite o CPF: ");
				String cpfMed = scan.nextLine();
				Medico medico = this.doutor.pesquisarMedico(cpfMed);
				if (medico == null) {
					System.out.println("CPF invalido");
				} else {
					System.out.println("Tem certeza que quer excluir o Dr. " + medico.getNome() + "?");
					System.out.println("1 - Sim");
					System.out.println("Outro - nao");
					int opcao2 = scan.nextInt();
					scan.nextLine();
					if (opcao2 == 1) {
						this.doutor.removerMedico(medico);
						System.out.println("Medico removido");
					}
				}
			} else if (opcao == 5) {
				return;
			} else {
				System.out.println("Opcao invalida.");
			}
		}
	}

	public void admRec() {
		System.out.println("1-Listar Recepcionistas");
		System.out.println("2-Cadastrar Recepcionistas");
		System.out.println("3-Editar Recepcionista");
		System.out.println("4-Remover Recepcionista");
		System.out.println("");
	}
}