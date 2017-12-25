package trees;

import graph.Place;

import java.util.HashMap;

// Q1

public class UnionFind {
	//parent relation, parent.put(src,dst) indicates that src points to dst
    private HashMap<Place,Place> parent;
    
    public UnionFind( ){
        this.parent = new HashMap<Place, Place>();
    }
    
    public Place find( Place src ){
    	Place repr = src;
    	if(parent.get(src) == null)
    		return src;
    	//finds the representative
    	while(parent.get(repr) != null)
        	repr = parent.get(repr);
    	
    	//Path compression	
    	Place it = src, father = parent.get(it);
    	while(!father.equals(repr)) {
    		parent.put(it, repr);
    		it = father;
    		father = parent.get(it);
    	}
        	
    	return repr;
    }
    
    public void union( Place v0, Place v1 ){
        Place repr1 = find(v0), repr2 = find(v1);
    	if(!repr1.equals(repr2))
    		parent.put(repr1, repr2);
    }
}
