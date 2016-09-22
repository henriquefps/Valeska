package repositorio;

import java.util.ArrayList;

import negocio.beans.Recepcionista;

public class RepositorioRecepcionistas {
	private ArrayList<Recepcionista> recepcionista;
	int proximo;
	public RepositorioRecepcionistas()
	{
		this.recepcionista = new ArrayList<Recepcionista>();
		this.proximo = 0;
	}


	public void cadastrar(Recepcionista recepcionista)
	{
		boolean result = this.recepcionista.contains(recepcionista);

		if ( result == true)
		{
			System.out.println("Recepcionista Já cadastrado(a)");
		}else{
			this.recepcionista.add(proximo, recepcionista);
			proximo++;
		}
	}
	public void remover(Recepcionista recepcionista)
	{
		boolean result = this.recepcionista.contains(recepcionista);

		if ( result == true)
		{
			System.out.println("Recepcionista Já cadastrado(a)");
		}else{
			this.recepcionista.remove(recepcionista);
		}
	}

	public void atualizar(Recepcionista recepcionista , String nome,String cpf,String telefone,String rg,char sexo,int idade,double salario)
	{
		recepcionista.setNome(nome);
		recepcionista.setIdade(idade);
		recepcionista.setCpf(cpf);
		recepcionista.setRg(rg);
		recepcionista.setSalario(salario);
		recepcionista.setSexo(sexo);
		recepcionista.setTelefone(telefone);
	}

	public Recepcionista pesquisar(String cpf)
	{
		Recepcionista resultado = null;

		for ( int i = 0 ; i < this.recepcionista.size() ; i++)
		{
			if(this.recepcionista.get(i).getCpf() == cpf)
			{
				resultado = recepcionista.get(i);
			}

		}
		return resultado;

	}
}
