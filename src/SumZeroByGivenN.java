
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Given N = 3, 
Output: return a list of array which gives sum = 0,
       [ [-1, 0, 1], [-1, -1, 2] ],....

O(n^3)

 */

public class SumZeroByGivenN  {
	public static void main (String[] args)
    {
	/*	int[] nums = {-4,-3,-2,-1,0,1,2,3,4};
		List<List<Integer>> result = kSumZero(nums, 0, 4);
		System.out.println(result);
	*/	
		int[] nums2 = {-3,-2,-1,0,1,2,3};
		List<List<Integer>> result2 = kSumZero(nums2, 0, 3);
		System.out.println(result2);
		
	/*	int[] nums3 = {-2,-1,0,1,2};
		List<List<Integer>> result3 = kSumZero(nums3, 0, 2);  //0 is target Two sum = [[-2, 2], [-1, 1]]
		System.out.println(result3);
	*/
    }
	
	public static List<List<Integer>> kSumZero(int[] nums, int target, int N) 
    {
        Arrays.sort(nums);
        return kSum(nums, 0, target, N); //Three sum, target = 0
        
    }

    // find N numbers such that their sum == target.
    // assuming:
    // 1) N >= 2
    // 2) input array is sorted
    private static List<List<Integer>> kSum(int[] arr, int start, int target, int N)
    {
        if (N == 2) return twoSum(arr, start, target);
        
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = start; i <= arr.length - N; ++i) // k is three sum or four sum
        {
            if (i == start || arr[i] != arr[i - 1]) //duplicate
            {
                for (List<Integer> cur : kSum(arr, i + 1, target - arr[i], N - 1))
                {
                    cur.add(arr[i]);
                    result.add(cur);
                }
            }
        }
        
        return result;
    }
    
    
	 //target = 0, or any number
	 private static  List<List<Integer>> twoSum(int[] ary, int start, int target)
    {
        List<List<Integer>> result = new ArrayList<>();
        int left = start;
        int right = ary.length - 1;
        
        while (left < right)
        {   
        	int sum = ary[left] + ary[right];
            if ( sum == target)
            {
                if (left == start || ary[left] != ary[left - 1]) // prevent duplicate data
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
            	++left;
            }
            else // sum > target
            {
            	--right;
            }
        }
        
        return result;
    }
	    

}