package repositorios;
import java.util.ArrayList;
import beans.*;

public class RepositorioConsultas {
	private static ArrayList<Consulta> lista = new ArrayList<Consulta>();
	
	public void add(Consulta consulta){
		RepositorioConsultas.lista.add(consulta);
	}
	
	public Consulta procurar(int id){
		for(int i = 0; i < RepositorioConsultas.lista.size(); i++){
			if(RepositorioConsultas.lista.get(i).getId() == id){
				return RepositorioConsultas.lista.get(i);
			}
		}
		return null;
	}
	
	public void excluir(int id){
		Consulta con = procurar(id);
		if(con != null){
			RepositorioConsultas.lista.remove(con);
		}
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
	
	public ArrayList<Consulta> getConsultasComPaciente(Paciente paciente){
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