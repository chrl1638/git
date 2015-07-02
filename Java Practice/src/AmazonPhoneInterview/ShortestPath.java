package AmazonPhoneInterview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath {

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();	
		Collections.addAll(al, 3, 2, 0, 1, 3, 1, 2, 0, 0);
		
		FindShortestPath(al);
			
	}

	private static boolean FindShortestPath(ArrayList<Integer> al) {
		Queue<Node> q = new LinkedList<Node>();
		HashSet<Integer> visitedNodes = new HashSet<Integer>();
		q.add(new Node(0, ""));
		while(true) {
			Node n = q.poll();
			visitedNodes.add(n.currentPosition);
			for(int j = n.currentPosition + 1; j <= n.currentPosition + al.get(n.currentPosition); j++) {
				if (j == al.size() -1) {
					System.out.println("Shortest path found: " + n.currentPath + " " + j);
					return true;
				}
				if(!visitedNodes.contains(j))
					q.add(new Node(j, n.currentPath + " " + j));
			}
		}
	}
}

class Node {
	int currentPosition;
	String currentPath;
	Node(int pos, String path) {
		currentPosition = pos;
		currentPath = path;
	}
}
