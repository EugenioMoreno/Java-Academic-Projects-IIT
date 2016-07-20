package iit.edu.itmd510.mp3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CheckOutForm extends JFrame {
	
	private JButton okButton;
    private JButton cancelButton;
    private JComboBox occupiedRooms;
    private JLabel checkOutText= new JLabel("Room to Check Out: ");
    
    private JPanel panel;
    private JPanel panel2;
    
    public static boolean allAvailable;
    
    private RegistryInterfaceController controller;

	/**
	 * @param args
	 */
    //Create the interface
    public CheckOutForm(){
    	super("Check Out Form");
    	controller=new RegistryInterfaceController();
    	
    	ActionListener checkOutListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                checkOutRoom();
            }
        };
    	
    	ActionListener closeListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame();
            }
        };
        
    	occupiedRooms=new JComboBox<>();
    	checkOccupiedRooms();
    	
    	setUpInterface();
    	
    	okButton.addActionListener(checkOutListener);
        cancelButton.addActionListener(closeListener);
    	
    	pack();
		setResizable(false);
		setVisible(true);
    }
    //Set up the first view of the interface
	private void setUpInterface() {
		panel=new JPanel();
    	panel.add(checkOutText);
    	panel.add(occupiedRooms);
    	panel2=new JPanel(new FlowLayout());
    	okButton = new JButton("OK");
    	cancelButton = new JButton("Cancel");
    	panel2.add(okButton);
        panel2.add(cancelButton);
    	getContentPane().setLayout(new BorderLayout());
    	getContentPane().add(panel,BorderLayout.NORTH);
    	getContentPane().add(panel2, BorderLayout.SOUTH);
	}
	//Close check out form
	private void closeFrame() {
		// TODO Auto-generated method stub
		RegistryInterface.checkOutFrame.dispose();

	}
	//Check rooms which are not available
	private void checkOccupiedRooms(){
		allAvailable=true;
		occupiedRooms.removeAllItems();
		DefaultComboBoxModel<String> occupaidRoomsModel = (DefaultComboBoxModel) occupiedRooms.getModel();
		for (int i = 0; i < HotelRegistry.hotelRooms.length; i++) {
			if(HotelRegistry.hotelRooms[i].getAvailable()==false){
				allAvailable=false;
				occupaidRoomsModel.addElement(""+HotelRegistry.hotelRooms[i].getRoomNumber());
			}
		}
		occupiedRooms.setModel(occupaidRoomsModel);
		if(allAvailable){
			occupiedRooms.removeAllItems();
			occupiedRooms.addItem("All rooms available");
		}
	}
	//This method do the check out of the room and call the controllor for changing the main view.
	private void checkOutRoom(){
		if(allAvailable){
			RegistryInterface.checkOutFrame.dispose();
		}else{
			int roomNumber=Integer.parseInt(occupiedRooms.getSelectedItem().toString());
			for (int i = 0; i < HotelRegistry.getHotelRooms().length; i++) {
				if(HotelRegistry.getHotelRooms()[i].getRoomNumber()==roomNumber){
					String auxType=HotelRegistry.getHotelRooms()[i].getRoomType();

					controller.checkOutRoom(auxType);
					controller.deleteGuest(i);
				}	
			}	
		}
	}
}
