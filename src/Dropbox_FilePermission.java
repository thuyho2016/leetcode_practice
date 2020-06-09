import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Dropbox - Phone interview
Given the list of Directories and a hashmap to which directories the user has access to. If a parent has permission, all the children will have permissions as well.

Given a directory name, return true or false if the user will have permissions or not.

Example:
Input:
A
----->B
---------->H
---------->I
----->C
---------->D
---------->E
--------------->F
--------------->G

Access to the directory: E & I

Also given the directory name for which we are checking permissions

Ouput:
// given G - return true as the parent E has access
// Given D - return false as neither D nor parent of D has access
// Given H - return false
// Given I - return true

 */
public class Dropbox_FilePermission {
  
	  public static boolean hasAccess(Map<String, String> map, String dir) {
		  
	        while (map.get(dir) != null) {
	        	
	            if (dir.equals("E") || dir.equals("I")) {
	                return true;
	            }
	            if (map.get(dir).equals("E") || map.get(dir).equals("I")) { // map.get(dir) to get value of dir
	                return true;
	            }
	            dir = map.get(dir);
	        }
	        return false;
	  }
	  
	  public static void main (String[] args) 
	  {
		  Map<String, String> childToParent = new HashMap<>();  //Key is child, value is Parent
        childToParent.put("A", null);
        childToParent.put("B", "A");
        
        childToParent.put("H", "B"); //// map.get("H") return B -> // map.get("B") return A --> flase
        childToParent.put("I", "B"); 
        
        childToParent.put("C", "A");
        
        childToParent.put("D", "C"); // map.get("I") return C -->  map.get("C") return A -- > false
        childToParent.put("E", "C");
        
        childToParent.put("F", "E");
        childToParent.put("G", "E"); // map.get("G") return E

        System.out.println(hasAccess(childToParent, "G")); //true
        System.out.println(hasAccess(childToParent, "D")); //false
        System.out.println(hasAccess(childToParent, "H")); //false
        System.out.println(hasAccess(childToParent, "I")); //true

    }
}
