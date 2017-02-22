package br.ufrpe.clinica_medica.gui;

import java.time.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.ufrpe.clinica_medica.exceptions.*;
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
				try {
					System.out.println(fachada.consultasDoDia(medico));
				} catch (NCDException ncd) {
					System.out.println(ncd.getMessage());
				}
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
					try {
						System.out.println(fachada.consultasComMedicoNoDia(medico, LocalDate.of(ano, mes, dia)));
					} catch (PNEException pne) {
						System.out.println(pne.getMessage());
					} catch (NCDException ncd) {
						System.out.println(ncd.getMessage());
					}
				} else {
					System.out.println("Data invalida");
				}
			} else if (opcao == 3) {
				try {
					System.out.print("Digite o cpf do paciente: ");
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
						System.out.print("Descreva a consulta: ");
						scan.nextLine();
						String descricao = scan.nextLine();
						fachada.realizarConsulta(fachada.pesquisarConsulta(cpfDoPaciente, LocalDate.of(ano, mes, dia)),
								descricao);
					} else {
						System.out.println("Data invalida");
					}
				} catch (CNEException cne) {
					cne.getMessage();
				}
			} else if (opcao == 4) {
				System.out.print("Digite seu nome: ");
				String nome = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu telefone fixo: ");
				String telefone = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu celular: ");
				String celular = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu sexo: ");
				String sexomf = scan.nextLine();
				scan.nextLine();
				Character sexo = sexomf.charAt(0);
				sexo = Character.toUpperCase(sexo);

				System.out.print("Digite o nome da sua rua: ");
				String rua = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite o CEP da sua residencia: ");
				String cep = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu bairro: ");
				String bairro = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite sua cidade: ");
				String cidade = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu estado(PE, SP, AL): ");
				String estado = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite o complemento da residencia: ");
				String complemento = scan.nextLine();
				scan.nextLine();

				int dia = 0, mes = 0, ano = 0;
				System.out.println("Digite sua data de aniversario.");
				System.out.print("Dia: ");
				dia = scan.nextInt();
				scan.nextLine();

				System.out.print("Mes: ");
				mes = scan.nextInt();
				scan.nextLine();

				System.out.print("Ano: ");
				ano = scan.nextInt();
				scan.nextLine();

				System.out.print("Digite o numero do CRM: ");
				int numCrm = scan.nextInt();
				scan.nextLine();

				System.out.print("Digite o numero de consultas por dia: ");
				int consultasPorDia = scan.nextInt();
				scan.nextLine();

				System.out.print("Digite sua nova senha: ");
				String senha = scan.nextLine();
				scan.nextLine();
				
				System.out.print("Digite sua Especialidade: ");
				String especialidade = scan.nextLine();
				scan.nextLine();
				ArrayList<EspecialidadeMedico> lista = new ArrayList<EspecialidadeMedico>();
				lista.add(new EspecialidadeMedico(especialidade));

				Medico m = new Medico(nome, medico.getCpf(), medico.getRg(), telefone, celular, sexo,
						new Endereco(rua, cidade, bairro, estado, cep, complemento), medico.getDataDeNascimento(),
						medico.getNumCRM(), consultasPorDia, senha, lista);
				try {

					fachada.alterarMedico(medico.getCpf(), m);

				} catch (PNEException a) {
					System.out.println(a.getMessage());
				} catch (DateTimeException a) {
					System.out.println(a.getMessage());
				}
			} else if (opcao == 5) {
				System.out.println("Tem certeza que deseja sair?");
				System.out.println("1 - Sim");
				System.out.println("Outro - Nao");
				int op2;
				try {
					op2 = scan.nextInt();
					scan.nextLine();
					if (op2 == 1) {
						break;
					}
				} catch (InputMismatchException a) {
					op2 = 2;
				}
			}
		}
	}

	public void recepcionista(Recepcionista recepcionista) {
		IRepositorioPessoas pessoas = RepositorioPessoas.getInstance();
		int op = 0;
		do {
			System.out.println("Bem Vindo " + recepcionista.getNome());
			System.out.println("1 - Ver consultas do dia para um medico");
			System.out.println("2 - Ver consultas de um determinado dia para um medico");
			System.out.println("3 - Cadastrar consultas");
			System.out.println("4 - Atualizar consultas");
			System.out.println("5 - Cancelar consultas");
			System.out.println("6 - Pacientes");
			System.out.println("7 - Atualizar perfil");
			System.out.println("8 - Sair");
			System.out.print("Selecionou: ");
			op = scan.nextInt();
			scan.nextLine();

			// Marcar consultas - nessa opção o usuário(recepcionista)
			// interage
			// com o paciente e o médico.
			if (op == 1) {
				System.out.print("Digite o CPF do medico: ");
				String cpf = scan.nextLine();
				Medico medico = this.fachada.pesquisarMedico(cpf);
				try {
					System.out.println(fachada.consultasComMedicoNoDia(medico, LocalDate.now()));
				} catch (PNEException pne) {
					System.out.println(pne.getMessage());
				} catch (NCDException ncd) {
					System.out.println(ncd.getMessage());
				}
			} else if (op == 2) {
				System.out.print("Digite o CPF do medico: ");
				String cpf = scan.nextLine();
				Medico medico = this.fachada.pesquisarMedico(cpf);
				int dia = 0;
				int mes = 0;
				int ano = 0;
				System.out.print("Dia: ");
				dia = scan.nextInt();
				System.out.print("Mes: ");
				mes = scan.nextInt();
				System.out.print("Ano: ");
				ano = scan.nextInt();
				try {
					System.out.println(fachada.consultasComMedicoNoDia(medico, LocalDate.of(ano, mes, dia)));
				} catch (DateTimeException dte) {
					System.out.println("Data invalida");
				} catch (PNEException pne) {
					System.out.println(pne.getMessage());
				} catch (NCDException ncd) {
					System.out.println(ncd.getMessage());
				}
			} else if (op == 3) {

				System.out.println(recepcionista.getNome() + " voce escolheu a opcao de cadastrar consultas.");
				System.out.print("Digite o cpf do medico: ");
				String cpfMedico = scan.next();
				System.out.print("Digite o cpf do paciente: ");
				String cpfPaciente = scan.next();
				System.out.print("Digite o ano: ");
				int anoC = scan.nextInt();
				System.out.print("Digite o mes: ");
				int mesC = scan.nextInt();
				System.out.print("Digite o dia: ");
				int diaC = scan.nextInt();
				System.out.print("Digite a hora: ");
				int horaC = scan.nextInt();
				System.out.print("Digite o minuto: ");
				int minC = scan.nextInt();
				Medico a = fachada.pesquisarMedico(cpfMedico);
				Paciente b = fachada.pesquisarPaciente(cpfPaciente);
				try {
					try {
						fachada.cadastrarConsulta(a, b, LocalDate.of(anoC, mesC, diaC));
					} catch (PNEException | NCDException e) {
						e.printStackTrace();
					}
				} catch (DateTimeException dte) {
					System.out.println("Data ou hora invalida");
				} catch (IllegalArgumentException ile) {
					System.out.println(ile.getMessage());
				} catch (ECException ec) {
					System.out.println(ec.getMessage());
				}
			} else if (op == 4) {
				try {
					System.out.println(recepcionista.getNome() + "voce escolheu a opcao de alterar consultas.");
					System.out.print("Digite o cpf do paciente: ");
					String cpfPaciente = scan.next();
					System.out.print("Digite o ano: ");
					int anoC = scan.nextInt();
					System.out.print("Digite o mes: ");
					int mesC = scan.nextInt();
					System.out.print("Digite o dia: ");
					int diaC = scan.nextInt();
					System.out.println("-----Novos Dados-----");
					System.out.print("Digite o cpf do novo medico: ");
					String cpfMedico = scan.next();
					System.out.print("Digite o ano: ");
					int anoC2 = scan.nextInt();
					System.out.print("Digite o mes: ");
					int mesC2 = scan.nextInt();
					System.out.print("Digite o dia: ");
					int diaC2 = scan.nextInt();
					System.out.print("Digite a hora: ");
					int horaC2 = scan.nextInt();
					System.out.print("Digite o minuto: ");
					int minC2 = scan.nextInt();
					fachada.alterarConsulta(fachada.pesquisarConsulta(cpfPaciente, LocalDate.of(anoC, mesC, diaC)),
							fachada.pesquisarMedico(cpfMedico), LocalDateTime.of(anoC2, mesC2, diaC2, horaC2, minC2));
				} catch (DateTimeException dte) {
					System.out.println("Data ou hora invalida");
				} catch (CNEException cne) {
					System.out.println(cne.getMessage());
				} catch (PNEException pne) {
					System.out.println(pne.getMessage());
				}
			} else if (op == 5) {
				System.out.println(recepcionista.getNome() + "voce escolheu a opcao de cancelar consultas.");
				System.out.print("Digite o cpf do paciente: ");
				String cpfPaciente = scan.next();
				System.out.print("Digite o ano: ");
				int anoC = scan.nextInt();
				System.out.print("Digite o mes: ");
				int mesC = scan.nextInt();
				System.out.print("Digite o dia: ");
				int diaC = scan.nextInt();
				try {
					fachada.removerConsulta(cpfPaciente, LocalDate.of(anoC, mesC, diaC));
				} catch (DateTimeException dte) {
					System.out.println("Data invalida");
				} catch (PNEException pne) {
					System.out.println(pne.getMessage());
				} catch (CNEException cne) {
					System.out.println(cne.getMessage());
				}
			} else if (op == 7) {
				System.out.print("Digite seu nome: ");
				String nome = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu telefone fixo: ");
				String telefone = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu celular: ");
				String celular = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu sexo: ");
				String sexomf = scan.nextLine();
				Character sexo = sexomf.charAt(0);
				sexo = Character.toUpperCase(sexo);
				scan.nextLine();

				System.out.print("Digite o nome da sua rua: ");
				String rua = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite o CEP da sua residencia: ");
				String cep = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu bairro: ");
				String bairro = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite sua cidade: ");
				String cidade = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite seu estado(PE, SP, AL): ");
				String estado = scan.nextLine();
				scan.nextLine();

				System.out.print("Digite o complemento da residencia: ");
				String complemento = scan.nextLine();
				scan.nextLine();

				int dia = 0;
				int mes = 0;
				int ano = 0;
				System.out.print("Dia: ");
				dia = scan.nextInt();
				scan.nextLine();

				System.out.print("Mes: ");
				mes = scan.nextInt();
				scan.nextLine();

				System.out.print("Ano: ");
				ano = scan.nextInt();
				scan.nextLine();

				System.out.print("Digite sua senha: ");
				String senha = scan.nextLine();
				scan.nextLine();

				Recepcionista r = new Recepcionista(nome, recepcionista.getCpf(), recepcionista.getRg(), telefone,
						celular, sexo, new Endereco(rua, cidade, bairro, estado, cep, complemento),
						recepcionista.getDataDeNascimento(), senha);

				try {
					fachada.alterarRecepcionista(recepcionista.getCpf(), r);

				} catch (PNEException pne) {
					System.out.println(pne.getMessage());
				} catch (DateTimeException dte) {
					System.out.println("Data invalida.");
				}

			} else if (op == 8) {
				System.out.println("Tem certeza que deseja sair?");
				System.out.println("1 - Sim");
				System.out.println("Outro - Nao");
				int op2 = scan.nextInt();
				scan.nextLine();
				if (op2 == 1) {
					break;

				}
			} else if (op == 6) {
				int op2 = 0;
				while (true) {
					System.out.println("1 - Listar Pacientes");
					System.out.println("2 - Cadastrar Paciente");
					System.out.println("3 - Remover Paciente");
					System.out.println("4 - Alterar Paciente");
					System.out.println("5 - Voltar");
					System.out.print("Selecionou: ");
					op2 = scan.nextInt();
					scan.nextLine();
					if (op2 == 1) {
						System.out.println(pessoas.listaTodosPacientes());
					} else if (op2 == 2) {
						try {
							System.out.print("Digite o CPF: ");
							String cpf = scan.nextLine();
							System.out.print("Digite o RG: ");
							String rg = scan.nextLine();
							System.out.print("Digite o nome: ");
							String nome = scan.nextLine();
							System.out.print("Digite o telefone fixo: ");
							String telefone = scan.nextLine();
							System.out.print("Digite o celular: ");
							String celular = scan.nextLine();
							System.out.print("Digite o sexo: ");
							String sexomf = scan.nextLine();
							Character sexo = sexomf.charAt(0);
							sexo = Character.toUpperCase(sexo);
							System.out.print("Digite o nome da sua rua: ");
							String rua = scan.nextLine();
							System.out.print("Digite o CEP da sua residencia: ");
							String cep = scan.nextLine();
							System.out.print("Digite o bairro: ");
							String bairro = scan.nextLine();
							System.out.print("Digite a cidade: ");
							String cidade = scan.nextLine();
							System.out.print("Digite o estado(PE, SP, AL): ");
							String estado = scan.nextLine();
							System.out.print("Digite o complemento da residencia: ");
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
							fachada.cadastrarPaciente(nome, cpf, rg, telefone, celular, sexo,
									new Endereco(rua, cidade, bairro, estado, cep, complemento),
									LocalDate.of(ano, mes, dia));
						} catch (PJCException a) {
							System.out.println(a.getMessage());
						}
					} else if (op2 == 3) {
						System.out.print("Digite o cpf do paciente a ser removido: ");
						String cpf = scan.nextLine();
						try {
							fachada.removerPaciente(fachada.pesquisarPaciente(cpf));
						} catch (PNEException pne) {
							System.out.println(pne.getMessage());
						}
					} else if (op2 == 4) {
						System.out.print("Digite o cpf do paciente a ser alterado: ");
						String cpf = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o novo CPF: ");
						String novoCpf = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o RG: ");
						String rg = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o nome: ");
						String nome = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o telefone fixo: ");
						String telefone = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o celular: ");
						String celular = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o sexo: ");
						String sexomf = scan.nextLine();
						Character sexo = sexomf.charAt(0);
						sexo = Character.toUpperCase(sexo);
						scan.nextLine();

						System.out.print("Digite o nome da sua rua: ");
						String rua = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o CEP da sua residencia: ");
						String cep = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o bairro: ");
						String bairro = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite a cidade: ");
						String cidade = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o estado(PE, SP, AL): ");
						String estado = scan.nextLine();
						scan.nextLine();

						System.out.print("Digite o complemento da residencia: ");
						String complemento = scan.nextLine();
						scan.nextLine();

						int dia = 0;
						int mes = 0;
						int ano = 0;
						System.out.println("Digite a data de aniversario.");
						System.out.print("Dia: ");
						dia = scan.nextInt();
						scan.nextLine();

						System.out.print("Mes: ");
						mes = scan.nextInt();
						scan.nextLine();

						System.out.print("Ano: ");
						ano = scan.nextInt();
						scan.nextLine();

						Paciente p = new Paciente(nome, novoCpf, rg, telefone, celular, sexo,
								new Endereco(rua, cidade, bairro, estado, cep, complemento),
								LocalDate.of(dia, mes, ano));

						try {
							fachada.alterarPaciente(cpf, p);
						} catch (PNEException pne) {
							System.out.println(pne.getMessage());
						} catch (DateTimeException dte) {
							System.out.println("Data invalida");
						}
					} else if (op2 == 5) {
						break;
					}

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
				scan.nextLine();
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
		IRepositorioPessoas pessoas = RepositorioPessoas.getInstance();
		while (true) {
			System.out.println("======= ADM-Medicos =======");
			System.out.println("1 - Listar medicos");
			System.out.println("2 - Cadastrar medico");
			System.out.println("3 - Entrar como medico");
			System.out.println("4 - Deletar medico");
			System.out.println("5 - Voltar");
			System.out.print("Selecionou: ");
			int opcao = 0;
			try {
				opcao = scan.nextInt();
			} catch (Exception a) {
				opcao = 10;
			}
			scan.nextLine();

			if (opcao == 1) {
				if (pessoas.listarTodosMedicos() != null) {
					System.out.println(pessoas.listarTodosMedicos());
				} else {
					System.out.println("Nao ha medicos cadastrados no sistema.");
				}

			} else if (opcao == 2) {
				try {
					System.out.print("Digite o CPF: ");
					String cpf = scan.nextLine();
					System.out.print("Digite o RG: ");
					String rg = scan.nextLine();
					System.out.print("Digite o nome: ");
					String nome = scan.nextLine();
					System.out.print("Digite o telefone fixo: ");
					String telefone = scan.nextLine();
					System.out.print("Digite o celular: ");
					String celular = scan.nextLine();
					System.out.print("Digite o sexo: ");
					String sexomf = scan.nextLine();
					Character sexo = sexomf.charAt(0);
					sexo = Character.toUpperCase(sexo);
					System.out.print("Digite o nome da sua rua: ");
					String rua = scan.nextLine();
					System.out.print("Digite o CEP da sua residencia: ");
					String cep = scan.nextLine();
					System.out.print("Digite o bairro: ");
					String bairro = scan.nextLine();
					System.out.print("Digite a cidade: ");
					String cidade = scan.nextLine();
					System.out.print("Digite o estado(PE, SP, AL): ");
					String estado = scan.nextLine();
					System.out.print("Digite o complemento da residencia: ");
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
					System.out.print("Digite o numero do CRM: ");
					int numCrm = scan.nextInt();
					System.out.print("Digite o numero de consultas por dia: ");
					int consultasPorDia = scan.nextInt();
					scan.nextLine();
					System.out.print("Digite a senha: ");
					String senha = scan.nextLine();
					System.out.print("Digite a senha: ");
					String especialidade = scan.nextLine();
					ArrayList<EspecialidadeMedico> lista = new ArrayList<EspecialidadeMedico>();
					lista.add(new EspecialidadeMedico(especialidade));
					fachada.cadastrarMedico(nome, cpf, rg, telefone, celular, sexo,
							new Endereco(rua, cep, bairro, cidade, estado, complemento), LocalDate.of(ano, mes, dia),
							numCrm, consultasPorDia, senha, lista);
				} catch (InputMismatchException campoInvalido) {
					System.out.println("Data Invalida");
				} catch (PJCException a) {
					System.out.println(a.getMessage());
				}
			} else if (opcao == 3) {
				System.out.print("Digite o CPF do medico: ");
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
						try {
							this.fachada.removerMedico(medico);
							System.out.println("Medico removido");
						} catch (PNEException pne) {
							System.out.println(pne.getMessage());
						}
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
		IRepositorioPessoas pessoas = RepositorioPessoas.getInstance();
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
				try {
					System.out.print("Digite o CPF: ");
					String cpf = scan.nextLine();
					System.out.print("Digite o RG: ");
					String rg = scan.nextLine();
					System.out.print("Digite o nome: ");
					String nome = scan.nextLine();
					System.out.print("Digite o telefone fixo: ");
					String telefone = scan.nextLine();
					System.out.print("Digite o celular: ");
					String celular = scan.nextLine();
					System.out.print("Digite o sexo: ");
					String sexomf = scan.nextLine();
					Character sexo = sexomf.charAt(0);
					sexo = Character.toUpperCase(sexo);
					System.out.print("Digite o nome da sua rua: ");
					String rua = scan.nextLine();
					System.out.print("Digite o CEP da sua residencia");
					String cep = scan.nextLine();
					System.out.print("Digite o bairro: ");
					String bairro = scan.nextLine();
					System.out.print("Digite a cidade: ");
					String cidade = scan.nextLine();
					System.out.print("Digite o estado(PE, SP, AL): ");
					String estado = scan.nextLine();
					System.out.print("Digite o complemento da residencia: ");
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
					System.out.print("Digite a senha: ");
					String senha = scan.nextLine();
					fachada.cadastrarRecepcionista(nome, cpf, rg, telefone, celular, sexo,
							new Endereco(rua, cidade, bairro, estado, cep, complemento), LocalDate.of(ano, mes, dia),
							senha);
				} catch (PJCException a) {
					System.out.println(a.getMessage());
				}
			} else if (opcao == 3) {
				System.out.print("Digite o CPF do recepcionista: ");
				String cpf = scan.nextLine();
				Recepcionista recepcionista = this.fachada.pesquisarRecepcionista(cpf);
				if (recepcionista == null) {
					System.out.println("CPF invalido.");
				} else {
					recepcionista(recepcionista);
				}

			} else if (opcao == 1) {
				System.out.println(pessoas.listarTodosRecepcionistas());

			} else if (opcao == 4) {
				System.out.print("Digite o cpf do recepcionista a ser removido: ");
				String cpf = scan.nextLine();
				try {
					fachada.removerRecepcionista(fachada.pesquisarRecepcionista(cpf));
					System.out.println("Recepcionista removido");
				} catch (PNEException pne) {
					System.out.println(pne.getMessage());
				}

			} else if (opcao == 5) {
				return;

			} else {
				System.out.println("Opcao invalida.");
			}
		}
	}
}