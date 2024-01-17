// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// 01/17/24

import java.util.*;

public class DeckOfCards {
    private static final int NUMBER_OF_CARDS = 52;
    private final Card[] deck; // Initializes array of card objects
    private int currentCard; // Index of next card to be dealt


    public DeckOfCards() {
        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five",
                "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        deck = new Card[NUMBER_OF_CARDS];
        currentCard = 0;

        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    public void shuffle() {
        currentCard = 0;
        Random randomNumber = new Random();

        for (int first = 0; first < deck.length; first++) {
            int second = randomNumber.nextInt(NUMBER_OF_CARDS);

            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    //deals a hand and increments deck position
    public Card[] dealHand(int handSize) {
        Card[] hand = new Card[handSize];
        for (int deckPosition = 0; deckPosition < handSize; deckPosition++) {
            hand[deckPosition] = deck[currentCard];
            currentCard++;
        }
        return hand;
    }

    //deals a single card and increments CurrentCard
    public Card dealCard() {
        if (currentCard < deck.length) {
            Card card = deck[currentCard];
            currentCard++;
            return card;
        } else {
            return null;
        }
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
