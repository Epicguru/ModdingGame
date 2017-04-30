package co.uk.epicguru.IO;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class IO {

	private static String drive;
	private static String directory;

	public static void refresh(){

		// Drive, for example "C:\" or "D:\"
		IO.drive = new File("/").getAbsolutePath();

		// I dunno, D:\Dev\Something\Bla bla\Game Dir\
		IO.directory = new File("'").getAbsolutePath().substring(0, new File("'").getAbsolutePath().length() - 1);
	}

	public static String getWorkingPath(){
		return IO.directory;
	}

	public static String getDrive(){
		return IO.drive;
	}

	public static void write(File file, final String content){
		boolean simple = false;
		
		if(simple){
			writeToFileSimple(file, content);
		}else{
			writeToFile(file, content);
		}
	}
	
	private static boolean writeToFileSimple(File file, final String line){
		if(file == null){
			return false;
		}
		if(line == null || line.trim().isEmpty()){
			return false;
		}

		try{
			FileUtils.writeStringToFile(file, line, Charset.defaultCharset());
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	private static boolean writeToFile(File file, final String content){

		if(file == null){
			return false;
		}
		if(content == null || content.trim().isEmpty()){
			return false;
		}

		try{

			ArrayList<String> arr = new ArrayList<String>();

			final String sep = "\n";

			for(String s : content.split(sep)){
				arr.add(s);
			}

			FileUtils.writeLines(file, arr, null);	
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}
}
