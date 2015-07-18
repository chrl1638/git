package AirBnb;

import java.util.Scanner;

public class MapMaze {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 		
		System.out.println("Iteration 0:");


		System.out.println("Distance (0, 0, 1) = " + distance(0, 1, 0));
		System.out.println("Distance (0, 0, 1) = " + distance(1, 1, 0));
		System.out.println("Distance (0, 0, 1) = " + distance(1, 0, 0));
	
		System.out.println("Iteration 1:");
		System.out.println("Distance (0, 0, 1) = " + distance(0, 0, 1));
		System.out.println("Distance (1, 0, 1) = " + distance(1, 0, 1));
		System.out.println("Distance (1, 1, 1) = " + distance(1, 1, 1));
		System.out.println("Distance (0, 1, 1) = " + distance(0, 1, 1));
		

		System.out.println("Distance (0, 2, 1) = " + distance(0, 2, 1));
		System.out.println("Distance (0, 3, 1) = " + distance(0, 3, 1));
		System.out.println("Distance (1, 3, 1) = " + distance(1, 3, 1));
		System.out.println("Distance (1, 2, 1) = " + distance(1, 2, 1));

		
	
		System.out.println("Distance (2, 2, 1) = " + distance(2, 2, 1));
		System.out.println("Distance (2, 3, 1) = " + distance(2, 3, 1));
		System.out.println("Distance (3, 3, 1) = " + distance(3, 3, 1));
		System.out.println("Distance (3, 2, 1) = " + distance(3, 2, 1));



		System.out.println("Distance (3, 1, 1) = " + distance(3, 1, 1));
		System.out.println("Distance (2, 1, 1) = " + distance(2, 1, 1));
		System.out.println("Distance (2, 0, 1) = " + distance(2, 0, 1));
		System.out.println("Distance (3, 0, 1) = " + distance(3, 0, 1));
		*/		

		
		while(true) {
			System.out.print("Input x:");
			int x = sc.nextInt();
			System.out.print("Input y:");
			int y = sc.nextInt();
			System.out.print("Input iteration:");
			int iteration = sc.nextInt();
		
			System.out.println("Distance = " + distance(x, y, iteration));
		}

		
	}

	/*
	 * Recursive function to calculate the distance
	 */
	public static int distance(int x, int y, int iteration) {
		if(iteration == 0) {
			return quadrant(x, y, 0);
		}
		int q = quadrant(x, y, iteration);
		int offset = q * (int)Math.pow(2, iteration * 2);
		Point p = new Point(x, y);
		shiftFlipBackXY(p, iteration, q);
		return offset + distance(p.getX(), p.getY(), iteration -1);
	}
	
	/*
	 * Identify which quadrant the point lies
	 * Clockwise lower left is 0
	 */
	
	private static int quadrant(int x, int y, int iteration) {
		int mid = (int) Math.pow(2, iteration);
		if(x >= mid)
			if(y >= mid)
				return 2;
			else
				return 3;
		else
			if(y >= mid)
				return 1;
			else
				return 0;
		
	}

	/*
	 * Convert (x, y) using shifting and fliping to one-previous iteration (x', y')
	 */
	private static void shiftFlipBackXY(Point p, int iteration, int q) {
		int shiftDistance = (int) Math.pow(2,iteration);
		switch(q) {
		case 0: 
			//no shift
			//flip along y=x line
			int temp;
			temp = p.getX();
			p.setX(p.getY());
			p.setY(temp);
			break;
		case 1: 
			//shift down
			p.setY(p.getY() - shiftDistance);
			break;
		case 2:
			//shit left & down
			p.setX(p.getX() - shiftDistance);
			p.setY(p.getY() - shiftDistance);
			break;
		case 3:
			//shift left
			p.setX(p.getX() - shiftDistance);
			//flip along y=-x + b line
			double b = (Math.pow(2, iteration) - 1) / 2;
			double x, y;
			x = p.getX() - b;
			y = p.getY() - b;
			p.setX((int)(-y + b));
			p.setY((int)(-x + b));
			break;
		}
	}
}

/*
 * This class is needed for by pass java "pass by value" limitation
 */
class Point {
	int x;
	int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
