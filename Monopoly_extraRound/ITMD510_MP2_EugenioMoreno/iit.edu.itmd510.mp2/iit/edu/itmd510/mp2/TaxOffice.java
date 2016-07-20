package iit.edu.itmd510.mp2;

public class TaxOffice extends Space {
	public static final int INCOMETAX=200;
	public static final int LUXURYTAX=100;
	public TaxOffice(int i, String type, String name, int credit) {
		super(i, type, name, credit);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCreditPaid() {
		// TODO Auto-generated method stub
		switch (this.name) {
		case "Income Tax":
			return INCOMETAX;
		case "Luxury Tax":
			return LUXURYTAX;
		default:
			return 0;
		}
	}
}
