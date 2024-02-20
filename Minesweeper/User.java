package Minesweeper;

import java.util.Scanner;

public class User {

	private String name;
	private int attempts;
	private int losses;
	private int wins;
	
	
	public User(String name, int attempts, int losses, int wins) {
		this.name=name;
		this.attempts=attempts;
		this.losses=losses;
		this.wins=wins;
	}
	
    //getters
    public String getNombre(){
        return name;
    }
    public int getAttempts(){
        return attempts;
    }
    public int getLosses(){
        return losses;
    }
    public int getWins(){
        return wins;
    }

    //Add attempt
    public void attempt(){
        attempts++;
    }
    //Add losses
    public void losses(){
        losses++;
    }
    //Add wins
    public void wins(){
        wins++;
    }

    //ask login
    public static User Login(){
        try{
            Scanner sc=new Scanner(System.in);

            //Save username
            System.out.print("What is your username? ");
            String Name=sc.nextLine();

            //Add conection to database and take the data from it
            //User user=new User(Name, Attempts, Loses, Wins);
            return user;
        } catch(exception e){
            return null;
        }
    }

    //Add a function that updates the information in database, might be in another class
}
