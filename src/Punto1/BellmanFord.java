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

		for (int i=0;i<tamanio;i++)
		{
			m[i][i]=0;
			for(int j=0;j<tamanio;j++)
			{
				if(i!=j)m[i][j]=1000000;
			}
		}
		int it=0;
		while(it<tamanio) {
			for(int i=0;i<tamanio;i++)
			{		
				for(int j=0;j<tamanio;j++)
				{
					int min=1000000;
					for(int e=0;e<tamanio;e++)
					{				
						if(i!=j && grafo[e][j]!=-1 && m[i][e]+grafo[e][j]<min)
						{
							min=m[i][e]+grafo[e][j];
							m[i][j]=min;
						}
					}
				}
			}
			it++;
		}


		return m;
	}

}
