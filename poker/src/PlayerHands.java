import java.util.ArrayList;

public class PlayerHands {

    private ArrayList<Hand> playerHands= new ArrayList<Hand>();

    public void addHand(Hand newHand){
        this.playerHands.add(newHand);
    }

    public void addHand(Deck myDeck, String player){
        playerHands.add(myDeck.givePlayerHand(player));
    }

    public void printHands(){
        for(Hand hands : this.playerHands){
            hands.printHands();
        }
    }
}
