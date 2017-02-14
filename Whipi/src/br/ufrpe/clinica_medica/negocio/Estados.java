package br.ufrpe.clinica_medica.negocio;

import java.util.ArrayList;

public enum Estados {
	AC , AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PE, PI, PR, RJ, RN, RS, RO, RR, SC, SP, SE, TO;
	
	
	
	public static ArrayList<String> pegarEstados(){
		
		ArrayList<String> est = new ArrayList<>();
		Estados array[] = Estados.values();
		for (Estados estados : array) {
			est.add(estados.name());
		}
		
		return est;
		
	}
	
}

