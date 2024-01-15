// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// 01/17/24

import java.util.*;

public class CardGame {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("This is a console based poker game");
        game(console);
    }

    public static void game(Scanner console) {
        String response = "y";
        while (!response.equals("n")) {
            DeckOfCards gameDeck = new DeckOfCards();
            gameDeck.shuffle();
            int handSize = 5;

            //deal hands
            Card[] dealerHand = gameDeck.dealHand(handSize);
            Arrays.sort(dealerHand);
            int dealerHandScore = DeckOfCards.evaluateHand(dealerHand);

            Card[] playerHand = gameDeck.dealHand(handSize);
            Arrays.sort(playerHand);
            int playerHandScore = DeckOfCards.evaluateHand(playerHand);
            System.out.println(DeckOfCards.stateEvaluation(playerHandScore, playerHand));
            System.out.println("Player Hand:");
            for (Card card : playerHand) {
                System.out.println(card);
            }

            //add if player wants to change card function
            //add if deal return null, reshuffle deck
            //ensure if reshuffle happens, players hands don't change. ie hand array is pointing to deck array position
            //instead of taking a copy of values and returning it.
            //Ensure player hand contents aren't being added back into deck,
            //IE deck size now smaller, card values in hands not reappearing in deck.

            //Compare dealer hand vs player hand result
            if (dealerHandScore > playerHandScore) {
                System.out.println("Dealer Wins");
            } else if (playerHandScore > dealerHandScore) {
                System.out.println("Player Wins");
            } else if (playerHandScore == 10) {
                if (DeckOfCards.highCard(playerHand) == DeckOfCards.highCard(dealerHand)) {
                    System.out.println("It's a tie!");
                } else {
                    System.out.print(DeckOfCards.highCard(playerHand) > DeckOfCards.highCard(dealerHand) ? "Player Wins" : "Dealer Wins");
                }
            }



            System.out.println("Would you like to play again? (Y/N)");
            while (!((response.equals("n") || (response.equals("y"))))) {
                System.out.println("Invalid input -- Try again");
                response = console.next();
            }
            response = console.next();
        }
    }
}
