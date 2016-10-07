package br.ufrpe.clinica_medica.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import br.ufrpe.clinica_medica.negocio.*;
import br.ufrpe.clinica_medica.negocio.beans.*;
import br.ufrpe.clinica_medica.repositorio.*;

public class TelaTextual {

	private static Scanner scan = new Scanner(System.in);

	private FachadaClinicaMedica fachada;

	public TelaTextual() {
		this.fachada = FachadaClinicaMedica.getInstance();
	}

	public Pessoa inicio() {
		String cpf;
		String senha;

		while (true) {
			System.out.println("===== Clinica Boladona =====");
			System.out.printf("CPF: ");
			cpf = scan.nextLine();
			System.out.printf("Senha: ");
			senha = scan.nextLine();

			Pessoa individuo = fachada.efetuarLogin(cpf, senha);
			if (individuo != null) {
				System.out.println("Login realizado com sucesso!");
				return individuo;
			} else {
				System.out.println("Usuario ou senha incorreto!");
			}
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
			scan.nextLine();
			if (opcao == 1) {
				System.out.println(fachada.consultasComMedicoNoDia(medico, LocalDate.now()));
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
					System.out.println(fachada.consultasComMedicoNoDia(medico, LocalDate.of(ano, mes, dia)));
				} else {
					System.out.println("Data invalida");
				}
			} else if (opcao == 3) {
				System.out.print("Digite o cpf do paciente: ");
				scan.nextLine();
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
				System.out.print("Descreva a consulta: ");
				scan.nextLine();
				String descricao = scan.nextLine();
				if (dia <= 31 && mes <= 12) {
					fachada.realizarConsulta(fachada.pesquisarConsulta(cpfDoPaciente, LocalDate.of(ano, mes, dia)), descricao);
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
				scan.nextLine();
				System.out.println("Digite sua nova senha: ");
				String senha = scan.nextLine();
				if (dia <= 31 && mes <= 12) {
					fachada.alterarMedico(medico, nome, medico.getCpf(), medico.getRg(), telefone, celular, sexo,
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
			System.out.println("1 - Ver consultas do dia para um medico");
			System.out.println("2 - Ver consultas de um determinado dia para um medico");
			System.out.println("3 - Cadastrar consultas");
			System.out.println("4 - Atualizar consultas");
			System.out.println("5 - Cancelar consultas");
			System.out.println("6 - Atualizar perfil");
			System.out.println("7 - Sair");
			System.out.print("Selecionou: ");
			op = scan.nextInt();

			// Marcar consultas - nessa opção o usuário(recepcionista)
			// interage
			// com o paciente e o médico.
			if (op == 1) {
				System.out.println("Digite o CPF do medico: ");
				String cpf = scan.nextLine();
				Medico medico = this.fachada.pesquisarMedico(cpf);
				if (medico == null) {
					System.out.println("CPF invalido.");
				} else {
					System.out.println(fachada.consultasComMedicoNoDia(medico, LocalDate.now()));
				}
			} else if (op == 2) {
				System.out.println("Digite o CPF do medico: ");
				String cpf = scan.nextLine();
				Medico medico = this.fachada.pesquisarMedico(cpf);
				if (medico == null) {
					System.out.println("CPF invalido.");
				} else {
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
						System.out.println(fachada.consultasComMedicoNoDia(medico, LocalDate.of(ano, mes, dia)));
					} else {
						System.out.println("Data invalida");
					}
				}
			} else if (op == 3) {
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
				Medico a = fachada.pesquisarMedico(cpfMedico);
				Paciente b = fachada.pesquisarPaciente(cpfPaciente);
				if (a != null && b != null) {
					fachada.cadastrarConsulta(a, b, LocalDateTime.of(anoC, mesC, diaC, horaC, minC));
				}
			} else if (op == 4) {
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

				boolean result = fachada.alterarConsulta(
						fachada.pesquisarConsulta(cpfPaciente, LocalDate.of(anoC, mesC, diaC)),
						fachada.pesquisarMedico(cpfMedico), LocalDateTime.of(anoC2, mesC2, diaC2, horaC2, minC2));

				if (result == false) {
					System.out.println("Problema ao atualizar!!");
				} else {
					System.out.println("Atualização feita com sucesso!!");
				}

			} else if (op == 5) {
				System.out.println(recepcionista.getNome() + "você escolheu a opção de cancelar consultas.");
				System.out.println("Digite o cpf do paciente : ");
				String cpfPaciente = scan.next();
				System.out.println("Digite o ano : ");
				int anoC = scan.nextInt();
				System.out.println("Digite o mês : ");
				int mesC = scan.nextInt();
				System.out.println("Digite o dia : ");
				int diaC = scan.nextInt();

				boolean result = fachada.removerConsulta(cpfPaciente, LocalDate.of(anoC, mesC, diaC));

				if (result == false) {
					System.out.println("Problema ao remover");
				} else {
					System.out.println("Remorção feita com sucesso!!");
				}

			} else if (op == 6) {
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
					fachada.alterarRecepcionista(recepcionista, nome, recepcionista.getCpf(), recepcionista.getRg(),
							telefone, celular, sexo, new Endereco(rua, cidade, bairro, estado, cep, complemento),
							LocalDate.of(ano, mes, dia), senha);
				} else {
					System.out.println("Data invalida.");
				}

			} else if (op == 7) {
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
			System.out.println("3 - Sair");
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

			case 3:
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
			System.out.println("3 - Entrar como medico");
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
				System.out.print("Digite o bairro: ");
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
				fachada.cadastrarMedico(nome, cpf, rg, telefone, celular, sexo,
						new Endereco(rua, cep, bairro, cidade, estado, complemento), LocalDate.of(ano, mes, dia),
						numCrm, consultasPorDia, senha);

			} else if (opcao == 3) {
				System.out.println("Digite o CPF do medico: ");
				String cpf = scan.nextLine();
				Medico medico = this.fachada.pesquisarMedico(cpf);
				if (medico == null) {
					System.out.println("CPF invalido.");
				} else {
					medico(medico);
				}

			} else if (opcao == 4) {
				System.out.print("Digite o CPF: ");
				String cpfMed = scan.nextLine();
				Medico medico = this.fachada.pesquisarMedico(cpfMed);
				if (medico == null) {
					System.out.println("CPF invalido");
				} else {
					System.out.println("Tem certeza que quer excluir o Dr. " + medico.getNome() + "?");
					System.out.println("1 - Sim");
					System.out.println("Outro - nao");
					int opcao2 = scan.nextInt();
					scan.nextLine();
					if (opcao2 == 1) {
						this.fachada.removerMedico(medico);
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
		RepositorioPessoas pessoas = RepositorioPessoas.getInstance();
		while (true) {
			System.out.println("======= ADM-Recepcionistas =======");
			System.out.println("1-Listar Recepcionistas");
			System.out.println("2-Cadastrar recepcionista");
			System.out.println("3-Entrar como recepcionista");
			System.out.println("4-Remover recepcionista");
			System.out.println("5-Voltar");
			System.out.print("Escolheu: ");
			int opcao = scan.nextInt();
			scan.nextLine();

			if (opcao == 2) {
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
				scan.nextLine();
				System.out.println("Digite a senha: ");
				String senha = scan.nextLine();
				fachada.cadastrarRecepcionista(nome, cpf, rg, telefone, celular, sexo,
						new Endereco(rua, cidade, bairro, estado, cep, complemento), LocalDate.of(ano, mes, dia),
						senha);
				
			} else if (opcao == 3) {
				System.out.println("Digite o CPF do recepcionista: ");
				String cpf = scan.nextLine();
				Recepcionista recepcionista = this.fachada.pesquisarRecepcionista(cpf);
				if (recepcionista == null) {
					System.out.println("CPF invalido.");
				} else {
					recepcionista(recepcionista);
				}
				
			} else if (opcao == 1) {
				System.out.println(pessoas.getListaRecepcionista());
				
			} else if (opcao == 4) {
				System.out.print("Digite o cpf do recepcionista a ser removido: ");
				String cpf = scan.nextLine();
				fachada.removerRecepcionista(fachada.pesquisarRecepcionista(cpf));
				
			} else if (opcao == 5) {
				return;
				
			} else {
				System.out.println("Opcao invalida.");
			}
		}
	}
}