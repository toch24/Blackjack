public class Pot{

    private static int potTotal;

    public static void addToPot(int bet){
        Pot.potTotal += bet;
    }

    public static int getPot(){
        return potTotal;
    }
}