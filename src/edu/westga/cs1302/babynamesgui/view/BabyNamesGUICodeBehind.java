package edu.westga.cs1302.babynamesgui.view;

import java.io.File;

import java.util.Optional;

import edu.westga.cs1302.babynamesgui.model.GenderType;
import edu.westga.cs1302.babynamesgui.utilities.CommonMessages;
import edu.westga.cs1302.babynamesgui.utilities.CustomNumberStringConverter;
import edu.westga.cs1302.babynamesgui.viewmodel.BabyNamesGUIViewModel;
import edu.westga.cs1302.babynamesgui.viewmodel.BabyNamesGUIViewModelChart;

import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * The class baby names GUI controller
 * 
 * @version Spring 2024
 * @author Colby
 */

public class BabyNamesGUICodeBehind {

	private BabyNamesGUIViewModel viewModel;
	@FXML
	private Pane guiPane;
	@FXML
	private MenuItem fileSaveMenuItem;
	@FXML
	private MenuItem fileOpenMenuItem;
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu fileMenu;
	@FXML
	private MenuItem fileExitMenuItem;

	@FXML
	private RadioButton femaleRadioButton;

	@FXML
	private Label frequencLabel;

	@FXML
	private TextField frequencyTextField;

	@FXML
	private Label genderLabel;

	@FXML
	private RadioButton maleRadioButton;

	@FXML
	private Label nameLabel;

	@FXML
	private TextField nameTextField;

	@FXML
	private ListView<String> recordsListView;

	@FXML
	private Label stateLabel;

	@FXML
	private Label yearLabel;

	@FXML
	private Button addButton;
	@FXML
	private Menu sortMenu;

	@FXML
	private MenuItem sortMenuNameYearMenuItem;

	@FXML
	private MenuItem sortMenuYearFrequencyMenuItem;
	@FXML
	private Menu helpMenu;

	@FXML
	private MenuItem helpMenuAboutMenuItem;
	@FXML
	private ComboBox<Integer> yearComboBox;
	@FXML
	private Label errorLabel;
	@FXML
	private Button deleteButton;
	@FXML
	private Button searchButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button clearButton;
	@FXML
	private BarChart<String, Integer> namesBarChart;
	private BabyNamesGUIViewModelChart viewModelChart;
	@FXML
	private TextField chartYearTextField;
	@FXML
	private Label yearChartLabel;
	@FXML
	private Button nextButton;

	@FXML
	private Button previousButton;
	@FXML
	private RadioButton allFemaleRadioButton;

	@FXML
	private RadioButton allMaleRadioButton;
	@FXML
	private RadioButton halfFemaleMaleRadioButton;
	@FXML
	private TextField endYearTextField;
	@FXML
	private LineChart<Number, Number> namesLineChart;
	@FXML
	private RadioButton lineChartFemaleRadioButton;

	@FXML
	private RadioButton lineChartMaleRadioButton;
	@FXML
	private TextField lineChartNameTextField;

	@FXML
	private Button lineChartSubmitButton;
	@FXML
	private TextField startYearTextField;
	@FXML
	private NumberAxis lineChartXAxis;

	@FXML
	private NumberAxis lineChartYAxis;

	/**
	 * Instantiates a controller for the GUI
	 */

	public BabyNamesGUICodeBehind() {
		this.viewModel = new BabyNamesGUIViewModel();
		this.viewModelChart = new BabyNamesGUIViewModelChart();

	}

	/**
	 * Initializes all the controls and functionality of the GUI
	 */

	@FXML
	public void initialize() {
		this.bindToViewModel();
		this.setUpButtons();
		this.setUpButtons2();
		this.stateLabel.setVisible(false);
		this.errorLabel.setVisible(false);
		this.setUpListeners();
		this.setUpListeners2();
		this.setUpListeners3();
		this.addButton.setDisable(true);
		this.deleteButton.setDisable(true);
		this.searchButton.setDisable(true);
		this.updateButton.setDisable(true);
		this.setUpButtons3();
		this.setUpChartListeners();
		this.setUpLineChart();
		this.setUpNameTextFields();
		this.setUpIntegerFiltersForYear();
	}

	/**
	 * Binds the fields of the GUI to the view model
	 */

