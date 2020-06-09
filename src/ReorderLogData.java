import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* 
 * 937. Reorder Log Files
 * https://leetcode.com/problems/reorder-log-files/
 * 
You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.

We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, 
with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

Example 1:
 Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 
 Output ["g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1","zo4 4 7"]
 
 
The rules are:

1. Letter-logs come before digit-logs;
2. Letter-logs are sorted alphanumerically, by content then identifier;
3. Digit-logs remain in the same order.

Steps:
1. Iterate through Array String of logs and add numeric/digits logs to one list and letter logs to another list

2. Sort the letter list by using  Collections.sort

  Example: "g1 act car" and "ab1 off key dog"
  
   - compare second part letters after " "  [act car]  and    [off key dog[
   - compare first element:  g1 and ab1 
    
3. Add digit log to letter list -  letterList.addAll(digitList);

 */

public class ReorderLogData {
	
	//Prefer this solution: use  Comparator to compare letterList
	
	 public static String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) return logs;
        int len = logs.length;
        
        List<String> letterList = new ArrayList<>();
        List<String> digitList = new ArrayList<>();
        
        for (String log : logs) {
        	
        	String[] splited = log.split(" ");  //[a1, 9, 2, 3, 1]
        	
        	char c = splited[1].charAt(0);
            
        	if (c >= '0' && c <= '9') {   // is digit . Else is letter
                digitList.add(log);
            } else {
                letterList.add(log);
            }
        }

        //2. Sort the letter list
        Collections.sort(letterList, new LetterCompare()); 
          
        
        System.out.println("Letter List: " + letterList.toString());
        System.out.println("Digit List: " + digitList.toString());
        
        
        letterList.addAll(digitList);
        
        String[] ans = new  String[len];
        int i = 0;
        for(String s: letterList){  // convert List String to Array String
            ans[i++] = s;
        }
        return ans;	        

    }
	
	public static class LetterCompare implements Comparator<String> {
		 @Override
         public int compare(String a, String b){
			 
			//get index of " "
         	int index1 = a.indexOf(" "); ////Returns the index within this string of the first occurrence " "
         	int index2 = b.indexOf(" ");
         	
         	String s1 = a.substring(index1 + 1); //off key dog  
         	String s2 = b.substring(index2 + 1); //act car
         	
             int value = s1.compareTo(s2);
             
             System.out.println(s1 + " compareTo? " + s2); 
             
             System.out.println( "--> compare the first part: " + a.substring(0, a.indexOf(" ")) + " with " + b.substring(0, b.indexOf(" ")) );
             		
             if(value == 0)
             {	 System.out.println("SAME");
             	//a8 act car ,  g1 act car - compare log based on ID
             	return a.substring(0,index1).compareTo(b.substring(0, index2)); //a8 and g1
             
             } else { // value < 0 if a is less b, value > 0 if a is greater than b
             	System.out.println("NOT SAME");
             	return value;
             }
         }
	}
	 
	 
	 //Way 2: O(nlogn) for time and O(1) for space
	 public static String[] reorderLogFiles2(String[] logs) {
	 
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] strs1 = s1.split(" ");  //[g1, act, car]
                String[] strs2 = s2.split(" ");  //[a1, 9, 2, 3, 1]
                
                // Both of 2nd element are difit
                if (Character.isDigit(strs1[1].charAt(0)) && Character.isDigit(strs2[1].charAt(0))) return 0;
                // 2nd element of string 1 is letter && 2nd element of string 2 is digit
                if (Character.isLetter(strs1[1].charAt(0)) && Character.isDigit(strs2[1].charAt(0))) return -1;
               
                if (Character.isDigit(strs1[1].charAt(0)) && Character.isLetter(strs2[1].charAt(0))) return 1;

                String log1 = s1.substring(s1.indexOf(" ") + 1);
                String log2 = s2.substring(s2.indexOf(" ") + 1);
                
                if (log1.equals(log2)) {
                    return strs1[0].compareTo(strs2[0]);
                }
                return log1.compareTo(log2);
            }
        });
        return logs;
    }
	 
	
	 public static void main(String args[])
	 {
		  	
		  String[] lines = { "a write code", "d debug code", "b improve design skill", "c review binary tree",
				  "e hello java", "f hello algorithms"};
		//  String[] result =reorderLogFiles(lines);
	    //  System.out.println( "Reordered log data: " + Arrays.toString(result));
	     //[d debug code, f hello algorithms, e hello java, b improve design skill, c review binary tree, a write code]
		  
		  
		  String[] logs = {"a1 9 2 3 1", "g1 act car","zo4 4 7", "ab1 off key dog","a8 act zoo"};
		  String[] answer = reorderLogFiles(logs);
		  System.out.println(Arrays.toString(answer));
	 }
}