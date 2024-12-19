package edu.westga.cs1302.babynamesgui.datatier;

import java.io.File;

import java.io.PrintWriter;

import edu.westga.cs1302.babynamesgui.model.BabyKey;
import edu.westga.cs1302.babynamesgui.model.BabyNameRecord;
import edu.westga.cs1302.babynamesgui.utilities.CommonMessages;

/**
 * The class save file used to write all data to a file for reusability later
 * 
 * @version Spring 2024
 * @author Colby 
 */

public class SaveFile {
	private File file;
	
	/**
	 * Instantiates a file saver object 
	 * @precondition file != null
	 * @param file the file to write all data to
	 */
	public SaveFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null");
		}
		this.file = file;
	}
	/**
	 * Writes all tokens to a file separated by commas
	 * @param record the record to retrieve the data from to write with
	 */
	
	public void fileSave(BabyNameRecord record) {
		try (PrintWriter fileWriter = new PrintWriter(this.file)) {
			for (BabyKey key : record.keySet()) {
				fileWriter.println(record.getState() + CommonMessages.COMMA_SPLITTER + key.getGender().name().charAt(0) + CommonMessages.COMMA_SPLITTER + key.getYear() + CommonMessages.COMMA_SPLITTER + key.getName() + CommonMessages.COMMA_SPLITTER + record.get(key).getFrequency());
				
			}
		} catch (Exception exception) {
			System.err.println("Error reading file: " + exception.getMessage());
		}
		
	}
}
