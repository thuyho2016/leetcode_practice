import java.util.HashMap;
import java.util.Map;

/*
 * 697. Degree of an Array ( Easy level)
 * https://leetcode.com/problems/degree-of-an-array/
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2

Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.

Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6

Solution: One pass on Array,

For each different number a in Array,
we need to count its frequency and it first occurrence index.

If a has the maximum frequency, update the degree = count[a] and len = i - first[A[i]] + 1.

If a is one of the numbers that has the maximum frequency, update the len = min(len, i - first[A[i]] + 1)

		                      
Time O(N), hardly find the any reason to scan twice.
Space O(M), where M is the size of different numbers.

 map.getOrDefault(nums[i], 0) - Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key
 */


public class ContinuousSubArray_SmallestLengthHasMaxFrequency {

	public static int findShortestSubArray(int[] nums) {
		 if (nums.length == 0) return 0;
		 
		 Map<Integer, Integer> count = new HashMap<>();  //to record the frequency/times of appearance of nums[i], (K,V) = ( nums[i], frequency of nums[i])
		 Map<Integer, Integer> firstOccurence = new HashMap<>(); // (k, V) = (nums[i], index) to store index i as value
		 
		 int minLen = 0, maxFreq = 0; //same degree

		 for (int i = 0; i < nums.length; i++) {
			
			 //This is for the first time we see this value, then add to hashmap
			 if (!firstOccurence .containsKey(nums[i])) { 
				 firstOccurence.put(nums[i], i); // value is index i  - {1=0, 2=1,...}
			 }
			 //seen again, update the frequency by increasing +1
			 count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);  //{1=1, 2=1}		                       
			 
			 // two cases to update the minLen
			 
			 // Case 1: if times of nums[i] bigger than global maxFreq, then update the degree (maxFeq) and minLen
			 if (count.get(nums[i]) > maxFreq) {
				 maxFreq = count.get(nums[i]);
				 minLen = i - firstOccurence.get(nums[i]) + 1;
				 
			 }  // Case 2: if frequency of current number nums[i] is the same with global maxFreq, then length between its first seen index and current index is shorter
			 else if ( count.get(nums[i]) == maxFreq) {
				 minLen = Math.min(minLen, i - firstOccurence.get(nums[i])  + 1 );
			 }
		 
		 }
		 return minLen;
    }
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 2, 3, 1};
		System.out.println("Shortest Length: " +  findShortestSubArray(nums)); //4

	}

}
