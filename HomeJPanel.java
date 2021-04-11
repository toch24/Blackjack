
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

public class HomeJPanel extends JPanel
{
  // private JTextField textField1; 							//text field with set size
   public static Blackjack game;
   private JLabel welcomeLabel;
   private JButton button1;
   private JButton button3;
   private JButton button2;
   private static JFrame basicsFrame;
   private static JPanel basicsPanel;
   private static JButton newRound;

   private int userBuyIn;
   JTextField buyInField;

   public HomeJPanel()
   {
      setLayout( new FlowLayout() ); 						//set frame layout

      setBackground( Color.green.darker());

      welcomeLabel = new JLabel( "BLACKJACK");
      setLayout(null);

      Border border = BorderFactory.createLineBorder(Color.BLACK);
      welcomeLabel.setBorder(border);
      //welcomeLabel.setPreferredSize(new Dimension(450, 300));

      welcomeLabel.setBounds(150, 50, 450, 300);
      welcomeLabel.setToolTipText( "This is label1" );
      welcomeLabel.setFont( new Font( null, Font.BOLD, 50 ) );
      welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
      welcomeLabel.setVerticalAlignment(JLabel.CENTER);
      add( welcomeLabel); 									    //add label1 to JFrame


      button1 = new JButton("Play Basics"); 					//option for user to play again
      button2 = new JButton("RULES"); 					        //option for user to play again
      button3 = new JButton("Play Casino"); 					//option for user to play again

      //setLayout(null);
      Dimension size = button1.getPreferredSize();
      button1.setBounds(100, 400,100, 100);
      button2.setBounds(350, 400,100, 100);
      button3.setBounds(600, 400,100, 100);

      add(button1);
      add(button2);
      add(button3);

      //play basics button
      button1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            basicsFrame = new JFrame("Basic BlackJack");
            basicsPanel = new basicsPanel();

            basicsFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            basicsFrame.setBackground( Color.green.darker()); 				//set frame background color

            basicsFrame.setSize( 800, 600 ); 						//set frame size
            basicsFrame.setResizable(false);
            setVisible(true);

            // start testing

            newRound = new JButton("newRound");
            newRound.setBounds(350,300,100,50);
            newRound.setEnabled(true);
            basicsPanel.add(newRound);

            newRound.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            newRound();
        }
      });

      // end testing

            // Adding the buyIn frame and its functionality
            JFrame buyInFrame = new JFrame("Buy In");
            JPanel buyInPanel = new JPanel();
            JLabel buyInLabel = new JLabel("Please determine your buy in value (>= 100): ");
            buyInField = new JTextField();

            buyInFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            buyInFrame.add( buyInPanel );
            buyInFrame.setResizable(false);
            buyInFrame.setSize(300,100);

            // Create JTextField to allow for user inputs
            buyInField = new JTextField(4);
            buyInField.setBounds(50,100, 200,30);
            buyInField.setVisible(true);

            // add the textfield to the JPanel with the label
            buyInPanel.add(buyInLabel);
            buyInPanel.add(buyInField);

            buyInFrame.setVisible(true);                                   // Make it visible.
            buyInFrame.toFront();                                          // Make it stand out at something that needs to be interacted with.
            buyInFrame.requestFocus();                                     // Focus the BuyInFrame so the user knows to enter something.

            buyInField.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    userBuyIn = Integer.parseInt(buyInField.getText());     // Take the user bet.
                    int bet = userBuyIn;
                    //System.out.println(userBet);
                    if(bet >= 100 && bet % 5 == 0)
                    {
                  //      add basicsPanel
                    //    JPanel basicsPanel = new basicsPanel();

                        Pot.addToPot(bet);                                  // Add to the pot.
                        Player.setWallet(bet);                              // This sets the current bet from the user for comparison purposes

                        int temp = 0;
                        while(temp < 3){
                            Bot.setBotBuyIn();
                            temp++;
                        }

                        basicsFrame.add( basicsPanel ); 					//add screensaverJPanel to frame
                        basicsPanel.setBackground( Color.green.darker());
                        basicsFrame.setVisible( true );                     //display the game
                        int checkWallet = Player.getWallet();
                        System.out.println("Players wallet is: "+ checkWallet);

                        buyInFrame.dispose();                               // Forse the JFrame closed when we successfully make a bet.
                        String betAddress = "Buy In Successful.";           // Just let the user know their bet was successfully placed, we can take this out if you guys want.
                        JOptionPane.showMessageDialog(null, betAddress);
                    }
                    else
                    {   // Use this to error check, the user cannot make bets < 0.
                        String error = "Error: Please Input A Value Greater Than Or Equal To 100, but a factor of 5";
                        JOptionPane.showMessageDialog(null, error);
                    }
                }
            });


          }
      });


      button2.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  String string = "Insert Rules Here: ";
        	  JOptionPane.showMessageDialog( null, string );
          }
      });


      //play casino button
      button3.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JFrame basicsFrame = new JFrame("Casino BlackJack");
            JPanel casinoPanel = new JPanel();
            basicsFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            basicsFrame.add( casinoPanel ); 					//add screensaverJPanel to frame
            basicsFrame.setBackground( Color.green.darker() ); 				//set frame background color
            casinoPanel.setBackground( Color.green.darker());
            basicsFrame.setSize( 800, 600 ); 						//set frame size
            basicsFrame.setResizable(false);
            setVisible(false );
            basicsFrame.setVisible( true ); 						//display frame
          }
      });
   }

   public static void newRound(){

            //basicsFrame.dispose();
            //JFrame newRoundFrame = new JFrame();
            JPanel newRoundPanel = new basicsPanel();

            basicsFrame.remove(newRoundPanel);
            basicsFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            basicsFrame.remove(basicsPanel);

            basicsFrame.add( newRoundPanel ); 					//add screensaverJPanel to frame
            basicsFrame.setBackground( Color.green.darker()); 				//set frame background color
            newRoundPanel.setBackground( Color.green.darker());
            basicsFrame.setSize( 800, 600 ); 						//set frame size
            basicsFrame.setResizable(false);
            //HomeJPanel.setVisible(true);
            basicsFrame.setVisible( true );
            newRound = new JButton("newRound");
            newRound.setBounds(350,300,100,50);
            newRound.setEnabled(true);
            newRoundPanel.add(newRound);

            int temp = Pot.getPot();
            System.out.println(temp);
        }
} // End JHomePanel
