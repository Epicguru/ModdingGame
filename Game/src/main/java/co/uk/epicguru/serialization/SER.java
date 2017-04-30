package co.uk.epicguru.serialization;

import java.io.File;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.uk.epicguru.IO.IO;
import co.uk.epicguru.logging.Log;
import co.uk.epicguru.objects.AdvancedObject;

public final class SER {

	public static Gson newGson(){
		return new GsonBuilder().setPrettyPrinting().create();
	}
	
	public static void init(){
		
		// Init IO utilities
		IO.refresh();
		
		Gson gson = newGson();
		
		String test = gson.toJson(new AdvancedObject());
		Log.info(test);
		
		File file = new File(IO.getWorkingPath() + "Test.txt");		
		IO.write(file, test);
	}
	
}
