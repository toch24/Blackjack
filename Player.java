public class Player {
    //A new hand for the player
    private Hand hand = new Hand();
	private int bet;

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

	public void currentBet(int b){
		bet = b;
	}

	public int getCurrentBet(){
		return bet;
	}
}
