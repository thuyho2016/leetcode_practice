import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 1167. Minimum Cost to Connect Sticks
https://leetcode.com/problems/minimum-cost-to-connect-sticks/

You have some sticks with positive integer lengths.

You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.

 

Example 1:

Input: sticks = [2,4,3]
Output: 14

Example 2:

Input: sticks = [1,8,3,5]
Output: 30

Use MinHeap
 */
public class MinCosttoConnectSticks {
	
	public static int connectSticks(int[] sticks) {
		int cost = 0;
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
				
		for (int stick: sticks) {
			minHeap.add(stick);
		}
		while (minHeap.size() > 1 ) { //take from root
			int sum = minHeap.remove() + minHeap.remove(); // first small stick + second small stick
			cost += sum; // after combination 2 stick, put back the heap
			minHeap.add(sum);
		}
		return cost;
		
	}
	
	/* Min Cost to Connect Ropes ( Amazon OA) -  Similar with the above problem

		Given n ropes of different lengths, we need to connect these ropes into one rope. We can connect only 2 ropes at a time. The cost required to connect 2 ropes is equal to sum of their lengths. The length of this connected rope is also equal to the sum of their lengths. This process is repeated until n ropes are connected into a single rope. Find the min possible cost required to connect all ropes.

		Example 1:

		Input: ropes = [8, 4, 6, 12]
		Output: 58
		Explanation: The optimal way to connect ropes is as follows
		1. Connect the ropes of length 4 and 6 (cost is 10). Ropes after connecting: [8, 10, 12]
		2. Connect the ropes of length 8 and 10 (cost is 18). Ropes after connecting: [18, 12]
		3. Connect the ropes of length 18 and 12 (cost is 30).
		Total cost to connect the ropes is 10 + 18 + 30 = 58
		
		Example 2:

		Input: ropes = [20, 4, 8, 2]
		Output: 54
		
		Example 3:

		Input: ropes = [1, 2, 5, 10, 35, 89]
		Output: 224
		Example 4:

		Input: ropes = [2, 2, 3, 3]
		Output: 20
		Solution
		
		Time complexity: O(nlogn).
		Space complexity: O(n).
*/
	public static int minCost(List<Integer> ropes) {
	    Queue<Integer> pq = new PriorityQueue<>(ropes);
	    int totalCost = 0;
	    
	    while (pq.size() > 1) {
	        int cost = pq.poll() + pq.poll();
	        pq.add(cost);
	        totalCost += cost;
	    }
	    return totalCost;
	}
	
	public static void main (String[] args)
    {
		int[] sticks = {2, 4, 3};
		System.out.println(connectSticks(sticks));
		
		List<Integer> ropes = Arrays.asList(2, 4, 3);
		System.out.println(minCost(ropes));
		
    }
}
