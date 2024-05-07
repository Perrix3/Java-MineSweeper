package src.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import src.Functions.*;

public class Main {
	
	//ADD CUSTOM MODE? WHERE YOU CHOOSE HOW MANY MINES AND HOW BIG THE MAP IS
	//ADD TRY CATCH TO EVERYTHING THAT CAN FAIL
	//ADD Timer for scores (except for custom mode) and save it in .txt, and how many times played in each mode and stats of victories and defeats?
	//Maybe add another main class that is the main of the main, so it has a switch for if you want to play or check scores, or stop playing?
	//Create "user" class to save stats, so it can be saved as "user" object
	//Change map creation to an object, so you call the constructor with the data you want to introduce

	//For graphics, maybe create a frame, and add 1 panel for each window needed, when needed change visibility of window? And add whatever is needed for that window on that pannel.
	
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		boolean loop =true;
		
		while(loop==true) {
			try {
				int act;
				System.out.println("What do you want to do?");
				System.out.println("1. Login (Optional, to keep stats).");
				System.out.println("2. Play MineSweeper.");
				System.out.println("3. Check scores.");
				System.out.println("4. Exit.");
				act= sc.nextInt();
				while(act>4 || act<1){
					System.out.print("The number must be between 1 and 4, please try again. ");
					act=sc.nextInt();
					System.out.println();
				}
				
				MapCreation fullMap = null; //Create variable outside
				
				switch(act) {
				case 1:
					
				break;
				case 2:
					//Boolean to loop again if failed
					boolean loopy=true;
					
					while(loopy==true) {
						try {
							int dif;
							//ask difficulty and if it's not between 1 and 4 ask again
							System.out.print("What difficulty do you want to play? (Type 1 for easy, 2 for medium, 3 for hard, 4 for custom). ");
							dif=sc.nextInt();
							System.out.println();
							while(dif>4 || dif<1){
								System.out.print("The number must be between 1 and 4, please try again. ");
								dif=sc.nextInt();
								System.out.println();
							}
						//Switch to create map
							switch(dif) {
							case 1:
								fullMap=new MapCreation(8, 10, 15);
							break;
							case 2:
								fullMap=new MapCreation(14, 18, 40);
							break;
							case 3:
								fullMap=new MapCreation(20, 24, 100);
							break;
							case 4:
								System.out.print("How many rows do you want the map to have? ");
								int rows=sc.nextInt();
								System.out.println();
								System.out.print("How columns do you want the map to have? ");
								int cols=sc.nextInt();
								System.out.println();
								System.out.print("how many mines should there be? ");
								int mines=sc.nextInt();
								fullMap=new MapCreation(rows, cols, mines);
							break;
							}
							//If map is valid it prints it
							if(fullMap.getMap() != null) {
								//fullMap.PrintMap();					//UNCOMMENT TO PRINT SOLVED MAP AT THE START
								loopy=false;
								Gaming.gameMenu(fullMap); //Maybe need to push map to use it inside
							} else {
								System.out.println("Map creation failed.");
								System.out.println();
							}
						}catch(InputMismatchException e) {
							System.err.println("Invalid input. Please enter valid numeric values.");
							System.out.println();
							sc.nextLine();
						}
					}
				break;
				case 3:
					//When database is added, this will send user to a place where it can ask for data
				break;
				case 4:
					loop=false;
					System.out.println("Exiting...");
				break;
				}
			}catch(InputMismatchException e){
				System.err.println("Invalid input. Please enter valid numeric values.");
				System.out.println();
				sc.nextLine();
			}
		} //TEST
	}

}