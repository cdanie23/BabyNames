package edu.westga.cs1302.babynamesgui.viewmodel;

import edu.westga.cs1302.babynamesgui.model.BabyKey;
import edu.westga.cs1302.babynamesgui.model.BabyNameRecord;

import edu.westga.cs1302.babynamesgui.model.GenderType;
import edu.westga.cs1302.babynamesgui.utilities.RecordDisplay;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 * The class Baby Names GUI View Model Bar Chart which is used to control the
 * bar chart
 * 
 * @version Spring 2024
 * @author Colby
 */

public class BabyNamesGUIViewModelChart {
	private RecordDisplay recordDisplay;

	private ListProperty<Series<String, Integer>> barDataProperty;
	private IntegerProperty barYearProperty;

	private XYChart.Series<String, Integer> females;
	private XYChart.Series<String, Integer> males;
	private XYChart.Series<Number, Number> nameLineData;
	private ListProperty<Series<Number, Number>> lineChartDataProperty;
	private StringProperty nameProperty;
	private BooleanProperty maleSelectedProperty;
	private BooleanProperty femaleSelectedProperty;
	private IntegerProperty startYearProperty;
	private IntegerProperty endYearProperty;
	private int lineChartHighestFrequency;

	/**
	 * Instantiates a view model for the bar chart
	 */

	public BabyNamesGUIViewModelChart() {
		this.lineChartDataProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.nameLineData = new Series<Number, Number>();
		this.lineChartDataProperty.add(this.nameLineData);
		this.barYearProperty = new SimpleIntegerProperty();
		this.recordDisplay = new RecordDisplay();
		this.females = new XYChart.Series<>();
		this.males = new XYChart.Series<>();
		this.barDataProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.females.setName("Females");
		this.males.setName("Males");
		this.barDataProperty.add(this.females);
		this.barDataProperty.add(this.males);
		this.nameProperty = new SimpleStringProperty();
		this.maleSelectedProperty = new SimpleBooleanProperty();
		this.femaleSelectedProperty = new SimpleBooleanProperty();
		this.startYearProperty = new SimpleIntegerProperty();
		this.endYearProperty = new SimpleIntegerProperty();

	}

	/**
	 * Gets the line chart data property
	 * 
	 * @return the lineChartDataProperty
	 */
	public ListProperty<Series<Number, Number>> lineChartDataProperty() {
		return this.lineChartDataProperty;
	}

	/**
	 * Gets the name property
	 * 
	 * @return the nameProperty
	 */
	public StringProperty nameProperty() {
		return this.nameProperty;
	}

	/**
	 * Gets the male boolean property
	 * 
	 * @return the maleSelectedProperty
	 */
	public BooleanProperty maleSelectedProperty() {
		return this.maleSelectedProperty;
	}

	/**
	 * Gets the female boolean property
	 * 
	 * @return the femaleSelectedProperty
	 */
	public BooleanProperty femaleSelectedProperty() {
		return this.femaleSelectedProperty;
	}

	/**
	 * Gets the start year property
	 * 
	 * @return the startYearProperty
	 */
	public IntegerProperty startYearProperty() {
		return this.startYearProperty;
	}

	/**
	 * Gets the end year property
	 * 
	 * @return the endYearProperty
	 */
	public IntegerProperty endYearProperty() {
		return this.endYearProperty;
	}

	/**
	 * Gets the bar data property
	 * 
	 * @return the bar data property
	 */

	public ListProperty<Series<String, Integer>> barDataProperty() {
		return this.barDataProperty;
	}

	/**
	 * Gets the highest frequency of the line chart
	 * 
	 * @return the lineChartHighestFrequency
	 */
	public int getLineChartHighestFrequency() {
		return this.lineChartHighestFrequency;
	}

	/**
	 * Sets the data to the bar chart
	 * 
	 * @param record the records to set to the bar chart
	 * @return true or false based on if there are records available for the
	 *         specified year in the GUI
	 */

