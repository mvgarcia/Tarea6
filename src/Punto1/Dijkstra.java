package Punto1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra implements CaminoCostoMinimoAlgorithm
{
	private int n;
	private int[] distancia;
	private int[][] grafo;
	
	public Dijkstra()
	{
		n = 0;
		distancia = null;
	}
	
	@Override
	public int[][] calcularCaminoCostoMinimo(int[][] grafo) throws Exception 
	{
		this.grafo = grafo;
		n = grafo.length;
		int i = 0;
		int[][] solucion = new int[grafo.length][grafo.length];
		
		while(i < grafo.length)
		{
			solucion[i] = dijkstra(i);
			
			i ++;
		}
		
		return solucion;
	}
	
	public static class Nodo
	{
		int id;
		int valor;
		
		public Nodo(int id, int valor)
		{
			this.id = id;
			this.valor = valor;
		}
	}
	
	private Comparator<Nodo> comparador = new Comparator<Nodo>() 
	{
        @Override
        public int compare(Nodo nodo1, Nodo nodo2) 
        {
          return (nodo1.valor - nodo2.valor) > 0 ? +1 : -1;
        }
    };
    
    private int[] dijkstra(int origen)
    {
    	distancia = new int[n];
    	int i = 0;
    	while(i<distancia.length)
    	{
    		distancia[i] = -1;
    		i ++;
    	}
    	distancia[origen] = 0;
    	
    	PriorityQueue<Nodo> pq = new PriorityQueue<>(2 * n, comparador);
    	pq.offer(new Nodo(origen, 0));
    	
    	boolean[] visitado = new boolean[n];
        
        while(!pq.isEmpty())
        {
        	Nodo nodo = pq.poll();
        	visitado[nodo.id] = true;
        	
        	if (distancia[nodo.id] < nodo.valor) continue;
        	
        	for(i = 0; i < grafo[nodo.id].length; i++)
        	{
        		if(visitado[i]) continue;
        		
        		int nuevaDistancia = Integer.MAX_VALUE;
            	if(distancia[nodo.id] != -1 && grafo[nodo.id][i] != -1)
            	{
            		nuevaDistancia = distancia[nodo.id] + grafo[nodo.id][i];
            	}
            	
            	if(nuevaDistancia < distancia[i] || (distancia[i] == -1 && nuevaDistancia != Integer.MAX_VALUE))
            	{
            		distancia[i] = nuevaDistancia;
            		pq.offer(new Nodo(i, distancia[i]));
            	}
        	}
        }
        
        return distancia;
    }
}
