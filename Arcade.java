package javaGame;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

import javaGame.RockPaperScissors;
import java.util.Scanner;

class Arcade {
	
	//Create a static array of Game objects (2 only)
	//So, all arcades made will have the same games in this case.
	//(Could also make this non static so the games in each arcade are unique
	public static Game[] gameArray = new Game[2];
	
	public static ArrayList<Player> leaderboard = new ArrayList<>();//Create a static leaderboard that belongs to Class Arcade
		
	//The Arcade has a main method, as it "runs the show"
	public static void main(String[] args) {

		Scanner myObj = new Scanner(System.in);
		boolean done = false;
		System.out.println("******Welcome to the arcade.******");
		
		//Create the games
		RockPaperScissors game1 = new RockPaperScissors();
		NumberGuess game2 = new NumberGuess();
		
		createPlayers();//Reads from file to create players and Leaderboard from previous games

		
		do {

			int userSelection = 0;
			
			try {
				// Keep asking for input while input incorrect
				System.out.println("Please choose an option:\n");
				System.out.println("1.New Regular Player\n");
				System.out.println("2.New VIP Player\n");
				System.out.println("3.Quit\n");
				userSelection = myObj.nextInt();// Read user input
			} catch (Exception e){
                System.out.println("\n");
            }

			myObj.nextLine(); //Clear scanner to throw away \n

			if (userSelection == 1) {
				Player newPlayer1=createRegularPlayer(myObj); // Create a new player
				done = true;//Set condition
				int gameChoice = chooseAGame(newPlayer1, myObj);//Get user game selection
				runChosenGame(gameChoice, newPlayer1,game1, game2, done,myObj);//Run chosen game
				break;
		
			} else if (userSelection == 2) {
				Player newPlayer2 = createVIPPlayer(myObj);// Create a new VIP player
				done = true;//Set condition
				int gameChoice = chooseAGame(newPlayer2, myObj);//Get user game selection
				runChosenGame(gameChoice, newPlayer2,game1, game2, done,myObj);//Run chosen game
				break;
				
			}else if (userSelection == 3) {
				myObj.close();
				System.out.println("Leaving arcade. See you next time.");
				System.out.println("Here is the final leaderboard:");
				Arcade.showLeaderboard();//Show the leaderboard and print it to a file
				break;				
			} else {
				System.out.println("Please enter a valid input.");
			}

		} while (!done);

		myObj.close();

	}

	
	// Get games in Arcade
		public static String getGames() {
			return gameArray.toString();
		}
		
		
	//Create regular player
		public static Player createRegularPlayer(Scanner myObj) {
			// Create a new regular player
			System.out.println("Please enter your name:");
			String playerName = myObj.nextLine();// Read user input
			Player RegularPlayer = new Player(playerName, 0, false);
			return RegularPlayer;
		}
		
	//Create VIP player
		public static VIP createVIPPlayer(Scanner myObj) {
			// Create a new regular player
			System.out.println("Welcome, VIP! Please enter your name:");
			String playerName = myObj.nextLine();// Read user input			
			VIP VIPplayer = new VIP(playerName, 0);
			return VIPplayer;
		}
		
	//Read in leaderboard and create players
		public static void createPlayers() {
			
			//Reads player names, points from leaderboard
			//Creates player objects and adds them to the Arcade
	        try {
	            List<String> playerList = Files.readAllLines(java.nio.file.Paths.get("./leaderboard.txt"), StandardCharsets.UTF_8);

	            for (String line: playerList) {
	                String[] playerFields = line.split(",");
	                String name = playerFields[0];
	                int gamesWon = Integer.parseInt(playerFields[1]);
	                Player player = new Player(name,gamesWon, false);//Note variable not used, but instantiating it adds it to leaderboard
	                //System.out.println(playerFields[0]+playerFields[1]);
	            }

	            
	        } catch (Exception e) {
	            System.out.println("");
	        }

			
		}
		
	

	//Ask user to choose a game
		public static int chooseAGame(Player playerName, Scanner myObj) {
			
			int gameChoice = 0;
			boolean incorrectAnswer = false;
			
			boolean validChoice=false;
			 do {
				 try {	 
					    // Ask user to choose a game
					 	if (incorrectAnswer) {
							System.out.println("You entered an incorrect option, please select again: ");

					 	} else {
							System.out.println("Hello " + playerName + ". Please choose a game:");
							incorrectAnswer = true;
					 	}
						RockPaperScissors game1 = new RockPaperScissors();//Create Rock Paper Scissors Game
						NumberGuess game2 = new NumberGuess();//Create number game
						System.out.println("1."+ game1.getGameName());//Offer rock paper scissors option
						System.out.println("2."+ game2.getGameName());//Offer number game option
						System.out.println("3.View arcade leaderboard");//Offer option to view leaderboard
						
						try {
							 gameChoice = myObj.nextInt();//Read in the game chosen
						}
						catch (InputMismatchException e) {
							//Print out error message in default of case statement(which will also catch
							//errors relating to the incorrect number being passed
			                myObj.nextLine();  // Clear invalid input from scanner buffer.
			            }
					 
					 if (gameChoice == 1 || gameChoice == 2 || gameChoice == 3 || gameChoice == -1) {
						 validChoice=true;
				 	 }
				 } catch (InputMismatchException e){
					 System.out.println("Only number inputs accepted.");//Print error if a non-int entered
					 myObj.nextLine();
				 }
			 } while (!validChoice);

			
		
			return gameChoice;//Return the game that was chosen (as an int)
		}
		
	//Run chosen game
	public static void runChosenGame(int gameChoice, Player newPlayer1, Game game1, Game game2, boolean done,Scanner myObj) {
		
		 switch(gameChoice) { 
		 
		 case 1: //Option 1 (Rock paper scissors) 
			 //RockPaperScissors game1 = new RockPaperScissors();
			 System.out.println("Playing : "+ game1.getGameName());
			 game1.runGame(newPlayer1);
			 break;
		 case 2: //Option 2 (Number game)
			 System.out.println("Playing game 2"); 
			 game2.runGame(newPlayer1);
			 break;
		 case -1: //Option -1 (Return to arcade)
			 System.out.println("Returning to main arcade..");
			 done = false;
			 break;
		 case 3: //Option 3 (Show leaderboard)
			 System.out.println("Showing Leaderboard..");
			 showLeaderboard(); //Call leaderboard function
			 System.out.println("\n\n");
			 done = false;
			 break;
			 
		 }
	}
		 
		 
		 
			
	//Show leaderboard
		public static void showLeaderboard() {
			//Print the leaderboard on screen
			System.out.print("\n======Arcade Leaderboard===== \n");
			Collections.sort(leaderboard);
			for(int i=0; i<leaderboard.size(); i++){
			    System.out.print(i+1+". "+leaderboard.get(i) + ":     "+leaderboard.get(i).totalGamesWon+" points \n");
			}
			
			//Save the leaderboard to a txt file
			try {
			      FileWriter myWriter = new FileWriter("leaderboard.txt");
			      for(int i=0; i<leaderboard.size(); i++){
			    	  myWriter.write(leaderboard.get(i) + ","+leaderboard.get(i).totalGamesWon+"\n");
					}
			      myWriter.close();
			      //System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			
		}
		
			
}
