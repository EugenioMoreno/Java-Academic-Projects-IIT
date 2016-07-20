package iit.edu.itmd510.mp2;

public class RailRoad extends Space {
	
	public int railRoadVisited;
	public static final int extraCredit=200;
	public RailRoad(int i, String type, String name, int credit) {
		super(i, type, name, credit);
		// TODO Auto-generated constructor stub
		railRoadVisited=0;
	}
	public int getRailRoadVisited() {
		return railRoadVisited;
	}
	public void setRailRoadVisited(int railRoadVisited) {
		this.railRoadVisited = railRoadVisited;
	}
	@Override
	public int getCreditPaid() {
		//In case we go to the jail and pass through more than once in some RailRoad space
		if (railRoadVisited>4){
			setRailRoadVisited(4);
		}
		return extraCredit*getRailRoadVisited();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " awards " +railRoadVisited+" x "+extraCredit+" = "+getCreditPaid()+" credits.";
	}
}
