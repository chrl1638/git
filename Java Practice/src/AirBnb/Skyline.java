/*
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a
 *  distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo 
 *  (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 *  Buildings  Skyline Contour
 *  The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri 
 *  are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is 
 *  guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect 
 *  rectangles grounded on an absolutely flat surface at height 0. For instance, the dimensions of all buildings in
 *   Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] . The output is a list of 
 *   "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines 
 *   a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the
 *  rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, 
 *  the ground in between any two adjacent buildings should be considered part of the skyline contour. For instance, 
 *  the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *  
 *  Notes:
 *  The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 *  The input list is already sorted in ascending order by the left x position Li.
 *  The output list must be sorted by the x position.
 *  There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], 
 *  [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the 
 *  final output as such: [...[2 3], [4 5], [12 7], ...]
 */

package AirBnb;

import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

public class Skyline {

	public static void main(String[] args) {
		int[][] buildings = { {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8} } ;
		
		try {
			for(int[] seg:skyline(buildings)) {
				System.out.println("@location(" + seg[0] + "}, the height: " + seg[1]);
			};
		} catch (Exception e) {
			System.out.println("Encountered Exception: " + e.getMessage());
		}
	}
	
	public static List<int[]> skyline(int[][] buildings) throws Exception {
		List<int[]> segments = new Vector<int[]>();
		
		TreeMap<Integer, Integer> segs = new TreeMap<Integer, Integer>();
		segs.put(0,  0);
		
		for(int i = 0; i < buildings.length; i++) {
			Integer bLeft = buildings[i][0];
			Integer bRight = buildings[i][1];
			Integer bHeight = buildings[i][2];
			
			Integer left2bLeft = segs.floorKey(bLeft);
			if(left2bLeft == null)
				throw new Exception("Input error.");
			Integer currentHeight = segs.get(left2bLeft);
			Integer currentHeightWOThisBuilding = currentHeight;
			if(currentHeight < bHeight) {
				segs.put(bLeft, bHeight);
				currentHeight = bHeight;
			}
			
			Integer right2bLeft = segs.higherKey(bLeft) ;

			if(right2bLeft == null || right2bLeft > bRight) {
				segs.put(bRight, currentHeightWOThisBuilding); //set the sky down to previous level at the right edge
			} else {
				Integer left2bRight = segs.floorKey(bRight);
				for(Integer k:segs.subMap(right2bLeft, true, left2bRight, true).keySet()) {
					currentHeightWOThisBuilding = segs.get(k);
					if(currentHeightWOThisBuilding < bHeight)
						if (currentHeight == bHeight)
							segs.remove(k);
						else {
							segs.put(k, bHeight);
							currentHeight = bHeight;
						}
					else {
						currentHeight = currentHeightWOThisBuilding;
					}
				}
			}
			
			if(currentHeightWOThisBuilding < bHeight)
				segs.put(bRight, currentHeightWOThisBuilding);
		}
		
		//Copy the final result to the return list
		for(Integer k:segs.keySet()) {
			int[] entry = new int[2];
			entry[0] = k;
			entry[1] = segs.get(k);
			segments.add(entry);
		}
		
		return segments;
	}

}
