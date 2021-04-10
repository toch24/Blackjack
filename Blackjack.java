// Andrew Morris, Oksana Rubis, Tommy Chong
// Blackjack game
// Project X

import java.util.ArrayList;
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
          //  try
          //  {
           //     Thread.sleep(1000);
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
          //  }
          //  catch(InterruptedException ex)
         //  {
        //        Thread.currentThread().interrupt();
         //   }
          //  players++;

            return true;
          }


    //bot2 plays
    if(players == 3){
        //add logic for bot1 to make moves and then eventually pass

        //resetting values for each player turn
        raise = false;
        match = false;
        fold = false;
            if(bot2.getBotTotal() >= 16){
                //match the bet or raise the bet
                if(bot1.getCurrentBotBet() < Pot.getHighestBet() && bot2.getBotWallet() > Pot.getHighestBet()){
                    if(bot1.getBotTotal() <= 21){
                        //raise the bet
                        raise = true;
                        int rBet;
                        //get the highest bet
                        rBet = Pot.getHighestBet();
                        //getting the difference
                        rBet = bot2.getBotWallet() - rBet;
                        //raising half, need more implementation (I'm not sure how much to raise...)
                        if(rBet/2 > 0) rBet = rBet/2;
                        else rBet = Pot.getHighestBet();

                        bot2.setCurrentBotBet(rBet);
                        int newWallet = bot2.getBotWallet() - rBet;
                        bot2.setBotWallet(newWallet);

                    }
                    else{
                        //match the bet
                        match = true;
                        int mBet;
                        mBet = Pot.getHighestBet();
                        bot2.setCurrentBotBet(mBet);
                        int newWallet = bot2.getBotWallet() - mBet;
                        bot2.setBotWallet(newWallet);


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


        return true;
      }


    //dealer

   return false;
}

/* public void newRound(){
  deck = new Deck();
  deck.shuffle(deck.getDeck());
} */

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
    checkGameState();
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

public void checkGameState(){
    Hand playerHand;      // here we are accessing different hands
    Hand bot1Hand;        // here we are accessing different hands
    Hand bot2Hand;        // here we are accessing different hands
    Hand dealerHand;      // here we are accessing different hands

    playerHand = player.getHand();
    bot1Hand = bot1.getHand();
    bot2Hand = bot2.getHand();
    dealerHand = dealer.getHand();

    if(playerHand.total() > 21)
    {
        HomeJPanel.newRound();
    }
    else if(bot1Hand.total() > 21)
    {
      HomeJPanel.newRound();
    }
    else if(bot2Hand.total() > 21)
    {
      HomeJPanel.newRound();
    }
    else if(dealerHand.total() > 21)
    {
      HomeJPanel.newRound();
    }
}

} //end of BlackJack class
