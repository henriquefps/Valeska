package repositorio;

import java.util.ArrayList;

import negocio.beans.Endereco;
import negocio.beans.Medico;

public class RepositorioMedicos {
	ArrayList<Medico> repositorio = new ArrayList<>();
	
	public void cadastrar(Medico m)
	{
		repositorio.add(m);
	}

	public void remover(int id)
	{
		Medico m = pesquisar(id);
		repositorio.remove(m);
	}
	
	public Medico pesquisar(int id)
	{
		Medico res = null;
		for(int i = 0; i < repositorio.size(); i++)
		{
			if(repositorio.get(i).getId() == id);
				res = repositorio.get(i);
		}
		
		return res;
		
	}
	
	public void atualizar(int id, String nome, int numCRM, String cPF, String rg, 
			Endereco end, String telefone, String celular)
	{
		Medico m = pesquisar(id);
		m.setNome(nome);
		m.setNumCRM(numCRM);
		m.setCPF(cPF);
		m.setRg(rg);
		m.setEnd(end);
		m.setTelefone(telefone);;
		m.setCelular(celular);
	}
}
