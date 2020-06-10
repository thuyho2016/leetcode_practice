
import java.util.HashSet;
import java.util.Set;

/**
 * The complexity of that algorithm is O(n^m), where:
 *  n - the length of a string.
 *  m - number of permutations.
 */
public class NearbyWords {

    public static void main(String[] args) {
        System.out.println(nearbyWords("gi"));
    }

    static Set<String> nearbyWords(String input) {
        char[] letters = input.toCharArray();
        Set<String> nearbyPermutations = nearbyPermutations(letters, 0);
        Set<String> words = new HashSet<>();
        for (String pw : nearbyPermutations) {
            if (isword(pw)) {
                words.add(pw);
            }
        }
        return words;
    }

    private static Set<String> nearbyPermutations(char[] letters, int index) {
        if (index >= letters.length) {
            HashSet<String> strings = new HashSet<>();
            strings.add("");
            return strings;
        }

        Set<String> subWords = nearbyPermutations(letters, index + 1);
        
        Set<Character> nearbyLetters = getNearbyChars(letters[index]);

        return permutations(subWords, nearbyLetters);
    }

    private static Set permutations(Set<String> subWords, Set<Character> nearbyLetters) {
        Set permutations = new HashSet<>();

        for (String subWord : subWords) {
            for (Character letter : nearbyLetters) {
                permutations.add(letter + subWord);
            }
        }
        return permutations;
    }


    //Put all nearby  character in Hashset
    private static Set<Character> getNearbyChars(Character character) {
        HashSet<Character> characters = new HashSet<>();
        if (character == 'g') {
            characters.add('g');
            characters.add('h');
            characters.add('f');
        } else {
            characters.add('i');
            characters.add('o');
            characters.add('k');
        }
        return characters;
    }

    static boolean isword(String word) {
        return word.equals("go") || word.equals("hi");
    }
}