package sort;
/*
 * comparing each pair of adjacent items and swapping them if they are in the wrong order
 * i = 0, j = 1 -> 5
 * { 4, 2, 9, 6, 0, 1 }  compare a pair of 4,2 -> 4 > 2, then swap 
 * { 2, 4, 9, 6, 0, 1 }  compare a pair of 4,9 -> no swap
 * { 2, 4, 9, 6, 0, 1 }  compare a pair of 9,6 -> swap
 * { 2, 4, 6, 9, 0, 1 }  compare a pair of 9,0 -> swap
 * { 2, 4, 6, 0, 9, 1 }  compare a pair of 9,1 -> swap
 * { 2, 4, 6, 0, 1, 9 }  
 *  i = 1, i = 0-> 4
 *  { 2, 4, 6, 0, 1, 9 } compare a pair of 2, 4 -> no swap
 *  { 2, 4, 6, 0, 1, 9 } compare a pair of 4, 6 -> no swap
 *  { 2, 4, 6, 0, 1, 9 } compare a pair of 6, 0 -> swap
 *  { 2, 4, 0, 6, 1, 9 } compare a pair of 6, 1 -> swap
 *  { 2, 4, 0, 1, 6, 9 } compare a pair of 6, 9 -> no swap
 *  ... continute
 *  m = 2, i = 0->4
 *  
 *  use 2 arrays - 2nd array start index i + 1
 */

public class BubbleSort {
	 
    
    public static void bubble_sort(int array[]) {
    	 System.out.println ( "Length: " + array.length);
       // { 4, 2, 9, 6, 0, 1 }
        for (int i = 0; i <  array.length ; i++)  {  // i start from 0 to 5  
        	System.out.println ( "i = " + i);
            for (int j = i + 1; j < array.length; j++) {   // j start from index 1 -> 5  ==> { 2, 9, 6, 0, 1 };
                System.out.println ( "Compare " + array[i] + " and " + array[j]); 
                if (array[i] > array[j]) { //swap because 4 > 2 
                	System.out.println ("Swap as " +  array[i] + " > " + array[j] );
                    swap(array, i, j);
                }
            }
            printNumbers(array);
        }
    }
 
    private static void swap( int[] array, int i, int j) {
 
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
 
    private static void printNumbers(int[] input) {
         
        for (int i = 0; i < input.length; i++) {
        	System.out.print(input[i]);
        	if (i < input.length - 1) {
        		System.out.print(", ");
        	} else 
        		 System.out.println("\n");
        }
    }
 
    public static void main(String[] args) {
        int[] input = { 4, 2, 9, 6, 0, 1 };
        bubble_sort(input);
 
    }
}	