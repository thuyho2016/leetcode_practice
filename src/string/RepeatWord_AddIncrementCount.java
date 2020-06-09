package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatWord_AddIncrementCount {
	
   public static List<String> usernamesSystem2(List<String> u) {
	    // Write your code here
	   List<String> res = new ArrayList<>();
	   Set<String> set = new HashSet<String>();
	   int count =0;
       
	   for(int i=0; i< u.size(); i++){
            
        	if(!set.contains(u.get(i))) {
		        set.add(u.get(i)); // count to track for same index {cdeo=0}
		        res.add(u.get(i));
	    	} else {
	    		count++;
	    		String temp = u.get(i) + count;
	    		res.add(temp);
	    	}
	   }

    	return res;
   } 
   
   public static void main (String[] agrs) {	
	  List<String> input = Arrays.asList("bob", "alice", "bob", "alice", "bob");
	  System.out.println(usernamesSystem2(input)); 
	  
	  List<String> input2 = Arrays.asList("john", "john", "tom", "john");
	  System.out.println(usernamesSystem2(input2));
   }
}
