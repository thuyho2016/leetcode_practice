import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * 556. Next Greater Element III
 * https://leetcode.com/problems/next-greater-element-iii/

 Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21
 

Example 2:

Input: 21
Output: -1    
 */
public class NextGreaterNumberIII {
	/**
	Solution: using stack

	For example [5, 4, 3, 2, 1, 6] then the greater number 6 is the next greater element for all previous numbers in the sequence

	We use a stack to keep a decreasing sub-sequence, whenever we see a number x greater than stack.peek() we pop all elements less than x and for all the popped ones, their next greater element is x.

	For example [9, 8, 7, 3, 2, 1, 6]
	
	The stack will first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is greater than 1 so we pop 1 2 3 whose next greater element should be 6

	 */

	public static int nextGreaterElement(int n)  {
		 char[] arr=(n+"").toCharArray();
	        if(arr.length<=1)  return -1;
	        int i=arr.length-2;
	        //Find the rightmost element, which is not in a non-increasing sequence ending at arr[arr.length-1];
	        //For example, for 679833,  7 is the rightmost element, which is not in the non-increasing sequence 9,8,3,3.
	        while(i>=0&&arr[i]>=arr[i+1]){
	            i--;
	        }
	        if(i<0)   return -1;
	        int j = arr.length - 1;
	        //Find the smallest element larger than arr[i] in the non-increasing sequence.
	        //Here, 8 is the smallest element larger than 7 in the sequence 9,8,3,3.
	        while (arr[j] <= arr[i]) {
	            j--;
	        }
	        //Swap arr[i] with the element arr[j]. 
	        //Here, swap 7 with 8.
	        char tmp=arr[i];
	        arr[i]=arr[j];
	        arr[j]=tmp;
	        //Sort this newly modified non-increasing sequence, which is equivalent to reversing the sequence.
	        //Now, 9,7,3,3, becomes 3,3,7,9;
	        Arrays.sort(arr,i+1,arr.length);
	        
	        //Use long to avoid overflow;
	        //Here, res=683379
	        long res=Long.parseLong(new String(arr));
	        return res>Integer.MAX_VALUE?-1:(int)res;
    }
	
	public static void main(String[] args) {
		int num =  12;
		
		System.out.println(nextGreaterElement(num));
		
		
	}

}
