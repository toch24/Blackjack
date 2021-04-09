import java.util.Random;

public class Bot {
    //A new hand for the bot
    private Hand hand = new Hand();
	private Random rand;
	private int botBet;
	private static int botWallet;

    //Add a card to hand
	public void addCard(Card card) {
		hand.addCard(card);
	}

	public Hand getHand(){

		return hand;
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
			//take a card from deck
			hand.addCard(deck.nextCard());
		}

	}

	public static void setBotBuyIn(){							// Set the bot buyin to equal the players and add the buyin value to the total pot
		botWallet = Player.getWallet();
		Pot.addToPot(botWallet);
	}

	public void getRandomBotBet(){						// W.I.P
		rand = new Random();
	}

	public void setCurrentBotBet(int bet){
		botBet =  bet;
	}

	public int getCurrentBotBet(){
		return botBet;
	}

}
