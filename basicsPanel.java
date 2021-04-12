import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument.Content;
import javax.imageio.ImageIO;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class basicsPanel extends JPanel{
  private JButton bet, hit, pass, newRound, startGame;
  private JTextField betField;                      // Using this to get the input from the bets i guess
  private int userBet = 0;
  private Blackjack game;
  private Card[] cards;
  private int players = 1;
  private JLabel highestbetlabel, cp1wallet, pwallet, cp2wallet, dealerwallet;


    public basicsPanel(){
      game = new Blackjack();
      setCards(1);                                   //sets the cards images

      //setting layout to null, default layout is flow layout
      setLayout(null);
      bet = new JButton("Bet");
      bet.setBounds(300,200,100,50);
      bet.setEnabled(false);

      hit = new JButton("Hit");
      hit.setBounds(400,200,100,50);
      hit.setEnabled(false);

      //add(bet);

      pass = new JButton("Hold");
      pass.setBounds(350,250,100,50);
      pass.setEnabled(false);
      add(pass);

      startGame = new JButton("Start Game");
      startGame.setBounds(350,500,100,50);
      startGame.setVisible(true);
      startGame.setEnabled(true);
      add(startGame);

      highestbetlabel = new JLabel("Current Pot Total: " + String.valueOf(Pot.getPot()));
      highestbetlabel.setBounds(300,150,200,50);
      add(highestbetlabel);

      pwallet = new JLabel("Your wallet total: " + String.valueOf(game.returnWallet(2)));
      pwallet.setBounds(250,350,200,50);
      add(pwallet);

      cp1wallet = new JLabel("Player 1 wallet total: " + String.valueOf(game.returnWallet(1)));
      cp1wallet.setBounds(50,150,200,50);
      add(cp1wallet);

      cp2wallet = new JLabel("Player 3 wallet total: " + String.valueOf(game.returnWallet(3)));
      cp2wallet.setBounds(600,150,200,50);
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
        bet.setEnabled(false);
        hit.setEnabled(false);
        pass.setEnabled(false);
        players++;
        playerturns(0);
        System.out.println("On player: "+ players +" In panel");
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
            players++;
            playerturns(1);
          }
          else{
            String msg = "Card drawn!";
            JOptionPane.showMessageDialog(null, msg);
          }

          //update the set of cards for player
          setCards(1);
      }
    });



    } //end of basicsPanel class

