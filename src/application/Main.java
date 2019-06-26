package application;

import java.util.ArrayList;
import java.sql.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Main extends Application {


	@Override
	public void start(Stage primaryStage) 
	{
		//Establish the connection to the database
		
		try {
			SQL.connect();
		} catch (SQLException e) {
			showError(e);
		}
		

		primaryStage.setTitle("Maps");

		GridPane root = new GridPane();
		
		//Create the canvas
		Canvas canvas = new Canvas(900, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		

		//List View Setup
		final ListView<String> listView = new ListView<String>();
		listView.setPrefSize(200, 300);
		listView.setEditable(true);
		
		//Get the map names from the Database and add their names to the listView
		ObservableList<String> items = FXCollections.observableArrayList();
		try {
			SQL.getMaps(items);
		} catch (SQLException e) {
			
		}
		listView.setItems(items);
		
		//ListView Selection
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	
	        	//The ID of the map is selected via the position of the map in the ListView
	            int mapID = listView.getSelectionModel().getSelectedIndex() + 1;
	            
	            //Pass the map id to select the desired map
	            drawMap(gc, mapID);
	        }
		});
		
        
		//Add elements to the stage
		root.add(listView, 0, 0);
		root.add(canvas, 1, 0);
		
		//show the stage
		primaryStage.setScene(new Scene(root, 1100, 600));
		primaryStage.show();
	}


	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	//This method is called by the JavaFx application thread when the program is closed
	//Here we also close the connection
    public void stop() 
    {
		try {
			SQL.disconnect();
		} catch (SQLException e) {
			showError(e);
		}
    }
	
	private void drawMap(GraphicsContext gc, int MapID)
	{
		//Clear the canvas before drawing
		gc.clearRect(0, 0, 900, 600);
		
		//Draw the background
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, 900, 600);
		
		//Set the color for drawing to black and draw the roads and the cities
		gc.setFill(Color.BLACK);
		try {
			SQL.getCities(gc, MapID);
			SQL.getRoads(gc, MapID);
		} 
		catch (SQLException e)
		{
			showError(e);
		}
		
	}
	
	//If an SQL exception occurs during any operations, it will be caught and a error message will be displayed
	private void showError(SQLException e) 
	{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText(e.getMessage());
 
 
        alert.showAndWait();
	}

}