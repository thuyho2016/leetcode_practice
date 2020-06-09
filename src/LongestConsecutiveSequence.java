import java.util.HashSet;
import java.util.Set;

/* 128. Longest Consecutive Sequence ( Hard level)
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * 
 * Given [10, 4, 20, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. 
 * Return its length: 4.
 * 
 * Number is increased by 1
 * 
Time complexity : O(n^3) 
Space complexity : O(1)

 */

public class LongestConsecutiveSequence{
	
	/* Solution: HashSet and Intelligent Sequence Building
	 * 1. Add all numbers in HashSet.
	 * 2. Go each element in array:
	 *    if HashSet doesn't contains number - 1,
	 *    and Hashset contains consecutive number (number + 1), then increase count
	*/
	
	//Time complexity and Space complexity : O(n)
	public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        Set<Integer> set = new HashSet<>();
        
        for (int n : nums)
            set.add(n);   //[1, 2, 3, 4, 20, 10]
        
        int count = 1;
        int max = 0;
        
        for (int e : nums) {
            if (!set.contains(e - 1)) { //e.g  e = 20, e - 1 = 19 is not in HashSet, go inside loop
            	//if set contains consecutive number, then increase count
                while (set.contains(e + 1)) { // e = 2 , e + 1 = 3, 
                    count++;
                    e = e + 1;
                    System.out.println("Count " + count);
                }
                max = Math.max(max, count);
                count = 1; //reset back to 1
            }
        }
        return max;
    }
	
	
	public static void main(String[] args) {
	  // int[] nums = { 10, 4, 20, 1, 3, 2};
		int[] nums = {0,1,2,5,6,5,7};
	   int res = longestConsecutive(nums);
	   System.out.println("Longest Length: " + res); //4
	   
	   int[] nums2 = { 4, 6, 10 , 3, 2};
	   int res2 = longestConsecutive(nums2);
	   System.out.println("Longest Length2: " + res2);  //3
	   
	}

}
	
	