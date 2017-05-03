package co.uk.epicguru.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.uk.epicguru.IO.IO;

public final class SER {

	public static Gson newGson(){
		return new GsonBuilder().setPrettyPrinting().create();
	}
	
	public static void init(){
		
		// Init IO utilities
		IO.refresh();
		
		//Gson gson = newGson();
	}
	
}
