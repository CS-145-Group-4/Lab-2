// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// Source: Deitel/Deitel
// 01/17/24

// This is basically what the book has with the addition of getFace and getSuit
public class Card {
   private String face;
   private String suit;
   private int faceValue;
   private int suitValue;
   
   public Card(String face, String suit){
      this.face = face;
      this.suit = suit;
      this.faceValue = setFaceValue();
      this.suitValue = setSuitValue();
   }

   public String getFace() {
      return this.face;
   }

   public String getSuit() {
      return this.suit;
   }

   public String toString() {
      return face + " of " + suit;
   }

   public int getFaceValue() {
      return this.faceValue;
   }

   public int getSuitValue() {
      return this.suitValue;
   }

   private int setFaceValue() {
       switch (this.face) {
           case "Ace":
               return 1;
           case "Deuce":
               return 2;
           case "Three":
               return 3;
           case "Four":
               return 4;
           case "Five":
               return 5;
           case "Six":
               return 6;
           case "Seven":
               return 7;
           case "Eight":
               return 8;
           case "Nine":
               return 9;
           case "Ten":
               return 10;
           case "Jack":
               return 11;
           case "Queen":
               return 12;
           default:
               return 13;
       }
   }
   private int setSuitValue() {
       switch (this.face) {
           case "Hearts":
               return 1;
           case "Diamonds":
               return 2;
           case "Clubs":
               return 3;
           default:
               return 4;
       }
   }
}
