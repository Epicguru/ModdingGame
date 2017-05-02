package co.uk.epicguru.packing;

import java.io.File;

public interface ProgressMeter {

	public void progress(File currentFile, float percentageOfFile, float overallPercentage);
	
}
