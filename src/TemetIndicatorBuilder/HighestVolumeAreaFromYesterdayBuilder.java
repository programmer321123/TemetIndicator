package TemetIndicatorBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import DataModels.HighestVolumeArea;
import Settings.Intervals;
import Util.DateOperations;

public class HighestVolumeAreaFromYesterdayBuilder {
	private DateOperations dateOperations = new DateOperations();
	
	public Map<String, Map<Intervals,HighestVolumeArea>> buildHighestVolumeFromYesterday(Map<String, Map<Intervals,HighestVolumeArea>> originalMap){
		 Map<String, Map<Intervals,HighestVolumeArea>> highestVolumeAreasYesterday = new TreeMap<>();
		 List<Map<Intervals,HighestVolumeArea>> values = new ArrayList<>(originalMap.values());
		 List<String> keys = new ArrayList<>(originalMap.keySet());

		 for(int i=1;i<keys.size();i++) {
			 highestVolumeAreasYesterday.put(keys.get(i), modifyTimeRangeOfYesteradyAreas(values.get(i-1)));
			 
		 }
		
		return highestVolumeAreasYesterday;
	}


	private  Map<Intervals,HighestVolumeArea> modifyTimeRangeOfYesteradyAreas( Map<Intervals,HighestVolumeArea> map){
		map.forEach((k,v)->{
			v.openTime = dateOperations.getBeggingOfNextDay(v.openTime);
			v.closeTime = dateOperations.getBeggingOfNextDay(v.openTime);
		});	
		return map;
	}

}
