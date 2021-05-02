package Punto1;

import java.util.ArrayList;

public class BellmanFord implements CaminoCostoMinimoAlgorithm
{

	public BellmanFord() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] calcularCaminoCostoMinimo(int[][] grafo) {
		int tamanio=grafo.length;
		int m [][] = new int[tamanio][tamanio];
		//ArrayList<ArrayList<Integer>> hijos  = new ArrayList<>();
		//int[]predescesor = new int[tamanio];

		for (int i=0;i<tamanio;i++)
		{
			//predescesor[i]=i;
			//ArrayList <Integer> hijos_i= new ArrayList<Integer>();
			m[i][i]=0;
			for(int j=0;j<tamanio;j++)
			{
				if(i!=j)m[i][j]=1000000;
				//if(grafo[i][j]!=-1 && i!=j)hijos_i.add(j);
			}
			//hijos.add(hijos_i);
		}

		int it=0;
		while(it<tamanio) {
			System.out.println(it);
			for(int i=0;i<tamanio;i++)
			{		
				for(int j=0;j<tamanio;j++)
				{
					//int anterior=m[i][j];
					int min=1000000;
					//System.out.println("nuevo e");
					for(int e=0;e<tamanio;e++)
					{
						//System.out.println(e);
						//if(i==j)predescesor[j]=j;					
						if(i!=j && grafo[e][j]!=-1 && m[i][e]+grafo[e][j]<min)
						{
							min=m[i][e]+grafo[e][j];
							//predescesor[j]=e;
							m[i][j]=min;
							//System.out.println(m[i][e]+" "+grafo[e][j] );
							//System.out.println("min "+min);
						}
						//System.out.println(i+" "+" "+j+" "+predescesor[j]);

					}
					//if(grafo[i][j]!=-1){predescesor[j]=i;}

					//System.out.println(anterior+" "+(m[i][predescesor[j]]+grafo[predescesor[j]][j]));
					//m[i][j]=Integer.min(anterior, m[i][predescesor[j]]+grafo[predescesor[j]][j]);
					//if(anterior!=m[i][j]) {predescesor[j]=i;}
					//System.out.println(m[i][j]);
				}
			}
			it++;
		}


		return m;
	}

}
