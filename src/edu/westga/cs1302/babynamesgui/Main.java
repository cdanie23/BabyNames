package edu.westga.cs1302.babynamesgui;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * The main class
 * @version Spring 2024
 * @author Colby
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			final String name = "Project 3 by Colby Daniel";
			final String fxmlFile = "view/babyNamesGUI.fxml";
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(fxmlFile));
			Pane pane = loader.load();
			Scene scene = new Scene(pane);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle(name);
			primaryStage.show();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	/**
	 * Starts the application
	 * @param args the args to run with the applications
	 */
	
	public static void main(String[] args) {
		launch(args);
	}
}
