/*
 * Problem Statement: Provide a set of positive integers (an array of integers). Each integer
 * represent number of nights user request on Airbnb.com. If you are a host, you need to design
 * and implement an algorithm to find out the maximum number a nights you can accommodate. The 
 * constrain is that you have to reserve at least one day between each request, so that you have 
 * time to clean the room. Example: 1) Input: [1, 2, 3] ===>;output: 4, because you will pick 1 
 * and 3 2) input: [5, 1, 2, 6] ===> output: 11, because you will pick 5 and 6 3) input: [5, 1, 
 * 2, 6, 20, 2] ===> output: 27, because you will pick 5, 2, 20  
 * 
 * Solution: The solution provided also return the detailed schedule besides the total room nights.
 * This solution can be further optimized if we follow DP concept to record intermediate result of each "requestOffset".
 */

package AirBnb;

import java.util.ArrayList;

public class RoomNight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] requests = {1, 2, 3};
		ArrayList<Integer> results = new ArrayList<Integer>();
		int totalNights = scheduleRoomNight(requests, 0, results);
		System.out.println("For [1, 2, 3] Total nights = " + totalNights + "\nSchedule as following:");
		for(int i = results.size() - 1; i >= 0 ; i--) {
			System.out.print(" " + results.get(i));
		}
		System.out.println();
		
		requests = new int[]{5, 1, 2, 6};
		results.clear();
		totalNights = scheduleRoomNight(requests, 0, results);
		System.out.println("For [5, 1, 2, 6] Total nights = " + totalNights + "\nSchedule as following:");
		for(int i = results.size() - 1; i >= 0 ; i--) {
			System.out.print(" " + results.get(i));
		}
		System.out.println();
		
		requests = new int[]{5, 1, 2, 6, 20, 2};
		results.clear();
		totalNights = scheduleRoomNight(requests, 0, results);
		System.out.println("For [5, 1, 2, 6, 20, 2] Total nights = " + totalNights + "\nSchedule as following:");
		for(int i = results.size() - 1; i >= 0 ; i--) {
			System.out.print(" " + results.get(i));
		}
		
	}

	public static int scheduleRoomNight(int[] requests, int requestOffset, ArrayList<Integer> results) {
		if(requestOffset >= requests.length) {
			return 0;
		}
		if(requestOffset == requests.length - 1) {
			results.add(0, requests[requestOffset]);
			return requests[requestOffset];
		}
		results.clear();
		ArrayList<Integer> resultsX = new ArrayList<Integer>();
		ArrayList<Integer> resultsY = new ArrayList<Integer>();
		int x = requests[requestOffset] + scheduleRoomNight(requests, requestOffset + 2, resultsX);
		int y = scheduleRoomNight(requests, requestOffset + 1, resultsY);
		if(x == 0 && y == 0) {
			return 0;
		} else if(x >= y) {
			results.addAll(resultsX);
			results.add(requests[requestOffset]);
			return x;
		} else {
			results.addAll(resultsY);
			return y;
		}
			
	}
}
