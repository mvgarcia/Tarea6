package Punto1;

import java.util.ArrayList;

public class FloydWarschall implements CaminoCostoMinimoAlgorithm
{
	public FloydWarschall() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int[][] calcularCaminoCostoMinimo(int[][] grafo) {
		int tamanio=grafo.length;
		int m [][] = new int[tamanio][tamanio]; 
		
		int i=0;
		int j=0;
		int k=0;
		
		while(k<=tamanio)
		{
			if(k==0) {m[i][j]= grafo[i][j];}
			else if(m[i][k-1]==-1 || m[k-1][j]==-1) {m[i][j]=m[i][j];}
			else if(k>0) {
				if(m[i][j]==-1){m[i][j]=m[i][k-1]+m[k-1][j];}
				else{m[i][j] = Integer.min(m[i][j], m[i][k-1]+m[k-1][j]);}
			}
			
			if(j<tamanio-1) {j++;}
			else if(j==tamanio-1 && i<tamanio-1) {i++; j=0;}
			else if(j==tamanio-1 && i==tamanio-1) {i=0;j=0;k++;}
		}
		
		return m;
	}

}
