public class Card {
    private int cardValue;
    private String cardSuit;
    private String specCard;

    //default constructor
	public Card() {

		cardSuit = "";
        specCard = "";
		cardValue = 0;
	}

    //constructor with parameters
    public Card(String newcardSuit, int newcardValue){
        if(newcardValue < 1 || newcardValue > 13){
            //card value is invalid
        }
        else{
            this.cardValue = newcardValue;

        if(!newcardSuit.isEmpty()){
            this.cardSuit = newcardSuit;
        }

        if(newcardValue == 1){
            this.specCard = "Ace";
        }
        else if(newcardValue == 11){
            this.specCard = "Jack";
            this.cardValue = 11;
        }
        else if(newcardValue == 12){
            this.specCard = "Queen";
            this.cardValue = 12;
        }
        else if(newcardValue == 13){
            this.specCard = "King";
            this.cardValue = 13;
        }
        } //end of else
    }

    public String getcardSuit() {
		return cardSuit;
	}

    public String getspecCard() {
		return specCard;
	}

    public int getcardValue(){
        return cardValue;
    }

}
