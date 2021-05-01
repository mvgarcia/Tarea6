package Punto1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CaminoCostoMinimo {

	public static void main(String[] args1) 
	{
		FileReader fr;
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
			if(Integer.parseInt(archivo)==1) {archivo = "./src/distances5.txt";}
			else if(Integer.parseInt(archivo)==2) {archivo = "./src/distances100.txt";}
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
			
			//Convierte el archivo en una matriz ArrayList<int[]>
			//	donde el indice del int[] indica la columna y el indice del ArrayList indica la fila
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
				int tamanio=filaString.length;
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
			
			//Presenta opciones de algoritmos
			System.out.println("Escoja un algoritmo para encontrar los costos minimos:\n");
			System.out.println("\t1. Dijkstra");
			System.out.println("\t2. Bellman Ford");
			System.out.println("\t3. Floyd Warschall");
			
			//Usa es algoritmo indicado
			long inicio=0;
			long fin=0;
			String algoritmo = sc.nextLine();
			sc.close();
			int [][] matrizCaminosCostosMinimos;
			if(Integer.parseInt(algoritmo)==1) 
			{
				Dijkstra dijkstra = new Dijkstra();
				inicio = System.currentTimeMillis();
				matrizCaminosCostosMinimos = dijkstra.calcularCaminoCostoMinimo(grafo);
				fin = System.currentTimeMillis();
			}
			else if(Integer.parseInt(algoritmo)==2) 
			{
				BellmanFord bellmanFord = new BellmanFord();
				inicio = System.currentTimeMillis();
				matrizCaminosCostosMinimos = bellmanFord.calcularCaminoCostoMinimo(grafo);
				fin = System.currentTimeMillis();
			}
			else if(Integer.parseInt(algoritmo)==3) 
			{
				FloydWarschall floydWarschall = new FloydWarschall();
				inicio = System.currentTimeMillis();
				matrizCaminosCostosMinimos = floydWarschall.calcularCaminoCostoMinimo(grafo);
				fin = System.currentTimeMillis();
			}
			else {throw new Exception("La opcion del algoritmo \"" + algoritmo + "\" no es valida");}
			long tiempo = fin-inicio;
			System.out.println("\nSe obtuvo el resultado en: " + tiempo + " milisegundos");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
