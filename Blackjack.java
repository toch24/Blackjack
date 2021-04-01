// Andrew Morris, Oksana Rubis, Tommy Chang
// Blackjack game
// Project X

import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class Blackjack{

    public static void main(String args[])
    {
        deck temporary = new deck();
        temporary.populateDeck();
    }   
}

class dealer{ 

}

class player{
    // Player can get another card if they call hit
    public void hit(){                      // This is where we are going to implement some buttons and some button functionality to give the player another card

    }

    public void fillHand(deck.card getCard){

    }
}

class deck{
    private card[] gameDeck = new card[52];
    private Random rand = new Random();


    public void populateDeck(){
        int counter = 0;
        card temp = new card();

        temp.setCards(gameDeck);

        while(counter != gameDeck.length){
            System.out.print("\nThis is whats in the gameDeck at counter: " + counter + " " + gameDeck[counter].getValue(gameDeck[counter]) + " " + gameDeck[counter].getSuit(gameDeck[counter]));
            if(gameDeck[counter].getSpec(gameDeck[counter]) != null)
            {
                System.out.print(" " + gameDeck[counter].getSpec(gameDeck[counter]));
            }
            counter++;
        }
    }

    public void shuffle(){
        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 52; ++j){
                int r = j + rand.nextInt(52 - j);

                card temp = gameDeck[r];
                gameDeck[j] = gameDeck[r];
                gameDeck[r] = temp;
            }
        }
    }

    class card{
        private int cardValue;
        private String cardSuit;
        private String specCard;
    
        public card[] setCards(card newCard[]){  
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 13; ++j)
                {
                    if(i == 0)
                        newCard[j].cardSuit = "Hearts";
                    else if(i == 1)
                        newCard[j].cardSuit = "Spades";
                    else if(i == 2)
                        newCard[j].cardSuit = "Clubs";
                    else if(i == 3)
                        newCard[j].cardSuit = "Diamonds";
                    
                    if(j == 0)
                    {
                        newCard[j].cardValue = 1;
                        newCard[j].specCard = "Ace";
                    }
                    else if(j > 0 && j < 9)
                    {
                        newCard[j].cardValue = j+1;
                    }
                    else if(j >= 9)
                    {
                        newCard[j].cardValue = 10;
                        if(j == 10){
                            newCard[j].cardValue = 10;
                            newCard[j].specCard = "Jack";
                        }
                        else if(j == 11){
                            newCard[j].cardValue = 10;
                            newCard[j].specCard = "Queen";
                        }
                        else if(j == 12){
                            newCard[j].cardValue = 10;
                            newCard[j].specCard = "King";
                        }
                    }
                }
                return newCard;
            }
    
        public int getValue(card passed){
            return passed.cardValue;
        }
    
        public String getSuit(card passed){
            return passed.cardSuit;
        }
    
        public String getSpec(card passed){
            if(specCard != null)
                return passed.specCard;
            else
                return null;    
        }
    }    
}

