

//Definition for an interval.
public class Interval {
	  int start;
	  int end;
	  
	  public Interval() {
		  start = 0;
		  end = 0;
	  }
	  
	  public Interval(int s, int e) {
		  start = s;
		  end = e;
	  }
	  
	  public Interval merge(Interval o) {
		  return new Interval(Math.min(start, o.start), Math.max(end, o.end));
	  }
}