package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;


public class Main extends Application {
	
	Stage window;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		//GridPane for overall layout
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(50, 25, 25, 25));
		
		//Header text, column 0 and row 0
		Text formTitle = new Text ("Enter a location below to find weather!");
		formTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(formTitle, 0, 0, 2, 1);
		
		// City label and City text field, columns 0 and 1, row 1
		Label city = new Label ("City:");
		grid.add(city, 0, 1);
		TextField cityTF = new TextField();
		grid.add(cityTF, 1, 1);
		
		// Province label and Province text field, columns 0 and 1, row 2
		Label province = new Label ("Province:");
		grid.add(province, 0, 2);
		
		ObservableList <String> options = FXCollections.observableArrayList("ON", "NL", "PE", "NS", "NB", "QC", "MB", 
																			"SK", "AB", "BC", "YT", "NT", "NU");
		final ComboBox provinceTF = new ComboBox(options);
		grid.add(provinceTF, 1, 2);
		
		// Text for displaying weather and search button
		Text weatherDisplay = new Text ("Weather: ");
		weatherDisplay.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		
		Text weatherText = new Text ("0 degrees Celsius");
		weatherText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		
		Button button = new Button("Search");
		button.setOnAction(e -> {
			Scraper scrape = new Scraper(cityTF.getText(), provinceTF.getValue().toString());
			double temp = 0;
			try {
				temp = scrape.mainScraper();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			weatherText.setText(String.valueOf(temp) + " degrees Celsius");
		});
		
		// HBox for containing the above 3 nodes, column 0 and row 3
		HBox hbox = new HBox(15);
		hbox.getChildren().addAll(button, weatherDisplay, weatherText);
		grid.add(hbox, 0, 3, 2, 1);
		
		Scene scene = new Scene(grid, 500, 300);
		window.setScene(scene);
		window.setTitle("Weather Finder");
		window.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
