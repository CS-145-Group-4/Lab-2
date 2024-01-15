// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// Source: Deitel/Deitel
// 01/17/24

import java.util.*;

public class DeckOfCardsTest {
    public static void main(String[] args) {
        DeckOfCards gameDeck = new DeckOfCards();
        gameDeck.shuffle();

        Card[] hand;

        hand = gameDeck.deal();
        Arrays.sort(hand);
        DeckOfCards.evaluate(hand);
    }
}
