import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception{



        //build new deck
        Deck deck = new Deck();

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
