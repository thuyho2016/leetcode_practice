package string;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/*
 * "abdbba"
 * found duplicate characters 
 * 
 * 
 * Solution 1 . using Recursive and substring,
 * Solution 2. using HashMap
 */
public class CountDuplicateCharacters {	
	
	//using Recursive and substring
    //count number repetition of characters from String by recursive	
	public static int countChars(String s, char c) {  // "abdbba"
		  if (s.length() == 0)
		    return 0;
		  else if (s.charAt(0) == c)
		    return 1 + countChars(s.substring(1), c); // if found , add 1 and continue recursive
		  else
		    return countChars(s.substring(1), c);  // s.substring(1) start from index 1 , so s = bdbba, s = dbba, s = bba, s = ba,...
	
	}
	
	// use charAt(i)
	public static int countChars2(String s, char c) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				count++;
			}
		}
		return count;
	}
	
	
	// use HashMap - Optimize time O (n + m)
	public static List<Integer> countDuplicateChars(String s, char[] c) {
		List<Integer> result = new ArrayList<Integer>();
		
		// create a Map that hold Char as key, count as Integer value 
		HashMap<Character, Integer> map = new HashMap<>(); // {a = 2, b = 3}
		
		//check map contains Key
		for  (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i)  ))  {// add element to HashMap, set value = 1
				map.put(s.charAt(i), 1);
			} else {
				int count = map.get(s.charAt(i)); //
				map.put(s.charAt(i), count + 1); // if key found duplicate, then increase value = count + 1
			}
		}
		
		//print HashMap
/*		for (Character key: map.keySet()) {
			System.out.println("key = " + key);
		}
		
		for (Integer value: map.values()) {
			System.out.println("value = " + value);
		}
		
		//print both key and values
		Set<Entry<Character, Integer>> set = map.entrySet();
		for (Entry<Character, Integer> entry : set) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	*/	
		//add count of duplicate values to List result 
		for ( int j = 0; j < c.length; j++) { 
			//int count;
			if (!map.containsKey(c[j])) {
				result.add(0);  //
			} else {  //map contains key a , b
				System.out.println("Map contains key " + c[j] + " ,add value to result: " + map.get(c[j]));
				result.add(map.get(c[j]));  //map.get(key) Returns the value for the specified key 
			}
		}
		
		return result;
	}
	
		
	public static void main (String[] agrs) {
		
		String s = "abcbba";
		char c = 'b';
		int count = countChars(s, c);
		System.out.println("Count Number of character 'b' from string: " + count);
		
		count = countChars2(s, c);
		System.out.println("Count Number of character 'b' from string: " + count);
		

		char[] chars = {'a', 'b'};
		List<Integer> count2 = countDuplicateChars(s, chars);
		System.out.println("Counts of a, b : " + count2); //[2,3]
		
		char[] chars2 = {'c'};
		List<Integer> count3 = countDuplicateChars(s, chars2);
		System.out.println("Counts of c : " + count3); //1
		
		
		// to check runtime 
		String s2 = "abcdbba";
		 
	}
}
