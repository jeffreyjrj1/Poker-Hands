public class Card {
    private char suit;
    private char rank;


    Card(char rank, char suit){
        this.rank = rank;
        this.suit = suit;
    }

    public char getRank(){
        return this.rank;
    }

    public char getSuit(){
        return this.suit;
    }

    @Override
    public String toString() {
        return "" + this.rank + this.suit;
    }
}
