
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/* Given an array of N integers denoting the temperatures on all days of the year, 
 * returns a string with the name of the season with the highest temperature amplitude (following "WINTER", "SPRING", "SUMMER", "AUTUMN")
 * Amplitude is the difference between the highest and lowest temperatures over the given period.
 * 
 * https://gist.github.com/poornan/af6ef1979ebae9e64c8d88a11cde1ff3
 */
public class SeasonHighestAmplitudeTemperature {


    private static final String WINTER = "WINTER";
    private static final String AUTUMN = "AUTUMN";
    private static final String SUMMER = "SUMMER";
    private static final String SPRING = "SPRING";
    private static final String COLON_SEPARATOR = ": ";

    public static void main(String[] args) {

        int[] arg1 = {-3, -14, -5, 7, 8, 42, 8, 3};
        int[] arg2 = {2, -3, 3, 1, 10, 8, 2, 5, 13, -5, 3, -18};
        
       SeasonHighestAmplitudeTemperature solution = new SeasonHighestAmplitudeTemperature();
       System.out.println("Answer for " + solution.solution(arg1));
       System.out.println("Answer for "  + solution.solution(arg2));
    }

    public String solution(int[] arr) {
    //  List<Integer> list = arrayToList(arr);
    	
    	List<Integer> list = new ArrayList<>() ;
    	for (int i =0; i < arr.length; i++) {
    		list.add(arr[i]);
    	}
        
        int numberOfDays = list.size() / 4;
        List<Integer> winter = list.subList(0, numberOfDays);
        List<Integer> spring = list.subList(numberOfDays, numberOfDays * 2);
        List<Integer> summer = list.subList(numberOfDays * 2, numberOfDays * 3);
        List<Integer> autumn = list.subList(numberOfDays * 3, numberOfDays * 4);

        System.out.println(WINTER + COLON_SEPARATOR + winter);
        System.out.println(SPRING + COLON_SEPARATOR + spring);
        System.out.println(SUMMER + COLON_SEPARATOR + summer);
        System.out.println(AUTUMN + COLON_SEPARATOR + autumn);

        TreeMap<Integer, String> seasonAmplitude = new TreeMap<>();                           
        seasonAmplitude.put(getAmplitude(winter), WINTER);
        seasonAmplitude.put(getAmplitude(spring), SPRING);
        seasonAmplitude.put(getAmplitude(summer), SUMMER);
        seasonAmplitude.put(getAmplitude(autumn), AUTUMN);

        System.out.println(getAmplitude(summer));
        return seasonAmplitude.lastEntry().getValue();

    }
    
 /*   private List<Integer> arrayToList(int[] arr)  {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }
 */   
    private int getAmplitude(List<Integer> data) {
        return Collections.max(data) - Collections.min(data);

    }
}
