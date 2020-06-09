
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* 287. Find the Duplicate Number
 https://leetcode.com/problems/find-the-duplicate-number/
 
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
 Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,6,2]
Output: 2

Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.


Q2: 26. remove duplicates from sorted array and Return the new length -
https://leetcode.com/problems/remove-duplicates-from-sorted-array/

Given nums = [1,1,2],
return length = 2, with the first two elements of nums being 1 and 2.

Q3: 80. Remove Duplicates from Sorted Array II 
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

remove the duplicates in-place such that duplicates appeared at most TWICE and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Given nums = [1,1,1,2,2,3], return length = 5 because output [1,1,2,2,3]

Given nums =  = [0,0,1,1,1,1,2,3,3]

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 */


public class FindDuplicateNumbers_RemoveDuplicates {
	
	// Floyd's Tortoise and Hare (Cycle Detection - Time O(n), O(1) extra space. -- Assume that there is only one duplicate number
	public static int findDuplicate_SpaceO1(int[] nums) {
	    int fast, slow;  // Find the intersection point of the two runners.
		
		fast = slow = nums[0];
		
		do {
			fast = nums[nums[fast]];
			slow = nums[slow];
		} while (fast != slow);
		
		slow = nums[0];
		while (fast != slow) {
			fast = nums[fast];
			slow = nums[slow];
		}
		
		return fast;
	}
	
	
	//Q1: use Hash Set - return duplicate nums in the list
	//Time and Space complexity : O(n)
	public static List<Integer> findDuplicate(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();  // to hold duplicates
        
		Set<Integer> set = new HashSet<Integer>();
        
        for (int num : nums) {
            if (set.contains(num)) {
                result.add(num);   //add to result and return
            }
            set.add(num);
        }
        
        // example to access element from HashSet
   /*     Iterator<Integer> itr = set.iterator();
        while(itr.hasNext() ){ //Returns true if the iteration has more elements.
            System.out.print(itr.next() + " ");
        } */
       
        return result;
	}
	 
	
	//Q1: Use HashMap to remove duplicates , return int[]
	public static int[] removeDuplicateNumber(int[] arr) {
		   
	   HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
	   for (int i = 0; i < arr.length; i++) {
	   
		   //if hashMap already contain key, increase count 
		   if (map.containsKey(arr[i])) { 
			   
			   int count = map.get(arr[i]);  ////call get(key) to get value
			   map.put(arr[i], count + 1); //increase value = previous count + 1
			   
		   } else {
			   map.put(arr[i], 1);
		   }
	    }
	   	
	   Set<Integer> keys = map.keySet();
	   
	   int[] result = new int[map.size()];
	   int i = 0;
	   for (Integer k: keys) {
			result[i] = k; 
			i++;
	   }
	   
       return result;
	}
	
	 
	 // Q2: remove duplicates from sorted array and Return the new length
	 // - Time complextiy : O(n), Space O(1)
	 // 2 pointers i j
	 public static int lenAfterRemoveDuplicates(int[] nums) {
		 if (nums.length ==0) return 0;
		 
		 int i = 0;
		 
		  // Start from the second element of the array
		 for (int j = 1; j < nums.length; j++) {
			 if (nums[i] != nums[j]) {
				 i++;        // move forward index i
				 nums[i] = nums[j]; //update value of new index i
			 }
		 }
		 return i + 1;
	 }
	 
	 
	 // Q3: Remove Duplicates from Sorted Array II
	 // Time Complexity: O(N) , Space O(1)
	 
	 public static int lenAfterRemoveDuplicateTwice(int[] nums) {
	     // Initialize the counter and the second pointer.
	     int j = 1, count = 1;
	     
	     // Start from the second element of the array and process
	     // elements one by one.
	     for (int i = 1; i < nums.length; i++) {
	         
	         // If the current element is a duplicate, increment the count.
	         if (nums[i] == nums[i - 1]) {	             
	             count++;
	             
	         } else {
	             
	             // Reset the count since we encountered a different element than the previous one.
	             count = 1;
	         }
	         
	         // For a count <= 2, we copy the element over thus overwriting the element at index "j" in the array
	         if (count <= 2) {
	             nums[j++] = nums[i];
	         }
	     }
	     return j;
	 }

	public static void main (String[] agrs) {				
		
        int[] arr1 = {1, 3, 2, 1, 2, 1, 4};
        
        System.out.println("Return ONE duplicate number: " + findDuplicate_SpaceO1(arr1));
		
        System.out.println("Duplicate numbers: " + findDuplicate(arr1)); //[1, 2, 1]
        
		int[] filtered = removeDuplicateNumber(arr1);
		System.out.println("Removed duplicate: " + Arrays.toString(filtered)); //[1, 2, 3, 4]
		
		//Q2
		int[] arr3 = {0,0,1,1,1,2,2,3,3,4};
		System.out.println("Length array after filter duplicates: " + lenAfterRemoveDuplicates(arr3));//5
		
		//Q3
		int[] arr4 = {0,0,1,1,1,1,2,3,3};
		System.out.println("Length array after filter number appeared twice : " + lenAfterRemoveDuplicateTwice(arr4));//7
	}
}
