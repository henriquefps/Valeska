package classesTeste;

import java.time.LocalDate;

import negocio.beans.Cliente;

public class TestCliente {

	public static void main(String[] args) {
		int dia = 2;
		int mes = 6; 
		int ano = 1998;
		LocalDate nascimento = LocalDate.of(ano, mes, dia);
		Cliente teste = new Cliente("Henrique Farias", "22312245534", nascimento, "Masculino");
		System.out.println(teste.toString());
		System.out.println(teste.getCpf());
	}

}
