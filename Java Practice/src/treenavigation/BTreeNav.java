package treenavigation;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class BTreeNav {
	static Queue<Node> q = new LinkedList<Node>();
	static Stack<Node> s = new Stack<Node>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node n = new Node("0a");
		n.left = new Node("1a");
		n.right = new Node("1b");
		n.left.left = new Node("2a");
		n.left.right = new Node("2b");
		n.right.left = new Node("2c");
		n.left.left.left = new Node("3a");
		
		System.out.print("DFR:");
		NavDFRecursive(n);
		System.out.print("\nDFI:");
		NavDFIterative(n);
		System.out.print("\nBF:");
		NavBF(n);
	}
	public static void NavDFRecursive(Node n) {
		if(n.left == null && n.right == null) {
			System.out.print(n.value);
			return;
		}
		if(n.left!=null) {
				NavDFRecursive(n.left);
		}
		if(n.right!=null) {
				NavDFRecursive(n.right);
		};
		return;
	}
	
	public static void NavDFIterative(Node n){
		s.add(n);
		while(!s.isEmpty()){
			Node m = s.pop();
			System.out.print(m.value);
			if(m.right!=null)s.push(m.right);
			if(m.left!=null)s.push(m.left);
		}
	}
	
	public static void NavBF(Node n) {
		q.add(n);
		while(!q.isEmpty()) {
			Node m = q.remove();
			System.out.print(m.value);
			if(m.left != null) q.add(m.left);
			if(m.right != null) q.add(m.right);
		}
		return;
	}

}

