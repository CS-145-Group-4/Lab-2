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

            int dealerHandScore = DeckOfCards.evaluateHand(dealerHand);
            System.out.println(DeckOfCards.stateEvaluation(dealerHandScore));
            System.out.println("Dealer Hand:");
            for (Card card : dealerHand) {
                System.out.println(card);
            }
            System.out.println();

            int playerHandScore = DeckOfCards.evaluateHand(playerHand);
            System.out.println(DeckOfCards.stateEvaluation(playerHandScore));
            System.out.println("Player Hand:");
            for (Card card : playerHand) {
                System.out.println(card);
            }
            System.out.println();
            gameDeck.shuffle();
            winMessage(dealerHand,playerHand);
            keepPlaying = 3;
            //Ask player if they would like to play again using while statement for proper input
            System.out.println("Would you like to play a game? (Y/N)");
            while (keepPlaying != 1 && keepPlaying != 0 ) {
                String response = console.next().substring(0, 1);
                if (response.equalsIgnoreCase("N")) {
                    keepPlaying = 0;
                } else if (response.equalsIgnoreCase("Y")) {
                    keepPlaying = 1;
                } else {
                    System.out.println("Invalid input -- Try again");
                }
            }
        }
    }

    //tells the player who won and their current statistics for all games played
    public static void winMessage(Card[] dealerHand,Card[] playerHand){
        int dealerHandScore = DeckOfCards.evaluateHand(dealerHand);
        int playerHandScore = DeckOfCards.evaluateHand(playerHand);
        if (dealerHandScore < playerHandScore) {
            System.out.println("Dealer Wins");
            DEALER_WINS++;
        } else if (playerHandScore < dealerHandScore) {
            System.out.println("Player Wins");
            PLAYER_WINS++;
        } else if (playerHandScore == 5 || playerHandScore == 9) {
            if (DeckOfCards.highCard(playerHand) == DeckOfCards.highCard(dealerHand)) {
                System.out.println("It's a tie!");
                TIES++;
            } else if (DeckOfCards.highCard(playerHand) < DeckOfCards.highCard(dealerHand)) {
                System.out.println("Player Wins");
                PLAYER_WINS++;
            } else {
                System.out.println("Dealer Wins");
                DEALER_WINS++;
            }
            /*
        //the following should have the same format as above matching scores.
        //code cleanliness means this should probably be broken into its own methods for each of these matching ones
        //instead of having a super long chain of if else
        } else if (playerHandScore == 1){
            // if contains ace and king, and other contains ace and king, or high card match = tie
            // else high card = win
        } else if (playerHandScore == 2) {
            // compare 4 and 5 for each hand. if 4 and 5 match, value of either. if not value of lower.
            //compare to other hand
        } else if (playerHandScore == 3) {
            // same as above, but with 3 cards.
        } else if (playerHandScore == 4) {
            //Each flush is ranked first by the rank of its highest-ranking card,
            // then by the rank of its second highest-ranking card,
            // then by the rank of its third highest-ranking card,
            // then by the rank of its fourth highest-ranking card,
            // then by the rank of its lowest-ranking card.
        } else if (playerHandScore == 6) {
            // for each hand compare highest 3 cards. If match 3 cards match, compare to other hands
            // if 2 match, compare that to other hands.
            // if none, lowest compared to other hand
        } else if (playerHandScore == 7) {
            //Each two pair is ranked first by the rank of its higher-ranking pair,
            //then by the rank of its lower-ranking pair,
            //and finally by the rank of its last card
        } else if (playerHandScore == 8) {
            //Each one pair is ranked first by the rank of its pair,
            // then by the rank of its highest-ranking kicker,
            // then by the rank of its second highest-ranking kicker,
            // and finally by the rank of its lowest-ranking kicker.
        }
             */

        //Game results
        System.out.println("Game Results");
        System.out.printf("\t%-13s = %d%n", "Dealer Wins", DEALER_WINS);
        System.out.printf("\t%-13s = %d%n", "Player Wins", PLAYER_WINS);
        System.out.printf("\t%-13s = %d%n", "Total Ties", TIES);
    }
}
