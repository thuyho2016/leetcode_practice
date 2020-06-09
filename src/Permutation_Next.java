import java.util.ArrayList;
import java.util.List;

/*
 * 31. Next Permutation
 * https://leetcode.com/problems/next-permutation/
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

*/


public class Permutation_Next {
	
	public static void nextPermutation(int[] nums) {
        if(nums.length <=1)
            return ;
        
        for(int i = nums.length-1; i>= 1; i--){
            if(nums[i]<= nums[i-1])
                continue;
            
            // find correct position for root
            for(int j = nums.length - 1; j>=i; j--){
                if(nums[j] > nums[i-1]){
                    swap(nums,j,i-1);
                    reverse(nums,i,nums.length-1);
                    return;    
                }
            }
            
        }
        reverse(nums,0,nums.length-1);
        return ;
        
    }
    
    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static void reverse(int[] nums, int start, int end){
        while(start<=end){
            swap(nums,start,end);
            start++;
            end--;
        }
    }
	  
	 	 
	  public static void main(String[] args) { 
 
	  	int[] nums = {1, 2, 3};
	  	nextPermutation(nums);
		System.out.println(); 
		
		
	  } 
}




