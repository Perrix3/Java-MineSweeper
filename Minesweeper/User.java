package Minesweeper;

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
}
