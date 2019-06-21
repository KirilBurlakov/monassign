package application;

public class Map {
	private int MapID;
	private String name;
	
	public Map(int mapID, String name) {
		MapID = mapID;
		this.name = name;
	}
	
	public int getMapID() {
		return MapID;
	}
	
	public void setMapID(int mapID) {
		MapID = mapID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
