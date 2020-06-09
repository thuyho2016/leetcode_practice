import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * 503. Next Greater Element II
 * https://leetcode.com/problems/next-greater-element-ii/

 Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. 
 The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. 
 If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]

Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.   
    
 */
public class NextGreaterNumberII {
	/**
	Solution of NextGreaterNumberI: using stack

	For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence

	We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x.

	For example [9, 8, 7, 3, 2, 1, 6]
	The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3 whose next greater element should be 6


	The approach is same as Next Greater Element I
	The only difference here is that we use stack to keep the indexes of the decreasing subsequence

	 */

	public static int[] nextGreaterElements(int[] nums) {
		 // corner case
        if(nums == null) return null;
        
        int len = nums.length;
	 	int[] res = new int[nums.length];
    
        Stack<Integer> stack = new Stack<>(); //to store index
       
     // push index to stack
        for (int i = len - 1 ; i >= 0; i--) {
        	stack.push(i);   //stack = [4, 3, 2, 1, 0]
        }
        
        
        for (int i = len - 1; i >= 0; i--) {
            res[i] = -1; //[0, 0, 0, 0, -1], [0, 0, 0, -1, 5], [0, 0, -1, 5, 5],..[-1, 5, 5, 5, 5]
            
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) { 
              stack.pop();  //pop 4, stack = [4, 3, 2, 1, 0], ....[4, 3], [4]
            }
            if (!stack.isEmpty()) {
            	res[i] = nums[stack.peek()]; // top of stack 0, nums[0] = 5, so res =[0, 0, 0, 0, 5], [0, 0, 0, 5, 5], [0, 0, 5, 5, 5], [0, 5, 5, 5, 5]
            }
            stack.add(i);  //i = 4: [4, 3, 2, 1, 0, 4], i = 3: [4, 3, 2, 1, 0, 3], add i = 2: [4, 3, 2, 1, 0, 2], [4, 3, 2, 1, 0, 1], ...[0]
        } 
                      
        return res; //[0, 0, 0, 0, 5]
    }
	
	public static void main(String[] args) {
	/*	int[] nums =  {1,2,1};
		
		int[] res = nextGreaterElements(nums);
		System.out.println(Arrays.toString(res)); //[2,-1,2]
	*/	
		int[] nums2 = {5,4,3,2,1};
		int[] res2 = nextGreaterElements(nums2);
		System.out.println(Arrays.toString(res2)); //[-1, 5, 5, 5, 5]
	}

}
