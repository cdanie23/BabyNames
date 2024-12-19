package edu.westga.cs1302.babynamesgui.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.westga.cs1302.babynamesgui.model.BabyKey;
import edu.westga.cs1302.babynamesgui.model.BabyNameRecord;
import edu.westga.cs1302.babynamesgui.model.FrequencyValue;
import edu.westga.cs1302.babynamesgui.model.GenderType;
import edu.westga.cs1302.babynamesgui.utilities.CommonMessages;

/**
 * The class load file used to load a file :) 
 * @version Spring 2024
 * @author Colby
 */
public class LoadFile {
	private File file;
	
	/**
	 * Instantiates a load file object 
	 * @precondition file != null
	 * @postcondition this.file == file
	 * @param file the file to load from
	 */
	public LoadFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		this.file = file;
	}
	/**
	 * Gets the file
	 * @return the file
	 */
	
	public File getFile() {
		return this.file;
	}
	/**
	 * Sets the file
	 * @precondition file != null
	 * @param file the file to set 
	 */
	
	public void setFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		this.file = file;
	}
	
	/**
	 * Reads date from a file 
	 * 
	 * @return the baby record with all the information from the files passed onto it
	 */
	public BabyNameRecord loadData() {
		
		int lineNum = 1;
		String line = null;
		BabyNameRecord babyRecord = new BabyNameRecord();
		try (Scanner scnr = new Scanner(this.file)) {
			
			while (scnr.hasNextLine()) {
				try {
					
					line = scnr.nextLine();
					
					String [] tokens = line.split(CommonMessages.COMMA_SPLITTER);
					
					String state = tokens[0];
					String genderChar = tokens[1];
					int year = Integer.parseInt(tokens[2]);
					String name = tokens[3];
					int frequency = Integer.parseInt(tokens[4]);
					GenderType gender = null;
					
					if (genderChar.equals("F")) {
						gender = GenderType.FEMALE;
					} else if (genderChar.equals("M")) {
						gender = GenderType.MALE;
					} 
					
					babyRecord.setState(state);
					
					BabyKey key = new BabyKey(name, gender, year);
					FrequencyValue value = new FrequencyValue(frequency);
					
					babyRecord.put(key, value);
					lineNum++;
					
				} catch (Exception exception) {
					
					System.err.println("Error reading file on line " + String.format("%4s", lineNum) + ": " + exception.getMessage() + " [" + line + "]");
				}
			}
		} catch (FileNotFoundException exception) {
			System.err.println("Your selected file was not found :(");
		}
		
		return babyRecord;
	}
}
