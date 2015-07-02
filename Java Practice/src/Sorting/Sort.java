package Sorting;

import java.util.ArrayList;
import java.util.Collections;

public class Sort {

	public static void main(String[] args) {
		ArrayList<Integer> alist = new ArrayList<Integer>(10);
		
		//Bubble Sort O(n^2), all in place no memory cost
		alist.clear();
		Collections.addAll(alist, 4, 3, 6, 2, 8, 9, 1, 7, 0, 5, 7);
		System.out.println(alist);
		BubbleSort(alist);
		System.out.println("Bubble Sort Result: " + alist);
		
		//Selection Sort O(n^2), all in place no memory cost
		alist.clear();
		Collections.addAll(alist, 4, 3, 6, 2, 8, 9, 1, 7, 0, 5, 7);
		System.out.println(alist);
		SelectionSort(alist);
		System.out.println("Selection Sort Result: " + alist);
		
		//Insertion Sort O(n^2), all in place no memory cost
		alist.clear();
		Collections.addAll(alist, 4, 3, 6, 2, 8, 9, 1, 7, 0, 5, 7);
		System.out.println(alist);
		InsertionSort(alist);
		System.out.println("Insertion Sort Result: " + alist);
		
		//Quick Sort O(nlogn) on average, but o(n^2) when it is already sorted.
		//For each layer of partition there are n comparisons, number of partition
		//  can be logN or N
		//To avoid the worst scenario, don't pick the last one item for each iteration, instead pick the median number, or randomly
		alist.clear();
		Collections.addAll(alist, 4, 3, 6, 2, 8, 9, 1, 7, 0, 5, 7);
		System.out.println(alist);
		QuickSort(alist);
		System.out.println("Quick Sort Result: " + alist);
		
		//Merge Sort O(nlog(n)) in worst case, not in-place, so the space complexity is O(n)
		alist.clear();
		Collections.addAll(alist, 4, 3, 6, 2, 8, 9, 1, 7, 0, 5, 7);
		System.out.println(alist);
		MergeSort(alist);
		System.out.println("Merge Sort Result: " + alist);
	}
	
	private static void BubbleSort(ArrayList<Integer> al) {
		int i = al.size() - 1;
		while(i >= 0){
			for(int j = i - 1; j >= 0; j--) {
				if(al.get(j) > al.get(i)){
					int t = al.get(j);
					al.set(j, al.get(i));
					al.set(i, t);
				}
			}
			i--;
		}
	}
	
	private static void SelectionSort(ArrayList<Integer> al) {
		int separation = -1;
		while(separation < al.size() - 1) {
			int minValue = al.get(separation + 1);
			int minIndex = separation + 1; 
			for(int i = separation + 1; i < al.size(); i++) {
				if(al.get(i) < minValue) {
					minValue = al.get(i);
					minIndex = i;
				}
			}
			int temp = al.get(minIndex);
			al.set(minIndex, al.get(separation + 1));
			al.set(separation + 1, temp);
			separation ++;
		}
	}
	
	private static void InsertionSort(ArrayList<Integer> al) {
		int separation = -1;
		while(separation < al.size() - 1) {
			int temp = al.get(separation + 1);
			int j;
			for(j = separation + 1; j > 0; j--) {
				if(al.get(j - 1) > temp) {
					al.set(j,al.get(j - 1));
				}
				else {
					break;
				}
			}
			al.set(j, temp);
			separation ++;
		}
	}
	
	private static void QuickSort(ArrayList<Integer> al) {
		QuickSortAlg(al, 0, al.size() - 1);
	}
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	private static void QuickSortAlg(ArrayList<Integer> al, int left, int right) {
		
		
		//exit condition
		if(left >= right)
			return;
		
		//record the boundary of the partition
		int leftBoundary = left;
		int rightBoundary = right;
		//partition
		int currentPivotLocation = LEFT;
		while(left < right) {
			while(al.get(left) <= al.get(right) && left < right) {
				if(currentPivotLocation == LEFT)
					right--;
				else
					left++;
			}
			if(left != right) {
				int temp = al.get(left);
				al.set(left,  al.get(right));
				al.set(right, temp);
				if(currentPivotLocation == LEFT)
					currentPivotLocation = RIGHT;
				else
					currentPivotLocation = LEFT;
			}
		}
		QuickSortAlg(al, leftBoundary, left - 1);
		QuickSortAlg(al, right + 1, rightBoundary); //left and right should be equal any way
	}
	
	private static void MergeSort(ArrayList<Integer> al) {
		MergeSortAlg(al, 0, al.size() - 1);
	}
	
	private static ArrayList<Integer> buf = new ArrayList<Integer>(100);
	private static void MergeSortAlg(ArrayList<Integer> al, int left, int right) {
		if(left>=right)
			return;
		int mid = (left+right) / 2;
		MergeSortAlg(al, left, mid);
		MergeSortAlg(al, mid + 1, right);
		
		//merging
		buf.clear();
		int i = left;
		int j = mid + 1;
		
		while(i <= mid && j <= right) {
			if(al.get(i) < al.get(j)) {
				buf.add(al.get(i));
				i++;
			} else {
				buf.add(al.get(j));
				j++;
			}
		}
		//merge the left over
		while( i <= mid )
			buf.add(al.get(i++));
		while( j <= right)
			buf.add(al.get(j++));
		
		//copy back from buf
		for(i = 0; i < buf.size(); i++) {
			al.set(i + left, buf.get(i));
		}
	}
}
