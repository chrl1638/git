package AirBnb;

import java.util.ArrayList;

public class CircularBuffer {

	public static void main(String[] args) {
		CircularBuffer cb = new CircularBuffer();
		for(int i = 0; i < 200; i++) {
			cb.add(i);
		}
		
		cb.print();
		
		System.out.println("Element # 32 = " + cb.get(32));

	}

	private final static int BufferSize = 128;
	private static int[] buffer;;
	private int head;
	private int tail;
	public CircularBuffer() {
		buffer = new int[BufferSize];
		head = 0; tail = -1;
	}
	
	public void add(int value) {
		if(tail == -1)
			tail = 0;
		else {
			if((tail + 1) % BufferSize == head) {
				head = (head + 1) % BufferSize;
			}
			tail = (tail + 1) % BufferSize;
		}
		buffer[tail] = value;
		
		return;
	}
	
	public int get(int index) {
		return buffer[(head + index) % BufferSize];
	}
	
	public void print() {
		if(tail == -1) 
			System.out.println("Empty Buffer");
		else {
			int i = head;
			System.out.println("" + (i - head) + ": " + buffer[i]);
			i++;
			while(i != (tail+ 1) % BufferSize) {
				if(i >= head) {
					System.out.println("" + (i - head) + ": " + buffer[i]);
				} else {
					System.out.println("" + (i - head + BufferSize) + ": " + buffer[i]);
				}
				i = (i + 1) % BufferSize;
			}
		}
	}
}
