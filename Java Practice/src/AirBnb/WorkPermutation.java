/*
 * The problem statement: two dimensional word permutation problem  
 */
package AirBnb;

import java.util.ArrayList;

public class WorkPermutation {

	public static void main(String[] args) {
		String word = "test";
		
		ArrayList<String> words = find(word);
		
		for(String s:words) {
			System.out.println(s);
		}

	}
	
	/*
	 * Function to find all permutations, it is an wrapper so recursion related signature is not exposed to caller
	 */
	public static ArrayList<String> find(String word) {
		ArrayList<String> results = new ArrayList<String>();
		
		permutation(word, "", results);
		
		return results;
	}

	/*
	 * THe recursive algorithm to generate all permutations
	 */
	private static void permutation(String word, String prefix, ArrayList<String> results) {
		//Exit condition
		if(prefix.length() == word.length()) {
			results.add(prefix);
			return;
		}
		
		//Prepare for available set of chars to be used for the next letter of the string
		ArrayList<Character> availableChars = new ArrayList<Character>();
		for(int i = 0; i < word.length(); i++) {
			availableChars.add(word.charAt(i));
		}
		for(int i = 0; i < prefix.length(); i ++) {
			int idx = availableChars.indexOf(prefix.charAt(i));
			if(idx >= 0) {
				availableChars.remove(idx);
			}
		}

		// For all possible chars, append to the prefix and continue on building rest of the word.
		for(char c:availableChars) {
			permutation(word, prefix + c, results);
		}
		
	}
}
