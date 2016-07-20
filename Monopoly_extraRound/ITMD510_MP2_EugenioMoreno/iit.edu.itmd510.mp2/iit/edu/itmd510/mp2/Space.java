package iit.edu.itmd510.mp2;

public class Space {

	/**
	 * @param args
	 */
	int boardIndex;
	String typeOfSpace;
	String name;
	int creditPaid;
	public Space(int i, String type, String name, int credit) {
		// TODO Auto-generated constructor stub
		this.boardIndex=i;
		this.typeOfSpace=type;
		this.name=name;
		this.creditPaid=credit;
	}
	//Getters
	public int getBoardIndex() {
		return boardIndex;
	}
	public int getCreditPaid() {
		return creditPaid;
	}
	public String getTypeOfSpace() {
		return typeOfSpace;
	}
	public String getName() {
		return name;
	}
	//Setter
	public void setBoardIndex(int boardIndex) {
		this.boardIndex = boardIndex;
	}
	public void setCreditPaid(int creditPaid) {
		this.creditPaid = creditPaid;
	}
	public void setTypeOfSpace(String typeOfSpace) {
		this.typeOfSpace = typeOfSpace;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " awards " +creditPaid+" credits.";
	}

}
