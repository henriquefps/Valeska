package repositorio;
import java.time.LocalDateTime;
import java.util.ArrayList;
import negocio.beans.*;

public class RepositorioConsultas {
	private static ArrayList<Consulta> lista = new ArrayList<Consulta>();
	
	public void add(Consulta consulta){
		if(consulta != null){
			RepositorioConsultas.lista.add(consulta);
		}
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
	
	public void excluir(Consulta con){
		if(con != null){
			RepositorioConsultas.lista.remove(con);
		}
	}
	
	public void modificar(Consulta con, Paciente paciente){
		if(con != null && paciente != null){
			con.setPaciente(paciente);
		}
	}
	
	public void modificar(Consulta con, LocalDateTime horario){
		if(con != null){
			con.setHorario(horario);;
		}
	}

	public void modificar(Consulta con, String descricao){
		if(con != null){
			con.setDescricao(descricao);;
		}
	}
	
	public void modificar(Consulta con, Medico medico){
		if(con != null && medico != null){
			con.setMedico(medico);;
		}
	}
	
	public void trocarStatus(Consulta con){
		con.trocarStatus();
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