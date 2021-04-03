public class Bot {
    //Question... Do we need a separate class for the dealer or do we take the dealer as another bot?


    //A new hand for the bot
    private Hand hand = new Hand();

    //Add 2 cards to hand
	public void addCard(Card card) {
		hand.addCard(card);
        hand.addCard(card);
	}

    //Return true if the bot has a blackjack
	public boolean hasBlackjack(){
		if (hand.total() == 21){
			return true;
		} else {
			return false;
		}
	}

	//Automate the bots moves. Passing deck as parameter so that it can take a card if needed
	public void botPlay(Deck deck){
		if(hand.total() <= 16){
			//take a card from deck. TODO: implement a function in Deck.java to return next card
		}

	}
	

}
