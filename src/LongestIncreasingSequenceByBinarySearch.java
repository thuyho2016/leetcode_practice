import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 300. Longest Increasing Subsequence (medium level)
 * https://leetcode.com/problems/longest-increasing-subsequence/
 Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?

*/

public class LongestIncreasingSequenceByBinarySearch{
	
	// Best Solution : dynamic programm and binary search

	/** Steps:
	1. Create a tmp ArrayList to store elements in the increasing subsequences 
	   - Add first element to tmp 
	   
	   - Go to each element in nums array. The idea is keep checking if nums[i] is bigger than a number from tmp ArrayList or not?
	
	If nums[i] > tmp.get(len) 
	  -  add the current element nums[i] into temp ArrayList (means I can retrieve a longer subsequence)
	  - so I need to increase the pointer 'len' by one and put the current element into the new index 
	
	Else: 
	  - use binary search to find out the index of the largest element
	  - and update the tmp with value nums[i].
	
	10, 9, 2, 5, 3, 7, 101, 18
	
	Steps
tmp[] =	[10]    --> add 10
		[9 ]    --> replace 10 with 9
		[2 ]    --> replace 9 with 2
		[2,5]   --> add 5
		[2,3]   --> replace 5 with 3 as 5 > 3
		[2,3,7]      --> add 7
		[2,3,7,101]  --> add 101
		[2,3,7,18]   -->replace 101 with 18

	set(index, element) method is to replaces the element at the specified index
	*/
	
	//Solution 1,  tmp is a ArrayList and binarySereach doesn't need to pass len because List has actual size/length
	public static int longestIncreaseSubsequence( int[] nums) {
	   if(nums.length == 0) return 0;       
	
       List<Integer> tmp = new ArrayList<>(); //to store the largest elements in the increasing subsequences			
       tmp.add(nums[0]) ;  // Add first element to tmp 
      
       int len = 0;        // pointer len to track length of the subsequence 
      
       
       for(int i=0 ; i<nums.length ; i++){
    	   
           if(nums[i] > tmp.get(len)) {  // compare with last element of arr
        	   len++;
        	   tmp.add(nums[i]);
        	   
           } else {      // find index of larger number  nums[i] < tmp.get(len):  10 <= 10, 9 <= 10
        	   
        	   int index = binarySearch(tmp, nums[i]); //find index of largest number, index = 0, 
        	   
        	   //update the tmp[index] with current nums[i]
        	   tmp.set(index, nums[i]);  //set method is to replaces the element at the specified position - index
        	   							// [9] replace with [10], [9] replace with [2], [2,5] 
        	   							// replace 5 with 3 ->[ 2,3], [2,3,7,101] replace 101 with 18 -> [2,3,7,18]				
           }            
       }
       
       System.out.println("array of increasing seq: " + tmp.toString());
       return tmp.size();  // return len + 1; also works fine
	       
	}
	
	// use binary search to find out the index of the largest element
	public static int binarySearch( List<Integer> nums, int target) { //nums =[2,5] target = 3
		int left = 0;
		int right = nums.size() - 1; // index of last element
		
		while ( left <= right) {       // go to each element
			int mid = left + (right - left) / 2;
			
			if (nums.get(mid) == target) 
				return mid;
			
			else if (nums.get(mid) < target)  // left side 2 < 3
				left = mid + 1 ;
			else  // nums.get(mid >= target   // 5 > 3
				right = mid - 1;
		}
		return left;
	}
	
	//Solution 2: use tmp array and binarySearch needs to pass actual length
	public static int longestIncreaseSubsequence2( int[] nums) {
	   if(nums.length == 0) return 0;
       
	   int[] tmp = new int[nums.length]; //to store the largest elements in the increasing subsequences     
       tmp[0] = nums[0];               // [10, 0, 0, 0, 0]
       
       int len = 0;                    // to track length of the subsequence
       
       for(int i = 0 ; i< nums.length ; i++){
    	   
           if(nums[i] > tmp[len]){    //add larger number to temp array. Else replace
        	   //len++; increase length
               tmp[++len] = nums[i]; 
           } else{      // 10 <= 10 ?, 9 <= 10 ?, 2 <= 9 ?
        	   
        	   int index = search(tmp, len, nums[i]); //has to pass actual len, index = 0
        	   //update the arr[index] with value nums[i]
        	   tmp[index] = nums[i]; 
           }            
       }

       System.out.println("array of increasing seq: " + Arrays.toString(tmp));
       return len + 1;  //(Do not return nums.length because it was initialized with nums.length)
	}
	
	// use binary search - pass actual len
	private static int search(int[] arr, int len, int target ) { //arr = [2,5,0,0,0], len = 1 , target = 1
		 int left = 0;	 
		 int right = len; // actual len of arr
         
         while(left <= right){
             int mid = left +(right - left)/2;  // len = 1: mid = 0,  len = 2: 1 , 0
             
             if (arr[mid] == target) {
            	 return mid;  // index
             }
             else if(arr[mid] < target){  //arr[0] = 1 < 3
                 left = mid + 1;     // search on the right side, left = 1
             } else {               // 2 > 1 ?, 5 > 3
                 right = mid - 1;    // search on the left side, right = -1
             }
         }
         return left;
	}
	
	
	
	public static void main(String[] args) {
			   
	   int[] nums = {10,9,2,5,3,7,101,18};
	   int res = longestIncreaseSubsequence(nums);
	   System.out.println("Longest Length: " + res); //length = 4  , [2,3,7,101]
	   

	   int[] nums2 = {10,9,2,5,3,7,101,18};
	   int res2 = longestIncreaseSubsequence2(nums2);
	   System.out.println("Longest Length: " + res2); //length = 4  , [2,3,7,101]
	   
	}
}
	
	