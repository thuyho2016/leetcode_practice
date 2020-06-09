package string;
import java.util.HashSet;

/*

 */
public class WordMatchKindexInString {
	
	public static String solution(String message, int K) {
       
        String output = "";
        if (K > message.length()) {
        	return message;
        }
        
        HashSet<String> map = new HashSet<String>();
        for(String word: message.replaceAll("[^a-zA-Z]"," ").split(" ")  ) { 
        	map.add(word);
        }
        
       
        String sub = message.substring(0, K);
    	
        for(String word: sub.replaceAll("[^a-zA-Z]"," ").split(" ")  ) { 
        	
    	   	System.out.println("Word: " + word);
    	   	
    	   	if(!map.contains(word)) {
    	   		int len = word.length();
    	   		output = sub.substring(0, sub.length() - len - 1);
    	   	} else {
    	   		output = sub;
    	   	}
        }
        
        return output;
    }
	
	
	public static void main (String[] args)
    {
		//String input = "Codility We test coder";
		String input ="The quick brown fox jumps over the lazy dog";
		String result = solution(input, 39);
		//String result = solution(input, 14);
		System.out.println("Output: " + result); //5
		
		
	
    }
}
