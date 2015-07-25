/*
 * Problem Statement: Write a method which prints an array in spiral order. Starts at top left, goes to top right, then bottom right, 
 * then bottom left.
 * 
 * Test test
 */
package AirBnb;

public class SpiralPrint {

	public static void main(String[] args) {
		int[][] a = {
				{1, 2, 3, 4, 5, 6},
				{18, 19, 20, 21, 22, 7},
				{17, 28, 29, 30, 23, 8},
				{16, 27, 26, 25, 24, 9},
				{15, 14, 13, 12, 11, 10}
		};
	
		
		print(a, 6, 5);
	}
	public static void print(int[][] a, int width, int height) {
		for(int i = 0; i < (int) (height / 2.0 + 0.5) && i < (int) (width /2.0 + 0.5); i++) {
			//print cross right
			for(int m = i; m < width - i; m++) {
				System.out.print (" " + a[i][m]);
			}
			
			//print down
			for(int m = i + 1; m < height - i - 1; m++) {
				System.out.print (" " + a[m][width  - i - 1]);
			}
			//print cross left
			if(height - i - 1 != i) { //not square with h = 1
				for(int m = width - i - 1; m >= i; m--) {
					System.out.print (" " + a[height - i - 1][m]);
				}
			}
			//print up
			if(i != width - i -1) {  //not square with w = 1
				for(int m = height - i - 2; m > i; m--) {
					System.out.print (" " + a[m][i]);
				}
			}
		}
	}

}
