package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.exceptions.CJAException;
import br.ufrpe.clinica_medica.exceptions.CNEException;
import br.ufrpe.clinica_medica.exceptions.ECException;
import br.ufrpe.clinica_medica.exceptions.EMEException;
import br.ufrpe.clinica_medica.exceptions.EMNEException;
import br.ufrpe.clinica_medica.exceptions.NCDException;
import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.EspecialidadeMedico;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;

public class FachadaClinicaMedica {

	private GerenciamentoConsulta consulta;
	private GerenciamentoMedico medico;
	private GerenciamentoPaciente paciente;
	private GerenciamentoRecepcionista recepcionista;
	private GerenciamentoLogin login;
	private GerenciamentoEspecialidadeMedico especialidade;

	private static FachadaClinicaMedica instance;

	private FachadaClinicaMedica() {
		this.consulta = new GerenciamentoConsulta();
		this.medico = new GerenciamentoMedico();
		this.paciente = new GerenciamentoPaciente();
		this.recepcionista = new GerenciamentoRecepcionista();
		this.login = new GerenciamentoLogin();
		this.especialidade = new GerenciamentoEspecialidadeMedico();
	}

	public static FachadaClinicaMedica getInstance() {
		if (instance == null) {
			instance = new FachadaClinicaMedica();
		}
		return instance;
	}

	public ArrayList<Consulta> consultasDoDia(Medico medico) throws NCDException {
		return this.consulta.consultasDoDia(medico);
	}

	public void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime horario) throws ECException {
		this.consulta.cadastrarConsulta(medico, paciente, horario);
	}

	public void removerConsulta(String cpfDoPaciente, LocalDate dia) throws PNEException, CNEException {
		this.consulta.removerConsulta(cpfDoPaciente, dia);
	}

	public void alterarConsulta(Consulta consulta, Medico novoMedico, LocalDateTime novoHorario)
			throws PNEException, CNEException {
		this.consulta.alterarConsulta(consulta, novoMedico, novoHorario);
	}

	public Consulta pesquisarConsulta(String cpfDoPaciente, LocalDate dia) {
		return this.consulta.pesquisarConsulta(cpfDoPaciente, dia);
	}

	public void realizarConsulta(Consulta consulta, String descricao) throws CNEException {
		this.consulta.realizarConsulta(consulta, descricao);
	}

	public ArrayList<Consulta> consultasComMedicoNoDia(Medico medico, LocalDate dia) throws PNEException, NCDException {
		return this.consulta.consultasComMedicoNoDia(medico, dia);
	}

	public void cadastrarMedico(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, int numCRM, int consultasPorDia, String senha, ArrayList<EspecialidadeMedico> especialidade)
			throws PJCException {
		this.medico.cadastrarMedico(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento, numCRM,
				consultasPorDia, senha, especialidade);
	}

	public void removerMedico(Medico medico) throws PNEException {
		this.medico.removerMedico(medico);
	}

	public void alterarMedico(String cpf, Medico novo) throws PNEException {
		this.medico.alterarMedico(cpf, novo);
	}

	public Medico pesquisarMedico(String cpf) {
		return this.medico.pesquisarMedico(cpf);
	}

	public ArrayList<Medico> pesquisarMedicoNome(String nome) {
		return medico.pesquisarMedicoNome(nome);
	}
	
	public ArrayList<Medico> listarMedicos() {
		return medico.listarMedicos();
	}

	public void cadastrarPaciente(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento) throws PJCException {
		this.paciente.cadastrarPaciente(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
	}

	public Paciente pesquisarPaciente(String cpf) {
		return this.paciente.pesquisarPaciente(cpf);
	}

	public void removerPaciente(Paciente a) throws PNEException {
		this.paciente.removerPaciente(a);
	}

	public void alterarPaciente(String cpf, Paciente novo) throws PNEException {
		this.paciente.alterarPaciente(cpf, novo);
	}

	public ArrayList<Paciente> pesquisarPacienteNome(String nome) {
		return paciente.pesquisarPacienteNome(nome);
	}
	
	public ArrayList<Paciente> listarPacientes() {
		return paciente.listarPacientes();
	}

	public void cadastrarRecepcionista(String nome, String cpf, String rg, String telefone, String celular, char sexo,
			Endereco endereco, LocalDate dataDeNascimento, String senha) throws PJCException {
		this.recepcionista.cadastrarRecepcionista(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento,
				senha);
	}

	public Recepcionista pesquisarRecepcionista(String cpf) {
		return this.recepcionista.pesquisarRecepcionista(cpf);
	}

	public void removerRecepcionista(Recepcionista a) throws PNEException {
		this.recepcionista.removerRecepcionista(a);
	}

	public void alterarRecepcionista(String cpf, Recepcionista novo) throws PNEException {
		this.recepcionista.alterarRecepcionista(cpf, novo);
	}

	public Pessoa efetuarLogin(String cpf, String senha) {
		return this.login.efetuarLogin(cpf, senha);
	}

	public ArrayList<Recepcionista> listarRecepcionistas() {
		return recepcionista.listarRecepcionistas();
	}

	public void trabalharDiaX(Medico medico, int diaDaSemana, int horaI, int minutoI, int horaF, int minutoF) {
		this.medico.trabalharDiaX(medico, diaDaSemana, horaI, minutoI, horaF, minutoF);
	}

	public void cancelarDiaDeTrabalhoX(Medico medico, int diaDaSemana) throws NCDException, CJAException {
		this.medico.cancelarDiaDeTrabalhoX(medico, diaDaSemana);
		ArrayList<Consulta> cons = consulta.consultasDoDia(medico);
		for (int i = 0; i < cons.size(); i++) {
			if (cons.get(i).getHorario().getDayOfWeek().getValue() - 1 == diaDaSemana && !cons.get(i).foiRealizada()) {
				throw new CJAException(cons.get(i));
			}
		}
	}

	public void cancelarTodasAsConsultasDeUmDia(Medico med, LocalDate dia)
			throws PNEException, NCDException, CNEException {
		ArrayList<Consulta> cons = consulta.consultasComMedicoNoDia(med, dia);
		if (cons != null) {
			for (int i = 0; i < cons.size(); i++) {
				consulta.removerConsulta(cons.get(i));
			}
		}
	}

	public ArrayList<Consulta> listarConsultas() {
		return consulta.listarConsultas();
	}
	
	public void cadastrarEspecialidade(String nome) throws EMEException{
		especialidade.cadastrar(nome);
	}
	
	public void removerEspecialidade(EspecialidadeMedico esp) throws EMNEException{
		especialidade.remover(esp);
	}
	
	public void alterarEspecialidade(String nome, EspecialidadeMedico novo) throws EMNEException{
		especialidade.atualizar(nome, novo);
	}
	
	public ArrayList<EspecialidadeMedico> pesquisarEspecialidade(String nome){
		return especialidade.pesquisar(nome);
	}
	
	public ArrayList<EspecialidadeMedico> listarTodosEspecialidade(){
		return especialidade.listarTodos();
	}
}