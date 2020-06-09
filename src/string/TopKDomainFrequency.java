package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 *  Amazon Interview: Display top K of a list domains which has highest frequency
 *  Input:  "amazon.com/shopping",
		     "facebook.com",
		     "www.google.com/mail/compose",
		     "www.facebook.com/feed",
		     "www.facebook.com",
		     "www.yahoo.com",
		     "www.yahoo.com/news"
		     
		     
 *  
 * Output = ['www.facebook.com', 'www.yahoo.com']
 * 
 * Time Complexity: O(NlogN), where N is the length of words. 
 * We count the frequency of each url in O(N) time, then we sort the given words in O(NlogN) time.
 * Space Complexity: O(N),
 * 
 * 
 */
import java.util.PriorityQueue;
public class TopKDomainFrequency {
	
	 //prefer this
	  public static List<String> findTopKDomains_Queue( List<String> urls, int k) {
		    if (urls == null || urls.size() ==0 ) return null;
		    
		    HashMap<String, Integer> hm = new HashMap<String, Integer>();		    
		    
		    for (String url: urls) {
		    
		    	if(url.contains("/")) {
		    		int idx = url.indexOf("/");
		    		url = url.substring(0,idx);  // assume removed shopping from www.amazon.com
		    	}
		    	
		        hm.put(url, hm.getOrDefault(url, 0) + 1);  // value is frequency 5 
		    }
		    
		    //hm = {www.yahoo.com=2, www.facebook.com=3, www.amazon.com=1, www.google.com=1}		    
		   // sort hash by value 
		   PriorityQueue<String> q = new PriorityQueue<>((a,b) -> hm.get(b) - hm.get(a));		  
		   q.addAll(hm.keySet());   // sorted list of keySet - [www.facebook.com, www.yahoo.com, www.amazon.com, www.google.com]
		  
		   //After sort, queue = [www.facebook.com, www.yahoo.com, www.amazon.com, www.google.com]
		   List<String> result = new ArrayList<>();
		   while (result.size() < k) {     	
	        	String key = q.poll();
	            result.add(key); // add key 1, 2 to list -  [www.facebook.com, www.yahoo.com]	
	        }
	        return result;
		   
		    
		  }
		
	  
	  public static List<String> findTopKDomains( List<String> urls, int k) {
	    if (urls == null || urls.size() ==0 ) return null;
	    
	    HashMap<String, Integer> hm = new HashMap<String, Integer>();		    
	    
	    for (String url: urls) {
	    
	    	if(url.contains("/")) {
	    		int idx = url.indexOf("/");
	    		url = url.substring(0,idx);  // assume removed shopping from www.amazon.com
	    	}
	    	
	        hm.put(url, hm.getOrDefault(url, 0) + 1);  // value is frequency 5 
	    }
	    
	    //hm = {www.yahoo.com=2, www.facebook.com=3, www.amazon.com=1, www.google.com=1}
	    
	    // get list of keySet - [www.yahoo.com, www.facebook.com, www.amazon.com, www.google.com]
	    List<String> frequencyList = new ArrayList<String>(hm.keySet());
	   
	    // compare values
	    Collections.sort(frequencyList, (k1, k2) -> hm.get(k1).equals(hm.get(k2)) ?
               
	    		             k1.compareTo(k2) : hm.get(k2) - hm.get(k1));
	    
	   //After sort, [www.facebook.com, www.yahoo.com, www.amazon.com, www.google.com]
	  
	    //top k
	    return frequencyList.subList(0, k);  //[www.facebook.com, www.yahoo.com]	
	    
	  }
	  
		
	   
		  public static void main(String[] args) {
		    List<String> urls = Arrays.asList( 
		     "www.amazon.com/shopping",
		     "www.facebook.com",
		     "www.google.com/mail/compose",
		     "www.facebook.com/feed",
		     "www.facebook.com",
		     "www.yahoo.com",
		     "www.yahoo.com/news" );
		      
		   // List<String> res = findTopKDomains(urls, 2);  // Output = ['www.facebook.com', 'www.yahoo.com']
		    List<String> res = findTopKDomains_Queue(urls, 2); 
		    System.out.println(res);
		  }
		}