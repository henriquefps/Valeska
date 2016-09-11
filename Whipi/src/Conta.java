
public class Conta {
	private String nomeDaConta;
	private int saldo;
	// transaçoes
	// dono da conta
	
	public Conta(String nome, int saldo){
		this.nomeDaConta = nome;
		this.saldo = saldo;
	}
	public int getSaldo(){
		return this.saldo;
	}
	public void depositar(int valor){
		this.saldo += valor;
	}
	public void setNomeDaConta(String nome){
		this.nomeDaConta = nome;
	}
	public String getNomeDaConta(){
		return this.nomeDaConta;
	}
	public String toString(){
		return "Nome da Conta: " + this.nomeDaConta + "\nSaldo: " + this.saldo + "\n";
	}
}
