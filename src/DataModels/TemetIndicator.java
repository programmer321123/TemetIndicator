package DataModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Settings.Intervals;

public class TemetIndicator {
public List<Kijun> kijun = new ArrayList<>();
public Map<String, Map<Intervals,HighestVolumeArea>> highestVolumeAreas = new TreeMap<>();
public Map<String, Map<Intervals,HighestVolumeArea>> highestVolumeAreasYesterday = new TreeMap<>();
private Map<Intervals,HighestVolumeArea> lastHighestVolumeAreas;

public TemetIndicator(List<Kijun> kijun, Map<String, Map<Intervals, HighestVolumeArea>> highestVolumeAreas) {
	super();
	this.kijun = kijun;
	this.highestVolumeAreas = highestVolumeAreas;
}



public Map<Intervals,Integer> getSingals(double price){
	Map<Intervals,Integer> signals = new HashMap<>();
	extractLastArea();
	lastHighestVolumeAreas.forEach((k,v)->
		signals.put(k, getSignalForArea(price,v))
	);
	return signals;
}


private int getSignalForArea(double price,HighestVolumeArea area) {
	double lastKijun = kijun.get(kijun.size()-1).value;
	if(price>lastKijun&&lastKijun>area.high)return 1;
	if(price<lastKijun&&lastKijun<area.low)return -1;
	return 0;
}



private void extractLastArea(){
 List<String> keys = new ArrayList<>(highestVolumeAreas.keySet());
 lastHighestVolumeAreas = highestVolumeAreas.get(keys.get(keys.size()-1));
}


}
