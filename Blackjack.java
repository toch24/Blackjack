// Andrew Morris, Oksana Rubis, Tommy Chang
//Blackjack game
// Project X

import java.util.Random;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.Vector;

public class Blackjack{

    public void main(String args[])
    {

    }   
}

class dealer{ 

}

class player{

}

class deck{
    private Random rand = new Random();
    private card[] deck;

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

    class card{
        private int cardValue;
        private int suitValue;
        private String cardSuit;
        private String specCard;
        
        public void setCard(){  
            //Create the cards for our deck;
            while(deck.length != 51){
                cardValue = rand.nextInt(4);                                            // Generate a random value 1-4 for the card face;
                suitValue = rand.nextInt((13));                                         // generate a random value 2-10 for the card value;
                card newCard = new card();                                              // Create a card object to hold the information in the card

                if(cardValue < 2 || cardValue > 10)
                {
                    if (cardValue == 1)
                    {
                        newCard.specCard = "Ace";
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

                switch(suitValue){
                    case 1 : {cardSuit = "Clubs"; newCard.cardSuit = cardSuit;}
                    case 2 : {cardSuit = "Diamonds"; newCard.cardSuit = cardSuit;}
                    case 3 : {cardSuit = "Hearts"; newCard.cardSuit = cardSuit;}
                    case 4 : {cardSuit = "Spades"; newCard.cardSuit = cardSuit;}
                }


            }
        }

    }
}   