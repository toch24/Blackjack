public class Player {
    //A new hand for the player
    private Hand hand = new Hand();
	private int bet;
	private static int wallet; 								// hold the "buy in value"

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

	public static void setWallet(int buyIn){
		wallet += buyIn;
	}

	public static int getWallet(){
		return wallet;
	}

	public void currentBet(int b){
		bet = b;
	}

	public int getCurrentBet(){
		return bet;
	}

	public Hand getHand(){
		return hand;
	}
}
