import java.util.Arrays;

/*
 
 215. Kth Largest Element in an Array
 https://leetcode.com/problems/kth-largest-element-in-an-array/
 
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5


Example 2:
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4



Time complexity : O(N) in the average case
Space complexity : O(1).
 
 */
public class KthLargestElement_ByQuickSort
{ 
    public static void main(String[] args) 
    {	
    	int[] arr = {50, 30, 90, 80, 40, 70};
    	//int[] arr = {3,2,1,5,6,4};
        int k = 2;
    	int res = findKthLargest(arr,k); //Queue: [5, 6]
    	
    	System.out.println("Kth largest element by Quicksort " + res); //5
    	
    /*	int[] arr2 = {3,2,3,1,2,4,5,5,6};  // 1 2 2 3 3 4 5 5 6
    	k = 4;
    	System.out.println("Kth largest element By Quicksort "+ findKthLargest(arr2, k)); //4
    	*/
    }
 
    
    /* Solution: I am taking pivot at start index
    Find kth largest element is equivalent to find (n - k)th smallest element in array.
    It is worth mentioning that (n - k) is the real index (start from 0) of an element. 
    
    pivot element 3
    Before swap in left side : [3, 2, 1, 5, 6, 4]
    will swap left a[end]= 1 and a[pivot]=3
    After swapped left: [1, 2, 3, 5, 6, 4]
    pivotIndex 2
    pivot element 5
    will swap a[start] = 6 and  a[end]=4
    After swapped right: [1, 2, 3, 5, 4, 6]
    Before swap in left side : [1, 2, 3, 5, 4, 6]
    will swap left a[end]= 4 and a[pivot]=5
    After swapped left: [1, 2, 3, 4, 5, 6]
    pivotIndex 4
    Kth largest element by Quicksort 5
    */
    public static int findKthLargest(int[] nums, int k) {
    	int start = 0;
    	int end = nums.length - 1; //5 
    	
    	//recursively
    	while (start < end) {
    		
    		int pivotIndex = partion(nums, start, end); // start 0 to length -1
	    	
    		System.out.println("pivotIndex " + pivotIndex); //3,2,5
    		
	    	if (pivotIndex == nums.length - k) {
	    		return nums[pivotIndex];
	    	}
	    	else if (pivotIndex < nums.length - k)  //2 < (n - k) = 3
	    		start = pivotIndex + 1;
	    	
	    	else //if (pivotIndex > nums.length - k)  
	    		end = pivotIndex - 1;
	    	
    	}
    	
    	System.out.println("K largest: at " + start + " is " + nums[start]);
		return nums[start]; //5
    }
   

    private static int partion(int[] a, int start, int end) {
       
    	int pivot = start; // pivot as start index 
        System.out.println("pivot element " + a[pivot]); //3
       
       //start < pivot < end 
       
    	while (start <= end) {   //3 <= 4
           //identify a number from start side which is less then the pivot value
    		while (a[start] <= a[pivot]) {  // 3 <= 3
    			start ++;  // move to next element
    		}
    		
          //identify a number from end side which is greater then the pivot value
    		while (a[end] > a[pivot]) {   // a[5] = 4 > a[0] = 3
    			end--;    // move to previous element
    		}
     
    		if (start > end) break; //3 > 2 , 5 > 4
    		
    		
    		System.out.println("will swap a[start] = " + a[start] + " and  a[end]=" + a[end]);
    		
    		swap(a, start, end);
    		
    		System.out.println("After swapped right: " +  Arrays.toString(a)); // [1, 2, 3, 5, 4, 6]
    		
    	}
    	
    	System.out.println("Before swap in left side : " + Arrays.toString(a));  //[3, 2, 1, 5, 6, 4]
    	System.out.println("will swap left a[end]= " + a[end] + " and a[pivot]=" + a[pivot]);
    	
    	swap(a, end, pivot); // swap end 1 & pivot 0
    	
    	System.out.println("After swapped left: " + Arrays.toString(a)); //[1, 2, 3, 5, 6, 4]
    	
    	
    	return end;
    }
     
    private static void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
}