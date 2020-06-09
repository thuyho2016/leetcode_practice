package design;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*  
 
 * * * * * * * * * * * * * * * * * * * * *
 Design Logging system - Oracle interview
 CRUD
 We need some external APIs for user to put logs
 APIs:
  1. putLog   : Save log to the service for a specific user. 
  2. getLog . : Get logs for the specific user. return List<Log>
  3. updateLog : (optional)
  4. deleteLog : (optional)
  
  Internal APIs
  1. getNumberOfCalls : Get number of external api calls in the last 5 mins
  2. getRecentLogs: (optional) Get all logs from all users in the last 5 mins.
  
Example: 
    get number of API calls in the last 5 mins. 
    time starts with 0;
    putLog(String messages, timestamp)
    getLog(timestamp)
    updateLog(timestamp currentTime, String userId, String singleMessages)
    
    
    putLog("msg", 0); 
    putLog("msg2", 1);
    getLog(3);
    
    updateLog(4, "id", "msg");
    
    getNumberOfCalls(5); // = 4
    getNumberOfCalls(6); // = 3
    
    Explanation:
    From min 0 to min 5, there are 4 external API calls (putLog, putLog, getLog, updateLog)
    From min 1 to min 6, there are 3 exteral API calls (putLog, getLog, updateLog)
    
    
 */

public class LogSystem {
  public class Log {
    Timestamp time;
    String userId;
    String LogMessage;
    
    
	public Log() {
	}

	public Log(Timestamp time, String userId, String logMessage) {
		super();
		this.time = time;
		this.userId = userId;
		LogMessage = logMessage;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
  }

  LinkedList<Log> counter = new LinkedList<>();
  
  public void putLog(String userId, String message, Timestamp currentTime) {
	  	Log logMessage = new Log();
        Date date = new Date();
        Timestamp curTime = new Timestamp(date.getTime());
        logMessage.setTime(curTime);
        logMessage.setUserId(userId);
        counter.add(logMessage);
  
  }
  
  public List<Log> getLog(String userId) {
 
  
  }
  
  
  public void updateLog(Timestamp timestamp, String userId, String message) {
      if (message != null && userId != null) {
    	  Log l = new Log(timestamp, userId, message);
    	  counter.add(l);
      }

  }
    
  public int getNumberOfCalls(Timestamp time) {
    int counterNumber = 0;
        while (counter.hasNext()) {
          if (counter.get().time > curTime - 5 ){
        	  counterNumber++;
          }
          
          return counterNumber;
        }
  }
    
  public static void main(String[] args) {
  
  }
}

