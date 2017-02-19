package br.ufrpe.clinica_medica.negocio.beans;

import java.io.Serializable;

public class EspecialidadeMedico implements Serializable{
	private int id;
	private String nome;
	private static int proximoId = 1;

	public EspecialidadeMedico(String nome){
		id = proximoId;
		this.nome = nome;
		proximoId++;
	}
	public int getId() {
		return id;
	}

	public static int getProximoId() {
		return proximoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){
		String res = "Especialidade: " + nome + "\n";
		
		return res;
	}
	
	public boolean equals(Object obj){
		boolean res = false;
		if(obj != null){
			if(obj instanceof EspecialidadeMedico){
				EspecialidadeMedico em = (EspecialidadeMedico) obj;
				if(this.nome.equals(em.getNome())){
					res = true;
				}
			}
		}
		
		return res;
	}

}
