package string;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


//Map<K,V> is an interface, HashMap<K,V> is a class that implements Map

public class FindMostUsedWords {
	 
	
	public static  List<String> retrieveMostFrequentlyUsedWords(String literatureText, List<String> wordsToExclude) {
        
        List<String> mostUsedWords = new ArrayList<String>();
        //convert String to array
        literatureText = literatureText.replace(".",""); //remove dot
        literatureText = literatureText.replace("'"," "); 
        
        String[] textArry = literatureText.split(" "); // convert to array
       
        mostUsedWords = findDuplicateWords(textArry);
        System.out.println( "Found MostUsedWords: " + mostUsedWords); //[s, and, to, Jack, Jill, cheese]
              
        List<String> list = findWordsNotInExcludeList(mostUsedWords, wordsToExclude);
        
		return list;
    }
	
	public static List<String> findDuplicateWords(String[] words) {
		
		List<String>  duplicateElement = new ArrayList<String>();
		
		// create a Map that hold Char as key, count as Integer value 
		Map<String, Integer> map = new HashMap<String, Integer>(); // {a = 2, b = 3}
		
		//check map contains Key
		for  (int i = 0; i < words.length; i++) {
			int count = 0;
			if (!map.containsKey(words[i].toLowerCase()))  {// add word to HashMap, set value = 1
				map.put(words[i], 1);
			} else {
				count = map.get(words[i].toLowerCase()); //get specific key
				map.put(words[i].toLowerCase(), count + 1); // if word is found duplicate , then increase value = count + 1
				
			}
		}

		map.forEach( (key, value) -> { if (value > 1 ) { 
					duplicateElement.add(key); 
					System.out.println(key + " = " + value);
				}}
		);
	
 
		return duplicateElement;
	}
	
	// return a List with words not in mostUsedWords
	public static List<String> findWordsNotInExcludeList(List<String> mostUsedWords, List<String> wordsToExclude) {
		List<String>  result = new ArrayList<String>();
		
		for (String m: mostUsedWords) {  
			int count = 0;
			for (String ex: wordsToExclude) { 
				if (m.equalsIgnoreCase(ex)) {
					count++;
				} 
			}
			
			if (count == 0) { 
				result.add(m);
			}	
		}
		return result;
	
	}

	
  public static void main(String args[])
  {
	String literatureText = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
	System.out.println(literatureText);
	
	//[and, he, the, to, is, Jack, Jill]
	List<String> wordsToExclude=new ArrayList<String>();
	wordsToExclude.add("and");  
	wordsToExclude.add("he"); 
	wordsToExclude.add("the"); 
	wordsToExclude.add("to"); 
	wordsToExclude.add("is"); 
	wordsToExclude.add("Jack"); 
	wordsToExclude.add("Jill"); 
	
	System.out.println( "words to exclude: " + wordsToExclude);
	
    List<String> result = retrieveMostFrequentlyUsedWords(literatureText, wordsToExclude);
    System.out.println( "Most Used/duplicate words and not in excluded list: " + result); //[s, cheese]
    
    
  }
}