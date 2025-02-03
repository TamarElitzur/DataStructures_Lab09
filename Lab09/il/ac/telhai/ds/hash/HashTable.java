package il.ac.telhai.ds.hash;

import il.ac.telhai.ds.linkedlist.DLinkedList;
import il.ac.telhai.ds.linkedlist.List;


public class HashTable<V> {

	public static final int DEF_MAX_HASH_SIZE = 10;

	private List<V>[] array;
	private int arraySize;


	/**
	 * constructs a hash-table of size 'hashSize'.
	 * @param hashSize, the size of the constructed hash-table.
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public HashTable (int hashSize) {
		array = new DLinkedList[hashSize];
		arraySize = hashSize;
		for (int i = 0; i < hashSize; i++) {
			array[i] = new DLinkedList<>();
		}
	}


	/**
	 * constructor
	 * constructs a hash-table of max-size "DEF_MAX_HASH_SIZE".
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public HashTable() {
		this(DEF_MAX_HASH_SIZE);
	}



	/**
	 * @param val
	 * @return true if the hash-table contains val, otherwise return false
	 */
	public boolean contains(V val) {
		int hash = myHashCode(val);

		if (array[hash].isEmpty()) {
			return false;
		}

		array[hash].goToBeginning();  // Set cursor to the first element

		while (true) {  // Traverse the list
			if (array[hash].getCursor().equals(val)) {  // Check if the current element matches the value
				return true;
			}
			if (!array[hash].hasNext()){
				break;
			}
			array[hash].getNext();  // Move the cursor to the next element
		}

		return false;  // Return false if no match found
	}

	public int myHashCode(V val) {
		return Math.abs(val.hashCode()) % arraySize;
	}

	/**
	 * Add val to the hash-table.
	 * 
	 * @param val
	 * @return If the val has already existed in the the hash-table, it will not be
	 *         added again. Return true if the val was added successfully. Otherwise
	 *         return false.
	 */
	public boolean add(V val) {
		int hash = myHashCode(val);
		if (!contains(val)) {
			array[hash].insert(val);
			return true;
		}
		return false;

	}

	/**
	 * Remove val from the hash-table.
	 * 
	 * @param val
	 * @return Return true if the val was removed successfully. Otherwise return
	 *         false.
	 */
	public boolean remove(V val) {
		int hash = myHashCode(val);
		if (contains(val)){
			array[hash].remove(val);
			return true;
		}
		return false;
	}

	/**
	 * clear all the data in the hash-table
	 */
	public void clear() {
		for (int i = 0; i < arraySize; i++) {
			array[i].clear();
		}
	}



	/**
	 * @return true if the hash-table is empty, otherwise return false.
	 */
	public boolean isEmpty() {
		for (int i = 0; i < arraySize; i++) {
			if (!array[i].isEmpty()){
				return false;
			}
		}
		return true;
	}
}