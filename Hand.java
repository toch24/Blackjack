public class Hand {
    //Maximum cards in hand is 14
    private Card[] hand = new Card[14];
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

        //Peek the card that is face down
		int value = hand[1].getcardValue();
		return value;
	}

	public Card[] getCards(){
		return hand;
	}


    //Calculate the total value of cards. This also checks to see whether to use 1 or 11 for ace
	public int total() {
        boolean ace = false;
		int total = 0;

		for (int i = 0; i < count; i++) {
			int value = hand[i].getcardValue();

            //if card value is 10, Jack, Queen or King
			if (value >= 10) {
				value = 10;
			} else if ( value == 1) {
				ace = true;
			}
            //add value to the total
			total += value;
		}

        //Add 10 to the total if there is an ace and the total with 11 is equal to or less than 21
		if (ace && (total + 10 <= 21)) {
			total += 10;
		}
    
		return total;
	}
}
