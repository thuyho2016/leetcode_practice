package sort;
/*
 * Absolute Value Sort - Mock
  Given an array of integers arr, write a function absSort(arr)
  If two numbers have the same absolute value, sort them according to sign, where the negative numbers come before the positive numbers
   
   input:  arr = [2, -7, -2, -2, 0]
   output: [0, -2, -2, 2, -7]
   
   
   Steps: using Merge Sort By recursive
 * Divide the unsorted array into 2 parts - find middle 
 * sorts the left side of the array by recursive:  mergeSort(arr, l, m)
 * sorts the right side of the array by recursive  mergeSort(arr, m+1, r)
 * merge both sides together merge(arr, l, m, r) .It is based on merge two sorted

 //Output : wrong ?????
 
 * See : MergeTwoArrays.java and MergeSortByDivideConquer.java
 */

public class AbsoluteValueSortByMerge { 
	
	//Output : wrong ?????
	public static void main(String[] args) {
        int[] arr = { 2, -7, -2, -2, 0 };
        
        int high = arr.length - 1;
        mergeSort(arr, 0, high);  
        for(int i: arr){
            System.out.print(i + " ");  //Expect: [0, -2, -2, 2, -7] , 
        }
       
	}
	
    public static int[] mergeSort(int[] arr, int low, int high) {
    	if (arr == null || arr.length == 0)
			return null;
    	
    	if (low == high) {
    		return arr;
    		
    	} else if (low < high) { //  0 < 4
            int mid = low + (high - low) / 2;  //middle = 2  
            
            // sorts the left side of the array
            int[] a = mergeSort(arr, low, mid);  //(low = 0, middle = 2) 
            
            // sorts the right side of the array
            int[] b = mergeSort(arr, mid + 1, high); // (3,4)  - remember to plus 1 to start the right side
            
            // compare and merge two arrays
           return mergeTwoArrays(a, b); // 
            
        } else {
        	return null;
        }
    }
    
    /* 
     * 1. Add all elements from array to temp array
     * 2. compare and swap
     */
 
    //Output : wrong ?????
    private static int[] mergeTwoArrays(int[] a, int[] b) { 
    	//index of left subarray, right subarray, merge array
    	int i = 0, j = 0, k = 0;
    	
    	int m = a.length;
    	int n = b.length;
    	int[] arr3 = new int[m + n];
        
        while (i < m && j < n) {
        	//left side
            if (Math.abs(a[i]) < Math.abs(b[j]) ) {         
            	arr3[k++] = a[i++];                                   
          
            } else {//if (Math.abs(a[i]) >= Math.abs(b[j])) {  
                arr3[k++] = b[j++]; 
            } 
        }  
        // Store remaining elements of first array 
        while ( i < m) {
        	arr3[k] = a[i];
        	i++;
        	k++;
        }
       // Store remaining elements of second array
        while ( j < n) {
        	arr3[k] = b[j];
        	j++;
            k++;
        }
        
        return arr3;
    }
    
    /*
     * function absSort(arr) {
  //build comparison function
  function absoluteValueComparison(a, b) {
    //sort by absolute value
    if(Math.abs(a) < Math.abs(b)) {
      return -1
    } else if(Math.abs(a) > Math.abs(b)) {
      return 1;
    //sort identical absolute values in numerical order
    } else if(a < b) {
      return -1;
    } else if(a > b) {
      return 1;
    } else {
      return 0;
    }
  } 
  //call comparison function as callback in array sort
  return arr.sort(absoluteValueComparison);
}
     */
	
} 
