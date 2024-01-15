// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// 01/17/24

import java.util.*;

public class DeckOfCards {
    private static final int NUMBER_OF_CARDS = 52;
    private Card[] deck; // Initializes array of card objects
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

    public Card[] dealHand(int handSize) {
        Card[] hand = new Card[handSize];
        for (int deckPosition = 0; deckPosition < handSize; deckPosition++) {
            hand[deckPosition] = deck[deckPosition];
            currentCard++;
        }
        return hand;
    }

    public Card dealCard() {
        if (currentCard < deck.length) {
            return deck[currentCard++];
        } else {
            return null;
        }
    }

    public static int evaluateHand(Card[] hand) {
        if (royalFlush(hand) == 1) {
            return 1;
        } else if (straightFlush(hand) == 1) {
            return 2;
        } else if (fourOfaKind(hand) == 1) {
            return 3;
        } else if (fullHouse(hand) == 1) {
            return 4;
        } else if (flush(hand) == 1) {
            return 5;
        } else if (straight(hand) == 1) {
            return 6;
        } else if (triple(hand) == 1) {
            return 7;
        } else if (twoPairs(hand) == 1) {
            return 8;
        } else if (pair(hand) == 1) {
            return 9;
        } else {
            return 10 + highCard(hand);
        }
    }
    //give player information
    public static String stateEvaluation(int score,Card[] hand) {
        if (score == 1) {
            return "This hand has a royal flush";
        } else if (score == 2) {
            return "This hand has a straight flush";
        } else if (score == 3) {
            return "This hand has four of a kind";
        } else if (score == 4) {
            return "This hand has a full house";
        } else if (score == 5) {
            return "This hand has a flush";
        } else if (score == 6) {
            return "This hand has a straight";
        } else if (score == 7) {
            return "This hand has a triple";
        } else if (score == 8) {
            return "This hand has two pairs";
        } else if (score == 9) {
            return "This hand has a pair";
        } else {
            return "This hand's highest card is " + highCard(hand);
        }
    }
    //Checks if hand has a royalFlush
    public static int royalFlush(Card[] hand) {
        if() {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a straightFlush
    public static int straightFlush(Card[] hand){
        if() {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a fourOfaKind
    public static int fourOfaKind(Card[] hand){
        if() {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a fullHouse
    public static int fullHouse(Card[] hand){
        if() {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a flush
    public static int flush(Card[] hand){
        for (int i = 0; i < hand.length; i++) {
            if (hand[i].getSuit().equals(hand[i + 1].getSuit())) {
                return 1;
            }
        }
        return 0;
    }

    //Checks if hand has a straight
    public static int straight(Card[] hand){
        if() {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a triple
    public static int triple(Card[] hand){
        if() {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a twoPairs
    public static int twoPairs(Card[] hand){
        if() {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a pair
    public static int pair(Card[] hand) {
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].getFaceValue() == hand[i+1].getFaceValue()){
                return 1;
            }
        }
        return 0;
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
