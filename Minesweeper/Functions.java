package Minesweeper;

import java.util.Random;

public class Functions {

//Create EASY map
	public static char[][] easy() {
		
	//Create variables
		int mines = 15;
		char[][] map = new char[8][10];
		
	//For random numbers
		Random rand = new Random();
		
	//Go through array and create mines
		for(int i=0;i<mines;i++) {
			int row, col;
			
	//Generates random number and if in it's coords there is an 'x', generates new ones
			do {
                row = rand.nextInt(8);
                col = rand.nextInt(10);
            } while (map[row][col] == 'x');
	//places the mine
            map[row][col] = 'x';
		}
		
	//Fill the empty cells
		for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != 'x') {
                	 int count = NearbyMines(map, i, j);
                     map[i][j] = (char) (count + '0');
                }
            }
        }
		return map;
	}
	
//Create MEDIUM map
		public static char[][] medium() {
			
		//Create variables
			int mines = 40;
			char[][] map = new char[14][18];
			
		//For random numbers
			Random rand = new Random();
			
		//Go through array and create mines
			for(int i=0;i<mines;i++) {
				int row, col;
				
		//Generates random number and if in it's coords there is an 'x', generates new ones
				do {
	                row = rand.nextInt(14);
	                col = rand.nextInt(18);
	            } while (map[row][col] == 'x');
		//places the mine
	            map[row][col] = 'x';
			}
			
		//Fill the empty cells
			for (int i = 0; i < map.length; i++) {
	            for (int j = 0; j < map[i].length; j++) {
	                if (map[i][j] != 'x') {
	                	 int count = NearbyMines(map, i, j);
	                     map[i][j] = (char) (count + '0');
	                }
	            }
	        }
			return map;
		}
	
//Create HARD map
		public static char[][] hard() {
			
		//Create variables
			int mines = 100;
			char[][] map = new char[20][24];
			
		//For random numbers
			Random rand = new Random();
			
		//Go through array and create mines
			for(int i=0;i<mines;i++) {
				int row, col;
				
		//Generates random number and if in it's coords there is an 'x', generates new ones
				do {
	                row = rand.nextInt(20);
	                col = rand.nextInt(24);
	            } while (map[row][col] == 'x');
		//places the mine
	            map[row][col] = 'x';
			}
			
		//Fill the empty cells
			for (int i = 0; i < map.length; i++) {
	            for (int j = 0; j < map[i].length; j++) {
	                if (map[i][j] != 'x') {
	                	 int count = NearbyMines(map, i, j);
	                     map[i][j] = (char) (count + '0');
	                }
	            }
	        }
			return map;
		}
//Adds the number to the tiles 
		public static int NearbyMines(char[][] map, int row, int col) {
		    int amount = 0;
		    for (int r=row-1;r<=row+1;r++){
		        for (int c=col-1;c<=col+1;c++) {
		            if (r>=0&&r<map.length && c>=0 && c<map[0].length && map[r][c]=='x'){
		                amount++;
		            }
		        }
		    }
		    return amount;
		}
		
//Prints the map
		public static void PrintMap(char PlayerMap[][]) {
			for(int i=0;i<PlayerMap.length;i++){
	            for(int j=0;j<PlayerMap[i].length;j++){
					System.out.print(PlayerMap[i][j]+"\t");
				}
	            System.out.println();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}