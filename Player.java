public class Player {
    //A new hand for the player
    private Hand hand = new Hand();

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

}
