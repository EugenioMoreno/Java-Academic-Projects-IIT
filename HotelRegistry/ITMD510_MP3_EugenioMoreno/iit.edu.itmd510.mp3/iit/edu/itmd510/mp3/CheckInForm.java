package iit.edu.itmd510.mp3;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CheckInForm extends JFrame{
	 private JLabel firstNamelLabel = new JLabel("First Name: ");
	 private JTextField firstNameField = new JTextField("", 20);
	 private JLabel lastNamelLabel = new JLabel("Last Name: ");
	 private JTextField lastNameField = new JTextField("", 20);
	 
	 private JLabel addresslLabel = new JLabel("Address: ");
	 private JTextField addressField = new JTextField("", 50);
	 
	 private JLabel cityLabel = new JLabel("City: ");
	 private JTextField cityField = new JTextField("", 12);
	 private JLabel statelLabel = new JLabel("State: ");
	 private JTextField stateField = new JTextField("", 12);
	 private JLabel zipCodeLabel = new JLabel("ZIP Code: ");
	 private JTextField zipCodeField = new JTextField("", 8);
	 
	 private JLabel nAdultsLabel = new JLabel("Number of Adults: ");
	 private JLabel nChildrenLabel = new JLabel("Number of Children: ");
	 private JComboBox<String> nAdults;
	 private JComboBox<String> nChildren;
	 public static final int NADULTMAX=5;
	 public static final int NCHILDRENMAX=4;
	 
	 private ButtonGroup roomType; 
	 private JRadioButton kingRoom=new JRadioButton("King");
	 private JRadioButton oneQueenRoom=new JRadioButton("One Queen");
	 private JRadioButton twoDoubleBedRoom=new JRadioButton("Two Double Beds");
	 private JRadioButton twoDoubleOneCotRoom=new JRadioButton("Two Double Beds Plus Cot");
	 
	 private JLabel roomLabel = new JLabel("Room: ");
	 private JComboBox<String> availableRooms;
	 private JButton registerButton = new JButton("Register");
	 private JButton cancelButton = new JButton("Cancel");
	 
	 private JPanel personalDetailsPanel;
	 private JPanel addressPanel1;
	 private JPanel addressPanel2;
	 private JPanel guestPanel;
	 private JPanel roomTypePanel;
	 private JPanel confirmationPanel;
	 
	 private RegistryInterfaceController mainController;
	 private CheckInController checkInController;
	 //Create the interface
	public CheckInForm(){
		 super("Check In");
		 mainController=new RegistryInterfaceController();
		 checkInController= new CheckInController();
		 ActionListener optionsListener = new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                checkAvailableRooms();
	            }
	        };
	        ActionListener closeListener = new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                closeFrame();
	            }
	        };
	        ActionListener registerListener = new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                saveRecord();
	            }
	        };
		 //Create personal detail panel
		 personalDetailsPanel= new JPanel();
		 personalDetailsPanel.add(firstNamelLabel);
		 personalDetailsPanel.add(firstNameField);
		 personalDetailsPanel.add(lastNamelLabel);
		 personalDetailsPanel.add(lastNameField);
		 //Create address panel 1
		 addressPanel1= new JPanel();
		 addressPanel1.add(addresslLabel);
	     addressPanel1.add(addressField);
	     //Create address Panel 2
	     addressPanel2= new JPanel();
		 addressPanel2.add(cityLabel);
	     addressPanel2.add(cityField);
	     addressPanel2.add(statelLabel);
	     addressPanel2.add(stateField);
	     addressPanel2.add(zipCodeLabel);
	     addressPanel2.add(zipCodeField);
	     //Create Guest Panel
	     guestPanel= new JPanel();
	     nAdults= new JComboBox<>();
	     for (int i = 1; i <= NADULTMAX; i++) {
	    	 nAdults.addItem(""+i);
		}
	     nAdults.addActionListener(optionsListener);
	     nChildren= new JComboBox<>();
	     for (int i = 0; i <= NCHILDRENMAX; i++) {
	    	 nChildren.addItem(""+i);
		}
	     nChildren.addActionListener(optionsListener);
	     guestPanel.add(nAdultsLabel);
	     guestPanel.add(nAdults);
	     guestPanel.add(nChildrenLabel);
	     guestPanel.add(nChildren);
	     //Create room type panel
	     roomTypePanel=new JPanel();
	     roomType=new ButtonGroup();
	     roomType.add(kingRoom);
	     kingRoom.setActionCommand(HotelRegistry.type1);
	     kingRoom.addActionListener(optionsListener);
	     roomType.add(oneQueenRoom);
	     oneQueenRoom.setActionCommand(HotelRegistry.type2);
	     oneQueenRoom.addActionListener(optionsListener);
	     roomType.add(twoDoubleBedRoom);
	     twoDoubleBedRoom.setActionCommand(HotelRegistry.type3);
	     twoDoubleBedRoom.addActionListener(optionsListener);
	     roomType.add(twoDoubleOneCotRoom);
	     twoDoubleOneCotRoom.setActionCommand(HotelRegistry.type4);
	     twoDoubleOneCotRoom.addActionListener(optionsListener);
	     Border etched=BorderFactory.createEtchedBorder ();
	     Border titled=BorderFactory.createTitledBorder (etched , "Room Type");
	     roomTypePanel.setBorder(titled);
	     
	     roomTypePanel.add(kingRoom);
	     roomTypePanel.add(oneQueenRoom);
	     roomTypePanel.add(twoDoubleBedRoom);
	     roomTypePanel.add(twoDoubleOneCotRoom);
	     //Create confirmation panel
	     confirmationPanel=new JPanel();
	     confirmationPanel.add(roomLabel);
	     availableRooms= new JComboBox<>();
	     availableRooms.addItem("Select a room type");
	     confirmationPanel.add(availableRooms);
	     confirmationPanel.add(registerButton);
	     registerButton.addActionListener(registerListener);
	     confirmationPanel.add(cancelButton);
	     cancelButton.addActionListener(closeListener);
	  
	     //Add panels to the frame
	     getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
	     getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	     getContentPane().add(personalDetailsPanel, BorderLayout.WEST);
	     getContentPane().add(addressPanel1);
	     getContentPane().add(addressPanel2);
	     getContentPane().add(guestPanel);
	     getContentPane().add(roomTypePanel);
	     getContentPane().add(confirmationPanel);
	 
	     setResizable(false);
	     pack();
	     setVisible(true);
	 }

	public Dimension getPreferredSize(){
 		return new Dimension(650,300);
 	}
	//Update available rooms
	public void checkAvailableRooms() {
		boolean someRoomAvailable=false;
		
		int numberOfAdults=Integer.parseInt(nAdults.getSelectedItem().toString());
		int numberOfChildren=Integer.parseInt(nChildren.getSelectedItem().toString());
		int totalGuest=numberOfAdults+numberOfChildren;
		String roomTypeSelected;
		if(roomType.getSelection()==null){
			roomTypeSelected= "";
		}else{
			roomTypeSelected= roomType.getSelection().getActionCommand();
		}
		//Call the controller for updating the combo box.
		
		checkInController.updateAvailableRooms(roomTypeSelected, totalGuest);
		//System.out.println("Guest has selected a "+roomTypeSelected+" with "+numberOfAdults+" adults and "+numberOfChildren+" children.");
	}
	//Close check in window
	public void closeFrame(){
		RegistryInterface.checkInFrame.dispose();
	}
	//Saved the check in when typing in the registry button
	public void saveRecord(){
		//Check the form
		boolean validForm=true;
		//Empty fields
		if(firstNameField.getText().equals("")||lastNameField.getText().equals("")||addressField.getText().equals("")||
				cityField.getText().equals("")||stateField.getText().equals("")||zipCodeField.getText().equals("")){
			validForm=false;
			showInvalidFormDialog("Check Personal Details");
		}//White fields
		else if(firstNameField.getText().matches("^\\s*$")||lastNameField.getText().matches("^\\s*$")||addressField.getText().matches("^\\s*$")||
			cityField.getText().matches("^\\s*$")||stateField.getText().matches("^\\s*$")||zipCodeField.getText().matches("^\\s*$")){
			validForm=false;
			showInvalidFormDialog("Check Personal Details.\n White spaces are not valid.");
		}//Non room type selected
		else if(roomType.getSelection()==null){
			validForm=false;
			showInvalidFormDialog("Please select a room type");
		}//No specific room selected
		else if(availableRooms.getSelectedItem().equals("Select a room type")){
			validForm=false;
			showInvalidFormDialog("Please select a room number");
		}//No rooms available at the moment, or too much guests in a room.
		else if(availableRooms.getSelectedItem().equals("No available with these options")){
			validForm=false;
			showInvalidFormDialog("No rooms available with these options.\n"+"Plese select alternative options");
		}
		//Once it is a valid form, we call the controller for changing the main screen
		else if (validForm){
			
			int roomNumber=Integer.parseInt(availableRooms.getSelectedItem().toString());
		
			for (int i = 0; i < HotelRegistry.getHotelRooms().length; i++) {
				if(HotelRegistry.getHotelRooms()[i].getRoomNumber()==roomNumber){
					String auxType=HotelRegistry.getHotelRooms()[i].getRoomType();
					
					mainController.checkInRoom(auxType);
					mainController.saveNewGuest(firstNameField, lastNameField, i);
				}	
			}
		}
	}
	//show  a dialog form with the fields which should be reviewed by the user for completing a correct form
	public void showInvalidFormDialog(String message){
		UIManager.put("OptionPane.okButtonText", "OK");
		JOptionPane . showMessageDialog(getParent(),
				" Validation Failure! - "+message, " Validation Failure ",
				JOptionPane.WARNING_MESSAGE);
	}
	//Controller inner class
	private class CheckInController{
		public void updateAvailableRooms(String roomTypeSelected, int totalGuest){
			boolean someRoomAvailable=false;
			availableRooms.removeAllItems();
			DefaultComboBoxModel<String> availableRoomsModel = (DefaultComboBoxModel) availableRooms.getModel();
			
			for (int i = 0; i < HotelRegistry.hotelRooms.length; i++) {
				Room auxRoom=HotelRegistry.hotelRooms[i];
				if(auxRoom.getAvailable()==true
				&&auxRoom.getRoomType().equals(roomTypeSelected)
				&&auxRoom.getMaxOccupancy()>=totalGuest){
					someRoomAvailable=true;
					availableRoomsModel.addElement(""+auxRoom.getRoomNumber());
				}
			}
			if(someRoomAvailable){
				availableRooms.setModel(availableRoomsModel);
			}else {
				availableRooms.addItem("No available with these options");
			}
		}
	}
}

