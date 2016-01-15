package com.danilafe.markovlib;

import java.util.ArrayList;

public class ChainManager<T> {

	public ArrayList<ChainElement<T>> containedElements = new ArrayList<ChainElement<T>>();

	public ChainElement<T> getChainElementFor(T t){
		for(ChainElement<T> chainElement : containedElements)
			if(chainElement.value.equals(t)) return chainElement;
		return null;
	}

	public void train(T...ts){

	}

}
