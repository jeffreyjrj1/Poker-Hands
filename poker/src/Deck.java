import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck implements ValidCards {
    private ArrayList<Card> cards = new ArrayList<Card>();

    Deck(){
        constructDeck();
        shuffleDeck();
    }

    public void constructDeck(){
        for(int i = 0; i < this.suits.length; ++i){
            for(int j = 0; j < this.ranks.length; ++j){
                cards.add(new Card(this.ranks[j], this.suits[i]));
            }
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(this.cards);
    }

    public Hand givePlayerHand(String player){
        Hand newHand = new Hand();
        newHand.setPlayer(player);

        for(int i = 0; i < 5; ++i){
            newHand.addCard(cards.get(0));
            cards.remove(0);
        }

        return newHand;
    }
}
