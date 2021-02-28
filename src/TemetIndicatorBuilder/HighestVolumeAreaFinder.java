package TemetIndicatorBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import DataModels.HighestVolumeArea;
import DataModels.OHLC;
import DataModels.Volume;
import Util.CustomComparator;
import Util.DateOperations;


public class HighestVolumeAreaFinder {
	private CustomComparator customComparator = new CustomComparator();
	private DateOperations dateOperations = new DateOperations();

	public HighestVolumeArea findHighestVolumeArea(List<Volume> volume,List<OHLC> chart) {
		Volume highestVolume = findHighestVolumeVal(volume);
		OHLC highestVolumeCandle = findHighestVolumeCandle(chart,highestVolume.openTime);
		LocalDateTime closeTime = dateOperations.getBeggingOfNextDay(highestVolumeCandle.openTime);
		return new HighestVolumeArea(highestVolumeCandle.openTime,closeTime, highestVolumeCandle.low, highestVolumeCandle.high);
	}
	
	private Volume findHighestVolumeVal(List<Volume> volume) {
		List<Volume> sorted = volume.stream()
				.sorted((volume1,volume2)->customComparator.compareNumbers(volume1.value, volume2.value))
				.collect(Collectors.toList());
		 return sorted.get(sorted.size()-1);
	}
	
	private OHLC findHighestVolumeCandle(List<OHLC> chart,LocalDateTime highestVolumeTime) {
		return chart.stream()
				.filter(ohlc->ohlc.openTime.compareTo(highestVolumeTime)==0)
				.collect(Collectors.toList()).get(0);
	}

}
