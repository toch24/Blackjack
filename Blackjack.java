// Andrew Morris, Oksana Rubis, Tommy Chang
//Blackjack game
// Project X

import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.Vector;

public class blackjack{

    public static void main(String args[])
    {
        deck gameDeck = new deck();                 // testing
        gameDeck.populateDeck();
    }   
}

class dealer{ 

}

class player{
    // Player can get another card if they call hit
    public void hit(){                      // This is where we are going to implement some buttons and some button functionality to give the player another card

    }

    public void fillHand(card getCard){

    }
}

class deck{
    private card[] deck = new card[52];
    private Random rand = new Random();

    public void populateDeck(){
        int counter = 0;                // Create a counter so we can fill the entire deck without the use of an interator for the sake of runtime

        while (counter != 52)
        {   // Fill the deck with 52 cards (The entire deck)
            card newCard = new card();                          //Create card variable
            newCard.setCards(newCard);                          // Call the setCard function to populate this card

            deck[counter] = newCard;
            System.out.print("\nThis is whats in the deck at counter: " + counter + " " + deck[counter].getValue(deck[counter]) + " " + deck[counter].getSuit(deck[counter]));
            if(deck[counter].getSpec(deck[counter]) != null)
            {
                System.out.print(" " + deck[counter].getSpec(deck[counter]));
            }
            counter++;
        }
    }

    public void shuffle(){
        for (int i = 0; i < 4; ++i)
        {
            for (int j = 0; j < 52; ++j){
                int r = j + rand.nextInt(52 - j);

                card temp = deck[r];
                deck[j] = deck[r];
                deck[r] = temp;
            }
        }
    }
}

class card{
    private int cardValue;
    private int suitValue;
    private String cardSuit;
    private String specCard;
    private Random rand = new Random();
    private ArrayList<card> poppedCards = new ArrayList<card>(52);

    public void setCards(card newCard){  
        //Create the cards for our deck;
            
            cardValue = rand.nextInt((13 - 1) + 1);                                          // Generate a random value 1-4 for the card face;
            suitValue = rand.nextInt((4 - 1) + 1);                                         // generate a random value 2-10 for the card value;
            
            if(cardValue == 0){
                while(cardValue == 0){
                    cardValue = rand.nextInt((13 - 1) + 1);                                          // Generate a random value 1-4 for the card face;
                }
            }
            if(suitValue == 0){
                while(suitValue == 0){
                    suitValue = rand.nextInt((4 - 1) + 1);                                          // Generate a random value 1-4 for the card face;
                }
            }

            if(cardValue < 2 || cardValue > 10)
            {
                if (cardValue == 1)
                {
                    newCard.specCard = "Ace";
                    newCard.cardValue = 1;
                }
                else if (cardValue > 10)
                {
                    switch(cardValue){
                        case 11 : {newCard.specCard = "Jack"; newCard.cardValue = 10;}
                        case 12 : {newCard.specCard = "Queen"; newCard.cardValue = 10;}
                        case 13 : {newCard.specCard = "King"; newCard.cardValue = 10;}
                    }
                }
            }
            else
                newCard.cardValue = cardValue;

                if(suitValue > 0){
                    switch(suitValue){
                        case 1 : {newCard.cardSuit = "Clubs";}
                        case 2 : {newCard.cardSuit = "Diamonds";}
                        case 3 : {newCard.cardSuit = "Hearts";}
                        case 4 : {newCard.cardSuit = "Spades";}
                    }
                }
                
            if(!poppedCards.isEmpty()){
                if(!checkCardEqualsTo(newCard)){
                    poppedCards.add(newCard);
                }
            }
            else
                poppedCards.add(newCard);
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

    public boolean checkCardEqualsTo(card Check){                                       // This function will be used to make sure we dont put two of the same cards in the deck
        for (int i = 0; i < poppedCards.size() + 1; ++i){
            if(poppedCards.get(i).cardSuit == Check.cardSuit){
                if(poppedCards.get(i).cardValue == Check.cardValue)
                    return true;
            }
            else if(poppedCards.get(i).cardSuit == Check.cardSuit)
                if(poppedCards.get(i).specCard == Check.specCard){
                    return true;
                }
            else return false;
        }
        return false;
    }
}
