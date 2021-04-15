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
    private boolean casino = false;
    private boolean noDoubleDown = false;

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

    bot2.addCard(deck.nextCard());
    bot2.addCard(deck.nextCard());

    dealer.addCard(deck.nextCard());
    dealer.addCard(deck.nextCard());

    player.addCard(deck.nextCard());
    player.addCard(deck.nextCard());

}

public boolean play(int players){

        //bot1 plays
        if(players == 1){
            //add logic for bot1 to make moves and then eventually pass

            System.out.println("This is bot1's wallet: " + bot1.getEachBotWallet());
            //initializing doubledown boolean
            boolean doubledown = false;

            if(bot1.getBotTotal() == 21){
              basicsPanel.naturalbot1BlackJack = true;
            }

            if(!noDoubleDown){
            //if we are in casino panel
            if(casino){
              //check conditions for doubling down
            if(checkDoubleDown(1)){
              //doubledown is true
              doubledown = true;
              }
            }
            } // end of nodoubledown if statement

            if(!doubledown){
            while(bot1.getBotTotal() < 17){
              bot1.botPlay(deck);
              }               
            }  
                                
                           
            if(bot1.getEachBotWallet() >= 100 && bot1.getEachBotWallet() < 500)
              {
                int temp = rand.nextInt(3)+1;
                System.out.println(temp);
                System.out.println(bot1.getEachBotWallet());
                switch(temp)
                {
                  case 1: {bot1.setCurrentBotBet(5); System.out.println("bot1 bet 5"); break;}
                  case 2: {bot1.setCurrentBotBet(10); System.out.println("bot1 bet 10"); break;}
                  case 3: {bot1.setCurrentBotBet(50); System.out.println("bot1 bet 50"); break;}
                  case 4: {bot1.setCurrentBotBet(100); System.out.println("bot1 bet 100"); break;}
                }
              }
              else if(bot1.getEachBotWallet() >= 500)
              {
                int temp = rand.nextInt(4)+1;
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
              else if(bot1.getEachBotWallet() < 100 && bot1.getEachBotWallet() >= 50 ){
                int temp = rand.nextInt(2)+1;
                switch(temp)
                {
                  case 1: {bot1.setCurrentBotBet(5); System.out.println("bot1 bet 5"); break;}
                  case 2: {bot1.setCurrentBotBet(10); System.out.println("bot1 bet 10"); break;}
                  case 3: {bot1.setCurrentBotBet(50); System.out.println("bot1 bet 50"); break;}
                }
              }
              else if(bot1.getEachBotWallet() < 50){
                int temp = rand.nextInt(1)+1;
                switch(temp)
                {
                  case 1: {bot1.setCurrentBotBet(5); System.out.println("bot1 bet 5"); break;}
                  case 2: {bot1.setCurrentBotBet(10); System.out.println("bot1 bet 10"); break;}
                }
              }

              //if double down is true, then double the bet and give bot1 another card
              if(casino && doubledown && !noDoubleDown){
                int temp = bot1.getCurrentBotBet();
                temp *= 2; 
                if(temp <= bot1.getEachBotWallet()){
                  bot1.setCurrentBotBet(temp);
                  bot1.setNextCard(deck);
                  noDoubleDown = false;
                  System.out.println("Double down bot 1 = " + temp);
                }
                else {
                  //if bot doesnt have enough money for doubling, call play again but without double down
                  noDoubleDown = true;
                  play(1); 
                  }      
              }

              setHighestBet();
              double newWallet = bot1.getEachBotWallet() - bot1.getCurrentBotBet();
              bot1.setBotWallet(newWallet);

            if(bot1.getBotTotal() > 21)
              return true;
            else
              return false;
          }


    //bot2 plays
    if(players == 3){
        //add logic for bot1 to make moves and then eventually pass

        System.out.println("This is bot2's wallet: " + bot2.getEachBotWallet());
        //initializing doubledown boolean
        boolean doubledown = false;

        if(bot2.getBotTotal() == 21){
          basicsPanel.naturalbot2BlackJack = true;
        }

        if(!noDoubleDown){
            //if we are in casino panel
            if(casino){
              //check conditions for doubling down
            if(checkDoubleDown(2)){
              //doubledown is true
              doubledown = true;
              }
            }
            } // end of nodoubledown if statement

        if(!doubledown){
        while(bot2.getBotTotal() < 17){
          bot2.botPlay(deck);
        }
        }


        if(bot2.getEachBotWallet() >= 100 && bot2.getEachBotWallet() < 500)
              {
                int temp = rand.nextInt(3)+1;
            //    System.out.println(temp);
            //    System.out.println(bot2.getEachBotWallet());
                switch(temp)
                {
                  case 1: {bot2.setCurrentBotBet(5); System.out.println("bot2 bet 5"); break;}
                  case 2: {bot2.setCurrentBotBet(10); System.out.println("bot2 bet 10"); break;}
                  case 3: {bot2.setCurrentBotBet(50); System.out.println("bot2 bet 50"); break;}
                  case 4: {bot2.setCurrentBotBet(100); System.out.println("bot2 bet 100"); break;}
                }
              }
              else if(bot2.getEachBotWallet() >= 500)
              {
                int temp = rand.nextInt(4)+1;
              //  System.out.println(temp);

                switch(temp)
                {
                  case 1: {bot2.setCurrentBotBet(5); System.out.println("bot2 bet 5"); break;}
                  case 2: {bot2.setCurrentBotBet(10); System.out.println("bot2 bet 10"); break;}
                  case 3: {bot2.setCurrentBotBet(50); System.out.println("bot2 bet 50"); break;}
                  case 4: {bot2.setCurrentBotBet(100); System.out.println("bot2 bet 100"); break;}
                  case 5: {bot2.setCurrentBotBet(500); System.out.println("bot2 bet 500"); break;}
                }
              }
              else if(bot2.getEachBotWallet() < 100 && bot2.getEachBotWallet() >= 50 ){
                int temp = rand.nextInt(2)+1;
                switch(temp)
                {
                  case 1: {bot2.setCurrentBotBet(5); System.out.println("bot2 bet 5"); break;}
                  case 2: {bot2.setCurrentBotBet(10); System.out.println("bot2 bet 10"); break;}
                  case 3: {bot2.setCurrentBotBet(50); System.out.println("bot2 bet 50"); break;}
                }
              }
              else if(bot2.getEachBotWallet() < 50){
                int temp = rand.nextInt(1)+1;
                switch(temp)
                {
                  case 1: {bot2.setCurrentBotBet(5); System.out.println("bot2 bet 5"); break;}
                  case 2: {bot2.setCurrentBotBet(10); System.out.println("bot2 bet 10"); break;}
                }
              }

              //if double down is true, then double the bet and give bot1 another card
             if(casino && doubledown && !noDoubleDown){
                int temp = bot2.getCurrentBotBet();
                temp *= 2; 
                if(temp <= bot2.getEachBotWallet()){
                    bot2.setCurrentBotBet(temp);
                    bot2.setNextCard(deck);
                    noDoubleDown = false;
                    System.out.println("Double down bot 2 = " + temp);
                  }
                else {
                    //if bot doesnt have enough money for doubling, call play again but without double down
                    noDoubleDown = true;
                    play(2); 
                   }      
                  }

              setHighestBet();
          //    System.out.println("Bots wallet before is: "+bot2.getEachBotWallet() );
              double newWallet = bot2.getEachBotWallet() - bot2.getCurrentBotBet();
              bot2.setBotWallet(newWallet);
          //    System.out.println("Bots 2 wallet after  is: "+bot2.getEachBotWallet() );
          //    System.out.println("Bots 1 wallet after  is: "+bot1.getEachBotWallet() );

            if(bot2.getBotTotal() > 21)
              return true;
            else
              return false;
      }


    //dealer
    if(players == 4){
        //add logic for bot1 to make moves and then eventually pass

        while(dealer.getBotTotal() < 17){
          dealer.botPlay(deck);
        }

            if(dealer.getBotTotal() > 21)
              return true;
            else
              return false;
      }
  return false;
}


public void setUserBet(int bet){
    player.currentBet(bet);
}

public double getUserBet(){
    return player.getCurrentBet();
}

public boolean setHighestBet(){
    double bet1 = (double) bot1.getCurrentBotBet();
    double bet2 = (double) bot2.getCurrentBotBet();
    double bet3 = (double) dealer.getCurrentBotBet();
    double bet4 = (double) player.getCurrentBet();

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


    double playerWallet = Player.getWallet();
    double bot1Wallet = bot1.getEachBotWallet();
    double bot2Wallet = bot2.getEachBotWallet();

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

public void setCasino(){
  casino = true;
}

public double returnWallet(int playerNumber){
  if(playerNumber == 2){
    System.out.println("The Player Wallet is currently: "+ Player.getWallet() );
    return Player.getWallet();
  }
  else if(playerNumber == 1){
      System.out.println("Bot 1 wallet in returnWallet is: "+ bot1.getEachBotWallet() );
    return bot1.getEachBotWallet();
  }
  else if(playerNumber == 3){
    System.out.println("Bot 2 wallet in returnWallet is: "+ bot2.getEachBotWallet() );
    return bot2.getEachBotWallet();
  }
  else
    return 0;

}

public void setBotWallet(int n){
    if(n == 1){
      double newWallet = bot1.getEachBotWallet() + bot1.getCurrentBotBet();
      System.out.println("Bot 1 bet they will gain is: " + bot1.getCurrentBotBet());
      bot1.setBotWallet(newWallet);
    }
    else if(n==2){
      double newWallet = bot2.getEachBotWallet() + bot2.getCurrentBotBet();
      System.out.println("Bot 2 bet they will gain is: " + bot2.getCurrentBotBet());
      bot2.setBotWallet(newWallet);
    }
}

public void winningHand(int n){
  if(n == 1){
    double newWallet = bot1.getEachBotWallet() + (2*bot1.getCurrentBotBet());
    System.out.println("Bot 1 bet they will gain is: " + bot1.getCurrentBotBet());
    bot1.setBotWallet(newWallet);
  }
  else if(n==2){
    double newWallet = bot2.getEachBotWallet() + (2*bot2.getCurrentBotBet());
    System.out.println("Bot 2 bet they will gain is: " + bot2.getCurrentBotBet());
    bot2.setBotWallet(newWallet);
  }
}

public boolean blackjackHand(int n){
  if(n == 1){
    if(bot1.hasBlackjack()){
      double bet = 1.5 *bot1.getCurrentBotBet();
      double newWallet = bot1.getEachBotWallet() + bot1.getCurrentBotBet() + bet;
      System.out.println("Bot 1 bet they will gain is: " + bot1.getCurrentBotBet());
      bot1.setBotWallet(newWallet);
      return true;
    }
    else return false;
  }
  else if(n==2){
    if(bot2.hasBlackjack()){
      double bet = 1.5 *bot2.getCurrentBotBet();
      double newWallet = bot2.getEachBotWallet() + bot2.getCurrentBotBet() + bet;
      System.out.println("Bot 2 bet they will gain is: " + bot2.getCurrentBotBet());
      bot2.setBotWallet(newWallet);
      return true;
    }
    else return false;
  }
  else if(n ==3){
    if(player.hasBlackjack()){
      double bet = 1.5 * player.getCurrentBet();
      System.out.println("Player got black jack, current bet is:  " + player.getCurrentBet());
      System.out.println("Player got black jack, dealer pays:  " + bet);
      double newWallet = player.getWallet() + player.getCurrentBet() + bet;
      System.out.println("Player got blackjack. They will get: " + newWallet);
      player.setWalletBet(newWallet);
      return true;
    }
    else return false;
  }
  return false;
}

public boolean greaterHand(int n){
  if(n==1){
    if(bot1.getBotTotal() > dealer.getBotTotal())
      return true;
    else
      return false;
  }
  else if(n==2){
    if(bot2.getBotTotal() > dealer.getBotTotal())
      return true;
    else
      return false;
  }
  else if(n==3){
    if(player.getHandTotal() > dealer.getBotTotal())
      return true;
    else
      return false;
  }
  else
    return false;
}

public boolean handMatch(int n){
  if(n==1){
    if(bot1.getBotTotal() == dealer.getBotTotal())
      return true;
    else
      return false;
  }
  else if(n==2){
    if(bot2.getBotTotal() == dealer.getBotTotal())
      return true;
    else
      return false;
  }
  else if(n==3){
    if(player.getHandTotal() == dealer.getBotTotal())
      return true;
    else
      return false;
  }
  else
    return false;
}



public void setBothBotBets (int bot){
   double bet = player.getWallet();
   if (bot == 1){
       bot1.setBotBuyIn(bet);
   }
   else if(bot == 2){
     bot2.setBotBuyIn(bet);
   }
 }

 public void walletRound2(int bot, double wallet){
   if(bot == 1){
     bot1.setBotWallet(wallet);
   }
   else if(bot == 2){
     bot2.setBotWallet(wallet);
   }
 }

 public boolean checkDoubleDown(int player){
  switch(player){
    //higher chance of getting 21 if the total value is 11
    //play it safe, if the the dealer has an ace. Don't double down.
    case 1: if(bot1.getBotTotal() < 12 && dealer.getFirstCard() != 1){
              //todo: if the bot has an ace and its other card value is less than 9
              
              return true;
            }
    
    case 2: if(bot2.getBotTotal() < 12 && dealer.getFirstCard() != 1){
              //todo: if the bot has an ace and its other card value is less than 9
              
              return true;
            }

  }

  
  return false;
 }

 public int getHandValue(int n){
   if(n == 1){
     return bot1.getBotTotal();
   }
   else if(n == 2){
     return player.getHandTotal();
   }
   else if(n == 3){
     return bot2.getBotTotal();
   }
   else if(n == 4){
     return dealer.getBotTotal();
   }
   else return 0;
 }


} //end of BlackJack class
