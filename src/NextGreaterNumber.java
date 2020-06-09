
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * 496. Next Greater Element I
 * https://leetcode.com/problems/next-greater-element-i/

 You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]

Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.

Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].

Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.


Solution: using stack

For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence

We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x.

For example [9, 8, 7, 3, 2, 1, 6]
The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3 whose next greater element should be 6


  */
public class NextGreaterNumber {
	
	//Time Complexity:  O(n)  using stack
	public static int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] res = new int[findNums.length];
        
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        
        for (int num : nums) {  //{1,3,4,2
            while (!stack.isEmpty() && stack.peek() < num) { // when stack has 1, compare element in nums with 1. If it is less than, pop and store it to map.
                map.put(stack.pop(), num);
            }
            stack.push(num);  //[1], [3] , [4], [4, 2]
        } 
        // Map: {1 => 3}, {1=3, 3=4}
        
        for (int i = 0; i < findNums.length; i++)
        	res[i] = map.getOrDefault(findNums[i], -1); //if no key found in hashmap, set value = -1, so 
                     
        return res;
    }
	
	
	public static void main(String[] args) {
		int[] findNums =  {4,1,2};
		int[] nums = {1,3,4,2};
		
		int[]res = nextGreaterElement(findNums, nums);
		System.out.println(Arrays.toString(res));
		
		int[] findNums2 =  {2,4};
		int[] nums2 = {1,2,3,4};
		
		int[]res2 = nextGreaterElement(findNums2, nums2);
		System.out.println(Arrays.toString(res2));
	}

}
