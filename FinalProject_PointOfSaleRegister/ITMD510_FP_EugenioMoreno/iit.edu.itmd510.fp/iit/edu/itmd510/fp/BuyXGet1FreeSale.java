package iit.edu.itmd510.fp;

import java.util.ArrayList;

public class BuyXGet1FreeSale extends Sale {
	private int itemsForGetting1free;
	private static final double freePrice=0.00;
	public static int freeItems;
	public static int regularPriceItems;
	public static int payedItems;
	public BuyXGet1FreeSale(String sku, String typeOfSale, int itemsForGetting1Free) {
		super(sku, typeOfSale);
		this.itemsForGetting1free=itemsForGetting1Free;
		calculateFreeItem();
	}
	//Getters
	public int getItemsForGetting1free() {
		return itemsForGetting1free;
	}
	@Override
	public double getTotalSalePrice() {
		// TODO Auto-generated method stub
		//System.out.println("Payed items: "+payedItems);
		calculateFreeItem();
		return truncateNumber(payedItems*getUnitSalePrice());
	}
	//Setters
	public void setItemsForGetting1free(int itemsForGetting1free) {
		this.itemsForGetting1free = itemsForGetting1free;
	}
	public String freeItemEntry(){
		return getSku()+" "+getItemDescription()+" "+1+"@"+df.format(freePrice)+"="+df.format(freePrice);
	}
	public String regularItemEntry(int nItems){
		return getSku()+" "+getItemDescription()+" "+nItems+"@"+df.format(getUnitSalePrice())+"="+df.format(nItems*getUnitSalePrice());
	}
	public void calculateFreeItem(){
		int purchasedItems=getNumberOfItems();
		freeItems=0;
		regularPriceItems=0;
		
		int divisor=itemsForGetting1free+1;
		int quotient=getNumberOfItems()/divisor;
		freeItems=quotient;
		regularPriceItems=purchasedItems-freeItems;
		payedItems=regularPriceItems;
		//System.out.println("Free items:"+freeItems);
		//System.out.println("Regular price items:"+regularPriceItems);
	}
	@Override
	public String toString() {
		calculateFreeItem();
		ArrayList<String> registerTapeEntry= new ArrayList<String>();
		//Create the text depending on the number of items
		if(freeItems>=1){
			createDisplayMessage(registerTapeEntry);
			//Print all the lines in the text area
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<registerTapeEntry.size();i++){
				if(i==registerTapeEntry.size()-1){
					sb.append(registerTapeEntry.get(i));
				}else{
					sb.append(registerTapeEntry.get(i)+"\r\n");
				}
			}
			//System.out.println(getTotalSalePrice());
			return sb.toString();
		}else{
			return super.toString();
		}
	}
	
	private void createDisplayMessage(ArrayList<String> registerTapeEntry) {
		while(regularPriceItems>0){
			if(regularPriceItems>=itemsForGetting1free){
				registerTapeEntry.add(regularItemEntry(itemsForGetting1free));
				regularPriceItems=regularPriceItems-itemsForGetting1free;
				if (freeItems>0) {
					registerTapeEntry.add(freeItemEntry());
					freeItems--;
				}
			}else{
				registerTapeEntry.add(regularItemEntry(regularPriceItems));
				regularPriceItems=0;
			}
		}
	}
}
