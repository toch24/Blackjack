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

public class basicsPanel extends JPanel{
  private JButton bet;
  private JTextField betField;                      // Using this to get the input from the bets i guess
  private int userBet;
  private Blackjack game = new Blackjack();
  private Card[] cards;

    public basicsPanel(){
      setCards();                                   //sets the cards images
      bet = new JButton("Bet");
      bet.setBounds(350,400,100,50);
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
    }


    public void setCards(){
      int cardvalue;
      String suit;

      cards = game.getPlayerCards();
      for(Card c : cards){
        cardvalue = c.getcardValue();
        suit = c.getcardSuit();
        String png;
        String value = String.valueOf(cardvalue);
        
        //get the card png (easier way and less code...)
        if(suit.equals("Heart")){
          png = value + "H" + ".png";
        }
        else if(suit.equals("Spades")){
          png = value + "S" + ".png";
        }
        else if(suit.equals("Clubs")){
          png = value + "C" + ".png";
        }
        else if(suit.equals("Diamonds")){
          png = value + "D" + ".png";
        }
      }

    }


    public void paintComponent( Graphics g )
    {
        g.setColor(Color.white);
        g.fillRect(280, 360 , 100, 150);
        g.fillRect(400, 360 , 100, 150);
    }

    /*    Code too long, not deleting for now just in case...
    public String getPng(int value, String s){
      String png = null;
      String suit;

      switch(value){
        case 1: 
              switch(s){
                case "Hearts": png = "AH.png";
                            break;
                case "Spades": png = "AS.png";
                            break;
                case "Clubs": png = "AC.png";
                            break;
                case "Diamonds": png = "AD.png";
                            break;
              }
              break;
        case 2:
         switch(s){
                case "Hearts": png = "2H.png";
                            break;
                case "Spades": png = "2S.png";
                            break;
                case "Clubs": png = "2C.png";
                            break;
                case "Diamonds": png = "2D.png";
                            break;
              }
              break;
        case 3:
            switch(s){
                case "Hearts": png = "3H.png";
                            break;
                case "Spades": png = "3S.png";
                            break;
                case "Clubs": png = "3C.png";
                            break;
                case "Diamonds": png = "3D.png";
                            break;
              }
              break;
        case 4:
             switch(s){
                case "Hearts": png = "4H.png";
                            break;
                case "Spades": png = "4S.png";
                            break;
                case "Clubs": png = "4C.png";
                            break;
                case "Diamonds": png = "4D.png";
                            break;
              }
              break;
        case 5:
              switch(s){
                case "Hearts": png = "5H.png";
                            break;
                case "Spades": png = "5S.png";
                            break;
                case "Clubs": png = "5C.png";
                            break;
                case "Diamonds": png = "5D.png";
                            break;
              }
              break;
        case 6:
              switch(s){
                case "Hearts": png = "6H.png";
                            break;
                case "Spades": png = "6S.png";
                            break;
                case "Clubs": png = "6C.png";
                            break;
                case "Diamonds": png = "6D.png";
                            break;
              }
              break;
        


      }
      return png;
    }
   */
 
}

