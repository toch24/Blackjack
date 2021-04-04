// Andrew Morris, Oksana Rubis, Tommy Chong
// Blackjack game
// Project X

import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class Blackjack{

    public Blackjack(){
        newGame();
    }

public void newGame(){
    //setting the deck
    Deck deck = new Deck();
    deck.shuffle(deck.getDeck());

    //giving two cards to each player
    Bot bot1 = new Bot();
    bot1.addCard(deck.nextCard());
    bot1.addCard(deck.nextCard());

    Bot bot2 = new Bot();
    bot2.addCard(deck.nextCard());
    bot2.addCard(deck.nextCard());

    Bot dealer = new Bot();
    dealer.addCard(deck.nextCard());
    dealer.addCard(deck.nextCard());

}


} //end of BlackJack class
