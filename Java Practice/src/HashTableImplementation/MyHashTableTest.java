package HashTableImplementation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class MyHashTableTest {

	public static void main(String[] args) {
		
		MyHashTable<String, String> mht = new MyHashTable<String, String>();
		mht.set("1", "a");
		mht.set("2", "b");
		mht.set("3", "c");
		mht.set("4", "d");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Input a number from 1 to 4:");
		int k = sc.nextInt();
		String v = mht.get("3");
		System.out.println("Key = " + k + " value = " + v);
		sc.close();

	}

}

class MyHashTable<K, V> {
	private static final int HASH_TABLE_SIZE = 100;
	private ArrayList<LinkedList<HashNode<K, V>>> list;
	
	public MyHashTable() {
		list = new ArrayList<LinkedList<HashNode<K, V>>>(HASH_TABLE_SIZE);
		for(int i = 0; i < HASH_TABLE_SIZE; i++)
			list.add(i, null);	//prefill will null so we can set at random location
	}

	public V get(K k) {
		int hc = hashcode(k);
		LinkedList<HashNode<K, V>> nodeList = list.get(hc);
		for(HashNode<K, V> n:nodeList) {
			if(n.getKey().equals(k)) return n.getValue();
		}
		return null;
	}
	
	public boolean set(K key, V value) {
		int hc = hashcode(key);
		if(list.get(hc) == null)
			list.set(hc,  new LinkedList<HashNode<K, V>>());
		list.get(hc).add(new HashNode<K, V>(key, value));
		//Todo: Add duplicte check
		//and more Todo: increase the size when collition rate is high
		return true;
	}
	
	private int hashcode(K key) {
		return key.hashCode() % HASH_TABLE_SIZE;
	}
}

class HashNode<K, V> {
	public HashNode(K k, V v) {
		key = k;
		value = v;
	}
	private K key;
	private V value;
	public K getKey() {
		return key;
	}
	public V getValue() {
		return value;
	}
}
