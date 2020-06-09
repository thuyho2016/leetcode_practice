package string;
/*
 * 
The java string indexOf() method returns index of given character value or substring. If it is not found, it returns -1. The index counter starts from zero.
 
 he signature of indexOf methods are given below:

For char:
1	int indexOf(int ch)	returns index position for the given char value
2	int indexOf(int ch, int fromIndex)	returns index position for the given char value and from index

For string
3	int indexOf(String substring)	returns index position for the given substring
4	int indexOf(String substring, int fromIndex)	returns index position for the given substring and from index

 */
public class IndexOfExample {
	
	public static void main(String args[]){  
		String s="this is index or last index";
		
		int index1=s.indexOf("is");//Returns the index within this string of the first occurrence of the specified substring
		System.out.println("Index of is " + index1);
		
		int index2=s.indexOf("index");
		System.out.println("Index " + index2);
		
		int index3 = s.lastIndexOf("index");//returns last index of is substring 
		System.out.println("Index3 of is " + index3);
		
		String s1 = "Hi there";
    	System.out.println("Get char t from string: " + s1.charAt(3)); //t
    	System.out.println("Find index of e: " + s1.indexOf('e'));  // 5
    	System.out.println("Find last Index of e: " + s1.lastIndexOf('e')); //7
    	
    	System.out.println(" Length of string: " + s1.length());
    	System.out.println(" Get second word from string: " + s1.substring(3, 8)); //endIndex is length
    	
    	//1. reverse a string 'Hello'. By library method
  //  	StringBuilder sb = new StringBuilder("Hello");
   // 	sb.reverse();
   // 	System.out.println(sb);
    	
    	//2. Reverse by Iterative 'for' loop, start from endIndex
    	String s2 = "Hello";
    	
    	System.out.println("Length of Hello: " + s2.length());  //5
    	for (int i = s2.length() - 1; i >= 0; i--) {  // 4 -> 0
    		//char c = s2.charAt(i);
    		System.out.print(s2.charAt(i));
    	}
  	
	}	
}
