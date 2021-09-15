package javaGame;

public abstract class Game {
	protected String gameName; // Name of the game (default "Untitled game")
	private int numberOfPlayers;
	

	protected Game() {
		this.gameName = "Untitled Game";
		this.numberOfPlayers = 1;
		
		//If placeholder 1 not a game, add to this index
		//If placeholder 2 not a game, add to index 2
		//Else, print ("Sorry, this arcade has no room for more games. 
		//Please remove one if you wish to add a new one
		
		for(int i = 0; i < Arcade.gameArray.length; i++ ) {
		
		//Check if there are any free spots for games in the arcade
		//If i is not an instance of game (which it won't be unless a game is added)
		if (Arcade.gameArray[i] instanceof Game == false) {
			//Add the current game to the arcade options
			Arcade.gameArray[i] = this;
			System.out.println("Game created and added to arcade");
			break;
		}
		System.out.println("Game created, but there is no room in the arcade for it. Please remove one if you wish to add a new one.");
		}
	}
	
		// Constructor with specified name, number of players
		protected Game(String gameName, int numberofPlayers) {
			this.gameName = gameName;
			this.numberOfPlayers = numberofPlayers;
		}
		
		

		// Set a name
		public void setgameName(String gameName) {
			this.gameName = gameName;
		}
	
		// Return number of players
		public int getnumberOfPlayers() {
			return numberOfPlayers;
		}

		// Set number of players
		public void setnumberOfPlayers(int numberOfPlayers) {
			this.numberOfPlayers = numberOfPlayers;
		}	
		

		//Display winner
		public abstract String displayWinner(Player player1, Player player2);
		
		//Get user selection
		public String getUserSelection(Player player1, Player player2) {
			// TODO Auto-generated method stub
			return null;
		}

		//Run game
		protected abstract void runGame(Player newPlayer1);

		//Getter for game name
		protected abstract String getGameName();		
		
}
