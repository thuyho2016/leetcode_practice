import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Determine the minimum cost to make it either non-decreasing or non-increasing

arr = [0,1,2,5,6,5,7]
arr[5] = 5 

the array is non-decreasing: arr2 = [0,1,2,5,6,6,7] 
The cost is | arr[5] - arr2[5] | = | 5 - 6 | = 1
*/

public class MinCostToMakeNonIncreasingOrNonDecreasing{
	

	/** Steps of LongestIncreasingSubsequence:
	1. Create a tmp ArrayList to store elements in the increasing subsequences 
	   - Add first element to tmp 
	   
	   - Go to each element in nums array. The idea is keep checking if nums[i] is bigger than a number from tmp ArrayList or not?
	
	If nums[i] > tmp.get(len) 
	  - add the current element nums[i] into temp ArrayList (means I can retrieve a longer subsequence)
	  - so I need to increase the pointer 'len' by one and put the current element into the new index 
	
	Else: 
	  - use binary search to find out the index of the largest element
	  - and update the tmp with value nums[i].
	
	10, 9, 2, 5, 3, 7, 101, 18
	
	Steps
	10    --> add 10
	9     --> replace 10 with 9
	2     --> replace 9 with 2
	2,5   --> add 5
	2,3   -->replace 5 with 3 as 5 > 3
	2,3,7      --> add 7
	2,3,7,101  --> add 101
	2,3,7,18   -->replace 101 with 18
	
	*/	

	public static int minCost( List<Integer> arr) {

	   if(arr.size() == 0) return 0;
       
	
       List<Integer> tmp = new ArrayList<>(); //to store the largest elements in the increasing subsequences			
       tmp.add(arr.get(0)) ; // initialize 
       int len = 0;        // to track length of the subsequence 
       int cost = 0;
       
       for(int i = 0 ; i < arr.size(); i++){
    	   
           if(arr.get(i) > tmp.get(len)) {  // compare with last element of tmp
        	   len++;
        	   tmp.add(arr.get(i));
        	   
           } else{      // find index of largest number  arr[i] < tmp.get(len)
        	   
        	   int index = binarySearch(tmp, arr.get(i)); //find index of largest number, index = 0, 
        	   System.out.println("Index " + index);
        	   
        	   if (index < i) {
        		   tmp.add(tmp.get(len));
        		   cost = Math.abs(tmp.get(len) - arr.get(i));
        	   } else {
        	       //update the tmp[index] with value arr[i]
        		   tmp.set(index, tmp.get(len));  //set method is to replaces the element at the specified position - index
        	   }										
           }            
       }
       
       System.out.println("array of increasing seq: " + tmp.toString());
       return cost;  
	       
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
	
	public static void main(String[] args) {
	
	   List<Integer> nums = Arrays.asList(0,1,2,5,6,5,7);
	   int res = minCost(nums);
	   System.out.println("Longest Length: " + res);  //{2,5,8}
	
	   
	}

}
	
	