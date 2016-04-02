package com.danilafe.markovlib;

import java.util.ArrayList;

/**
 * ChainManager used to manage ChainElements and train for results.
 * @author vanilla
 *
 * @param <T>
 */
public class ChainManager<T> {

	/**
	 * The chain elements contained in this chain manager
	 */
	public ArrayList<ChainElement<T>> containedElements = new ArrayList<ChainElement<T>>();
	/**
	 * The start value
	 */
	public T startvalue;
	/**
	 * The end value
	 */
	public T endvalue;

	/**
	 * Creates a new ChainManager with the given start and end element values
	 * @param startelement the start value
	 * @param endelement the end value
	 */
	public ChainManager(T startelement, T endelement) {
		this.startvalue = startelement;
		this.endvalue = endelement;
	}

	/**
	 * Gets a chain element for the given value.
	 * @param t the value to search for
	 * @return the chain element or null if no chain elements with that value exist. 
	 */
	public ChainElement<T> getChainElementFor(T t){
		for(ChainElement<T> chainElement : containedElements)
			if(chainElement.value.equals(t)) return chainElement;
		return null;
	}

	/**
	 * Creates a new chain element with the given value, add it to internal list and returns it. 
	 * @param t the value of the new chain element
	 * @return the generated chain element
	 */
	public ChainElement<T> createChainElementFor(T t){
		ChainElement<T> newElement = new ChainElement<T>(t);
		containedElements.add(newElement);
		return newElement;
	}

	/**
	 * Loads or creates chain element with the given value
	 * @param t the value
	 * @return the chain element
	 */
	public ChainElement<T> chainElementFor(T t){
		ChainElement<T> element = null;
		return ((element = getChainElementFor(t)) == null) ? createChainElementFor(t) : element;
	}

	/**
	 * Trains this element with array of values, in oder
	 * @param ts the array of values. 
	 */
	public void train(T...ts){
		for(int i = 0; i < ts.length; i++){
			T previousValue = (i > 0) ? ts[i - 1] : startvalue;
			ChainElement<T> previousElement = chainElementFor(previousValue);
			previousElement.add(ts[i]);
		}

		if(ts.length > 0) chainElementFor(ts[ts.length - 1]).add(endvalue);
	}

	/**
	 * Generates a new chain. This also updates the internal elements
	 * @return the generated chain list
	 */
	public ArrayList<T> generateChain() {
		for(ChainElement<T> element : containedElements) element.update();

		ArrayList<T> generatedValues = new ArrayList<T>();
		for(T lastValue = startvalue; !(lastValue = chainElementFor(lastValue).getRandom()).equals(endvalue);) {
			generatedValues.add(lastValue);
		}

		return generatedValues;
	}

}
