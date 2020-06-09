
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
/*
 * Write a function that takes a year in Gregorian calendar as an integer and returns the day of october on which 
 * Ada Lovelace Day falls, also as an integer. 
 * e.g given 2018, you should return 9 ( since the 9th of October is the second Tuesday in October of 2018)
 * 
 */


public class AdaDay {

    /*
     * Complete the 'ada' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER year as parameter.
     */

    public static int ada(int year) {
    	int number = 0;
    	GregorianCalendar gcal = new GregorianCalendar();
    	System.out.print("Date: "
                + gcal.get(Calendar.MONTH) + " "
                + gcal.get(Calendar.DATE) + ", "
                + gcal.get(Calendar.YEAR)); 
    	
		return number;
    }
    
    public static void main (String[] agrs) {
    	String month[] = { "Jan", "Feb", "Mar", "Apr", 
                "May", "Jun", "Jul", "Aug", 
                "Sep", "Oct", "Nov", "Dec" };
    	
    	int output = ada(2018..);
		System.out.println(output);
		
	
    }
}