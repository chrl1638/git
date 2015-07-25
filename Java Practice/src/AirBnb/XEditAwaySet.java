/*
 * Problem Statement: Find all words from a dictionary that are x edit distance away. 
 */
package AirBnb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class XEditAwaySet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> dict = new HashMap<String, String>();

		dict.put("Test", "Test");
		dict.put("Tst", "Tst");
		dict.put("Tast", "Tast");
		dict.put("Teest", "Teest");
		
		dict.put("Tt", "Tt");
		dict.put("Taat", "Taat");
		dict.put("Teasat", "Teasat");
		dict.put("Far far away", "Far far away");
		
		HashMap<String, Set<String>> mp = findSets(dict, 2);
		
		for(String s:mp.keySet()) {
			System.out.println(s + ":");
			for(String t:mp.get(s)) {
				System.out.print(t + " ");
			}
			System.out.println();
		}
	}
	
	public static HashMap<String, Set<String>> findSets(HashMap<String, String> dictionary, int k) {
		HashMap<String, Set<String>> map = new HashMap<String, Set<String>>();
		for(String s:dictionary.keySet()) {
			for(String t:dictionary.keySet()){
				if(editDistance(s, t) == k) {
					Set<String> set = map.get(s);
					if(set == null) {
						set = new HashSet<String>();
						map.put(s, set);
					}
					set.add(t);
				}
			}
		}
		return map;
	}
	
	public static int editDistance(String s, String t) {
		if(s == null && t == null) {
			return 0;
		}
		if(s == null || s.length() == 0) {
			return t.length();
		}
		if(t == null || t.length() == 0) {
			return s.length();
		}
		if(s.charAt(0) == t.charAt(0)) {
			return editDistance(s.substring(1), t.substring(1));
		} else {
			int min = Integer.MAX_VALUE;
			//replace
			min = 1 + editDistance(s.substring(1), t.substring(1));
			//insertion
			int d = 1 + editDistance(s, t.substring(1));
			if(d < min)
				min = d;
			//deletion
			d = 1 + editDistance(s.substring(1), t);
			if(d < min)
				min = d;
			return min;
		}
	}

}
