public enum pokerHands {
    HIGH_CARD("high card"),
    PAIR("pair"),
    TWO_PAIRS("two pairs"),
    THREE_OF_A_KIND("three of a kind"),
    STRAIGHT("straight"),
    FLUSH("flush"),
    FULL_HOUSE("full house"),
    FOUR_OF_A_KIND("four of a kind"),
    STRAIGHT_FLUSH("straight flush");

    public String type;

    pokerHands(String handRank){
        this.type = handRank;
    }

    public String getHandRank(){
        return this.type;
    }
}
