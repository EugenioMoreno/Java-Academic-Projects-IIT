package iit.edu.itmd510.fp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Sale {

	private String sku;
	private String typeOfSale;
	private double unitSalePrice;
	public static DecimalFormat df;
	public static DecimalFormatSymbols dfs;
	public Sale(String sku, String typeOfSale) {
		this.sku=sku;
		this.typeOfSale=typeOfSale;
		this.unitSalePrice=PointOfSale.itemsSKUmap.get(sku).getUnitCost();
	
		//Decimal format
		df=new DecimalFormat("0.00");
		dfs = new DecimalFormatSymbols();
	    dfs.setDecimalSeparator('.');
	    df.setDecimalFormatSymbols(dfs);
	}
	//Getters
	public String getSku() {
		return this.sku;
	}
	public String getTypeOfSale() {
		return typeOfSale;
	}
	public int getNumberOfItems() {
		try {
			return PointOfSale.registerSKUmap.get(getSku());
		} catch (Exception e) {
			return 0;
		}
		//return PointOfSale.registerSKUmap.get(getSku());
		//return numberOfItems;
	}
	public double getUnitSalePrice() {
		return truncateNumber(unitSalePrice);
	}
	public double getTotalSalePrice() {
		return truncateNumber(getNumberOfItems()*getUnitSalePrice());
	}
	//Setters
	public void setUnitSalePrice(double salePrice) {
		this.unitSalePrice = salePrice;
	}
	
	public String getItemDescription(){
		return PointOfSale.itemsSKUmap.get(getSku()).getDescription();
	}
	
	public double truncateNumber(double decimalNumber) {
		decimalNumber=decimalNumber*100;
		int aux=(int) decimalNumber;
		decimalNumber=aux/100.00;
		return decimalNumber;
	}
	//To string for regular sales
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(PointOfSale.itemsSKUmap.containsKey(getSku())){
			return getSku()+" "+getItemDescription()+" "+getNumberOfItems()+"@"+df.format(getUnitSalePrice())+"="+df.format(getTotalSalePrice());
		}
		return "The Stock Keeping Unit (SKU) is not applicable to any of the current items of the store";
	}

}
