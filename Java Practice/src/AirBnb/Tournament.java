/*
 * Problem Statement: Store a set of sudden-death tournament results in a compact format (eg. a bit array) and a set 
 * of predicted match results (also in a bit array). Score the predictions, giving one point per correctly guessed 
 * match, without unpacking the bit array into a more convenient format (ie. you have to traverse the tree in-place).
 * 
 * Some assumptions: I assume if the lower prediction is already failed, then he does't get credit for upper 
 * predictions. Without this assumption, the solution can be simpler by just using xor and count number of ones.
 */

package AirBnb;

import java.util.BitSet;

public class Tournament {

	public static void main(String[] args) {
		BitSet results = new BitSet(15);
		BitSet prediction = new BitSet(15);
		
		results.set(1, true);
		results.set(4, true);
		results.set(5, true);

		prediction.set(1, true);
		prediction.set(4, true);
		prediction.set(5, true)
		;
		prediction.set(3, true);

		
		int s = score(results, prediction);
		
		System.out.println("Score= " + s);

	}
	
	static final int MaxSize = 15;
	static int timer = 0;
	static Day[] predictionStatus = new Day[MaxSize];
	static int[] score = new int[MaxSize];
	private enum Day {Succeeded, Failed};
	
	public static int score(BitSet results, BitSet prediction) {
		for(int i = 0;  i < MaxSize; i++) {
			predictionStatus[i] = Day.Succeeded;
			score[i] = 0;
		}
		timer = 0;
		int sc = dfs(results, prediction, 0);
		return sc;
	}
	
	
	
	
	private static int dfs(BitSet r, BitSet p, int node) {
		int sc1 = 0; 
		int sc2 = 0;
		int ps = p.size();
		if(2*node + 2 < MaxSize) {
			//not leaf node
			sc1 = dfs(r, p, 2*node + 1);
			sc2 = dfs(r, p, 2*node + 2);
			if(predictionStatus[2*node +1] == Day.Failed || predictionStatus[2 * node + 2] == Day.Failed) {
				predictionStatus[node] = Day.Failed;
				return (sc1 + sc2);
			}
			if(p.get(node) == r.get(node)){
				predictionStatus[node] = Day.Succeeded;
				return(sc1+sc2+1);
			} else {
				predictionStatus[node] = Day.Failed;
				return(sc1 + sc2);
			}
		} else {
			predictionStatus[node] = Day.Succeeded;
			return 0;
		}
		
	}

}


