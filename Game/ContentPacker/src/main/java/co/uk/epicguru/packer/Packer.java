package co.uk.epicguru.packer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.StringBuilderWriter;

import co.uk.epicguru.logging.Log;
import co.uk.epicguru.packing.PackingSettings;
import co.uk.epicguru.packing.ProgressMeter;

public final class Packer {

	/*
	 * What we want to achieve : 
	 * 1. Have a portable, reusable and flexible system.
	 * 2. Pack as many assets of any type as needed into just one file.
	 * 3. Ensure that it is possible to recreate perfectly the previous data structure.
	 * 
	 * NOTE - Basically just making a zip file, but customized for game devs.
	 */

	private String source, destination;
	private final String FILE_UNIQUE = "<@#FILE>";
	private final String LINE_SEPARATOR;
	{
        // avoid security issues
        final StringBuilderWriter buf = new StringBuilderWriter(4);
        final PrintWriter out = new PrintWriter(buf);
        out.println();
        LINE_SEPARATOR = buf.toString();
        out.close();
    }
	
	public static void main(String... args){

		/*
		 * Arg 0 - Source folder, from where all sub-folders are packed.
		 * Arg 1 - Destination file, where the whole source directory will be packed.
		 */

		if(args.length != 2){
			throw new PackerArgumentException("There are not the correct amount of arguments! There should be two, but there are" + args.length + ERRORS.ERROR_ARGS);
		}

		Log.info("Packing from '" + args[0] + "' to '" + args[1] + "'");

		Packer packer = new Packer(args[0], args[1]);
		
		packer.pack((file, fileProgress, progress) -> {
			
			// Log progress here
			// ...
			Log.info(progress + "%");
			
		}, null); // Default settings

		Log.info("Finished packing all assets!");
	}

	public Packer(String source, String destination){
		this.source = source;
		this.destination = destination;
	}

	private PackingSettings getDefaultSettings(){
		return new PackingSettings();
	}

	public String getSource(){
		return this.source;
	}

	public String getDestination(){
		return this.destination;
	}

	private void check(File source, File dest){
		// SOURCE - Exists, is directory and can read
		if(!source.exists()){
			throw new PackerArgumentException("The source directory does not exist!");
		}
		if(!source.isDirectory()){
			throw new PackerArgumentException("The source directory is not a directory?!?!");
		}
		if(!source.canRead()){
			throw new PackerArgumentException("The source directory cannot be read from!");
		}

		// DESTINATION - Make new file if does not exist, delete old if does and make sure we can write
		if(dest.exists()){
			try {
				FileUtils.forceDelete(dest);
			} catch (IOException e) {
				throw new PackerStateException("Exception deleting old destination file! - " + e.getMessage());		
			}
		}
		try {
			boolean worked = dest.createNewFile();
			if(!worked){
				throw new PackerStateException("Failed to create new destination file!");					
			}
		} catch (IOException e) {
			throw new PackerStateException("Exception creating destination file! - " + e.getMessage());
		}
		if(dest.isDirectory()){
			throw new PackerArgumentException("The destination passed is a directory, not a file! It should be a file, dummy!");
		}
		if(!dest.canWrite()){			
			throw new PackerStateException("Cannot write to newly created destination file!");
		}
	}

	private ArrayList<File> getFilesFromSource(File source, PackingSettings settings){
		ArrayList<File> files = new ArrayList<File>();

		for(File file : FileUtils.listFiles(source, new PackerFilter(settings), new PackerFilter(settings))){
			files.add(file);
		}

		return files;
	}
	
	private String getPathFrom(File base, File subFile){
		String basePath = base.getAbsolutePath();
		String subFilePath = subFile.getAbsolutePath();
		
		return subFilePath.substring(basePath.length());
	}
	
	private void packFiles(File parent, ArrayList<File> files, File destination, ProgressMeter m) throws Exception{
		/*
		 * Here is the plan:
		 * 1. Load files into memory one at a time...
		 * 2. Write that file to the destination.
		 * 3. Close destination.
		 * 4. Move on to next file.
		 * 
		 *  Furthermore, we need a way of recreating the structure, so we will save the file as follows:
		 *  TODO
		 *  
		 *  For now, lets just mash all the bytes into one file! Yay!
		 */
		
		StringBuilder str = new StringBuilder();
		for(File file : files){
			str.append(getPathFrom(parent, file));
			str.append(this.LINE_SEPARATOR);
		}
		
		FileUtils.writeByteArrayToFile(destination, str.toString().getBytes());
		
		int total = files.size();
		int current = 0;
		for(File file : files){
			// TODO write bytes
		}
	}

	public void pack(ProgressMeter meter, PackingSettings settings){	

		// Init settings, if null.
		if(settings == null){
			settings = this.getDefaultSettings();
		}

		// Make file objects
		File source = new File(this.getSource());
		File dest = new File(this.getDestination());

		// Check both source and destination
		check(source, dest);

		// Get all files to be packed
		ArrayList<File> files = this.getFilesFromSource(source, settings);
		Log.info("Now packing " + files.size() + " files...");
		
		// Pack
		try {
			this.packFiles(source, files, dest, meter);
		} catch (Exception e) {
			throw new RuntimeException(e);
			//throw new PackerStateException("Error whilst packing - " + e.getMessage());
		}
	}
}
