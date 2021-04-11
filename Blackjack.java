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

    private boolean raise = false;
    private boolean match = false;
    private boolean fold = false;

    private Random rand = new Random();

    public Blackjack(){
      newGame();
    }

public void newGame(){

    deck.shuffle(deck.getDeck());

    System.out.println("The game has started");

    //giving two cards to each player
    bot1.addCard(deck.nextCard());
    bot1.addCard(deck.nextCard());
    //bot1.botPlay(deck);

    bot2.addCard(deck.nextCard());
    bot2.addCard(deck.nextCard());
    //bot2.botPlay(deck);

    dealer.addCard(deck.nextCard());
    //dealer.botPlay(deck);
    dealer.addCard(deck.nextCard());

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
            while(bot1.getBotTotal() < 17){
              bot1.botPlay(deck);
            }                                       // 100-500
            if(bot1.getBotWallet() >= 100 && bot1.getBotWallet() < 500)
              {
                int temp = rand.nextInt((4 + 1) - 1);
                System.out.println(temp);
                switch(temp)
                {
                  case 1: {bot1.setCurrentBotBet(5); System.out.println("bot1 bet 5"); break;}
                  case 2: {bot1.setCurrentBotBet(10); System.out.println("bot1 bet 10"); break;}
                  case 3: {bot1.setCurrentBotBet(50); System.out.println("bot1 bet 50"); break;}
                  case 4: {bot1.setCurrentBotBet(100); System.out.println("bot1 bet 100"); break;}
                }
              }
              else if(bot1.getBotWallet() >= 500)
              {
                int temp = rand.nextInt((5 + 1) - 1);
                System.out.println(temp);

                switch(temp)
                {
                  case 1: {bot1.setCurrentBotBet(5); System.out.println("bot1 bet 5"); break;}
                  case 2: {bot1.setCurrentBotBet(10); System.out.println("bot1 bet 10"); break;}
                  case 3: {bot1.setCurrentBotBet(50); System.out.println("bot1 bet 50"); break;}
                  case 4: {bot1.setCurrentBotBet(100); System.out.println("bot1 bet 100"); break;}
                  case 5: {bot1.setCurrentBotBet(500); System.out.println("bot1 bet 500"); break;}
                }
              }
                /* if(bot1.getBotTotal() >= 16){
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
                            setHighestBet();
                        }
                        else{
                            //match the bet
                            match = true;
                            int mBet;
                            mBet = Pot.getHighestBet();
                            bot1.setCurrentBotBet(mBet);
                            int newWallet = bot1.getBotWallet() - mBet;
                            bot1.setBotWallet(newWallet);
                            setHighestBet();

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

                } */
            if(bot1.getBotTotal() > 21)
              return true;
            else
              return false;
          }


    //bot2 plays
    if(players == 3){
        //add logic for bot1 to make moves and then eventually pass

        //resetting values for each player turn
        raise = false;
        match = false;
        fold = false;
        while(bot2.getBotTotal() < 17){
          bot2.botPlay(deck);
        }
            if(bot2.getBotTotal() >= 16){
                //match the bet or raise the bet
                if(bot1.getCurrentBotBet() < Pot.getHighestBet() && bot2.getBotWallet() > Pot.getHighestBet()){
                    if(bot2.getBotTotal() <= 21){
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
                        setHighestBet();

                    }
                    else{
                        //match the bet
                        match = true;
                        int mBet;
                        mBet = Pot.getHighestBet();
                        bot2.setCurrentBotBet(mBet);
                        int newWallet = bot2.getBotWallet() - mBet;
                        bot2.setBotWallet(newWallet);
                        setHighestBet();

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


            if(bot2.getBotTotal() > 21)
              return true;
            else
              return false;
      }


    //dealer
    if(players == 4){
        //add logic for bot1 to make moves and then eventually pass

        //resetting values for each player turn
        raise = false;
        match = false;
        fold = false;

        while(dealer.getBotTotal() < 17){
          dealer.botPlay(deck);
        }
            if(dealer.getBotTotal() >= 16){
                //match the bet or raise the bet
                if(bot2.getCurrentBotBet() < Pot.getHighestBet() && dealer.getBotWallet() > Pot.getHighestBet()){
                    if(dealer.getBotTotal() <= 21){
                        //raise the bet
                        raise = true;
                        int rBet;
                        //get the highest bet
                        rBet = Pot.getHighestBet();
                        //getting the difference
                        rBet = dealer.getBotWallet() - rBet;
                        //raising half, need more implementation (I'm not sure how much to raise...)
                        if(rBet/2 > 0) rBet = rBet/2;
                        else rBet = Pot.getHighestBet();

                        dealer.setCurrentBotBet(rBet);
                        int newWallet = dealer.getBotWallet() - rBet;
                        dealer.setBotWallet(newWallet);
                        setHighestBet();

                    }
                    else{
                        //match the bet
                        match = true;
                        int mBet;
                        mBet = Pot.getHighestBet();
                        dealer.setCurrentBotBet(mBet);
                        int newWallet = dealer.getBotWallet() - mBet;
                        dealer.setBotWallet(newWallet);
                        setHighestBet();
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

            if(dealer.getBotTotal() > 21)
              return true;
            else
              return false;
      }
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

//returns an array of cards for player
public Card[] getPlayerCards(){
    Hand hand;
    hand = player.getHand();

    return hand.getCards();
}

public boolean playerHit(){
    player.addCard(deck.nextCard());
    Hand playerHand = player.getHand();
    if(playerHand.total() > 21)
      return true;
    else
      return false;
    //return checkGameState();
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

public boolean checkGameState(){
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
      //  HomeJPanel.newRound();

        return true;
    }


    int playerWallet = Player.getWallet();
    int bot1Wallet = bot1.getBotWallet();
    int bot2Wallet = bot2.getBotWallet();

    if(playerWallet == 0){          // Player eliminated
      return false;
    }
    else if(bot1Wallet == 0){       // Bot 1 eliminated
      return false;
    }
    else if(bot2Wallet == 0){       // Bot 2 eliminated
      return false;
    }

    return true;

}

public int returnWallet(int playerNumber){
  if(playerNumber == 2){
    int playerWallet = Player.getWallet();
    System.out.println("The buy in from player in blackjack is: "+ Player.getWallet() );
    return Player.getWallet();
  }
  else
    return 0;

}

} //end of BlackJack class
