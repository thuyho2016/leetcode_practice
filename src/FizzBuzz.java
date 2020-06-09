import java.util.ArrayList;
import java.util.List;

/*
 * 412. Fizz Buzz ( easy level)
 * https://leetcode.com/problems/fizz-buzz/
 *
Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",   (1 * 3 --> Fizz)
    "4",
    "Buzz",   (1 * 5 -- Buzz) 
    "Fizz",   (2 * 3 --> Fizz)
    "7",
    "8",
    "Fizz",    (3 * 3 --> Fizz)
    "Buzz",
    "11", 
    "Fizz",    (4 * 3 --> Fizz)
    "13",
    "14",
    "FizzBuzz"  ( 3 * 5 --> FizzBuzz)
]

Algorithm

1. Initialize an empty answer list.
2. Iterate on the numbers from 1 ... N.
   For every number, if it is divisible by both 3 and 5, add FizzBuzz to the answer list.

  Else, Check if the number is divisible by 3, add Fizz. ( Example  3 / 3 = 1 , 6 / 3 = 2, ...
  Else, Check if the number is divisible by 5, add Buzz.  ( 5 / 5 = 1, 10 / 5 = 2, ...
  Else, add the number.


Time Complexity: O(N)
Space Complexity: O(1)

 */
public class FizzBuzz {
	
	//nums divisible by 3, 9, 15, ..
	 public static List<String> fizzBuzz(int n) {
		 List<String> result = new ArrayList<String>();
		 
		 for (int num = 1; num <= n; num++) {
			 
		 	boolean divisibleBy3 = (num % 3) == 0;
		 	boolean divisibleBy5 = (num % 5) == 0;		 
			
		 	//IMPORTANT - need to check first to get FizzBuzz
		 	if (divisibleBy3 && divisibleBy5) { // // Divides by both 3 and 5, add FizzBuzz  
		 	
				result.add("FizzBuzz");
		 	} else if (divisibleBy3) { //Divides by 3, add Fizz		 	   
		 		result.add("Fizz");
		 		
		 	} else if (divisibleBy5){ // divides by 5, add Buzz
		 		result.add("Buzz");
		 		
			} else { 
				result.add(Integer.toString(num)); // Not divisible by 3 or 5, add the number
		        
			}
		 }
		return result;
	        
	 }
	 
	 public static void fizzBuzz2(int n) {
		    // Write your code here
		    List<String> result = new ArrayList<>();
	         // n = 3
	        for ( int i = 1; i <= n; i++) {
	            boolean divideBy3 = (i % 3) == 0;
	            boolean divideBy5 = (i % 5) == 0; 
	            if ( divideBy3)
	                result.add("Fizz");
	            else if (divideBy5)
	                 result.add( "Buzz");
	            else if (divideBy3 && divideBy5) {
	                result.add("FizzBuzz");
	            } 
	            else {  // print 1, 2
	                result.add(Integer.toString(i));
	            }
	        } 

	        System.out.println(result);  

	    }
	 
	 public static void main(String[] args) {
		 List<String> out = fizzBuzz(15);
		 System.out.println(out);
		 
		 fizzBuzz2(15);
	 }
	   
	 
}
