public class StackOfCards {
    Card[] stack;

    public StackOfCards(boolean isDeck) {
        if(isDeck){
            String[] suits = {"spades", "hearts", "diamonds", "clubs"};
            Card[] deck = new Card[52];
            int count = 0;
            for (String suit : suits) {
                for (int i = 0; i < 13; i++) {
                    deck[count] = new Card(i, suit);
                    count++;
                }
            }
            this.stack = deck;
        } else{
            this.stack = new Card[0];
        }
    }

    public Card[] getStack() {
        return stack;
    }

    public void setStack(Card[] stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder();
        if (stack.length > 0) {
            for (Card card : stack) {
                toPrint.append(card.toString()).append(" ");
            }
        } else {
            toPrint.append("(empty)");
        }
        return toPrint.toString();
    }

    public void shuffle(){
        for (int i = 0; i < 300; i++) {
            int card1 = (int) (Math.random() * stack.length);
            int card2 = (int) (Math.random() * stack.length);
            Card temp;
            temp = stack[card1];
            stack[card1] = stack[card2];
            stack[card2] = temp;
        }
    }

    public void sort(String method) {
        if (method.equals("position")) {
            for (int i = 1; i < stack.length; i++) {
                Card current = stack[i];
                int j = i - 1;
                while (j >= 0 && current.getCardDeckPos() < stack[j].getCardDeckPos()) {
                    stack[j + 1] = stack[j];
                    j--;
                }
                stack[j + 1] = current;
            }
        } else if (method.equals("value")) {
            for (int i = 1; i < stack.length; i++) {
                Card current = stack[i];
                int j = i - 1;
                while (j >= 0 && current.getCardValue() < stack[j].getCardValue()) {
                    stack[j + 1] = stack[j];
                    j--;
                }
                stack[j + 1] = current;
            }
        }
    }

    public int calculate(){
        int total = 0;
        int highAces = 0;

        for (Card card : stack) {
            if (card.getCardSeqNum() == 0) {
                highAces++;
            }
            total += card.getCardValue();
            while (total > 21 && highAces > 0) {
                total -= 10;
                highAces--;
            }
        }
        return total;
    }
}
