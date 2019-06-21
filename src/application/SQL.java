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

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
	}
	
	public static ArrayList<City> getCities(int mapID)
	{
		ArrayList<City> cities = new ArrayList<City>();
		try {
			// db parameters
			String url = "jdbc:sqlite:C:/sql/maps.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);
            s = conn.prepareStatement("SELECT C.ID, C.Name, C.PosX, C.PosY " +
										"FROM CITY C " +
            							"WHERE MapId = " + mapID);
			rs = s.executeQuery();
			while (rs.next()) {
				//While there are rows, save them into the ArrayList
				cities.add(new City(Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)), rs.getString(2), rs.getString(1)));
			}
			
		//Whenever you work with sql, this try-catch block has to be present	
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			try {
				if (conn != null) {
					conn.close();
					return cities;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}
	
	//Same logic as the getCities() method, but saves just the name
	public static ArrayList<Map> getMaps()
	{
		ArrayList<Map> maps = new ArrayList<Map>();
		try {
			
			// db parameters
			String url = "jdbc:sqlite:C:/sql/maps.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);
            s = conn.prepareStatement("SELECT M.ID, M.Name " +
										"FROM MAP M");
			rs = s.executeQuery();
			while (rs.next()) {
				
				maps.add(new Map(Integer.parseInt(rs.getString(1)), rs.getString(2)));
			}
			
		//Whenever you work with sql, this try-catch block has to be present	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
					return maps;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}
	
	public static ArrayList<Road> getRoads(int mapID)
	{
		ArrayList<Road> roads = new ArrayList<Road>();
		try {
			// db parameters
			String url = "jdbc:sqlite:C:/sql/maps.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);
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
		}  finally {
			try {
				if (conn != null) {
					conn.close();
					return roads;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}
	

	

}
