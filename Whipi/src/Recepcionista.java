import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Recepcionista {
	private String nome;
	private String cpf;
	private static ArrayList<Consulta> todasAsConsultas = new ArrayList<Consulta>();
	private static ArrayList<Cliente> todosOsClientes = new ArrayList<Cliente>();
	//todasAsConsultas é static pois todos os recepcionistas devem ter acesso a todas as consultas
	private String senha;
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public static ArrayList<Consulta> getTodasAsConsultas() {
		return todasAsConsultas;
	}
	public String getSenha() {
		return senha;
	}
	public boolean marcarConsulta(Medico medico, Cliente paciente, LocalDateTime hora){
		//verificar compatibilidade de horário
		Consulta a = new Consulta(paciente, hora, medico);
		todasAsConsultas.add(a);
		medico.registrarConsulta(a);
		return true;
	}
	public boolean desmarcarConsulta(Consulta a){
		int aux = todasAsConsultas.indexOf(a);
		Medico b = todasAsConsultas.get(aux).getMedico();
		b.getTodasAsConsultas().remove(a);
		todasAsConsultas.remove(a);
		return true;
	}
	public void cadastrarCliente(String nome, String cpf, String sexo, LocalDate nascimento){
		todosOsClientes.add(new Cliente(nome, cpf, nascimento, sexo));
	}
}
