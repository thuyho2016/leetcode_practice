package design;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Given interface, write implementation those methods
 * 
 * public interface StringBufferVariant {
    String append(String newText);
    char charAt(int index) throws StringIndexOutOfBoundsException;
    int length();
    String toString();
  }
  
  
 * @author thanhho
 *
 */

public class StringBufferVariant {
	 
	  List<String> list = new ArrayList<String>();
	  
	  public void append(String s1) {
	    if (s1 == null ) return;
	   
	    list.add(s1);
	  
	  }
	  
	  public String toString() {
	    if (list == null || list.size() ==0 ) {
	    	return null;
	    } 
	    String res = list.stream().map(Object::toString).collect(Collectors.joining());
	      return res;
		
	  }
	
	  public char charAt(int index) {
		  if(index < 0 || list.size() == 0) return 0;
		  String tmp = toString();
		  return (tmp.charAt(index));
	}
	  
	
	public static void main(String[] args) 
	{ 
		
		StringBufferVariant sbv = new StringBufferVariant();
	
		sbv.append("Hello ");
		sbv.append("World");
		//sbv.append("World");
	
		System.out.println(sbv.toString()); // returns "Hello World"
		System.out.println(sbv.charAt(2));
			        
	}
}
