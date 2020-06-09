
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


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

// Accepted by Leetcode - DFS
public class FlattenNestedList_ByQueue implements Iterator<Integer> {
	private Queue<Integer> q = new LinkedList<Integer>();;
    
    public FlattenNestedList_ByQueue(List<NestedInteger> nestedList) {
        if (nestedList != null) {
            flatten(nestedList);    
        }
    }

    //recursive
    private void flatten(List<NestedInteger> nestedList) {
    	 if(nestedList == null || nestedList.size() == 0)
             return;
       // for (int i = 0; i < nestedList.size(); i++) {
       // 	NestedInteger item = nestedList.get(i);
       for (NestedInteger item: nestedList) 	
        	if (item.isInteger()) {
        		q.add(item.getInteger());
        	} else {
        		flatten(item.getList());
        	}
        }
    
    
    
    @Override
    public Integer next() {
    	if(!q.isEmpty()) {
    		return (Integer) q.poll();  // remove the head of queue
    	}
        return null;
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
	 
    //[[1,1],2,[1,1]]
    //Output: [1,1,2,1,1]
    
    public static void main(String[] args) {
		 
    	List<NestedInteger> nestedList = null;
		FlattenNestedList i = new FlattenNestedList(nestedList);
		while (i.hasNext()) {
			System.out.println( i.next());
		}
	
    }
}
