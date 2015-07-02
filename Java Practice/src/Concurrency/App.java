package Concurrency;

import java.util.concurrent.*;
public class App {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for(int id=0; id< 10; id++) {
			executor.submit(new Processor(id));
		}
		executor.shutdown();
		System.out.println("All tasks submitted.");
		try {
			executor.awaitTermination(1,  TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All tasks completed.");
	}
}

class Processor implements Runnable {
	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		System.out.println("Processor "+id+" started.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Processor "+id+" finished.");
	}
	
}