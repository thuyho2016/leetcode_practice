/*
 4. Median of Two Sorted Arrays  ( Hard level)
 https://leetcode.com/problems/median-of-two-sorted-arrays/
 
 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

Median = (maxLeft + minRight) / 2

time Complexity: O(min(log m, log n)) and Space Complexity : O(1) space

 */
public class MedianTwoSortedArray {
	
	
/*	Same with MergeTwoArrays_Union.java , IntersectionTwoArrays.java
 *  1. uses merge sort to compare simultaneous values of the two sorted arrays and store them in a new array 
      which has the size of arr1 + size of arr2 m + n
     - Create an array arr3[] has size n1 + n2.

	- Check if current element of first array is smaller than current element of second array. 
	  If yes, store first array element and increment first array index. 
	  Otherwise do same with second array 
	   
	- If there are are remaining elements in arr1[] or arr2[], copy them in arr3[]. 
	
	2. Since the new array is sorted, we need to access the middle elements for the median:
	if the array length is odd, the middle element is the median
	if the array length is even , then the sum of the middle two elements is the median.

	
*/	
	public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
		
		//create third array 
        int m = nums1.length;
        int n = nums2.length;
        int[] arr = new int[m + n];
        
        int i = 0, j = 0, k = 0;
       
        while (i < m && j < n) {
			//Check if current element of first array is smaller than current element of second array
			if (nums1[i] < nums2[j]) {  // 1 < 3 , 2 < 3
				arr[k++] = nums1[i++];  //[1, 0, 0, 0], [1, 2, 0, 0]
			} else {
				arr[k++] = nums2[j++];
			}
		}
		
		 // Store remaining elements of first array
		while (i < m)  //2 < 2 , not go inside
			arr[k++] = nums1[i++];
		
		// Store remaining elements of second array
		while (j < n)  // 0 < 2
			arr[k++] = nums2[j++];  //[1, 2, 3, 0], [1, 2, 3, 4]
                      
        // arr is the merge sorted array - [1, 2, 3, 4]
        double median = 0;

        if((arr.length) % 2 == 1){  // (m + n) %2 length is odd
            median = arr[arr.length / 2];
        }
        else {
        	median = (double) ( arr[arr.length/2 - 1] + arr[arr.length/2]) / 2;
        }
      
        return median;
    }
	
	public static void main(String[] args) {
/*		int[] a = new int[]{1,3};
		int[] b = new int[]{2};
        System.out.println("Median " + findMedianSortedArrays(a,b)); //2
        System.out.println("Median2: " + findMedianSortedArrays2(a,b)); //2
 */       
        int[] a2 = new int[]{1,2};
		int[] b2 = new int[]{3,4};
	//	 System.out.println("Median " + findMedianSortedArrays(a2,b2));
        System.out.println("Median2: " + findMedianSortedArrays2(a2,b2)); //(2 + 3)/2 = 2.5
    
    }

}
