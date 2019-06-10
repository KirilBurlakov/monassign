package application;

public class City {
	private int posX;
	private int posY;
	private String name;
	private String ID;
	
	public City(int posX, int posY, String name, String ID)
	{
		this.posX = posX;
		this.posY = posY;
		this.name = name;
		this.ID = ID;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
}
