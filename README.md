# MarkovLib
A library that helps with creation of Markov chains, of any type.
More info on Markov Chains can be found on their [wiki page](https://en.wikipedia.org/wiki/Markov_chain)

## Usage
First, you need to create a ChainManager of the type you want your chain to be. For example, if I want to generate sentences from other sentences, I'd want to use the String type when I create my ChainManager.
```Java
ChainManager<String> chainManager = new ChainManager<String>("__start__", "__end__");
```
In the above piece of code, __start__ and __end__ are aribtrary values that are used to denote the beginning and the end of a chain. They can be anything you want.

Now you're ready to train the ChainManager!
To train it, you need to pass it an array of values of the type you specified before, in the order that you wish they are fed. It's important that you pass the entire array in one call, because each call starts apending to the __start__.
Essentially, running
```Java
chainManager.train("a");
chainManager.train("b");
```
Will result in a structure looking like this:
```
__start__ --> a (50%)
__start__ --> b (50%)
```
On the other hand, running
```Java
chainManager.train("a", "b");
```
Will result in the following structure:
```
__start__ --> a (100%)
a --> b (100%)
```
When you want to generate a chain, use
```Java
ArrayList<String> output = chainManager.generateChain();
```
Note that the array list is of the same type as ChainManager.
