
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.List;

/* 1. Two Sum
 Add two number, return a list of array which gives target = 0 or target = k:
 
 Given an array = [-1, 0, 1, 2, -1, -2], target =0
 Output:  [[-1, 1], [-2, 2]]


Given an array  = { 2, 7, 8, 6, 3 }, target = 9;
Output:  [[2, 7], [3, 6]]


Steps:
Using 2 pointers i and j 

       sort array = [-1, 0, 1, 2]
     *                i        j
     * sum = num1 + num 2
     * if sum == target, return true
     * if sum < target, then i++
     * if sum > target, then j--
*/
 
public class TwoSumEqualK_OrZero_2Pointers {
    
	public static List<List<Integer>> twoSumEqualTarget(int[] ary,  int target)
    { 
		Arrays.sort(ary);
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;
        int right = ary.length - 1;
        
        while (left < right)
        {   
        	int sum = ary[left] + ary[right];
            
        	if ( sum == target)
            {
                if (left == 0 || ary[left] != ary[left - 1]) // prevent duplicate data
                {
                   
                	List<Integer> r = new ArrayList<>();
                    r.add(ary[left]);
                    r.add(ary[right]);
                    result.add(r);
                }
                ++left;
                --right;
            }
            else if (sum < target)
            {
            	left++;  // shift left pointer to the right side
                
            }
            else // sum > target
            {
            	right--;   // shift right pointer to the left side
            }
        }
        
        return result;
    }
    
    public static void main (String args[]) {
        int target = 9;
	    int[] nums = { 2, 7, 8, 6, 3 };  //Output: [0,1],[3,4] 
	    	    
	    List<List<Integer>> result = twoSumEqualTarget(nums, target);
	    System.out.println("Two Sum: " + result);  //[[2, 7], [3, 6]]
	    
	    
	    int[] ary = {-1, 0, 1, 2, -2};
	    List<List<Integer>> result2 = twoSumEqualTarget(ary, 0);
	    System.out.println("Two Sum: " + result2); //[[2, 7], [3, 6]]
    }
}


