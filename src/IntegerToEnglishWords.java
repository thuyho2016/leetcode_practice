
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
 273. Integer to English Words 
 Convert a non-negative integer to its English words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:
Input: 123
Output: "One Hundred Twenty Three"

Example 2:
Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Example 3:
Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Example 4:
Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

** Problem2: Write a function to convert a string number into an integer

Input: one thousand four hundred and thirteen 
Output: 1413


Runtime: 1 ms, faster than 100.00%

Memory Usage: 35.9 MB

 */

public class IntegerToEnglishWords {
	
	public static void main (String[] args)
    {
		int num =  123;
		//Output: "One Hundred Twenty Three"
		System.out.println("String out: "+ numberToWords(num));
		
	    num =  1250;
	    System.out.println("String out: "+ numberToWords(num)); //One Thousand Two Hundred Fifty
    }		
			
    // use recursive    
	public static String numberToWords(int num) {
        if(num ==0) return "Zero";
        if(num <10) return intToStr(num);
        if( num < 20) return tenTo19(num);
        
        if(num <100){
            int x = num / 10;
            int y = num % 10;
            if(y==0) return tenth(x);
            return tenth(x) + " " + intToStr(y); //99 = ninety nine
        }
        
        if(num < 1000){  //3 number of 0
            int x = num / 100;
            int y = num % 100;
            if(y==0) return intToStr(x) + " Hundred";
            return intToStr(x) + " Hundred " + numberToWords(y);
        }
        
        if(num < 1000000){ //6 number of 0 , input = 1250
            int x = num /1000;   //1
            int y = num % 1000;     //250
            if(y==0) return numberToWords(x) + " Thousand";
            return numberToWords(x) + " Thousand " + numberToWords(y);
        }
        
        if(num < 1000000000){ // 9 number of 0
            int x = num / 1000000;
            int y = num % 1000000;
            if(y==0) return numberToWords(x) + " Million";
            return numberToWords(x) + " Million " + numberToWords(y);
        }
        
        int x = num/1000000000;
        int y = num % 1000000000;
        if(y==0) return numberToWords(x) + " Billion";
        return numberToWords(x) + " Billion " + numberToWords(y);
    }
    
    public static String intToStr(int i){
        if(i==1) return "One";
        if(i==2) return "Two";
        if(i==3) return "Three";
        if(i==4) return "Four";
        if(i==5) return "Five";
        if(i==6) return "Six";
        if(i==7) return "Seven";
        if(i==8) return "Eight";
        return "Nine";
    }
    
    public static String tenTo19(int i){
        if(i==10) return "Ten";
        if(i==11) return "Eleven";
        if(i==12) return "Twelve";
        if(i==13) return "Thirteen";
        if(i==14) return "Fourteen";
        if(i==15) return "Fifteen";
        if(i==16) return "Sixteen";
        if(i==17) return "Seventeen";
        if(i==18) return "Eighteen";
        return "Nineteen";
    }
    
    public static String tenth(int i){//30, 40, 50,60,70,80,90
        if(i==2) return "Twenty";
        if(i==3) return "Thirty";
        if(i==4) return "Forty";
        if(i==5) return "Fifty";
        if(i==6) return "Sixty";
        if(i==7) return "Seventy";
        if(i==8) return "Eighty";
        return "Ninety";
    }
    

    String[] ONES = {"", "One","Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] TEENS = {"Ten","Eleven","Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] TENS = {"Twenty","Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    public String numberToWords2(int num) {
      if (num == 0) return "Zero";
      String numString = "";
        if (num / 1000000000 % 1000 > 0)
          numString += numberToWords(num / 1000000000) + " Billion ";
        if (num / 1000000 % 1000 > 0)
          numString += numberToWords(num / 1000000 % 1000) + " Million ";
        if (num / 1000 % 1000 > 0)
          numString += numberToWords(num / 1000 % 1000) + " Thousand ";
        if (num / 100 % 10 > 0)
          numString += numberToWords(num / 100 % 10 % 1000) + " Hundred ";  
      
        if (num / 10 % 10 > 1)
          numString += TENS[num / 10 % 10 - 2];
              
        if (num / 10 % 10 == 1)
          numString += TEENS[num % 10];
        else {
          if (num / 10 % 10 > 1) numString += " ";
          numString += ONES[num % 10];
        }
      
        return numString.trim();
    }
}
	

	