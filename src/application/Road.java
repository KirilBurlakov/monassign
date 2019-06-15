package application;

public class Road {
	private int distance;
	private int posXFrom;
	private int posYFrom;
	private int posXTo;
	private int posYTo;

	



	public Road(int distance, int posXFrom, int posYFrom, int posXTo, int posYTo) {
		super();
		this.distance = distance;
		this.posXFrom = posXFrom;
		this.posYFrom = posYFrom;
		this.posXTo = posXTo;
		this.posYTo = posYTo;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getPosXTo() {
		return posXTo;
	}

	public void setPosXTo(int posXTo) {
		this.posXTo = posXTo;
	}

	public int getPosYTo() {
		return posYTo;
	}

	public void setPosYTo(int posYTo) {
		this.posYTo = posYTo;
	}

	public int getPosXFrom() {
		return posXFrom;
	}

	public void setPosXFrom(int posXFrom) {
		this.posXFrom = posXFrom;
	}

	public int getPosYFrom() {
		return posYFrom;
	}

	public void setPosYFrom(int posYFrom) {
		this.posYFrom = posYFrom;
	}
	
	

}
