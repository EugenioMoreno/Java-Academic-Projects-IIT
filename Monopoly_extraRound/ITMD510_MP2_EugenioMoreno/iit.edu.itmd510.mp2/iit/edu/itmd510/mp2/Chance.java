package iit.edu.itmd510.mp2;

import java.util.concurrent.ThreadLocalRandom;

public class Chance extends Space {

	public static final int NUMBERofCARDS=5;
	private static final int BANKDIVIDEND = 50;
	private static final int CROSSWORDPRIZE = 10;
	private static final int CHAIRMANBONUS = 100;
	public static String chanceCard;
	public Chance(int i, String type, String name, int credit) {
		super(i, type, name, credit);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCreditPaid() {
		// TODO Auto-generated method stub
		return super.getCreditPaid();
	}
	@Override
	public void setCreditPaid(int creditPaid) {
		// TODO Auto-generated method stub
		super.setCreditPaid(creditPaid);
	}
	public static void setChanceCard(String chanceCard) {
		Chance.chanceCard = chanceCard;
	}
	//User picks a random card
	public void pickACard(){
		int index=ThreadLocalRandom.current().nextInt(1,NUMBERofCARDS+1);
		switch (index) {
		case 1://Player goes to jail
			Player.tokenPostion=10;
			setChanceCard("Go directly to Jail");
			break;
		case 2://Player earns a dividend
			super.setCreditPaid(BANKDIVIDEND);
			setChanceCard("Bank pay you a dividend of "+ BANKDIVIDEND+" credits");
			break;
		case 3://Player goes back 3 spaces
			Player.tokenPostion=Player.tokenPostion-3;
			setChanceCard("Go back 3 spaces");
			break;
		case 4://Player wins a prize
			super.setCreditPaid(CROSSWORDPRIZE);
			setChanceCard("You have won a crossword competition. Collect "+CROSSWORDPRIZE+" credits.");
			break;
		case 5://Player becomes chairman of the board
			super.setCreditPaid(CHAIRMANBONUS);
			setChanceCard("You have been elected chairman of the board. Collect "
			+CHAIRMANBONUS+" credits.");
			break;
		default:
			break;
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return chanceCard;
	}
}
