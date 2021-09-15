package javaGame;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors extends Game{
	
	public static int totalScore = 0;
	public static int totalRounds = 1;
	public static int totalGames = 1;
	public static int player1Score = 0;
	public static int player2Score = 0;
	static Scanner myObj = new Scanner(System.in);
	//private String choices [] = {"Rock","Paper", "Scissors"};
	
	public RockPaperScissors() {
		super("Rock, Paper, Scissors", 2);
	}
	
	
	public void runGame(Player player1) {
		
		System.out.println("***You're playing: Rock, Paper, Scissors***");
		System.out.println("Would you like to play the computer or another player?");
		System.out.println("--Press 1 for the computer or any other key for second player--");
		String computerPlayer = myObj.nextLine();
		Player player2;
		
		if (computerPlayer.equals("1")) {
			player2 = new Player("Robot", true); //Create a robot player, if user chooses to play computer
		} else {
			player2=getSecondPlayer(player1); //get the second player
		}

		//Run Rock Paper Scissors for 4 rounds
		do {
			//Call the getUserSelection function
			System.out.println("*Round "+totalRounds+" *:");
			getUserSelection(player1,player2);
		} while(totalRounds<=4);
		
		if(totalRounds>=4) {
			System.out.println("Game Over.");
			System.out.println(displayWinner(player1,player2));
			resetGame();
		}
		myObj.nextLine();
		// When game is finished, offer option to play again or return to Arcade
		System.out.println("Do you want to play again?");
		System.out.println("Enter 1 to play again or any other key to return to the arcade.");
		
		String playAgainSelect = myObj.nextLine();
		
		if(playAgainSelect.equals("1")) {
			RockPaperScissors.totalGames+=1;
			RockPaperScissors.totalRounds=1;
			//Clear next line in the scanner to avoid it taking \n as next input for Player 2
			runGame(player1);
		}else {
			System.out.println("Returning to the Arcade..");
			Arcade.main(null);
			myObj.close();
		}
	}

	
	//Get the second player, when this option is selected
	public Player getSecondPlayer(Player player1) {
		System.out.println("This is a 2 player game.");
		System.out.println(player1.playerName+", who will you be playing today?");
		System.out.println("Player 2, enter your name:");
		String player2name=myObj.nextLine();
		Player player2 = new Player(player2name,0, false);
		return player2;
	}
	
	
	@Override
	public String getUserSelection(Player player1, Player player2) {
	
		
		boolean incorrect = false;


		// Asks user to select rock, paper, scissors
		//Scanner myObj = new Scanner(System.in);
		char selection1;
		do {
			
			if (incorrect) {
				System.out.println("Invalid selection, please enter again...");
			} 
			System.out.println(player1.playerName+", make a selection: 'r' for Rock, 'p' for paper, 's' for scissors");
			selection1 = myObj.next().charAt(0);
			incorrect = true;
			
		}while(!(Character.toLowerCase(selection1) == 'r' || Character.toLowerCase(selection1) == 'p' || Character.toLowerCase(selection1) == 's'));
		
		char selection2;
		incorrect = false;

		
		do {
			if (incorrect) {
				System.out.println("Invalid selection, please enter again...");
			} 
			if (player2.isComputer()){
				System.out.println("Robot is thinking of a choice...");

				selection2 = 'r';
				Random rand = new Random();//Create an instance of the random class
				int maxNumber = 2;
				int randomNumber = rand.nextInt(maxNumber);//Generate random number between 0 and 5
				if (randomNumber == 0) {
					selection2 = 'p';
				} else if (randomNumber == 1) {
					selection2 = 's';
				}
				System.out.println("Robot chooses: " + selection2);

			
			} else {
				System.out.println(player2.playerName+", make a selection: 'r' for Rock, 'p' for paper, 's' for scissors");
				selection2 = myObj.next().charAt(0);
			}
			
			incorrect = true;
		} while(!(Character.toLowerCase(selection2) == 'r' || Character.toLowerCase(selection2) == 'p' || Character.toLowerCase(selection2) == 's'));
		
		//Calls the evaluateUserSelection function with values passed in
		System.out.println(evaluateUserSelection(selection1, selection2, player1, player2));
		//Increment 'totalRounds' by one
		totalRounds+=1;
		return "";
	}
	
	
	
	
	public String evaluateUserSelection(char selection1, char selection2, Player player1, Player player2) {
		
		switch(selection1) {
		
		//Rock
		case 'r':
		case 'R':
			if(selection2 == 'r' || selection2 == 'R') {
				player1Score+=1;
				player2Score+=1;
				return "It's a tie. 1 all this round!";
			} else if (selection2 == 'p' || selection2 == 'P'){
				player2Score+=1;
				return "Paper beats rock. Congratulations "+ player2.playerName+" - you win this round.";
			} else if(selection2 == 's' || selection2 == 'S') {
				player1Score+=1;
				return "Rock beats scissors. Congratulations "+player1.playerName+" - you win this round.";
			} 
			
		//Paper	
		case 'p':
		case 'P':
			if(selection2 == 'r'||selection2 == 'R') {
				player1Score+=1;
				return "Paper beats rock. Congratulations "+player1.playerName+" - you win this round.";
			} else if (selection2 == 'p' || selection2 == 'P'){
				player1Score+=1;
				player2Score+=1;
				return "It's a tie. 1 all this round!";

			} else if(selection2 == 's' || selection2 == 'S') {
				player2Score+=1;
				return "Scissors beats paper. Congratulations "+ player2.playerName+" - you win this round.";
			} 
			
		//Scissors	
		case 's':
		case 'S':
			if(selection2 == 'r'||selection2 == 'R') {
				player2Score+=1;
				return "Rock beats scissors. Congratulations "+player2.playerName+" - you win this round.";
			} else if (selection2 == 'p' || selection2 == 'P'){
				player1Score+=1;
				return "Scissors beat paper. Congratulations "+ player1.playerName+" - you win this round.";

			} else if(selection2 == 's' || selection2 == 'S') {
				player1Score+=1;
				player2Score+=1;
				return "It's a tie. 1 all this round!";

			} 
		
		}
		return "";
		
	}
	
	
	

	
	public String displayWinner(Player player1, Player player2) {
		String winnerText;
		System.out.println("player 1:"+player1Score);
		System.out.println(player2Score);
		// Display winner text
		if(player1Score > player2Score) {
			winnerText = "And...the final winner is...."+player1.playerName+"! Congratulations!";
			//Increment the "totalGamesWon" counter for player 1
			player1.totalGamesWon+=1;
		} else if(player2Score>player1Score) {
			winnerText="And....the final winner is..."+player2.playerName+"! Congratulations!";
			//Increment the "totalGamesWon" counter for player 2
			player2.totalGamesWon+=1;
		} else {
			winnerText="It's a tie! Congratulations - you're both a winner in my eyes!";
			//Increment the "totalGamesWon" counter for player1 and player 2
			player1.totalGamesWon+=1;
			player2.totalGamesWon+=1;
		}
		return winnerText;
	}


	
	public static void resetGame() {
		//Reset static variables for the game when it's over
		totalScore = 0;
		totalRounds = 1;
		player1Score = 0;
		player2Score = 0;
	}

	
		
	//Get games in arcade
	public Object[] getGames() {
		return Arcade.gameArray;
	}
	
	//Getter method for game name
	public void setGameName(String gameName) {
		super.gameName = gameName;
	}
	
	//Getter method for game name
	public String getGameName() {
		return super.gameName;
	}


	
	
	
}
