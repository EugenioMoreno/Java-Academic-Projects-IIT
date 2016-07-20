package iit.edu.itmd510.mp2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class MonopolyTest {
	public static Monopoly monopoly;
	public static Player player;
	public static String [] arguments;
	public static ArrayList<String> board=new ArrayList<>();
	//Create the board for testing
	private static void addSpaces(){
		board.add("Test Board");
		board.add("0;Space;GO;200");
		board.add("1;Property;Mediterranean Avenue;60");
		board.add("3;Property;Baltic Avenue;60");
		board.add("6;Property;Oriental Avenue;100");
		board.add("8;Property;Vermont Avenue;100");
		board.add("9;Property;Connecticut Avenue;120");
		board.add("11;Property;St. Charles Place;140");
		board.add("13;Property;States Avenue;140");
		board.add("14;Property;Virginia Avenue;160");
		board.add("16;Property;St. James Place;180");
		board.add("18;Property;Tennessee Avenue;180");
		board.add("19;Property;New York Avenue;200");
		board.add("21;Property;Kentucky Avenue;220");
		board.add("23;Property;Indiana Avenue;220");
		board.add("24;Property;Illinois Avenue;240");
		board.add("26;Property;Atlantic Avenue;260");
		board.add("27;Property;Ventnor Avenue;260");
		board.add("29;Property;Marvin Gardens;280");
		board.add("31;Property;Pacific Avenue;300");
		board.add("32;Property;North Carolina Avenue;300");
		board.add("34;Property;Pennsylvania Avenue;320");
		board.add("37;Property;Park Place;350");
		board.add("39;Property;Boardwalk;400");
		board.add("4;TaxOffice;Income Tax;200");
		board.add("38;TaxOffice;Luxury Tax;100");
		board.add("5;RailRoad;Reading Railroad;200");
		board.add("15;RailRoad;Pennsylvania Railroad;400");
		board.add("25;RailRoad;B&O Railroad;600");
		board.add("35;RailRoad;Short Line Railroad;800");
		board.add("12;Supplier;Electric Company;5");
		board.add("28;Supplier;Water Works;10");
		board.add("30;GoToJail;Go to Jail;0");
		board.add("10;Space;Jail ;0");
		board.add("20;Space;Free Parking ;0");
		board.add("7;Chance;Chance;0");
		board.add("22;Chance;Chance;0");
		board.add("36;Chance;Chance;0");
		board.add("2;CommunityChest;CommunityChest;0");
		board.add("17;CommunityChest;CommunityChest;0");
		board.add("33;CommunityChest;CommunityChest;0");
	}

	public void setUp() {
		addSpaces();
        Monopoly.boardConstructor(board);
        player= new Player("Test Player");
        player.setTokenPostion(0);
        player.setCredit(0);
    }
	
	@Test
	public void landingOnOrientalAvenuePays100Credits() {
		//Given
		setUp();
		//When
		player.moveToken(2, 4);
		//Then
		assertEquals(100, player.getCredit());
		System.out.println("Test OK");
	}
	@Test
	public void landingOnReadingRaildRoadPays200Credits() {
		//Given
		setUp();
		player.setRailRoadVisited(0);
		//When
		player.moveToken(2, 3);
		//Then
		assertEquals(200, player.getCredit());
		System.out.println("Test OK");
	}
	@Test
	public void landingOnTwoRailRoadStationsPays400Credits() {
		//Given
		setUp();
		player.setRailRoadVisited(0);
		player.moveToken(2, 3);
		player.setCredit(0);
		//When
		player.moveToken(5, 5);
		//Then
		assertEquals(400, player.getCredit());
		System.out.println("Test OK");
	}
	@Test	
	public void landingOnThreeRailRoadStationsPays600Credits() {
		//Given
		setUp();
		player.setRailRoadVisited(0);
		player.moveToken(2, 3);
		player.moveToken(5, 5);
		player.setCredit(0);
		//When
		player.moveToken(6, 4);
		//Then
		assertEquals(600, player.getCredit());
		System.out.println("Test OK");
	}
	@Test
	public void landingOnFourRailRoadStationsPays800Credits() {
		//Given
		setUp();
		player.setRailRoadVisited(0);
		player.moveToken(2, 3);
		player.moveToken(5, 5);
		player.moveToken(6, 4);
		player.setCredit(0);
		//When
		player.moveToken(6, 4);
		//Then
		assertEquals(800, player.getCredit());
		System.out.println("Test OK");
	}
	@Test
	public void landingOnLuxuryTaxPays100Credits() {
		//Given
		setUp();
		//When
		player.moveToken(38, 0);
		//Then
		assertEquals(100, player.getCredit());
		System.out.println("Test OK");
	}
	@Test
	public void landingOnIncomeTaxPays200Credits() {
		//Given
		setUp();
		//When
		player.moveToken(3, 1);
		//Then
		assertEquals(200, player.getCredit());
		System.out.println("Test OK");
	}
	@Test
	public void landingOnGoToJailMovesPlayerToJustVisiting() {
		//Given
		setUp();
		//When
		player.moveToken(30, 0);
		//Then
		assertEquals(0, player.getCredit());
		assertEquals(10, player.getTokenPostion());
		System.out.println("Test OK");
	}
	@Test
	public void passingThroughGoSpacePays200Credits() {
		//Given
		setUp();
		//When
		player.moveToken(40, 0);
		//Then
		assertEquals(200, player.getCredit());
		System.out.println("Test OK");
	}
	@Test
	public void landingOnElectricCompanyWith2And4Pays30Credits() {
		//Given
		setUp();
		player.moveToken(4, 2);
		player.setCredit(0);
		//When
		player.moveToken(2, 4);
		//Then
		assertEquals(30, player.getCredit());
		System.out.println("Test OK");
	}
	@Test
	public void landingOnWaterWorksWith2And3Pays50Credits() {
		//Given
		setUp();
		player.moveToken(23, 0);
		player.setCredit(0);
		//When
		player.moveToken(2, 3);
		//Then
		assertEquals(50, player.getCredit());
		System.out.println("Test OK");
	}
	
}
