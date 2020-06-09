package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
 * 912. Sort an Array
 * https://leetcode.com/problems/sort-an-array/
 * 
Given an array of integers nums, sort the array in ascending order. 
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]


 * Quicksort first divides a large list into two smaller parts: the low elements and the high elements.
 * and recursively sort the parts
 *
 * Steps: use divide and conquer strategy
 *  1. Choose an element called pivot.It can be the middle index element
 *  2. Identify all elements with values less than (<) the pivot come before the pivot
 *     and elements with values greater than the pivot come after it:  left side < pivot < right side
 *  3. Swap numbers
 *  4. Recursively apply for left side and right side
 *  Θ(n log(n))
 *  
 *  Input:    {38,27,30,9,3,2} 
 * 
 *  Output: 2 20 24 56 75 99 
 */

public class QuickSort {
    
	//start pivot at the first number
    public static void main(String a[]){ 
      	//int[] input = {5,8,1,3,7,9,2};
       // int[] input = {5,2,3,1};
    	int[] input = {50, 30, 90, 80, 40, 70}; 
    	//				 i	                  j 
     	
        QuickSort qs = new QuickSort();        
        qs.sort(input);
        System.out.println("Output: " + Arrays.toString(input));
      
        int[] nums = {50, 30, 90, 80, 40, 70};
        System.out.println("Output: " + qs.sortArray2(nums));
    }
 
   /** Quicksort first divides a large list into two smaller parts: the low elements and the high elements.
    * and recursively sort the parts
    *
    * Steps: use divide and conquer strategy
    *  1. Choose an element called pivot. It can be the middle index element
    *  2. Identify all elements with values less than (<) the pivot come before the pivot
    *     and elements with values greater than the pivot come after it:  left side < pivot < right side
    *  3. Swap numbers
    *  4. Recursively apply for left side and right side
    *  Θ(n log(n))
    */
    
    public void sort(int[] arr) {
    	
        if (arr == null || arr.length == 0) { //check for arry is not empty
            return;
        }
  
        quickSort(arr, 0, arr.length - 1);  // start index 0 -> 5
    }
 
    private void quickSort(int[] ary, int left, int right) {
    	int position;
    	if ( left < right) {
    		//step 1,2,3
        	position = partition(ary, left, right); // find location
        	//4.recursively apply for left side and right side
        	quickSort(ary, left, position - 1); // left side from lower bound to before position of pivot
        	quickSort(ary, position + 1, right); // right side from after pos + 1 to higher bound   
    	}
    }
    
    //O(n^2)
   //pick the pivot as first number
    private int partition(int[] arr, int lowerBound, int upperBound) {
	    
	    
	    int pivot = arr[lowerBound]; // I am taking pivot as first number 	    
	    System.out.println("pivot: " + pivot);	    
	   
	    // 2 pointers
	    int left = lowerBound;
	    int right = upperBound;
	     
	    while ( left < right)  {
	        
	    	while (arr[left] <= pivot) {  //if element on left position is less than pivot , move i ++
		       left++;
	        } 
	    
	    	while (arr[right] >= pivot) { 
		       right--;
	        }
	    	
	    	if (left < right) {
	    		 swap(arr, left, right); 
	    		
	    	}
	    }
	//    if ( left < right) {
	    	swap(arr, lowerBound, right);  //swap pivot to element at right position
	//    }
        return right;
        
	}	        
    
    private static void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
    
   
    /**   Best Case to pick pivot is median - make the algorithm optimize 
     * My job to find the position for pivot ( is the item I am going to find position)
     * When I have pivot 's index,  I return the index of this pivot 
     * 
     * i, j are searching pointers
     
     What does i do?
     my job is to remember that last position that an element was placed in and was less than the pivot


    - Find all element is less than pivot, seat in the left side
    - Find all element is greater than pivot and seat in the right side
       
                      3 4 5 8 9 7 
       pivot = 8
                      i,j = 0 
       i = 0, Is arr[0] = 3 < pivot ? yes, i++
       i = 1  Is arr[0] = 7 < pivot ? no, i++           
                     less greater                               

                      
     */
    public List<Integer> sortArray2(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        if(nums.length==0) return list;
        
        quicksort(0,nums.length-1,nums);
       
        for (int i=0;i<nums.length;i++)
        {
            list.add(nums[i]);
        }
        return list;
    }
    
    public void quicksort(int left,int right,int[] a)
    {
        if(left==right) return;
		
        //choose middle as target
        int target= a[(left+right)/2];
        int start= left;
        int end=right;
       
        while(start <= end)
        {
            while(start<=end && a[start]< target) start++;
            while(start<=end && a[end]>target) end--;
            
            if(start<=end)
            {
            	//swap
                int temp=a[start];
                a[start]=a[end];
                a[end]=temp;
                
                start++;
                end--;
            }
        }
        
        //4.recursively apply for left side and right side
        if(end>=left) quicksort(left,end,a);
        if(start<=right) quicksort(start,right,a);
    }
}
	