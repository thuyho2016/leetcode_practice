package string;
/*
 */

public class GreatestCommonDivisorNumber  {
	public static void main (String[] args)
    {
		int[] arr = {2,4,6,8,10};
	    System.out.println("output: " + findGCD(5,arr )); //2
	    
	    int[] arr1 = {2};
	    System.out.println("output: " + findGCD(1,  arr1)); //2
	    
	    System.out.println(gcd_recursive( 4, 2));
	   
    }
	
	
	//Euclid's Algorithm for finding GCD
	public static int findGCD(int num, int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if ( arr.length == 1) return arr[0];
        	
        int ans = arr[0];
	
	    for (int i = 1; i < arr.length ; i++) {
	    	ans = gcd_recursive(arr[i], ans); 
	    }
		return ans;
	}  
		
	public static int gcd_recursive(int a, int b) {
	    if (a == 0)
	        return b;
	   
	   return gcd_recursive(b % a, a); // 6 % 3 = 0
	    
	}  
}