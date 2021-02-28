package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataModels.Kijun;
import DataModels.OHLC;
import DataModels.Volume;

public class DataSeriesToDaySplitter {
	private DateOperations dateOperations = new DateOperations();
	
	
	public Map<String,List<OHLC>>splitChartByDays(List<OHLC> chart) {
		Map<String,List<OHLC>>result = new HashMap<>();
		
	    chart.forEach(ohlc->{
	    	String day = dateOperations.extractDate(ohlc.openTime);
	    	if(result.containsKey(day)) {
	    		result.get(day).add(ohlc);
	    	} else {
	    		List<OHLC> candles = new ArrayList<>();
	    		candles.add(ohlc);
	    		result.put(day,candles);
	    	}	    	
	    });		
		
	    return result;
	}
	
	public Map<String,List<Volume>>splitVolumeByDays(List<Volume> volume) {
		Map<String,List<Volume>>result = new HashMap<>();
		
		volume.forEach(val->{
	    	String day = dateOperations.extractDate(val.openTime);
	    	if(result.containsKey(day)) {
	    		result.get(day).add(val);
	    	} else {
	    		List<Volume> vals = new ArrayList<>();
	    		vals.add(val);
	    		result.put(day,vals);
	    	}	    	
	    });		
		
	    return result;
	}
	
	public Map<String,List<Kijun>>splitKijunByDays(List<Kijun> kijun) {
		Map<String,List<Kijun>>result = new HashMap<>();
		
		kijun.forEach(val->{
	    	String day = dateOperations.extractDate(val.openTime);
	    	if(result.containsKey(day)) {
	    		result.get(day).add(val);
	    	} else {
	    		List<Kijun> vals = new ArrayList<>();
	    		vals.add(val);
	    		result.put(day,vals);
	    	}	    	
	    });		
		
	    return result;
	}
	
	
}
