public class Pot{

    private static int potTotal = 0;
    private static int userBet = 0;

    public static void addToPot(int bet){                   // add to the pot total.
        Pot.potTotal += bet;
    }

    public static int getPot(){                             // Return the total value of the pot
        return potTotal;
    }


}