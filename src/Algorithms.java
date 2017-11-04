import java.util.ArrayList;
import java.util.List;

public class Algorithms 
{
	private int num_frames;
	private int num_error;
	ArrayList<Integer> frames;
	
	public Algorithms(int num_frames)
	{
		this.num_frames = num_frames;
		num_error = 0;
	}
	
	public void FIFO(List<Integer> list)
	{
		int firstIn = 0;	//Indice do elemento mais antigo
		num_error = 0;
		
		frames = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++)
		{
			int num_page = list.get(i);
			
			if(!frames.contains(num_page))
			{
				if(frames.size() < num_frames)	//Erro de página. Se ainda possuir espaço livre, armazena nova página
				{
					frames.add(num_page);
					num_error++;
					continue;
				}
				else	//Caso contrário, substitui página mais antiga.
				{
					frames.remove(firstIn);
					frames.add(firstIn, num_page);
					firstIn++;
					num_error++;
					
					if(firstIn == num_frames)
					{
						firstIn = 0;
					}
				}
			}
		}
		
		System.out.println("FIFO " + num_error);
	}
	
	public void OTM(List<Integer> list)
	{
		int base = 0;
		int max_index = 0;
		int frame_index = 0;
		int tam = list.size();
		num_error = 0;
		frames = new ArrayList<>();
		
		for(int i=0; i<tam; i++)
		{
			int num_page = list.get(0);
			list.remove(base);
			
			if(!frames.contains(num_page))	
			{
				if(frames.size() < num_frames)
				{
					frames.add(num_page);
					num_error++;
					continue;
				}
				
				max_index = -1;
				
				for(int j=0; j<frames.size(); j++)
				{
					if(list.indexOf(frames.get(j)) == -1)
					{
						frame_index = j;
						break;
					}
					
					if(list.indexOf(frames.get(j)) > max_index)
					{
						frame_index = j;
						max_index = list.indexOf(frames.get(j));
					}
				}
				
				frames.set(frame_index, num_page);
				num_error++;
			}			
		}
		
		System.out.println("OTM " + num_error);
	}
	
	public void LRU(List<Integer> list)
	{
		ArrayList<Integer> pilha = new ArrayList<>(num_frames);
		boolean aux;
		num_error = 0;
		frames = new ArrayList<>();
		
		for(int i=0; i<list.size(); i++)
		{
			int num_page = list.get(i);
			aux = false;
			
			for(int j=0; j<pilha.size(); j++)		//Busca se valor já está na pilha e atualiza
			{
				if(pilha.get(j) == num_page)
				{
					pilha.remove(j);
					pilha.add(num_page);
					aux = true;
					break;
				}
			}		
			
			if(!aux)
			{
				if(pilha.size() < num_frames)		//Se ainda possui quadros disponíveis, insere nova página							
				{
					pilha.add(num_page);
					num_error++;
				}
				else								//Caso contrário, substitui quadro menos recente usado
				{
					pilha.remove(0);
					pilha.add(num_page);
					num_error++;
				}
			}
			
		}
		
		System.out.println("LRU " + num_error);
	}
}



