public class Pot{

    private static int potTotal = 0;
    private static int userBet = 0;

    public static void addToPot(int bet){
        Pot.potTotal += bet;
    }

    public static int getPot(){
        return potTotal;
    }


}