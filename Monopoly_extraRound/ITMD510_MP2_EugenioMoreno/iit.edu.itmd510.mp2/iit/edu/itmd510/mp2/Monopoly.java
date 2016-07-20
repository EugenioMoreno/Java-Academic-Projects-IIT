package iit.edu.itmd510.mp2;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Eugenio Miguel Moreno Gonzalez
 */
public class Monopoly {

	/**
	 * @param args
	 */
	public static boolean randomGame=true;
	public static final String optionalArgument="-dice";
	public static Space[] board= new Space[40];//Game Board
	public static int [] currentDieFaces= new int[2];
	public static final int numberOfFaces=6;//6-sided dies
	public static int nPlayers=0;
	public static String generationFilePath;
	public static final String PROPERTY="Property";
	public static final String TAXOFFICE="TaxOffice";
	public static final String RAILROAD="RailRoad";
	public static final String SUPPLIER="Supplier";
	public static final String CHANCE="Chance";
	public static final String COMCHEST="CommunityChest";
	
	//Getter
	public static int[] getCurrentDieFaces() {
		return currentDieFaces;
	}
	//Setters	
	public static void setCurrentDieFaces(int[] currentDieFaces) {
		Monopoly.currentDieFaces[0] = currentDieFaces[0];
		Monopoly.currentDieFaces[1] = currentDieFaces[1];
	}
	public static void setRandomGame(boolean randomGame) {
		Monopoly.randomGame = randomGame;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> boardFile= new ArrayList<String>();
		//Read the file where the board information is placed
		boardFile=readFile(args);
		//The is created with the file information
		boardConstructor(boardFile);
		//Check if the additional argument -dice has been written
		getGameMode(args);
		//create a new player and play
		Player player=new Player("Player "+ ++nPlayers);
		player.play();
		//Print the result once the bonus is finished
		System.out.println(player.toString());
	}
	//Create the board game from the txt file
	public static void boardConstructor(ArrayList<String> boardFile){
		//Finally we analyze the information of the txt file for obtaining the game board
		for (int i = 1; i <= board.length; i++) {
			String [] aux=new String[boardFile.get(i).split(";").length];
			for (int j = 0; j < aux.length; j++) {
				aux[j]=boardFile.get(i).split(";")[j];//[Index;Type;Name;Credit]
			}
			board[Integer.parseInt(aux[0])]=classifySpace(aux);
		}
	}
	//Read the txt file
	public static ArrayList<String> readFile(String[] args){
		generationFilePath=args[0];
		Scanner in=null;
		try {
			in=new Scanner(Paths.get(generationFilePath));
		} catch (IOException e) {
			System.out.println("Generation file path error");
		}
		//We should put the content into an array
		ArrayList<String> boardFile= new ArrayList<String>();
		while(in.hasNextLine()){	
			String line=in.nextLine();
			boardFile.add(line);
		}
		in.close();
		return boardFile;
	}
	//This method classifies and creates Spaces using polymorphism
	public static Space classifySpace(String [] s){
		switch (s[1]) {
		case PROPERTY:
			return new Property(Integer.parseInt(s[0]), s[1], s[2], Integer.parseInt(s[3]));
		case TAXOFFICE:
			return new TaxOffice(Integer.parseInt(s[0]), s[1], s[2], Integer.parseInt(s[3]));
		case RAILROAD:
			return new RailRoad(Integer.parseInt(s[0]), s[1], s[2], Integer.parseInt(s[3]));
		case SUPPLIER:
			return new Supplier(Integer.parseInt(s[0]), s[1], s[2], Integer.parseInt(s[3]));
		case CHANCE:
			return new Chance(Integer.parseInt(s[0]), s[1], s[2], Integer.parseInt(s[3]));
		case COMCHEST:
			return new CommunityChest(Integer.parseInt(s[0]), s[1], s[2], Integer.parseInt(s[3]));
		default:
			return new Space(Integer.parseInt(s[0]), s[1], s[2], Integer.parseInt(s[3]));
		}
	}
	//Look for the additional command option -dice
	public static void getGameMode(String[] args){
		String manualOption="";
		//It should be the last argument
		try {
			manualOption=args[args.length-1];
			if(manualOption.equals(optionalArgument)){
				randomGame=false;}
		} catch (Exception e) {
			randomGame=true;// TODO: handle exception
		}
	}
}
