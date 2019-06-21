package application;


import java.sql.*;
import java.util.ArrayList;

public class SQL {
	private static Connection conn = null;
	private static PreparedStatement s = null;
	private static ResultSet rs = null;
	
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String connectionDescriptor =
			"jdbc:sqlserver://I-MSSQL-01;databasename=kratzer_db;integratedSecurity=true";
	
	//Establish a test connection between
	public static void connect() {

		try {
			
			// Establish JDBC connection
			Class.forName(driverClass);
			conn =  DriverManager.getConnection(connectionDescriptor, "", "");
		//Whenever you work with sql, this try-catch block has to be present
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
			
			// Establish JDBC connection
			Class.forName(driverClass);
			conn =  DriverManager.getConnection(connectionDescriptor, "", "");
			
            s = conn.prepareStatement("SELECT * " +
										"FROM CITY " +
            							"WHERE MapId = " + mapID);
			rs = s.executeQuery();
			while (rs.next()) {
				//While there are rows, save them into the ArrayList
				cities.add(new City(Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)), rs.getString(3), rs.getString(2)));
			}
			
		//Whenever you work with sql, this try-catch block has to be present	
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			try {
				if (conn != null) {
					System.out.println("Connection in getCities is closed.");
					conn.close();
					return cities;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}
	
	//Same logic as the getCities() method
	public static ArrayList<Map> getMaps()
	{
		ArrayList<Map> maps = new ArrayList<Map>();
		try {
			
			// Establish JDBC connection
			Class.forName(driverClass);
			conn =  DriverManager.getConnection(connectionDescriptor, "", "");
			
            s = conn.prepareStatement("SELECT * " +
										"FROM MAP");
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
					System.out.println("Connection in getMaps is closed.");
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
			// Establish JDBC connection
			Class.forName(driverClass);
			conn =  DriverManager.getConnection(connectionDescriptor, "", "");
			
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
					System.out.println("Connection in getRoads is closed.");
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
