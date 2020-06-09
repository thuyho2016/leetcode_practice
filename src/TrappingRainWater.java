import java.util.Stack;

/*
 * 42. Trapping Rain Water (hard level)
 * https://leetcode.com/problems/trapping-rain-water/
 
 Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 compute how much water it is able to trap after raining.

Graph: ...

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
In this case, 6 units of rain water (blue section) are being trapped.

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

Solution: Using 2 pointers

Algorithm

Initialize left pointer to 0 and right pointer to size-1
While left<right, do:
	If height[left] is smaller than height[right]
		If height[left] ≥ left_max, update left_max
		Else add left_max − height[left] to ans
		Add 1 to left.
	Else
		If height[right] ≥ right_max, update right_max
		Else add right_max − height[right] to ans
		Subtract 1 from right.


Time complexity: O(n). Single iteration of O(n)O(n).
Space complexity: O(1) extra space. Only constant space required for left, right, left_max and right_max.

 */

public class TrappingRainWater {
	
	//Using 2 pointers - comparing leftMax and rightMax to decide which pointer to move:
	public static int trap(int[] height) {
	    int left = 0, right = height.length-1;
	    int left_max = 0, right_max = 0;
	    
	    int ans = 0;
	    
	    while (left < right) {
	        
	    	left_max = Math.max( height[left], left_max ); 
            right_max = Math.max( height[right], right_max );           
          
            if ( left_max < right_max) {   // a taller bar exists on left pointer's right side
                ans += left_max - height[left];
            	left++;
            }  else {   // a taller bar exists on right pointer's left side
                ans += right_max - height[right];
                right--;
            }
	    }
	    return ans;
	}
		
	
	public static void main(String[] args) {
		int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};
		
		System.out.println(trap(input));
	}
}
