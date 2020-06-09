
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

*/
public class NestedListWeightSum1 {
	/**
		
	 * // This is the interface that allows for creating nested lists.
	 * // You should not implement it, or speculate about its implementation
	 * public interface NestedInteger {
	 *     // Constructor initializes an empty nested list.
	 *     public NestedInteger();
	 *
	 *     // Constructor initializes a single integer.
	 *     public NestedInteger(int value);
	 *
	 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
	 *     public boolean isInteger();
	 *
	 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
	 *     // Return null if this NestedInteger holds a nested list
	 *     public Integer getInteger();
	 *
	 *     // Set this NestedInteger to hold a single integer.
	 *     public void setInteger(int value);
	 *
	 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
	 *     public void add(NestedInteger ni);
	 *
	 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
	 *     // Return null if this NestedInteger holds a single integer
	 *     public List<NestedInteger> getList();
	 * }
	 */
	
	//Solution - recursive DFS 
    //We go through the list of nested integers one by one, keeping track of the current depth dd. 
    //If a nested integer is an integer nn, we calculate its sum as n × d. 
    //If the nested integer is a list, we calculate the sum of this list recursively using the same process but with depth d+1.
    public static int depthSum(List<NestedIntegerImpl> nestedList) {
	    return depthSumHelper(nestedList, 1);
    }

	private static int depthSumHelper(List<NestedIntegerImpl> list, int depth) {
	    int sum = 0;
	    
	    for (NestedIntegerImpl n : list) {
	        if (n.isInteger()) {
	            sum += n.getInteger() * depth;
	        } else {
	            sum += depthSumHelper(n.getList(), depth + 1);
	        }
	    }
	    return sum;
	}     
	
	//BFS
	public int depthSumInverse(List<NestedInteger> nestedList) {
		
		int sum = 0;
		int depth = 1;
		
		Queue<List<NestedInteger>> queue = new LinkedList<>();
		queue.offer(nestedList);
		
		while(!queue.isEmpty()) {
			int n = queue.size();
			
			for(int i=0;i<n;i++) {
				List<NestedInteger> list = queue.poll();
				for(NestedInteger nestedInteger: list)
					if (nestedInteger.isInteger()) 
						sum += nestedInteger.getInteger() * depth;
					else
						queue.offer(nestedInteger.getList());
			}
			depth++;
		}
		return sum;
    }
	
	public static void main (String[] args)
    {
		//[1,[4,[6]]]
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
		 
		 System.out.print("Display elements: ");
			
		 for (NestedIntegerImpl obj : list3) {
			if (obj.isInteger())
				System.out.print(obj.getInteger() + " ");
			else {
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
		 }
		 
		 System.out.println("\nSum of nested Lists: " + depthSum(list3));	  
    }
}
