
/*Given an integer, write a function to determine if it is a power of three.
n = b * b * b.
If n is a power of 3, then 3^X == N
 if 9 is a power of 3, then 3^3 = 3 * 3 * 3 = 9. Remainder is 0

9 % 3  = 0 
   9 /3 = 3 
3 % 3 = 0 
   3 / 3 = 1
1 % 3 = 1 , not go inside while loop.   

if n == 1, return true
   
*/

public class PowerOfThree{

	
	public static boolean isPowerOfThree(int n) {

        if ( n < 1) {
        	return false;
        } else {
        	while (n % 3 == 0) {
        		n = n / 3;   
        	}
        }
		return n ==1;
    }
	
	public static boolean isPowerOfThree_v2(int n) {
		if (n <= 0)
			return false;
		double r = Math.log10(n) / Math.log10(3);
		if (r % 1 == 0)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		int n = 6;
		System.out.println(isPowerOfThree(n));
		int n2 = 3;
		System.out.println(isPowerOfThree(n2));
		int n3 = 9;
		System.out.println(isPowerOfThree(n3));
	}

}
	
	