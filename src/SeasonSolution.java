import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class SeasonSolution {
	public static void main(String args[]) {
	//	int[] T =  {-3, -14, -5, 7, 8, 42, 8, 3};
		int[] T = {2, -3, 3, 1, 10, 8, 2, 5, 13, -5, 3, -18};
		
		int[] arr1 = Arrays.copyOfRange(T, 0, T.length/2);
		int[] arr2 = Arrays.copyOfRange(T,  T.length/2, T.length);
		
		List<Season> seasons = new ArrayList<Season>();
		
		// winter
		int[] winter= Arrays.copyOfRange(arr1, 0, arr1.length/2);
		Season  winterSeason = new Season();
		winterSeason.setName("WINTER");
		winterSeason.setTemp(winter);
		seasons.add(winterSeason);
				
		// spring
		int[] spring= Arrays.copyOfRange(arr1,  arr1.length/2, arr1.length);
		Season  springSeason = new Season();
		springSeason.setName("SPRING");
		springSeason.setTemp(spring);
		seasons.add(springSeason);		
				
		// summer
		int[] summer= Arrays.copyOfRange(arr2, 0, arr2.length/2);
		Season  summerSeason = new Season();
		summerSeason.setName("SUMMER");
		summerSeason.setTemp(summer);
		seasons.add(summerSeason);
				
				
		// Autumn
		int[] autumn= Arrays.copyOfRange(arr2, arr2.length/2, arr2.length);
		Season  autumnSeason = new Season();
		autumnSeason.setName("AUTUMN");
		autumnSeason.setTemp(autumn);
		seasons.add(autumnSeason);
		
		List<Season> updatedSeasons =seasons.stream()
				.map(season -> season.getAmplitudeDiff(season)).collect(Collectors.toList());
		
		OptionalInt high = updatedSeasons.stream()
		.mapToInt(season -> season.getAmplitude())
		.max();
		
		updatedSeasons.stream()
		.filter(season -> season.getAmplitude() == high.getAsInt())
		.map(season -> season.getName())
		.forEach(name ->  System.out.println("Season is :" + name));
		
		Optional <Season> s = seasons.stream()
				.map(season -> season.getAmplitudeDiff(season))
				.collect(Collectors.maxBy(Comparator.comparing(Season :: getAmplitude)));
		System.out.println("New season is :" + s.get().getName());
						
	}

}


