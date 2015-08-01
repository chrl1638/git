/*
 * Problem statement: This is CodeKata 6. Given a word file wordlist.txt, find all anagrams.
 */
package Facebook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Anagrams {

	public static void main(String[] args) {
		long startTime, endTime;
		
		startTime = System.nanoTime();
		//Load word list
		BufferedReader bReader;
		try {
			bReader = new BufferedReader(new FileReader("src/Facebook/wordlist.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		String word;
		try {
			while((word = bReader.readLine()) != null) {
				dict.put(word, "");
			}
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		endTime = System.nanoTime();
		
		System.out.println("Dictionary reading time is: " + (endTime - startTime) / 1000000 + " mili-seconds.");
		
		startTime = System.nanoTime();
		//generateAnagramsForAllWordsNaive();
		generateAnagramsForAllWords();
		endTime = System.nanoTime();
		
		System.out.println("The process time is: " + (endTime - startTime)/1000000 + " mili-second.");
		
		startTime = System.nanoTime();
		try {
			FileWriter fw = new FileWriter("src/Facebook/anagrams.txt");
			for(ArrayList<String> lst: anagrams.values()) {
				if(lst.size() > 1) {
					for(String s:lst) {
						fw.write(s + " ");
					}
					fw.write("\n");
				}
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		
		System.out.println("Time for output to text file is: " + (endTime - startTime)/1000000 + " mili-seconds.");
		
	}
	
	private static TreeMap<String, String> dict = new TreeMap<String, String>();
	private static TreeMap<String, ArrayList<String>> anagrams = new TreeMap<String, ArrayList<String>>();
	
	/*
	 * A naive solution, when the word length is greater than 10, finding anagrams for one word becomes a problem
	 * (d!). This method works in theory, but doesn't work in practice. Running for half day only get to "Ab...".
	 */
	public static void generateAnagramsForAllWordsNaive() {
		for(String s:dict.keySet()) {
			System.out.println("Processing: [" + s + "]");
	
			if(s.equals("Aaronsburg's")) {
				System.out.println("Stop");
			}

	 		if(dict.get(s) != "") {
				continue;
			}
			ArrayList<String> al = new ArrayList<String>();
			anagrams("", s, s, al);
			if(al.size() > 0) {
				al.add(s);
				anagrams.put(s, al);
			}
		}
	}
	
	public static void anagrams(String prefix, String charSet, String originalWord, ArrayList<String> al) {
		if(charSet.length() == 0) {
			if(!prefix.equals(originalWord)) {
				if(dict.get(prefix) != null) {
					al.add(prefix);
					dict.put(prefix, originalWord);
				}
			}
			return;
		}
		for(int i = 0; i < charSet.length(); i++) {
			String newCharSet;
			if(i == 0)
				newCharSet = charSet.substring(1);
			else {
				newCharSet = charSet.substring(0,  i) + charSet.substring(i + 1);
			}
			anagrams(prefix + charSet.charAt(i), newCharSet, originalWord, al);
		}
	}
	
	/*
	 * A better solution
	 */
	public static void generateAnagramsForAllWords() {
		List<Character> keyCharSet = new ArrayList<Character>();
		for(String s:dict.keySet()) {
			//Sort the letters of the word
			keyCharSet.clear();
			for(int i = 0; i < s.length(); i++) {
				keyCharSet.add(s.charAt(i));
			}
			Collections.sort(keyCharSet);
			String key = keyCharSet.toString();
			ArrayList<String> anagramsForKey = anagrams.get(key);
			if(anagramsForKey == null) {
				anagramsForKey = new ArrayList<String>();
				anagrams.put(key,  anagramsForKey);
			}
			anagramsForKey.add(s);
		}
	}
}