	public boolean setData(BabyNameRecord record) {

		this.recordDisplay.formatByYear(record, this.barYearProperty.getValue().intValue());
		if (this.recordDisplay.isEmpty()) {
			return false;
		}
		try {
			this.barDataProperty.get().get(0).getData().clear();
			this.barDataProperty.get().get(1).getData().clear();
			this.recordDisplay.forEach(string -> {

				String[] tokens = string.split(", ");

				if (tokens[1].equals(GenderType.MALE.name().substring(0, 1)) && this.males.getData().size() < 3) {

					this.males.getData().add(new XYChart.Data<String, Integer>(tokens[0],
							Integer.parseInt(tokens[2].substring(6, tokens[2].length()))));

				} else if (tokens[1].equals(GenderType.FEMALE.name().substring(0, 1))
						&& this.females.getData().size() < 3) {
					this.females.getData().add(new XYChart.Data<String, Integer>(tokens[0],
							Integer.parseInt(tokens[2].substring(6, tokens[2].length()))));
				}

			});

		} catch (Exception ex) {
			System.err.println("Error with graph :(" + ex.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Gets the bar year property
	 * 
	 * @return the bar year property
	 */

	public IntegerProperty barYearProperty() {
		return this.barYearProperty;
	}

	/**
	 * Changes the data on the bar chart to the previous year
	 * 
	 * @param record the records to view from
	 * @return true or false based on if the record has data to show from the
	 *         previous year
	 */

	public boolean handlePreviousButton(BabyNameRecord record) {
		this.barYearProperty.set(this.barYearProperty.getValue() - 1);
		this.recordDisplay.formatByYear(record, this.barYearProperty.getValue());

		if (this.recordDisplay.isEmpty()) {
			return false;
		}
		try {
			this.barDataProperty.get().get(0).getData().clear();
			this.barDataProperty.get().get(1).getData().clear();
			this.recordDisplay.forEach(string -> {

				String[] tokens = string.split(", ");

				if (tokens[1].equals(GenderType.MALE.name().substring(0, 1)) && this.males.getData().size() < 3) {

					this.males.getData().add(new XYChart.Data<String, Integer>(tokens[0],
							Integer.parseInt(tokens[2].substring(6, tokens[2].length()))));

				} else if (tokens[1].equals(GenderType.FEMALE.name().substring(0, 1))
						&& this.females.getData().size() < 3) {
					this.females.getData().add(new XYChart.Data<String, Integer>(tokens[0],
							Integer.parseInt(tokens[2].substring(6, tokens[2].length()))));
				}

			});

		} catch (Exception ex) {
			System.err.println("Error with graph :(" + ex.getMessage());
		}
		return true;
	}

	/**
	 * Sets the data in the bar chart to the data in the next year
	 * 
	 * @param record the record to use data from
	 * @return true or falsed based on if the record has data to show from the next
	 *         year
	 */

	public boolean handleNextButton(BabyNameRecord record) {

		this.barYearProperty.set(this.barYearProperty.getValue() + 1);
		this.recordDisplay.formatByYear(record, this.barYearProperty.getValue());
		if (this.recordDisplay.isEmpty()) {
			return false;
		}
		try {
			this.barDataProperty.get().get(0).getData().clear();
			this.barDataProperty.get().get(1).getData().clear();
			this.recordDisplay.forEach(string -> {

				String[] tokens = string.split(", ");

				if (tokens[1].equals(GenderType.MALE.name().substring(0, 1)) && this.males.getData().size() < 3) {

					this.males.getData().add(new XYChart.Data<String, Integer>(tokens[0],
							Integer.parseInt(tokens[2].substring(6, tokens[2].length()))));

				} else if (tokens[1].equals(GenderType.FEMALE.name().substring(0, 1))
						&& this.females.getData().size() < 3) {
					this.females.getData().add(new XYChart.Data<String, Integer>(tokens[0],
							Integer.parseInt(tokens[2].substring(6, tokens[2].length()))));
				}

			});

		} catch (Exception ex) {
			System.err.println("Error with graph :(" + ex.getMessage());
		}
		return true;
	}

	/**
	 * Sets the data in the chart to have 6 males displayed
	 * 
	 * @param record the record to display in the chart
	 */

	public void handleSixMaleRadioButton(BabyNameRecord record) {
		this.recordDisplay.formatByYear(record, this.barYearProperty.getValue().intValue());

		try {
			this.barDataProperty.get().get(0).getData().clear();
			this.barDataProperty.get().get(1).getData().clear();
			this.recordDisplay.forEach(string -> {

				String[] tokens = string.split(", ");

				if (tokens[1].equals(GenderType.MALE.name().substring(0, 1)) && this.males.getData().size() < 6) {

					this.males.getData().add(new XYChart.Data<String, Integer>(tokens[0],
							Integer.parseInt(tokens[2].substring(6, tokens[2].length()))));

				}

			});

		} catch (Exception ex) {
			System.err.println("Error with graph :(" + ex.getMessage());

		}

	}

	/**
	 * Sets the bar chart to display six females
	 * 
	 * @param record the record to display from
	 */

	public void handleSixFemaleRadioButton(BabyNameRecord record) {
		this.recordDisplay.formatByYear(record, this.barYearProperty.getValue().intValue());

		try {
			this.barDataProperty.get().get(0).getData().clear();
			this.barDataProperty.get().get(1).getData().clear();
			this.recordDisplay.forEach(string -> {

				String[] tokens = string.split(", ");

				if (tokens[1].equals(GenderType.FEMALE.name().substring(0, 1)) && this.females.getData().size() < 6) {
					this.females.getData().add(new XYChart.Data<String, Integer>(tokens[0],
							Integer.parseInt(tokens[2].substring(6, tokens[2].length()))));
				}

			});

		} catch (Exception ex) {
			System.err.println("Error with graph :(" + ex.getMessage());

		}

	}

	/**
	 * Displays data in the line chart
	 * 
	 * @param record the record to display from in the line chart
	 */

	public void handleLineChartSubmitButton(BabyNameRecord record) {

		this.lineChartHighestFrequency = 0;
		this.nameLineData.getData().clear();
		GenderType gender = null;
		if (this.femaleSelectedProperty.get()) {
			gender = GenderType.FEMALE;
		} else if (this.maleSelectedProperty.get()) {
			gender = GenderType.MALE;
		}
		for (BabyKey key : record.keySet()) {
			if (key.getName().equals(this.nameProperty.getValue()) && key.getYear() >= this.startYearProperty.getValue()
					&& key.getYear() <= this.endYearProperty.getValue() && key.getGender() == gender) {
				this.nameLineData.getData()
						.add(new XYChart.Data<Number, Number>(key.getYear(), record.get(key).getFrequency()));
				if (record.get(key).getFrequency() > this.lineChartHighestFrequency) {
					this.lineChartHighestFrequency = record.get(key).getFrequency();
				}
			}
		}

	}
}
