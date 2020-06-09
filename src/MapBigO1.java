

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

//Big O Notation is the way how to measure a computer algorithm scale as the amount of data involved increases.
//Implement a Map to getValue(int index), setValue(int index, int value), and setAllValues(int value) are all O(1).

//Big O(1) = constant time
//O(N)

public class MapBigO1
{
	private static ArrayList<String> array = new ArrayList<String>();
	private static HashMap<String, String> hm = new HashMap<String, String>();
	
    public static void main (String[] args)
    {
    	setValue ("a", "A");
		setValue ("b", "B");
		
	 	System.out.println("Hash: " + hm);
	 	
		System.out.println("Get value of a: " + getValue ("a")); // A
		System.out.println("Get value of b: " + getValue("b")) ; //B
  
		setAll("X");
		System.out.println("After setAll");
		System.out.println(getValue("a")) ; //X
		System.out.println(getValue("b")); //X
		
		setAll_v2("Y");
		System.out.println(getValue("a")) ; //Y
		System.out.println(getValue("b")); //Y
		
		reverseMap("X");
    }
    
    //create a hash map and an array to hold key
    public static void setValue(String key, String value) {
    	hm.put(key,value);
    	array.add(key);
    }
    
    public static String getValue(String key) {   	
    	if (hm.containsKey(key)) {
    		return hm.get(key); //return Value of key
    	} else {
    		return null;
    	}
    }
    
    //set  newValue for all keys with big O(1)
  	public static void setAll(String newValue) {
        hm.replaceAll((k,v) -> newValue);
        System.out.println("Set new value by using replaceAll: " + hm);
  	}
   	
    //set  value for all keys with big o(n)
	public static void setAll_v2( String value) {
	    //1. for loop
		for (String v : hm.values()) {
	        System.out.println("Each Value: " + v);
	    }
		
	     // 2. using Set<Entry<String, String>
		 Collection values =  hm.values();
	     System.out.println("Get all Values: " + hm.values());
	     
	     //set new value for each Value in Big O(n)
	    Set<Entry<String, String>> entires = hm.entrySet();
	     for(Entry<String,String> ent: entires) {
	    	 hm.put(ent.getKey(), value);
	    	 System.out.println(ent.getKey() + " => " + ent.getValue());
	     } 
	}
	
	 //set  value for all keys with big O(1)
  	public static void reverseMap(String newValue) {
  	     // a=>A, b=>B  --> a=>X, b=>X  --> reverse X=>[a,b]  -->store values in Array
  		
  		for ( String element : array) {
			System.out.println("Elements in arry: " + element);
		}
     
      	Map<String,ArrayList<String>> reverseMap = new HashMap<>();
      	reverseMap.put(newValue, new ArrayList<>());  // X => [a,b]
     	 
    	//assign array of keys to value of HashMap
      	reverseMap.put(newValue, array);
      	System.out.println("Key: " + reverseMap.keySet());
      	 System.out.println("Values: " + reverseMap.values());
  	}
    
}