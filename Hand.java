public class Hand {
    //2 cards in each hand (will need to create a hand instance for each player including the dealer)
    private Card[] hand = new Card[2];
	private int count = 0;
    
    public void addCard(Card card) {
        //add a card to hand
		hand[count++] = card;
	}
	
	public void clearHand() {
        //clears the hand
		count = 0;
	}
	
	public int handPeek() {

        //returns the value of the second card in hand
		int value = hand[1].getcardValue();
		return value;
	}

}
