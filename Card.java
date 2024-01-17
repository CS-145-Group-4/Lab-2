// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// 01/17/24

public class Card implements Comparable<Card> {
   private final String face;
   private final String suit;
   private final int faceValue;
   
   public Card(String face, String suit){
      this.face = face;
      this.suit = suit;
      this.faceValue = setFaceValue();
   }

   @Override
    public int compareTo(Card other) {
        if (this.faceValue < other.faceValue) {
            return -1;
        } else if (this.faceValue > other.faceValue) {
            return 1;
        }
        return 0;
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

   private int setFaceValue() {
       switch (this.face) {
           case "Ace": return 1;
           case "Deuce": return 2;
           case "Three": return 3;
           case "Four": return 4;
           case "Five": return 5;
           case "Six": return 6;
           case "Seven": return 7;
           case "Eight": return 8;
           case "Nine": return 9;
           case "Ten": return 10;
           case "Jack": return 11;
           case "Queen": return 12;
           default: return 13;
       }
   }
}