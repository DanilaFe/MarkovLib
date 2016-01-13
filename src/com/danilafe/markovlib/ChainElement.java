package com.danilafe.markovlib;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A chain element that holds data of values of the same type after it.
 * @author vanilla
 *
 * @param <T>
 */
public class ChainElement<T> {

	/**
	 * Number of occurrences of each T in the element.
	 */
	public HashMap<T, Integer> occuranceCount = new HashMap<T, Integer>();
	/**
	 * Relative frequency map, updated when updated() is called.
	 */
	public HashMap<T, Float> relativeFrequency = new HashMap<T, Float>();
	/**
	 * Number of contained items, including repeats
	 */
	public int itemCount;
	/**
	 * The value of this chain itself.
	 */
	public T value;

	/**
	 * Creates a new chain element with the given value.
	 * @param value
	 */
	public ChainElement(T value) {
		this.value = value;
	}

	/**
	 * Adds the given item to the element
	 * @param toAdd the item to add
	 */
	public void add(T toAdd){
		occuranceCount.put(toAdd,(occuranceCount.containsKey(toAdd)) ? occuranceCount.get(toAdd) + 1 : 1);
		itemCount ++;
	}

	/**
	 * Adds all the items in the collection to this element
	 * @param collection the collection to take elements from
	 */
	public void add(Collection<T> collection) {
		Iterator<T> collectionIterator = collection.iterator();
		while(collectionIterator.hasNext()){
			T nextValue = collectionIterator.next();
			add(nextValue);
		}
	}

	/**
	 * Adds the given item(s) to the element.
	 * @param ts
	 */
	public void add(T ... ts){
		for(int i = 0; i < ts.length; i++) add(ts[i]);
	}

	/**
	 * Updates the frequency values from calculated floats, and deletes elements that no longer exist.
	 */
	public void update(){
		for(T key : occuranceCount.keySet()){
			relativeFrequency.put(key, (float) occuranceCount.get(key) / itemCount);
		}
		for(T key : relativeFrequency.keySet()){
			if(!occuranceCount.containsKey(key)) relativeFrequency.remove(key);
		}
	}

	/**
	 * Gets a likely option from the list of items, based on the frequency of occurences.
	 * @return an item chosen using (weighed) randomness.
	 */
	public T getRandom(){
		double random = Math.random();
		for(T key : relativeFrequency.keySet()){
			if((random -= relativeFrequency.get(key)) <= 0) return key;
		}
		return null;
	}

}
