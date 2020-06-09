
public class Deletion {

      
    // This function removes an element x from arr[] and 
    // returns new size after removal (size is reduced only 
    // when x is present in arr[] 
    static void deleteElement(int arr[], int x) 
    { 
        // Search x in array 
        int i; 
        int n = arr.length;
        for (i=0; i<n; i++) 
            if (arr[i] == x) 
                break; 
   
        // If x found in array 
        if (i < n) 
        { 
            // reduce size of array and move all 
            // elements on space ahead 
            n = n - 1; 
            for (int j=i; j < n; j++) {
                arr[j] = arr[j+1]; 
                System.out.println(arr[j]);
            }
        } 
   
        //return n; 
    } 
      
    // Driver program to test above function 
    public static void main(String[] args) 
    { 
        int arr[] = {11, 15, 6, 8, 9, 10}; 
        int n = arr.length; 
        int x = 6; 
   
        // Delete x from arr[] 
        deleteElement(arr, x); 
   
        System.out.println("Modified array is"); 
        for (int i = 0; i < n; i++) 
            System.out.print(arr[i]+" "); 
          
    } 
} 

