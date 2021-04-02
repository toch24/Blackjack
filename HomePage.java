//Home Page for BlackJack
import java.awt.Color;
import javax.swing.JFrame;

public class HomePage
{
   public static void main( String args[] )
   {
      JFrame frame = new JFrame( "BLACKJACK" );			//create frame for ScreenSaverJPanel
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

      HomeJPanel homeJPanel = new HomeJPanel(); 
      frame.add( homeJPanel ); 					//add screensaverJPanel to frame
      frame.setBackground( Color.green.darker() ); 				//set frame background color
      frame.setSize( 800, 600 ); 						//set frame size
      frame.setResizable(false); 
      frame.setVisible( true ); 						//display frame
   } // end main
} 