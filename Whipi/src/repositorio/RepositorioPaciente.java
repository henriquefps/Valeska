package repositorio;

import java.time.LocalDate;
import java.util.ArrayList;

import negocio.beans.*;

public class RepositorioPaciente {
	private ArrayList<Paciente> todosOsPacientes = new ArrayList<Paciente>();

	public void cadastrarPaciente(Paciente paciente){
		this.todosOsPacientes.add(paciente);
	}
	public void removerPaciente(Paciente paciente){
		this.todosOsPacientes.remove(paciente);
	}
	public void atualizarPaciente(Paciente paciente, String nome, String cpf, LocalDate dataDeNascimento, String telefone){
		paciente.setNomeDoPaciente(nome);
		paciente.setCpf(cpf);
		paciente.setDataDeNascimento(dataDeNascimento);
		paciente.setTelefone(telefone);
	}
	public Paciente pesquisarPeloNome(String nomeDoPaciente){
		Paciente aux = null;
		for (int i = 0; i < todosOsPacientes.size(); i++) {
			if(todosOsPacientes.get(i).getNomeDoPaciente() == nomeDoPaciente){
				aux = todosOsPacientes.get(i);
				break;
			}
		}
		return aux;
	}
}
