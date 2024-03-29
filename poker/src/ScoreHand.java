import java.util.ArrayList;

public class ScoreHand extends Hand implements ValidCards{
    private int cardsPerRank[] = new int[13];
    private int cardsPerSuit[] = new int[4];
    protected int highCardPos;
    protected int highPairPos;
    protected int threeOfKindPos;
    protected int fourOfKindPos;
    private int score;

    private StringBuilder winnerHand = new StringBuilder();

    private ArrayList<Card> cards;
    private Hand player;
    public pokerHands handRank;

    ScoreHand(){}

    ScoreHand(Hand newHand){
        this.player = newHand;
        this.setPlayer(newHand.getPlayer());
        this.cards = newHand.getCards();
        countRankAndSuit();
        findScore();
    }

    public void findScore(){
        if(straightFlush()){
            for (Card card: this.cards){
                this.winnerHand.append(card.toString() + " ");
            }
        }
        else if(fourOfKind()){
            this.winnerHand.append(this.ranks[this.threeOfKindPos] + "s");
        }
        else if(fullHouse()){
            this.winnerHand.append(this.ranks[this.threeOfKindPos] + " over " + this.ranks[this.highPairPos]);
        }
        else if(flush()){
            for (Card card: this.cards){
                this.winnerHand.append(card.toString() + " ");
            }
        }
        else if(straight()){
            this.winnerHand.append("With high card " + this.ranks[this.highCard()]);
        }
        else if(threeOfKind()){
            this.winnerHand.append(this.ranks[this.threeOfKindPos] + "s");
        }
        else if(twoPair()){
            this.winnerHand.append(this.ranks[this.highPairPos] + " and " + this.getNextPair());
        }
        else if(pair()){
            this.winnerHand.append(this.ranks[this.highPairPos] + "s");
        }
        else{
            this.handRank = pokerHands.HIGH_CARD;
            this.score = 1;
            this.winnerHand.append(this.ranks[this.highCard()]);
        }
    }

    public int getScore(){
        return this.score;
    }

    public StringBuilder getWinnerHand() {
        return this.winnerHand;
    }

    public void countRankAndSuit(){
        for(Card card: this.cards){
            for(int i = 0; i < this.ranks.length; ++i){
                if(card.getRank() == this.ranks[i]){
                    this.cardsPerRank[i]++;
                    break;
                }
            }

            for (int j = 0; j < suits.length; ++j){
                if(card.getSuit() == this.suits[j]){
                    this.cardsPerSuit[j]++;
                }
            }
        }
    }

    public int highCard(){
        for(int i = this.ranks.length-1; i >= 0; --i){
            if(this.cardsPerRank[i] > 0){
                return this.highCardPos = i;
            }
        }
        return 0;
    }

    public int nextHighCard(){

        for(int i = this.highCardPos-1; i >= 0; --i){
            if(this.cardsPerRank[i] > 0){
                return this.highCardPos = i;
            }
        }
        return this.highCardPos;
    }

    public boolean pair() {
        for (int i = 0; i < this.ranks.length; ++i) {
           if (this.cardsPerRank[i] == 2) {
               this.highPairPos = i;
               this.handRank = pokerHands.PAIR;
               this.score = 3;
               return true;
            }
        }
        return false;
    }

    public boolean twoPair(){
        int pairCounter = 0;
        for (int i = 0; i < this.ranks.length; ++i) {
            if (this.cardsPerRank[i] == 2) {
                this.highPairPos = i;
                ++pairCounter;
            }
            if (pairCounter == 2){
                this.handRank = pokerHands.TWO_PAIRS;
                this.score = 5;
                return true;
            }
        }
        return false;
    }

    public char getNextPair(){
        for (int i = this.highPairPos-1; i >= 0; --i) {
            if (this.cardsPerRank[i] == 2){
                this.highPairPos = i;
                return this.ranks[i];
            }
        }
        return '0';
    }

    public boolean threeOfKind(){
        for (int i = 0; i < this.ranks.length; ++i) {
            if (this.cardsPerRank[i] == 3) {
                this.threeOfKindPos = i;
                this.handRank = pokerHands.THREE_OF_A_KIND;
                this.score = 7;
                return true;
            }
        }
        return false;
    }

    public boolean straight() {
        this.highCardPos = this.highCard();
        for (int i = this.highCardPos; i >= this.highCardPos - 4; --i) {
            if (this.cardsPerRank[i] != 1) {
                return false;
            }
        }
        this.handRank = pokerHands.STRAIGHT;
        this.score = 9;
        return true;
    }

    public boolean flush(){
        for (int i = 0; i < this.suits.length; ++i) {
            if (this.cardsPerSuit[i] == 5) {
                this.handRank = pokerHands.FLUSH;
                this.score = 11;
                return true;
            }
        }
        return false;
    }

    public boolean fullHouse(){
        if(threeOfKind() && pair()){
            this.handRank = pokerHands.FULL_HOUSE;
            this.score = 13;
            return true;
        }
        return false;
    }

    public boolean fourOfKind(){
        for (int i = 0; i < this.ranks.length; ++i) {
            if (this.cardsPerRank[i] == 4) {
                this.fourOfKindPos = i;
                this.handRank = pokerHands.FOUR_OF_A_KIND;
                this.score = 15;
                return true;
            }
        }
        return false;
    }

    public boolean straightFlush(){
        if(flush() && straight()) {
            this.handRank = pokerHands.STRAIGHT_FLUSH;
            this.score = 17;
            return true;
        }
        return false;
    }
}