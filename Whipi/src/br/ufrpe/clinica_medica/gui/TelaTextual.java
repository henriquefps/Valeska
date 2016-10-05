package br.ufrpe.clinica_medica.gui;

import java.util.Scanner;

public class TelaTextual {
	
	private static Scanner scan;

	public static void main(String[] args){
		String cpf;
		String senha;
		scan = new Scanner(System.in);
		
		
		System.out.println("===== Clinica Boladona =====");
		System.out.printf("CPF: ");
		cpf = scan.nextLine();
		System.out.printf("Senha: ");
		senha = scan.nextLine();
	}
}
