
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.Border;

public class HomeJPanel extends JPanel 
{
  // private JTextField textField1; 							//text field with set size

   private JLabel welcomeLabel;
   private JButton button1;
   private JButton button3;
   private JButton button2;
 
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
      add( welcomeLabel); 									//add label1 to JFrame

      
      button1 = new JButton("Play Basics"); 					//option for user to play again
      button2 = new JButton("RULES"); 					//option for user to play again
      button3 = new JButton("Play Casino"); 					//option for user to play again
      
      //setLayout(null);
      Dimension size = button1.getPreferredSize();
      button1.setBounds(100, 400,100, 100);
      button2.setBounds(350, 400,100, 100);
      button3.setBounds(600, 400,100, 100);
      
      add(button1);
      add(button2);
      add(button3);
      
      
      button1.addActionListener(new ActionListener() { 		//if options to play again is clicked by user
          public void actionPerformed(ActionEvent e) {
        	  JFrame basicsFrame = new JFrame("Basic BlackJack");
              JPanel basicsPanel = new JPanel();
              basicsFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
              basicsFrame.add( basicsPanel ); 					//add screensaverJPanel to frame
              basicsFrame.setBackground( Color.green.darker() ); 				//set frame background color
              basicsPanel.setBackground( Color.green.darker());
              basicsFrame.setSize( 800, 600 ); 						//set frame size
              basicsFrame.setResizable(false); 
              setVisible(false ); 	
              basicsFrame.setVisible( true ); 						//display frame
              
          }
      });
      
      
      button2.addActionListener(new ActionListener() { 		//if options to play again is clicked by user
          public void actionPerformed(ActionEvent e) {
        	  String string = "Insert Rules Here: ";
        	  JOptionPane.showMessageDialog( null, string ); 
          }
      });
      
      button3.addActionListener(new ActionListener() { 		//if options to play again is clicked by user
          public void actionPerformed(ActionEvent e) {
        	  JFrame basicsFrame = new JFrame("Casino BlackJack");
              JPanel basicsPanel = new JPanel();
              basicsFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
              basicsFrame.add( basicsPanel ); 					//add screensaverJPanel to frame
              basicsFrame.setBackground( Color.green.darker() ); 				//set frame background color
              basicsPanel.setBackground( Color.green.darker());
              basicsFrame.setSize( 800, 600 ); 						//set frame size
              basicsFrame.setResizable(false); 
              setVisible(false ); 	
              basicsFrame.setVisible( true ); 						//display frame
          }
      });
      
   }
}