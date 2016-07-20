package iit.edu.itmd510.fp;

public class Item {
	
	private String sku;
	private String description;
	private double unitCost;
	public Item(String sku,String description,double unitCost) {
		this.sku=sku;
		this.description=description;
		this.unitCost=unitCost;
	}
	//Getters
	public String getSku() {
		return sku;
	}
	public String getDescription() {
		return description;
	}
	public double getUnitCost() {
		return unitCost;
	}

	//Setters
	public void setSku(String sku) {
		this.sku = sku;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
}
