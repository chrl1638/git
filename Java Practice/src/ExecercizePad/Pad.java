/*
 * This is used for doing any random exercises
 */
package ExecercizePad;

public class Pad {
	public static void main(String[] args) {
		testFuncWithVariableSignature(new Test(111));
		testFuncWithVariableSignature(new Test(111), new Test(222), new Test(333));
		
	}
	
	/*
	 * To try out function that take variable number of parameters
	 */
	public static void testFuncWithVariableSignature(Test... tests) {
		for(int i = 0; i < tests.length; i++) {
			System.out.print("No. " + i + " object: " + tests[i].x + " ");
		}
		System.out.println();
	}
}

class Test {
	int x;
	public Test(int xx) {
		x = xx;
	}
}