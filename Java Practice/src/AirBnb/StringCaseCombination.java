/*
 * Problem Statement: Find all the combinations of a string in lowercase and uppercase. For 
 * example, string "ab" -&gt; "ab", "Ab", "aB", "AB". So, you will have 2^n (n = number of 
 * chars in the string) output strings. The goal is for you to test each of these string and 
 * see if it match a hidden string. 
 * 
 * Solution: The first solution is straight forward, the trick point is that intermediate values
 * are passed forward in the recursion. The second solution is simple but easy for after thought.
 */
package AirBnb;

import java.util.ArrayList;

public class StringCaseCombination {

	public static void main(String[] args) {
		String s = "ab";
		
		System.out.println("All combination for [" + s + "] are:");
		for(String scc:findAllCaseCombination(s)) {
			System.out.print(scc + " ");
		}
		
		System.out.println("\nAll combination (Method 2) for [" + s + "] are:");
		for(String scc:findAllCaseCombinationMethod2(s)) {
			System.out.print(scc + " ");
		}

		s = "ceLebRAte";
		
		System.out.println("\nAll combination for [" + s + "] are:");
		for(String scc:findAllCaseCombination(s)) {
			System.out.print(scc + " ");
		}
		
		System.out.println("\nAll combination (Method 2) for [" + s + "] are:");
		for(String scc:findAllCaseCombinationMethod2(s)) {
			System.out.print(scc + " ");
		}
		
	}

	public static ArrayList<String> findAllCaseCombination(String origS) {
		ArrayList<String> sComb = new ArrayList<String>();
		subS("", origS, sComb);
		return sComb;
	}
	
	public static void subS(String prefix, String surfix, ArrayList<String> results) {
		if(surfix == null || surfix.length() == 0) {
			results.add(prefix);
			return;
		}
		subS(prefix + surfix.substring(0, 1).toLowerCase(), surfix.length() == 1?"":surfix.substring(1), results);
		subS(prefix + surfix.substring(0, 1).toUpperCase(), surfix.length() == 1?"":surfix.substring(1), results);
	}
	
	public static ArrayList<String> findAllCaseCombinationMethod2(String origS) {
		ArrayList<String> sComb = new ArrayList<String>();
		for(int i = 0; i < Math.pow(2,  origS.length()); i++){
			int x = i;
			String s = "";
			for(int j = origS.length() - 1; j >= 0;  j--) {
				if(x%2 == 0) {
					s = origS.substring(j, j + 1).toLowerCase() + s;
				} else {
					s = origS.substring(j, j + 1).toUpperCase() + s;
				}
				x = x / 2;
			}
			sComb.add(s);
		}
		return sComb;
	}

}
