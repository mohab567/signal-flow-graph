package Input;

import java.awt.Point;
import java.util.ArrayList;

public class AdjacencyLists {

	 int n;
	 ArrayList<Point>[] adj;
	    AdjacencyLists(int n0) {
	        n = n0;
	        adj = new ArrayList[n];
	        for (int i = 0; i < n; i++) 
	            adj[i] = new ArrayList<Point>();
	    }
	    
	    public void addEdge(int i, int j,int wight) {
	        adj[i].add(new Point (j,wight));
	    }
	    
	    public int getSize(){
	    	return n;
	    }
	    
	    /*public ArrayList getNode(int i){
	    	return adj[i];
	    }*/

		public ArrayList<Point> getNode(int i) {
			return adj[i];
		}

}
