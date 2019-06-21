import java.util.ArrayList;

public class ScoreHand extends Hand{
    private final char ranks[] = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    private final char suits[] = {'C', 'D', 'H', 'S'};
    private int cardsPerRank[] = new int[13];
    private int cardsPerSuit[] = new int[4];
    protected int highCardPos;
    protected int highPairPos;
    protected int threeOfKindPos;
    protected int fourOfKindPos;
    private int score;

    private StringBuilder winnerHand = new StringBuilder();
    public pokerHands handRank;

    private ArrayList<Card> cards;
    private Hand player;

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
            this.handRank = pokerHands.STRAIGHT_FLUSH;
            this.score = 17;
            for (Card card: this.cards){
                this.winnerHand.append(card.toString() + " ");
            }
        }
        else if(fourOfKind()){
            this.handRank = pokerHands.FOUR_OF_A_KIND;
            this.score = 15;
            this.winnerHand.append(this.ranks[this.threeOfKindPos] + "s");
        }
        else if(fullHouse()){
            this.handRank = pokerHands.FULL_HOUSE;
            this.score = 13;
            this.winnerHand.append(this.ranks[this.threeOfKindPos] + " over " + this.getPair());
        }
        else if(flush()){
            this.handRank = pokerHands.FLUSH;
            this.score = 11;
            for (Card card: this.cards){
                this.winnerHand.append(card.toString() + " ");
            }
        }
        else if(straight()){
            this.handRank = pokerHands.STRAIGHT;
            this.score = 9;
            this.winnerHand.append("With high card " + this.ranks[this.highCard()]);
        }
        else if(threeOfKind()){
            this.handRank = pokerHands.THREE_OF_A_KIND;
            this.score = 7;
            this.winnerHand.append(this.ranks[this.threeOfKindPos] + "s");
        }
        else if(twoPair()){
            this.handRank = pokerHands.TWO_PAIRS;
            this.score = 5;
            this.winnerHand.append(this.getPair() + " and " + this.getNextPair());
        }
        else if(pair()){
            this.handRank = pokerHands.PAIR;
            this.score = 3;
            this.winnerHand.append(this.getPair() + "s");
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
        int highCardPosition = 0;

        for(int i = this.ranks.length-1; i >= 0; --i){
            if(this.cardsPerRank[i] > 0){
                return this.highCardPos = i;
            }
        }
        return this.highCardPos = highCardPosition;
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
               return true;
            }
        }
        return false;
    }

    public char getPair(){
        for (int i = this.ranks.length-1; i >= 0 ; --i) {
            if (this.cardsPerRank[i] == 2) {
                this.highPairPos = i;
                return this.ranks[i];
            }
        }
        return '0';
    }

    public boolean twoPair(){
        int pairCounter = 0;
        for (int i = 0; i < this.ranks.length; ++i) {
            if (this.cardsPerRank[i] == 2) {
                ++pairCounter;
            }
            if (pairCounter == 2){
                return true;
            }
        }
        return false;
    }

    public char getNextPair(){
        for (int i = this.highPairPos-1; i > 0; --i) {
            if (this.cardsPerRank[i] == 2){
                this.highPairPos = i-1;
                return this.ranks[i];
            }
        }
        return '0';
    }



    public boolean threeOfKind(){
        for (int i = 0; i < this.ranks.length; ++i) {
            if (this.cardsPerRank[i] == 3) {
                this.threeOfKindPos = i;
                return true;
            }
        }
        return false;
    }




    public boolean straight(){
        int consecutiveValueCards = 0;
        this.highPairPos = this.highCard();
        for (int i = 1; i < this.ranks.length; ++i) {
            if (consecutiveValueCards == 0 && this.cardsPerRank[i] == 1){
                ++consecutiveValueCards;
            }
            else if((this.cardsPerRank[i-1] == 1 && this.cardsPerRank[i] == 1) && i!=0){
                ++consecutiveValueCards;
            }
        }

        if(consecutiveValueCards == 5){
            return true;
        }
            return false;
    }

    public boolean flush(){
        for (int i = 0; i < this.suits.length; ++i) {
            if (this.cardsPerSuit[i] == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean fullHouse(){
        if(threeOfKind() && pair()){
            return true;
        }
        return false;
    }

    public boolean fourOfKind(){
        for (int i = 0; i < this.ranks.length; ++i) {
            if (this.cardsPerRank[i] == 4) {
                this.fourOfKindPos = i;
                return true;
            }
        }
        return false;
    }

    public boolean straightFlush(){
        if(flush() && straight()) {
            return true;
        }
        return false;
    }
}