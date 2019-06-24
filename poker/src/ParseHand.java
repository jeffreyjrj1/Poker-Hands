import java.util.ArrayList;
import java.util.Arrays;

public class ParseHand implements ValidCards{

    private String newHand;
    private ArrayList<Card> myCards = new ArrayList();

    public Hand addCards(String preParsedHand) {
        this.newHand = preParsedHand;

        for (int i = 0; i < newHand.length(); ++i) {
            if (this.newHand.charAt(i) == ' ') {
                continue;
            }
            if (!validateRank(this.newHand.charAt(i))) {
                throw new RuntimeException("invalid rank" + newHand.charAt(i));
            }
            if (!validateSuit(newHand.charAt(i + 1))) {
                throw new RuntimeException("invalid suit" + newHand.charAt(i + 1));
            }
            else {
                this.myCards.add(new Card(newHand.charAt(i), newHand.charAt(i + 1)));
                ++i;
            }
        }

        return new Hand(this.myCards);

    }

    public boolean validateRank(char rank) {
        for (int i = 0; i < ranks.length; ++i) {
            if (rank == ranks[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean validateSuit(char suit) {
        for (int i = 0; i < suits.length; ++i) {
            if (suit == suits[i]) {
                return true;
            }
        }
        return false;
    }
}
