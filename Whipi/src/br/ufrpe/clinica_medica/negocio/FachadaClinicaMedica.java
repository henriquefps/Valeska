package br.ufrpe.clinica_medica.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;

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
	
	public FachadaClinicaMedica getInstance() {
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
	
}
