a) Eugenio Miguel Moreno Gonzalez A20363051
b)
	i. The program has one mandatory argument, which is the path of the boardDescription.txt file
	which contains the information with spaces of the board game. And the second argument, "-dice" is optional
	and allows the player to set the value of two dies. Therefore the command line should be like the following:
	java -classpath PathofMonopolyClass PathofTheFileboardDescription.txt [-dice]
	
	ii.A)The game board is created in the main class Monopoly. This class uses the first argument for
	reading a txt file which contains information of each space of the board. Then this class executes a
	method which create the board by creating spaces (objects) depending on the txt file.
	B) Each type of space is implemented in different classes, because maybe some functionalities could be added
	depending on the type of space. So when the board is created, Monopoly creates an Space object, but
	depending on the information of the txt source file, the program will use polymorphism for creating one
	type of subclass or other. EVERY TYPE OF SPACE IS A SUBCLASS OF THE SUPERCLASS SPACE. This is because the only
	difference is how each type of space gets the credit and the message that is printed for announcing this credit.
	C)Chance and CommunityChest are subclass of Superclass Space as well as the rest of the spaces. The difference,
	is that this classes will generate a random number between 1 and 5 and the card with this index will be picked.
	Each card sets a different amount of credit and prints a different message, but this depends on a random number.
	
	iii) Maybe the most difficult part is to think about the best design of the program for making it efficient.
	Once you have your design clear, it relatively easy to implement.
	
	iv) Everything has been implemented properly.
	
	v) Talking about the implementation of the Raildroad spaces, the MP file does not say what to do in case, that
	the user land more than 1 time in some of these spaces, what can be possible if the user land in Go to Jail space.
	So I have suppose that in that case the user will get the maximum credit of 800. So the user gets 200 credits
	times the number of railroad spaces that have been visited with a maximum of 4 (4x200=800).
	
	v)7. I think this MP is more realistic than the previous one but it still takes too much time for being implemented.
	Anyway, I think this MP helps to understand how inheritance and polymorphism work.
	
	