// Raymond Daniels, Mali Brunk
// CS 145
// Lab 2: Card Shuffling and Dealing
// Source: Deitel/Deitel
// 01/17/24

// This is basically what the book has with the addition of getFace and getSuit

public class Card {
   private String face;
   private String suit;
   
   public Card(String face, String suit){
      this.face = face;
      this.suit = suit;
   }

   public String getFace() {return this.face;}

   public String getSuit() {return this.suit;}

   public String toString() {return face + " of " + suit;}

} 
