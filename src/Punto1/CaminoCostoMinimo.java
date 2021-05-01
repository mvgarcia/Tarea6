package Punto1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaminoCostoMinimo {

	public static void main(String[] args1) throws Exception {

		String[] args= new String[2];
		//args[0]="Dijkstra";
		//args[0]="BellmanFord";
		//args[0]="FloydWarschall";

		//args[1]="5";
		//args[1]="100";
		//args[1]="1000";

		//Load algorithm class
		String algorithmClassName = CaminoCostoMinimo.class.getPackage().getName()+"."+args[0];
		CaminoCostoMinimoAlgorithm calculator = (CaminoCostoMinimoAlgorithm)Class.forName(algorithmClassName).newInstance();
		//Load input data
		int tamanio = Integer.parseInt(args[1]);
		String ruta="./distances"+tamanio+".txt";
		
		int[] []matriz = new int[tamanio][tamanio];

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			archivo = new File (ruta);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			String linea;
			String [] fila= new String[tamanio];
			int line=0;
			while((linea=br.readLine())!=null && line<tamanio)
				fila=linea.split(" ");
				int [] row = new int[tamanio];
				for(int i=0;i<tamanio;i++)
				{
					row[i]=Integer.parseInt(fila[i]);
				}
				matriz[line]=row;
				line++;				
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{                    
				if( null != fr ){   
					fr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}

		System.out.println("Algoritmo:"+ args[0]);
		System.out.println("Tamanio:"+tamanio);

		//Run the coin change algorithm
		long startTime = System.currentTimeMillis();
		int [][] caminoCostoMinimo = calculator.calcularCaminoCostoMinimo(matriz);
		long endTime = System.currentTimeMillis();

		//Output results
//		System.out.println("Coin\tNumber");
//		for(int i=0;i<numCoins.length;i++) {
//			System.out.println(""+denominations[i]+"\t"+numCoins[i]);
//			calculatedTotal += denominations[i]*numCoins[i];
//			totalCoins+=numCoins[i];
//		}
		System.out.println("Total time spent (milliseconds): "+(endTime-startTime));
	}

}
