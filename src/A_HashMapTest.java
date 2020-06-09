
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Map.Entry;

/*
 Differences between HashMap and Hashtable?
 
 HashMap is not	synchronized
 HashMap allows one null key and any number of null values.

Hashtable is synchronized
Hashtable does not allow null keys or values.  
	
Note: HashMap does not allow duplicate keys, but it allows to have duplicate values
       
      HashSet: does not allow duplicate elements. It allows null value, 

containsKey(Object key): Returns true if the map contains key .
get(key) method is used to return the value. Returns null if there is no mapping
entrySet(): to get keys and values
keySet():   to get keys


map.computeIfAbsent(key, k -> new Value(f(k)));

Map<Character, Integer> map = new HashMap<>(); // to put each character from t and increase count
		for (char c : t.toCharArray())
			map.put(c, map.getOrDefault(c, 0) + 1);  // {s=1, z=1}


*/

public class A_HashMapTest {
  
    /** iList =  {{9,3}, {11, 8}, {8,7}, {4,6}, {11,9}, {9,1}, {11,4}}
     After sorting, [[11, 8], [11, 9], [11, 4], [9, 3], [9, 1], [8, 7], [4, 6]]
    HashMap:
    4 = [6]
	8 = [7]
	9 = [3, 1]
	11 = [8, 9, 4]
   
     */
	public static HashMap<Integer,List<Integer>> convertToHashMap(int[][] iList) {
	
	    Arrays.sort(iList, (a,b) -> b[0] - a[0]);	
		 
/*		Arrays.sort(iList, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2 ) {
				return o2[0] - o1[0];
			}
		});
	*/	
		HashMap<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();

		 List<Integer> children = null;  
		 
		//convert iList to hashmap
		for (int i = 0; i < iList.length; i ++) {
			for (int j = 1; j < iList[0].length; j ++) {  //[i,1] = 8, 9, 10
				
				if (!map.containsKey(iList[i][0])) {
					children = new ArrayList<Integer>(); 
				} 
				children.add(iList[i][j]);
				map.put(iList[i][0],children);  //{9=[3], 11=[8]}
			}
		}
		
		map.forEach ((k,v) -> { System.out.println( k + " = " + v); 
			
		});
		
		return map;
	}


    //method to convert List of List to 2D array
	 public String[][] toArray(List<List<String>> list) {
	    String[][] r = new String[list.size()][];
	    int i = 0;
	    for (List<String> next : list) {
	       r[i++] = next.toArray(new String[next.size()]);
	    }
	    return r;
	 }
	 
	 
	 
	
	public static <K> void main(String[] args) {
		
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		
		 // example to access element from HashSet
        Iterator<Integer> it = set.iterator();
        while(it.hasNext() ){ //Returns true if the iteration has more elements.
            System.out.println(it.next());
        }
        
        
		int[][] pairs = {{9,3}, {11, 8}, {8,7}, {4,6}, {11,9}, {9,1}, {11,4}};
		//sorted in order: [[11, 8], [11, 9], [11, 4], [9, 3], [9, 1], [8, 7], [4, 6]]
		HashMap<Integer, List<Integer>> convert = convertToHashMap(pairs);
		
	
		HashMap<String, String> hm = new HashMap<String, String>();
        //add key-value pair to hashmap
        hm.put("monday", "exercise");
        hm.put("tuesday", "study code");
        hm.put("wednesday","pratice code");
        
        System.out.println("HashMap: " + hm);
        
        if (hm.get("monday") != null) {
        	System.out.println("Get value by get(key): " + hm.get("monday"));
        }
        
        System.out.println("Key thursday is not in HashMap? " + !hm.containsKey("thursday"));        
    	
        System.out.println("Size of hashmap: " + hm.size());
        
        //provide value for new key which is absent
        hm.computeIfAbsent("thursday", k -> "review");
        
        // get all keys from HashMap by keySet()
       // Set<String> keys = hm.keySet();
        System.out.println("Get all Keys by keySet(): " + hm.keySet());       
        
        for(String key: hm.keySet()){
            System.out.println("Key: " + key);
        }
        
        // get all values by values()
       // Collection<String> values =  hm.values();
        System.out.println("Get all Values by values(): " +hm.values());
        
        for(String val: hm.values()){
            System.out.println(val);
        }
       
	    // get keys and values by entrySet()
        Set<Entry<String, String>> entires = hm.entrySet();
        for(Entry<String,String> ent:entires){
            System.out.println(ent.getKey() + " => " + ent.getValue());
        }
	
        //get key and value by forEach
        hm.forEach((key, value) -> { if (value == "wednesday" ) {  
										System.out.println(key + " = " + value);  //[4, 5, 7]
        							 }
		});
        
        Map<String, Boolean> shoppingList = new HashMap<String, Boolean>();
        shoppingList.put("Handbads", true);
        shoppingList.put("table", false);
        shoppingList.put("phone", true);
        shoppingList.put("Tivi", Boolean.TRUE);
        
        System.out.println(shoppingList.toString());
        //isEmpty
        shoppingList.isEmpty();
        shoppingList.remove("table");            // remove key 
        shoppingList.replace("phone", false);    //replace value of key
        System.out.println(shoppingList.toString());
		
        Map<String, String> map = new HashMap<>();
        map.put("url1", "www.amazon.com");
        map.put("url2", "www.google.com");
        
       //to print both key and value
       Set<Entry<String, String>> setEnt = map.entrySet();
       
       for(Entry<String,String> ent: setEnt){
           System.out.println(ent.getKey() + " = "+ ent.getValue());
          
           if (ent.getKey() =="url2") {
        	   	System.out.println("Value of url:  "+  ent.getValue());
           }
           	
       }
     
       
       // 1. using Iterator to access value
       
       System.out.println("UseIterator:");
       Iterator<String> itr = map.values().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
 
	   map.forEach((key, value) -> { if (key == "1" ) { 
			
			System.out.println(key + " = " + value);
		}}
	   );
       
		  
        // 3. Java 8 - Collection.iterator() + Iterator.forEachRemaining()
        map.values().iterator().forEachRemaining(System.out::println);
 
        // 4. Java 8 - Collection.stream() + Stream.forEach()
        map.values().stream().forEach(System.out::println);
 
        // Java 8 - Stream.of() + Collection.toArray() + Stream.forEach()
        Stream.of(map.values().toArray()).forEach(System.out::println);
 
        // 5. Convert to String
        System.out.println(map.values().toString());
 
        // Java 8
        Stream.of(map.values().toString()).forEach(System.out::println); 
        
		
		IntStream.range(1,9).forEach(n -> { System.out.println(n); });
		
    }
	

	
}