public void playerturns(int n){
      boolean bot1Bust = false;
      boolean bot2Bust = false;
      boolean playerBust = false;

      if (n == 1)
        playerBust = true;
      else
        playerBust = false;

      if(players == 1){
        try{
      //bet.setEnabled(false);
      hit.setEnabled(false);
      pass.setEnabled(false);

      boolean play = game.play(players);
      setCards(1);

      cp1wallet.setVisible(false);
      cp1wallet = new JLabel("Player 1 wallet total: " + String.valueOf(game.returnWallet(1)));
      cp1wallet.setBounds(50,150,200,50);
      cp1wallet.setVisible(true);
      add(cp1wallet);

      if(play){
        String msg = "Player 1 busted, they are out for this round";
        JOptionPane.showMessageDialog(null, msg);
        bot1Bust = true;
        System.out.println("Player 1 busted" + bot1Bust);
        //highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
        players++;
      }
      else {
        String msg = "Player 1 played";
        JOptionPane.showMessageDialog(null, msg);
        highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
        bot1Bust = false;
        players++;
      }
        }
        catch(Exception e){
        }
      }
      //Enable buttons for human player once player places their bet
      if(players == 2){
        String msg = "Your turn!";
        JOptionPane.showMessageDialog(null, msg);
        bet.setEnabled(true);
        hit.setEnabled(true);
        pass.setEnabled(true);
  
        JFrame betFrame = new JFrame("Betting...");
        JPanel betPanel = new JPanel();
        JLabel betLabel = new JLabel("Place your bet: ");           // address the player!
        JOptionPane errorCheck = new JOptionPane("Error");

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

        betFrame.setVisible(true);                                   // Make it visible.

        betField.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              userBet = Integer.parseInt(betField.getText());      // Take the user bet.
              if(userBet == 5 || userBet == 10 || userBet == 50 || userBet == 100 || userBet == 500)
              {
                //TODO: if the bet is less than the highest bet, re-prompt the user for a new bet
                if(userBet >= Pot.getHighestBet()){
                  game.setUserBet(userBet);                            // This sets the current bet from the user for comparison purposes
                  Pot.addToPot(userBet);                               // Add to the pot.
                  game.setHighestBet();
                  highestbetlabel.setText("Current Pot Total: " + String.valueOf(Pot.getPot()));
                  betFrame.dispose();                              // Forse the JFrame closed when we successfully make a bet.
                  String betAddress = "Bet Placed.";               // Just let the user know their bet was successfully placed, we can take this out if you guys want.
                  JOptionPane.showMessageDialog(null, betAddress);
                  }
                else{
                  String betAddress = "Bet must match the highest bet or raise it.";              
                  JOptionPane.showMessageDialog(null, betAddress);
                  betPanel.add(betLabel);
                  betPanel.add(betField);
          
                  betFrame.setVisible(true);                                   // Make it visible.
          
                }

                int newWallet = Player.getWallet() - userBet;

                Player.setWalletBet(newWallet);

                pwallet.setVisible(false);
                pwallet = new JLabel("Player 1 wallet total: " + String.valueOf(game.returnWallet(2)));
                pwallet.setBounds(250,350,200,50);
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


        pwallet.setVisible(false);
        pwallet = new JLabel("Player 1 wallet total: " + String.valueOf(game.returnWallet(2)));
        pwallet.setBounds(250,350,200,50);
        pwallet.setVisible(true);
        add(pwallet);

      }

      if(players == 3){
        try{
          Thread.sleep(1000);
      bet.setEnabled(false);
      hit.setEnabled(false);
      pass.setEnabled(false);
      boolean play = game.play(players);

      cp2wallet.setVisible(false);
      cp2wallet = new JLabel("Player 2 wallet total: " + String.valueOf(game.returnWallet(3)));
      cp2wallet.setBounds(600,150,200,50);
      cp2wallet.setVisible(true);
      add(cp2wallet);

      setCards(1);
      if(play){
        String msg = "Player 3 busted, they are out for this round";
        JOptionPane.showMessageDialog(null, msg);
        bot2Bust = true;
        //highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
        players++;
      }
      else {
        String msg = "Player 3 played";
        JOptionPane.showMessageDialog(null, msg);
        highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
        bot2Bust = false;
        players++;
      }
    }
      catch(InterruptedException ex){
        Thread.currentThread().interrupt();
      }
      }

      if(players == 4){
        try{
        Thread.sleep(1000);
        bet.setEnabled(false);
        hit.setEnabled(false);
        pass.setEnabled(false);

        boolean play = game.play(players);
        setCards(2);
        if(play){
          String msg = "Dealer busted, all players still in the round win!";
          JOptionPane.showMessageDialog(null, msg);
          //highestbetlabel.setText("Highest bet: " + String.valueOf(Pot.getHighestBet()));
          players = 1;
          if(!bot1Bust){
            System.out.println("Dealer gives money to bot1");
            game.setBotWallet(1);
            System.out.println("Bot1 wallet is now: "+ game.returnWallet(1));
          }
          if(!bot2Bust){
            System.out.println("Dealer gives money to bot2");
            game.setBotWallet(2);
            System.out.println("Bot2 wallet is now: "+ game.returnWallet(3));
          }
          if(!playerBust){
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

          if(!bot1Bust && game.handMatch(1)){
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
          }

        }
      }
      catch(InterruptedException ex){
        Thread.currentThread().interrupt();
    }
  }
}


    public void setCards(int n){
      int cardvalue;
      String suit;
      int pos = 250;
      if(n ==1){
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

         //System.out.println("Png is: " +png);
         //adding the card images to the JPanel
        BufferedImage img = null;
        JLabel label = new JLabel();
        try {
          img = ImageIO.read(new File(png));

          //scaling the image to a smaller size and setting it as imageicon for jLabel
          Image dimg = img.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          label.setIcon(imageIcon);
          label.setBounds(pos, 300, 300, 300);
          pos += 100;
          add(label);
          repaint();


        //  System.out.println("Image opened " + png);
        } catch (IOException e) {
          System.out.println("Error = " + e);

        }

      }   //end of if statement for c != null
    }   //end of for loop for iteration of each card in hand
}

        //set bot cards

        int posbot = 150;
        int posbot2 = 150;
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
        for(Card c : cards){
          //If the card is not null, then get all the data. (Need this error checking because Card array is initialized with size of 14)
          if(c != null){
          cardvalue = c.getcardValue();
          suit = c.getcardSuit();
          //System.out.println("The dealer's card value is: " + cardvalue + "The suit is: " + suit);
          String temp = c.getspecCard();
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
                else if(n==2)
                  img = ImageIO.read(new File(png));
            }
            //scaling the image to a smaller size and setting it as imageicon for jLabel
            Image dimg = img.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            label.setIcon(imageIcon);
            label.setBounds(dpos, -50, 300, 300);
            dpos += 100;
            add(label);
            repaint();


            //System.out.println("Image opened " + png);
          } catch (IOException e) {
            System.out.println("Error = " + e);

          }

        }   //end of if statement for c != null
        }   //end of for loop for iteration of each card in hand



}

}
