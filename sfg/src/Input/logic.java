package Input;

import java.util.ArrayList;

public class logic {

	

private AdjacencyLists m;
public ArrayList[] paths = new ArrayList[1000];
public ArrayList[] loops = new ArrayList[1000];
public ArrayList pathsVal = new ArrayList();
public ArrayList deltaVal = new ArrayList();
public ArrayList loopsVal = new ArrayList();
public ArrayList<ArrayList> [] c ; //non touch combinations
int p=0; //paths length
int l=0; //loops length
int wholeDelta=1;

	logic (AdjacencyLists m){
		this.m = m;
	}
   boolean isVisited(ArrayList s , int x){
	   return s.contains(x);
   }	
	float findPathsAndLoops(int s, int t){
		findPath(new ArrayList(),s,t,1);
		
		
		calcLoopsGain();
		c = new ArrayList [l];
		calcloopsInt();
		
		getdelta();
		getwholedelta();
		
		return (float) nominator()/(float)wholeDelta;
		
	}

	private int nominator() {
		int x=0;
		
				for(int i=0;i<p;i++){
					
					
					x+=(int)pathsVal.get(i)*(int)deltaVal.get(i);
					
				}
				
		return x;
	}
	
	private void getwholedelta() {
		int x=1;
		
			
			for(int j=0;j<c.length;j++){
				for(int k=0;k<c[j].size();k++){
				x+=Math.pow(-1,j+1)*getLoopsVal(c[j].get(k));	
				}
			
			wholeDelta=x;
			}	
	}
	
	private void getdelta() {
		int x;
		for(int i=0;i<p;i++){
			x=1;
			for(int j=0;j<c.length;j++){
				for(int k=0;k<c[j].size();k++){
				if(!checkIntp(c[j].get(k), paths[i]))
				x+=Math.pow(-1,j+1)*getLoopsVal(c[j].get(k));	
				}
			}
			deltaVal.add(x);
		}
		
	}
	
	private void calcloopsInt() {
		for(int i=0;i<l;i++){
			c[i]=new ArrayList();
				loopsConcrete(new ArrayList(),c[i],-1,i);
		}
		
	}
	
	
	
	private void loopsConcrete(ArrayList path, ArrayList<ArrayList> nth, int current, int no) {
		 if(no==-1){
			 
			ArrayList c = new ArrayList(path);
			nth.add(c);
		}else if(current+1>l-1){
			
		}else{
			
			for(int i=current+1;i<l;i++){
				
				if(!checkIntSet(path,i)){
				ArrayList c = new ArrayList(path);
			    c.add(i);
			    loopsConcrete(c,nth,i,no-1);
				}
			}
		}
		
	}
	
	boolean checkIntSet(ArrayList a,int b){
		for(int i=0 ; i<a.size();i++){
			if(checkint((int)a.get(i),b)){
				return true;
			}
		}
		return false;
	}
	private boolean checkint(int a, int b) {
		ArrayList x = loops[a];
		ArrayList y = loops[b];
		
		for(int i=0;i<y.size();i++ ){
			if(x.contains(y.get(i))){
				return true;
			}
		}
		return false;
	}
	
	boolean checkIntp(ArrayList a,ArrayList b){
		for(int i=0 ; i<a.size();i++){
			if(checkintp((int)a.get(i),b)){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkintp(int a, ArrayList y) {
		ArrayList x = loops[a];
		
		for(int i=0;i<y.size();i++ ){
			if(x.contains(y.get(i))){
				return true;
			}
		}
		return false;
	}
	
	int getLoopsVal(ArrayList s){
		int x=1;
		for(int i =0;i<s.size();i++){
			x *=(int) loopsVal.get((int) s.get(i));
		}
		return x;
	}
	
	
	
	
	private void calcLoopsGain() {
		for(int i=0;i<l;i++){
			loopGain(i);
		}
		
	}
	
	private void loopGain(int i) {
		int x=1;
		int r;
		int c=0;
		int d=0;
		while(c<loops[i].size()-1){
			r=(int) loops[i].get(c);
			c++;
			d=0;
			while(m.adj[r].get(d).x!=(int)loops[i].get(c)){
				d++;
			}
			x*=m.adj[r].get(d).y;
		}
		loopsVal.add(x);
	}
	
	void findPath(ArrayList d ,int s , int t,int v ){
		if(s==t)
		{
			pathsVal.add(v);
			ArrayList c = new ArrayList(d);
			
			c.add(t);
			paths[p]=c;
		p++;
		for(int i=0;i< m.adj[s].size();i++){
			findPath(c,m.adj[s].get(i).x,-1,v*m.adj[s].get(i).y);
		}
		}
		else if(!isVisited(d, s)){
			ArrayList c = new ArrayList(d);
			c.add(s);
			for(int i=0;i< m.adj[s].size();i++){
				findPath(c,m.adj[s].get(i).x,t,v*m.adj[s].get(i).y);
			}
		
		}
        else{
        	ArrayList c = new ArrayList(d);
        	c.add(s);
        	for(int i=0;;){
        		if((int)c.get(i)!=s){
        			c.remove(i);}
        		else{
        			break;
        		}
        	}
        	if(!checkLoop(c)){
        		loops[l]=c;
        	    l++;
        	    }
		}
	}
	
	private boolean checkLoop(ArrayList c) {
		for(int i=0;i<l;i++)
        {
            if(checklists(c,loops[i]))
                return true;
        }
		return false;
	}
	
	boolean checklists(ArrayList list1,ArrayList list2){
		if(list1==null && list2==null)
            return true;
        if((list1 == null && list2 != null) || (list1 != null && list2 == null))
            return false;
        if(list1.size()!=list2.size())
            return false;
        for(int i=0;i<list1.size();i++)
        {
            if(!list1.contains(list2.get(i)))
                return false;
        }
        return true;
	}
	
}
