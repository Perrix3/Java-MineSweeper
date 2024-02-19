package Minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gaming {	
	
	public static void gameMenu(char map[][]) {
		Scanner sc=new Scanner(System.in);
		boolean game=true;
		while(game==true) {
			try {
				char[][] usermap = new char[map.length][map[0].length];
				//ask what they want to do and if it's not between 1 and 3 ask again
				int action;
				System.out.println("¿What do you want to do?");
				System.out.println("1. Reveal a tile.");
				System.out.println("2. Flag a mine.");
				System.out.println("3. Leave the game.");
				action=sc.nextInt();
				while(action>3 || action<1){
					System.out.print("The number must be between 1 and 3, please try again. ");
					action=sc.nextInt();
					System.out.println();
				}
				//switch for possible actions user might want to do
				switch(action) {
				case 1:
					usermap=revealTile(usermap, map);
				break;
				case 2:
					usermap=flagMine(usermap, map);
				break;
				case 3:
					System.out.println("Leaving the game...");
					game=false;
				break;
				}
			} catch(InputMismatchException e) {
				System.err.println("Invalid input. Please enter valid numeric values.");
				System.out.println();
				sc.nextLine();
			}

		}
	}

	public static char[][] revealTile(char usermap[][], char map[][]) {
		Scanner sc=new Scanner(System.in);
		
		try {
			
			return usermap;
		} catch(InputMismatchException e) {
			return null;
		}
	}
	
	public static char[][] flagMine(char usermap[][], char map[][]) {
		
		
		return usermap;
	}
	
	
}
