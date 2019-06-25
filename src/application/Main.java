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
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
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
		
		//Get the maps from the Database and add their names to the listView
		ObservableList<String> items = FXCollections.observableArrayList();
		ArrayList<Map> maps = SQL.getMaps();
		
		for(int i = 0; i < maps.size(); i++)
		{
			items.add(maps.get(i).getName());
		}
		
		listView.setItems(items);
		
		//ListView Selection
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	        	
	        	//The ID of the map is selected via the position of the map in the ListView
	            int mapID = maps.get(listView.getSelectionModel().getSelectedIndex()).getMapID();
	            drawMap(gc, mapID);
	            //Call of the draw method
	            //drawMap(gc, SQL.getCities(mapID), SQL.getRoads(mapID));
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
	
	private void drawMap(GraphicsContext gc, int MapID)
	{
		gc.clearRect(0, 0, 900, 600);
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, 900, 600);
		SQL.getCities(gc, MapID);
		SQL.getRoads(gc, MapID);
		
	}
	
	
	//Draw method, which accepts two ArrayLists containing the cities and the roads
	private void drawMap(GraphicsContext gc, ArrayList<City> cities, ArrayList<Road> roads) {
		
		gc.clearRect(0, 0, 900, 600);
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, 900, 600);
		



		gc.setFill(Color.BLACK);

		//Draw the cities and their names by iterating through the passed ArrayList
		for(int i = 0; i < cities.size(); i++)
		{
			gc.fillRect(cities.get(i).getPosX(), cities.get(i).getPosY(), 8, 8);
			gc.fillText(cities.get(i).getName(), cities.get(i).getPosX() - 20, cities.get(i).getPosY() - 7);
		}
		
		//Draw the roads and their lengths by iterating through the passed ArrayList
		for(int i = 0; i < roads.size(); i++)
		{
			gc.strokeLine(roads.get(i).getPosXFrom() + 5, roads.get(i).getPosYFrom() + 5, roads.get(i).getPosXTo() + 5, roads.get(i).getPosYTo() + 5);
			gc.fillText(Integer.toString(roads.get(i).getDistance()), 
					((roads.get(i).getPosXFrom() + roads.get(i).getPosXTo()) / 2) + 10, 
					(roads.get(i).getPosYFrom() + roads.get(i).getPosYTo()) / 2);
		}

	}


}