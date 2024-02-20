package Minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gaming {	
	
	public static void gameMenu(char map[][]) {
		Scanner sc=new Scanner(System.in);
		boolean game=true;
		//Create "usermap", the map the user will see
		char[][] usermap = new char[map.length][map[0].length];
		for(int i=0;i<usermap.length;i++){
			for(int j=0;j<usermap[i].length;j++){
				usermap[i][j]='-';
			}
		}
		//temp map to check if it came back null
		char[][] tempmap = new char[map.length][map[0].length];
		//Counts the mines in a map, so it can be used to see if game is won
		int mines=countMines(map);
		//Creates "flags" variable, to see how many flags are left to be placed
		int flags=mines;
		//Game loop, asks what the user wants to do, and does it
		while(game==true) {
			try {
				//ask what they want to do and if it's not between 1 and 3 ask again
				int action;
				System.out.println("¿What do you want to do?");
				System.out.println("1. Reveal a tile.");
				System.out.println("2. Flag a mine.");
				System.out.println("3. Remind information.");
				System.out.println("4. Leave the game.");
				action=sc.nextInt();
				System.out.println();
				while(action>3 || action<1){
					System.out.print("The number must be between 1 and 3, please try again. ");
					action=sc.nextInt();
					System.out.println();
				}
				//switch for possible actions user might want to do
				switch(action) {
				case 1: //Reveal a tile
					tempmap=revealTile(usermap, map); //add a temp array, so if it comes back as null the map is not lost?
					if(tempmap!=null){
						usermap=tempmap;
					} else{
						System.out.println("An error occurred while saving your last play.");
						System.out.println();
					}
				break;
				case 2: //Flags a mine
					tempmap=flagMine(usermap, map);
					if(tempmap!=null){
						usermap=tempmap;
						flags--;
					} else{
						System.out.println("An error occurred while saving your last play.");
						System.out.println();
					}
				break;
				case 3: //Prints map data
					System.out.println("The minefield contains "+map.length+" rows, "+map[0].length+" columns,  "+mines+" mines, and you have "+flags+" flags left.");
					System.out.println();
				break;
				case 4:
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

	//Reveal the value of a tile
	public static char[][] revealTile(char usermap[][], char map[][]) {
		Scanner sc=new Scanner(System.in);
		//maybe add a while so it doesn't exit if you are out of bounds or write a letter?
		try {
			System.out.print("In what row is the tile you want to reveal? ");
			int row=sc.nextInt()-1;
			System.out.print("In what column is the tile you want to reveal? ");
			int col=sc.nextInt()-1;
			System.out.println();

		//if mines are depleted win screen +1 win
		//Possibilities when revealing a mine
			if(map[row][col]=='x'){ //Mine, you lost
				usermap[row][col]=map[row][col];
				System.err.println("BOOM!");
				System.out.println("Seems like you hit a mine.");
				//Add a loss

			} else if (map[row][col]==0) { //0, check all around and reveal them
				usermap[row][col]=map[row][col]; //Reveal all tiles in it's radius, careful with being out of bounds of the array
				//Maybe add a function that reveals if it's 0? so it can be looped easier
			} else{ //Default, just reveal this tile
				usermap[row][col]=map[row][col];
			}
			
			//Print map
			System.out.println();
			MapCreation.PrintMap(usermap);
			System.out.println();
			return usermap;
			//Catch errors
		} catch(InputMismatchException e) { //Manage when receiving a null, so map is not lost
			System.err.println("Invalid input. Please enter valid numeric values.");
			return null;
		} catch(ArrayIndexOutOfBoundsException e){
			System.err.println("The coordinates are out of bounds, try again.");
			return null;
		}
	}
	
	//Flag a mine, flags=mines
	public static char[][] flagMine(char usermap[][], char map[][]) { //add a mine and flag counter, if mines=0 win, if flags=0 and mines !=0 continue without saying, might need to add "truemines"
		
		
		return usermap;
	}
	
	//Counts how many mines are in a map
	public static int countMines(char map[][]){
		int mines=0;
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				if(map[i][j]=='x'){
					mines++;
				}
			}
		}
		return mines;
	}
}
