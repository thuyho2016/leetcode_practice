
//Enum is used to define collections of constants

public class EnumTest
{
	public enum Level {
	    HIGH,
	    MEDIUM,
	    LOW
	}
	
	public static void main(String[] args)
	{
		Level l = Level.HIGH;
		System.out.println(l);
		
		for (Level level : Level.values()) {
		    System.out.println(level);
		}
		
		System.out.println(Level.valueOf("LOW"));
	}
}
