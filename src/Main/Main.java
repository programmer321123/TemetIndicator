package Main;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import CustomTests.SourceDataReading;
import CustomTests.displayOutput;
import DataModels.OHLC;
import DataModels.TemetIndicator;
import DataModels.Volume;
import Settings.Intervals;
import TemetIndicatorBuilder.TemetIndicatorBuilder;
import TemetScanner.TemetScanner;
import TemetScanner.TemetScannerInput;
import TemetScanner.TemetScannerOutput;

public class Main {

	public static void main(String args[]) {
		System.out.println("Started");
		
		//reading my test data don't look at this
		SourceDataReading sourceDataReading = new SourceDataReading();
		try {
			sourceDataReading.prepareDate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//just basic source data for next steps 
		List<OHLC> chart = sourceDataReading.createChart();
		List<Volume> volume = sourceDataReading.createVolume();
		
		//special builder which calculates everything needed for TemetIndicator
		TemetIndicatorBuilder temetIndicatorBuilder = new TemetIndicatorBuilder();
		Map<Intervals,List<OHLC>> chartsForVolumes = new HashMap<>();
		Map<Intervals,List<Volume>> volumes = new HashMap<>();
		/*
		 mapping data to build temetIndicator
		 for each chartForVolumes element you must put Volume element with same Interval to volumes 	
		 otherwise program will fail
		 this data is used to calculate areas with highest volumes
		
		 
		  to calculate multinterval kijun just give ohlc data with desired kijun interval as
		  1st argument of buildIndicatorForAllChart
		  
		 */
		chartsForVolumes.put(Intervals.M5, chart);
		volumes.put(Intervals.M5, volume);
		/*
		 look at DataModels TemetIndicator to know what are parts of TemetIdicator
		 */		
		TemetIndicator temetIndicator = temetIndicatorBuilder.buildIndicatorForAllChart(chart, volumes, chartsForVolumes);
		//temetIndicator is ready to go on chart
		

		
		/*
		 scannerInput - list of inputs for scanner
		 each element is one instrument to scan 
		 scanner gives result for all Intervals which exist in  temetIndicator passed as argument
		 */
       List<TemetScannerInput>scannerInput = new ArrayList<>();
       scannerInput.add(new TemetScannerInput("InstrumentName",temetIndicator,1500));
              
       TemetScanner temetScanner = new TemetScanner();
       /*
        scanner result list of results
        one output element for one input element - one per instrument
        look at TemetScanner -> TemetScannerOutput to konw sonetens of output element
       */       
       List<TemetScannerOutput> scanningResults = temetScanner.scanMarket(scannerInput);
       
       
       
       //just my test ouptu don't look at this
       displayOutput displayOutput = new displayOutput();
		try {
			displayOutput.displayKijun(temetIndicator.kijun);
			displayOutput.displayAreas(temetIndicator.highestVolumeAreas);
			displayOutput.displayAreasYesterday(temetIndicator.highestVolumeAreasYesterday);
			displayOutput.displayScanningResults(scanningResults);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("finished");
	}
	
	

}
