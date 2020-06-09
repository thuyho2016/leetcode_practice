package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* 284. Peeking Iterator
https://leetcode.com/problems/peeking-iterator/
 
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 

You call next() the final time and it returns 3, the last element. 

Calling hasNext() after that should return false.

Follow up: How would you extend your design to be generic and work with all types, not just integer?


Time & Space Complexity : All methods: O(1).

 */
public class PeekingIterator implements Iterator<Integer> {
	
	private Iterator<Integer> iter;
    private Integer peekedValue = null;  //cur
    
	public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
	}
	

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if (!iter.hasNext()) {
			throw new NoSuchElementException();  //if the next element doesn't exist, throw a NoSuchElementException
		} 
        if( peekedValue == null && iter.hasNext()) {
        	peekedValue = iter.next();
        }
        return peekedValue;
	}
	
	@Override
	public Integer next() {
		 /* Firstly, we need to check if we have a value already
         * stored in the peekedValue variable. If we do, we need to
         * return it and also set peekedValue to null so that the value
         * isn't returned again. */
		
		if( peekedValue != null) {
			Integer toReturn = peekedValue;
			peekedValue = null;
			return toReturn;
		}
		
		if (!iter.hasNext()) {
	            throw new NoSuchElementException();
		}
		return iter.next();
	}
	
	@Override
	public boolean hasNext() {
	    return peekedValue != null || iter.hasNext();
	}
	
	public static void main(String[] args)
	{
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		Iterator iterator = list.iterator();
		
		PeekingIterator o = new PeekingIterator(iterator);
		System.out.println(o.next());  //1
		System.out.println(o.peek());  //2
		System.out.println(o.next());  //2
		System.out.println(o.next()); //3
		System.out.println(o.hasNext()); //false
	}
}
