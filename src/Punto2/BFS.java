package Punto2;

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BFS 
{
	private int n;
	private static int[][] grafo;
	
	public BFS()
	{
		n = 0;
		grafo = null;
	}
	
	private String bfs(int[][] grafo) throws Exception
	{
		String respuesta ="{";
		
		this.grafo = grafo;
		Deque<Integer> fila = new ArrayDeque<>(grafo.length);
		boolean[] visitado = new boolean[grafo.length];
		
		int vertice = 0;
		while(vertice < grafo.length)
		{
			if(!visitado[vertice])
			{
				if(vertice == 0) {respuesta += "{";}
				else {respuesta += ",{";}
				fila.offer(vertice);
				visitado[vertice] = true;
				respuesta += vertice;
				
				while(!fila.isEmpty())
				{
					int nodo = fila.poll();
					
					int i = 0;
					while(i < grafo[nodo].length)
					{
						if(grafo[nodo][i]== 1)
						{
							if(!visitado[i])
							{
								visitado[i] = true;
								respuesta += "," + i;
								fila.offer(i);
							}
						}
						else if (grafo[nodo][i]== -1);
						else {throw new Exception("Hay elementos no validos den la matriz, esta solo deberia contener 1s y -1s");}
						
						i ++;
					}
				}
				respuesta +="}";
			}
			
			vertice ++;
		}
		
		return respuesta + "}";
	}
	
	
	public static void main(String[] args) 
	{
		FileReader fr;
		try
		{
			//Presenta opciones de archivos
			System.out.println("Escriba el nombre del archivo a evaluar que contiene el grafo representado por una matriz:\n");
			
			//Procesa el archivo indicado
			Scanner sc = new Scanner(System.in);
			String archivo = "./src/" +sc.nextLine();
			sc.close();
			int tamanio=0;
			
			//Procesa la matriz dada
			System.out.print("\nProcesando matriz\t.");
			fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			int[][] grafo= new int[0][0];
			System.out.print("\t.");
			int numLinea=0;
			while((linea = br.readLine()) != null)
			{
				String[] filaString = linea.split("\\s");
				tamanio=filaString.length;
				if(numLinea==0) grafo=new int[tamanio][tamanio];
				
				int[] filaInt = new int[tamanio];
				int i = 0;
				while(i < tamanio)
				{
					filaInt[i] = Integer.parseInt(filaString[i]);
					i ++;
				}
				grafo[numLinea]=filaInt;
				numLinea++;
			}
			br.close();
			System.out.print("\t.\t Matriz procesada existosamente!\n");
			
			System.out.println("Resolviendo usando algoritmo BFS\n");
			BFS o = new BFS();
			long start = System.currentTimeMillis();
			System.out.println("Resultado: " + o.bfs(grafo) + "\n");
			long time =  System.currentTimeMillis()- start;
			System.out.println("El algoritmo se demoró " + time + " milisegundos en resolver el problema");
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
