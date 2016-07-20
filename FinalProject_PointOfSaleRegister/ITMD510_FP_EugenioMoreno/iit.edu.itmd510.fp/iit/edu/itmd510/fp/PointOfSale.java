package iit.edu.itmd510.fp;

/**
 * @author Eugenio Miguel Moreno Gonzalez A20363051
 */
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;

public class PointOfSale {
	
	private static String costsFilePath;
	private static String salesFilePath;
	public static String registerTapeFilePath;
	
	public static Item[] storeItems;
	public static Sale[] storeSales;
	
	public static final String saleType1="DISCOUNT";
	public static final String saleType2="BUYXGET1FREE";
	public static final String saleType3="BUYXFORY";
	public static final String saleRegular="REGULARSALE";
	
	//Booleans for checking files and SKU duplicates
	public static boolean validCostsFile=true;
	public static boolean validSalesFile=true;
	public static boolean duplicateItemSKU=false;
	public static boolean duplicateSaleSKU=false;
	public static String skuDuplicated;
	public static String invalidSku;
	
	//Map for the items of the store
	public static HashMap<String,Item> itemsSKUmap = new HashMap<String,Item>();
	//Map for the sales available in the store
	public static HashMap<String,Sale> salesSKUmap = new HashMap<String,Sale>();
	//Map for the current customer of the point of sale
	public static HashMap<String,Integer> registerSKUmap = new HashMap<String,Integer>();
	//Interface controller and frame
	public static RegisterTapeController controller;
	public static JFrame mainFrame;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		registerTapeFilePath=args[2];
		//openRegisterTapeFile(registerTapeFilePath);
		ArrayList<String> costsFile= new ArrayList<String>();
		//Read the file where items information of the store is placed
		costsFile=readCostsFile(args);
		
		ArrayList<String> salesFile= new ArrayList<String>();
		//Read the file where the sales of the store are placed
		salesFile=readSalesFile(args);
		

		stockConstructor(costsFile);
		//Remove comment for testing purposes
		//readRegisterTapeFile(args);
		salesRegistryConstructor(salesFile);
			
		
		if(duplicateItemSKU){
			System.out.println("There is SKU which has been duplicated in the Costs File: "+skuDuplicated);
		}else if (validCostsFile==false){
			System.out.println("There is SKU in the Costs File which is invalid due to its format: "+invalidSku);
		}else if(duplicateSaleSKU){
			System.out.println("There is SKU which has been duplicated in the Sales File: "+skuDuplicated);
		}else if (validSalesFile==false){
			System.out.println("There is SKU in the Sales File which is invalid due to its format: "+invalidSku);
		}else{
			//The controller is initialized 
			controller= new RegisterTapeController(registerSKUmap);
	
			//Open the GUI, call the main screen
			try {
				EventQueue.invokeAndWait(new Runnable(){
				    public void run(){
				    	mainFrame = new RegisterInterface();
				        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    }
				});
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Remove comment for testing purposes
			//controller.applySales();
		}
	}

