
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 364. Nested List Weight Sum II
 * https://leetcode.com/problems/nested-list-weight-sum-ii/

Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. 
i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8 
Explanation: Four 1's at depth 1, one 2 at depth 2.

Example 2:

Input: [1,[4,[6]]]
Output: 17 
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; sum = 1 + 4 = 5; 5+ 6 = 11; 11 +6 = 17.

Steps:
1. Create a Queue and add NestedInteger object into the queue.
2. The queue has size 2. Iterate the queue, I remove the head element of queue to check
if it is Integer or NestedInteger object.
3. If it an integer, I will add value 1 to sum. Now, I got sum = 1
4. Next round, the second element in the queue is a list of NestedInteger objs ( obj has value 4 and a list of obj 6)
     - Remove 4 from the queue, add to sum, so sum 1 + 4 = 5.
     - Queue has a list of [6]. Go to next is a list of NestInterger 6: 
     - Remove a list of [6] from the queue, now queue is empty. sum_cur = 5 + 6 = 11
     - Because  6 is a list of NestInteger, add it back. Size of queue is 1. total_sum = 1 + 5 = 6
     - Remove 6 from the queue: total_sum = 11 + 6 = 17 
 * 
*/
public class NestedListWeightSum2 {
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
	
	//Best solution - BFS
	public static int depthSumInverse(List<NestedIntegerImpl> nestedList) 
    {
        if (nestedList == null) return 0;
        
        Queue<NestedIntegerImpl> q = new LinkedList<>();
        for (NestedIntegerImpl n : nestedList)
        {
            q.add(n); // [0] = 1, [1] = [4,[6]] is a list
        }
        
        int total_sum = 0;
        int sum_cur = 0;
         
        while (!q.isEmpty())
        {
            int size_cur = q.size();  // size 2 which is [1,[4,[6]]] , 2 which is [4,[6]], size = 1 which is [6]           
            for (int i = 0; i < size_cur; ++i)
            {
                NestedIntegerImpl node = q.poll();  //remove node 1, node 4, 6. when node = 6, add to sum 
                if (node.isInteger())     // 1, node 4 is integer
                {
                    sum_cur += node.getInteger();  //1 , 1 + 4 = 5, 5 + 6 = 11 , exit for loop
                }
                else  //[4,[6]], [6] is a list of NestedInteger
                {
                    for (NestedIntegerImpl next : node.getList())  //move to next node =[4,[6]] has index 0 & 1 ; [0] = 6
                    {
                        q.add(next);  //q has = [4, [6]], exit for loop. go to total_sum = 1
                    }
                }
            }
           
            total_sum += sum_cur; // 0 + 1 =1 , 1 + 5 = 6 (q has size 1, go to while loop) , 6 + 11 = 17 
        }
        
        return total_sum;
    }  
	
	public static void main (String[] args)
    {
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
			
		 System.out.println("\nSum of nested Lists: " + depthSumInverse(list3)); //17
	  
    }
}
