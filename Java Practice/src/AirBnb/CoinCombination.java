/*
 * Problem Statement: Return the coins combination with the minimum number of coins.Time complexity O(MN), where M is the target value and N is the number of distinct coins. Space complexity O(M).  
 * The solution below is only O(N) for both time and space. 
 */
package AirBnb;

public class CoinCombination {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] comb  = combination(93);
		for(int i = 0; i < N; i++ ) {
			System.out.println("" + comb[i] + "@" + faceValues[i]);
		}

	}
	
	private final static int N = 4;
	private static int faceValues[] = {1, 5, 10, 25};
	private static int[] cb = new int[N];
	public static int[] combination(int M) {
		for(int i = N - 1; i >= 0; i--) {
			cb[i] = M/faceValues[i];
			M = M % faceValues[i];
		}
		return cb;
	};
	

}
