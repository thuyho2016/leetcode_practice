/* Apple Phone Interview
 * Given an array of sorted integers and target . Find closest element to target
 * 
 * array = [10,20, 30,40, 50, 60, 70] and target = 45
 * Output = 50  ( 45 is number between 40 and 50, so pick 50 )
 * 
 * array = 10, 20, 30, 40, 50, 60, 70] , target = 75
 * output = 70, because 75 > last element 70 of sorted array
 * 
 * array = 10, 20, 30, 40, 50, 60, 70 and 50
 * output = 50 ( target is found) 

https://www.geeksforgeeks.org/find-closest-number-array/

An efficient solution:
- use Binary Search to find index of closest elment

Refer BinarySearch.java
 */


public class FindClosestNumberToTarget_InSortedArray_BS {	

	public static int findClosestNumber(int[] arr, int target) {
	
		int index = getClosestElementIndex(arr, target);
	    return arr[index];
    }
	 	
	
	private static int getClosestElementIndex(int[] arr, int target)
    {
        int left = 0,right = arr.length-1;
        
        while(left + 1 < right )   //left + 1 to avoid ArrayIndexOutOfBoundsException:
        {
            int mid = left + (right - left)/2; //mid index = 3
            
            if(target < arr[mid])  // search on the left side
            	right = mid - 1;
            else 
            	left = mid + 1;
        }
        
      //  int closestIdx = Math.abs(arr[left] - target) <= Math.abs(arr[right] - target) ? left : right;
      //  return closestIdx;
        if (Math.abs(target - arr[left]) <= Math.abs(arr[right] - target)) // left = 4, right = 4 
        	return left;
        else
        	return right;
      
    }
	
	
   public static void main(String[] args) {
  
    int[] arr = {10,20, 30,40, 50, 60, 70};
   	System.out.println(getClosestElementIndex(arr, 45)); //index = 4
   	System.out.println(findClosestNumber(arr,45));  //50
   	
   	int[] arr2 = {10,20, 30,40, 50, 60, 70};
   	System.out.println(getClosestElementIndex(arr2, 50)); //index = 4
   	System.out.println(findClosestNumber(arr2,50));  //50
   	
   	int[] arr3 = {10,20, 30,40, 50, 60, 70};
   	System.out.println(getClosestElementIndex(arr3, 75)); //index = 6
   	System.out.println(findClosestNumber(arr,75));  //70
   	
   	int[] arr5 = {1,2,3,4,5};
 	System.out.println(getClosestElementIndex(arr5, -1)); //index = 0  // while( left + 1 + right)
 	
    	
   }


}
	
	