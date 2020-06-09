package work;

public class GMTOffSetRange {
	int lowerTime;
	int upperTime;
	
	public GMTOffSetRange(int lowerTime,int upperTime) {
		
		this.lowerTime = lowerTime;
		this.upperTime = upperTime;
		
	}
	
	public boolean inThisTimeZone(int time) {
		if (time == -720) time = Math.abs(time);
		if(this.lowerTime < time && time <=this.upperTime){
			return true;
		}
		
		return false;	
	}

}
