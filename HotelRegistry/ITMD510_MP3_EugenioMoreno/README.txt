(a) Your name and student ID number
	Eugenio Miguel Moreno Gonzalez-A20363051
	
(b) Answers to the following questions:
i. How do you run your program (i.e. what is the command line)?
java -classpath PathofHotelRegistryClass PathofTheHotelRooms.txt
	
ii. Describe your object-oriented design for the program:
A. How are you implementing the hotel registry?
The application is run in the main class which is HotelRegistry.java. This class reads the room information from a txt file called
hotelRooms.txt, and creates an array of Room objects. After that, this class calls the class which creates the main screen which
is RegistryInterface. This class implements a menu functionality which is used for launching check in and check out forms.
This class uses a jpanel, jlabel a jtextfield arrays which are related to the array of rooms created in the main class after
reading the txt file. When a change should be done in the main interface, due to a new check in or check out form,
the controller (RegistryInterfaceController.java) is called and this class makes the changes on the main screen.

B. How are you implementing the different types of rooms?
I have created a class for creating room objects. Each object has 4 attributes:
-Number of the room (int)
-Type of the room (String)
-Maximum occupancy (int)
-Boolean which is true if the room is available.

C. How are you dynamically updating the forms and main frame based on the clerk’s
actions?
The main form is firstly created by the class RegistryInterface, with the initial information obtained from the txt file by the main class.
After that, this main frame will be updated each time that the clerk press on the "Register" button of the check in form, or on the
"OK" button of the class CheckOutForm. This update is done the controller class which changes the view of the application (MVC),
depending on the model of the Room which is checked in or checked out.

Talking about the check in form, it is updated when a change takes place in the number of adults, children and room type
selected by the clerk. This  update is done by a controller inner class. If some information is missing there will be a message and no check in will be done.
The information displayed is based on the requirements of the room, and its availability. If the options selected by the clerk,
do no match with the available rooms, none of them will be displayed, as well as if all of the room are occupied. In this case,
a message will be displayed in the Jcombobox for helping the user to identify the error.

The check out form will displayed rooms which are currently occupied (boolean available room is false). If a room is
selected and the clerk press OK, the main frame will be update and the text field of this room´s guest will be cleaned by the
controller. If no room is occupied, there will be an information message in the box.

iii. What specific problems or challenges did you have implementing your solution? For example,
was there a particular requirement that you had difficulty implementing? Or perhaps
there was a particularly nasty bug in your implementation you had problems tracking down.
	I implemented every GUI without any visual editor, so since this is the first time I have been dealing with Java GUI,
	I found a bit difficult to set layouts like the ones which are shown in the MP file. However, I think that looking into
	several examples of the book, I have been able to create enough good layouts which look very similar to those which were
	designed by the professor.

iv. Were there any requirements that were not implemented or not implemented properly in
your solution? If so, please elaborate.
	Every requirement was implemented
	
v. Were there any requirements that were vague or open to interpretation that you had to make
a decision on how to implement? How did you elect to interpret them?
	There was not specification about the message that should appear in dynamic FieldText such as Room to be 
	checked in or checked out when there is no available room which matches the options selected by the clerk.
	So in this case, an information message is shown: "No room available for these options" or "All rooms available",
	when trying to check out rooms and all of them are available. In this scenario, if the user types on the button "OK" 
	of the check out form, this button will just close the form, because all the rooms are empty.
	Besides, there was not specification about what should be done when all the room are occupied. In this case a message is shown
	and if the clerk try to press on check in button, a dialog warning will pop up.
	
vi. How would you rate the complexity of this MP on a scale of 1 to 10 where 1 is very easy
and 10 is very difficult. Why did you give this rating?
	8- I think the graphical interfaces were too complicated since this is first project where we are working with this tool.
	 Maybe there should have been more simply interface in this MP, and more advance in the final project. 
