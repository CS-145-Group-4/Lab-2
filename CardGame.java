// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// 01/17/24

import java.util.*;

//This program will run a poker card game between a player and a dealer.
public class CardGame {
    public static int PLAYER_WINS = 0;
    public static int DEALER_WINS = 0;
    public static int TIES = 0;
    //This method will create a scanner for input, introduce the game, and run the game.
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("This is a console based poker game");
        System.out.println();
        game(console);
    }

    //this method runs the poker game, looping until player says they don't want to play anymore into the console.
    //creates a new hand for both the dealer and player, outputs the players hand, and tells what they drew,
    //and if their hand beat the dealers hand.
    //at the end of each hand, shows statistics
    public static void game(Scanner console) {
        int keepPlaying = 3;
        while (keepPlaying != 0) {
            DeckOfCards gameDeck = new DeckOfCards();
            gameDeck.shuffle();
            int handSize = 5;

            //deal hands
            Card[] dealerHand = gameDeck.dealHand(handSize);
            Arrays.sort(dealerHand);
            Card[] playerHand = gameDeck.dealHand(handSize);

            Arrays.sort(playerHand);
            System.out.println("Player Hand:");
            for (Card card : playerHand) {
                System.out.println(card);
            }
            System.out.println();

// can also comment this out later but would like to see the dealer hand
            for (Card card : dealerHand) {
                System.out.println(card);
            }

            winMessage(dealerHand,playerHand);
            //Ask player if they would like to play again using while statement for proper input
            System.out.println("Would you like to play again? (Y/N)");
            response = console.next();
            while (!response.equalsIgnoreCase("y")){
                System.out.println("Invalid input -- Try again");
                response = console.next().substring(0,1);
            }
        }
    }

    public static void winMessage(Card[] dealerHand,Card[] playerHand){
        int dealerHandScore = DeckOfCards.evaluateHand(dealerHand);
        int playerHandScore = DeckOfCards.evaluateHand(playerHand);
        if (dealerHandScore < playerHandScore) {
            System.out.println("Dealer Wins");
            DEALER_WINS++;
        } else if (playerHandScore < dealerHandScore) {
            System.out.println("Player Wins");
            PLAYER_WINS++;
        } else if (playerHandScore == 6 || playerHandScore == 10) {
            if (DeckOfCards.highCard(playerHand) == DeckOfCards.highCard(dealerHand)) {
                System.out.println("It's a tie!");
                TIES++;
            } else if (DeckOfCards.highCard(playerHand) > DeckOfCards.highCard(dealerHand)) {
                System.out.println("Player Wins");
                PLAYER_WINS++;
            } else {
                System.out.println("Dealer Wins");
                DEALER_WINS++;
            }
        } // todo matching scores of 1,2,3,4,5,7,8,9

            //Game results
            System.out.println("Game Results");
            System.out.printf("\t%-13s = %d%n", "Dealer Wins", DEALER_WINS);
            System.out.printf("\t%-13s = %d%n", "Player Wins", PLAYER_WINS);
            System.out.printf("\t%-13s = %d%n", "Total Ties", TIES);
        }


    //calculate hands score
    public static int evaluateHand(Card[] hand) {
        if (straight(hand) == 1 && flushCheck(hand) ==1) {
            return 1; //straight flush
        } else if (sameCheck(hand) == 4) {
            return 2; // 4 of a kind
        } else if (sameCheck(hand) == 5) {
            return 3; // full house
        } else if (flushCheck(hand) == 1) {
            return 4; // flush
        } else if (straight(hand) == 1) {
            return 5; // straight
        } else if (sameCheck(hand) == 3) {
            return 6; // 3 of a kind
        } else if (sameCheck(hand) == 2) {
            return 7; // 2 pair
        } else if (sameCheck(hand) == 1) {
            return 8; // 1 pair
        }
        return 9; //highCard
    }

    //tells player what their hand score is.
    public static String stateEvaluation(int score) {
        switch(score) {
            case 1: return "This hand has a straight flush";
            case 2: return "This hand has four of a kind";
            case 3: return "This hand has a full house";
            case 4: return "This hand has a flush";
            case 5: return "This hand has a straight";
            case 6: return "This hand has three of a kind";
            case 7: return "This hand has two pairs";
            case 8: return "This hand has a pair";
            default: return "This hand only has a high card";
        }
    }

    //Checks if hand has multiple of the same cards. uses booleans to for score check, and returns highest score first.
    public static int sameCheck(Card[] hand){
        boolean foundTwoPair = false;
        boolean foundFull = false;
        boolean foundFourOfKind = false;
        boolean foundThreeOfKind = false;
        boolean foundPair = false;

        for (int first = 0; first < hand.length - 4; first++) {
            for (int second = first+1; second < hand.length - 3; second++) {
                for (int third = second+1; third < hand.length - 2; third++) {
                    for (int fourth  = third +1; fourth < hand.length-1; fourth++) {
                        if (hand[first].getFaceValue() == hand[second].getFaceValue() &&
                                hand[first].getFaceValue() == hand[third].getFaceValue() &&
                                hand[first].getFaceValue() == hand[fourth].getFaceValue()) {
                            foundFourOfKind = true;
                        }
                        if (hand[first].getFaceValue() == hand[second].getFaceValue() &&
                                hand[first].getFaceValue() == hand[third].getFaceValue()) {
                            foundThreeOfKind = true;
                        }
                        boolean highPair = hand[fourth].getFaceValue() == hand[fourth + 1].getFaceValue();
                        if (foundThreeOfKind && highPair) {
                            foundFull = true;
                        }
                        if (hand[first].getFaceValue() == hand[second].getFaceValue()) {
                            foundPair = true;
                        }
                        if (!foundFourOfKind && !foundThreeOfKind && foundPair && (hand[third].getFaceValue() == hand[fourth].getFaceValue()) ||
                                (highPair)){
                            foundTwoPair = true;
                        }
                    }
                }
            }
        }
        if (foundFull) {
            return 5;
        } else if (foundFourOfKind) {
            return 4;
        } else if (foundThreeOfKind){
            return 3;
        } else if (foundTwoPair) {
            return 2;
        } else if (foundPair)
            return 1;
        else {
            return 0;
        }
    }

    //Checks if hand has a straight
    public static int straight(Card[] hand) {
        int linearCount = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].getFaceValue() == 1 && hand[i + 1].getFaceValue() == 13) {
                linearCount++;
            } else if (hand[i].getFaceValue() == hand[i + 1].getFaceValue() - 1) {
                linearCount++;
            }
            if (hand[i].getFaceValue() == hand[i + 1].getFaceValue() - 1) {
                linearCount++;
            }
        } if (linearCount == 4) {
            return 1;
        } else return 0;
    }

    //checks if hand contains a flush
    public static int flushCheck(Card[] hand) {
        int suitCount = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].getSuit().equals(hand[i + 1].getSuit())) {
                suitCount++;
            }
        }
        if (suitCount == 4) {
            return 1;
        } else {
            return 0;
        }
    }

    //Finds hands highest card
    public static int highCard(Card[] hand) {
        int highCard = 0;
        for (int count = 0; count < 5; count++) {
            if (hand[count].getFaceValue() > highCard) {
                highCard = hand[count].getFaceValue();
            }
        }
        return highCard;
    }
}
