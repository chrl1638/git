package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/*
 * Problem to solve: 
 * 		1. Build a directed graph edge by edge
 * 		2. Each edge is represented with a pair of strings representing the start and the end vertices respectively.
 * 		3. Find if the graph is cyclic
 */
public class FindingCycleInDG {
	/*
	 * Test Harness
	 */
	public static void main(String[] args) {
		Graph g = new Graph();
		
		//Test case 1: no circle even with forward and bridge edge
		g.AddEdge("San Francisco", "Sunnyvale");
		g.AddEdge("Sunnyvale", "San Jose");
		g.AddEdge("San Francisco", "San Jose"); //Forward Edge
		g.AddEdge("San Francisco", "Fremont");
		g.AddEdge("Fremont", "Sunnyvale"); //Bridge Edge
		boolean foundCircle = g.Cyclic();
		System.out.println("Test Case I (No Circle): \n\t Result: The graph contains circle? " + foundCircle);
		
		//Test case 2: circle with direct parent
		g.AddEdge("Sunnyvale", "San Francisco");
		foundCircle = g.Cyclic();
		System.out.println("Test Case II (Circle with Direct Parent): \n\t Result: The graph contains circle? " + foundCircle);
		
		//Test case 3: circle with ancestor
		g = new Graph();
		g.AddEdge("San Francisco", "Sunnyvale");
		g.AddEdge("Sunnyvale", "San Jose");
		g.AddEdge("San Francisco", "San Jose"); //Forward Edge
		g.AddEdge("San Francisco", "Fremont");
		g.AddEdge("Fremont", "Sunnyvale"); //Bridge Edge
		g.AddEdge("San Jose", "San Francisco"); //Backward Edge
		foundCircle = g.Cyclic();
		System.out.println("Test Case III (Circle with Ancestor): \n\t Result: The graph contains circle? " + foundCircle);
	}
}

/*
 * The Graph Class to hold vertexes, edges and related functions. The graph is initialized by constructor. It can then be built edge by edge with AddEdge().
 */
class Graph {
	public static final int InitialSize = 100; //for simplicity, now just use one constant

	HashMap<String, ArrayList<Edge>> vertices;

	public Graph() {
		vertices = new HashMap<String, ArrayList<Edge>>(InitialSize);
	}
	
	/*
	 * Graph is built edge by edge. Related vertices are added when edges are built
	 */
	public void AddEdge(String s, String e) {
		ArrayList<Edge> edges = vertices.get(s);
		if(edges == null) {
			edges = new ArrayList<Edge>(InitialSize);
			vertices.put(s, edges);
		}
		edges.add(new Edge(s, e));
		//Add end vertex if it is not there yet
		if(vertices.get(e) == null)
			vertices.put(e, new ArrayList<Edge>(InitialSize));
	}
	
	/*
	 * Helping variables for DFS traversal
	 */
	boolean foundCircle = false;	//In this specific problem, we can overload foundCircle also as dfs finishing indication, in general case though, we shouldn't
	HashMap<String, Integer> visited = new HashMap<String, Integer>(InitialSize);
	HashMap<String, Integer> processed = new HashMap<String, Integer>(InitialSize);
	
	/*
	 * Detect whether the graph has circle in it. Return true as soon as it found one.
	 */
	public boolean Cyclic() {
		foundCircle = false;
		for(String v:vertices.keySet()) {
			//Given this is a directed graph, navigation is not restricted w/i the "connected component" like
			//undirected graph does. Therefore we have to initialize for each starting point
			for(String vv:vertices.keySet()) {
				visited.put(vv, 0);
				processed.put(vv, 0);
			}
			dfs(v);
			if (foundCircle) return foundCircle;
		}
		return foundCircle;
	}
	
	/*
	 * DFS traversal of the graph.
	 * Detect circle by looking for backedges (ignore forward and bridge edge)
	 * Exit if a circle already found in the graph.
	 */
	private void dfs(String v) {
		//Make sure once circle is found, all dfs call return immediately
		if(foundCircle)
			return;
		visited.put(v,  1);
		for(Edge e:vertices.get(v)) {
			if(visited.get(e.getEnd()) == 1 && processed.get(e.getEnd()) == 0) {
				foundCircle = true;
				return;
			}
			if(visited.get(e.getEnd()) == 0 )
				dfs(e.getEnd());
		}
		processed.put(v, 1);
	}
}

/*
 * Hold the start and end vertices of an edge.
 * (For this problem, it can actually be replaced with a simple String representing the end vertex.)
 */
class Edge {
	String start;
	String end;
	String getStart() {
		return start;
	}
	
	String getEnd() {
		return end;
	}
	
	public Edge(String s, String e) {
		start = s;
		end = e;
	}
}