package br.ufrpe.clinica_medica;

import java.time.LocalDate;

import br.ufrpe.clinica_medica.gui.*;
import br.ufrpe.clinica_medica.negocio.GerenciamentoMedico;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;

public class Principal {
	public static void main(String[] args) {
		TelaTextual tela = new TelaTextual();
		GerenciamentoMedico a = new GerenciamentoMedico();
		a.cadastrarMedico("Dexter", "123", "234231", "33212314", "991239123", 'M',
				new Endereco("Rua amelia", "Recife", "Gracas", "PE", "52012312", "APT1201"), LocalDate.of(1980, 8, 13),
				1337, 10, "123");
		tela.inicio();
	}
}
