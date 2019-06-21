import java.util.ArrayList;
import java.util.Arrays;

public class ParseHand {

    private String newHand;
    private final String ranks[] = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
    private final String suits[] = {"C", "D", "H", "S"};
    private ArrayList<Card> myCards = new ArrayList();

    public Hand addCards(String preParsedHand) {
        this.newHand = preParsedHand;

        for (int i = 0; i < newHand.length(); ++i) {
            if (this.newHand == " ") {
                continue;
            }
            if (!validateRank(newHand.charAt(i))) {
                throw new RuntimeException("invalid rank" + newHand.charAt(i));
            }
            if (!validateSuit(newHand.charAt(i + 1))) {
                throw new RuntimeException("invalid suit" + newHand.charAt(i + 1));
            }
            else {
                myCards.add(new Card(newHand.charAt(i), newHand.charAt(i + 1)));
                ++i;
            }
        }

        return new Hand(myCards);

    }

    public boolean validateRank(char rank) {
        for (int i = 0; i < ranks.length; ++i) {
            if (rank == ranks[i].charAt(0)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateSuit(char suit) {
        for (int i = 0; i < suits.length; ++i) {
            if (suit == suits[i].charAt(0)) {
                return true;
            }
        }
        return false;
    }
}
