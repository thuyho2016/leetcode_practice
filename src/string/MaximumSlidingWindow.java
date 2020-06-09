package string;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * 239. Sliding Window Maximum  (hard )
 * https://leetcode.com/problems/sliding-window-maximum/
 
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 

Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7


Approach: Deque (double-ended queue)

How can I improve the time complexity? The first idea is to use a heap, since in a maximum heap heap[0] is always the largest element. 
Though to add an element in a heap of size k costs log(k), that means O(Nlog(k)) time complexity for the solution.


The algorithm is quite straigthforward :

1. Process the first k elements separately to initiate the deque.

2. Iterate over the array. At each step :

  a. Clean the deque :

        Keep only the indexes of elements from the current sliding window.

        Remove indexes of all elements smaller than the current one, since they will not be the maximum ones.

  b. Append the current element to the deque.

  c. Append deque[0] to the output.

Return the output array.


Time complexity : O(N), since each element is processed exactly twice - it's index added and then removed from the deque.

Space complexity : O(N−k+1) is used for an output array and O(k) for a deque. 

 */

public class MaximumSlidingWindow {
	
	/*
	 * Solution: Deque
	 * http://tutorials.jenkov.com/java-collections/deque.html
	 * Deque(pronounce deck) represents a Double Ended Queue, meaning a queue where you can add and remove elements from both ends of the queue
	 
	add() - add elements to the beginning end of a Deque 
	addLast() - adds an element to the end (tail) of a Deque
	addFirst() - add an element at the beginning instead of the end of a Deque 
	
	remove() - removes the first element of a Java Deque
	removeFirst() - removes the first element of a Java Deque
	removeLast() - removes the last element of a Deque

	 */

	// using deque to save candidate max value of current sliding window. For convenience, I take the deque as a stack 
	// At first, we initiate the sliding window by traversing the first k elements in nums, saving the index of candidate max value.
	// Note that if nums[i] > nums[i - 1] then nums[i - 1] can't be the max element at any sliding window. 
	  	
	 //Each time the sliding window moves right by one position. Return the max sliding window.
	
	 // Best solution: deque
	 // Keep track index of max number and Store index  to deque.
	 // 3. when window is over, put peekFrist of dq to result	
	
	 //return mwx number of every shift
	  public static int[] maxSlidingWindow(int[] nums, int k) {
		  if(nums == null || nums.length == 0 || k > nums.length) return new int[0];
		  
		  int output[] = new int[nums.length - k + 1]; //k is window size 
		 
		  int count = 0; // results count
		  
	        // As deque is a double ended queue, the maximum element is stored at the front of queue (leftmost box)
		  Deque<Integer> dq = new LinkedList<>();
	        
	       
          for(int i = 0; i<nums.length; i++){
	          
	        // if dq is not empty and the first element index from deque is out of window size , remove 
            if(!dq.isEmpty() && dq.peekFirst() == i - k ) { 
                dq.removeFirst();  // removes the first element of this deque.
            }
            
            // as long as nums[i] has value greater than last element of deque, keep removing last element from deque
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i] ){ //  0 <= 3, then remove 0 from dq
                dq.removeLast();  //removes the last element of this deque
            }
            
            // add the index in deque
            dq.addLast(i);   //[0], [1], [ 1, 2] , [1, 2, 3] , [4] , [4,5], [6] , [7]
            
            // once an window is over, take its front value(which is the max) and put it in results
            if(i >= k-1) {  //2 => 2
           
            	output[count] = nums[dq.peekFirst()];  //or ouput[i -k +1] = output[0] = nums[1] = 3
            	count++;
            }
	          
	      }
	      return output;
	        
	  }
	
	  
	/* Solution 2:  Dynamic programming
	 Algorithm:  
	1. Iterate along the array in the direction left->right and build an array left.

	2. Iterate along the array in the direction right->left and build an array right.

	3. Build an output array as max(right[i], left[i + k - 1]) for i in range (0, n - k + 1).

    Time complexity : O(N), since all we do is 3 passes along the array of length N.
    Space complexity : O(N) to keep left and right arrays of length N, and output array of length N - k + 1
	*/
	public static int[] maxSlidingWindow_DP(int[] nums, int k) {
	    int n = nums.length;
	    if (n * k == 0) return new int[0];
	    if (k == 1) return nums;
	    
	    int[] left = new int[n];
	    left[0] = nums[0];
	    int[] right = new int[n];
	    
	    right[n-1] = nums[n-1];
	    
	    for (int i =1; i < n; i++) {
	      
	      //from left to right
	      if ( i % k == 0) left[i] = nums[i];
	      else left[i] = Math.max(left[i-1], nums[i]);
	      
	      //from right to left
	      int j = n - i - 1;
	      if (( j + 1) % k == 0) right[j] = nums[j];
	      else right[j] = Math.max(right[j + 1], nums[j]);
	           
	    }
	    
	    int[] output = new int[n - k + 1];
	    for (int i = 0; i < n - k + 1; i++)
	      output[i] = Math.max(left[i + k -1], right[i]);
	    
	    return output;
	  }
	  
	  public static void main(String[] args) {
	    int[] nums = {1,3,-1,-3,5,3,6,7};
	    
	    int[] out = maxSlidingWindow(nums,3);
	    System.out.println(Arrays.toString(out));	
	    
	  //  int[] out2 = maxSlidingWindow2(nums,3);
	  //  System.out.println(Arrays.toString(out2));	    
	    
	    
	  }

}