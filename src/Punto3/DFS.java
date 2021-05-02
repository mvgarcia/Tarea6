package Punto3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import Punto1.BellmanFord;
import Punto1.Dijkstra;
import Punto1.FloydWarschall;

public class DFS 
{
	private int[][] grafo;
	int[] ordenTopologico;
	
	
	public DFS()
	{
		grafo = null;
	}
	
	public boolean dfs(int[][] grafo)
	{
		this.grafo = grafo;
		
		boolean[] visitado = new boolean[grafo.length];
		boolean[] stack = new boolean[grafo.length];
		
		int i = 0;
		while(i< grafo.length)
		{
			if(hayCiclo(i, visitado, stack))
			{
				return true;
			}
			
			i ++;
		}
		
		return false;
	}
	
	private boolean hayCiclo(int i, boolean[] visitado, boolean[] stack) 
	{
		if(stack[i]) {return true;}
		else if (visitado[i]) {return true;}
		
		stack[i] = true;
		visitado[i] = true;
		
		int j = 0;
		while(j < grafo[i].length)
		{
			if(hayCiclo(j, visitado, stack) && grafo[i][j]>0)
			{
				return true;
			}
			
			stack[i]= false;
		}
		
		return false;
	}

	public static void main(String[] args1) 
	{
		FileReader fr;
		FileWriter fw;
		try 
		{
			//Presenta opciones de archivos
			System.out.println("Escoja un archivo con la matriz de costos del grafo:\n");
			System.out.println("\t1. distances5.txt");
			System.out.println("\t2. distances100.txt");
			System.out.println("\t3. distances1000.txt");
			System.out.println("\t4. otro");
			
			//Procesa el archivo indicado
			Scanner sc = new Scanner(System.in);
			String archivo = sc.nextLine();
			int tamanio=0;
			if(Integer.parseInt(archivo)==1) {archivo = "./src/distances5.txt";}
			else if(Integer.parseInt(archivo)==2) {archivo = "./src/distances100.txt"; }
			else if(Integer.parseInt(archivo)==3) {archivo = "./src/distances1000.txt";}
			else if(Integer.parseInt(archivo)==4)
			{
				System.out.println("Indique el nombre del archivo:");
				archivo = "./src/" + sc.nextLine();
			}
			else
			{
				sc.close();
				throw new Exception("La opcion de archivo \"" + archivo + "\" no es valida");
			}
			
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
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
