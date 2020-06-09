
/*
 * 190. Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 * 

Reverse bits of a given 32 bits unsigned integer.


Example 1:

Input: 00000010100101000001111010011100
Output: 00111001011110000010100101000000
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
Example 2:

Input: 11111111111111111111111111111101
Output: 10111111111111111111111111111111
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 

Note:

Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 

>>> (Unsigned right shift) 

& is bitwise. && is logical.

& evaluates both sides of the operation.
&& evaluates the left side of the operation, if it's true, it continues and evaluates the right side.
The second condition is not evaluated if the first one is false

&, Bitwise AND operator: returns bit by bit AND of input values.
|, Bitwise OR operator: returns bit by bit OR of input values.
^, Bitwise XOR operator: returns bit by bit XOR of input values.
~, Bitwise Complement Operator: This is a unary operator which returns the one’s compliment representation of the input value, i.e. with all bits inversed.


int a = 4;
int b = 7;
System.out.println(a & b); // prints 4
//meaning in an 32 bit system
 00000000 00000000 00000000 00000100
 00000000 00000000 00000000 00000111
 ===================================
 00000000 00000000 00000000 00000100
 * 
 */


public class ReverseBits 
{
	//the operator ‘>>’ is signed right shift
	public static int reverseBits(int n) {
		if (n == 0) return 0;
		
		int result = 0;
		
		for (int i = 0; i < 32; i++) {
			result <<= 1;        //shift result to the left by one res = res << 1
			
			result ^= (n & 1);  //res += n & 1;
			n >>= 1;            //shift n to right by one 
		}
		return result;
	}
	
	/**
	 * 
	We will start with rightmost bit and check whether it is set or not.

	To Check whether a bit at i th position is set or not we check num & (1 << n), if it turns out to be 0 then i th bit is not set.

	Then we will see if 0th bit is set, if it is then we will set the 31st bit, if 1st bit is set then we will set 30th bit, so on and so forth.

	 */
	public int reverseBits2(int n) {
        int x = 0;
        
        for(int i = 0; i < 32; i++) {
        	
            if((n & (1 << i)) != 0) {
                x |= (1 << (31 - i));
            }
        }
        return x;
    }
	
	public static void main(String[] args)
	{
	//	int nums = 00000010100101000001111010011100;
		
	//	System.out.println(reverseBits(nums));
	}
}
