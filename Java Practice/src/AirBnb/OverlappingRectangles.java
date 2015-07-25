/*
 * Problem Scope:You have a plain with lots of rectangles on it, find out how many of them 
 * intersect  
 */

package AirBnb;

import java.util.HashSet;
import java.util.Set;

public class OverlappingRectangles {

	public static void main(String[] args) {
		Set<Rectangular> set = new HashSet<Rectangular>();
		
		set.add(new Rectangular(0, 0, 4, 4));
		set.add(new Rectangular(1, 1, 3, 4));
		set.add(new Rectangular(7, 0, 9, 4));
		set.add(new Rectangular(5, 5, 10, 10));
		set.add(new Rectangular(8, 8, 12, 10));
		set.add(new Rectangular(3, 2, 5, 3));
		set.add(new Rectangular(11, 11, 12, 14));
		set.add(new Rectangular(100, 100, 102, 106));
		
		Set<Rectangular> results = new HashSet<Rectangular>();
		
		for(Rectangular r:set) {
			for(Rectangular t:set) {
				if(!r.equals(t) && r.overlap(t)) {
					results.add(r);
					results.add(t);
				}
			}
		}
		
		System.out.println("Number of rectangulars overlapping is: " + results.size());
		for(Rectangular r:results) {
			System.out.println("" + r.getX1() + " " + r.getY1() + " " + r.getX2() + " " + r.getY2());
		}

	}

}

class Rectangular {
	private int x1;
	private int y1;
	private int x2;
	private int y2;


	public Rectangular(int x1, int y1, int x2, int y2) {
		super();
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public boolean overlap(Rectangular r) {
		if(r.x2 < x1) return false;
		if(r.x1 > x2) return false;
		if(r.y2 < y1) return false;
		if(r.y1 > y2) return false;
		return true;
	}
	
}