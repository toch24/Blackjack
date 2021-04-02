// Andrew Morris, Oksana Rubis, Tommy Chong
// Blackjack game
// Project X

import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class Blackjack extends JFrame{

/*
    public static void main(String args[])
    {
        //creating a new Blackjack game
        new Blackjack();

        // This commented area is for error checking the deck (we should keep this here for now so we can see what happens over time)
         Deck object = new Deck();
        object.printDeck();
        object.shuffle(object.getDeck());
        object.printDeck();

    }

  */

    public Blackjack(){
        super("Blackjack");
        newGame();
    }

public void newGame(){
    //setting the deck
    Deck deck = new Deck();
    deck.shuffle(deck.getDeck());

    //setting the JPanel for the JFrame
/*
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    JPanel panel = new JPanel(new FlowLayout(SwingConstants.LEADING, 10, 10));
    panel.setBackground(Color.green.darker());
    add(panel);

    //setting size and packing everything up
    setPreferredSize(new Dimension(800,600));
    setResizable(false);
    pack();
    setVisible(true);
*/

}


class dealer{

}

class player{
    // Player can get another card if they call hit
    public void hit(){                      // This is where we are going to implement some buttons and some button functionality to give the player another card

    }

 /*   public void fillHand(Deck.card getCard){

    }*/
}


} //end of BlackJack class
