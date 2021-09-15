package javaGame;

public class Player implements Comparable<Player>{
	
	public String playerName;
	public int totalGamesWon=0;
	private boolean isComputer;
	
	//No arg constructor
	public Player() {	
		this.isComputer = false;
		//Add a default player name
		this.playerName="Player name unknown";
		//Will not have won any games when first created:
		this.totalGamesWon=0;
		//Add the player to list of players on the arcade leaderboard
		Arcade.leaderboard.add(this);
		
		//System.out.println("Default (No arg) Player created");
	}
	
	//Arg constructor
		public Player(String playerName, boolean isComputer) {
			this.isComputer = isComputer;
			//Add the player to list of players on the arcade leaderboard
			Arcade.leaderboard.add(this);	
			this.playerName = playerName;
			//Will not have won any games when first created:
			this.totalGamesWon = 0;

		}
	
	//Arg constructor
	public Player(String playerName, int totalGamesWon, boolean isComputer) {
		this.isComputer = isComputer;
		//Add the player to list of players on the arcade leaderboard
		Arcade.leaderboard.add(this);	
		this.playerName = playerName;
		//Will not have won any games when first created:
		this.totalGamesWon = totalGamesWon;
		//Add the player to list of players on the arcade leaderboard
	}
	
	//Override the ToString method
	@Override
	public String toString(){
        return this.playerName;
    }
	
	//Comparator to compare players by the total amount of games that they won
	//Will order by order added if equal
	@Override
	public int compareTo(Player b) {
		if(this.totalGamesWon > b.totalGamesWon) {
			return -1;
		} else if(this.totalGamesWon < b.totalGamesWon) {
			return 1;
		}else {
			return 0;
		}
		//return this.totalGamesWon >= b.totalGamesWon ? -1 : 0;
	}

	public boolean isComputer() {
		return isComputer;
	}

	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}
	
	
	

	
	
}
