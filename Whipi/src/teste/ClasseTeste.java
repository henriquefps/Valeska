package teste;

import java.time.LocalDate;
import java.time.LocalDateTime;

import negocio.beans.Consulta;
import negocio.beans.Endereco;
import negocio.beans.Login;
import negocio.beans.Medico;
import negocio.beans.Paciente;
import negocio.beans.Recepcionista;

public class ClasseTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nome = "Joo",rg = "12345",cpf = "123456",telefone = "1234567";
		char sexo = 'm';
		int idade = 20;
		Recepcionista a = new Recepcionista(nome,cpf,rg,telefone,sexo,idade, new Login(cpf, "1235", 2));
		Recepcionista b = new Recepcionista(nome,"132432",rg,telefone,sexo,idade, new Login("132432", "555", 2));
		
		boolean resultado = a.equals(b);
		
		System.out.println(resultado);
		
		b.setCpf(cpf);
		
		resultado = a.equals(b);
		
		System.out.println(resultado);
		
		
		Paciente c = new Paciente("Batman", "123124", "1414", 1, LocalDate.now(), "33233323", 
				new Endereco("Batcaverna", "NY", "Broklyn", "4432345", "Sala 01", "33012213"));
		Paciente d = new Paciente("Beto", "3456241", "1231", 2, LocalDate.of(1996, 12, 8), "33232157",
				new Endereco("Batcaverna", "NY", "Broklyn", "4432345", "Sala 02", "88910323"));
		
		Medico e = new Medico(1, "Dexter", 322123, "5566431", "67867", 
				new Endereco("rua dos medicos", "Recife", "Macaxeira", "4432345", "28391871", ""),
				"4121241", "08790872", new Login("5566431", "555", 1));
		
		Consulta c1 = new Consulta(d, LocalDateTime.of(2016, 9, 30, 16, 30), "", e);
		Consulta c2 = new Consulta(c, LocalDateTime.now(), "", e);
		System.out.println(a.toString() + b.toString() + c.toString() + d.toString() + e.toString() + c1.toString() + c2.toString());

	}

}
