package edu.westga.cs1302.babynamesgui.viewmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import edu.westga.cs1302.babynamesgui.datatier.LoadFile;
import edu.westga.cs1302.babynamesgui.datatier.SaveFile;
import edu.westga.cs1302.babynamesgui.model.BabyKey;
import edu.westga.cs1302.babynamesgui.model.BabyNameRecord;
import edu.westga.cs1302.babynamesgui.model.FrequencyValue;
import edu.westga.cs1302.babynamesgui.model.GenderType;
import edu.westga.cs1302.babynamesgui.utilities.RecordDisplay;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * The baby names GUI view model this is used to interact with the model from
 * the view/GUI
 * 
 * @version Spring 2024
 * @author Colby
 */

public class BabyNamesGUIViewModel {
	private SaveFile fileSaver;
	private LoadFile fileLoader;
	private BabyNameRecord record;
	private ListProperty<String> listRecordProperty;
	private StringProperty nameProperty;
	private ListProperty<Integer> listYearProperty;
	private BooleanProperty femaleProperty;
	private BooleanProperty maleProperty;
	private IntegerProperty frequencyProperty;
	private StringProperty stateProperty;
	private RecordDisplay formatRecord;
	private ArrayList<Integer> years;
	private IntegerProperty yearProperty;
	private ObjectProperty<String> selectedObjectProperty;

	/**
	 * Instantiates an instance of the view model
	 */

	public BabyNamesGUIViewModel() {
		this.selectedObjectProperty = new SimpleObjectProperty<String>();
		this.yearProperty = new SimpleIntegerProperty();
		this.years = new ArrayList<Integer>();
		this.record = new BabyNameRecord();
		this.formatRecord = new RecordDisplay();

		this.listRecordProperty = new SimpleListProperty<>(FXCollections.observableArrayList(this.formatRecord));
		this.listYearProperty = new SimpleListProperty<Integer>(FXCollections.observableArrayList(this.years));
		this.nameProperty = new SimpleStringProperty();
		this.femaleProperty = new SimpleBooleanProperty();
		this.maleProperty = new SimpleBooleanProperty();
		this.frequencyProperty = new SimpleIntegerProperty();
		this.stateProperty = new SimpleStringProperty();
	}

	/**
	 * Gets the record of the view model
	 * 
	 * @return the record to view
	 */

	public BabyNameRecord getRecord() {
		return this.record;
	}

	/**
	 * Gets the object property
	 * 
	 * @return the selected object property
	 */

	public ObjectProperty<String> selectedObjectProperty() {
		return this.selectedObjectProperty;
	}

	/**
	 * Gets the state property
	 * 
	 * @return the state property
	 */

	public StringProperty stateProperty() {
		return this.stateProperty;
	}

	/**
	 * Gets the List record property
	 * 
	 * @return the list record property
	 */
	public ListProperty<String> listRecordProperty() {
		return this.listRecordProperty;
	}

	/**
	 * Gets the name property
	 * 
	 * @return the name property
	 */

	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/**
	 * Gets the year list property
	 * 
	 * @return the year list property
	 */

	public ListProperty<Integer> yearListProperty() {
		return this.listYearProperty;
	}

	/**
	 * Gets the year property
	 * 
	 * @return the year property
	 */

	public IntegerProperty yearProperty() {
		return this.yearProperty;
	}

	/**
	 * Gets the female boolean property
	 * 
	 * @return the female boolean property
	 */

	public BooleanProperty femaleProperty() {
		return this.femaleProperty;
	}

	/**
	 * Gets the male boolean property
	 * 
	 * @return the male boolean property
	 */

	public BooleanProperty maleProperty() {
		return this.maleProperty;
	}

	/**
	 * Gets the frequency property
	 * 
	 * @return the frequency property
	 */

	public IntegerProperty frequencyProperty() {
		return this.frequencyProperty;
	}

	/**
	 * Adds a baby through the event fired by the add button
	 */

	private void updateDisplay() {
		this.listRecordProperty.setValue(FXCollections.observableArrayList(this.formatRecord));
	}

	private void updateComboBox() {
		Collections.sort(this.years);
		this.listYearProperty.setValue(FXCollections.observableArrayList(this.years));
	}

	/**
	 * Adds a baby to the list view
	 * 
	 * @return true or false based on if the record was added
	 */

	public boolean addBabyRecord() {

		GenderType gender = null;
		if (this.maleProperty.get()) {
			gender = GenderType.MALE;
		} else if (this.femaleProperty.get()) {
			gender = GenderType.FEMALE;
		}

		BabyKey key = new BabyKey(this.nameProperty.get(), gender, this.yearProperty.getValue());

		FrequencyValue value = new FrequencyValue(this.frequencyProperty.getValue());
		if (!this.record.containsKey(key)) {
			this.record.put(key, value);
		} else {
			return false;
		}
		this.formatRecord.format(this.record);
		this.updateDisplay();
		this.record.keySet().forEach(babyKey -> {
			if (!this.years.contains(babyKey.getYear())) {
				this.years.add(babyKey.getYear());
			}
		});
		this.updateComboBox();
		return this.record.containsKey(key);

	}

