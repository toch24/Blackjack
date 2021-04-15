public class Pot{

    private static double potTotal = 0;
    private static double highestBet = 0;

    public static void addToPot(double bet){                   // add to the pot total.
        Pot.potTotal += bet;
    }

    public static double getPot(){                             // Return the total value of the pot
        return potTotal;
    }

    public static double getHighestBet(){                      // Return the current highest best
        return highestBet;
    }

    public static void resetPot(){

        potTotal = 0;
    }
    public static void setHighestBet(double bet){
        highestBet = bet;
    }


}
