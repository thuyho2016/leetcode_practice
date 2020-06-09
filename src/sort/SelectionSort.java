package sort;
/*
Following are the steps involved in selection sort in ascending order):

1. Start from the first element, we search the smallest element in the array, and replace it with the element in the first position.
2. move on to the second position, and look for smallest element present in the subarray, starting from index 1, till the last index.
3. replace the element at the second position in the original array with the second smallest element.

   This is repeated, until the array is completely sorted.
   
Input:  {3, 6, 1, 8, 4, 5}  //first position, search smallest, found smallest number 1 

        {1, 6, 3, 8, 4, 5} // replace smallest with first position (swap)
        {1, 3, 6, 8, 4, 5} // move to 2nd position, replace smallest to the 2nd position
        {1, 3, 4, 8, 6, 5} // move to 3rd position, swap 6 & 4, 
        {1, 3, 4, 5, 6, 8} // move to 4th position, 8 & 5 
   		{1, 3, 4, 5, 6, 8} // move to 5th position, no swap
 */

public class SelectionSort  {
	
	public static void selectionSort(int[] arr) {
		int high = arr.length;
		
	    for (int i = 0; i < arr.length - 1; i ++) {
	    	int index = indexOfSmallestNumber(arr, i, high); // look for index of smallest element in the given subarray
	        swap(arr, i, index);
	    }
	}
	
	//find  the smallest element in the array
	public static int indexOfSmallestNumber(int[] arr, int startIndex, int endIndex) {
		int minIndex = startIndex;  //initialize
		int minValue = arr[startIndex];
		
		for(int i = minIndex + 1 ; i < endIndex; i++) {
			if (minValue > arr[i]) {  //search the smallest element in the array
				minIndex = i;         //get index i that has smallest 
				minValue = arr[i] ;  // swap to first position
			}
		}
		return minIndex;
	}
 
	private static void swap(int[] nums, int i, int j) {
	    int temp = nums[i];
	    nums[i] = nums[j];
	    nums[j] = temp;
	}
	

	public static void main (String[] args)
    {
		int[] nums = {3, 6, 1, 8, 4, 5};
		//int[] nums = {3, 6, 2, 8, 1, 5};
		selectionSort(nums);
		for (int i = 0; i <nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println(); //1 3 4 5 6 8 
		//System.out.println(); // 1 2 3 5 6 8 
    }
	
}