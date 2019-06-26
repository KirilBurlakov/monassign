package application;


import java.sql.*;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SQL {
	private static Connection conn = null;
	private static PreparedStatement s = null;
	private static ResultSet rs = null;
	
	public static void connect() throws SQLException
	{
		try {
			// db parameters
			String url = "jdbc:sqlite:C:/sql/maps.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

		} catch (SQLException e) {
			throw e;
		} 
		
	}
	
	public static void disconnect() throws SQLException
	{
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	public static void getCities(GraphicsContext gc, int mapID) throws SQLException
	{
		try {
            s = conn.prepareStatement("SELECT C.Name, C.PosX, C.PosY " +
										"FROM CITY C " +
            							"WHERE MapId = " + mapID);
			rs = s.executeQuery();
			while (rs.next()) {
				gc.fillRect(rs.getInt(2), rs.getInt(3), 8, 8);
				gc.fillText(rs.getString(1), rs.getInt(2) + 10, rs.getInt(3) - 5);
			}
			
		//Whenever you work with sql, this try-catch block has to be present	
		} 
		catch (SQLException e) {
			throw e;
		} 
	}
	
	//Same logic as the getCities() method, but saves just the name
	public static void getMaps(ObservableList<String> items) throws SQLException
	{
		try {
            s = conn.prepareStatement("SELECT M.ID, M.Name " +
										"FROM MAP M " +
            								"ORDER BY 1 ASC");
			rs = s.executeQuery();
			while (rs.next()) 
			{
				items.add(rs.getString(2));
			}
			
		//Whenever you work with sql, this try-catch block has to be present	
		} catch (SQLException e) {
			throw e;
		} 
	}
	
	public static void getRoads(GraphicsContext gc, int mapID) throws SQLException
	{
		try {

            s = conn.prepareStatement("select R.Distance, Cfrom.PosX, Cfrom.PosY, Cto.PosX, Cto.PosY " + 
            							"FROM ROAD R " + 
            							"INNER JOIN CITY Cfrom ON R.IDfrom = Cfrom.ID " + 
            							"INNER JOIN CITY Cto ON R.IDto = Cto.ID " + 
            							"WHERE R.IDfrom > R.IDto and R.MapID = " + mapID);
			rs = s.executeQuery();
			while (rs.next()) 
			{
				//While there are rows, save them into the ArrayList
				gc.strokeLine(rs.getInt(2) + 5, rs.getInt(3) + 5, rs.getInt(4) + 5, rs.getInt(5) + 5);
				gc.fillText(rs.getString(1), 
						((rs.getInt(2) + rs.getInt(4)) / 2) + 10, 
						(rs.getInt(3) + rs.getInt(5)) / 2);
			}
			
		//Whenever you work with SQL, this try-catch block has to be present	
		} catch (SQLException e) {
			throw e;
		} 
	}
	
}
