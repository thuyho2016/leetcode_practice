
/* 258. Add Digits
 * https://leetcode.com/problems/add-digits/
 * 
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Input: 38
Output: 2 

Explanation: The process is like: 3 + 8 = 11, then 1 + 1 = 2. 
             Since 2 has only one digit, return it.
Follow up:
Could you do it without any loop/recursion in O(1) runtime?


Steps:
            
 If num is a single digit ( 0 -> 9), then return. 
 Else:  
   repeat split number, then add together until single digit: 1 + 1 = 2   

To split:
 1. To get 3 from 38, use divided by 10:  num / 10 = 38 / 10 = 3
 2. To get 8 from 38, use % :  num % 10 = 8
 3. then Add together = num / 10 + num % 10  = 3 + 8 = 11
 
 Repeat until single digit 1 + 1 = 2

 */

public class AddDigits {
   
  
   //add all its digits until the result has only one digit.
    public static int addDigits(int num) {
    
        if (num < 10 ) return num; // single digit
        
        else {
           // go inside while loop if num /10  is not 0  
            while (num / 10 != 0) {  //e.g 11 / 10 = 1, 10 / 10 = 1
            	num = num % 10 + num / 10; // repeat it until add is single digit
            }
        }
        return num;
      
	}
    
    /**
    use recursive
    Let nums = 123 -> 1 + 2 + 3 = 6
    Step 1-> 123 % 10  =  3 + ( send 123/10 to next step )
    Step 2-> 12 % 10  =  2 + ( send 12/10 to next step )
    Step 3-> 1 % 10  = 1 + ( send 1/10 to next step )
    Step 4-> 0 algorithm stops ( return 0 if ( num ==0)
    */    
	public static int addDigits2(int num) {
		if ( num == 0) return 0;
		return (num % 10 + addDigits( num / 10) );
	}
	
	// without using loop and recursive
	//using Reminder 9
	public static int addDigits3(int num) {
        if(num < 10)
            return num;
        
        if(num % 9 == 0) 
            return 9;
        
        return num % 9; // 38 % 9 = 2 ( 38 / 9 = 4, 38 - 36 = 2 )
    }

    
   public static void main(String[] args) {
   	int sum = addDigits(38);
   	System.out.println("Single digit: " + sum);
   	
   	sum = addDigits(9);
   	System.out.println("Single digit: " + sum); // 9
   	
   	System.out.println("Recursive: " + addDigits2(123));
   	
   	System.out.println("Recursive: " + addDigits3(38)); //6
   }
}
	
	