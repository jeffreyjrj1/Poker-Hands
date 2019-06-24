import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception{

        //build new deck
        Deck deck = new Deck();

        //Build a hand manually for testing purposes;
//        ParseHand temp = new ParseHand();
//        Hand white = temp.addCards("4H 5H 6H 7H 8H");


        //create white and black hands
        Hand white = new Hand();
        Hand black = new Hand();

        //add cards to each hand
        black = deck.givePlayerHand("Black");
        white = deck.givePlayerHand("White");

        //display each players' hands'.
        black.printHands();
        white.printHands();//enter hands

        //determine a winner between the two hands.
        CompareHands winner = new CompareHands(black, white);

        return;
    }
}
