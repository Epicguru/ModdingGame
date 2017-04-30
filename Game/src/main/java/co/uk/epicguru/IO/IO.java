package co.uk.epicguru.IO;

import java.io.File;

public class IO {

	private static String drive;
	private static String directory;
	
	public static void refresh(){
		
		// Drive, for example "C:\" or "D:\"
		String startingPoint = new File("'").getAbsolutePath();
		IO.drive = startingPoint.substring(0, startingPoint.length() - 1);
		
		startingPoint = new File("\\").getAbsolutePath();
		IO.directory = startingPoint.substring(0, startingPoint.length() - 1);
	}
	
	public static String getWorkingPath(){
		return IO.directory;
	}
	
}