	public static void openRegisterTapeFile(String registerTapeFilePath2) {
		Desktop dt=Desktop.getDesktop();
		File file=new File(registerTapeFilePath2);
		try {
			dt.open(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static ArrayList<String> readCostsFile(String[] args){
		costsFilePath=args[0];
		Scanner in=null;
		try {
			in=new Scanner(Paths.get(costsFilePath));
		} catch (IOException e) {
			System.out.println("Generation file path error");
		}
		//We should put the content into an array
		ArrayList<String> costsFile= new ArrayList<String>();
		while(in.hasNextLine()){	
			String line=in.nextLine();
			costsFile.add(line);
		}
		in.close();
		//Initialize the items buffer with the same size of the text file, excluding the header
		storeItems=new Item[costsFile.size()-1];
		return costsFile;
		
		
	}	
	
	public static ArrayList<String> readSalesFile(String[] args){
		salesFilePath=args[1];
		Scanner in=null;
		try {
			in=new Scanner(Paths.get(salesFilePath));
		} catch (IOException e) {
			System.out.println("Generation file path error");
		}
		//We should put the content into an array
		ArrayList<String> salesFile= new ArrayList<String>();
		while(in.hasNextLine()){	
			String line=in.nextLine();
			salesFile.add(line);
		}
		in.close();
		//Initialize the items buffer with the same size of the text file, excluding the header
		storeSales=new Sale[salesFile.size()-1];
		return salesFile;
	}
	
	public static void readRegisterTapeFile(String[] args){
		registerTapeFilePath=args[2];
		Scanner in=null;
		try {
			in=new Scanner(Paths.get(registerTapeFilePath));
		} catch (IOException e) {
			System.out.println("Generation file path error");
		}
		//Read how many items of each class are in the register
		while(in.hasNextLine()){	
			String line=in.nextLine();
			if(registerSKUmap.containsKey(line)){
				int newValue=registerSKUmap.get(line)+1;
				registerSKUmap.put(line, newValue);
			}else{
				registerSKUmap.put(line, 1);}
		}
		in.close();
		//System.out.println("THERE ARE: "+registerSKUmap.get("APPLES")+" "+itemsSKUmap.get("APPLES").getDescription());
	}
	
	public static void stockConstructor(ArrayList<String> costsFile){
		for (int i = 1; i <= storeItems.length; i++) {
			String [] aux=new String[costsFile.get(i).split("\\|").length];
			for (int j = 0; j < aux.length; j++) {
				aux[j]=costsFile.get(i).split("\\|")[j];//[SKU,Description,Unit price]
			}
			//Check if the SKU is a 6 capital letter field
			if (!aux[0].equals(aux[0].toUpperCase())||aux[0].length()!=6){
				validCostsFile=false;
				invalidSku=aux[0];
			}
			//Check for duplicates
			if(itemsSKUmap.containsKey(aux[0])){
				duplicateItemSKU=true;
				skuDuplicated=aux[0];
			}
			//Save the item  into the buffer
			double unitPrice=Double.parseDouble(aux[2])/100;
			storeItems[i-1]=new Item(aux[0],aux[1],unitPrice);
			//Add the item to the map with its SKU
			itemsSKUmap.put(aux[0], storeItems[i-1]);
			
			//System.out.println(storeItems[i-1].toString());
		}
	}
	public static void salesRegistryConstructor(ArrayList<String> salesFile){
		for (int i = 1; i <= storeSales.length; i++) {
			String [] aux=new String[salesFile.get(i).split("\\|").length];
			for (int j = 0; j < aux.length; j++) {
				aux[j]=salesFile.get(i).split("\\|")[j];//[SKU,Sale´s type,...]
			}
			//Check if the SKU is a 6 capital letter field
			if (!aux[0].equals(aux[0].toUpperCase())||aux[0].length()!=6){
				validSalesFile=false;
				invalidSku=aux[0];
			}
			//Check for duplicates
			if(salesSKUmap.containsKey(aux[0])){
				duplicateSaleSKU=true;
				skuDuplicated=aux[0];
			}
			//Create a sale depending on its type
			if(itemsSKUmap.containsKey(aux[0])){
				storeSales[i-1]=classifySale( aux);
			}else{System.out.println("Sale not applicable because this item does not exist in the store");}
			//Add the sale to a map with its SKU as a key
			salesSKUmap.put(aux[0], storeSales[i-1]);
			
			//System.out.println(storeSales[i-1].toString());
		}
		//If the item does not have any specific discount, it will be a regular sale
		for(String key: itemsSKUmap.keySet()){
			if(!salesSKUmap.containsKey(key)){
				Sale regularSale= new Sale(itemsSKUmap.get(key).getSku(), saleRegular);
				salesSKUmap.put(regularSale.getSku(), regularSale);
			}
		}
	}
	//Classify each sale depending on its type
	public static Sale classifySale(String [] s){
		String sku=s[0];
		String typeOfSale=s[1];
		switch (s[1]) {
		case saleType1:
			int discountRate=Integer.parseInt(s[2]);
			return new DiscountSale(sku, typeOfSale,discountRate);
		case saleType2:
			int itemsForGetting1Free=Integer.parseInt(s[2]);
			return new BuyXGet1FreeSale(sku, typeOfSale, itemsForGetting1Free);
		case saleType3:
			int buyX=Integer.parseInt(s[2]);
			int payY=Integer.parseInt(s[3]);
			return new BuyXForYSale(sku, typeOfSale, buyX, payY);
		default:
			return new Sale(sku,saleRegular);
		}
	}

}