	public void bindToViewModel() {
		this.namesLineChart.dataProperty().bind(this.viewModelChart.lineChartDataProperty());
		this.lineChartNameTextField.textProperty().bindBidirectional(this.viewModelChart.nameProperty());
		this.viewModelChart.femaleSelectedProperty().bind(this.lineChartFemaleRadioButton.selectedProperty());
		this.viewModelChart.maleSelectedProperty().bind(this.lineChartMaleRadioButton.selectedProperty());
		this.startYearTextField.textProperty().bindBidirectional(this.viewModelChart.startYearProperty(),
				new CustomNumberStringConverter());
		this.endYearTextField.textProperty().bindBidirectional(this.viewModelChart.endYearProperty(),
				new CustomNumberStringConverter());
		this.chartYearTextField.textProperty().bindBidirectional(this.viewModelChart.barYearProperty(),
				new CustomNumberStringConverter());
		this.namesBarChart.dataProperty().bind(this.viewModelChart.barDataProperty());
		this.viewModel.selectedObjectProperty().bind(this.recordsListView.getSelectionModel().selectedItemProperty());
		this.recordsListView.itemsProperty().bindBidirectional(this.viewModel.listRecordProperty());
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.nameProperty());

		this.yearComboBox.getEditor().textProperty().bindBidirectional(this.viewModel.yearProperty(),
				new CustomNumberStringConverter());

