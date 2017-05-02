package co.uk.epicguru.packer;

import java.io.File;

import org.apache.commons.io.filefilter.IOFileFilter;

import co.uk.epicguru.packing.PackingSettings;

public class PackerFilter implements IOFileFilter {

	private PackingSettings settings;
	
	public PackerFilter(PackingSettings settings) {
		this.settings = settings;
	}

	@Override
	public boolean accept(File file) {
		
		if(settings.ignoredFiles.isEmpty()){			
			return true;
		}else{
			return !settings.ignoredFiles.contains(file.getName());
		}
	}

	@Override
	public boolean accept(File dir, String name) {
		return false;
	}

}
