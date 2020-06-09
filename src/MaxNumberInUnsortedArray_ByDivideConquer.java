
/* 
 Find max number in  unsorted array
 
 int[] arr = {6, -1, 0, 10};
 Output = 10
 
 Steps:
 1. Apply Divide and Conquer
 2. Use Math.max to get maximum of two numbers 
 
 It similar with find Kth largest in sort array
 */

public class MaxNumberInUnsortedArray_ByDivideConquer {
   
	
	//use divide and conquer.
	public static int findMaxDivideConquer(int[] arr){
		if ( arr == null) return 0;
	    return findMax(arr, 0, arr.length-1);
	}
	
	//use divide and conquer and Math.max
	public static int findMax(int[] arr, int left, int right) {  // right = arr.length -1
	   // check indexes
	   if (right - left <= 1) {
	     return Math.max(arr[left], arr[right]);
	     
	   } else { //if (left < right )
		   
		   int mid = (left + right) /2; // it can be solved by left + (right - left) / 2; 
	     
		   int leftMax =  findMax(arr, left, mid);  // recursive
	     
		   int rightMax = findMax(arr, mid + 1, right);
	    
	    
		   // use Math.max to get the largest one
		   int maxNum = Math.max(leftMax, rightMax);
			
		   return maxNum;
	   }
	}
	
	public static void main(String[] args) {
		
		int[] arr = {6, -1, 0, 10};
		
		int max = findMaxDivideConquer(arr);
		System.out.println("Max number in arry: " +  max ); //10
		
		//get Index of max number
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == max)
				System.out.println("Index of max number:  " + i);
		}
	}
}


