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

    //deals
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
        if (royalFlush(hand) == 1) {
            return 1;
        } else if (straightFlush(hand) == 1) {
            return 2;
        } else if (kindCount(hand) == 3) {
            return 3;
        // } else if (fullHouse(hand) == 1) {
        //     return 4;
        } else if (kindCount(hand) == 2) {
            return 5;
        } else if (straight(hand) == 1) {
            return 6;
        } else if (kindCount(hand) == 1) {
            return 7;
        } else if (pair(hand) == 2) {
            return 8;
        } else if (pair(hand) == 1) {
            return 9;
        } else {
            return 10 + highCard(hand);
        }
    }
    //gives player information
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
        if(highCard(hand) == 1 && straightFlush(hand) == 1 ) {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a straightFlush
    public static int straightFlush(Card[] hand){
        if(straight(hand) == 1 && kindCount(hand) == 4) {
            return 1;
        }
        return 0;
    }

    public int isFlush(Card[] hand) { // all cards of same suit, do not have to be consecutive
        if ((kindCount(hand) == 3) && (straightFlush(hand) == 0)) {
            return 1;
        }
        return 0;
    }

    //Checks if hand has a fullHouse
    // public static int fullHouse(Card[] hand){
    //     if() {
    //         return 1;
    //     }
    //     return 0;
    // }

    //Checks if hand has a straight
    public static int straight(Card[] hand) {
        int linearCount = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if ((hand[i].getFaceValue() + 1) == hand[i + 1].getFaceValue()) {
                linearCount++;
            }
        } if (linearCount == 4) {
            return 1;
        } else return 0;
    }

    //checks how many cards in a hand have the same suit
    //kindcount 2 = 3ofakind, 3  = fourofakind, 4 = flush
    public static int kindCount(Card[] hand) {
        int kindCount = 0;
        for (int i = 0; i < hand.length - 2; i++) {
            if ((hand[i].getSuit().equals(hand[i + 1].getSuit())) && (hand[i].getSuit().equals(hand[i + 2].getSuit()))) {
                kindCount++;
            }
        }
        return kindCount;
    }
    
    // checks if hand has a three of a kind
    public static int threeOfAKind(Card[] hand) {
        int count = 1;
        // checks equivalence between current card starting at index 1
        // against all cards in the indices below it
        // hopefully
        for (int x = 1; x < hand.length; x++) {
            for (int y = 0; y < x; y++) {
                if(hand[x].getFace().equals(hand[y].getFace())) {
                    count++;
                }
            }
            if (count == 3) {
                return 1;
            }
        }
        return 0;
    }

    // I might mess with this since for a full house we'll have to differentiate
    // the face values for the three of a kind and the pair

    // Checks if hand has one or two pairs
    public static int pair(Card[] hand) {
        int pairCounter = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].getFaceValue() == hand[i + 1].getFaceValue()) {
                pairCounter++;
                // pair counter will increment if three of a kind, hand[i + 2] equals the previous cards
                // there's not a great way to keep track of the face value of the card that is in a pair
                // I have no idea how we track that for full house or two separate pairs since we have to compare face value
                // might write comparison method in Card class
                // could write set method in this file for each type
            }
        }
        return pairCounter;
        // I would not enjoy having to write separate methods for two pairs and four of a kind
        // but kind of at a loss on how we'd do it
        // wondering about enums since they were introduced, but I really don't understand them well
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
