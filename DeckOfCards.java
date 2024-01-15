// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// Source: Deitel/Deitel
// 01/17/24

import java.security.SecureRandom;
import java.util.*;

public class DeckOfCards {
    private static final int NUMBER_OF_CARDS = 52;
    private final int HAND_SIZE = 5;
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

    public Card[] deal() {
        Card[] hand = new Card[HAND_SIZE];
        for (int deckPosition = 0; deckPosition < 5; deckPosition++) {
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

    public static void evaluate(Card[] hand) {
        if (royalFlush(hand) == 1) {
            System.out.println("This hand has a royal flush");
        } else if (straightFlush(hand) == 1) {
            System.out.println("This hand has a straight flush");
        } else if (fourOfaKind(hand) == 1) {
            System.out.println("This hand has four of a kind");
        } else if (fullHouse(hand) == 1) {
            System.out.println("This hand has a full house");
        } else if (flush(hand) == 1) {
            System.out.println("This hand has a flush");
        } else if (straight(hand) == 1) {
            System.out.println("This hand has a straight");
        } else if (triple(hand) == 1) {
            System.out.println("This hand has a triple");
        } else if (twoPairs(hand) == 1) {
            System.out.println("This hand has two pairs");
        } else if (pair(hand) == 1) {
            System.out.println("This hand has a pair");
        } else {
            System.out.println("This hand's highest card is " + highCard(hand));
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
        for (int i = 0; i < 4; i++) {
            if (hand[i].getSuit() == hand[i + 1].getSuit()) {
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
    public static int pair(Card[] hand){
        if() {
            return 1;
        }
        return 0;
    }

    //Finds hands highest card
    public static int highCard(Card[] hand) {
        int highCard = 0;
        for (int count = 0; count < 5; count++) {
            if (hand[count].getFace() > highCard) {
                highCard = hand[count].getFace();
            }
        }
        return highCard;
    }

}
