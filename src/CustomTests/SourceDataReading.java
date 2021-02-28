package CustomTests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataModels.OHLC;
import DataModels.Volume;
import Settings.Intervals;
import Util.DateOperations;

public class SourceDataReading {
private final String pathToData = "C:\\Users\\Admin\\Documents\\stonksprices\\historicalohlc\\sp500v2.txt";
private DateOperations dateOperations = new DateOperations();
private List<String[]> data = new ArrayList<>();

public List<OHLC>createChart(){
	List<OHLC> result = new ArrayList<>();
	data.forEach(lineAr->{
		OHLC ohlc = new OHLC(
				buildOpenTime(lineAr[0],lineAr[1]),
				Double.parseDouble(lineAr[2]), 
				Double.parseDouble(lineAr[3]), 
				Double.parseDouble(lineAr[4]), 
				Double.parseDouble(lineAr[5]));
		
		result.add(ohlc);
	});	
	return result;
}


public List<Volume>createVolume(){
	List<Volume> result = new ArrayList<>();
	data.forEach(lineAr->{
	   Volume volume = new Volume(
				buildOpenTime(lineAr[0],lineAr[1]),
				Double.parseDouble(lineAr[6])); 
	   result.add(volume);
		
	});	
	return result;
}


private LocalDateTime buildOpenTime(String part1,String part2) {
	part1 = part1.replace(".", "-");
	return dateOperations.parseLocalDateTime(part1+" "+ part2);
}



public void prepareDate() throws IOException {
	List<String> sourceData = readSourceData();
	sourceData.forEach(line->{
		String[] dataAr = line.split(",");
		data.add(dataAr);
	});
}

private List<String> readSourceData() throws IOException {
	List<String>result = new ArrayList<>();
	File file = new File(pathToData);
	BufferedReader bf = new BufferedReader(new FileReader(file));
	String line;
	while((line=bf.readLine())!=null) 
		result.add(line);
	bf.close();
	return result;	
}

}
