

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataCenterUtil {
	
   private static DataCenterUtil instance = new DataCenterUtil();
    
    private static Map<String, GMTOffSetRange> thisMap = new LinkedHashMap<String, GMTOffSetRange>();

    public static DataCenterUtil getInstance() {
        return instance;
    }
    
    //DC to timezone range mapping
    public void populateMap() {
		thisMap.put("PM", new GMTOffSetRange(-720, -360));
		thisMap.put("VA", new GMTOffSetRange(-359, -60));
		thisMap.put("LN", new GMTOffSetRange(-60, 299));
		thisMap.put("SG", new GMTOffSetRange(300, 720));
	}	
	
    public String getDataCenter(int gmtOffSet, List<String> dataCentres) {
		int minFromZone=0;
		int minimum = Integer.MAX_VALUE;
		String closestDC = "";
		populateMap();
		if (dataCentres == null || dataCentres.size() == 0) {
			return "data Centre list is null";
		}
		if (dataCentres.size() == 1)
			return dataCentres.get(0);

		for (String dc : dataCentres) {
			if (thisMap.containsKey(dc)) {
				GMTOffSetRange tz = thisMap.get(dc);
				
				if (tz.inThisTimeZone(gmtOffSet)) {
					return dc;
				} else {
					minFromZone = Math.min(nearestDiff(gmtOffSet,tz.lowerTime+1),nearestDiff(gmtOffSet,tz.upperTime));
					if(minFromZone < minimum) {
						minimum = minFromZone;
						closestDC = dc;
					}
				}

			}
		}
		return closestDC;
	}
	
	public int nearestDiff(int disp,int range) {
		int diff = Math.abs(disp-range);
		if(diff>720) return(720-Math.abs(diff-720));
		
		return diff;
	}
	
	public static void main(String args[]) {
   	 ArrayList<String >dataCenterList = new ArrayList<String>(Arrays.asList("PM", "VA"));
   	 //gmtOffSet = -120
   	 String dataCenterID = DataCenterUtil.getInstance().getDataCenter(-120, dataCenterList);
   	 System.out.println("DataCenterID: " + dataCenterID);
   	 
   	//gmtOffSet = 120
   	 dataCenterID = DataCenterUtil.getInstance().getDataCenter(120, dataCenterList);
   	 System.out.println("DataCenterID: " + dataCenterID);
   	 
   	//gmtOffSet = 560
   	 dataCenterID = DataCenterUtil.getInstance().getDataCenter(560, dataCenterList);
   	 System.out.println("DataCenterID: " + dataCenterID);
   }
	
}
	
	

