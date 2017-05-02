package co.uk.epicguru.unpacker;

public class ERRORS {

	public static final String ERROR_ARGS = 
			  "Argument 0 : The full and absolute path of the source file to be unpacked, basically the location of the packed file.\n"
			+ "Argument 1 : The folder to unpack all packed assets to. If it does not exist, it is created. If it does exist, see other arguments for handling.\n"
			+ "Argument 2 : true or false, wheather to delete the unpacking destination if it exists. If true, the destination folder will be deleted. If false, see below.\n"
			+ "Argument 3 : true or false, wheather to replace or ignore any existing extracted assets in the destination folder. If true, files with the same name will be replaced with the newly unpacked ones. If false, then the old files will have priority and will not be replaced.";
	
}
