

public class DoubleArray_PrintSquare {
	
	public static void printMultiplationSquare(int n) {
	
	    int square = 0;
	    if (n == 0) {
	      return;    
	    }
	   
	    for (int i = 1; i <= n; i++) {  
	      for (int j = 1 ; j <= n; j++) {  
	    	
	          square = i * j;   
	          System.out.print(square + " ");
	      }
	      System.out.println(" ");
	    }
	}

	public static void main (String[] args) {

	    // 4 x 4
		
	    // 1 2 3 4
	    // 2 4 6 8
	    // 3 6 9 12
	    // 4 8 12 16

        printMultiplationSquare(2); 
	}
}
