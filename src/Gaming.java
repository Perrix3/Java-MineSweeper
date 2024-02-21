package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gaming {	
	
	public static void gameMenu(MapCreation fullMap) {
		Scanner sc=new Scanner(System.in);
		boolean game=true;
		//Create "usermap", the map the user will see
		char[][] usermap = new char[fullMap.getRows()][fullMap.getColumns()];
		for(int i=0;i<usermap.length;i++){
			for(int j=0;j<usermap[i].length;j++){
				usermap[i][j]='-';
			}
		}
		//temp map to check if it came back null
		char[][] tempmap = new char[fullMap.getRows()][fullMap.getColumns()];
		//Counts the mines in a map, so it can be used to see if game is won
		int mines=countMines(fullMap.getMap());
		//Creates "flags" variable, to see how many flags are left to be placed
		int flags=mines;
		//TrueMines, if it reaches 0, then you win
		int TrueMines=mines;
		//Boolean to check if it won
		boolean hasWon=false;
		//Game loop, asks what the user wants to do, and does it
		while(game==true && fullMap.getLost()==false && hasWon==false) {
			try {
				//ask what they want to do and if it's not between 1 and 3 ask again
				int action;
				System.out.println("¿What do you want to do?");
				System.out.println("1. Reveal a tile.");
				System.out.println("2. Flag a mine.");
				System.out.println("3. Remove flag from tile.");
				System.out.println("4. Remind information.");
				System.out.println("5. Leave the game.");
				action=sc.nextInt();
				System.out.println();
				while(action>5 || action<1){
					System.out.print("The number must be between 1 and 5, please try again. ");
					action=sc.nextInt();
					System.out.println();
				}
				//switch for possible actions user might want to do
				switch(action) {
				case 1: //Reveal a tile
					tempmap=revealTile(usermap, fullMap.getMap(), fullMap); //add a temp array, so if it comes back as null the map is not lost?
					if(tempmap!=null){
						usermap=tempmap;
					} else{
						System.out.println("That tile was flagged or an error occurred while saving your last play.");
						System.out.println();
					}
				break;
				case 2: //Flags a mine
					System.out.print("In what row is the tile you want to flag a mine? ");
					int row=sc.nextInt()-1;
					System.out.print("In what column is the tile you want to flag a mine? ");
					int col=sc.nextInt()-1;
					System.out.println();
					tempmap=flagMine(usermap, fullMap.getMap(), fullMap, flags, row, col);
					if(tempmap!=null){
						usermap=tempmap;
						flags--;
						if (fullMap.getMap()[row][col]=='x') {
							//TrueMines
							TrueMines--;
						}
					} else{
						System.out.println("That tile couldn't be flagged or an error occurred while saving your last play.");
						System.out.println();
					}
				break;
				case 3: //unflag
					System.out.print("In what row is the tile you want to remove the flag from? ");
					int roww=sc.nextInt()-1;
					System.out.print("In what column is the tile you want to remove the flag from? ");
					int coll=sc.nextInt()-1;
					System.out.println();
					tempmap = unFlagTile(usermap, flags, roww, coll);
					if(tempmap!=null){
						usermap=tempmap;
						flags++;
						if (fullMap.getMap()[roww][coll]=='x') {
							//Add a TrueMine
							TrueMines++;
						}
					} else{
						System.out.println("Wasn't able to remove a flag from that tile or an error occurred while saving your last play.");
						System.out.println();
					}
				break;
				case 4: //Prints map data
					System.out.println("The minefield has "+fullMap.getRows()+" rows, "+fullMap.getColumns()+" columns, "+mines+" mines, and you have "+flags+" flags left.");
					System.out.println();
					PrintMap(usermap);
					System.out.println();
				break;
				case 5:
					System.out.println("Leaving the game...");
					game=false;
				break;
				}
				if(fullMap.getLost()==true){
					//Add loss to user
					System.out.println();
					System.err.println("You lost.");
					System.out.println();
				}
				if(TrueMines==0){
					System.out.println();
					System.out.println("Congratulations, you flagged all the mines!");
					System.out.println();
					hasWon=true;
					System.out.println("Here is the full map: ");
					PrintMap(fullMap.getMap());
					System.out.println();
					//ADD A VICTORY
				}
			} catch(InputMismatchException e) {
				System.err.println("Invalid input. Please enter valid numeric values.");
				System.out.println();
				sc.nextLine();
			}
		}
	}

	//Reveal the value of a tile
	public static char[][] revealTile(char usermap[][], char map[][], MapCreation fullMap) {
		Scanner sc=new Scanner(System.in);
		//maybe add a while so it doesn't exit if you are out of bounds or write a letter?
		try {
			boolean flagged=false;
			System.out.print("In what row is the tile you want to reveal? ");
			int row=sc.nextInt()-1;
			System.out.print("In what column is the tile you want to reveal? ");
			int col=sc.nextInt()-1;
			System.out.println();

		//if mines are depleted win screen +1 win
		//Possibilities when revealing a mine
			if(usermap[row][col]=='M'){//if flagged, can't be revealed
				System.out.println("You can't reveal flagged tiles, please remove the flag before.");
				flagged=true;
			}else if(map[row][col]=='x'){ //Mine, you lost
				usermap[row][col]=map[row][col];
				System.err.println("BOOM!");
				System.out.println("Seems like you hit a mine.");
				//Add a loss
				fullMap.lost();
			} else if (map[row][col]=='0') { //0, check all around and reveal them
				revealAdjacentTiles(usermap, map, row, col);

			} else{ //Default, just reveal this tile
				usermap[row][col]=map[row][col];
			}
			
			//Print map
			if(flagged==false){
				System.out.println();
				PrintMap(usermap);
				System.out.println();
				return usermap;
			} else{
				return null;
			}
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
	public static char[][] flagMine(char usermap[][], char map[][], MapCreation fullMap, int flags, int row, int col) { //Add trueMines (number of mines not currently flagged)	
	try {
		boolean flagged=true;
	// Only flag if the tile is not revealed	
		if(flags>0){	
			if (usermap[row][col] == '-') { 
				usermap[row][col] = 'M';
				System.out.println();
				PrintMap(usermap);
				System.out.println();
			} else if (usermap[row][col]=='M'){
				System.out.println();
				System.out.println("That tile is already flagged.");
				System.out.println();
				flagged=false;
			}else {
				System.out.println();
				System.out.println("You can only flag unrevealed tiles.");
				System.out.println();
				flagged=false;
			}
		} else{
			System.out.println();
			System.out.println("Sorry, you are out of flags.");
			System.out.println();
			flagged=false;
		}
		if(flagged==true){ //if was able to use flag return map
			return usermap;
		} else{ //if not able to use flag return null, so the play isn't saved and no flag is used
			return null;
		}
		
		//Catch errors
	} catch(InputMismatchException e) { //Manage when receiving a null, so map is not lost
		System.err.println("Invalid input. Please enter valid numeric values.");
		return null;
	} catch(ArrayIndexOutOfBoundsException e){
		System.err.println("The coordinates are out of bounds, try again.");
		return null;
	}
	}

	//unflag
	public static char[][] unFlagTile(char usermap[][], int flags, int row, int col){
	try {
		boolean flagged=true;
	// Only if tile is flagged	
		if (usermap[row][col] == 'M') { 
			usermap[row][col] = '-';
			System.out.println();
			PrintMap(usermap);
			System.out.println();
		} else {
			System.out.println();
			System.out.println("You can only remove the flag from flagged tiles.");
			System.out.println();
			flagged=false;
		}
		if(flagged==true){ //if was able to remove flag return map
			return usermap;
		} else{ //if not able to remove flag return null, so the play isn't saved and no flag is given
			return null;
		}
		
		//Catch errors
	} catch(InputMismatchException e) { //Manage when receiving a null, so map is not lost
		System.err.println("Invalid input. Please enter valid numeric values.");
		return null;
	} catch(ArrayIndexOutOfBoundsException e){
		System.err.println("The coordinates are out of bounds, try again.");
		return null;
	}
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
	
	public static void PrintMap(char map[][]) {
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
	}

	//Reveals adjacent tiles when it is 0 in the current tiles
	public static void revealAdjacentTiles(char usermap[][], char map[][], int row, int col) {
		if (row< 0||col<0||row>=usermap.length||col>=usermap[0].length||usermap[row][col]!='-') {
			return; //If out of bounds or revealed, don't reveal
		}
		usermap[row][col] = map[row][col]; // Reveal the current tile
		//calls this same function reveal adjacent tiles
		if(map[row][col]=='0'){
			revealAdjacentTiles(usermap, map, row-1, col);
			revealAdjacentTiles(usermap, map, row+1, col);
			revealAdjacentTiles(usermap, map, row, col-1);
			revealAdjacentTiles(usermap, map, row, col+1);
			revealAdjacentTiles(usermap, map, row-1, col-1);
			revealAdjacentTiles(usermap, map, row-1, col+1);
			revealAdjacentTiles(usermap, map, row+1, col-1);
			revealAdjacentTiles(usermap, map, row+1, col+1);
		}
	}
}
