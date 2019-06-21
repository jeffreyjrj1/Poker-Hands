public class CompareHands {
    private ScoreHand blackScore, whiteScore;
    private BreakTies tieBreaker;

    CompareHands(Hand black, Hand white){
        this.blackScore = new ScoreHand(black);
        this.whiteScore = new ScoreHand(white);


        if(this.blackScore.getScore() > this.whiteScore.getScore()){
            printWinner(blackScore);
        }
        else if (this.blackScore.getScore() < this.whiteScore.getScore()){
            printWinner(whiteScore);
        }
        else{
            tieBreaker = new BreakTies(blackScore,whiteScore);
            printWinner(tieBreaker.compare());
        }


    }

    public void printWinner(ScoreHand winner){
        System.out.println( winner.getPlayer()+ " wins with " + winner.handRank.getHandRank()  + ": " + winner.getWinnerHand() );
    }

    public ScoreHand winningHand(ScoreHand winner){
        return winner;
    }

}
