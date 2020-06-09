import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * 535. Encode and Decode TinyURL
 * https://leetcode.com/problems/encode-and-decode-tinyurl/
 
 TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl 
 and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

https://leetcode.com/discuss/interview-question/124658/Design-a-URL-Shortener-(-TinyURL-)-System/

References: http://blog.gainlo.co/index.php/2016/03/08/system-design-interview-question-create-tinyurl-system/


https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/
A Better Solution is to use the integer id stored in the database and convert the integer to a character string that is at most 6 characters long

A URL character can be one of the following

A lower case alphabet [‘a’ to ‘z’], total 26 characters
An upper case alphabet [‘A’ to ‘Z’], total 26 characters
A digit [‘0′ to ‘9’], total 10 characters
There are total 26 + 26 + 10 = 62 possible characters.

Steps:
1. Design tiny URL
  Design a system that takes big URLs like “http://www.geeksforgeeks.org/count-sum-of-digits-in-numbers-from-1-to-n/”
 and converts them into a short 6 character URL. It is given that URLs are stored in the database and every URL has an associated integer id.

http://tinyurl.com/<alias_hash> and alias_hash is a fixed length string.

To begin with, let’s store all the mappings in a single database. 
A straightforward approach is using alias_hash as the ID of each mapping, which can be generated as a random string of length 7.

 we can first just store <ID, URL>. When a user inputs a long URL “http://www.google.com”, 
 the system creates a random 7-character string like “abcd123” as ID and inserts entry <“abcd123”, “http://www.gainlo.co”> into the database.
In the run time, when someone visits http://tinyurl.com/abcd123, we look up by ID “abcd123”
 and redirect to the corresponding URL “http://www.google.com”.
 
 one way to optimize this is to use incremental IDs. Every time a new URL is inserted, we increment the ID by 1 for the new entry. 
 We also need a hash function that maps each integer ID to a 7-character string. If we think each string as a 62-base numeric,
 the mapping should be easy.
 
 Storage cost: Each entry is stored as <ID, URL> where ID is a 7-character string. Assuming max URL length is 2083 characters, 
 then each entry takes 7 * 4 bytes + 2083 * 4 bytes = 8.4 KB. If we store a million URL mappings, we need around 8.4G storage.
 
 Multiple machines
 a single machine is not capable to store all the mappings. How do we scale with multiple servers?
 
 The more general problem is how to store hash mapping across multiple machines.
 if you want to store a huge amount of key-value pairs across multiple instances, you need to design a lookup algorithm that 
 allows you to find the corresponding machine for a given lookup key.
 
 For example, if the incoming short alias is http://tinyurl.com/abcd123, based on key “abcd123” the system should know which 
 machine stores the database that contains entry for this key
 
 A common approach is to have machines that act as a proxy, which is responsible for dispatching requests to corresponding backend stores 
 based on the lookup key. Backend stores are actually databases that store the mapping. 
 They can be split by various ways like use hash(key) % 1024 to divide mappings to 1024 stores.
 
*/

public class TinyURL  {
	
	public static void main (String[] args)
    {
		int n = 1234; 
	    String shortUrl = idToShortURL(n);
	    System.out.println("Generated id to shortURL :" +idToShortURL(n));
	    System.out.println("Generated shortURL to ID :" + shortURLtoID(shortUrl));
	    
	    String longUrl = "https://leetcode.com/problems/design-tinyurl";
	    String sUrl = encode(longUrl);
	    System.out.println("Encode longUrl to shortUrl :" + sUrl );
	    System.out.println("Decode shortUrl to longUrl:" + decode(sUrl));
    }
	
	//use Ramdom
	static Map<Integer, String> map = new HashMap<>();
	static Random r = new Random();
	static int key = r.nextInt(Integer.MAX_VALUE);
	
	 // Encodes a URL to a shortened URL.
    public static String encode(String longUrl) {
        while (map.containsKey(key)) {
        	key = r.nextInt(Integer.MAX_VALUE);
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public static String decode(String shortUrl) {
    	return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", ""))); 
    	//after replace, Integer.parseInt(1735565885) --> key
    }
    
    
	//approach variable-length encoding - Generate short url
	public static String idToShortURL(int n) 
	{
	    // Map to store 62 possible characters
	    String chars =  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
	    char [] map = chars.toCharArray();
	    
	    StringBuffer shorturl = new StringBuffer();
	  
	    // Convert given integer id to a base 62 number
	    while (n > 0)
	    {
	    	//store actual character in shortUrl
	        shorturl.append(map[ n % 62]);
	        n = n / 62;
	    }
	  
	    // Reverse shortURL to complete base conversion
	    return shorturl.reverse().toString();

	}
	  
	// Function to get integer ID back from a short url
	public static int shortURLtoID(String shortURL)
	{
	     int id = 0; // initialize result
	  
	    // A simple base conversion logic
	    for (int i=0; i < shortURL.length(); i++)
	    {
	        if ('a' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'z')
	          id = id * 62 + shortURL.charAt(i) - 'a';
	        
	        if ('A' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'Z')
		          id = id * 62 + shortURL.charAt(i) - 'A' + 26;
	        
	        if ('0' <= shortURL.charAt(i) && shortURL.charAt(i) <= '9')
		          id = id * 62 + shortURL.charAt(i) - '0' + 52;
	    }
	    return id;
	}
	

}