/*
 * Problem Statement: Given a dictionary, and a matrix of letters, find all the words in the 
 * matrix that are in the dictionary. (Going across, down or diagonally) 
 */
package AirBnb;

import java.util.ArrayList;
import java.util.HashMap;

public class CrossWord {

	public static void main(String[] args) {
		char[][] matrix = {
				{'C', 'A', 'B'},
				{'A', 'A', 'U'},
				{'R', 'A', 'T'}
		};
		HashMap<String, String> dict  = new HashMap<String, String>();
		dict.put("CAT", "Bluh");
		dict.put("CAB", "Bluh");
		dict.put("AT", "Bluh");
		dict.put("BUT", "Bluh");
		
		ArrayList<String> words = new ArrayList<String>();
		
		findWords(matrix, dict, words);
		
		for(String s:dict.keySet()) {
			System.out.println(s);
		}
		
	}
	
	final static int MSize = 3;
	
	public static void findWords(char[][] m, HashMap<String, String> d, ArrayList<String> w) {
		
		for(int i = 0; i < MSize; i++) {
			for(int j = 0; j < MSize; j++) {
				//Cross
				String s = "";
				for(int k = j; k < MSize; k++) {
					s = s + m[i][k];
					if(d.containsKey(s))
						w.add(s);
				}
				//Down
				s = "";
				for(int k = i; k < MSize; k++) {
					s = s + m[k][j];
					if(d.containsKey(s))
						w.add(s);
				}
				//Diagonal
				s = "";
				for(int k = 0; k < MSize - i && k < MSize - j; k++) {
					s = s + m[i + k][j + k];
					if(d.containsKey(s))
						w.add(s);
				}
			}
		}
		
	}

}
