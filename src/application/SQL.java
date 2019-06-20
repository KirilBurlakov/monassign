package application;


import java.sql.*;
import java.util.ArrayList;

public class SQL {
	private static Connection conn = null;
	private static PreparedStatement s = null;
	private static ResultSet rs = null;
	public static void connect() {

		try {
			// db parameters
			String url = "jdbc:sqlite:C:/sql/maps.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		//This final stuff should be added in the end
		} /*
		finally {
			try {
				if (conn != null) {
					//conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		*/
	}
	
	public static ArrayList<City> getCities(int mapID)
	{
		ArrayList<City> cities = new ArrayList<City>();
		try {
            s = conn.prepareStatement("SELECT * " +
										"FROM CITY " +
            							"WHERE MapId = " + mapID);
			rs = s.executeQuery();
			while (rs.next()) {
				//While there are rows, save them into the ArrayList
				cities.add(new City(Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)), rs.getString(3), rs.getString(2)));
			}
		
		//Whenever you work with sql, this try-catch block has to be present	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return cities;
	}
	
	//Same logic as the getCities() method, but saves just the name
	//Probably maps will have to become their own class, but for now it is enough to save only the name
	public static ArrayList<String> getMaps()
	{
		ArrayList<String> maps = new ArrayList<String>();
		try {
            s = conn.prepareStatement("SELECT * " +
										"FROM MAP");
			rs = s.executeQuery();
			while (rs.next()) {
				
				maps.add(rs.getString(2));
			}
		//Whenever you work with sql, this try-catch block has to be present	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return maps;
	}
	
	public static ArrayList<Road> getRoads(int mapID)
	{
		ArrayList<Road> roads = new ArrayList<Road>();
		try {
            s = conn.prepareStatement("select R.Distance, Cfrom.Name, Cfrom.PosX, Cfrom.PosY, Cto.Name, Cto.PosX, Cto.PosY " + 
            							"FROM ROAD R " + 
            							"INNER JOIN CITY Cfrom ON R.IDfrom = Cfrom.ID " + 
            							"INNER JOIN CITY Cto ON R.IDto = Cto.ID " + 
            							"WHERE R.MapID = " + mapID);
			rs = s.executeQuery();
			while (rs.next()) 
			{
				//While there are rows, save them into the ArrayList
				roads.add(new Road(Integer.parseInt(rs.getString(1)), 
						Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)), 
						Integer.parseInt(rs.getString(6)), Integer.parseInt(rs.getString(7))));
			}
		
		//Whenever you work with sql, this try-catch block has to be present	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return roads;
	}
	

	

}
