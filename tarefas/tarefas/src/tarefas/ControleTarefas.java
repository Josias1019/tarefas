package tarefas;

import java.util.ArrayList;

public class ControleTarefas {

	private ArrayList<Tarefas> tarefa;
	
	public ControleTarefas()
	{
		tarefa = new ArrayList<>();
	}
	
	public void addTarefa(Tarefas tarefas)
	{
		tarefa.add(tarefas);
	}
	
	public void removeTarefa(String nome)
	{
		boolean removed = tarefa.removeIf(tarefas -> tarefas.getNome().equalsIgnoreCase(nome));
		
		if (removed)
		{
			System.out.println("Tarefa " + nome + " removida com sucesso!");
		} else 
		{
			System.out.println("Tarefa" + nome + " não foi removida!");
		}
	}
	
	public void ListTarefa()
	{
		if (tarefa.isEmpty())
		{
			System.out.println("Não há tarefas registradas.");
		} else
		{
			for (Tarefas tarefas : tarefa)
			{
				System.out.println(tarefas);
			}
		}
	}
	
}
