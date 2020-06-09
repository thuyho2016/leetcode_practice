
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.Queue;

/* 339. Nested List Weight Sum
 * https://leetcode.com/problems/nested-list-weight-sum/

Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 10 
Explanation: Four 1's at depth 2, one 2 at depth 1.

Example 2:

Input: [1,[4,[6]]]
Output: 27 
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.


By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 
*/

public class NestedIteratorSum {
	  	
	 // Solution1 DFS with Recursive  use NestedIntegerImpl object - see NestedListWeightSum.java
	 public static int depthSum(List<NestedIntegerImpl> nestedList) {
		//base case
	    if (nestedList.size() == 0) return 0;
	    
	    return depthSumHelper(nestedList, 1);
	}
	
	private static int depthSumHelper(List<NestedIntegerImpl> nestedlist, int depth) {
	    int sum = 0;
	    
	    for (NestedIntegerImpl n : nestedlist) {
	        if (n.isInteger()) {
	            sum += n.getInteger() * depth;
	        } else {
	            sum += depthSumHelper(n.getList(), depth + 1); //depth increased (since its a new nest)
	        }
	    }
	    return sum;
	} 


	//Solution2:  BFS
	public int depthSum2(List<NestedInteger> nestedList) {
        int ans = 0, level = 1;
        
        if(nestedList==null|| nestedList.isEmpty()) return ans;
        
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        
        while(!queue.isEmpty()){
        	
            int size = queue.size();
            
            for(int i=0;i<size;i++){
                NestedInteger nest = queue.poll();
                
                if(nest.isInteger()){
                    int val = nest.getInteger();
                    ans += val*level;
                } else {
                    for(NestedInteger n : nest.getList()){
                        queue.add(n);
                    }
                }
            }
            level++;
        }
        return ans;
    }

	 
	public static void main (String[] agrs) {
	    // ------- Solution 1 ---------- 
		
 		 List<NestedIntegerImpl> list1 = new ArrayList<NestedIntegerImpl>();
 		 NestedIntegerImpl n6 = new NestedIntegerImpl(6);
 		 list1.add(n6);  // list only has 1 element 6
 		 NestedIntegerImpl nestedIntegerList1 = new NestedIntegerImpl(list1); //[6] 
 		 
 		 List<NestedIntegerImpl> list2 = new ArrayList<NestedIntegerImpl>();
 		 NestedIntegerImpl n4 = new NestedIntegerImpl(4);
 		 list2.add(n4);  // add 4 to list
 		 list2.add(nestedIntegerList1);    //[4,[6]]] 
 		 NestedIntegerImpl nestedIntegerList2 = new NestedIntegerImpl(list2);
 		 
 		 NestedIntegerImpl n1 = new NestedIntegerImpl(1);
 		 List<NestedIntegerImpl> list3 = new ArrayList<NestedIntegerImpl>();
 		 list3.add(n1);
 		 list3.add(nestedIntegerList2);  //[1,[4,[6]]]
 		
 		 System.out.print("\nDisplay elements: ");
 		
 		for (NestedIntegerImpl obj : list3) {
 			if (obj.isInteger())
 				System.out.print(obj.getInteger() + " ");
 			else 
 				for (NestedIntegerImpl o : obj.getList()) {
 					if (o.isInteger()) {
 						System.out.print(o.getInteger() + " ");
 					} else {
 						for (NestedIntegerImpl o2 : o.getList()) {
 							System.out.print(o2.getInteger() + " ");
 						}
 					}
 				}
 		}
 			
 		System.out.println("\nSum: " + depthSum(list3));
	 		
	}
	
}


