package iit.edu.itmd510.mp2;

import java.util.concurrent.ThreadLocalRandom;

public class CommunityChest extends Space {
	
	public static final int NUMBERofCARDS=5;
	private static final int BANKERROR = 200;
	private static final int STOCKSALE = 50;
	private static final int HOLIDAYFUND = 100;
	private static final int TAXREFUND = 20;
	public static String CommunityCard;
	public CommunityChest(int i, String type, String name, int credit) {
		super(i, type, name, credit);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCreditPaid() {
		// TODO Auto-generated method stub
		return super.getCreditPaid();
	}
	public static String getCommunityCard() {
		return CommunityCard;
	}
	@Override
	public void setCreditPaid(int creditPaid) {
		// TODO Auto-generated method stub
		super.setCreditPaid(creditPaid);
	}
	public static void setCommunityCard(String communityCard) {
		CommunityCard = communityCard;
	}
	//User picks a random card
	public void pickACard(){
		int index=ThreadLocalRandom.current().nextInt(1,NUMBERofCARDS+1);
		switch (index) {
		case 1://Bank error
			super.setCreditPaid(BANKERROR);
			setCommunityCard("Bank error in your favor. Collect "+ BANKERROR+" credits");
			break;
		case 2://Sale of stocks
			super.setCreditPaid(STOCKSALE);
			setCommunityCard("From sale of stock, you get "+ STOCKSALE+" credits");
			break;
		case 3://Player goes directly to jail
			Player.tokenPostion=10;
			setCommunityCard("Go directly to Jail");
			break;
		case 4://Player wins a prize
			super.setCreditPaid(HOLIDAYFUND);
			setCommunityCard("Holiday Fund matures. Receive "+HOLIDAYFUND+" credits.");
			break;
		case 5://Player has a tax refund
			super.setCreditPaid(TAXREFUND);
			setCommunityCard("Income tax refund. Collect "+TAXREFUND+" credits.");
			break;
		default:
			break;
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getCommunityCard();
	}

}
