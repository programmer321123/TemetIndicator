package TemetIndicatorBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import DataModels.HighestVolumeArea;
import DataModels.OHLC;
import DataModels.Volume;
import Settings.Intervals;
import Util.DataSeriesToDaySplitter;

public class HighestVolumeDataSeriesBuilder {
private HighestVolumeAreaFinder  highestVolumeAreaFinder= new HighestVolumeAreaFinder();	
private DataSeriesToDaySplitter dataSeriesToDaySplitter = new DataSeriesToDaySplitter();

private Map<String, Map<Intervals,HighestVolumeArea>> highestVolumeAreas = new TreeMap<>();


public Map<String, Map<Intervals,HighestVolumeArea>> buildHighestVolumeDataSeries(Map<Intervals,List<Volume>> volumes, Map<Intervals,List<OHLC>> chartsForVolumes) {
	volumes.forEach((interval,v)->{
		Map<String,List<Volume>> volumeSplitedbyDays = dataSeriesToDaySplitter.splitVolumeByDays(v);
		Map<String,List<OHLC>> chartSplitedbyDays = dataSeriesToDaySplitter.splitChartByDays(chartsForVolumes.get(interval));
		
		volumeSplitedbyDays.forEach((day,dataSeries)->{
			HighestVolumeArea area = 	highestVolumeAreaFinder.findHighestVolumeArea(volumeSplitedbyDays.get(day), chartSplitedbyDays.get(day));
			addHighestVolumeArea(day,interval , area);			
		});
	});
	return highestVolumeAreas;
}


private void addHighestVolumeArea(String day,Intervals interval,HighestVolumeArea area) {
	if(!highestVolumeAreas.containsKey(day)) {
	    Map<Intervals,HighestVolumeArea> areasMap = new HashMap<>();
        areasMap.put(interval,area);
		highestVolumeAreas.put(day, areasMap);
		return;
	}	
	highestVolumeAreas.get(day).put(interval, area);
}

}
