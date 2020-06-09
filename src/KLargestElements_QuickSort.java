
/* Quicksort first divides a large list into two smaller parts: the low elements and the high elements.
 * and recursively sort the parts
 *
 * Steps:
 *  1. Choose an element called pivot.It can be the middle index element
 *  2. Identify all elements with values less than (<) the pivot come before the pivot
 *     and elements with values greater than the pivot come after it:  left side < pivot < right side
 *  3. Swap numbers
 *  4. Recursively apply 
 *  Θ(n log(n))
 *  
 *  Input:   24, 20, 56, 75, 2, 99
 *  Indexes:  0  1   2   3   4  5   
 *  pivot[2] = 56
 *  Swap 56 and 2
 *  [24, 20, 2, 75, 56, 99]  
 *   recursive left side from 0 -> 2 for [24,20,2]
 *   pivot[1] = 20
 *   Swap 24 and 2
 *   [2, 20, 24, 75, 56, 99]
 *   Swap 20 and 20
 *   [2, 20, 24, 75, 56, 99]
 *   
 *   recursive right side from 3 -> 5 for [75, 56, 99]
 *   [2, 20, 24, 75, 56, 99]
 *   pivot[4]= 56
 *   swap 75 and 56
 *   [2, 20, 24, 56, 75, 99]
 *   recursive right side from 4 -> 5
 *   [2, 20, 24, 56, 75, 99]
 *   pivot[4] = 75
 *   swap 75 and 75
 *   [2, 20, 24, 56, 75, 99]
 *   
 *  Output: 2 20 24 56 75 99 
 *  
 *  
 * Find the first Kth largest elements (3 largest elements), k = 3
 * Output: 56 75 99 
 */

public class KLargestElements_QuickSort {
       
 
    // Solution 1:  I am taking pivot at middle index
    public int[] kLargest(int[] arr, int k) 
    {
        
    	sort(arr); // do quick Sort  
    	
        int[] newArr = new int [k];  // length k = 3
	    
        int length = arr.length - 1; 
	    for (int i = 0; i < newArr.length; i++){  // use size of new array to print the first kth largest elements, length = 3
	    	k--;	
	    	newArr[k] = arr[length - i]; //99, 75, 56
	    	System.out.println("At k = " + k + " has " + newArr[k]);	 	    	
	    }
	  
	    return newArr; //[50, 30, 23]
	} 
   
    public void sort(int[] arr) {
    	int high  = arr.length - 1; 
        if (arr == null || arr.length == 0) { //check for arry is not empty
            return;
        }
        quickSort(arr, 0, high);  // start index 0 -> 5 (length - 1 = 6 - 1)
    }
 
    // quickSort(0, 5, input)
    private void quickSort(int[] input, int low, int high ) {
         
        int i = low;
        int j = high;
        
        // I am taking pivot as middle index number
        // calculate pivot index = 0 + (5 - 0 /2) = 2
        
        int pivotIndex = low + (high -low) / 2;
        int pivot = input[pivotIndex];  //56
        System.out.println("pivot index " + pivotIndex + ", pivot " + input[pivotIndex]);
        
        // Divide into two arrays
        while (i <= j) {  // 0 <= 5  ; 3 <= 3 (at second while -  input = [24, 20, 2, 75, 56, 99] ; 3 <= 2, exit
            /**
             * In each iteration,identify a number from left side which 
             * is greater then the pivot value, and identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then swap both numbers.
             */
        	
            while (input[i] < pivot) {  // 24, 20 < 56  ,  when i = 2 -> input[2] = 56 < 56 , exit 1st while loop.  2nd while loop, 75 < 56, skip
            	System.out.println("input[i] = " + input[i] + " > " + pivot);
            	i++;                    //1, 2
            }
            
            while (input[j] > pivot) {  //  75 2 99 , j = 5, input[5] = 99 > 56 , comes inside while loop, 
            	 						//  input[4] = 2 > 56 , because 2 > 56, exit 1st while loop. 
            	System.out.println("input[j] = " + input[j] + " > " + pivot);						//  input [3] = 75 > 56, comes inside while loop
                j--; //5, 4
            }
            
            System.out.println("Index i=" + i + " <= j=" + j + " ?");
            
            if (i <= j) {  // i = 2 <= j = 4 , go inside ;  i = 3 <= j = 2, exit
            	System.out.println("swap " + input[i] + " and " + input[j]);
                swap(input, i, j);  // swap 56 and 2
                print(input);
                
                //move index to next position on both sides
                i++;   
                j--;  
                System.out.println("shift i to " + i + " <= j to " + j);
            }
        }
        
        //i = 3, j= 2, 
        // when i > j ,  recursive call quickSort() method 
        // cases:  high > i > j > low 
        if (low < j) {   // 0 < 2  
         	System.out.println("recursive left side from low " + low + " to j = " + j );
         	print(input);
            quickSort(input, low, j);
        }
        
        if (i < high)  {  // 3 < 5
        	System.out.println("recursive right side from i = " + i + " to high = " + high);
        	print(input);
            quickSort(input, i, high);
        }
    }
 
    private void swap(int[] input, int i, int j) {
    	int temp = input[i];   //56
    	input[i] = input[j];   //2
    	input[j] = temp;
    }
    
    public void print(int[] input) {
    	for(int i:input){
            System.out.print(i);
            System.out.print(" ");
        }
    	System.out.println(" ");
    }
    
    public static void main(String a[]){
        
    	int[] input = {24,20,56,75,2,99, 6};
        KLargestElements_QuickSort qs = new KLargestElements_QuickSort();
        qs.print(input);
  /*    qs.sort(input);
        System.out.print("Sorted: ");
        qs.print(input);
   */   
        
      // find Kth largest (3 largest elements)
        int k = 3;
        int[] res = qs.kLargest(input, k) ;
        for (int i : res){  
	    	System.out.print(i + " ");	//56 75 99 
	    }
    }
}
	