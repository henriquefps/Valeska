import java.time.LocalDate;
import java.util.ArrayList;

public class Medico {
	private String nome;
	private String especializacao;
	private ArrayList<Consulta> consultasDoMedico = new ArrayList<Consulta>();
	private String cpf;
	private String senha;
	private int numeroDaSala;
	public Medico(String nome, String cpf, String especializacao, String senha){
		this.nome = nome;
		this.especializacao = especializacao;
		this.cpf = cpf;
		this.senha = senha;
	}
	public void registrarConsulta(Consulta a){
		this.consultasDoMedico.add(a);
	}
	public ArrayList<Consulta> getTodasAsConsultas() {
		return consultasDoMedico;
	}
	public String getNome() {
		return nome;
	}
	public String getEspecializacao() {
		return especializacao;
	}
	public String getCpf() {
		return cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void removerConsultasRealizadas(){
		int i = 0;
		while(i < this.consultasDoMedico.size()){
			if(this.consultasDoMedico.get(i).isRealizada()){
				this.consultasDoMedico.remove(i);
			}
		}
	}
	public ArrayList<Consulta> consultasDeHoje(){
		ArrayList<Consulta> hoje = new ArrayList<Consulta>();
		for (int i = 0; i < this.consultasDoMedico.size(); i++) {
			if(this.consultasDoMedico.get(i).getDia() == LocalDate.now());
			hoje.add(this.consultasDoMedico.get(i));
		}
		return hoje;
	}
	
}
