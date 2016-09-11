import java.util.ArrayList;

public class Perfil {
	private String usuario;
	private String senha;
	private String nome;
	private String cpf;//opcional
	ArrayList<Conta> listaDeContas = new ArrayList<Conta>();
	
	public Perfil(String nome, String usuario, String senha){
		this.usuario = usuario;
		this.nome = nome;
		this.senha = senha;
	}
	
	public boolean validaSenha(String senha){
		return this.senha == senha;
	}
	public void resetSenha(){
		//a ideia é passar a senha atual para o validaSenha, para confirmar,
		//depois, se retornar true, podemos autorizar a passar uma nova senha;
	}
	public boolean validaUsuario(String usuario){//nao sei se vai ser necessario
		return this.usuario == usuario;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public String getNome(){
		return this.nome;
	}
	public String getUsuario(){
		return this.usuario;
	}
	public void novaConta(String nomeDaConta, int saldo){
		this.listaDeContas.add(new Conta(nomeDaConta, saldo));
	}
	//funcoes de controlar conta
}