		this.femaleRadioButton.selectedProperty().bindBidirectional(this.viewModel.femaleProperty());
		this.maleRadioButton.selectedProperty().bindBidirectional(this.viewModel.maleProperty());
		this.frequencyTextField.textProperty().bindBidirectional(this.viewModel.frequencyProperty(),
				new CustomNumberStringConverter());
		this.stateLabel.textProperty().bind(this.viewModel.stateProperty());
		this.yearComboBox.itemsProperty().bind(this.viewModel.yearListProperty());
	}

	private void setUpButtons() {
		this.addButton.setOnAction(event -> {
			if (this.areRecordFieldsFilledOut()) {
				if (this.viewModel.addBabyRecord()) {
					this.clearFields();
				} else {
					this.errorLabel.setText(CommonMessages.ERROR_DUPLICATE_MSG);
					this.errorLabel.setVisible(true);
				}
			} else {
				this.errorLabel.setText(CommonMessages.ERROR_FIELDS_NOT_COMPLETED_MSG);
				this.errorLabel.setVisible(true);
			}
		});
		this.fileOpenMenuItem.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter(CommonMessages.FILE_OPENER_CVS, "*.csv"),
					new ExtensionFilter(CommonMessages.FILE_OPENER_TXT, "*.txt")

			);
			Stage stage = (Stage) this.guiPane.getScene().getWindow();
			File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				this.viewModel.loadFile(selectedFile);
				this.stateLabel.setVisible(true);
			}
		});
		this.fileSaveMenuItem.setOnAction(event -> {
			String populatedText = "";
			if (this.stateLabel.isVisible()) {
				populatedText = this.stateLabel.getText().substring(7);
			}
			TextInputDialog dialog = new TextInputDialog(populatedText);
			dialog.setTitle("Setting state name");
			dialog.setHeaderText("Enter state name");
			dialog.setContentText("Name:");
			Optional<String> name = dialog.showAndWait();
			if (name.isPresent()) {

				this.viewModel.updateState(name.get());
				this.stateLabel.setVisible(true);
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Save");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter(CommonMessages.FILE_OPENER_CVS, "*.csv"),
						new ExtensionFilter("All Files", "*.*"));
				Stage stage = (Stage) this.guiPane.getScene().getWindow();
				File selectedFile = fileChooser.showSaveDialog(stage);
				if (selectedFile != null) {
					this.viewModel.writeToFile(selectedFile);
				}

			}
		});
		this.sortMenuNameYearMenuItem.setOnAction(event -> {
			this.viewModel.sortByNameYear();
		});

	}

	private void setUpButtons2() {
		this.helpMenuAboutMenuItem.setOnAction(event -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setHeaderText("Baby Name Records GUI");
			alert.setContentText(CommonMessages.ABOUT_INFORMATION_DIALOG);
			alert.showAndWait();
		});
		this.searchButton.setOnAction(event -> {

			if (this.viewModel.searchRecord() != null) {
				this.recordsListView.getSelectionModel().select(this.viewModel.searchRecord());
			} else {
				this.errorLabel.setText(CommonMessages.ERROR_RECORD_NOT_FOUND);
				this.errorLabel.setVisible(true);
			}
		});
		this.updateButton.setOnAction(event -> {
			if (this.areRecordFieldsFilledOut()) {
				if (this.viewModel.updateRecord()) {
					this.errorLabel.setVisible(false);
				} else {
					this.errorLabel.setText(CommonMessages.ERROR_RECORD_COULD_NOT_BE_UPDATED);
					this.errorLabel.setVisible(true);
				}

			} else {
				this.errorLabel.setText(CommonMessages.ERROR_RECORD_COULD_NOT_BE_UPDATED_WITHOUT);
				this.errorLabel.setVisible(true);
			}

		});
		this.clearButton.setOnAction(event -> {
			this.viewModel.clearRecords();
			this.clearFields();
		});
		this.deleteButton.setOnAction(event -> {
			if (this.areRecordFieldsFilledOut()) {
				if (this.viewModel.deleteRecord()) {
					this.clearFields();
					this.errorLabel.setVisible(false);
				} else {
					this.errorLabel.setText(CommonMessages.ERROR_CANNOT_BE_REMOVED);
					this.errorLabel.setVisible(true);
				}
			}

		});
		this.sortMenuYearFrequencyMenuItem.setOnAction(event -> {
			this.viewModel.sortByYearFrequency();
		});
	}

	private void setUpButtons3() {

		this.chartYearTextField.setOnAction(event -> {

			if (this.viewModelChart.setData(this.viewModel.getRecord())) {
				this.namesBarChart.setTitle("Top Names: " + this.chartYearTextField.textProperty().getValue());
				this.halfFemaleMaleRadioButton.selectedProperty().set(true);
			} else {
				this.errorLabel.setText(CommonMessages.ERROR_NO_RECORDS_TO_DISPLAY);
				this.errorLabel.setVisible(true);

			}
		});
		this.previousButton.setOnAction(event -> {

			if (this.viewModelChart.handlePreviousButton(this.viewModel.getRecord())) {
				this.namesBarChart.setTitle("Top Names: " + this.chartYearTextField.textProperty().getValue());
				this.allFemaleRadioButton.setSelected(false);
				this.allMaleRadioButton.setSelected(false);
				this.halfFemaleMaleRadioButton.setSelected(true);
			} else {
				this.viewModelChart.handleNextButton(this.viewModel.getRecord());
				this.errorLabel.setText(CommonMessages.ERROR_NO_RECORDS_TO_DISPLAY);
				this.errorLabel.setVisible(true);
			}
		});
		this.nextButton.setOnAction(event -> {

			if (this.viewModelChart.handleNextButton(this.viewModel.getRecord())) {
				this.namesBarChart.setTitle("Top Names: " + this.chartYearTextField.textProperty().getValue());
				this.allFemaleRadioButton.setSelected(false);
				this.allMaleRadioButton.setSelected(false);
				this.halfFemaleMaleRadioButton.setSelected(true);
			} else {

				this.viewModelChart.handlePreviousButton(this.viewModel.getRecord());
				this.errorLabel.setText(CommonMessages.ERROR_NO_RECORDS_TO_DISPLAY);
				this.errorLabel.setVisible(true);
			}
		});

	}

	private void setUpLineChart() {
		this.lineChartSubmitButton.setOnAction(event -> {
			GenderType gender = null;
			if (this.lineChartFemaleRadioButton.isSelected()) {
				gender = GenderType.FEMALE;
			} else if (this.lineChartMaleRadioButton.isSelected()) {
				gender = GenderType.MALE;
			}
			this.namesLineChart
					.setTitle(this.lineChartNameTextField.textProperty().get() + " & " + gender.name().toLowerCase());
			this.viewModelChart.handleLineChartSubmitButton(this.viewModel.getRecord());
			this.lineChartXAxis.setTickLabelFormatter(new StringConverter<Number>() {

				@Override
				public Number fromString(String string) {
					return Integer.parseInt(string);
				}

				@Override
				public String toString(Number arg0) {
					return arg0.toString().replaceAll("\\.[0-9]*", "");
				}

			});
			int xLowerBound = Integer.parseInt(this.startYearTextField.textProperty().get());
			int xUpperBound = Integer.parseInt(this.endYearTextField.textProperty().get());
			while (xLowerBound % 25 != 0) {
				xLowerBound = xLowerBound - 1;
			}
			while (xUpperBound % 25 != 0) {
				xUpperBound = xUpperBound + 1;
			}
			this.lineChartXAxis.setLowerBound(xLowerBound);
			this.lineChartXAxis.setUpperBound(xUpperBound);
			this.lineChartXAxis.setTickUnit((xUpperBound - xLowerBound) / 25);
			double yUpperBound = this.viewModelChart.getLineChartHighestFrequency();
			while (yUpperBound % 3 != 0) {
				yUpperBound++;
			}
			this.lineChartYAxis.setTickUnit(yUpperBound / 3);
			this.lineChartYAxis.setUpperBound(yUpperBound + 6);
		});
	}

	private void setUpListeners() {
		this.nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("[A-Z][a-z]*") && !newValue.isEmpty()) {

				String formattedName = newValue.toLowerCase();
				formattedName = formattedName.replace(formattedName.charAt(0),
						Character.toUpperCase(formattedName.charAt(0)));
				this.nameTextField.textProperty().setValue(formattedName);
			}
			if (newValue.matches("[A-Z][a-z]*")) {

				if (this.areRecordFieldsFilledOut()) {
					this.addButton.setDisable(false);
					this.deleteButton.setDisable(false);
					this.updateButton.setDisable(false);
				} else {
					this.addButton.setDisable(true);
					this.deleteButton.setDisable(true);
					this.updateButton.setDisable(true);
				}
			}
			if (newValue.isEmpty()) {
				this.errorLabel.setVisible(false);
			}
			if (this.canRecordBeSearched()) {
				this.searchButton.setDisable(false);
			} else {
				this.searchButton.setDisable(true);
			}

		});
		this.yearComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.matches("[0-9]*") && !newValue.isEmpty()) {
				if (Integer.parseInt(newValue) < 1910 || Integer.parseInt(newValue) > 2024) {
					this.errorLabel.setText(CommonMessages.ERROR_YEAR_BOUNDARY_MSG);
					this.errorLabel.setVisible(true);
					this.addButton.setDisable(true);
					this.deleteButton.setDisable(true);
					this.updateButton.setDisable(true);
				} else if (Integer.parseInt(newValue) >= 1910 && Integer.parseInt(newValue) <= 2024) {
					this.errorLabel.setVisible(false);
					this.addButton.setDisable(false);
					this.deleteButton.setDisable(false);
					this.updateButton.setDisable(false);
				}
			}
			if (newValue.isEmpty()) {
				this.errorLabel.setVisible(false);
			}
			if (this.canRecordBeSearched()) {
				this.searchButton.setDisable(false);
			} else {
				this.searchButton.setDisable(true);
			}

		});

	}

	private void setUpListeners3() {
		this.frequencyTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (this.areRecordFieldsFilledOut()) {
				this.addButton.setDisable(false);
				this.deleteButton.setDisable(false);
				this.updateButton.setDisable(false);
			} else {
				this.addButton.setDisable(true);
				this.deleteButton.setDisable(true);
				this.updateButton.setDisable(true);
			}
		});
		this.chartYearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.matches("[0-9]*") && !newValue.isEmpty()) {
				if (Integer.parseInt(newValue) < 1910 || Integer.parseInt(newValue) > 2024) {
					this.errorLabel.setText(CommonMessages.ERROR_YEAR_BOUNDARY_MSG);
					this.errorLabel.setVisible(true);
					this.nextButton.setDisable(true);
					this.previousButton.setDisable(true);
				} else if (Integer.parseInt(newValue) >= 1910 && Integer.parseInt(newValue) <= 2024) {
					this.errorLabel.setVisible(false);
					this.nextButton.setDisable(false);
					this.previousButton.setDisable(false);
				}
			}
			if (newValue.isEmpty()) {
				this.errorLabel.setVisible(false);
			}
			if (this.canRecordBeSearched()) {
				this.searchButton.setDisable(false);
			} else {
				this.searchButton.setDisable(true);
			}

		});
		this.lineChartFemaleRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue && this.lineChartMaleRadioButton.isSelected()) {
				this.errorLabel.setText(CommonMessages.ERROR_MULTIPLE_GENDERS_MSG);
				this.errorLabel.setVisible(true);
				this.lineChartSubmitButton.setDisable(true);
			} else {
				this.errorLabel.setVisible(false);
				this.lineChartSubmitButton.setDisable(false);
			}
		});
		this.lineChartMaleRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue && this.lineChartFemaleRadioButton.isSelected()) {
				this.errorLabel.setText(CommonMessages.ERROR_MULTIPLE_GENDERS_MSG);
				this.errorLabel.setVisible(true);
				this.lineChartSubmitButton.setDisable(true);
			} else {
				this.errorLabel.setVisible(false);
				this.lineChartSubmitButton.setDisable(false);
			}
		});

	}

	private void setUpListeners2() {

		this.maleRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (this.areRecordFieldsFilledOut()) {
				this.addButton.setDisable(false);
				this.deleteButton.setDisable(false);
				this.updateButton.setDisable(false);
			} else {
				this.addButton.setDisable(true);
				this.deleteButton.setDisable(true);
				this.updateButton.setDisable(true);
			}
			if (this.canRecordBeSearched()) {
				this.searchButton.setDisable(false);
			} else {
				this.searchButton.setDisable(true);
			}
		});
		this.femaleRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (this.areRecordFieldsFilledOut()) {
				this.addButton.setDisable(false);
				this.deleteButton.setDisable(false);
				this.updateButton.setDisable(false);
			} else {
				this.addButton.setDisable(true);
				this.deleteButton.setDisable(true);
				this.updateButton.setDisable(true);
			}
			if (this.canRecordBeSearched()) {
				this.searchButton.setDisable(false);
			} else {
				this.searchButton.setDisable(true);
			}
		});
		this.recordsListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						String[] tokens = newValue.split(", ");
						this.nameTextField.textProperty().setValue(tokens[0]);
						this.yearComboBox.getEditor().textProperty()
								.setValue(tokens[2].substring(0, tokens[2].indexOf(':')));
						if (tokens[1].equals(GenderType.FEMALE.name().substring(0, 1))) {
							this.femaleRadioButton.setSelected(true);
							this.maleRadioButton.setSelected(false);
						} else if (tokens[1].equals(GenderType.MALE.name().substring(0, 1))) {
							this.maleRadioButton.setSelected(true);
							this.femaleRadioButton.setSelected(false);
						}
						this.frequencyTextField.textProperty()
								.setValue(tokens[2].substring(tokens[2].indexOf(' '), tokens[2].length()).trim());
					}
				});
	}

	private void clearFields() {
		this.nameTextField.textProperty().setValue("");
		this.yearComboBox.getEditor().textProperty().setValue("");
		this.femaleRadioButton.selectedProperty().setValue(false);
		this.maleRadioButton.selectedProperty().setValue(false);
		this.frequencyTextField.textProperty().setValue("");
		this.stateLabel.setVisible(false);

	}

	private boolean areRecordFieldsFilledOut() {

		boolean isFilled;
		isFilled = !(this.nameTextField.textProperty().isEmpty().get()
				|| this.yearComboBox.getEditor().textProperty().isEmpty().get()
				|| (!this.maleRadioButton.isSelected() && !this.femaleRadioButton.isSelected())
				|| this.frequencyTextField.textProperty().isEmpty().get());

		if (this.maleRadioButton.isSelected() && this.femaleRadioButton.isSelected()) {
			this.errorLabel.setText("Only one gender can be selected");
			this.errorLabel.setVisible(true);
			isFilled = false;
		} else {
			this.errorLabel.setVisible(false);
		}
		return isFilled;

	}

	private boolean canRecordBeSearched() {
		boolean isFilled;
		isFilled = !(this.nameTextField.textProperty().isEmpty().get()
				|| this.yearComboBox.getEditor().textProperty().isEmpty().get()
				|| (!this.maleRadioButton.isSelected() && !this.femaleRadioButton.isSelected()));

		if (this.maleRadioButton.isSelected() && this.femaleRadioButton.isSelected()) {
			isFilled = false;
		}
		return isFilled;
	}

	private void setUpChartListeners() {
		this.allMaleRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.viewModelChart.handleSixMaleRadioButton(this.viewModel.getRecord());
				this.halfFemaleMaleRadioButton.setSelected(false);
				this.allFemaleRadioButton.setSelected(false);
			}
		});
		this.halfFemaleMaleRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.viewModelChart.setData(this.viewModel.getRecord());
				this.allMaleRadioButton.setSelected(false);
				this.allFemaleRadioButton.setSelected(false);
			}
		});
		this.allFemaleRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				this.viewModelChart.handleSixFemaleRadioButton(this.viewModel.getRecord());
				this.allMaleRadioButton.setSelected(false);
				this.halfFemaleMaleRadioButton.setSelected(false);
			}
		});
		this.chartYearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.trim().isEmpty()) {
				int year = Integer.parseInt(newValue);
				if (!newValue.trim().isEmpty() && year > 2024 || year < 1910) {
					this.errorLabel.setText(CommonMessages.ERROR_YEAR_BOUNDARY_MSG);
					this.errorLabel.setVisible(true);
				} else {
					this.errorLabel.setVisible(false);
				}
			}

		});
		this.startYearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.trim().isEmpty()) {
				int year = Integer.parseInt(newValue);
				if (!newValue.trim().isEmpty() && year > 2024 || year < 1910) {
					this.errorLabel.setText(CommonMessages.ERROR_YEAR_BOUNDARY_MSG);
					this.errorLabel.setVisible(true);
				} else {
					this.errorLabel.setVisible(false);
				}
			}

		});
		this.endYearTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.trim().isEmpty()) {
				int year = Integer.parseInt(newValue);
				if (!newValue.trim().isEmpty() && year > 2024 || year < 1910) {
					this.errorLabel.setText(CommonMessages.ERROR_YEAR_BOUNDARY_MSG);
					this.errorLabel.setVisible(true);
				} else {
					this.errorLabel.setVisible(false);
				}
			}

		});
	}

	private void setUpIntegerFiltersForYear() {
		TextFormatter<String> yearIntegerFilter = new TextFormatter<String>(change -> {
			if (change.getText().matches("[0-9]*")) {

				return change;
			} else {
				this.errorLabel.setText(CommonMessages.ERROR_YEAR_MSG);
				this.errorLabel.setVisible(true);
				return null;
			}
		});
		TextFormatter<String> frequencyIntegerFilter = new TextFormatter<String>(change -> {
			if (change.getText().matches("[0-9]*")) {
				return change;
			} else {
				this.errorLabel.setText(CommonMessages.ERROR_YEAR_MSG);
				this.errorLabel.setVisible(true);
				return null;
			}
		});
		TextFormatter<String> chartYearIntegerFilter = new TextFormatter<String>(change -> {
			if (change.getText().matches("[0-9]*")) {
				return change;
			} else {
				this.errorLabel.setText(CommonMessages.ERROR_YEAR_MSG);
				this.errorLabel.setVisible(true);
				return null;
			}
		});
		TextFormatter<String> lineChartStartYearIntegerFilter = new TextFormatter<String>(change -> {
			if (change.getText().matches("[0-9]*")) {
				return change;
			} else {
				this.errorLabel.setText(CommonMessages.ERROR_YEAR_MSG);
				this.errorLabel.setVisible(true);
				return null;
			}
		});
		TextFormatter<String> lineChartEndYearIntegerFilter = new TextFormatter<String>(change -> {
			if (change.getText().matches("[0-9]*")) {
				return change;
			} else {
				this.errorLabel.setText(CommonMessages.ERROR_YEAR_MSG);
				this.errorLabel.setVisible(true);
				return null;
			}
		});

		this.frequencyTextField.setTextFormatter(frequencyIntegerFilter);
		this.yearComboBox.getEditor().setTextFormatter(yearIntegerFilter);
		this.chartYearTextField.setTextFormatter(chartYearIntegerFilter);
		this.startYearTextField.setTextFormatter(lineChartStartYearIntegerFilter);
		this.endYearTextField.setTextFormatter(lineChartEndYearIntegerFilter);
	}

	private void setUpNameTextFields() {

		this.nameTextField.setTextFormatter(new TextFormatter<String>(change -> {
			if (!change.getText().matches("[A-Z]*[a-z]*")) {
				this.errorLabel.setText(CommonMessages.ERROR_NAME_MSG);
				this.errorLabel.setVisible(true);
				return null;
			} else {
				this.errorLabel.setVisible(false);
				return change;
			}
		}));

		this.lineChartNameTextField.setTextFormatter(new TextFormatter<String>(change -> {
			if (!change.getText().matches("[A-Z]*[a-z]*")) {
				this.errorLabel.setText(CommonMessages.ERROR_NAME_MSG);
				this.errorLabel.setVisible(true);
				return null;
			} else {
				this.errorLabel.setVisible(false);
				return change;
			}
		}));
		this.lineChartNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.trim().isEmpty()) {
				String formattedName = newValue.toLowerCase();
				formattedName = formattedName.replace(formattedName.charAt(0),
						Character.toUpperCase(formattedName.charAt(0)));
				this.lineChartNameTextField.textProperty().setValue(formattedName);
			}
		});
	}

}
