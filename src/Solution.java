

public class Solution {
  
    public static int findLargestDiff( int[] A) {
    int j = 0;  // 13
    int max = 0;
    
    for (int i = 1; i < A.length; i++) {  //2 8 5 11
        int diff = A[i] - A[j];     // 2 - 13 =-11, 6,  3, 9
        if ( max < diff) {   
             max = diff;
        }
        j++;
     }
     return max;  // 6

    }

    
    public static void main(String[] args) 
    { 
  
        StringBuffer sb = new StringBuffer("Geeks"); 
        System.out.println(" String buffer  before = " + sb); 
  
        char[] cstr = new char[] { 'f', 'o', 'r', 'G', 'e', 'e', 'k', 's', 
                                   'b', 'e', 'a', 'g', 'e', 'e', 'k' }; 
  
        /* appends the string representation of char array argument to this 
      string buffer with offset initially at index 0 and length as 8 */
        sb.append(cstr, 0, 8); 
  
        // Print the string buffer after appending 
        System.out.println("After appending string buffer = " + sb); 
    } 
    
    
    
}
