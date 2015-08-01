/*
 * Problem Statement: Process the dictionary and look for all six letter words which are composed of two 
 * concatenated smaller words.
 */

package Facebook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class WordSplit {

	public static void main(String[] args) {
		long startTime, endTime;
		
		//Load the word list
		startTime = System.nanoTime();
		try {
			BufferedReader bReader = new BufferedReader(new FileReader("src/Facebook/wordlist.txt"));
			String word;
			while((word = bReader.readLine()) != null) {
				dict.put(word, "");
			}
			bReader.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		endTime = System.nanoTime();
		
		System.out.println("File load time is: " + (endTime - startTime) / 1000000 + " mili-seconds.");
		
		//Generate split word
		startTime = System.nanoTime();
		generateSplitWord();
		endTime = System.nanoTime();
		
		System.out.println("Processing time is: " + (endTime - startTime)/1000000 + " mili-seconds.");
		
		startTime = System.nanoTime();
		//Output the result
		try {
			FileWriter fw = new FileWriter("src/Facebook/splitwords.txt");
			for(String s:results.keySet()) {
				String[] subwords = results.get(s);
				fw.write(s + ":" + subwords[0] + "," + subwords[1] + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		
		System.out.println("File output time is: " + (endTime - startTime)/1000000 + " mili-seconds.");
	}

	private static HashMap<String, String> dict = new HashMap<String, String>();
	private static TreeMap<String, String[]> results = new TreeMap<String, String[]>();
	
	public static void generateSplitWord() {
		for(String s:dict.keySet()) {
			if(s.length() == 6) {
				for(int i = 0; i < s.length(); i++) {
					String prefix = s.substring(0, i);
					String surfix = s.substring(i);
					if(surfix.equals("een")) {
						System.out.println("Pause");
					}
 					if(dict.get(prefix) != null && dict.get(surfix) != null) {
						String[] subs = new String[2];
						subs[0] = prefix;
						subs[1] = surfix;
						results.put(s, subs);
						break;	//find one split location is enough
					}
				}
			}
		}
	}
	

}
