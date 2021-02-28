package TemetIndicatorBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import DataModels.HighestVolumeArea;
import DataModels.Kijun;
import DataModels.OHLC;
import DataModels.TemetIndicator;
import DataModels.Volume;
import Settings.Intervals;
import Util.DataSeriesToDaySplitter;
import Util.DateOperations;

public class TemetIndicatorBuilder {
private KijunCalculator kijunCalculator = new KijunCalculator();
private HighestVolumeDataSeriesBuilder HighestVolumeDataSeriesBuilder = new HighestVolumeDataSeriesBuilder();
private HighestVolumeAreaFromYesterdayBuilder highestVolumeAreaFromYesterdayBuilder = new HighestVolumeAreaFromYesterdayBuilder();	

public TemetIndicator buildIndicatorForAllChart(List<OHLC> chartForKijun, Map<Intervals,List<Volume>> volumes, Map<Intervals,List<OHLC>> chartsForVolumes) {
	TemetIndicator temetIndicator = new TemetIndicator(
				kijunCalculator.calculateKijun(chartForKijun),
				HighestVolumeDataSeriesBuilder.buildHighestVolumeDataSeries(volumes, chartsForVolumes)
				);
		
	temetIndicator.highestVolumeAreasYesterday = highestVolumeAreaFromYesterdayBuilder.buildHighestVolumeFromYesterday(temetIndicator.highestVolumeAreas);
		
		return temetIndicator;
	
	}







  
















	
	
}
