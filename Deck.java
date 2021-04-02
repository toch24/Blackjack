public class Deck {
    private Card[] gameDeck = new Card[52];
   
	public Deck(){

		int count = 0;
		try{
            //setting the deck of cards
		for (int i = 1; i <= 13; i++) {
			gameDeck[count++] = new Card("Hearts", i);
		}
		for (int i = 1; i <= 13; i++) {
			gameDeck[count++] = new Card("Spades", i);
		}
		for (int i = 1; i <= 13; i++) {
			gameDeck[count++] = new Card("Clubs", i);
		}
		for (int i = 1; i <= 13; i++) {
			gameDeck[count++] = new Card("Diamonds", i);
		}	
		}
		
		catch(Exception e) {
			
		}
	
	}
}
