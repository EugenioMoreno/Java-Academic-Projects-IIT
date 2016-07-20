package iit.edu.itmd510.fp;

public class DiscountSale extends Sale {
	private double discountRate;
	private double discountPrice;
	public DiscountSale(String sku, String typeOfSale,double discountRate) {
		super(sku, typeOfSale);
		this.discountRate=discountRate;
	}
	
	//Getters
	public double getDiscountRate() {
		return discountRate;
	}
	@Override
	public double getTotalSalePrice() {
		// TODO Auto-generated method stub
		return truncateNumber(getNumberOfItems()*getDiscountPrice());
	}
	//Setters
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	
	public double getDiscountPrice() {
		discountPrice=getUnitSalePrice()*(1-getDiscountRate()/100);
		//Truncate the number
		return truncateNumber(discountPrice);
	}

	@Override
	public String toString() {
		return getSku()+" "+getItemDescription()+" "+(int)getDiscountRate()+"% off "+
		getNumberOfItems()+"@"+df.format(getDiscountPrice())+"="+df.format(getTotalSalePrice());
	}

}
