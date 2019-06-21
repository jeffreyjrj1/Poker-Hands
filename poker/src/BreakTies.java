public class BreakTies extends ScoreHand{
    private ScoreHand black, white, winner;


    BreakTies(ScoreHand Black, ScoreHand White){
        this.black = Black;
        this.white = White;
    }

    public ScoreHand compare(){
        switch(this.black.getScore()){
            case 1: return highCardTie();
            case 3: return pairTie();
            case 5: return twoPairTie();
            case 7: return threeOfKindTie();
            case 9: return straightTie();
            case 11: return flushTie();
            case 13: return fullHouseTie();
            case 15: return fourOfKindTie();
            case 17: return straightFlushTie();
            default: System.out.println("failed to compare hands.");

        }
        return this.black;
    }


    public ScoreHand highCardTie(){
        while(this.black.highCardPos == this.white.highCardPos){
            this.black.highPairPos = this.black.nextHighCard();
            this.white.highPairPos = this.white.nextHighCard();
        }

        return this.winner = (this.black.highCardPos > this.white.highCardPos) ? this.black : this.white;
    }

    public ScoreHand pairTie(){
        if(this.black.highPairPos == this.white.highPairPos){
            return highCardTie();
        }

        return this.winner = (this.black.highPairPos > this.white.highPairPos) ? this.black : this.white;
    }

    public ScoreHand twoPairTie(){
        if(this.black.highPairPos == this.white.highPairPos){
            this.black.getNextPair();
            this.white.getNextPair();
        }

        if(this.black.highPairPos == this.white.highPairPos){
            return highCardTie();
        }

        return this.winner = (this.black.highPairPos > this.white.highPairPos) ? this.black : this.white;
    }

    public ScoreHand threeOfKindTie(){
        return this.winner = (this.black.threeOfKindPos > this.white.threeOfKindPos) ? this.black : this.white;
    }

    public ScoreHand straightTie(){
        return this.winner = (this.black.highCard() > this.white.highCard()) ? this.black : this.white;
    }

    public ScoreHand flushTie(){
        return highCardTie();
    }

    public ScoreHand fullHouseTie(){
        return threeOfKindTie();
    }

    public ScoreHand fourOfKindTie(){
        return this.winner = (this.black.fourOfKindPos > this.white.fourOfKindPos) ? this.black : this.white;
    }

    public ScoreHand straightFlushTie(){
        return this.winner = (this.black.highCardPos > this.white.highCardPos) ? this.black : this.white;
    }
}

