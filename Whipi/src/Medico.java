import java.time.LocalDate;
import java.util.ArrayList;

public class Medico {
	private String nome;
	private String especializacao;
	private ArrayList<Consulta> consultasDoMedico = new ArrayList<Consulta>();
	private String cpf;
	private String senha;
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
	// getSenha é private aqui e em recepcionista
	private String getSenha() {
		return senha;
	}
	// nova Funcao
	public void realizarConsulta(Consulta a, String descricao){
		if(this.consultasDoMedico.contains(a)){
			int aux = this.consultasDoMedico.indexOf(a);
			this.consultasDoMedico.get(aux).setDescricaoDaConsulta(descricao);
			this.consultasDoMedico.get(aux).setRealizada(true);
			this.consultasDoMedico.remove(a);
		}
	}
	// removi removerConsultasRealizadas
	// alterada
	public ArrayList<Consulta> consultasDeHoje(){
		ArrayList<Consulta> hoje = new ArrayList<Consulta>();
		for (int i = 0; i < this.consultasDoMedico.size(); i++) {
			if(!this.consultasDoMedico.get(i).isRealizada() && this.consultasDoMedico.get(i).getDia().getDayOfYear() == LocalDate.now().getDayOfYear() && this.consultasDoMedico.get(i).getDia().getMonthValue() == LocalDate.now().getMonthValue() && this.consultasDoMedico.get(i).getDia().getYear() == LocalDate.now().getYear()){
				hoje.add(this.consultasDoMedico.get(i));
			}
		}
		return hoje;
	}
	public String toString(){
		return "Nome: " + this.nome + "\nEspecialização: " + this.especializacao + "\nCPF: " + this.cpf + "\n";
	}
}
