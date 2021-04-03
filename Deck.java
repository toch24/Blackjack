import java.util.Random;

public class Deck {
    private Card[] gameDeck = new Card[52];
    private Random rand = new Random();
	
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
