package trees;

import java.util.*;

import graph.*;

public class SpanningTree {
    
    public static Collection<Edge> kruskal(UnionFind u, EuclideanGraph g){
    	// Q2
    	//sort
    	List<Edge> edges = g.getAllEdges();
    	Collections.sort(edges, new EdgeComparator());
    	
    	//building the tree
    	Collection<Edge> MSF = new LinkedList<Edge>();
    	for(Edge e: edges) {
    		if(!u.find(e.source).equals(u.find(e.target))) {
    			u.union(e.source, e.target);
    			MSF.add(e);
    		}
    	}
    	
    	return MSF;
    }
    
    public static Collection<Collection<Edge>> kruskal(EuclideanGraph g){
    	// Q3
    	HashMap<Place,Collection<Edge>> edgelist = new LinkedHashMap<Place,Collection<Edge>>();
    	UnionFind u = new UnionFind();
    	Collection<Edge> edges = kruskal(u, g);
    	for(Edge e: edges) {
    		if(edgelist.get(u.find(e.source)) == null) {
    			edgelist.put(u.find(e.source), new LinkedList<Edge>());
    		}
    		edgelist.get(u.find(e.source)).add(e);
    	}
    	return edgelist.values();
    }
    
    public static Collection<Edge> primTree(HashSet<Place> nonVisited, Place start, EuclideanGraph g){
    	Collection<Edge> MST = new LinkedList<Edge>();
    	// Q4
    	PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new EdgeComparator());
    	for(Edge e: g.edgesOut(start)) {
			if(nonVisited.contains(e.target))
				pq.add(e);
		}
    	nonVisited.remove(start);
    	Place it;
    	
    	while(!pq.isEmpty()){
    		MST.add(pq.peek());
    		it = pq.peek().target;
    		nonVisited.remove(it);
    		for(Edge e: g.edgesOut(it)) {
    			if(nonVisited.contains(e.target))
    				pq.add(e);
    		}
    		while(!pq.isEmpty() && !nonVisited.contains(pq.peek().target))pq.poll();
    	}
    	
    	return MST;
    }
    private static Place peek(HashSet<Place> s) {
    	Iterator<Place> it = s.iterator();
    	if(it.hasNext())
    		return it.next();
    	else return null;
    }
    public static Collection<Collection<Edge>> primForest(EuclideanGraph g){
    	// Q5
    	HashMap<Place,Collection<Edge>> MSF = new LinkedHashMap<Place, Collection<Edge>>();
    	HashSet<Place> nonVisited = new HashSet<Place>(g.places());
    	Place start;
    	Collection<Edge> MST;
    	while(!nonVisited.isEmpty()) {
    		start = peek(nonVisited);
    		MST = primTree(nonVisited, start, g);
    		if(!MST.isEmpty())
    			MSF.put(start, MST);
    	}
    	return MSF.values();
    }
    
   
}
