package CustomTests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import DataModels.HighestVolumeArea;
import DataModels.Kijun;
import Settings.Intervals;
import TemetScanner.TemetScannerOutput;
import Util.DateOperations;

public class displayOutput {
private final String dir = "C:\\Users\\Admin\\Desktop\\gie³dowe_badania\\TemetIndicator\\";
private DateOperations dateOperations = new DateOperations();


public void displayKijun(List<Kijun> list) throws IOException {
	File file = new File(dir+"kijun.txt");
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	
	for(Kijun kijun:list)
		bw.write(dateOperations.formatDateTime(kijun.openTime) + "	" + String.valueOf(kijun.value).replace(".", ",") + "\n"); 	
	//bw.write(new Gson().toJson(list));
	bw.close();
}

public void displayAreas(Map<String, Map<Intervals,HighestVolumeArea>> highestVolumeAreas) throws IOException {
	File file = new File(dir+"areas.txt");
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));	
	highestVolumeAreas.forEach((k,v)->{
		try {
			bw.write(k + "	"+v.get(Intervals.M5).openTime +"	" + v.get(Intervals.M5).low + "	" +  v.get(Intervals.M5).high);
			bw.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	});
	bw.close();
}


public void displayAreasYesterday(Map<String, Map<Intervals,HighestVolumeArea>> highestVolumeAreas) throws IOException {
	File file = new File(dir+"areasYesterday.txt");
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));	
	highestVolumeAreas.forEach((k,v)->{
		try {
			bw.write(k + "	"+v.get(Intervals.M5).openTime +"	" + v.get(Intervals.M5).low + "	" +  v.get(Intervals.M5).high);
			bw.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	});
	bw.close();
}



	
public void displayScanningResults(List<TemetScannerOutput> scanningResult) throws IOException {
	File file = new File(dir+"scanningResults.txt");
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));	
	scanningResult.forEach((r)->{
		try {
			bw.write(new Gson().toJson(r));
			bw.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	});
	bw.close();
}
	
	
}
