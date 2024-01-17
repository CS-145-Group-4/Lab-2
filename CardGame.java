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
        game(console);
    }

    //this method runs the poker game, looping until player says they don't want to play anymore into the console.
    //creates a new hand for both the dealer and player, outputs the players hand, and tells what they drew,
    //and if their hand beat the dealers hand.
    //at the end of the hands dealt, outputs the statistics for the hands played.
    public static void game(Scanner console) {
        String response = "y";
        while (!response.equals("n")) {
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

            winMessage(dealerHand,playerHand);
            //Ask player if they would like to play again using while statement for proper input
            System.out.println("Would you like to play again? (Y/N)");
            response = console.next();
            while (!response.equalsIgnoreCase("n") || !response.equalsIgnoreCase("y")){
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
        } else if (playerHandScore == 5 || playerHandScore == 9 || playerHandScore == 1) {
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
        } // todo matching scores of 1,2,3,4,6,7,8,

        //Game results
        System.out.println("Game Results");
        System.out.printf("\t%-13s = %d%n", "Dealer Wins", DEALER_WINS);
        System.out.printf("\t%-13s = %d%n", "Player Wins", PLAYER_WINS);
        System.out.printf("\t%-13s = %d%n", "Total Ties", TIES);
    }
}
