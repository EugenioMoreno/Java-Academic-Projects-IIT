package iit.edu.itmd510.mp3;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistryInterface extends JFrame{

	/**
	 * @param args
	 */
	public static JFrame checkInFrame;
	public static JFrame checkOutFrame;
	private JPanel contentPane;
	private JPanel roomsPanel;//Grid number of room + 1,1
	private JPanel headerPanel;//Grid 1,2
	public static JPanel[] panelArray;//For rooms
	private JPanel vacanciesPanel;//Grid 4,2
	
	public static JLabel[] labelsArray;
	public static JTextField[] fieldArray;
	//Menu elements
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnRegistry = new JMenu("Registry");
	private JMenuItem mntmCheckIn = new JMenuItem("Check In");
	private JMenuItem mntmCheckOut = new JMenuItem("Check Out");
	private JMenuItem mntmExit = new JMenuItem("Exit");
	private JMenu mnHelp = new JMenu("Help");
	private JMenuItem mntmAbout = new JMenuItem("About");
	
	private JLabel roomHeaderLabel = new JLabel("Room ");
	private JLabel guestHeaderLabel = new JLabel("Guest ");
	
	private JLabel type1Label = new JLabel("One King ");
	private JLabel type2Label= new JLabel("One Queen ");
	private JLabel type3Label = new JLabel("Two Double Beds ");
	private JLabel type4Label = new JLabel("Two Double Beds w/ Cot ");
	public static JTextField type1vacancies = new JTextField(""+HotelRegistry.oneKingBedRoomAvailable);
	public static JTextField type2vacancies = new JTextField(""+HotelRegistry.oneQueenBedRoomAvailable);
	public static JTextField type3vacancies = new JTextField(""+HotelRegistry.doubleBedRoomAvailable);
	public static JTextField type4vacancies = new JTextField(""+HotelRegistry.doubleBedOneCotRoomAvailable);
	//Create the interface
	public RegistryInterface(){
		super("Hotel Registry");
	
		ActionListener checkInListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openCheckInForm();
            }
        };
        ActionListener checkOutListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openCheckOutForm();
            }
        };
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
        		
		setUpInterface();
		setUpMenu();
		
		mntmCheckIn.addActionListener(checkInListner);
		mntmCheckOut.addActionListener(checkOutListner);
		mntmExit.addActionListener(exitListner);
		mntmAbout.addActionListener(helpListner);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1,2));

		//Room Panel
		setUpRoomPanel();
		//Vacancies Panel
		setUpVacancyPanel();
		
		pack();
		setResizable(false);
		setVisible(true);
		
	}
	
	//Create the first view of the interface
	private void setUpInterface(){
		labelsArray=new JLabel[HotelRegistry.hotelRooms.length];
		fieldArray=new JTextField[HotelRegistry.hotelRooms.length];
		panelArray=new JPanel[HotelRegistry.hotelRooms.length];
		for (int i = 0; i < HotelRegistry.hotelRooms.length; i++) {
			labelsArray[i]=new JLabel(""+HotelRegistry.hotelRooms[i].getRoomNumber()+":");
			fieldArray[i]= new JTextField("",25);
			fieldArray[i].setEditable(false);
			panelArray[i]= new JPanel();
		}
		type1vacancies.setEditable(false);
		type2vacancies.setEditable(false);
		type3vacancies.setEditable(false);
		type4vacancies.setEditable(false);
	}
	//Create the menu
	private void setUpMenu() {
		setJMenuBar(menuBar);
		menuBar.add(mnRegistry);
		mnRegistry.add(mntmCheckIn);
		mnRegistry.add(mntmCheckOut);
		mnRegistry.add(mntmExit);
		menuBar.add(mnHelp);
		mnHelp.add(mntmAbout);
	}
	//Create the first view of the room panel
	private void setUpRoomPanel() {
		roomsPanel=new JPanel();
		roomsPanel.setLayout(new GridLayout(HotelRegistry.getHotelRooms().length+1,0));
		headerPanel=new JPanel(new GridLayout(1,2));
		headerPanel.add(roomHeaderLabel);
		headerPanel.add(guestHeaderLabel);
		roomsPanel.add(headerPanel);
		for (int i = 0; i < panelArray.length; i++) {
			panelArray[i].add(labelsArray[i]);
			panelArray[i].add(fieldArray[i]);
			roomsPanel.add(panelArray[i]);
		}
		contentPane.add(roomsPanel,0);
	}
	//Create the first view of the vacancy panel
	private void setUpVacancyPanel() {
		vacanciesPanel=new JPanel(new GridLayout(4,2,0,100));
		vacanciesPanel.add(type1Label, 0);
		vacanciesPanel.add(type1vacancies, 1);
		vacanciesPanel.add(type2Label, 2);
		vacanciesPanel.add(type2vacancies, 3);
		vacanciesPanel.add(type3Label, 4);
		vacanciesPanel.add(type3vacancies, 5);
		vacanciesPanel.add(type4Label, 6);
		vacanciesPanel.add(type4vacancies, 7);
		Border etched=BorderFactory.createEtchedBorder();
	    Border titled=BorderFactory.createTitledBorder (etched , "Vacancies");
	    vacanciesPanel.setBorder(titled);
		contentPane.add(vacanciesPanel, 1);
	}
	//Fix frame dimension
	public Dimension getPreferredSize(){
 		return new Dimension(650,500);
 	}
	//Open a check in form
	private void openCheckInForm(){
		EventQueue.invokeLater(new Runnable()
        {
            public void run(){
            	checkInFrame=new CheckInForm(); 
                checkInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
	}
	//Open a check out form
	private void openCheckOutForm(){
		EventQueue.invokeLater(new Runnable()
        {
            public void run(){
            	checkOutFrame=new CheckOutForm(); 
                checkOutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
	}
	//Close the application
	private void exitApplication(){
		HotelRegistry.mainFrame.dispose();
	}
	//Show about information
	private void showAboutDialog(){
		UIManager.put("OptionPane.okButtonText", "OK");
		JOptionPane . showMessageDialog(getParent(),
				" Eugenio Miguel Moreno Gonzalez\n"+" A20363051", " About",
				JOptionPane.PLAIN_MESSAGE);
	}

}
