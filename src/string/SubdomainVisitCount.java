package string;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
/*
 * 811. Subdomain Visit Count

 We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.

Example 1:
Input: 
["9001 discuss.leetcode.com"]
Output: 
["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
Explanation: 
We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.

Example 2:
Input: 
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: 
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]

Explanation: 

We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.

 */

import java.util.Set;

public class SubdomainVisitCount {
  private static HashMap<String, Integer> hm = new HashMap<String, Integer>();
  
  /** google.mail.com --> 3 domains are google.mail.com, mail.com, com
   * Find index to ., then getString from index(.) + 1 
   * It will be mail.com
   * 
   * //hm.getOrDefault - Returns the value to which the specified key is mapped or defaultValue if this map contains no mapping for the key
   */
  
  public static List<String> subdomainVisits(String[] cpdomains) {
       // HashMap<String, Integer> hm = new HashMap<String, Integer>();
       // create a hash map - key is domain, count total of domains as value
      
	  for (String cpd : cpdomains) {
     
          String[] cpInfo = cpd.split("\\s+");
          
          int count = Integer.parseInt(cpInfo[0]); //convert to Integer, count = 900
         
          int indexSpace = cpd.indexOf(" "); //3
          
          String tempStr = cpInfo[1];
          System.out.println("Index " + tempStr.indexOf(".")); // index of the first occurrence of the specified substring . is 6
           //google.mail.com --> mail.com, then  mail.com --> com
          
         
          // Add the full domain to map
          // putToMap(tempStr, count);
      
          String[] domains = cpInfo[1].split("\\.");  //[google, mail, com] --> 3 
          
          for (int i = 0; i < domains.length; i++) {
                       
            hm.put(tempStr, hm.getOrDefault(tempStr, 0) + count);  
            
            tempStr = tempStr.substring(tempStr.indexOf(".") + 1); // start index 6 + 1 = 7, use substring method to get sub-string from 7 to end.
            
            System.out.println("tempStr=" + tempStr); // mail.com is string from index 7 to end
          }
      }
        
      //return List of String
      List <String> ans = new ArrayList<String>();
      
      //get pairs of keys and values from HasMap
      Set<Entry <String, Integer>> entries = hm.entrySet();
      for (Entry <String, Integer> entry : entries) {
        ans.add(entry.getValue() + " " + entry.getKey());  //"901 mail.com"
      }
      return ans;
  }
    
  
	private static void putToMap(String key, int value) {
	    if (hm.containsKey(key)) {
	      hm.put(key, value + hm.get(key)); // add previous count for the same key 900 + 50
	    } else {
	      hm.put(key, value);
	    }
	}
	  
    public static void main(String[] args) {
      String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
      System.out.println("List strings: " +  subdomainVisits(cpdomains));
    }
}