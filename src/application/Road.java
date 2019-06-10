package application;

public class Road {
	private int distance;
	private String idTo;
	private String idFrom;
	
	public Road(int distance, String idTo, String idFrom) {
		super();
		this.distance = distance;
		this.idTo = idTo;
		this.idFrom = idFrom;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getIdTo() {
		return idTo;
	}

	public void setIdTo(String idTo) {
		this.idTo = idTo;
	}

	public String getIdFrom() {
		return idFrom;
	}

	public void setIdFrom(String idFrom) {
		this.idFrom = idFrom;
	}
	
	

}
