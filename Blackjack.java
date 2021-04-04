// Andrew Morris, Oksana Rubis, Tommy Chong
// Blackjack game
// Project X

import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class Blackjack extends JFrame{

    public Blackjack(){
        super("Blackjack");
        newGame();
    }

public void newGame(){
    //setting the deck
    Deck deck = new Deck();
    deck.shuffle(deck.getDeck());

}


} //end of BlackJack class
