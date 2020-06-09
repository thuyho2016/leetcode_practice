import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sort.MyBook;


/* 
 * Example sort
 */

public class ComparatorExample {
	
	//To sort Array, use Arrays.sort 
	public static String sortStrByconvertToArray(String s) {
	    char[] arr = s.toCharArray();
	    Arrays.sort(arr);
	    
	    String newS = new String(arr);
	    return newS;
	    
	  } 
	
	
   //Compare 2 objects - sort start
	public static class IntervalComparator implements Comparator<Object> {

	    public int compare(Object o1, Object o2){
	        Interval i1 = (Interval)o1;
	        Interval i2 = (Interval)o2;
	        return i1.start - i2.start;  //use compareTo if it is String
	    }
	}	

	//sort first number from each array []
    public static int[][] sortList(int[][] input) {
        
        Arrays.sort(input, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        
        return input;

    }
    
    // overloading methods - same method namem but pass different parameter type
    public static boolean compare(int a, int b) {
        if (a == b) return true;
        else return false;
   }

   public static boolean compare(String a, String b) {
       if(a.equals(b)) return true;
       else return false;
   }
   
    public static boolean compare (int[] a, int[] b) {
        if (a == null || b == null)  // checks for null arrays
            return false;
        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) return false;
            }
        }
         return true;
    }
    
    public static void main(String[] agrs) {
    	 //1. sort string
  		System.out.println(sortStrByconvertToArray("acb")); //abc
      
  		//2. Sort array []
  		String[] colors = new String[] {"Blue","Red", "White", "Green"};
		Arrays.sort(colors);
		int i = 0;
		System.out.println("array after sort: " + Arrays.toString(colors));
	/*	for(String c: colors){
			System.out.println("Color " + ++i + " : " + c);
		}
		*/
		//3. To sort a List, use the Collections.sort().
		List<String> fruits = new ArrayList<String>();
		fruits.add("Orange");
		fruits.add("Apple");
		fruits.add("Banana");
		System.out.println("list before sort: " + fruits);
		
		//Collections.sort(fruits);
		System.out.println("list after sort: " + fruits);
		
		//Java 8
		Collections.sort(fruits, (s1, s2) -> s1.compareTo(s2));
		
		//4. sort object by name
		MyBook[] myBooks = {
		        new MyBook("John", 2, "author1", "publisher1"),
		        new MyBook("Marry", 30, "author2", "publisher2"),
		        new MyBook("David", 5, "author3", "publisher3"),
		};
		
		System.out.println("before sort");
		System.out.println(Arrays.asList(myBooks));
		
		Arrays.sort(myBooks, (a, b) -> a.name.compareTo(b.name));
		
		System.out.println("after sorting by name");
		System.out.println(Arrays.asList(myBooks));
		
		
  		//5. sort 2D array [][]
    	int[][] iList = {{9,3}, {11, 8}, {8,7}, {4,6}, {11,9}, {9,1}, {11,4}};
  	    //sort in ascending
  	    Arrays.sort(iList, (a,b) -> Integer.compare(a[0], b[0]));
  	    System.out.println(Arrays.deepToString(iList));	
  	    
    	//sort in descending
  	    Arrays.sort(iList, (a,b) -> b[0] - a[0]);	
  	    System.out.println(Arrays.deepToString(iList));	
  	   	    
    	int[][] input = {{9,3}, {11, 8}, {8,7}, {4,6}, {11,9}, {9,1}, {11,4}}; 
    	System.out.println(Arrays.deepToString(sortList(input)));
		//[[11, 8], [11, 9], [11, 4], [9, 3], [9, 1], [8, 7], [4, 6]]
    	
  	    
  		//[[0, 30],[5, 10],[15, 20]]
  	    Interval i1 = new Interval(0, 30);
		Interval i2 = new Interval(5, 10);
		Interval i3  = new Interval(15, 20);
		Interval[] intervals = {i1, i2, i3};
  	    Arrays.sort(intervals, new IntervalComparator());
  	    System.out.println(Arrays.deepToString(intervals));	
  	    
  	    int num1 = 4;
  	    int num2 = 3;
  	    System.out.println(compare(num1, num2) ? "Same" : "Different");
  	    
  	    String s1 = "hello";
  	    String s2 = "hell";
  	    System.out.println(compare(s1,s2) ? "Same" : "Different");
  	
  	    int[] a = {1,2};
  	    int[] b = {1,4};
  	    System.out.println(compare(a,b) ? "Same" : "Different");
  	    
  	  
	}
}