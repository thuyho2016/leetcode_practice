import java.util.PriorityQueue;

/* 1046. Last Stone Weight
 * https://leetcode.com/problems/last-stone-weight/
 
We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)

 

Example 1:

Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 

Note:

1 <= stones.length <= 30
1 <= stones[i] <= 1000

 */

public class LastStoneWeight {
    // Time & Space O(n)
    public static int lastStoneWeight(int[] stones) {
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) ->(b - a)); // MAX Heap

    	for (int stone: stones) {
    		maxHeap.offer(stone);   //can use add
    	}
    	//[8, 7, 4, 1, 2, 1]
    	while(maxHeap.size() > 1) {
    		int stoneOne = maxHeap.poll(); //remove - 1st largest weighted stone
    		int stoneTwo = maxHeap.poll(); // 2nd largest weighted stone
    		
    		if(stoneOne != stoneTwo) { // if two stones are not the same
    			maxHeap.add(stoneOne - stoneTwo);
    		}
    	}
    	return maxHeap.isEmpty() ? 0 : maxHeap.peek();
      
	}
    
    public static void main(String[] args) {
    	int[] stones = {2,7,4,1,8,1};
    	System.out.println(lastStoneWeight(stones));
    	
    }
  
}
	
	