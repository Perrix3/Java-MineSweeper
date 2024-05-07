package src.Functions;

import java.util.Random;

public class MapCreation {

//Variables
	private int mines;
	private char[][] map;
	Random rand = new Random();
	boolean lost;
	
//Constructor
	public MapCreation(int row, int col, int mines) {
		this.mines=mines;
		this.map = new char[row][col];
		fill();
		lost=false;
	}
	
	//Getters
	public int getMines() {
		return mines;
	}
	public char[][] getMap(){
		return map;
	}
	public boolean getLost(){
		return lost;
	}
	public int getRows(){
		return map.length;
	}
	public int getColumns(){
		return map[0].length;
	}

	//Lost game
	public void lost(){
		lost=true;
	}
	
//Fills the map
	public char[][] fill() {
		//Go through array and create mines
		for(int i=0;i<mines;i++) {
			int row, col;
					
			//Generates random number and if in it's coords there is an 'x', generates new ones
			do {
				row = rand.nextInt(map.length);
		        col = rand.nextInt(map[0].length);
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
	public void PrintMap() {
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[i].length;j++){
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
	}
			
	
}