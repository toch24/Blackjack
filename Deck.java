import java.util.Random;

public class Deck {
    private Card[] gameDeck = new Card[52];
    private Random rand = new Random();
	private int next = 0;
	
	public Deck(){

		int count = 0;
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

	//get the next card of the deck
	public Card nextCard(){
		next++;
		if(next >= 0 && next <= 52){
			return gameDeck[next];
		}
		//Do people even use the entire deck in blackjack??
		
		return null;
	}

	public void shuffle(Card[] gameDeck){                  // Shuffle the deck, this is a core function for blackjack.
        rand = new Random();	

        for (int i = 0; i < 4; ++i){	
            for (int j = 0; j < 52; ++j){
                int r = rand.nextInt((52-1) + 1) % 52;
															// Pretty basic function, just swaps the position of elements within the deck.
                Card temp = gameDeck[j];
                gameDeck[j] = gameDeck[r];
                gameDeck[r] = temp;
			}
            }
    } 
	
	public void printDeck(){        						// tester function - helps make sure the deck is working, preserve this for future work.
		for (int i = 0; i < 52; ++i){
			System.out.print(gameDeck[i].getcardSuit());
			System.out.print(" " + gameDeck[i].getcardValue());
			if(gameDeck[i].getspecCard() != null){
				System.out.print(" " + gameDeck[i].getspecCard());
			}
			System.out.println();
		}
	}

	public Card[] getDeck(){								// Actually useful function returns deck so we can shuffle it
		return gameDeck;
	}
}
