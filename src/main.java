import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class main 
{

	public static void main(String[] args) throws FileNotFoundException
	{
		int num_frames = 0;
		FileReader arq = new FileReader("src/dados.txt");
		Scanner dados = new Scanner(arq);
		ArrayList<Integer> listaInputs = new ArrayList<>();
		ArrayList<Integer> listaInputs_OTM = new ArrayList<>();
		
		if(dados.hasNext())
		{
			num_frames = Integer.valueOf(dados.next()); 
		}
		
		while(dados.hasNext())
		{			
			listaInputs.add(Integer.valueOf(dados.next()));
		}
		
		for(int i=0; i<listaInputs.size(); i++)
		{
			listaInputs_OTM.add(listaInputs.get(i));
		}
		
		Algorithms algoritmo = new Algorithms(num_frames);
	
		algoritmo.FIFO(listaInputs);
		algoritmo.OTM(listaInputs_OTM);
		algoritmo.LRU(listaInputs);
	}	

}
