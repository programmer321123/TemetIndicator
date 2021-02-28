package TemetIndicatorBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import DataModels.Kijun;
import DataModels.OHLC;
import Util.CustomComparator;

public class KijunCalculator {
private final int chartSectionLength = 26;
private CustomComparator customComparator = new CustomComparator();

	public List<Kijun> calculateKijun(List<OHLC>chart){
		List<Kijun>kijun = new ArrayList<>();
		
		for(int i = chartSectionLength;i<chart.size();i++)
			kijun.add(calcKijunForCandle(chart.get(i),chart.subList(i-chartSectionLength,i)));
		
		return kijun;
	}
	
	private Kijun calcKijunForCandle(OHLC currentCandle,List<OHLC> pastChartSection) {
		double kijunVal = 0.5* (findMaxPriceFromChartSection(pastChartSection)+findMinPriceFromChartSection(pastChartSection));
		return new Kijun(currentCandle.openTime, kijunVal);
	}
	
	
	private double findMaxPriceFromChartSection(List<OHLC> chartSection){
		List<OHLC> sorted = chartSection.stream()
				.sorted((ohlc1,ohlc2)->customComparator.compareNumbers(ohlc1.high, ohlc2.high))
				.collect(Collectors.toList());
		return sorted.get(sorted.size()-1).high;
	}
	
	private double findMinPriceFromChartSection(List<OHLC> chartSection){
		List<OHLC> sorted = chartSection.stream()
				.sorted((ohlc1,ohlc2)->customComparator.compareNumbers(ohlc1.low, ohlc2.low))
				.collect(Collectors.toList());
		return sorted.get(0).low;
	}
}
