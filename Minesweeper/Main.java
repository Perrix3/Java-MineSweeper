package Minesweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		
		//ask difficulty and if it's not between 1 and 3 ask again
		int dif=0;
		System.out.print("What difficulty do you want to play? (Type 1 for easy, 2 for medium, 3 for hard). ");
		dif=sc.nextInt();
		System.out.println();
		while(dif>3 || dif<1){
		System.out.print("The number must be between 1 and 3, please try again. ");
		dif=sc.nextInt();
		System.out.println();
		}
	//Switch to create map
		char[][] map = null;
		switch(dif) {
		case 1:
			map=Functions.easy();
		break;
		case 2:
			map=Functions.medium();
		break;
		case 3:
			map=Functions.hard();
		break;
		}
		
	//Game Loop
		boolean game=true;
		Functions.PrintMap(map);
		
		/*while(game=true) {
			System.out.print("");
		}
		*/
		
		
		
		
		
		
		
		
		
		
	}
}
