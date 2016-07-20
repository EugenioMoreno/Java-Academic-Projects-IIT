package iit.edu.itmd510.mp2;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
/**
 * @author Eugenio Miguel Moreno Gonzalez
 */
public class Player {
	
	public static String name;
	public static int tokenPostion=0;
	public static int credit=0;
	public static boolean endBonusGame=false;
	public static int railRoadVisited=0;
	
	//Constructor with the name of the player created
	public Player(String name) {
		// TODO Auto-generated constructor stub
		Player.name=name;
	}
	//Getters
	public static String getName() {
		return name;
	}
	public int getTokenPostion() {
		return tokenPostion;
	}
	public int getCredit() {
		return credit;
	}
	public int getRailRoadVisited() {
		return railRoadVisited;
	}
	//Setters
	public static void setName(String name) {
		Player.name = name;
	}
	public void setTokenPostion(int tokenPostion) {
		Player.tokenPostion = tokenPostion;
	}
	public void setCredit(int credit) {
		Player.credit = credit;
	}
	public void setRailRoadVisited(int railRoadVisited) {
		Player.railRoadVisited = railRoadVisited;
	}
	//Message printed when the round is finished
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Total credit won by"+" "+name+": "+getCredit() +" credits.";
	}
	//Play a bonus round
	public void play(){
		int[]currentDies=new int[2];
		//Select the game mode, manual or random depends on the arguments
		while(endBonusGame==false){
			if(Monopoly.randomGame==true){
				currentDies=rollDies();
			}else{
				currentDies=manualDies();
			}
			int die1=currentDies[0];
			int die2=currentDies[1];
			moveToken(die1, die2);
		}
	}
	//Move to the position result of rolling the dies and evaluate this space
	public void moveToken(int die1, int die2) {
		//Update token position
		tokenPostion=tokenPostion+die1+die2;
		//If the user passes through Go, the round finishes
		if(tokenPostion>=Monopoly.board.length){
			System.out.println(name+" passes "+Monopoly.board[0].getName()+" and is awared 200 credits");
			endBonusGame=true;
			credit=credit+Monopoly.board[0].getCreditPaid();
			setCredit(credit);
		}//If token is sent to the Go to the jail space, then the new position will be Jail
		else if(tokenPostion==30){
			tokenPostion=10;
			System.out.println("Go directly to Jail . Do not pass Go , do not collect 200 credits .");
		}else{
			//We look for special spaces
			Space currentSpace=Monopoly.board[tokenPostion];
			if(currentSpace instanceof RailRoad){
				if(railRoadVisited<4){
					railRoadVisited++;}
				((RailRoad) currentSpace).setRailRoadVisited(railRoadVisited);
			}else if(currentSpace instanceof Supplier){
				((Supplier) currentSpace).setDie1(die1);
				((Supplier) currentSpace).setDie2(die2);
			}else if(currentSpace instanceof Chance){
				((Chance) currentSpace).pickACard();
			}else if(currentSpace instanceof CommunityChest){
				((CommunityChest) currentSpace).pickACard();
			}
			//Inform about the space where the user has landed
			System.out.println(name+" landed on "+currentSpace.getName());
			//Print the space result
			System.out.println(currentSpace.toString());
			//Update the user´s credit
			credit=credit+currentSpace.getCreditPaid();	
		}
	}
	//Roll dies for getting random numbers
	public int[] rollDies(){
		int[] currentDieFaces= new int[2];
		for (int i = 0; i < currentDieFaces.length; i++) {
			currentDieFaces[i]=ThreadLocalRandom.current().nextInt(1,Monopoly.numberOfFaces+1);
		}
		int sum=currentDieFaces[0]+currentDieFaces[1];
		System.out.println(name+" rolled "+currentDieFaces[0]+" and "+currentDieFaces[1]+" = "+sum);
		return currentDieFaces;
	}
	//Get dies from the console, where the user should set the values.
	public int[] manualDies(){
		Scanner scanner = new Scanner ( System .in );
		int[] currentDieFaces= new int[2];
		
		System.out.print ("Please enter your dice roll:");
		String dies=scanner.nextLine();
		currentDieFaces[0]=Integer.parseInt(dies.split(" ")[0]);
		currentDieFaces[1]=Integer.parseInt(dies.split(" ")[1]);
		if(endBonusGame==true){
			scanner.close();
		}
		return currentDieFaces;
	}
}
