package com.danilafe.markovlib;

import java.util.ArrayList;

public class ChainManager<T> {

	public ArrayList<ChainElement<T>> containedElements = new ArrayList<ChainElement<T>>();
	public T startvalue;
	public T endvalue;

	public ChainManager(T startelement, T endelement) {
		this.startvalue = startelement;
		this.endvalue = endelement;
	}

	public ChainElement<T> getChainElementFor(T t){
		for(ChainElement<T> chainElement : containedElements)
			if(chainElement.value.equals(t)) return chainElement;
		return null;
	}

	public ChainElement<T> createChainElementFor(T t){
		ChainElement<T> newElement = new ChainElement<T>(t);
		containedElements.add(newElement);
		return newElement;
	}

	public ChainElement<T> chainElementFor(T t){
		ChainElement<T> element = null;
		return ((element = getChainElementFor(t)) == null) ? createChainElementFor(t) : element;
	}

	public void train(T...ts){
		for(int i = 0; i < ts.length; i++){
			T previousValue = (i > 0) ? ts[i - 1] : startvalue;
			ChainElement<T> previousElement = chainElementFor(previousValue);
			previousElement.add(ts[i]);
		}

		if(ts.length > 0) chainElementFor(ts[ts.length - 1]).add(endvalue);
	}

	public ArrayList<T> generateChain() {
		for(ChainElement<T> element : containedElements) element.update();

		ArrayList<T> generatedValues = new ArrayList<T>();
		for(T lastValue = startvalue; !(lastValue = chainElementFor(lastValue).getRandom()).equals(endvalue);) {
			generatedValues.add(lastValue);
		}

		return generatedValues;
	}

}
