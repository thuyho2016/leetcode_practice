
/*
 * 50. Pow(x, n)
 * https://leetcode.com/problems/powx-n/
 * 
 * Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000

Using Divide and Conquer
Time & Space complexity  :O(logn)

 2^10 =
          2^5 * 2^5
    2^2 * 2^3 * 2^2 * 2^3
2^2 * 2^2 * 2 * 2^2 * 2^2 * 2^1

myPow(2,10)  --> pow(2,5)-> pow(2,2) ;  pow(2,3) = pow(2,2) and pow(2,1) -> pow(2,0)


Example : x = 2, n = 8, 2 ^8  = 2^4 * 2^4 = 2^2 * 2^2 = 2^1 * 2^1  *2^1 * 2^1

       2^8
     /     \
   2^4      2^4
  /  \     /  \
2^2  2^2  2^2  2^2
/ \
2^1  2^1 ......

myPow(2,8)
mypow(2,2)
myPow(2,1)
myPow(2,0)


2^-2 = 1/2^2 = 0.25

*/

public class PowerXn {
	
	public static double myPow(double x, int n) {
		if (n == 0){
	        return 1;
	    } else {  
	        double half = myPow(x, n/2);  // when n = 8, myPow(2,4)...recursive, x = 2 , n =1,  half = 1
	        
	        if ( n % 2 == 0){  // remainder = 10 % 2 = 0 (because 10 / 2 = 5), 5/2 = 2   
	            return half * half;
	            
	        } else {  // for odd n:  2^5 ,  n= 5, 5%2 = 1
	            if (n > 0){
	                return x * half * half; // 2^5 = 2 * 2^2 * 2^2
	            } else {  //negative n = -2
	                return half * ( half / x) ; //case 2^-2: n = -1, so 1.0 * ( 1.0 /2) = 0.5 for line 58
	                							// half = 0.5: 0.5 * 0.5 = 0.25 at line 61
	            }                
	        }   
	    }
        
    }
	

	
	public static void main(String[] args) {

	//	System.out.println(myPow(2.00, 10)); // 2^10
		
		System.out.println(myPow(2, 8)); // 2^4
		
	}
}
