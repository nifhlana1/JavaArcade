package javaGame;

public class VIP extends Player {
	
	
	
	public VIP() {
		super.playerName="VIP Player name unknown";
		this.totalGamesWon+=1;
		System.out.println(super.playerName);
	}
	
	
	public VIP(String playerName, int totalGamesWon){
		super(playerName+"**VIP**",totalGamesWon, false);
		//Increment total games won +1 for a VIP player
		this.totalGamesWon+=1;
		//this.totalGamesWon=1;
	}

	
	

}
