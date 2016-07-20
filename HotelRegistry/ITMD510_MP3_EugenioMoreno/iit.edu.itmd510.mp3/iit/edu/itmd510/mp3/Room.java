package iit.edu.itmd510.mp3;

import java.io.ObjectInputStream.GetField;

public class Room {

	private int roomNumber;
	private String roomType;
	private int maxOccupancy;
	private boolean available;
	//Constructor
	public Room(int roomNumber, String roomType, int maxOccupancy,boolean available){
		this.roomNumber=roomNumber;
		this.roomType=roomType;
		this.maxOccupancy=maxOccupancy;
		this.available=available;
	}
	//Getters
	public int getRoomNumber() {
		return roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public boolean getAvailable(){
		return available;
	}
	//Setters
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
