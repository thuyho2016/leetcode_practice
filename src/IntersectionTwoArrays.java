
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* 
 * 349. Intersection of Two Arrays (easy level)
 * https://leetcode.com/problems/intersection-of-two-arrays/
 * 
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]

Note:

Each element in the result must be unique.
The result can be in any order.

Best solution: Using Hashmap

O(n+m) time complexity



* 350. Intersection of Two Arrays II
* https://leetcode.com/problems/intersection-of-two-arrays-ii/

Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

*/
		
public class IntersectionTwoArrays  {	
	
	/* 349. Intersection of Two Arrays (easy level)
	        Input: nums1 = [1,2,2,1], nums2 = [2,2]
			Output: [2]
	
	  solution: Using Hashmap  
	 - add nums1 elements to hashmap with visited as value = false
	 - go through elements of nums2 and check if map contains element
         if yes ( mean intersection), then check if it is visited 1st time.
             set true if it is first time
         else skip that element (because it is already visited)
	*/
	public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Boolean> map = new HashMap<Integer,  Boolean>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        for(int i = 0; i < nums1.length; i++)
        {  //set visited as value = false
            map.put(nums1[i], false); 
        }
        
        //go through elements of nums2 and check if map contains element
        // if yes then check if it is visited 1st time
        for(int i = 0; i < nums2.length; i++)
        {    //find a match 
            if(map.containsKey(nums2[i]) && !map.get(nums2[i])) // value of nums2[i] is not true
            {
            	 map.put(nums2[i], true);  //  set "true" if it is not seen 1st time, 
                 //add to result
            	 result.add(nums2[i]);
            }
        }
              
        //convert ArrayList<Integer> to int arry 
       int[] r = new int[result.size()];
       for(int i = 0; i < result.size(); i++)
       {
           r[i] = result.get(i); //[4,9]
       }
  
       return r; 
    }
	

	/*
	 * 
    350. Intersection of Two Arrays II
	Best Solution 1
	
	Input: nums1 = [1,2,2,1], nums2 = [2,2]
    Output: [2,2]

	Steps: 1. Sort arrays
    	   2. if element from two arrays equals, then add to new ArrayList
    	   3. Move two pointers to next index to keep comparision.
   */
	public static List<Integer> intersection(int[] nums1, int[] nums2) {
		Arrays.sort(nums1); //{1,1,2,3};
        Arrays.sort(nums2); //{2,3}
        int i = 0;
        int j = 0;
  
       List<Integer> myList = new ArrayList<Integer>();
        
        while((i < nums1.length) && (j < nums2.length)){
        	
        	if (nums1[i] == nums2[j]){  // if element from two arrays equal, then add to new ArrayList
                myList.add(nums1[i]); 
                i++;
                j++;
            }
        	else if(nums1[i] < nums2[j]){
                i++;
            }
            else if (nums1[i] > nums2[j]){
                j++;
            }
            
        }
    
        return myList;
	}

	
	public static void main (String[] args)
    {
		int[] nums1 = new int[] {1,2,3,1};
		int[] nums2= new int[] {2,3};
		
		int[] list = intersect(nums1,nums2);
		System.out.println("Intersect: " +  Arrays.toString(list));
		
		
		// Solution 2
		List<Integer> list1 = intersection(nums1,nums2);
		System.out.println("Intersection Elements: "+ list1);
				
		
		nums1 = new int[] {6,5,2,3,4,1};
		nums2= new int[] {2,3,5,7};
		
		int[] list2 = intersect(nums1,nums2);
		System.out.println("Intersect: " + Arrays.toString(list2));
		
		list1 = intersection(nums1,nums2);
		System.out.println("Intersection Elements in a list: "+ list1); //2,3,5
		
		
		nums1 = new int[] {4,9,5};
		nums2 = new int[] {9,4,9,8,4};
		
		//Solution 1
		int[] list3 = intersect(nums1,nums2);
		System.out.println("Intersect: " +  Arrays.toString(list3));
		
		list1 = intersection(nums1, nums2);
		System.out.println("Intersection Elements in List: "+ list1); //4,9

		
		nums1 = new int[] {1,2,2,1};
		nums2= new int[] {2,2};
		
		int[] list4 = intersect(nums1,nums2);
		for(int i = 0; i < list4.length; i++) {
			System.out.println("Intersect: " +  + list4[i]); //2
		}
		
		List<Integer> list5 = intersection (nums1,nums2);
		System.out.println("Intersection in list: " + list5);
		

    }
}