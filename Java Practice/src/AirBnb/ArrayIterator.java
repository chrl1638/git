/*
 * Problem Statement: Given an array of arrays, implement an iterator class to allow the client to traverse and remove elements
 * in the array list. This iterator should provide three public class member functions: boolean has_next() return true or false 
 * if there is another element in the set int next() return the value of the next element in the set void remove() remove the last
 * element returned by the iterator. That is, remove the element that the previous next() returned This method can be called only 
 * once per call to next(), otherwise, an exception will be thrown. 
 * See http://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html#remove() for details. The code should be well structured,
 * and robust enough to handle any access pattern. Additionally, write code to demonstrate that the class can be used for the 
 * following basic scenarios: Print elements Given: [[],[1,2,3],[4,5],[],[],[6],[7,8],[],[9],[10],[]] 
 * Print: 1 2 3 4 5 6 7 8 9 10 Remove even elements Given: [[],[1,2,3],[4,5],[],[],[6],[7,8],[],[9],[10],[]] 
 * Should result in: [[],[1,3],[5],[],[],[],[7],[],[9],[],[]] Print: 1 3 5 7 9 
 * 
 * Solution: The solution bellow can be improved in several ways: we can leveraging size() of each column and quickly get to the 
 * right element.
 */
package AirBnb;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayIterator implements Iterable<Integer> {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 11; i++) {
			a.add(new ArrayList<Integer>());
		}
		
		a.get(1).add(1);
		a.get(1).add(2);
		a.get(1).add(3);
		
		a.get(2).add(4);
		a.get(2).add(5);
		
		a.get(5).add(6);
		a.get(6).add(7);
		a.get(6).add(8);
		
		a.get(8).add(9);
		a.get(9).add(10);
		
		ArrayIterator ai = new ArrayIterator(a);
		
		Iterator<Integer> itr = ai.iterator();
		
		while(itr.hasNext()) {
			System.out.print(" " + itr.next());
		}
		
		itr = ai.iterator();
		while(itr.hasNext()) {
			if(itr.next() %2 == 0)
				itr.remove();
		}
		
		itr = ai.iterator();
		System.out.println("\nRemoved Even:");
		while(itr.hasNext()) {
			System.out.print(" " + itr.next());
		}

	}

	ArrayList<ArrayList<Integer>> a;
	
	ArrayIterator(ArrayList<ArrayList<Integer>> aa) {
		a = aa;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<Integer> {
		private int cursorI, cursorJ;
		
		public MyIterator() {
			cursorI = 0;
			cursorJ = 0;
		}
		
		@Override
		public boolean hasNext() {
			for(int i = cursorI; i < a.size(); i++) {
				ArrayList<Integer> b = a.get(i);
				if(b == null || b.size() == 0)
					continue;
				if(i == cursorI && b.size() > cursorJ + 1)
					return true;
				if(i > cursorI && b.size() > 0)
					return true;
			}
			return false;
		}

		@Override
		public Integer next() {
			int i = cursorI;
			int j;
			while( i < a.size()) {
				ArrayList<Integer> b = a.get(i);
				if(b == null || b.size() == 0) {
					i++;
					continue;
				}
				if(i == cursorI)
					j = cursorJ + 1;
				else
					j = 0;
				
				if(j < b.size()) {
					cursorI = i;
					cursorJ = j;
					return b.get(j);
				} else {
					j = 0;
					i++;
				}
			}
			return null;
		}

		@Override
		public void remove() {
			ArrayList<Integer> b = a.get(cursorI);
			b.remove(cursorJ);
		}
		
	}
	
}
