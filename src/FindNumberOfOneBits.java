
/*
 *  The 32-bit integer ’11' has binary representation 00000000000000000000000000001011, 
 *  so the function should return 3
 */

public class FindNumberOfOneBits {

	public static void main (String[] agrs) {
		long n = 00000000000000000000000000001011;
		FindNumberOfOneBits number = new FindNumberOfOneBits();
		int bit1 = number.numSetBits2(n);
		System.out.println("Way1: " + bit1);
		
		int bit2 = number.numSetBits2(n);
		System.out.println("Way2: " + bit2);
	}

	
	public int numSetBits(long a) {
		 int binaryNum = 0;
	     int num = (int) a;
	     int sum = 0;
	   
	     while(num > 0){
	       binaryNum = num % 2;
	       num = num/2;
	       sum = sum + binaryNum;
	   }
	    return sum;
    }
	 
	public int numSetBits2(long a) {
        int count=0;
        while(a>0)
        {
            if(a % 2 == 1 )
                count=count+1;
            a=a/2;

	    }
	    return count;
	}
	
}
	
	