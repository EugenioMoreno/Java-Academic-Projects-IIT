package iit.edu.itmd510.mp3;

import java.awt.EventQueue;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;


public class HotelRegistry {
	
	public static String generationFilePath;
	//Room buffers
	public static int oneKingBedRoomAvailable=0;
	public static int oneQueenBedRoomAvailable=0;
	public static int doubleBedRoomAvailable=0;
	public static int doubleBedOneCotRoomAvailable=0;
	public static int totalRoomsAvailable=0;
	//Room types
	public static final String type1="One King Bed";
	public static final String type2="One Queen Bed";
	public static final String type3="Two Double Beds";
	public static final String type4="Two Double Beds and One Cot";
	public static int MAXtype1=2;//Maximum occupancy for rooms type1
	public static int MAXtype2=2;//Maximum occupancy for rooms type2
	public static int MAXtype3=4;//Maximum occupancy for rooms type3
	public static int MAXtype4=5;//Maximum occupancy for rooms type4
	public static Room[] hotelRooms;
	public static JFrame mainFrame; //Main frame of the application

	//Getters
	public static int getOneKingBedRoomAvailable() {
		return oneKingBedRoomAvailable;
	}
	public static int getOneQueenBedRoomAvailable() {
		return oneQueenBedRoomAvailable;
	}
	public static int getDoubleBedRoomAvailable() {
		return doubleBedRoomAvailable;
	}
	public static int getDoubleBedOneCotRoomAvailable() {
		return doubleBedOneCotRoomAvailable;
	}
	public static Room[] getHotelRooms() {
		return hotelRooms;
	}
	public static int getTotalRoomsAvailable() {
		return totalRoomsAvailable;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> roomFile= new ArrayList<String>();
		//Read the file where the hotel room information is placed
		roomFile=readFile(args);
		registryConstructor(roomFile);
		//Call the main screen
		EventQueue.invokeLater(new Runnable(){
            public void run(){
            	mainFrame = new RegistryInterface();
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
	}
	//Read file
	public static ArrayList<String> readFile(String[] args){
		generationFilePath=args[0];
		Scanner in=null;
		try {
			in=new Scanner(Paths.get(generationFilePath));
		} catch (IOException e) {
			System.out.println("Generation file path error");
		}
		//We should put the content into an array
		ArrayList<String> roomFile= new ArrayList<String>();
		while(in.hasNextLine()){	
			String line=in.nextLine();
			roomFile.add(line);
		}
		in.close();
		hotelRooms=new Room[roomFile.size()-1];
		return roomFile;
	}
	//Create the room registry from the txt file
	public static void registryConstructor(ArrayList<String> roomFile){
		//Finally we analyze the information of the txt file for obtaining the available rooms
		for (int i = 1; i <= hotelRooms.length; i++) {
			String [] aux=new String[roomFile.get(i).split(",").length];
			for (int j = 0; j < aux.length; j++) {
				aux[j]=roomFile.get(i).split(",")[j];//[Room Number,Room Type,Maximum Occupancy, Availability]
			}
			int numberAux=Integer.parseInt(aux[0]);
			int occupancyAux=Integer.parseInt(aux[2]);
			String roomType=aux[1];
			hotelRooms[i-1]=new Room(numberAux,roomType,occupancyAux,true);
			classifyRoom(roomType);
		}
	}
	//Add the room to the buffer of available rooms
	private static void classifyRoom(String roomType) {
		switch (roomType) {
		case type1:
			oneKingBedRoomAvailable++;
			totalRoomsAvailable++;
			break;
		case type2:
			oneQueenBedRoomAvailable++;
			totalRoomsAvailable++;
			break;
		case type3:
			doubleBedRoomAvailable++;
			totalRoomsAvailable++;
			break;
		case type4:
			doubleBedOneCotRoomAvailable++;
			totalRoomsAvailable++;
			break;
		default:
			break;
		}
	}
}
