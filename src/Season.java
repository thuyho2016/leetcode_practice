
import java.util.Arrays;
import java.util.function.Function;

public class Season {
 private String name ;
 private int[] temp;
 private int amplitude;
 
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int[] getTemp() {
	return temp;
}
public void setTemp(int[] temp) {
	this.temp = temp;
}
public int getAmplitude() {
	return amplitude;
}
public void setAmplitude(int amplitude) {
	this.amplitude = amplitude;
}
 
 public  Season getAmplitudeDiff(Season season) {
	 int[] temps = season.getTemp();
	 Arrays.sort(temps);
	 Arrays.stream(temps).forEach(i -> System.out.println(i));
	 int diff = temps[temps.length-1] -temps[0];
	
	System.out.println("Name :" + season.getName() + "  Diff :" + diff);
	 season.setAmplitude(diff);
	 return season;
 }
 
 
}
