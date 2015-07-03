import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class Skyline {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Building> buildings = new ArrayList<Building>(100);
		
		buildings.add(new Building(1, 11, 5));
		buildings.add(new Building(2, 6, 7));
		buildings.add(new Building(3, 13, 9));
		buildings.add(new Building(12, 7, 16));
		buildings.add(new Building(14, 3, 25));
		buildings.add(new Building(19, 18, 22));
		buildings.add(new Building(23, 13, 29));
		buildings.add(new Building(24, 4, 28));
		//build the grid first
		SkylineData.buildGrid(buildings);
		//setup hight one build at a time
		for(Building b:buildings) {
			SkylineData.insert(b);
		}
		//merge buildings are adjacent with same hight
		SkylineData.merge();
		SkylineData.print();
	}
}

class Building {
	private int left;
	private int right;
	private int hight;
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
	Building(int lt, int ht, int rt) {
		left = lt;
		right = rt;
		hight = ht;
	}

}

class SkylineData {
	private static TreeMap<Integer, Integer> skyline = new TreeMap<Integer, Integer>();
	
	public static void buildGrid(ArrayList<Building> blds) {
		for(Building b:blds) {
			skyline.put(b.getLeft(), 0);
			skyline.put(b.getRight(), 0);
		}
	}
	
	public static void insert(Building bd) {
		//Here we can still optimize to locate the first region block that has key = .left, and iterates till key > .right
		for(Integer key:skyline.keySet()) {
			if(bd.getLeft() <= key && bd.getRight() > key) {
				if(skyline.get(key) < bd.getHight()) {
					skyline.put(key, bd.getHight());
				}
			}
		}
	}
	
	public static void merge() {
		//make a copy so we can iterating on the copy and delete on the real map
		TreeMap<Integer, Integer> cp = new TreeMap<Integer, Integer>(skyline);
		int prev = -1;
		for(Integer key:cp.keySet()) {
			if(cp.get(key) == prev) 
				skyline.remove(key);
			else
				prev = cp.get(key);
		}
	}
	
	public static void print() {
		for(Integer key:skyline.keySet()) {
			System.out.print(" " + key + " " + skyline.get(key));
		}
		System.out.println();
	}
}