package iit.edu.itmd510.mp2;

public class Supplier extends Space {
	
	int die1;
	int die2;
	public static final int ELECTRICITYMULTIPLIER=5;
	public static final int WATERMULTIPLIER=10;
	//The amount of credit received depends on the die´s number
	public Supplier(int i, String type, String name, int credit) {
		super(i, type, name, credit);
		// TODO Auto-generated constructor stub
		die1=0;
		die2=0;
	}
	public int getDie1() {
		return die1;
	}
	public int getDie2() {
		return die2;
	}
	public void setDie1(int die1) {
		this.die1 = die1;
	}
	public void setDie2(int die2) {
		this.die2 = die2;
	}
	@Override
	public int getCreditPaid() {
	
		switch (this.name) {
		case "Electric Company":
			return (getDie1()+getDie2())*ELECTRICITYMULTIPLIER;
		case "Water Works":
			return (getDie1()+getDie2())*WATERMULTIPLIER;
		default:
			return 0;
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		switch (this.name) {
		case "Electric Company":
			return name + " awards (" +getDie1()+" + "+getDie2()+") x "+ELECTRICITYMULTIPLIER+
					" = "+getCreditPaid()+" credits.";
		case "Water Works":
			return name + " awards (" +getDie1()+" + "+getDie2()+") x "+WATERMULTIPLIER+
					" = "+getCreditPaid()+" credits.";
		default:
			return "";
		}
	}
}
