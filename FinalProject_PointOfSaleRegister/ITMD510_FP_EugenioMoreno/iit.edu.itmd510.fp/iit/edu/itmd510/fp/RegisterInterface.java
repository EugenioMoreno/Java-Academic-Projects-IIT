package iit.edu.itmd510.fp;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class RegisterInterface extends JFrame {
	
	private JPanel contentPane;
	private JPanel controlPane;
	
	JScrollPane scrollPanel;//new JScrollPane(panel);
	public static JTextArea registerTape;
	
	public static JTextField totalItemsTextField = new JTextField("",5);
	public static JTextField totalCostTextField = new JTextField("",10);
	public static JTextField sku = new JTextField("",10);
	private JLabel totalItemsLabel = new JLabel("Total Items: ");
	private JLabel totalCostLabel = new JLabel("Total Cost: ");
	private JLabel skuLabel = new JLabel("SKU: ");
	private JButton addItemButton = new JButton("Add Item");
	private JButton payButton = new JButton("Pay");
	private JButton voidButton = new JButton("Void");
	
	//Menu elements
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnRegistry = new JMenu("Registrer");
	private JMenuItem mntmExit = new JMenuItem("Exit");
	private JMenu mnHelp = new JMenu("Help");
	private JMenuItem mntmAbout = new JMenuItem("About");
	
	private JPanel controlPane1;
	private JPanel controlPane2;
	private JPanel controlPane3;

	public RegisterInterface(){
		super("Point of Sale Register");
		
		ActionListener exitListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	exitApplication();
            }
        };
        ActionListener helpListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	showAboutDialog();
            }
        };
        ActionListener payListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	PointOfSale.controller.closeOrder();
            }
        };
        ActionListener addItemListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	if(PointOfSale.itemsSKUmap.containsKey(sku.getText())){
            		PointOfSale.controller.addNewItem(sku.getText());
            	}else{
            		showInvalidSKUDialog();
            	}
            }
        };
        ActionListener voidListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	PointOfSale.controller.clearOrder();
            }
        };
		
		setUpMenu();
		mntmExit.addActionListener(exitListner);
		mntmAbout.addActionListener(helpListner);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2,1));
		
		registerTape=new JTextArea(10,80);
		registerTape.setEditable(false);
	
		scrollPanel=new JScrollPane(registerTape);
		contentPane.add(scrollPanel);
		
		controlPane = new JPanel();
		//setContentPane(controlPane);
		controlPane.setLayout(new GridLayout(3,1,200,0));
		contentPane.add(controlPane);
		
		controlPane1=new JPanel();
		controlPane1.add(totalItemsLabel);
		controlPane1.add(totalItemsTextField);
		controlPane1.add(totalCostLabel);
		controlPane1.add(totalCostTextField);
		totalCostTextField.setEditable(false);
		totalItemsTextField.setEditable(false);
		
		controlPane2=new JPanel();
		controlPane2.add(skuLabel);
		controlPane2.add(sku);
		controlPane2.add(addItemButton);
		addItemButton.addActionListener(addItemListner);
		
		controlPane3=new JPanel();
		controlPane3.add(payButton);
		payButton.addActionListener(payListner);
		controlPane3.add(voidButton);
		voidButton.addActionListener(voidListner);
		
		controlPane.add(controlPane1);
		controlPane.add(controlPane2);
		controlPane.add(controlPane3);
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	//Create the menu
	private void setUpMenu() {
		setJMenuBar(menuBar);
		menuBar.add(mnRegistry);
		mnRegistry.add(mntmExit);
		menuBar.add(mnHelp);
		mnHelp.add(mntmAbout);
	}
	
	//Close the application
	private void exitApplication(){
		PointOfSale.mainFrame.dispose();
	}
	//Show about information
	private void showAboutDialog(){
		UIManager.put("OptionPane.okButtonText", "OK");
		JOptionPane . showMessageDialog(getParent()," Eugenio Miguel Moreno Gonzalez\n"+" A20363051", " About",
		JOptionPane.PLAIN_MESSAGE);
	}
	//show  a dialog form with when the clerks introduces a wrong SKU
	public void showInvalidSKUDialog(){
		UIManager.put("OptionPane.okButtonText", "OK");
		JOptionPane . showMessageDialog(getParent()," Invalid SKU", " Invalid SKU ",JOptionPane.WARNING_MESSAGE);
	}
	//Fix frame dimension
	public Dimension getPreferredSize(){
		return new Dimension(900,450);
	}
}
