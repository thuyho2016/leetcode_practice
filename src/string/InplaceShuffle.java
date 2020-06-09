package string;
import java.util.Random;

/*
 * The shuffle must be "uniform," meaning each item in the original array must have the same probability of ending up in each spot in the final array.

* Assume that you have a method getRandom(floor, ceiling) for getting a random integer that is >= floor and <= ceiling.

 
Suppose our array had 3 elements: [a, b, c]. This means it'll make 3 calls to getRandom(0, 2). That's 3 random choices, each with 3 possibilities. 
So our total number of possible sets of choices is 3*3*3=27. Each of these 27 sets of choices is equally probable.

But how many possible outcomes do we have? If you paid attention in stats class you might know the answer is 3!, which is 6


a, b, c
a, c, b
b, a, c
b, c, a
c, b, a
c, a, b

 */
public class InplaceShuffle {
	  

	  private static Random rand = new Random();

	  
	  private static int getRandom(int floor, int ceiling) {
	      return rand.nextInt((ceiling - floor) + 1) + floor;
	  }

	 //common first idea is to walk through the array and swap each element with a random other element
	  public static void naiveShuffle(int[] array) {

	      // for each index in the array
	      for (int firstIndex = 0; firstIndex < array.length; firstIndex++) {

	          // grab a random other index
	          int secondIndex = getRandom(0, array.length - 1);

	          // and swap the values
	          if (secondIndex != firstIndex) {
	              int temp = array[firstIndex];
	              array[firstIndex] = array[secondIndex];
	              array[secondIndex] = temp;
	          }
	      }
	  }
	  
	  public static void main(String[] args) {
		  System.out.println(getRandom(0, 2)); //27 ???
	  }
}
