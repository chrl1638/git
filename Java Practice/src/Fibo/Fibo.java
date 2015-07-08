/*
 * This is a typical fibonachi sequence calculation problem.
 * 		The iterative approach: O(n)
 * 		The recursive non-polinormial approach: O(2^n)
 * 		Challenges:
 * 			The polinormial approach: O(n)
 * 			The polinormial approach: with math optimization O(log(n)) (Why math is so important? !)
 */

package Fibo;

import java.util.Scanner;

public class Fibo {

	public static void main(String[] args) {	
		System.out.print("Input value of n:");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		System.out.println("FB w/ math geek " + n + " = " + fbMathGeek(n));
		System.out.println("FB w/ iterative" + n + " = " + fbIterative(n));
		System.out.println("FB w/ recursive polinormial " + n + " = " + fbRecursivePolinormial(n, new FB(), new FB()));
		System.out.println("FB w/ recursive non-polinormial " + n + " = " + fbRecursiveNonPolinormial(n));
	}
	
	//The straight forward recursive method is O(2^n) in computationally and O(n) in storage
	public static long fbRecursiveNonPolinormial(int n) {
		return n<=1?n:fbRecursiveNonPolinormial(n-1) + fbRecursiveNonPolinormial(n-2);
	}
	
	//This is the most interesting part and why I tried this project
	//The solution posted here is more of using recursion to trace down to the bottom of the well and carry back value level by level
	//Other options (not coded here): fb1 and fb2 can actually be just static variable. Also, we can use a hash table to save all the past fb values 
	public static long fbRecursivePolinormial(int n, FB fb1, FB fb2) {
		if(n <= 1) {
				fb1.setFb(1); 
				fb2.setFb(0);
				return n;
		}
		//we just use recursive to go deep and carries value back level by level. so dummy is used here
		//Since java only pass by value, so use object to simulate pass by reference (pass refernece to the object by value)
		long dummy = fbRecursivePolinormial(n - 1, fb1, fb2);
		long fb = fb1.getFb() + fb2.getFb();
		fb2.setFb(fb1.getFb());
		fb1.setFb(fb);
		return fb;
	}
	
	public static long fbIterative(int n) {
		if(n<=1) return n;
		
		long fb = 0;
		long fb1 = 1;
		long fb2 = 0;
		
		for(int i = 2; i <=n; i++) {
			fb = fb1 + fb2;
			fb2 = fb1;
			fb1 = fb;
		}
		return fb;
	}
	
	/*
	 * From FB definition, we can induct following formulas (not work for n =2, because k+1 = 2, dead loop):
	 * 		f(2k) = f(k)(2f(k + 1) - f(k));
	 * 		f(2k+1) = f(k+1)^2 + f(k)^2
	 * Thus we have O(log(n)) for both computation and space
	 */
	public static long fbMathGeek(int n) {
		if(n <= 1) return n;
		if(n == 2) return 1;
		if(n%2 == 0) 
			return fbMathGeek(n/2) * (2*fbMathGeek(n/2+1) - fbMathGeek(n/2));
		return (long) (Math.pow((double)fbMathGeek((n+1)/2), 2) + Math.pow((double)fbMathGeek((n-1)/2), 2));
	}
}

class FB {
	long fb;

	public long getFb() {
		return fb;
	}

	public void setFb(long fb) {
		this.fb = fb;
	}
}
