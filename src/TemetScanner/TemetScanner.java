package TemetScanner;

import java.util.ArrayList;
import java.util.List;

public class TemetScanner {

	
	public List<TemetScannerOutput> scanMarket(List<TemetScannerInput> input) {
		List<TemetScannerOutput> output = new ArrayList<>();
		input.forEach(inp->
			output.add(new TemetScannerOutput(inp.instrumentName,inp.temetIndicator.getSingals(inp.price)))
		);
		return output;
		
	}
	
	
}
