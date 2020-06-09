/*
 * Sum with even number
 * Input: 2, 10
 * Output: 30
 * 
 * Explanation: 2 + 4 + 6 + 8 + 10 = 30
 * 2 * 1 = 2
 * 2 * 2 = 4
 * 2 * 3 = 6
 * 2 * 4 = 8
 * 2 * 5 = 10
 * a * i  where i starts from 1, 2, 3, 4, 5
 */

public class AddEvenNumbersInRange {
	
	public static int addEven(int a, int b) {
	
	    int sum = 0;
	    if (a <= 0 || b <= 0) {
	      return 0;    
	    }
	   
	   for (int i = 1; 2 * i <= b; i ++) {
	      sum += a * i;
	      System.out.println("Add " + sum);
	    }
	   return sum;
	}
	
	public static int addEven2(int a, int b) {
		
	    int sum = 0;
	    int i = 1;
	    
	    if (a <= 0 || b <= 0) {
	      return 0;    
	    }
	   
	   while(a * i <= b) {
	      sum += a * i;
	      i++;
	      System.out.println("Add " + sum);
	    }
	   return sum;
	}

	public static void main (String[] args) {
	   System.out.println("Output " + addEven(2,10));
       System.out.println("Output " + addEven2(2,10)); 
	}
}
