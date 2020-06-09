

public class HackerRank_ConstructLetter {
	public static boolean canConstructLetter(String text, String note) {
	    // Write your code here

	        if (note == null || note.length() == 0) return true;
	        if (text == null || text.length() == 0) return false;

	        int[] counts = new int[256];

	        for (char c : text.toCharArray()) {
	        	int pos =	c - 'a'; 
	      //   int pos =	Math.abs(c - 'a'); //-13
	           counts[pos] += 1;  // convert character to int and store to array count. if character is repeated, increase by 1
	        }

	        for (int j =0; j < note.length(); j++) {
	            if (counts[note.charAt(j) - 'a'] == 0) { 
	                return false;
	            } else
	                counts[note.charAt(j) - 'a']--; // decrease  Eg counts= [2,0,0,..] will be [1,0,0,...]
	        }
	        return true;
	}
	
	public static void main (String[] args)
    {
		
		// System.out.println(canConstructLetter("aa", "aab"));
		 System.out.println(canConstructLetter("the quick brown fox jumps over the lazy dog", "visa"));
    }
}
