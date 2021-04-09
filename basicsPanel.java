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
  private JButton bet, hit, pass;
  private JTextField betField;                      // Using this to get the input from the bets i guess
  private int userBet;
  private Blackjack game = new Blackjack();
  private Card[] cards;
  //private int players = 1;
  private JLabel wait1, wait2;

    public basicsPanel(){
      setCards();                                   //sets the cards images

      //setting layout to null, default layout is flow layout
      setLayout(null);
      bet = new JButton("Bet");
      bet.setBounds(300,200,100,50);
      bet.setEnabled(false);

      hit = new JButton("Draw");
      hit.setBounds(400,200,100,50);
      hit.setEnabled(false);

      add(bet);

      pass = new JButton("Pass");
      pass.setBounds(350,250,100,50);
      pass.setEnabled(false);
      add(pass);

      wait1 = new JLabel("Waiting for computer player 1 to make moves");
      wait1.setBounds(300,300,200,50);
      add(wait1);

      wait2 = new JLabel("Waiting for computer player 2 to make moves");
      wait2.setBounds(300,300,200,50);
      wait2.setVisible(false);
      add(wait2);


      //Enable buttons for human player once first bot plays
      if(game.players == 2){
        wait1.setVisible(false);
        bet.setEnabled(true);
        hit.setEnabled(true);
        pass.setEnabled(true);
      }

      pass.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
        bet.setEnabled(false);
        hit.setEnabled(false);
        pass.setEnabled(false);
        wait2.setVisible(true);
        System.out.println("On player: "+ game.players+" In panel");
        }
      });


      // Create functionality for the bet button, this will let players place bets.
      bet.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            // Make a new frame to display the betting area.
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
                    int bet = userBet;
                    //System.out.println(userBet);
                    if(userBet > 0)
                    {
                        Pot.addToPot(bet);                               // Add to the pot.
                        game.setUserBet(bet);                           // This sets the current bet from the user for comparison purposes
                        /* int checkPot = Pot.getPot();
                        System.out.println(checkPot);  */
                        betFrame.dispose();                              // Forse the JFrame closed when we successfully make a bet.
                        String betAddress = "Bet Placed.";               // Just let the user know their bet was successfully placed, we can take this out if you guys want.
                        JOptionPane.showMessageDialog(null, betAddress);
                    }
                    else if(userBet < 0)
                    {   // Use this to error check, the user cannot make bets < 0.
                        String error = "Error: Please Input A Value Greater Than 0";
                        JOptionPane.showMessageDialog(null, error);
                    }
                }
            });
        }
      });


      //add hit button
      add(hit);
    hit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        //draw a hard if clicked
          game.playerHit();
          String msg = "Card drawn!";
          JOptionPane.showMessageDialog(null, msg);
          //update the set of cards for player
          setCards();

      }
    });

    } //end of basicsPanel class


    public void setCards(){
      int cardvalue;
      String suit;
      int pos = 250;

      cards = game.getPlayerCards();
      for(Card c : cards){
        //If the card is not null, then get all the data. (Need this error checking because Card array is initialized with size of 14)
        if(c != null){
        cardvalue = c.getcardValue();
        suit = c.getcardSuit();
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
          { // This whole switch statement accounts for if the card has a face (I.E] Jack,Queen,King)
            if(c.getspecCard() == "King"){
              switch(suit){
                case "Hearts" : {png = "Cards/" + 13 + "H" + ".png";}
                case "Clubs"  : {png = "Cards/" + 13 + "C" + ".png";}
                case "Spades"  : {png = "Cards/" + 13 + "S" + ".png";}
                case "Diamonds"  : {png = "Cards/" + 13 + "D" + ".png";}
              }
            }
            else if(c.getspecCard() == "Queen"){
              switch(suit){
                case "Hearts" : {png = "Cards/" + 12 + "H" + ".png";}
                case "Clubs"  : {png = "Cards/" + 12 + "C" + ".png";}
                case "Spades"  : {png = "Cards/" + 12 + "S" + ".png";}
                case "Diamonds"  : {png = "Cards/" + 12 + "D" + ".png";}
              }
            }
            else if(c.getspecCard() == "Jack"){
              switch(suit){
                case "Hearts" : {png = "Cards/" + 11 + "H" + ".png";}
                case "Clubs"  : {png = "Cards/" + 11 + "C" + ".png";}
                case "Spades"  : {png = "Cards/" + 11 + "S" + ".png";}
                case "Diamonds"  : {png = "Cards/" + 11 + "D" + ".png";}
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


          System.out.println("Image opened " + png);
        } catch (IOException e) {
          System.out.println("Error = " + e);

        }

      }   //end of if statement for c != null
      }   //end of for loop for iteration of each card in hand


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
          System.out.println("The bot's card value is: "+cardvalue);
          System.out.println("The bot's card suit is: "+suit);
          
          String png = "";                                     //name of the png file
          String value = String.valueOf(cardvalue);

          //for bot 1 and bot 2, just show the amount of cards with image of the back of the card
          BufferedImage img = null;
          JLabel label = new JLabel();
          try {
            img = ImageIO.read(new File("Cards/blue_back.png"));


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
            if(i == 1) label.setBounds(20, posbot, 200, 200);
            else {
              label.setBounds(600,posbot2,200,200);
              posbot2 += 80;
            }

            posbot += 80;

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
          System.out.println("The dealer's card value is: " + cardvalue + "The suit is: " + suit);

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
                case "Hearts" : {png = "Cards/" + 13 + "H" + ".png";}
                case "Clubs"  : {png = "Cards/" + 13 + "C" + ".png";}
                case "Spades"  : {png = "Cards/" + 13 + "S" + ".png";}
                case "Diamonds"  : {png = "Cards/" + 13 + "D" + ".png";}
              }
            }
            else if(c.getspecCard() == "Queen"){
              switch(suit){
                case "Hearts" : {png = "Cards/" + 12 + "H" + ".png";}
                case "Clubs"  : {png = "Cards/" + 12 + "C" + ".png";}
                case "Spades"  : {png = "Cards/" + 12 + "S" + ".png";}
                case "Diamonds"  : {png = "Cards/" + 12 + "D" + ".png";}
              }
            }
            else if(c.getspecCard() == "Jack"){
              switch(suit){
                case "Hearts" : {png = "Cards/" + 11 + "H" + ".png";}
                case "Clubs"  : {png = "Cards/" + 11 + "C" + ".png";}
                case "Spades"  : {png = "Cards/" + 11 + "S" + ".png";}
                case "Diamonds"  : {png = "Cards/" + 11 + "D" + ".png";}
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
                img = ImageIO.read(new File("Cards/blue_back.png"));
            }
            //scaling the image to a smaller size and setting it as imageicon for jLabel
            Image dimg = img.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            label.setIcon(imageIcon);
            label.setBounds(dpos, -50, 300, 300);
            dpos += 100;
            add(label);
            repaint();


            System.out.println("Image opened " + png);
          } catch (IOException e) {
            System.out.println("Error = " + e);

          }

        }   //end of if statement for c != null
        }   //end of for loop for iteration of each card in hand



      }



/*
    public void paintComponent( Graphics g )
    {
        g.setColor(Color.white);
        g.fillRect(280, 360 , 100, 150);
        g.fillRect(400, 360 , 100, 150);
    }
*/

}
