/*
 * Problem Statement: Sort a list of numbers in which each number is at a distance k from its 
 * actual position
 */
public class KSort {

	public static void main(String[] args) {
		int[] data = {2, 3, 0, 1, 6, 7, 4, 5};
		
		System.out.print("Original Data: [");
		for(int e:data) {
			System.out.print(e + " ");
		}
		System.out.println("]");
		
		System.out.print("Result Data: [");
		kSort(data, 2);
		for(int e:data) {
			System.out.print(e + " ");
		}
		System.out.println("]");
	}
	
	/*
	 * Function to sort an already prepared list of data, where each eliment is k distance away 
	 * from it's supposed-to-be location.
	 */
	public static void kSort(int[] data, int k) {
		for(int i = 0; i < data.length - k; i++) {
			
			if(data[i] > data[i+k]) {
				int t = data[i];
				data[i] = data[i+k];
				data[i+k] = t;
			}
		}
	}

}
