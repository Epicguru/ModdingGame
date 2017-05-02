package co.uk.epicguru.unpacker;

import co.uk.epicguru.packing.PackingSettings;

public class Unpacker {

	public static void main(String... args){
		
		/*
		 * 0 - The file to unpack.
		 * 1 - The folder, existing or not, that should be packed to.
		 * 2 - True or false whether any existing destination folder should be deleted.
		 * 3 - True or false whether any existing files should be overridden or ignored.
		 */
		
		if(args.length != 4){
			throw new UnpackerArgumentException("There are not the correct amount of arguments! There should be four, but there are" + args.length + ERRORS.ERROR_ARGS);
		}
		
		
	}
	
	
	private String source, destination;
	private boolean deleteOld, replaceFiles;
	public Unpacker(String source, String destination, boolean deleteOld, boolean replaceFiles){
		this.source = source;
		this.destination = destination;
		this.deleteOld = deleteOld;
		this.replaceFiles = replaceFiles;
	}
	
	public String getSource() {
		return source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public boolean isDeletingOld() {
		return deleteOld;
	}
	
	public boolean isReplacingFiles() {
		return replaceFiles;
	}
	
	public void unpack(PackingSettings settings){
		if(settings == null){
			settings = new PackingSettings();
		}
		
		
	}
	
}
