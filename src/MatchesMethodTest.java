
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
find() + start() + end()
find() method searches for occurrences of the regular expressions in the text passed to the Pattern.matcher(text) method, when the Matcher was created. 
If multiple matches can be found in the text, the find() method will find the first, and then for each subsequent call to find() it will move to the next match.

The methods start() and end() will give the indexes into the text where the found match starts and ends.
 end() returns the index of the character just after the end of the matching section. 
 Thus, you can use the return values of start() and end() inside a String.substring() call.
 */


public class MatchesMethodTest {
		
	public static void main(String[] args) {

		
        String text    = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
        String patternString = "Jack";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        
        boolean matches = matcher.matches();
        
        System.out.println("match: " + matches);
        
        String text2   = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food. ";
        String patternString2 = "Jack";

        pattern = Pattern.compile(patternString2);
        matcher = pattern.matcher(text2);
        int count = 0;
        while(matcher.find()) {
            count++;
            System.out.println("found: " + count + " : "
                    + matcher.start() + " - " + matcher.end());
        }
        
        
    }
	
}