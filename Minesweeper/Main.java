package Minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	//ADD CUSTOM MODE? WHERE YOU CHOOSE HOW MANY MINES AND HOW BIG THE MAP IS
	//ADD TRY CATCH TO EVERYTHING THAT CAN FAIL
	//ADD Timer for scores (except for custom mode) and save it in .txt, and how many times played in each mode and stats of victories and defeats?
	//Maybe add another main class that is the main of the main, so it has a switch for if you want to play or check scores, or stop playing?
	
	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		boolean loop =true;
		
		while(loop==true) {
			try {
				int act;
				System.out.println("What do you want to do?");
				System.out.println("1. Play MineSweeper.");
				System.out.println("2. Check scores.");
				System.out.println("3. Exit.");
				act= sc.nextInt();
				while(act>3 || act<1){
					System.out.print("The number must be between 1 and 3, please try again. ");
					act=sc.nextInt();
					System.out.println();
				}
				
				switch(act) {
				case 1:
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
							char[][] map = null;
							switch(dif) {
							case 1:
								map=MapCreation.easy();
							break;
							case 2:
								map=MapCreation.medium();
							break;
							case 3:
								map=MapCreation.hard();
							break;
							case 4:
								map=MapCreation.custom();
							break;
							}
							//If map is valid it prints it
							if(map != null) {
								MapCreation.PrintMap(map);
								loopy=false;
								Gaming.gameMenu(map); //Maybe need to push map[][] to use it inside
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
				case 2:
					
				break;
				case 3:
					loop=false;
					System.out.println("Exiting...");
				break;
				}
			}catch(InputMismatchException e){
				System.err.println("Invalid input. Please enter valid numeric values.");
				System.out.println();
				sc.nextLine();
			}
		}
		
		
	}

}