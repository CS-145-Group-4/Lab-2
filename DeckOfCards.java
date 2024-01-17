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
}
