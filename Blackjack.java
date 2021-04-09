// Andrew Morris, Oksana Rubis, Tommy Chong
// Blackjack game
// Project X

import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;



import java.util.Scanner;

public class Blackjack{
    private Bot bot1 = new Bot();
    private Bot bot2 = new Bot();
    private Bot dealer = new Bot();
    private Player player = new Player();
    private Deck deck = new Deck();
    int players = 1;

    public Blackjack(){
        newGame();
    }

public void newGame(){
    deck.shuffle(deck.getDeck());

    System.out.println("The game has started");

    //giving two cards to each player
    bot1.addCard(deck.nextCard());
    bot1.addCard(deck.nextCard());
    bot1.botPlay(deck);

    bot2.addCard(deck.nextCard());
    bot2.addCard(deck.nextCard());
    bot2.botPlay(deck);

    dealer.addCard(deck.nextCard());
    dealer.botPlay(deck);
    //dealer.addCard(deck.nextCard());

    player.addCard(deck.nextCard());
    player.addCard(deck.nextCard());

    //bot1 plays
    if(players == 1){
      //add logic for bot1 to make moves and then eventually pass

      try
      {
          Thread.sleep(1000);
      }
      catch(InterruptedException ex)
      {
          Thread.currentThread().interrupt();
      }
      players++;
    }



    System.out.println("Bots1 hand is: "+getBotCards(1));

}

public void setUserBet(int bet){
    player.currentBet(bet);
}

public int getUserBet(){
    return player.getCurrentBet();
}

//returns an array of cards for player
public Card[] getPlayerCards(){
    Hand hand;
    hand = player.getHand();

    return hand.getCards();
}

public void playerHit(){
    player.addCard(deck.nextCard());
}

public Card[] getBotCards(int b){
    Hand hand;

    switch(b){
        case 1: hand = bot1.getHand();
                return hand.getCards();
        case 2: hand = bot2.getHand();
                return hand.getCards();
        case 3: hand = dealer.getHand();
                return hand.getCards();
    }
    return null;
}


} //end of BlackJack class
