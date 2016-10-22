package br.ufrpe.clinica_medica.teste;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.ufrpe.clinica_medica.negocio.beans.*;
import br.ufrpe.clinica_medica.repositorio.*;

public class ClasseTeste {

	public static void main(String[] args) {

		Medico m1 = new Medico("Dexter", "123145", "234231", "33212314", "991239123", 'M',
				new Endereco("Rua amelia", "Recife", "Gracas", "PE", "52012312", "APT1201"), LocalDate.of(1980, 8, 13),
				1337, 10, "2345");
		Medico m2 = new Medico("Enfermeira Joy", "655566", "121256", "335678887", "9923413413", 'F',
				new Endereco("Rua da Consolacao", "Recife", "Ibura", "PE", "5224323", ""), LocalDate.of(1992, 12, 24),
				4221, 15, "666");
		Paciente p1 = new Paciente("Henrique", "2132131239", "1257852", "21326364", "912391212", 'M',
				new Endereco("Estrada Do Arraial", "Recife", "Casa Amarela", "PE", "52342342", "APT 501"),
				LocalDate.of(1998, 6, 2));
		Paciente p2 = new Paciente("Airton", "0123123955", "74839930", "385849382", "992948783", 'M',
				new Endereco("Rua das Pernambucanas", "Recife", "Gracas", "PE", "56788632", "APT 33"),
				LocalDate.of(1990, 3, 27));
		Recepcionista r1 = new Recepcionista("Valeska", "69696969696", "666999", "34567452", "948338322", 'F', null,
				LocalDate.of(1985, 10, 23), "77778");

		IRepositorioPessoas registroDePessoas = RepositorioPessoas.getInstance();
		registroDePessoas.cadastrar(p1);
		registroDePessoas.cadastrar(m1);
		registroDePessoas.cadastrar(m2);
		registroDePessoas.cadastrar(r1);
		registroDePessoas.cadastrar(p2);

		System.out.println(registroDePessoas.pesquisar("2132131239").toString());
		registroDePessoas.remover(registroDePessoas.pesquisar("2132131239"));
		System.out.println(registroDePessoas.pesquisar("2132131239")); 
		System.out.println();

		Consulta c1 = new Consulta(((Paciente) registroDePessoas.pesquisar("0123123955")),
				LocalDateTime.of(2016, 10, 5, 16, 00), ((Medico) registroDePessoas.pesquisar("123145")));
		IRepositorioConsultas registroConsultas = RepositorioConsultas.getInstance();
		registroConsultas.cadastrar(c1);
		//System.out.println(registroConsultas.pesquisar(1));
	}

}
