package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;

public class FachadaClinicaMedica {

	private GerenciamentoConsulta consulta;
	private GerenciamentoMedico medico;
	private GerenciamentoPaciente paciente;
	private GerenciamentoRecepcionista recepcionista;

	private static FachadaClinicaMedica instance;

	private FachadaClinicaMedica() {
		this.consulta = new GerenciamentoConsulta();
		this.medico = new GerenciamentoMedico();
		this.paciente = new GerenciamentoPaciente();
		this.recepcionista = new GerenciamentoRecepcionista();
	}

	public static FachadaClinicaMedica getInstance() {
		if (instance == null) {
			instance = new FachadaClinicaMedica();
		}
		return instance;
	}

	public ArrayList<Consulta> consultasDoDia(Medico medico) {
		return this.consulta.consultasDoDia(medico);
	}

	public void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime horario) {
		this.consulta.cadastrarConsulta(medico, paciente, horario);
	}

	public boolean removerConsulta(String cpfDoPaciente, LocalDate dia) {
		return this.consulta.removerConsulta(cpfDoPaciente, dia);
	}

	public boolean alterarConsulta(Consulta consulta, Medico novoMedico, LocalDateTime novoHorario) {
		return this.consulta.alterarConsulta(consulta, novoMedico, novoHorario);
	}

	public Consulta pesquisarConsulta(String cpfDoPaciente, LocalDate dia) {
		return this.consulta.pesquisarConsulta(cpfDoPaciente, dia);
	}

	public void realizarConsulta(Consulta consulta, String descricao) {
		this.consulta.realizarConsulta(consulta, descricao);
	}

	public String consultasComMedicoNoDia(Medico medico, LocalDate dia) {
		return this.consulta.consultasComMedicoNoDia(medico, dia);
	}

	public void cadastrarMedico(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha) {
		this.medico.cadastrarMedico(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM,
				consultasPorDia, senha);
	}

	public void removerMedico(Medico medico) {
		this.medico.removerMedico(medico);
	}

	public void alterarMedico(Medico medico, String nome, String novocpf, String rg, String telefone, String celular,
			char sexo, Endereco endereco, LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha) {
		this.medico.alterarMedico(medico, nome, novocpf, rg, telefone, celular, sexo, endereco, dataDeNascimento,
				numCRM, consultasPorDia, senha);
	}

	public Medico pesquisarMedico(String cpf) {
		return this.medico.pesquisarMedico(cpf);
	}

	public void cadastrarPaciente(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento) {
		this.paciente.cadastrarPaciente(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
	}

	public Paciente pesquisarPaciente(String cpf) {
		return this.paciente.pesquisarPaciente(cpf);
	}

	public void removerPaciente(Paciente a) {
		this.paciente.removerPaciente(a);
	}

	public void alterarPaciente(Paciente a, String nome, String novoCpf, String rg, String telefone, String celular,
			char sexo, Endereco endereco, LocalDate dataDeNascimento) {
		this.paciente.alterarPaciente(a, nome, novoCpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
	}

	public void cadastrarRecepcionista(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, String senha) {
		this.recepcionista.cadastrarRecepcionista(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento,
				senha);
	}

	public Recepcionista pesquisarRecepcionista(String cpf) {
		return this.recepcionista.pesquisarRecepcionista(cpf);
	}

	public void removerRecepcionista(Recepcionista a) {
		this.recepcionista.removerRecepcionista(a);
	}

	public void alterarRecepcionista(Recepcionista a, String nome, String novoCpf, String rg, String telefone,
			String celular, char sexo, Endereco endereco, LocalDate dataDeNascimento, String senha) {
		this.recepcionista.alterarRecepcionista(a, nome, novoCpf, rg, telefone, celular, sexo, endereco,
				dataDeNascimento, senha);
	}

}
