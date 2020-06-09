
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/* 341. Flatten Nested List Iterator
 * https://leetcode.com/problems/flatten-nested-list-iterator/

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].

Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
 
             
 Time Complexity: O(1)
 
 */

// Accepted by Leetcode

public class FlattenNestedList implements Iterator<Integer> {
	private List<Integer> list = new ArrayList<Integer>();
    
    public FlattenNestedList(List<NestedInteger> nestedList) {
        if (nestedList != null || nestedList.size() != 0) {
            flatten(nestedList, list);    
        }
    }

    //recursive
    private void flatten(List<NestedInteger> nestedList, List<Integer> ilist) {
    	if(nestedList == null)
            return;
    	
       // for (int i = 0; i < nestedList.size(); i++) {
       // 	NestedInteger item = nestedList.get(i);
    	
       for (NestedInteger item: nestedList) 	
        	if (item.isInteger()) {
        		ilist.add(item.getInteger());
        	} else {
        		flatten(item.getList(), ilist);
        	}
        }
    
   
    
    @Override
    public Integer next() {
    	int item = -1;
        
    	if (hasNext())
        {
            item = list.remove(0); // remove(int index) method - Removes the element at the specified position in this list
        }
        return item;
    }

    @Override
    public boolean hasNext() {
        return list.size() > 0;
    }
	 
    public static void main(String[] args) {
        // Input: [[1,1],2,[1,1]]
        //Output: [1,1,2,1,1]  
     	List<NestedInteger> nestedList = new ArrayList<>();
    	
		List<Object> list1 = new ArrayList<>();
		NestedIntegerImpl i1 = new NestedIntegerImpl(1);
		NestedIntegerImpl i2 = new NestedIntegerImpl(2);
		list1.add(i1);
		list1.add(i2);
		
	 	Integer n2 = new Integer(2);
		 
/*	 	List<NestedIntegerImpl> list2 = new ArrayList<NestedIntegerImpl>();
		NestedIntegerImpl e1 = new NestedIntegerImpl(1);
		NestedIntegerImpl e2 = new NestedIntegerImpl(2);
		list2.add(e1);
		list2.add(e2);
			
		nestedList.add(list1); 
		nestedList.add(n2);
		nestedList.add(list2); 
    */	
		FlattenNestedList i = new FlattenNestedList(nestedList);
		while (i.hasNext()) {
			System.out.println( i.next());
		}
		
    		 
	
    }
}
