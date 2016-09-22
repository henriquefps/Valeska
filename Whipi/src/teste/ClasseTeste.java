package teste;

import negocio.beans.Recepcionista;

public class ClasseTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nome = "João",rg = "12345",cpf = "123456",telefone = "1234567";
		char sexo = 'm';
		int idade = 20;
		double salario = 220.09;
		Recepcionista a = new Recepcionista(nome,cpf,rg,telefone,sexo,idade,salario);
		Recepcionista b = new Recepcionista(nome,"132432",rg,telefone,sexo,idade,salario);
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		boolean resultado = a.equals(b);
		
		System.out.println(resultado);
		
		b.setCpf(cpf);
		
		resultado = a.equals(b);
		
		System.out.println(resultado);
		
	
		
		
		

	}

}
