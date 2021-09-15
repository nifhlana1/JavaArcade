package javaGame;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuess extends Game{
	
	public int totalScore = 0;
	public int totalRounds = 1;
	public int totalGames = 1;
	private int randomNumber = 0;//Make the number private so subclasses cannot create a getter to see it
	private boolean correctlyGuessed=false;
	private Player gameWinner=null;
	static Scanner myObj = new Scanner(System.in);
	
	
	//No arg constructor
	public NumberGuess() {
		super("Guess the Number!", 2);
	}
	
	//Generate a random number
	private int generateRandomNumber() {
		Random rand = new Random();//Create an instance of the random class
		int maxNumber = 6;
		this.randomNumber = rand.nextInt(maxNumber);//Generate random number between 0 and 5
		return this.randomNumber;
	}
	
	//Run the game
	public void runGame(Player player1) {
		
		
		System.out.println("***You're playing: Number Guessing Game***");
		System.out.println("Would you like to play the computer or another player?");
		System.out.println("--Press 1 for the computer or any other key for second player--");
		String computerPlayer = myObj.nextLine();
		Player player2;
		
		if (computerPlayer.equals("1")) {
			player2 = new Player("Robot", true);
		} else {
			player2=getSecondPlayer(player1); //get the second player
		}

		generateRandomNumber();//Generate the random  number
		//Run the game until the number is guessed
		
				do {
					//Call the getUserSelection function
					System.out.println("*Round "+totalRounds+"*:");
					getPlayerGuesses(player1,player2);
				} while(!this.correctlyGuessed) ;
				
				if(this.correctlyGuessed) {
					System.out.println("Game Over.");
					System.out.println(displayWinner(player1,player2));
					//Increment total games
					this.totalGames+=1;
					resetGame();
				}
				
				playAgainQuery(player1);
		
	}
	
	
	//Ask players for a guess
	private void getPlayerGuesses(Player player1, Player player2) {
		
		boolean invalidInput;
		//Player 1 Guess:
		do {
			invalidInput=false;
			try {
			System.out.println(player1.playerName+" , choose a number between 0 and 5.");
			int player1Guess = myObj.nextInt();
			evaluateUserSelection(player1Guess, player1);
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid integer.");
				myObj.nextLine();
				invalidInput = true;
			}
		} while(invalidInput);
			
		do {
				invalidInput=false;
				try {
					// Only ask player 2 if player 1 was incorrect
					if(!this.correctlyGuessed) {
						int player2Guess;
						if (player2.isComputer()) {
							System.out.println("Robot is thinking of number...");
							Random rand = new Random();//Create an instance of the random class
							int maxNumber = 6;
							player2Guess = rand.nextInt(maxNumber);//Generate random number between 0 and 5
						} else {
							//Player 2 Guess		
							System.out.println(player2.playerName+" , choose a number between 0 and 5.");
							player2Guess = myObj.nextInt();
						}
						
						evaluateUserSelection(player2Guess, player2);
						} 
					} catch (InputMismatchException e) {
						System.out.println("Please enter a valid integer.");
						myObj.nextLine();
						invalidInput = true;
						} 
				}while(invalidInput);
			
			this.totalRounds+=1;
		}
	

		
	
	
	
	
	public void evaluateUserSelection(int playerSelection, Player player) {
		
		if(playerSelection!=randomNumber) {
			
			if(player.isComputer()) {
				System.out.println("Robot guessed incorrectly.");
			} else {
			System.out.println("Sorry, you guessed incorrectly.");
			this.correctlyGuessed=false;
			}
			
		} else if(playerSelection==randomNumber) {
			System.out.println("Congratulations, you guessed correctly.");
			//player1Score+=1;
			this.gameWinner=player;//Set the winner of the game to the player that gussed correctly
			this.correctlyGuessed=true;//Set correctly guessed to true to break out of loop
			player.totalGamesWon+=1;
		}
		
		//return correctlyGuessed;
	}
	
	
	
	public Player getSecondPlayer(Player player1) {
		System.out.println("This is a 2 player game.");
		System.out.println(player1.playerName+", who will you be playing today?");
		System.out.println("Player 2, enter your name:");
		String player2name=myObj.nextLine();
		Player player2 = new Player(player2name,0, false);
		return player2;
	}
	
	
	public void playAgainQuery(Player player1) {
		myObj.nextLine();
		
		System.out.println("     --------------------    ");
		System.out.println("   Do you want to play again?     ");
		System.out.println("Enter 1 to play again, or any other key to return to the arcade.");
		
		String playAgainSelect = myObj.nextLine();
		
		if(playAgainSelect.equals("1")) {
			RockPaperScissors.totalGames+=1;
			RockPaperScissors.totalRounds=1;
			//Clear next line in the scanner to avoid it taking \n as next input for Player 2
			//myObj.nextLine();//clear the scanner so it doesn't take \n
			runGame(player1);
		}else {
			System.out.println("Returning to the Arcade..");
			Arcade.main(null);
			
		}
	}
	
	
	public void resetGame() {
		//Reset static variables for the game when it's over
		totalScore = 0;
		totalRounds = 1;
	}
	
	public String getGameName() {
		return super.gameName;
	}
	

	@Override
	public String displayWinner(Player player1, Player player2) {
		// TODO Auto-generated method stub
		String winnerText="And...the final winner is...."+this.gameWinner.playerName+"! Congratulations!";
		return winnerText;
	}


}
