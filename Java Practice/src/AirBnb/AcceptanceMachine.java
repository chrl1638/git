package AirBnb;

import java.util.ArrayList;

public class AcceptanceMachine {
	private final static int TransitionMatrixSize = 4;
	
	public static void main(String[] args) {
		String[][] tMatrix = {
				{"A", "C", "A", "A"},
				{"DC", "A", "D", "A"},
				{"B", "A", "A", "B"},
				{"C", "D", "A", "A"} 
		};
		
		AcceptanceMachine am = new AcceptanceMachine("AB", tMatrix);
		
		String testString = "ABBCD";
		
		System.out.println("Does the machine accept (" + testString + ")? " + am.accept(testString));
	}
	

	
	private String acceptanceSet;		//Hold the acceptable set of chars
	
	private String[][] transitionMatrix = new String[TransitionMatrixSize][TransitionMatrixSize];
	
	/*
	 * Given an acceptance set aSting, and a transition matrix x, construct an instance of AcceptanceMachine
	 */
	public AcceptanceMachine(String aString, String[][] m) {
		acceptanceSet = aString;
		for(int i = 0; i < TransitionMatrixSize; i++) {
			for(int j = 0; j < TransitionMatrixSize; j++) {
				transitionMatrix[i][j] = m[i][j];
			}
		}
	}
	
	/*
	 * Assistant variables. In this exercise, finished can be replace with accepted. 
	 */
	private boolean accepted = false;
	private boolean finished = false;
	
	/*
	 * Function to check the machine's acceptance of input string "in"
	 */
	public boolean accept(String in) {
		accepted = false;
		finished = false;
		
		/*
		 * Carry input string into internal ArrayList representation
		 */
		ArrayList<String> input = new ArrayList<String>();
		for(int i = 0; i < in.length(); i++) {
			input.add(i, in.substring(i,i + 1));
		}
		
		/*
		 * Run check
		 */
		run(input);
		return accepted;
	}
	
	/*
	 * The recursive function to run the check. Recursion is used for two purposes:
	 * 		a) advance in transformation, and b) reduce degree of non-deterministic [DC}A -> DA and CA
	 * Both a) and b) can actually be down simply by iterative approach. For a) it is straight forward. For b), we can just "cross-
	 * 	product" two non-determinstic strings to form one non-determinsitic string at next level. The iterative implementation is
	 * 	not provided here. 
	 */
	private void run(ArrayList<String> input) {
		if(finished) return; //exit if already found acceptance from another branch
		
		/*
		 * If the input contains only single node of single char (deterministic), check the acceptance of the char and exit.
		 */
		if(input.size() == 1 && input.get(0).length() == 1) {
			//final state, check for acceptance
			if(acceptanceSet.contains(input.get(0))) {
				accepted = true;
				finished = true;
			}
			return;
		}
		
		//Assume input has non-deterministic node, split (reduce) to one deterministic input and and one non-deterministic input
		ArrayList<ArrayList<String>> reducedList = reduce(input);
		if(reducedList.size() > 1) {	//2 to be exact
			for(ArrayList<String> in: reducedList) {
				run(in);
			}
		} else {
			//The input is already deterministic, nothing to reduce, perform transformation and go to next level
			ArrayList<String> next = transform(reducedList.get(0));
			run(next);
		}
		
	}
	
	/*
	 * Method to reduce a non-deterministic input into two inputs: one deterministic, one not but the deterministic
	 * 	 degree is reduced by 1
	 * Returns a list of inputs, one or two to be exact. If the original input is already deterministic, return the original input in 
	 * 	 the list. Otherwise, both newly created input in the list.
	 */
	private ArrayList<ArrayList<String>> reduce(ArrayList<String> input) {
		int i;
		String nodeString = "";
		ArrayList<ArrayList<String>> reducedList = new ArrayList<ArrayList<String>>();
		
		for(i = 0; i < input.size(); i++) {
			nodeString = input.get(i);
			if(nodeString.length() > 1) {
				//Non deterministic node, keep the first choice
				input.set(i, nodeString.substring(0, 1));
				break;
			}
		}
		
		reducedList.add(0, input);
		
		if(i < input.size()) {
			//Found non-deterministic node, create the second split
			ArrayList<String> second = new ArrayList<String>();
			for(int j = 0; j < input.size(); j++ ) {
				if(j == i) {
					second.add(j, nodeString.substring(1, nodeString.length()));
				} else {
					second.add(j, input.get(j));
				}
			}
			reducedList.add(1, second);
		}
		return reducedList;
	}
	
	/*
	 * The method transform the input based on machine's transformation matrix. 
	 * The result is a one letter shorter input to be transformed at the next level. The result could contain non-deterministic options,
	 *   which will be "reduced" by the caller before further transformation.
	 */
	private ArrayList<String> transform(ArrayList<String> input) {
		ArrayList<String> nextInput = new ArrayList<String>();
		for(int i = 1; i < input.size(); i++) {
			nextInput.add(i - 1, transitionMatrix[input.get(i - 1).charAt(0) - 'A'][input.get(i).charAt(0) - 'A']);
		}
		return nextInput;
	}
}
