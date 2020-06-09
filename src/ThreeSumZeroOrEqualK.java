
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
 15. 3Sum
https://leetcode.com/problems/3sum/
 
Find all unique triplets in the array which gives the sum of zero.

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
		
Given an array S = [-1, 0, 1, 2, -1, -4],

Output: return a list of array which gives sum = 0:
       [ [-1, 0, 1], [-1, -1, 2] ]

Steps:

1. Sort the array

If the number is the same as the number before, we have used it as target already, continue. [1]
 start the left pointer from i+1, right pointer is length - 1. [2]

calculate the total:
If the total is less than zero, we need it to be larger, so we move the left pointer. [3]
If the total is greater than zero, we need it to be smaller, so we move the right pointer. [4]
If the total is zero, bingo! [5]

I need to move the left and right pointers to the next different numbers, so we do not get repeating result. [6]


For time complexity
Sorting takes O(NlogN)
Now, we need to think as if the 'nums' is really really big
We iterate through the 'nums' once, and each time we iterate the whole array again by a while loop
So it is O(NlogN+N^2)~=O(N^2)

For space complexity
We didn't use extra space except the 'res'
Since we may store the whole 'nums' in it. So it is O(N), N is the length of 'nums'
 */

public class ThreeSumZeroOrEqualK  {
	
	/* a + b + c = 0
	 * a + b = -c <--- target
	 * check for duplicate numbers  nums[i] == nums[i -1]
	
	  if i > 0 and nums[i] == nums[i-1]  because when i = 0, it doesn't need to check if it's a duplicate element 
	  since it doesn't even have a previous element to compare 
	 
	 */
	
	//ThreeSum Zero
	public static List<List<Integer>> threeSum(int[] nums) {
	        
			List<List<Integer>> result = new ArrayList<>();
			
			if(nums == null || nums.length <= 2) { 
				return result;
			}
	        
			Arrays.sort(nums); //{-4, -1, -1, 0, 1, 2}
	        
	        for (int i = 0; i < nums.length - 2; i++) { //last second element in array, we need at least 3 numbers to continue. //[8]
	            
	        	//if (i > 0 && nums[i] == nums[i-1]) continue;
	        	if (i == 0 || (i > 0 && nums[i] != nums[i-1])) { // prevent duplicate data
	                
	        		
	        		int left = i + 1;
	                int right = nums.length - 1;
	                
	                while (left < right) {
	                    int sum = nums[i] + nums[left] + nums[right];
	                    
	                    if (sum == 0) {
	                    	
	                    	List<Integer> list = new ArrayList<>();
	    					list.add(nums[i]);
	    					list.add(nums[left]);
	    					list.add(nums[right]);
	                        result.add(list);
	                       
	                        // prevent duplicate data. Input [-2,0,0,2,2], output [[-2,0,2],[-2,0,2]]. Expected [[-2,0,2]]
	                        while (left < right && nums[left] == nums[left + 1]) left++;  
	                        while (left < right && nums[right] == nums[right - 1]) right--;
	                        
	                        left++;
	                        right--;
	                        
	                    } else if (sum < 0) {   //[3]
	                        left++;
	                   
	                    } else {    //[4]
	                        right--;
	                    }
	                }
	            }
	        }
	        return result;
	    }
	
	//ThreeSum match K
	public static List<List<Integer>> threeSumEqualK(int[] nums, int k) {
        
		List<List<Integer>> result = new ArrayList<>();
		
		if(nums == null || nums.length <= 2) { 
			return result;
		}
        
		Arrays.sort(nums); //{-4, -1, -1, 0, 1, 2}
        
        for (int i = 0; i < nums.length - 2; i++) { //last second element in array, we need at least 3 numbers to continue. //[8]
            
        	//if (i > 0 && nums[i] == nums[i-1]) continue;
        	if (i == 0 || (i > 0 && nums[i] != nums[i-1])) { // prevent duplicate data
                
        		//int target = -num[i];
        		int left = i + 1;
                int right = nums.length - 1;
                
                while (left < right) {
                	
                    int sum = nums[i] + nums[left] + nums[right];
                    
                    if (sum == k) {
                    	List<Integer> list = new ArrayList<>();
    					list.add(nums[i]);
    					list.add(nums[left]);
    					list.add(nums[right]);
                        result.add(list);
                       
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        
                        left++;
                        right--;
                        
                    } else if (sum < k) {   //[3]
                        left++;
                   
                    } else {    //sum > k , [4]
                        right--;
                    }
                }
            }
        }
        return result;
    }
	
	public static void main (String[] args)
    {
		int[] nums1 = new int[] {-1, 0, 1, 2, -1, -4};
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result = threeSum(nums1);
		System.out.println("Sum equal zero " + result);
		
		
		result = threeSumEqualK(nums1, 3);
		System.out.println("Sum equal target: " + result);
	
    }
	
}