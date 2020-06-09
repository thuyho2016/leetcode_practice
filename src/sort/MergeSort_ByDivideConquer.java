package sort;

/* 
 * Steps of Merge Sort By recursive:
 * Divide the unsorted array into 2 parts - find middle 
 * sorts the left side of the array by recursive
 * sorts the right side of the array by recursive
 * merge both sides together
 
 Example 1:
 Input:  { 38,27,30,9,3, 2}   
 Output: [2 3 9 27 30 38]
 
  
  Step1:  Divide 
   Left side = 38,27,30                        Right side = 9, 3, 2   //divide
  Step2:  38,27      30   //divide                      9,3   2 
  Step3:  38  27  //divide    30                    9  3   2
   
  Step 4: merge & sort
     27,38 //merge & sort   30               3,9     //merge & sorted   2
   
   Step 5: merge & sort                           2,3,9
       27, 30, 38 
  
   Step 6: merge both side & sort                
       2, 3, 9, 27, 30, 38


Example 2:       
input:  arr = [2, -7, -2, -2, 0]
output: [0, -2, -2, 2, -7]

NOTE: Same with  MergeTwoArrays_Union.java or AbsoluteValueSort.java

 */

public class MergeSort_ByDivideConquer {
   
    public static void sort(int[] arr) {
        
        mergeSort(arr, 0, arr.length - 1);  //{38,27,30,9,3} --> index from 0 to 4
    }
 
   
    private static void mergeSort(int[] arr, int lower, int higher) {
    	if (arr == null || arr.length == 0)
			return;
					
    	if (lower == higher) 
    		return;
    		
        if (lower < higher) { //  0 < 4
            int middle = lower + (higher - lower) / 2;  //middle = 2  ( like quick sort)
            
            // sorts the left side of the array
            mergeSort(arr, lower, middle);  //(lower = 0, middle = 2) 
            
            // sorts the right side of the array
            mergeSort(arr, middle + 1, higher); // (3,4)  - remember to plus 1 to start the right side
            
            // compare and merge both sides
            mergeBothSides(arr, lower, middle, higher); // pass 3 parameters - lower , middle, higher
        } 
        else {
        	return;
        }
    }
    
    /* Steps:
     * 1. create a temp Array with the same size
     * 2. compare and swap
     * NOTE: use Math.abs(..) for negative number
     */
 
    private static void mergeBothSides(int[] arr, int lower, int middle, int higher) {  //low = 0 ,m = 0 , high = 1
    	
    	int[] tmpArr = new int[arr.length]; //create a temp Array with the same size
        
    	for (int i = lower; i <= higher; i++) {  // add elements at index 0, 1 to tempArr -> tempArr = [38, 27, 0, 0, 0]
            tmpArr[i] = arr[i];               //[38, 27, 0, 0, 0, 0], [27, 38, 30, 0, 0, 0]
        }
    	
        int i = lower;
        int j = middle + 1;
        int t = lower;
        
        while (i <= middle && j <= higher) {
        	//left side
            if (Math.abs(tmpArr[i]) <= Math.abs(tmpArr[j])) {         //compare 38 & 27 from [38, 27, 0, 0, 0, 0]
            //	arr[t] = tempArr[i];                                   //  i   j
            //    i++;
                arr[t++] = tmpArr[i++];  
            } else {                     // 38 > 27  -- > swap
             //   arr[t] = tempArr[j];   //{27,27,30,9,3,2}, [27, 30, 30, 9, 3, 2]               
              //  j++;
                arr[t++] = tmpArr[j++];
            }
           // t++;
        }
        while (i <= middle) {       //swap
            arr[t] = tmpArr[i];  //{27,38,30,9,3,2} , [27, 30, 38, 9, 3, 2], [27, 30, 38, 3, 9, 2]
            t++;
            i++;
        }
    }
  
  
    public static void main(String a[]){
		int[] arr = {38, 27, 30, 9, 3, 2}; 
         sort(arr);
         
         for(int i:arr){
             System.out.print(i + " ");   // 2 3 9 27 30 38 
         }
         
         System.out.println();
         int[] arr2 = {2, -7, -2, -2, 0}; 
         sort(arr2);
          
         for(int i : arr2){
             System.out.print(i + " "); //  0 2 -2 -2 -7 
         }
         
     }
}

	  
	