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

            //deals and sorts hands
            Card[] dealerHand = gameDeck.dealHand(handSize);
            Arrays.sort(dealerHand);
            Card[] playerHand = gameDeck.dealHand(handSize);
            Arrays.sort(playerHand);

            int dealerHandScore = evaluateHand(dealerHand);
            System.out.println(stateEvaluation(dealerHandScore));
            System.out.println("Dealer Hand:");
            for (Card card : dealerHand) {
                System.out.println(card);
            }
            System.out.println();

            int playerHandScore = evaluateHand(playerHand);
            System.out.println(stateEvaluation(playerHandScore));
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
    public static void winMessage(Card[] dealerHand,Card[] playerHand) {
        boolean foundAce = false;
        boolean foundKing = false;
        int playerCompareValue;
        int dealerCompareValue;
        int dealerHandScore = evaluateHand(dealerHand);
        int playerHandScore = evaluateHand(playerHand);

        System.out.println(dealerHandScore);
        System.out.println(playerHandScore);
        System.out.println(highCard(dealerHand));
        System.out.println(highCard(playerHand));

        if (dealerHandScore < playerHandScore) {
            System.out.println("Dealer Wins");
            DEALER_WINS++;
        } else if (playerHandScore < dealerHandScore) {
            System.out.println("Player Wins");
            PLAYER_WINS++;
        } else if (playerHandScore == 5 || playerHandScore == 9) {
            if (playerHand[4].getFaceValue() > dealerHand[4].getFaceValue()) {
                PLAYER_WINS++;
            } else if (playerHand[4].getFaceValue() < dealerHand[4].getFaceValue()) {
                DEALER_WINS++;
            } else {
                TIES++;
            }
            // int currentCard = dealerHand.length - 1;
            // while (currentCard >= 0 && playerHand[currentCard].getFaceValue() == dealerHand[currentCard].getFaceValue() ) {
            //     currentCard--;
            // }
            // if (playerHand[currentCard].getFaceValue() > dealerHand[currentCard].getFaceValue()) {
            //     PLAYER_WINS++;
            // } else if (playerHand[currentCard].getFaceValue() < dealerHand[currentCard].getFaceValue()){
            //     DEALER_WINS++;
            // } else TIES++;
        }
        
        //the following should have the same format as above matching scores.
        //code cleanliness means this should probably be broken into its own methods for each of these matching ones
        //instead of having a super long chain of if else
         else if (playerHandScore == 1){
            for (int currentCard = 0; currentCard < playerHand.length; currentCard++) {
                if (playerHand[currentCard].getFaceValue() == 1 && dealerHand[currentCard].getFaceValue() == 1){
                    foundAce = true;
                }
                if (playerHand[currentCard].getFaceValue() == 13 && dealerHand[currentCard].getFaceValue() == 13){
                    foundKing = true;
                }
                if (foundAce && foundKing || highCard(playerHand) == highCard(dealerHand) ) {
                    TIES++;
                } else if (highCard(playerHand) > highCard(dealerHand)) {
                    PLAYER_WINS++;
                } else DEALER_WINS++;
            }


        } else if (playerHandScore == 2) {
            //Each flush is ranked first by the rank of its highest-ranking card,
            // then by the rank of its second highest-ranking card,
            // then by the rank of its third highest-ranking card,
            // then by the rank of its fourth highest-ranking card,
            // then by the rank of its lowest-ranking card.
            playerCompareValue = playerHand[3].getFaceValue();
            dealerCompareValue = dealerHand[3].getFaceValue();
            if (playerCompareValue > dealerCompareValue) {
                PLAYER_WINS++;
            } else if (playerCompareValue < dealerCompareValue) {
                DEALER_WINS++;
            } else TIES++; //breakout again
        } else if (playerHandScore == 3) {
            playerCompareValue = playerHand[2].getFaceValue();
            dealerCompareValue = dealerHand[2].getFaceValue();
            if (playerCompareValue > dealerCompareValue) {
                PLAYER_WINS++;
            } else if (playerCompareValue < dealerCompareValue) {
                DEALER_WINS++;
            } else TIES++; //breakout again
        } else if (playerHandScore == 4) {
            for (int i = 4; i >= 0; i--) {
                if ((playerHand[i].getFaceValue() != dealerHand[i].getFaceValue()) && (playerHand[i].getFaceValue() > dealerHand[i].getFaceValue())){
                    PLAYER_WINS++;
                    break;
                } else if ((playerHand[i].getFaceValue() != dealerHand[i].getFaceValue()) && (playerHand[i].getFaceValue() < dealerHand[i].getFaceValue())) {
                    DEALER_WINS++;
                    break;
                }
            }

        } else if (playerHandScore == 6) { // three of a kind will have three formats: J J J Q K, J Q Q Q K, or J Q K K K
            // for each hand compare highest 3 cards. If match 3 cards match, compare to other hands
            // if 2 match, compare that to other hands.
            // if none, lowest compared to other hand

            playerCompareValue = playerHand[2].getFaceValue();
            dealerCompareValue = dealerHand[2].getFaceValue();
            if (playerCompareValue > dealerCompareValue) {
                PLAYER_WINS++;
            } else if (playerCompareValue < dealerCompareValue) {
                DEALER_WINS++;
            } else TIES++; //breakout again

        } else if (playerHandScore == 7) {
            //Each two pair is ranked first by the rank of its higher-ranking pair,
            //then by the rank of its lower-ranking pair,
            //and finally by the rank of its last card

            int playerLowPair = 0;
            int dealerLowPair = 0;
            int playerHighPair = 0;
            int dealerHighPair = 0;
            int playerKicker = 0;
            int dealerKicker = 0;

            for (int i = 0; i < playerHand.length - 1; i++) {
                if (playerHand[i].getFaceValue() == playerHand[i + 1].getFaceValue() && playerLowPair == 0){
                    playerLowPair = playerHand[i].getFaceValue();
                } else if (playerHand[i].getFaceValue() == playerHand[i + 1].getFaceValue()) {
                    playerHighPair = playerHand[i].getFaceValue();
                } else playerKicker = playerHand[i].getFaceValue();
            }

            for (int i = 0; i < dealerHand.length - 1; i++) {
                if (dealerHand[i].getFaceValue() == dealerHand[i + 1].getFaceValue() && dealerLowPair == 0){
                    dealerLowPair = dealerHand[i].getFaceValue();
                } else if (dealerHand[i].getFaceValue() == dealerHand[i + 1].getFaceValue()){
                    dealerHighPair = dealerHand[i].getFaceValue();
                } else dealerKicker = dealerHand[i].getFaceValue();
            }

            if (playerHighPair > dealerHighPair) {
                PLAYER_WINS++;
            } else if (playerHighPair < dealerHighPair) {
                DEALER_WINS++;
            } else if (playerLowPair > dealerLowPair){
                PLAYER_WINS++;
            } else if (playerLowPair < dealerLowPair) {
                DEALER_WINS++;
            } else if (playerKicker > dealerKicker){
                PLAYER_WINS++;
            } else if (playerKicker < dealerKicker){
                DEALER_WINS++;
            } else TIES++;
        } else if (playerHandScore == 8) {
            //Each one pair is ranked first by the rank of its pair,
            // then by the rank of its highest-ranking kicker,
            // then by the rank of its second highest-ranking kicker,
            // and finally by the rank of its lowest-ranking kicker.

            int playerPairValue = findPairValue(playerHand);
            int dealerPairValue = findPairValue(dealerHand);
            int playerKicker = 0;
            int dealerKicker = 0;

             if (playerPairValue > dealerPairValue) {
                PLAYER_WINS++;
            } else if (playerPairValue < dealerPairValue) {
                DEALER_WINS++;
            } else {
                for (int i = 4; i > 0; i--) {
                    if (playerHand[i] != playerHand[i - 1]) {
                        playerKicker = playerHand[i].getFaceValue();
                    }
                    if (dealerHand[i] != dealerHand[i - 1]) {
                        dealerKicker = dealerHand[i].getFaceValue();
                    }
                }
                if (playerKicker > dealerKicker) {
                    PLAYER_WINS++;
                } else if (playerKicker < dealerKicker) {
                    DEALER_WINS++;
                } else {
                    TIES++;
                }
            }
        }
            //Game results
            System.out.println("Game Results");
            System.out.printf("\t%-13s = %d%n", "Dealer Wins", DEALER_WINS);
            System.out.printf("\t%-13s = %d%n", "Player Wins", PLAYER_WINS);
            System.out.printf("\t%-13s = %d%n", "Total Ties", TIES);
        }
        // takes player hand as input and returns the value of the pair
        public static int findPairValue(Card[] playerHand) {
            int pairValue = 0;

            for (int i = 0; i < playerHand.length - 1; i++) {
                if (playerHand[i].getFaceValue() == playerHand[i + 1].getFaceValue()){
                    pairValue = playerHand[i].getFaceValue();
                }
            }
            return pairValue;
        }

    //calculate hands score
    public static int evaluateHand(Card[] hand) {
        if (findStraight(hand) == 1 && findFlush(hand) ==1) {
            return 1; //straight flush
        } else if (findFourOfAKind(hand) == 1) {
            return 2; // 4 of a kind
        } else if (findFull(hand) == 1) {
            return 3; // full house
        } else if (findFlush(hand) == 1) {
            return 4; // flush
        } else if (findStraight(hand) == 1) {
            return 5; // straight
        } else if (findThreeOfAKind(hand) == 1) {
            return 6; // 3 of a kind
        } else if (findTwoPair(hand) == 1) {
            return 7; // 2 pair
        } else if (findPair(hand) == 1) {
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
    
    public static int findFull(Card[] hand) {
        if (findThreeOfAKind(hand) == 1 && findTwoPair(hand) == 1) {
            return 1;
        }
        return 0;
    }

    public static int findFourOfAKind(Card[] hand) {
        int sameCount = 1;
        for (int currentCard = 0; currentCard < hand.length - 1; currentCard++ ){
            if (hand[currentCard].getFaceValue() == hand[currentCard + 1].getFaceValue()){
                sameCount++;
            }
            if (sameCount == 4) {
                return 1;
            }
        } return 0;
    }
    
    public static int findThreeOfAKind(Card[] hand) {
        for (int currentCard = 0; currentCard < hand.length - 2; currentCard++ ){
            if ((hand[currentCard].getFaceValue() == hand[currentCard + 1].getFaceValue())  && (hand[currentCard].getFaceValue()== hand[currentCard + 2].getFaceValue())) {
                return 1;
            }
        } 
        return 0;
    }
    //checks if hand contains 2 pairs of same value cards.
    public static int findTwoPair(Card[] hand) {
        for (int currentCard = 0; currentCard < hand.length - 3; currentCard++ ){
            if (hand[currentCard].getFaceValue() == hand[currentCard + 1].getFaceValue() && hand[currentCard+2].getFaceValue() == hand[currentCard+3].getFaceValue()){
                return 1;
            }
        }
        if (hand[0].getFaceValue() == hand[1].getFaceValue() &&  hand[3].getFaceValue() == hand[4].getFaceValue()){
            return 1;
        } else return 0;
    }
    
    public static int findPair(Card[] hand) {
        for (int currentCard = 0; currentCard < hand.length - 1; currentCard++ ){
            if (hand[currentCard].getFaceValue() == hand[currentCard + 1].getFaceValue()) {
                return 1;
            }
        } 
        return 0;
    }

    //Checks if hand has a straight
    public static int findStraight(Card[] hand) {
        boolean possibleStraight = hand[4].getFaceValue() == 13 && hand[0].getFaceValue() == 1;
        //high aces straight

        int linearCount = 0;
        for (int i = 0; i < hand.length - 1; i++) {
            if (hand[i].getFaceValue() + 1 == hand[i + 1].getFaceValue()) {
                linearCount++;
            }
        }
        if (possibleStraight && linearCount == 4){
            return 1;
        } else if (linearCount == 5){
            return 1;
        } else return 0;
    }

    //checks if hand contains a flush
    public static int findFlush(Card[] hand) {
        String suit = hand[0].getSuit();
        for (int i = 1; i < hand.length; i++) {
            if (!hand[i].getSuit().equals(suit)) {
                return 0;
            }
        }
        return 1;
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
