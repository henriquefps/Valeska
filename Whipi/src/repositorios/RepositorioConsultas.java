package repositorios;
import java.util.ArrayList;
import beans.*;

public class RepositorioConsultas {
	private static ArrayList<Consulta> lista = new ArrayList<Consulta>();
	
	public void add(Consulta consulta){
		lista.add(consulta);
	}
	
	public ArrayList<Consulta> getConsultasComMedico(Medico medico){
		ArrayList<Consulta> comMedico = new ArrayList<Consulta>();
		
		for(int i = 0; i < RepositorioConsultas.lista.size(); i++){
			if(RepositorioConsultas.lista.get(i).getMedico() == medico){
				comMedico.add(RepositorioConsultas.lista.get(i));
			}
		}
		if(comMedico.isEmpty()){
			return null;
		}
		else{
			return comMedico;
		}
	}
	//retorna um ArrayList com todas as consultas com um determinado medico
	
	public ArrayList<Consulta> getConsultasComPaciente(Cliente paciente){
		ArrayList<Consulta> comPaciente = new ArrayList<Consulta>();
		
		for(int i = 0; i < RepositorioConsultas.lista.size(); i++){
			if(RepositorioConsultas.lista.get(i).getPaciente() == paciente){
				comPaciente.add(RepositorioConsultas.lista.get(i));
			}
		}
		if(comPaciente.isEmpty()){
			return null;
		}
		else{
			return comPaciente;
		}
	}
	//retorna um ArrayList com todas as consultas de um determinado paciente
	
	public ArrayList<Consulta> getLista(){
		return RepositorioConsultas.lista;
	}
}