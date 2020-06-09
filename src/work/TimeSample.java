package work;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
	
public class TimeSample {

	  public static void main(String[] args) {
	    ZonedDateTime z = ZonedDateTime.now(ZoneId.systemDefault());
	    
	    System.out.println(z);
	    
	    OffsetTime ot1 = OffsetTime.now();
	    System.out.println("Current  offset  time: " + ot1);

	    // a zone offset +01:30
	 /*   ZoneOffset offset = ZoneOffset.ofHoursMinutes(1, 30);

	    OffsetTime offsetTime = OffsetTime.of(16, 40, 28, 0, offset);
	    System.out.println(offsetTime);*/
	    
	    Instant instant = Instant.now();
	    System.out.println("Instant time: " + instant.toString());
	    
	  
		ZoneId zoneId = ZoneId.of("GMT");
		System.out.println("ZoneID: " + zoneId);
		
		ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
		String targetStartTime = zdt.toString(); 
		System.out.println("Target date: " + targetStartTime);
		
		targetStartTime = targetStartTime.substring(0, targetStartTime.indexOf("[GMT]"));
		System.out.println("targetStartTime: " + targetStartTime);

		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	     df.setTimeZone(TimeZone.getTimeZone("PST"));
	     String formattedDate = df.format(new Date());
	     System.out.println("formattedDate: " + formattedDate);
	     
	     DateTimeFormatter formatter
         = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSS");
 String text = "2011-10-02 18:48:05.123";
 	LocalDateTime dateTime = LocalDateTime.parse(text, formatter);
 System.out.println(dateTime);
 
	     String text1 = "22019-05-13T00:05:37Z";
	     Timestamp ts = convertStringToTimestamp(text1);
	     System.out.println(ts);
	     
	     System.out.println("Current Time: " + getCurrentTimeInUTC());
	  }
	  
	  public static Timestamp convertStringToTimestamp(String str_date) {
		    try {
		      DateFormat formatter;
		      formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		       // you can change format of date
		      Date date = formatter.parse(str_date);
		      java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

		      return timeStampDate;
		    } catch (ParseException e) {
		      System.out.println("Exception :" + e);
		      return null;
		    }
		  }
	
	  private static final String TIME_ZONE_UTC = "UTC";
      private static final String UTC_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

      /**
       * get current time in UTC timezone format as:
       * yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
       * 
       * @return
       */
      public static String getCurrentTimeInUTC() {
          TimeZone tz = TimeZone.getTimeZone(TIME_ZONE_UTC);
          DateFormat df = new SimpleDateFormat(UTC_TIME_FORMAT);
          df.setTimeZone(tz);
          return df.format(new Date());
      }
}
