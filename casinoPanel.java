import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class casinoPanel extends JPanel{
    private JButton  hit, pass, startGame, newRound;
    private JTextField betField;                      // Using this to get the input from the bets i guess
    private int userBet = 0;
    private Blackjack game;
    private Card[] cards;
    private int players = 1;
    private JLabel highestbetlabel, cp1wallet, pwallet, cp2wallet, dealerwallet, bot1Value, bot2Value, dealerValue, playerValue, bot1bet, bot2bet, playerbet;
    private int cp1WalletVal, cp2WalletVal;
    private static double bot1ResetWallet, bot2ResetWallet;
    private boolean bot1Bust = false;
    private boolean bot2Bust = false;
    private boolean playerBust = false;
    private boolean bot1BJ, bot2BJ, playerBJ;
    static boolean naturalbot1BlackJack = false;
    static boolean naturalbot2BlackJack = false;
    private boolean playerDoubleDown = false;


    public casinoPanel(){
        game = new Blackjack();
        //let the game know that we are in casino rules
        game.setCasino();
        setCards(1);                                   //sets the cards images

        //setting layout to null, default layout is flow layout
        setLayout(null);

        if(HomeJPanel.firstRound){
          game.setBothBotBets(1);
          game.setBothBotBets(2);
          HomeJPanel.firstRound = false;
          System.out.println("The is the first round");
        }
        else{
          System.out.println("The is a new round");
          game.walletRound2(1, bot1ResetWallet);
          game.walletRound2(2, bot2ResetWallet);
        }


        hit = new JButton("Hit");
        hit.setBounds(250,250,100,50);
        hit.setEnabled(false);

        pass = new JButton("Hold");
        pass.setBounds(350,250,100,50);
        pass.setEnabled(false);
        add(pass);

        startGame = new JButton("Start Game");
        startGame.setBounds(323,600,100,50);
        startGame.setVisible(true);
        startGame.setEnabled(true);
        add(startGame);

        newRound = new JButton("Next Round");
        newRound.setBounds(300,300,100,50);
        newRound.setEnabled(false);
        add(newRound);

        newRound.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
        HomeJPanel.firstRound = false;
        System.out.println("Starting a new round!!!!");
        HomeJPanel.newRoundCasino();
    }
  });

        highestbetlabel = new JLabel("Current Pot Total: " + String.valueOf(Pot.getPot()));
        highestbetlabel.setBounds(300,210,200,50);
        add(highestbetlabel);

        pwallet = new JLabel("Your wallet total: " + String.valueOf(game.returnWallet(2)));
        pwallet.setBounds(250,375,200,50);
        add(pwallet);

        cp1wallet = new JLabel("Player 1 wallet total: " + String.valueOf(game.returnWallet(1)));
        cp1wallet.setBounds(50,175,200,50);
        add(cp1wallet);

        cp2wallet = new JLabel("Player 3 wallet total: " + String.valueOf(game.returnWallet(3)));
        cp2wallet.setBounds(600,175,200,50);
        add(cp2wallet);

        startGame.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
          startGame.setEnabled(false);
          startGame.setVisible(false);
          playerturns(0);
          }
        });

        pass.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
          hit.setEnabled(false);
          pass.setEnabled(false);
          players++;
          playerturns(0);
          //System.out.println("On player: "+ players +" In panel");
          }
        });

        //add hit button
        add(hit);
      hit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
          //draw a hard if clicked
            if(game.playerHit()){
              String msg = "Your hand is more 21, you busted. Wait till next round";
              JOptionPane.showMessageDialog(null, msg);

              playerValue.setVisible(false);
              playerValue = new JLabel("Your hand value: " + String.valueOf(game.getHandValue(2)));
              playerValue.setBounds(250,385,200,50);
              add(playerValue);

              players++;
              playerturns(1);
            }
            else{
              String msg = "Card drawn!";
              JOptionPane.showMessageDialog(null, msg);
            }

            //update the set of cards for player
            setCards(1);
            playerValue.setVisible(false);
            playerValue = new JLabel("Your hand value: " + String.valueOf(game.getHandValue(2)));
            playerValue.setBounds(250,385,200,50);
            add(playerValue);

        }
      });



      } //end of casinoPanel class

      public void playerturns(int n){
        if (n == 1)
          playerBust = true;
        else
          playerBust = false;

        if(players == 1){
        bot1BJ = false;
          try{
        //bet.setEnabled(false);
        hit.setEnabled(false);
        pass.setEnabled(false);

        if(game.returnWallet(1) == 0.0){
        String msg = "Player 1 has no money left. They are out of the game";
        JOptionPane.showMessageDialog(null, msg);
        players++;
      }
      else{
        boolean play = game.play(players);
        setCards(1);

        if(naturalbot1BlackJack && game.blackjackHand(1)){
           bot1BJ = true;
           String msg = "Player 1 got blackjack!";
           JOptionPane.showMessageDialog(null, msg);
           players++;
        }

        bot1Value = new JLabel("Player 1 hand value: " + String.valueOf(game.getHandValue(1)));
        bot1Value.setBounds(50,200,200,50);
        add(bot1Value);

        cp1wallet.setVisible(false);
        cp1wallet = new JLabel("Player 1 wallet total: " + String.valueOf(game.returnWallet(1)));
        cp1wallet.setBounds(50,175,200,50);
        cp1wallet.setVisible(true);
        add(cp1wallet);

        bot1bet = new JLabel("Player 1 bet: " + String.valueOf(game.getBotBet(1)));
        bot1bet.setBounds(50,150,200,50);
        add(bot1bet);

        if(play && bot1BJ != true){
          String msg = "Player 1 busted, they are out for this round";
          JOptionPane.showMessageDialog(null, msg);
          bot1Bust = true;
          System.out.println("Player 1 busted" + bot1Bust);
          //highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
          players++;
        }
        else if(play != true && bot1BJ != true) {
          String msg = "Player 1 played";
          JOptionPane.showMessageDialog(null, msg);
          highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
          bot1Bust = false;
          players++;
        }
      }
    }
          catch(Exception e){
          }
} //end of player 1 turn

        //Enable buttons for human player once player places their bet
        if(players == 2){

          if(game.returnWallet(2) == 0.0){
          String msg = "You have no money left. Game over.";
          JOptionPane.showMessageDialog(null, msg);
        }
        else{
          playerBJ = false;
          String msg = "Your turn!";
          JOptionPane.showMessageDialog(null, msg);
          hit.setEnabled(true);
          pass.setEnabled(true);

          JFrame betFrame = new JFrame("Betting...");
          JPanel betPanel = new JPanel();
          JLabel betLabel = new JLabel("Place your bet: ");           // address the player!
          JOptionPane errorCheck = new JOptionPane("Error");
          JCheckBox doubleDownBox = new JCheckBox("Double Down");

          betFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
          betFrame.add( betPanel );
          betFrame.setResizable(false);
          betFrame.setSize(300,100);

          // Create JTextField to allow for user inputs
          betField = new JTextField(4);
          betField.setBounds(50,100, 200,30);
          betField.setVisible(true);

          // add the textfield to the JPanel with the label
          betPanel.add(betLabel);
          betPanel.add(betField);
          betPanel.add(doubleDownBox);
          betFrame.setVisible(true);                                   // Make it visible.

          //checking if double down checkbox is true or false
          doubleDownBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                playerDoubleDown = true;
            }
         });

          betField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                userBet = Integer.parseInt(betField.getText());      // Take the user bet.
                double newWallet;
                playerbet = new JLabel("You bet: " + userBet);
                playerbet.setBounds(250,330,200,70);
                add(playerbet);
                if(userBet == 5 || userBet == 10 || userBet == 50 || userBet == 100 || userBet == 500)
                {
                  //TODO: if the bet is less than the highest bet, re-prompt the user for a new bet
                  //Checking that user has the money
                    if(Player.getWallet() >= userBet){

                      if(game.blackjackHand(3)){
                      String blackjackMessage = "You got blackjack!";
                      JOptionPane.showMessageDialog(null, blackjackMessage);
                      playerBJ = true;
                      players++;
                    }

                    if(playerDoubleDown){
                        int temp = userBet;
                        temp *= 2;
                        if(temp <= Player.getWallet()){
                            game.setUserBet(temp);
                            newWallet = Player.getWallet() - temp;
                            Player.setWalletBet(newWallet);
                            Pot.addToPot(temp);                               // Add to the pot.

                            //disable hit button and give a card to player
                            hit.setEnabled(false);
                            if(game.playerHit()){
                                String msg = "Your hand is more 21, you busted. Wait till next round";
                                JOptionPane.showMessageDialog(null, msg);

                                playerValue.setVisible(false);
                                playerValue = new JLabel("Your hand value: " + String.valueOf(game.getHandValue(2)));
                                playerValue.setBounds(250,385,200,50);
                                add(playerValue);

                                players++;
                                playerturns(1);
                              }
                            else{
                                String msg = "Dealer gave card to you!";
                                JOptionPane.showMessageDialog(null, msg);
                              }
                              setCards(1);
                              playerValue.setVisible(false);
                              playerValue = new JLabel("Your hand value: " + String.valueOf(game.getHandValue(2)));
                              playerValue.setBounds(250,385,200,50);
                              add(playerValue);

                              game.setHighestBet();
                              highestbetlabel.setText("Current Pot Total: " + String.valueOf(Pot.getPot()));
                              betFrame.dispose();                              // Forse the JFrame closed when we successfully make a bet.
                              String betAddress = "Bet Placed.";               // Just let the user know their bet was successfully placed, we can take this out if you guys want.
                              JOptionPane.showMessageDialog(null, betAddress);
                        }
                        else{
                            String doublebet = "Can't double bet because bet will surprass wallet";
                            JOptionPane.showMessageDialog(null, doublebet);
                        }

                    }
                    else{
                        game.setUserBet(userBet);
                        newWallet = Player.getWallet() - userBet;
                        Player.setWalletBet(newWallet);
                        Pot.addToPot(userBet);                               // Add to the pot.
                        game.setHighestBet();
                        highestbetlabel.setText("Current Pot Total: " + String.valueOf(Pot.getPot()));
                        betFrame.dispose();                              // Forse the JFrame closed when we successfully make a bet.
                        String betAddress = "Bet Placed.";               // Just let the user know their bet was successfully placed, we can take this out if you guys want.
                        JOptionPane.showMessageDialog(null, betAddress);
                    }

                    }
                    else{
                      String betAddress = "You dont have that amount to bet.";
                      JOptionPane.showMessageDialog(null, betAddress);
                    }


                  pwallet.setVisible(false);
                  pwallet = new JLabel("Your wallet total: " + String.valueOf(game.returnWallet(2)));
                  pwallet.setBounds(250,360,200,50);
                  pwallet.setVisible(true);
                  add(pwallet);

              }
                else
                {   // Use this to error check, the user cannot make bets < 0.
                    String error = "Error: Player can bet either: 5,10,50,100,500 ";
                    JOptionPane.showMessageDialog(null, error);
                }
            }
        });
          // Making insurance stuff
          if(insurance()){
            JFrame insuranceFrame = new JFrame("Insurance");
            JPanel insurancePanel = new JPanel();
            JLabel insuranceLabel = new JLabel("The dealer has an Ace; Place an Insurance Bet?");

            JButton yes = new JButton("Yes");
            JButton no = new JButton("No");

            insuranceLabel.setAlignmentX(JLabel.CENTER);
            yes.setBounds(250,250,100,50);
            no.setBounds(250,250,100,50);

            insuranceFrame.add(insurancePanel);
            insurancePanel.add(yes);
            insurancePanel.add(no);

            insuranceFrame.setVisible(true);
            insurancePanel.setVisible(true);
            yes.setVisible(true);
            no.setVisible(true);

            no.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                insuranceFrame.dispose();
              }
            });
          }

          // Making insurance stuff
          if(insurance()){
            JFrame insuranceFrame = new JFrame("Insurance");
            JPanel insurancePanel = new JPanel();
            JLabel insuranceLabel = new JLabel("The dealer has an Ace; Place an Insurance Bet?");

            JButton yes = new JButton("Yes");
            JButton no = new JButton("No");

            insuranceLabel.setAlignmentX(JLabel.CENTER);
            yes.setBounds(250,250,100,50);
            no.setBounds(250,250,100,50);

            insuranceFrame.add(insurancePanel);
            insurancePanel.add(yes);
            insurancePanel.add(no);

            insuranceFrame.setVisible(true);
            insurancePanel.setVisible(true);
            yes.setVisible(true);
            no.setVisible(true);

            no.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                insuranceFrame.dispose();
              }
            });
          }

          pwallet.setVisible(false);
          pwallet = new JLabel("Your wallet total: " + String.valueOf(game.returnWallet(2)));
          pwallet.setBounds(250,360,200,50);
          pwallet.setVisible(true);
          add(pwallet);

          playerValue = new JLabel("Your hand value: " + String.valueOf(game.getHandValue(2)));
          playerValue.setBounds(250,385,200,50);
          add(playerValue);

      } //end of else

}

        if(players == 3){
          bot2BJ = false;
          try{
            Thread.sleep(1000);
        hit.setEnabled(false);
        pass.setEnabled(false);

        if(game.returnWallet(3) == 0.0){
            String msg = "Player 2 has no money left. They are out of the game";
            JOptionPane.showMessageDialog(null, msg);
            players++;
        }
        else{
        boolean play = game.play(players);
        setCards(1);

        if(naturalbot2BlackJack && game.blackjackHand(2)){
         bot2BJ = true;
         String msg = "Player 3 got blackjack!";
         JOptionPane.showMessageDialog(null, msg);
         players++;
      }

        bot2Value = new JLabel("Player 3 hand value: " + String.valueOf(game.getHandValue(3)));
        bot2Value.setBounds(600,200,200,50);
        add(bot2Value);

        cp2wallet.setVisible(false);
        cp2wallet = new JLabel("Player 3 wallet total: " + String.valueOf(game.returnWallet(3)));
        cp2wallet.setBounds(600,175,200,50);
        cp2wallet.setVisible(true);
        add(cp2wallet);

      bot2bet = new JLabel("Player 3 bet: " + String.valueOf(game.getBotBet(2)));
      bot2bet.setBounds(600,150,200,50);
      add(bot2bet);

        if(play && bot2BJ != true){
          String msg = "Player 3 busted, they are out for this round";
          JOptionPane.showMessageDialog(null, msg);
          bot2Bust = true;
          players++;
        }
        else if(play != true && bot2BJ != true) {
          String msg = "Player 3 played";
          JOptionPane.showMessageDialog(null, msg);
          highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
          bot2Bust = false;
          players++;
        }
      }
    }
        catch(InterruptedException ex){
          Thread.currentThread().interrupt();
        }
  }

        if(players == 4){
          try{
          Thread.sleep(1000);
          hit.setEnabled(false);
          pass.setEnabled(false);

          boolean play = game.play(players);
          setCards(2);

          dealerValue = new JLabel("Dealer hand value: " + String.valueOf(game.getHandValue(4)));
          dealerValue.setBounds(260,5,200,50);
          add(dealerValue);

          if(play){
            String msg = "Dealer busted, all players still in the round win!";
            JOptionPane.showMessageDialog(null, msg);
            //highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
            players = 1;
            if(!bot1Bust && !bot1BJ){
              System.out.println("Dealer gives money to bot1");
              game.winningHand(1);
              System.out.println("Bot1 wallet is now: "+ game.returnWallet(1));
            }
            if(!bot2Bust && !bot2BJ){
              System.out.println("Dealer gives money to bot2");
              game.winningHand(2);
              System.out.println("Bot2 wallet is now: "+ game.returnWallet(3));
            }
            if(!playerBust && !playerBJ){
              System.out.println("Dealer gives money to player");
              //int payback = Player.getCurrentBet();
              Player.revertBet();
              System.out.println("Player wallet is now: "+ game.returnWallet(2));
            }
          }
          else {
            String msg = "Dealer played";
            JOptionPane.showMessageDialog(null, msg);
            highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
            players = 1;

            payouts(bot1Bust, bot2Bust, playerBust);

          /*  if(!bot1Bust && game.handMatch(1)){
              System.out.println("Dealer gives money to bot1");
              game.setBotWallet(1);
              System.out.println("Bot1 wallet is now: "+ game.returnWallet(1));
            }
            if(!bot2Bust && game.handMatch(2)){
              System.out.println("Dealer gives money to bot2");
              game.setBotWallet(2);
              System.out.println("Bot2 wallet is now: "+ game.returnWallet(3));
            }
            if(!playerBust && game.handMatch(3)){
              System.out.println("Dealer gives money to player");
              //int payback = Player.getCurrentBet();
              Player.revertBet();
              System.out.println("Player wallet is now: "+ game.returnWallet(2));
            }   */
          }
          String message = "Round Over: Press 'Next Round' to Continue.";
          JOptionPane.showMessageDialog(null,message);
          newRound.setEnabled(true);
        }
        catch(InterruptedException ex){
          Thread.currentThread().interrupt();
      }
      bot1ResetWallet = game.returnWallet(1);
      bot2ResetWallet = game.returnWallet(3);
    }
  }

  public void payouts(boolean bot1Bust, boolean bot2Bust, boolean playerBust){
  if(!bot1BJ && !bot1Bust && game.greaterHand(1)){
  //  System.out.println("Dealer gives twice bet to bot1");
    game.winningHand(1);
//    System.out.println("Bot1 wallet is now: "+ game.returnWallet(1));
  }
  if(!bot2BJ && !bot2Bust && game.greaterHand(2)){
  //  System.out.println("Dealer gives twice bet to bot2");
    game.winningHand(2);
//    System.out.println("Bot2 wallet is now: "+ game.returnWallet(3));
  }
  if(!playerBust && game.greaterHand(3)){
  //  System.out.println("Dealer twice bet to player");
    Player.winningPHand();
  //  System.out.println("Player wallet is now: "+ game.returnWallet(2));
  }
  if(!bot1Bust && game.handMatch(1)){
  //     System.out.println("Dealer gives bet back to bot1");
       game.setBotWallet(1);
  //     System.out.println("Bot1 wallet is now: "+ game.returnWallet(1));
     }
  if(!bot2Bust && game.handMatch(2)){
    //   System.out.println("Dealer gives bet back to bot2");
       game.setBotWallet(2);
    //   System.out.println("Bot2 wallet is now: "+ game.returnWallet(3));
     }
  if(!playerBust && game.handMatch(3)){
  //     System.out.println("Dealer gives bet back to player");
       Player.revertBet();
  //     System.out.println("Player wallet is now: "+ game.returnWallet(2));
  }
}


      public void setCards(int n){
        int cardvalue;
        String suit;
        int pos = 240;
        int newPos = 240;
        if(n ==1){
        int cardIterator = 1;
        cards = game.getPlayerCards();
        for(Card c : cards){
          //If the card is not null, then get all the data. (Need this error checking because Card array is initialized with size of 14)
          if(c != null){
          cardvalue = c.getcardValue();
          suit = c.getcardSuit();
          String png = "";                                     //name of the png file
          String value = String.valueOf(cardvalue);
          //System.out.println("The player's card value is: " + cardvalue);
          //System.out.println("The player's card suit is: " + suit);
          String temp = c.getspecCard();
          //System.out.println("The player's spec value is: " + temp);


          //get the card png (easier way and less code...)
          if(c.getspecCard() == null)
            {
              if(suit.equals("Hearts")){
                png = "Cards/" + value + "H" + ".png";
              }
              else if(suit.equals("Spades")){
                png = "Cards/" + value + "S" + ".png";
              }
              else if(suit.equals("Clubs")){
                png = "Cards/" + value + "C" + ".png";
              }
              else if(suit.equals("Diamonds")){
                png = "Cards/" + value + "D" + ".png";
              }
            }
            else if(c.getspecCard() != null)        // we are checking if we have a face card :)
            { // This whole switch statement accounts for if the card has a face (I.E] Jack,Queen,King)
              if(c.getspecCard() == "King"){
                switch(suit){
                  case "Hearts" : {png = "Cards/" + 13 + "H" + ".png"; break;}
                  case "Clubs"  : {png = "Cards/" + 13 + "C" + ".png"; break;}
                  case "Spades"  : {png = "Cards/" + 13 + "S" + ".png"; break;}
                  case "Diamonds"  : {png = "Cards/" + 13 + "D" + ".png"; break;}
                }
              }
              else if(c.getspecCard() == "Queen"){
                switch(suit){
                  case "Hearts" : {png = "Cards/" + 12 + "H" + ".png"; break;}
                  case "Clubs"  : {png = "Cards/" + 12 + "C" + ".png"; break;}
                  case "Spades"  : {png = "Cards/" + 12 + "S" + ".png"; break;}
                  case "Diamonds"  : {png = "Cards/" + 12 + "D" + ".png"; break;}
                }
              }
              else if(c.getspecCard() == "Jack"){
                switch(suit){
                  case "Hearts" : {png = "Cards/" + 11 + "H" + ".png"; break;}
                  case "Clubs"  : {png = "Cards/" + 11 + "C" + ".png"; break;}
                  case "Spades"  : {png = "Cards/" + 11 + "S" + ".png"; break;}
                  case "Diamonds"  : {png = "Cards/" + 11 + "D" + ".png"; break;}
                }
              }
              else if(c.getspecCard() == "Ace"){
                switch(suit){
                  case "Hearts" : {png = "Cards/" + 1 + "H" + ".png"; break;}
                  case "Clubs"  : {png = "Cards/" + 1 + "C" + ".png"; break;}
                  case "Spades"  : {png = "Cards/" + 1 + "S" + ".png"; break;}
                  case "Diamonds"  : {png = "Cards/" + 1 + "D" + ".png"; break;}
                }
              }
            }

           //adding the card images to the JPanel
          BufferedImage img = null;
          JLabel label = new JLabel();
          try {
            img = ImageIO.read(new File(png));

            //scaling the image to a smaller size and setting it as imageicon for jLabel
            Image dimg = img.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            label.setIcon(imageIcon);
            if(cardIterator <= 3){
              label.setBounds(pos, 350, 300, 300);
            }
            else{
              label.setBounds(newPos, 475, 300, 300);
              newPos += 100;
            }
            pos += 100;
            add(label);
            repaint();


          //  System.out.println("Image opened " + png);
          } catch (IOException e) {
            System.out.println("Error = " + e);

          }

        }   //end of if statement for c != null
        cardIterator++;
      }   //end of for loop for iteration of each card in hand
  }

          //set bot cards

          int posbot = 200;
          int posbot2 = 200;
          //iterating each bot
          for(int i = 1; i <= 2; i++){
            cards = game.getBotCards(i);
            for(Card c : cards){
            //If the card is not null, then get all the data. (Need this error checking because Card array is initialized with size of 14)
           if(c != null){
            cardvalue = c.getcardValue();
            suit = c.getcardSuit();
            //System.out.println("The bot2's card value is: "+cardvalue);
            //System.out.println("The bot2's card suit is: "+suit);
            String temp = c.getspecCard();
            //System.out.println("The bot2's spec value is: " + temp);

            String png = "";                                     //name of the png file
            String value = String.valueOf(cardvalue);

            if(c.getspecCard() == null)
              {
                if(suit.equals("Hearts")){
                  png = "Cards/" + value + "H" + ".png";
                }
                else if(suit.equals("Spades")){
                  png = "Cards/" + value + "S" + ".png";
                }
                else if(suit.equals("Clubs")){
                  png = "Cards/" + value + "C" + ".png";
                }
                else if(suit.equals("Diamonds")){
                  png = "Cards/" + value + "D" + ".png";
                }
              }
              else if(c.getspecCard() != null)        // we are checking if we have a face card :)
              { // This whole switch statement accounts for if the card has a face (I.E] Jack,Queen,King)
                if(c.getspecCard() == "King"){
                  switch(suit){
                    case "Hearts" : {png = "Cards/" + 13 + "H" + ".png"; break;}
                    case "Clubs"  : {png = "Cards/" + 13 + "C" + ".png"; break;}
                    case "Spades"  : {png = "Cards/" + 13 + "S" + ".png"; break;}
                    case "Diamonds"  : {png = "Cards/" + 13 + "D" + ".png"; break;}
                  }
                }
                else if(c.getspecCard() == "Queen"){
                  switch(suit){
                    case "Hearts" : {png = "Cards/" + 12 + "H" + ".png"; break;}
                    case "Clubs"  : {png = "Cards/" + 12 + "C" + ".png"; break;}
                    case "Spades"  : {png = "Cards/" + 12 + "S" + ".png"; break;}
                    case "Diamonds"  : {png = "Cards/" + 12 + "D" + ".png"; break;}
                  }
                }
                else if(c.getspecCard() == "Jack"){
                  switch(suit){
                    case "Hearts" : {png = "Cards/" + 11 + "H" + ".png"; break;}
                    case "Clubs"  : {png = "Cards/" + 11 + "C" + ".png"; break;}
                    case "Spades"  : {png = "Cards/" + 11 + "S" + ".png"; break;}
                    case "Diamonds"  : {png = "Cards/" + 11 + "D" + ".png"; break;}
                  }
                }
                else if(c.getspecCard() == "Ace"){
                  switch(suit){
                    case "Hearts" : {png = "Cards/" + 1 + "H" + ".png"; break;}
                    case "Clubs"  : {png = "Cards/" + 1 + "C" + ".png"; break;}
                    case "Spades"  : {png = "Cards/" + 1 + "S" + ".png"; break;}
                    case "Diamonds"  : {png = "Cards/" + 1 + "D" + ".png"; break;}
                  }
                }
              }


            //for bot 1 and bot 2, just show the amount of cards with image of the back of the card
            BufferedImage img = null;
            JLabel label = new JLabel();
            try {

                img = ImageIO.read(new File(png));
                //System.out.println("Show image!!");

              int height = img.getHeight();
              int width = img.getWidth();
              //rotated image (switching height and width)
              BufferedImage rotateImg = new BufferedImage(height, width, img.getType());
              for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x ++){
                  if(i ==1){
                    rotateImg.setRGB(y, (width-1)-x, img.getRGB(x, y));
                  }
                else{
                  rotateImg.setRGB((height-1)-y, x, img.getRGB(x, y));
                }
                  }
                }

              //TODO: Rotate the image clockwise for i = 1 and counterclockwise for i = 2
              //scaling the image to a smaller size and setting it as imageicon for jLabel
              Image dimg = rotateImg.getScaledInstance(120, 100, Image.SCALE_SMOOTH);
              ImageIcon imageIcon = new ImageIcon(dimg);



              label.setIcon(imageIcon);

              if(i == 1) label.setBounds(50, posbot, 200, 200);
              else {
                label.setBounds(600,posbot2,200,200);
                posbot2 += 80;
              }



              posbot += 80;
              //label.setVisible(true);
              add(label);


            } catch (IOException e) {
              System.out.println("Error = " + e);

            }
          } //end of if statement for c != null
        } //end of for loop
  }

          //Set Dealer cards
          int dpos = 250;
          cards = game.getBotCards(3);
          boolean first_card = true;
          int iterate = 1;
          for(Card c : cards){
            //If the card is not null, then get all the data. (Need this error checking because Card array is initialized with size of 14)
            if(c != null){
            cardvalue = c.getcardValue();
            suit = c.getcardSuit();
            //System.out.println("The dealer's card value is: " + cardvalue + "The suit is: " + suit);
            //String temp = c.getspecCard();
            //System.out.println("The dealer spec value is: " + temp);

            String png = "";                                     //name of the png file
            String value = String.valueOf(cardvalue);

            //get the card png (easier way and less code...)
            if(c.getspecCard() == null)
            {
              if(suit.equals("Hearts")){
                png = "Cards/" + value + "H" + ".png";
              }
              else if(suit.equals("Spades")){
                png = "Cards/" + value + "S" + ".png";
              }
              else if(suit.equals("Clubs")){
                png = "Cards/" + value + "C" + ".png";
              }
              else if(suit.equals("Diamonds")){
                png = "Cards/" + value + "D" + ".png";
              }
            }
            else if(c.getspecCard() != null)        // we are checking if we have a face card :)
            {
              if(c.getspecCard() == "King"){
                switch(suit){
                  case "Hearts" : {png = "Cards/" + 13 + "H" + ".png"; break;}
                  case "Clubs"  : {png = "Cards/" + 13 + "C" + ".png"; break;}
                  case "Spades"  : {png = "Cards/" + 13 + "S" + ".png"; break;}
                  case "Diamonds"  : {png = "Cards/" + 13 + "D" + ".png"; break;}
                }
              }
              else if(c.getspecCard() == "Queen"){
                switch(suit){
                  case "Hearts" : {png = "Cards/" + 12 + "H" + ".png"; break;}
                  case "Clubs"  : {png = "Cards/" + 12 + "C" + ".png"; break;}
                  case "Spades"  : {png = "Cards/" + 12 + "S" + ".png"; break;}
                  case "Diamonds"  : {png = "Cards/" + 12 + "D" + ".png"; break;}
                }
              }
              else if(c.getspecCard() == "Jack"){
                switch(suit){
                  case "Hearts" : {png = "Cards/" + 11 + "H" + ".png"; break;}
                  case "Clubs"  : {png = "Cards/" + 11 + "C" + ".png"; break;}
                  case "Spades"  : {png = "Cards/" + 11 + "S" + ".png"; break;}
                  case "Diamonds"  : {png = "Cards/" + 11 + "D" + ".png"; break;}
                }
              }
              else if(c.getspecCard() == "Ace"){
                switch(suit){
                  case "Hearts" : {png = "Cards/" + 1 + "H" + ".png"; break;}
                  case "Clubs"  : {png = "Cards/" + 1 + "C" + ".png"; break;}
                  case "Spades"  : {png = "Cards/" + 1 + "S" + ".png"; break;}
                  case "Diamonds"  : {png = "Cards/" + 1 + "D" + ".png"; break;}
                }
              }
            }
              //System.out.println("Png is: " +png);
            //adding the card images to the JPanel
            BufferedImage img = null;
            JLabel label = new JLabel();
            try {
              if(first_card){
                img = ImageIO.read(new File(png));
                first_card = false;
              }
              else{
                  if(n == 1)
                    img = ImageIO.read(new File("Cards/blue_back.png"));
                  else if(n==2){
                    img = ImageIO.read(new File(png));
                  }
              }
              //scaling the image to a smaller size and setting it as imageicon for jLabel
              Image dimg = img.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
              ImageIcon imageIcon = new ImageIcon(dimg);
              label.setIcon(imageIcon);
              if(n == 1){
                label.setBounds(dpos, -50, 300, 300);
              }
              else if(n == 2){
                if(iterate == 2){
                  label.setBounds(dpos, 5, 300, 300);
                }
                else{
                  label.setBounds(dpos, -50, 300, 300);
                }
              }
              dpos += 100;
              add(label);
              repaint();


              //System.out.println("Image opened " + png);
            } catch (IOException e) {
              System.out.println("Error = " + e);

            }

          }   //end of if statement for c != null
          iterate++;
          }   //end of for loop for iteration of each card in hand
  }

  public boolean insurance(){
    Card cards[] = game.getBotCards(3);
    if(cards[0].getspecCard() == "Ace")
      return true;
    else 
      return false;
  }
}
