package application;

import java.util.ArrayList;
import java.sql.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Maps");


		
		GridPane root = new GridPane();
		
		//Create the canvas
		Canvas canvas = new Canvas(900, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		

		//List View Setup
		final ListView listView = new ListView();
		listView.setPrefSize(200, 300);
		listView.setEditable(true);
		
		//Get the map names from the Database and set them
		ObservableList<String> items = FXCollections.observableArrayList (
				SQL.getMaps());
		listView.setItems(items);
		
		//Some selection magick, I have zero idea how this thing works, just found it on the internet
		//Probably need to be changed into a Event Handler
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        	 
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            	
            	//Gets the cities from the database, newValue is the index of the currently selected list item 
        		ArrayList<City> cities = SQL.getCities((int) newValue + 1);
        		drawMap(gc, cities, SQL.getRoads((int)newValue + 1));
                	
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
		
		SQL.connect();
		
		launch(args);
	}
	
	
	
	//This bad boy draws the city on the maps by going through the ArrayList with the cities
	//We can probably draw roads here aswell 
	private void drawMap(GraphicsContext gc, ArrayList<City> cities, ArrayList<Road> roads) {
		
		gc.clearRect(0, 0, 900, 600);
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, 900, 600);



		gc.setFill(Color.BLACK);

		for(int i = 0; i < cities.size(); i++)
		{
			gc.fillRect(cities.get(i).getPosX(), cities.get(i).getPosY(), 8, 8);
			gc.fillText(cities.get(i).getName(), cities.get(i).getPosX() - 20, cities.get(i).getPosY() - 7);
		}
		
		for(int i = 0; i < roads.size(); i++)
		{
			gc.strokeLine(roads.get(i).getPosXFrom() + 5, roads.get(i).getPosYFrom() + 5, roads.get(i).getPosXTo() + 5, roads.get(i).getPosYTo() + 5);
			gc.fillText(Integer.toString(roads.get(i).getDistance()), 
					((roads.get(i).getPosXFrom() + roads.get(i).getPosXTo()) / 2) + 10, 
					(roads.get(i).getPosYFrom() + roads.get(i).getPosYTo()) / 2);
		}

	}


}