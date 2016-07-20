package iit.edu.itmd510.mp3;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class RegistryInterfaceController extends JFrame {
	//Update rooms available when doing a check in
	public void checkInRoom(String roomType) {
		switch (roomType) {
		case HotelRegistry.type1:
			HotelRegistry.oneKingBedRoomAvailable--;
			HotelRegistry.totalRoomsAvailable--;
			RegistryInterface.type1vacancies.setText(""+HotelRegistry.getOneKingBedRoomAvailable());
			break;
		case HotelRegistry.type2:
			HotelRegistry.oneQueenBedRoomAvailable--;
			HotelRegistry.totalRoomsAvailable--;
			RegistryInterface.type2vacancies.setText(""+HotelRegistry.getOneQueenBedRoomAvailable());
			break;
		case HotelRegistry.type3:
			HotelRegistry.doubleBedRoomAvailable--;
			HotelRegistry.totalRoomsAvailable--;
			RegistryInterface.type3vacancies.setText(""+HotelRegistry.getDoubleBedRoomAvailable());
			break;
		case HotelRegistry.type4:
			HotelRegistry.doubleBedOneCotRoomAvailable--;
			HotelRegistry.totalRoomsAvailable--;
			RegistryInterface.type4vacancies.setText(""+HotelRegistry.getDoubleBedOneCotRoomAvailable());
			break;
		default:
			break;
		}
	}
	//Update rooms available when doing a check out
	public void checkOutRoom(String auxType) {
		switch (auxType) {
		case HotelRegistry.type1:
			HotelRegistry.oneKingBedRoomAvailable++;
			HotelRegistry.totalRoomsAvailable++;
			RegistryInterface.type1vacancies.setText(""+HotelRegistry.getOneKingBedRoomAvailable());
			break;
		case HotelRegistry.type2:
			HotelRegistry.oneQueenBedRoomAvailable++;
			HotelRegistry.totalRoomsAvailable++;
			RegistryInterface.type2vacancies.setText(""+HotelRegistry.getOneQueenBedRoomAvailable());
			break;
		case HotelRegistry.type3:
			HotelRegistry.doubleBedRoomAvailable++;
			HotelRegistry.totalRoomsAvailable++;
			RegistryInterface.type3vacancies.setText(""+HotelRegistry.getDoubleBedRoomAvailable());
			break;
		case HotelRegistry.type4:
			HotelRegistry.doubleBedOneCotRoomAvailable++;
			HotelRegistry.totalRoomsAvailable++;
			RegistryInterface.type4vacancies.setText(""+HotelRegistry.getDoubleBedOneCotRoomAvailable());
			break;
		default:
			break;
		}
	}
	//Update the main screen after a check out
	public void deleteGuest(int i){
		RegistryInterface.fieldArray[i].setText("");
		HotelRegistry.getHotelRooms()[i].setAvailable(true);
		RegistryInterface.checkOutFrame.dispose();
	}
	//Update the main screen after a check in
	public void saveNewGuest(JTextField firstNameField,JTextField lastNameField,int i){
		String guestName=firstNameField.getText()+" "+lastNameField.getText();
		RegistryInterface.fieldArray[i].setText(guestName);
		HotelRegistry.getHotelRooms()[i].setAvailable(false);
		RegistryInterface.checkInFrame.dispose();
	}

}
