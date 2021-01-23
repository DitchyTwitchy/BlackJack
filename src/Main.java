import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            printTopMenu();
            int menuChoice = readInt();
            if (menuChoice == 0) {
                System.out.println("============================");
                System.out.println("Bye! See you later!");
                break;
            } else if (menuChoice == 1) {
                System.out.println("============================");
                playGame();
            } else if (menuChoice == 2) {
                StackOfCards deck = new StackOfCards(true);
                StackOfCards testHand = new StackOfCards(false);
                while (true) {
                    printTestMenu();
                    int testMenuChoice = readInt();
                    if (testMenuChoice == 0) {
                        break;
                    } else if (testMenuChoice == 1) {
                        System.out.println(deck.toString());
                    } else if (testMenuChoice == 2) {
                        deck.shuffle();
                        System.out.println("Deck shuffled!");
                    } else if (testMenuChoice == 3) {
                        deck.sort("position");
                        System.out.println("Deck sorted!");
                    } else if (testMenuChoice == 4) {
                        System.out.print("Your hand: ");
                        System.out.println(testHand.toString());
                        System.out.println();
                    } else if (testMenuChoice == 5) {
                        drawCard(deck, testHand);
                        System.out.println("Card drawn!");
                    } else if (testMenuChoice == 6) {
                        testHand.sort("value");
                        System.out.println("Hand sorted!");
                    } else if (testMenuChoice == 7) {
                        System.out.println("Hand value: " + testHand.calculate());
                    } else if (testMenuChoice == 8) {
                        discardHand(deck, testHand);
                        System.out.println("Hand discarded!");
                    } else {
                        System.out.println("Incorrect input! Please enter correct menu item.");
                    }
                }
            } else {
                System.out.println("Incorrect input! Please enter correct menu item.");
            }
        }
    }

    public static int readInt() {
        int menuItem;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Your input: ");
                menuItem = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Incorrect input! Please enter menu item as an integer.");
            }
        }
        return menuItem;
    }

    public static void printTopMenu() {
        System.out.println("=========== Menu ===========");
        System.out.println("1 - Play a game of BlackJack");
        System.out.println("2 - Test features");
        System.out.println("0 - Exit");
    }

    public static void printTestMenu() {
        System.out.println("======= Test Features =======");
        System.out.println("1 - Print deck");
        System.out.println("2 - Shuffle deck");
        System.out.println("3 - Sort deck");
        System.out.println("4 - Print hand");
        System.out.println("5 - Draw card");
        System.out.println("6 - Sort hand");
        System.out.println("7 - Calculate hand");
        System.out.println("8 - Discard hand");
        System.out.println("0 - Exit to top menu");
    }

    public static void drawCard(StackOfCards deck, StackOfCards hand) {
        Card[] newDeck = new Card[deck.getStack().length - 1];
        Card[] newHand;
        System.arraycopy(deck.getStack(), 0, newDeck, 0, newDeck.length);
        newHand = new Card[hand.getStack().length + 1];
        System.arraycopy(hand.getStack(), 0, newHand, 0, hand.getStack().length);
        newHand[newHand.length - 1] = deck.getStack()[deck.getStack().length - 1];

        StackOfCards newHandStack = new StackOfCards(false);
        newHandStack.setStack(newHand);
        newHandStack.sort("value");

        deck.setStack(newDeck);
        hand.setStack(newHandStack.getStack());
    }

    public static void discardHand(StackOfCards deck, StackOfCards hand) {
        Card[] newDeck;
        newDeck = new Card[deck.getStack().length + hand.getStack().length];
        System.arraycopy(deck.getStack(), 0, newDeck, 0, deck.getStack().length);
        System.arraycopy(hand.getStack(), 0, newDeck, deck.getStack().length, hand.getStack().length);
        hand.setStack(new Card[0]);
        deck.setStack(newDeck);
    }

    public static void playGame() {
        boolean play = true;
        int stack = 50;
        int bet = 10;

        while (play) {
            StackOfCards deck = new StackOfCards(true);
            deck.sort("position");
            StackOfCards playerHand = new StackOfCards(false);
            StackOfCards dealerHand = new StackOfCards(false);
            drawCard(deck, dealerHand);
            drawCard(deck, playerHand);
            drawCard(deck, playerHand);

            System.out.println("Cards are dealt! You have " + stack + "$ left. Bet is " + bet + "$.");
            printHands(dealerHand, playerHand);

            boolean dealerDraws = true;
            while (true) {
                System.out.println("Hit or stand? (1 - Hit, 0 - Stand)");
                int menuChoice = readInt();
                if (menuChoice == 0) {
                    break;
                } else if (menuChoice == 1) {
                    drawCard(deck, playerHand);
                    if (playerHand.calculate() > 21) {
                        stack -= bet;
                        System.out.println("You are burst! Dealer wins! (Your have " + stack + "$ left)");
                        dealerDraws = false;
                        break;
                    }
                } else {
                    System.out.println("Incorrect input! Please enter correct menu item.");
                }
                System.out.println("-----------------------------");
                printHands(dealerHand, playerHand);
            }
            if (dealerDraws) {
                while (true) {
                    drawCard(deck, dealerHand);
                    if (dealerHand.calculate() > 21) {
                        printHands(dealerHand, playerHand);
                        stack += bet;
                        System.out.println("Dealer is burst! You win! (You have " + stack + "$ left)");
                        break;
                    } else if (dealerHand.calculate() > 17 || dealerHand.calculate() >= playerHand.calculate()) {
                        printHands(dealerHand, playerHand);
                        if (playerHand.calculate() > dealerHand.calculate()) {
                            stack += bet;
                            System.out.println("You win! (You have " + stack + "$ left)");
                        } else {
                            stack -= bet;
                            System.out.println("You loose! (You have " + stack + "$ left)");
                        }
                        break;
                    } else {
                        drawCard(deck, dealerHand);
                    }
                }
            }
            if (stack > bet) {
                while (true) {
                    System.out.println("Do you wish to play again? (1 - Yes, 0 - No)");
                    int menuChoice = readInt();
                    if (menuChoice == 0) {
                        System.out.println("Bye! Come again!");
                        play = false;
                        break;
                    } else if (menuChoice == 1) {
                        System.out.println("Here we go!");
                        System.out.println("============================");
                        break;
                    } else {
                        System.out.println("Incorrect input! Please enter correct menu item.");
                    }
                }
            } else {
                System.out.println("You run out of money! Come again later!");
                play = false;
            }
        }
    }

    public static void printHands(StackOfCards dealerHand, StackOfCards playerHand) {
        System.out.print("Dealer cards: ");
        System.out.print(dealerHand.toString());
        System.out.print(". Total: " + dealerHand.calculate() + "\n");

        System.out.print("Player cards: ");
        System.out.print(playerHand.toString());
        System.out.print(". Total: " + playerHand.calculate() + "\n");
    }
}