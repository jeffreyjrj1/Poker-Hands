import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> myCards = new ArrayList<Card>();
    private String player;


    Hand(){}

    Hand(ArrayList<Card> cards){
        this.myCards = cards;
    }

    public void addCard(Card newCard){
        myCards.add(newCard);
    }

    public ArrayList<Card> getCards(){
        return this.myCards;
    }

    public void setPlayer(String player){
        this.player = player;
    }

    public String getPlayer(){
        return this.player;
    }

    public void printHands(){
        System.out.print(this.player + " cards: ");

        for(Card cards: myCards){
            System.out.print(cards.toString() + " ");
        }

        System.out.println();
    }
}
