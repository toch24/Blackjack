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
  private JButton bet, hit;
  private JTextField betField;                      // Using this to get the input from the bets i guess
  private int userBet;
  private Blackjack game = new Blackjack();
  private Card[] cards;

    public basicsPanel(){
      setCards();                                   //sets the cards images
      bet = new JButton("Bet");
      bet.setBounds(350,400,100,50);

      hit = new JButton("Draw");
      hit.setBounds(350,500,100,50);

      add(bet);
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
                        System.out.println(checkPot); */
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


      add(hit);
      // Create functionality for the bet button, this will let players place bets.
    hit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
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

      cards = game.getPlayerCards();
      for(Card c : cards){
        //If the card is not null, then get all the data. (Need this error checking because Card array is initialized with size of 14)
        if(c != null){
        cardvalue = c.getcardValue();
        suit = c.getcardSuit();
        String png = "";                                     //name of the png file
        String value = String.valueOf(cardvalue);

        //get the card png (easier way and less code...)
        if(suit.equals("Hearts")){
          png = "Cards\\" + value + "H" + ".png";
        }
        else if(suit.equals("Spades")){
          png = "Cards\\" + value + "S" + ".png";
        }
        else if(suit.equals("Clubs")){
          png = "Cards\\" + value + "C" + ".png";
        }
        else if(suit.equals("Diamonds")){
          png = "Cards\\" + value + "D" + ".png";
        }

        //todo: add the png to jpanel
        //code goes here
        BufferedImage img = null;
        JLabel label = new JLabel();
        try {
          img = ImageIO.read(new File(png));
  
          //scaling the image to a smaller size and setting it as imageicon for jLabel
          Image dimg = img.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
          ImageIcon imageIcon = new ImageIcon(dimg);
          label.setIcon(imageIcon);
          add(label);


          System.out.println("Image opened " + png);
        } catch (IOException e) {
          System.out.println("Error = " + e);
  
        } 

      }   //end of if statement
      }   //end of for loop


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


