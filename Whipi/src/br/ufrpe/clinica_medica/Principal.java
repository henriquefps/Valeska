package br.ufrpe.clinica_medica;

import java.time.LocalDate;

import br.ufrpe.clinica_medica.gui.TelaTextual;
import br.ufrpe.clinica_medica.negocio.GerenciamentoMedico;
import br.ufrpe.clinica_medica.negocio.GerenciamentoPaciente;
import br.ufrpe.clinica_medica.negocio.GerenciamentoRecepcionista;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;

public class Principal {
	public static void main(String[] args) {
		TelaTextual tela = new TelaTextual();
		GerenciamentoMedico a = new GerenciamentoMedico();
		a.cadastrarMedico("Dexter", "123", "234231", "33212314", "991239123", 'M',
				new Endereco("Rua amelia", "Recife", "Gracas", "PE", "52012312", "APT1201"), LocalDate.of(1980, 8, 13),
				1337, 10, "123");
		GerenciamentoRecepcionista recep = new GerenciamentoRecepcionista();
		recep.cadastrarRecepcionista("Jurema", "123", "456", "789", "098", 'F',
				new Endereco("Rua sosino", "Recife", "Gracas", "PE", "52012312", "APT1201"), 
				LocalDate.of(1990, 9, 27), "456");
		GerenciamentoPaciente paciente = GerenciamentoPaciente.getInstance();
		paciente.cadastrarPaciente("valeskka", "987","654", "321", "123", 'F',
				new Endereco("Rua guaran�", "Recife", "Gracas", "PE", "52012312", "APT1201") , 
				LocalDate.of(1992, 9, 24));
		
		Pessoa logada = tela.inicio();
		if (logada instanceof Medico) {
			tela.medico((Medico)logada);
		} else if(logada instanceof Recepcionista){
			tela.recepcionista((Recepcionista)logada);
		} else{
			tela.adm();
		}
	}
}
