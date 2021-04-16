public class Player {
    //A new hand for the player
    private Hand hand = new Hand();
	private static double bet;
	private static double wallet;
	private static double playerInsurance;
	private static double currentBet;							// hold the "buy in value"

    //Return true if the player has a blackjack
	public boolean hasBlackjack(){
		if (hand.total() == 21){
			return true;
		} else {
			return false;
		}
	}

    //Add a card to hand
	public void addCard(Card card) {
		hand.addCard(card);
	}

	public static void setPlayerInsurance(int passedInsurance){
		playerInsurance = passedInsurance;
	}

	public static double getPlayerInsurance(){
		return playerInsurance;
	}

	public static void setWallet(int buyIn){
		wallet += buyIn;
	}

public static void setWalletBet(double bet){
		wallet = bet;
	}

	public static void resetWallet(){
		wallet = 0;
	}

  public static void currentBet(int b){
  bet = b;
}

  public static void revertBet(){
    wallet = wallet + bet;
  //  System.out.println("The bet was: "+ bet);
  }

  public static void winningPHand(){
    wallet = wallet + (2*bet);
  //  System.out.println("Player's hand is higher than dealer's "+ 2*bet);
  }
  
	public static double getWallet(){
		return wallet;
	}

	public double getCurrentBet(){
		return bet;
	}

	public Hand getHand(){
		return hand;
	}

  public int getHandTotal(){

		return hand.total();
	}
}
