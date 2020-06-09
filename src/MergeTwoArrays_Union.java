import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 88. Merge Sorted Array
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.


Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

Time complexity : O(n + m)
Space complexity : O(1).


  Input: arr1[] = { 1, 3, 4, 5}, 
         arr2[] = { 2, 4, 6, 8}
          
  Output: arr3[] = {1, 2, 3, 4, 4, 5, 6, 8}
  
Solution:
- Create an array arr3[] of size n1 + n2.

- Check if current element of first array is smaller than current element of second array. 
  If yes, store first array element and increment first array index. 
  Otherwise do same with second array 
   
- If there are are remaining elements in arr1[] or arr2[], copy them in arr3[]. 
 */

public class MergeTwoArrays_Union {
	
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = 0, j = 0, k = 0;
		
		int[] arr3 = new int[m + n]; 
		
		while (i < m && j < n) {
			//Check if current element of first array is smaller than current element of second array
			if (nums1[i] < nums2[j]) {
				arr3[k++] = nums1[i++];
			} else {
				arr3[k++] = nums2[j++];
			}
		}
		
		 // Store remaining elements of first array 
		while (i < m) 
			arr3[k++] = nums1[i++];
		
		// Store remaining elements of second array
		while (j < n)  //6,8
			arr3[k++] = nums2[j++];
				
		
		for (int l: arr3) {
			System.out.print(l + " ");
		}
		
	}

	
	
	
	/**
	 * @param a
	 *            - a list of non null integers, in ascending order
	 * @param b
	 *            - a list of non null integers, in ascending order
	 * @return the integers that are contained in either a or b, in ascending order
	 */
	static List<Integer> union(List<Integer> a, List<Integer> b) {
		int l1 = a.size();
		int l2 = b.size();
		int i = 0;
		int j = 0;
		
		List<Integer> ret = new ArrayList<Integer>();
		
		while (i < l1 && j < l2) {
			
			if (a.get(i) == b.get(j)) {
				ret.add(a.get(i++));
				j++;
				
			} else if (a.get(i) < b.get(j)) { //Check if current element of first list is smaller than current element of second list
				ret.add(a.get(i++));
			} else {
				ret.add(b.get(j++));
			}
		}
		
		// Store remaining elements of first array 
		while (i < l1) {
			ret.add(a.get(i++));
		}
		
		// Store remaining elements of second array
		while (j < l2) {
			ret.add(b.get(j++));
		}
		return ret;
	}
	
	
	public static void main (String[] agrs) {
		int[] arr1 = { 1, 3, 4, 5}; 
		int[] arr2 = {2, 4, 6, 8};		
	    //Output: arr3[] = {1, 2, 3, 4, 4, 5, 6, 8}
		
		int m = arr1.length;
		int n = arr2.length;
		merge(arr1, m, arr2, n);
				
		
		int[] nums1 = {1,2,3,0,0,0};
		int[] nums2 = {2,5,6};
		System.out.println("\nmerged: ");
		merge(nums1, 3, nums2, 3);
		
		List<Integer> a = Arrays.asList(1, 3, 4, 5 );
		List<Integer> b = Arrays.asList(2, 4, 6, 8);
				
		List<Integer> res = union(a, b) ;
		System.out.println(res);
	}
}