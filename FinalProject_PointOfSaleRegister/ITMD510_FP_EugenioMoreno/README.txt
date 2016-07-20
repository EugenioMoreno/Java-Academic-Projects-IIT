(a) Your name and student ID number
	Eugenio Miguel Moreno Gonzalez A20363051
(b) Answers to the following questions:
i. How do you run your program (i.e. what is the command line)?
java -classpath PathofPointOfSaleClass.java PathofCostsFile.txt PathOfSalesFile.txt PathOfRegisterTapeFile.txt

ii. Describe your object-oriented design for the program:
A. How are you implementing the costs?
I have created a class called Item which attributes are  SKU, description and cost. The program creates objects of that class using
the information of the costsFile.txt and stores these objects in a HashMap which uses the SKU as a key.
B. How are you implementing the sales?
I also use a HashMap for these objects, so the program can connect sales and Items using the SKU. However, the program creates the HashMap 
using the information of the salesFile.txt for creating Sale objects (class Sale.java), which attributes are SKU and type of Sales.
The items which do not have any special discount reflected in the salesFile are assigned to a regular sale. Therefore, sale class
extends to the rest of the special discounts classes such a DiscountSale, BuyXGet1Free and BuyXForYSale.
This makes very easy the fact of navigating through all of items and sales because all of them are related to each SKU of the store items.
C. How are you implementing a customer’s order?
For this purpose the third HashMap of the program is implemented, so each time the application has a new customer this HashMap is cleaned.
This map stores the pair <SKU,numberOfItem> that the clerk type on the interface, so if he types a new SKU this item will be added to the map
with the quantity of 1 and if the SKU had been purchased before, the program will add 1 to the value of that key (SKU).
Then the toString method of the Sale is applied for printing the interface and the txt file registerTapeFile.txt

iii. What specific problems or challenges did you have implementing your solution? For example,
was there a particular requirement that you had difficulty implementing? Or perhaps
there was a particularly nasty bug in your implementation you had problems tracking down.
	I found difficult to find the most optimized way of implementing the BUYXGet1Free and BuyXForY sales

iv. Were there any requirements that were not implemented or not implemented properly in
your solution? If so, please elaborate.
	Every requirement was implemented

v. Were there any requirements that were vague or open to interpretation that you had to make
a decision on how to implement? How did you elect to interpret them?
	Maybe the part of the register tape file is not properly explain. I use this file for writing the customer receipt.
	However, I used this file as a test file when I was implementing the solution. I left two code line commented in the PointOfSale
	class which allow the program to test the file when this includes a list of valid SKUs.

vi. How would you rate the complexity of this MP on a scale of 1 to 10 where 1 is very easy
and 10 is very difficult. Why did you give this rating?
8 I think this is a pretty good project for a final project assignment like this.