	/**
	 * Loads all data from a file
	 * 
	 * @param selectedFile the file to load from
	 */

	public void loadFile(File selectedFile) {
		this.fileLoader = new LoadFile(selectedFile);
		this.record = this.fileLoader.loadData();
		this.formatRecord.clear();
		this.formatRecord.format(this.record);
		this.updateDisplay();
		this.stateProperty.setValue("State: " + this.record.getState());
		this.years.clear();
		this.record.keySet().forEach(babyKey -> {
			if (!this.years.contains(babyKey.getYear())) {
				this.years.add(babyKey.getYear());
			}
		});
		this.updateComboBox();

	}

	/**
	 * Sets the state of the record given the change in the view by the text input
	 * dialog
	 * 
	 * @param name the name to set the state to
	 */

	public void updateState(String name) {
		this.stateProperty.setValue("State: " + name);
		this.record.setState(name);
	}

	/**
	 * Writes to a file
	 * 
	 * @param file the file to write to
	 */

	public void writeToFile(File file) {
		this.fileSaver = new SaveFile(file);
		this.fileSaver.fileSave(this.record);
	}

	/**
	 * Sorts the list view by name and year
	 */

	public void sortByNameYear() {
		this.formatRecord.clear();
		this.formatRecord.formatByNameYear(this.record);
		this.updateDisplay();
	}

	/**
	 * Sorts the list by year and frequency
	 */

	public void sortByYearFrequency() {
		this.formatRecord.clear();
		this.formatRecord.formatByYearFrequency(this.record);
		this.updateDisplay();
	}

	/**
	 * Deletes the record from the list
	 * 
	 * @return true or false based on if the record was removed
	 */

	public boolean deleteRecord() {
		GenderType gender = null;
		String[] tokens = null;
		String name = "";
		int year = 0;
		if (this.selectedObjectProperty.getValue() != null) {
			String selectedRecord = this.selectedObjectProperty.getValue();
			tokens = selectedRecord.split(", ");
			name = tokens[0];
			year = Integer.parseInt(tokens[2].substring(0, tokens[2].indexOf(':')));
			if (tokens[1].equals("F")) {
				gender = GenderType.FEMALE;
			} else if (tokens[1].equals("M")) {
				gender = GenderType.MALE;
			}
		} else {
			name = this.nameProperty.getValue();
			year = this.yearProperty.getValue();
			if (this.femaleProperty.get()) {
				gender = GenderType.FEMALE;
			} else if (this.maleProperty.getValue()) {
				gender = GenderType.MALE;
			}
		}

		BabyKey key = new BabyKey(name, gender, year);
		if (this.record.containsKey(key)) {
			this.record.remove(key);
			this.formatRecord.clear();
			this.formatRecord.format(this.record);
			this.updateDisplay();
			this.years.clear();
			this.record.keySet().forEach(babyKey -> {
				if (!this.years.contains(babyKey.getYear())) {
					this.years.add(babyKey.getYear());
				}
			});
			this.updateComboBox();
			return true;
		} else {
			return this.record.containsKey(key);
		}

	}

	/**
	 * Finds a record
	 * 
	 * @return the record searched for
	 */

	public String searchRecord() {
		String name = this.nameProperty.getValue();
		int year = this.yearProperty.getValue();
		GenderType gender = null;
		if (this.femaleProperty.get()) {
			gender = GenderType.FEMALE;
		} else if (this.maleProperty.getValue()) {
			gender = GenderType.MALE;
		}
		BabyKey key = new BabyKey(name, gender, year);

		if (this.record.containsKey(key)) {
			String recordDisplay = key.toString() + this.record.get(key).toString();
			for (String string : this.formatRecord) {
				if (string.equals(recordDisplay)) {
					return string;
				}
			}
		}
		return null;
	}

	/**
	 * Updates a record's frequency
	 * 
	 * @return true or false based on if the record was updated
	 */

	public boolean updateRecord() {
		String name = this.nameProperty.getValue();
		int year = this.yearProperty.getValue();
		GenderType gender = null;
		if (this.femaleProperty.get()) {
			gender = GenderType.FEMALE;
		} else if (this.maleProperty.getValue()) {
			gender = GenderType.MALE;
		}
		BabyKey key = new BabyKey(name, gender, year);
		int frequency = this.frequencyProperty.getValue();
		FrequencyValue value = new FrequencyValue(frequency);
		if (this.record.containsKey(key)) {
			String recordDisplay = key.toString() + this.record.get(key).toString();
			for (String string : this.formatRecord) {
				if (string.equals(recordDisplay)) {
					this.record.replace(key, value);
					this.formatRecord.format(this.record);
					this.updateDisplay();
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Clears all records
	 */

	public void clearRecords() {
		this.formatRecord.clear();
		this.record.clear();
		this.formatRecord.format(this.record);
		this.updateDisplay();
		this.updateComboBox();
	}
}
