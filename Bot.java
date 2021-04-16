import java.util.Random;

public class Bot {
    //A new hand for the bot
  private Hand hand = new Hand();
	private Random rand;
	private double cpuWallet;
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

	public double getEachBotWallet(){
		return cpuWallet;
	}

	public void setIndividualBotWallet(){
		cpuWallet = Player.getWallet();
	}

	public double getBotWallet(){
		return cpuWallet;
	}

	public void setBotBuyIn(double buyIn){							// Set the bot buyin to equal the players and add the buyin value to the total pot
		cpuWallet = Player.getWallet();
		Pot.addToPot(cpuWallet);
	}

	//Automate the bots moves. Passing deck as parameter so that it can take a card if needed
	public void botPlay(Deck deck){
		if(hand.total() <= 16){
			//take a card from deck
			hand.addCard(deck.nextCard());
		}
	}

	public void setNextCard(Deck deck){
		hand.addCard(deck.nextCard());
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

	public int getBotTotal(){

		return hand.total();
	}

	public void setBotWallet(double newWallet){
		cpuWallet = newWallet;
	}

	//returns the value of first card of the dealer, for double down checking purposes
	public int getFirstCard(){
		int firstcard = hand.firstCard();
		return firstcard;
	}


}
