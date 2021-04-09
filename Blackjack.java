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

//    int players = 1;
    private boolean raise = false;
    private boolean match = false;
    private boolean fold = false;

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

}

public boolean play(int players){
        //bot1 plays
        if(players == 1){
            //add logic for bot1 to make moves and then eventually pass
      
            //resetting values for each player turn
            raise = false;
            match = false;
            fold = false;
            try
            {
                Thread.sleep(1000);
                if(bot1.getBotTotal() >= 16){
                    //match the bet or raise the bet
                    if(bot1.getCurrentBotBet() < Pot.getHighestBet() && bot1.getBotWallet() > Pot.getHighestBet()){
                        if(bot1.getBotTotal() <= 21){
                            //raise the bet
                            raise = true;
                            int rBet;
                            //get the highest bet
                            rBet = Pot.getHighestBet();
                            //getting the difference
                            rBet = bot1.getBotWallet() - rBet;
                            //raising half, need more implementation (I'm not sure how much to raise...)
                            if(rBet/2 > 0) rBet = rBet/2;
                            else rBet = Pot.getHighestBet();
      
                            bot1.setCurrentBotBet(rBet);
                            int newWallet = bot1.getBotWallet() - rBet;
                            bot1.setBotWallet(newWallet);
      
                        }
                        else{
                            //match the bet
                            match = true; 
                            int mBet;
                            mBet = Pot.getHighestBet();
                            bot1.setCurrentBotBet(mBet);
                            int newWallet = bot1.getBotWallet() - mBet;
                            bot1.setBotWallet(newWallet);
      
      
                        }
      
      
                    }
                    else{
                        //fold
                        fold = true;
                    }
                }
                else{
                  //fold
                  fold = true;
      
                }
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
          //  players++;

            return true;
          }


   return false;
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

public void setCurrentBotBet(int bet, int bot){
    switch(bot){
        case 1: bot1.setCurrentBotBet(bet);
                break;
        case 2: bot2.setCurrentBotBet(bet);
                break;
        case 3: dealer.setCurrentBotBet(bet);
                break;
    }

}

public boolean setHighestBet(){
    int bet1 = bot1.getCurrentBotBet();
    int bet2 = bot2.getCurrentBotBet();
    int bet3 = dealer.getCurrentBotBet();
    int bet4 = player.getCurrentBet();
    
    if(bet1 >= bet2 && bet1 >= bet3 && bet1 >= bet4){ Pot.setHighestBet(bet1); return true;} 
    else if(bet2 >= bet1 && bet2 >= bet3 && bet2 >= bet4){ Pot.setHighestBet(bet2); return true;} 
    else if(bet3 >= bet1 && bet3 >= bet2 && bet3 >= bet4){ Pot.setHighestBet(bet3); return true;} 
    else if(bet4 >= bet1 && bet4 >= bet2 && bet4 >= bet3){ Pot.setHighestBet(bet4); return true;} 
 
    return false;
}

public boolean getRaise(){

    return raise;
}
public boolean getMatch(){

    return match;
}
public boolean getFold(){
    
    return fold;
}


} //end of BlackJack class
