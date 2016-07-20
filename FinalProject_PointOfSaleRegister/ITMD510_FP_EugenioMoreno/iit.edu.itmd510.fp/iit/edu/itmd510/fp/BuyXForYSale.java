package iit.edu.itmd510.fp;

import java.util.ArrayList;

public class BuyXForYSale extends Sale {
	
	private int buyX;
	private int payY;
	public static int freeItems;
	public static int regularPriceItems;
	public static int payedItems;
	public BuyXForYSale(String sku, String typeOfSale, int buyX, int getY) {
		super(sku, typeOfSale);
		this.buyX=buyX;
		this.payY=getY;
		calculateFreeItem();
	}
	@Override
	public double getTotalSalePrice() {
		//System.out.println("Payed items: "+payedItems);
		calculateFreeItem();
		return truncateNumber(payedItems*getUnitSalePrice());
	}
	public void calculateFreeItem(){
		int purchasedItems=getNumberOfItems();
		freeItems=0;
		regularPriceItems=0;
		int divisor=buyX;
		int quotient=getNumberOfItems()/divisor;
		freeItems=quotient;
		regularPriceItems=purchasedItems-freeItems;
		payedItems=regularPriceItems;
	}
	public String extraItemEntry(){
		return getSku()+" "+getItemDescription()+" "+buyX+" for "+payY+"@"+df.format(getUnitSalePrice())+"="+df.format(payY*getUnitSalePrice());
	}
	public String regularItemEntry(int nItems){
		return getSku()+" "+getItemDescription()+" "+nItems+"@"+df.format(getUnitSalePrice())+"="+df.format(nItems*getUnitSalePrice());
	}
	@Override
	public String toString() {
		calculateFreeItem();
		int purchasedItems=getNumberOfItems();
		ArrayList<String> registerTapeEntry= new ArrayList<String>();
		if(freeItems>=1){
			while(purchasedItems>0){
				if(purchasedItems>=buyX){
					registerTapeEntry.add(extraItemEntry());
					purchasedItems=purchasedItems-buyX;
				}else{
					registerTapeEntry.add(regularItemEntry(purchasedItems));
					purchasedItems=0;
				}
			}
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<registerTapeEntry.size();i++){
				if(i==registerTapeEntry.size()-1){
					sb.append(registerTapeEntry.get(i));
				}else{
					sb.append(registerTapeEntry.get(i)+"\r\n");
				}
			}
			return sb.toString();
		}else{
			return super.toString();
		}
	}
}
