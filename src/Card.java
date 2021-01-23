public class Card {
    final int cardSeqNum;
    final int cardDeckPos;
    final String cardSuit;
    final int cardValue;
    final char cardSuitChar;

    public Card(int cardSeqNum, String cardSuit) {
        this.cardSeqNum = cardSeqNum;
        this.cardSuit = cardSuit;

        int cardValue;
        if (cardSeqNum == 0){
            cardValue = 11;
        } else if (cardSeqNum < 9) {
            cardValue = cardSeqNum + 1;
        } else {
            cardValue = 10;
        }
        this.cardValue = cardValue;

        switch (cardSuit) {
            case "spades" -> {
                this.cardSuitChar = '\u2660';
                this.cardDeckPos = cardSeqNum + 100;
            }
            case "hearts" -> {
                this.cardSuitChar = '\u2764';
                this.cardDeckPos = cardSeqNum + 200;
            }
            case "diamonds" -> {
                this.cardSuitChar = '\u2666';
                this.cardDeckPos = cardSeqNum + 300;
            }
            case "clubs" -> {
                this.cardSuitChar = '\u2663';
                this.cardDeckPos = cardSeqNum + 400;
            }
            default -> {
                this.cardSuitChar = '\u003F';
                this.cardDeckPos = cardSeqNum;
            }
        }
    }

    @Override
    public String toString() {
        String rank;
        switch (cardSeqNum) {
            case 0 -> rank = "A";
            case 1 -> rank = "2";
            case 2 -> rank = "3";
            case 3 -> rank = "4";
            case 4 -> rank = "5";
            case 5 -> rank = "6";
            case 6 -> rank = "7";
            case 7 -> rank = "8";
            case 8 -> rank = "9";
            case 9 -> rank = "10";
            case 10 -> rank = "J";
            case 11 -> rank = "Q";
            case 12 -> rank = "K";
            default -> rank = "???";
        }
        return rank + cardSuitChar;
    }

    public int getCardSeqNum() {
        return cardSeqNum;
    }

    public String getCardSuit() {
        return cardSuit;
    }

    public int getCardValue() {
        return cardValue;
    }

    public char getCardSuitChar() {
        return cardSuitChar;
    }

    public int getCardDeckPos() {
        return cardDeckPos;
    }
}
