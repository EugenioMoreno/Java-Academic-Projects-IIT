package iit.edu.itmd510.fp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;

public class RegisterTapeController {

	public HashMap<String,Integer> registerSKUmap;
	public static double totalCost=0.00;
	public static int totalItems=0;
	public static DecimalFormat df;
	public static DecimalFormatSymbols dfs;
	public static PrintWriter out;
	public RegisterTapeController(HashMap<String,Integer> registerSKUmap) {
		this.registerSKUmap=registerSKUmap;
		
		//Decimal format
		df=new DecimalFormat("0.00");
		dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		
		try {
			out = new PrintWriter (new FileWriter (PointOfSale.registerTapeFilePath,true),true);//True for attending
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Get the map
	public HashMap<String, Integer> getRegisterSKUmap() {
		return registerSKUmap;
	}
	//Apply current sales
	public void applySales(){
		//Clean Text area of the interface
		RegisterInterface.registerTape.setText("");
		
		for(String key: registerSKUmap.keySet()){
			
			int numberOfItems=registerSKUmap.get(key);
			Sale auxSale=PointOfSale.salesSKUmap.get(key);
			totalItems=totalItems+numberOfItems;
			totalCost=totalCost+auxSale.getTotalSalePrice();
			
			System.out.println(auxSale.toString());
			//Write the paint in the text area with the format of each type of sale (Polymorphism)
			RegisterInterface.registerTape.append(auxSale.toString()+"\n\r");
		}
		//Update total items and total cost of the interface
		RegisterInterface.totalCostTextField.setText("$"+df.format(totalCost));
		RegisterInterface.totalItemsTextField.setText(""+totalItems);
		System.out.println("Total Items: "+totalItems);
		System.out.println("Total Cost: $"+df.format(truncateNumber(totalCost)));
	}
	public void updateTextArea(){
		RegisterInterface.registerTape.setText("");
		
		for(String key: registerSKUmap.keySet()){
			int numberOfItems=registerSKUmap.get(key);
			Sale auxSale=PointOfSale.salesSKUmap.get(key);
			totalItems=totalItems+numberOfItems;
			totalCost=totalCost+auxSale.getTotalSalePrice();
			System.out.println(auxSale.toString());
			//Write the paint in the text area with the format of each type of sale (Polymorphism)
			RegisterInterface.registerTape.append(auxSale.toString()+"\r\n");
		}
		//Update total items and total cost of the interface
		RegisterInterface.totalCostTextField.setText("$"+df.format(totalCost));
		RegisterInterface.totalItemsTextField.setText(""+totalItems);
	}
	public void addNewItem(String sku){
		if(registerSKUmap.containsKey(sku)){
			int newValue=registerSKUmap.get(sku)+1;
			//Update maps with the number of items
			registerSKUmap.put(sku, newValue);
			PointOfSale.registerSKUmap.put(sku, newValue);
		}else{
			//If it is the first item of this type, create a new pair with 1 item
			registerSKUmap.put(sku, 1);
			PointOfSale.registerSKUmap.put(sku, 1);
		}
		//Update the text area because some special sale could be apply
		totalItems=0;
		totalCost=0.00;
		clearAreaText();
		updateTextArea();
		//applySales();
	}
	public void clearAreaText(){
		RegisterInterface.registerTape.setText("");
	}
	//Print order completion and write the register tape file
	public void closeOrder(){
		RegisterInterface.registerTape.append("Total Items: "+totalItems+"\r\n");
		RegisterInterface.registerTape.append("Total Cost: $"+df.format(truncateNumber(totalCost))+"\r\n");
		RegisterInterface.registerTape.append("+-------------------------------------------------------+"+"\r\n");
		
		out.print(RegisterInterface.registerTape.getText());
		out.print("\n\r");
		out.flush();
		clearOrder();
		PointOfSale.openRegisterTapeFile(PointOfSale.registerTapeFilePath);
	}
	//Clear the interface and maps
	public void clearOrder(){
		RegisterInterface.registerTape.setText("");
		RegisterInterface.totalCostTextField.setText("");
		RegisterInterface.totalItemsTextField.setText("");
		RegisterInterface.sku.setText("");
		totalItems=0;
		totalCost=0.00;
		registerSKUmap.clear();
		PointOfSale.registerSKUmap.clear();
	}
	//Truncate number with 2 decimal number
	public double truncateNumber(double decimalNumber) {
		decimalNumber=decimalNumber*100;
		int aux=(int) decimalNumber;
		decimalNumber=aux/100.00;
		return decimalNumber;
	}

}
