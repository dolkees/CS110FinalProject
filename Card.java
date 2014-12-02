//Cory Arcovitch
//CS110
//HW 5 number 3
import javax.swing.*;
public class Card
{
   //suits
   public static final int SPADES=1;
   public static final int CLUBS=2;
   public static final int HEARTS=3;
   public static final int DIAMONDS=4;
   //ranks
   public static final int ACE=14;
   public static final int JACK=11;
   public static final int QUEEN=12;
   public static final int KING=13;
   //Image
   private ImageIcon front;
   
   private int rank;
   private int suit;
   
   /**
      Constructor
      @param suit1 the suit of the card
      @param rank1 the rank of the card
   */
   
   public Card(int suit1, int rank1)
   {
      suit = suit1;
      rank = rank1;
      front = new ImageIcon(((rank*10+suit)+".jpg"));
   }
   
   /**
      getSuit method returns the suit of the card
      @return the suit of the card
   */
   
   public int getSuit()
   {
      return suit;
   }
   
   /**
      getRank method returns the rank of the card
      @return the rank of the card
   */
   
   public int getRank()
   {
      return rank;
   }
   
   /**
      getIcon method returns the icon image associated with the card
      @return the Image Icon of the card
   */
   
   public ImageIcon getIcon()
   {
      return front;
   }
   
   /**
      toString method returns a string form representation of the card
      @return string of suit and rank of card
   */
   
   public String toString()
   {
      String title;
      String suits="";
      switch (suit)
      {
         case 1: suits = "Spades";
            break;
         case 2: suits = "Clubs";
            break;
         case 3: suits = "Hearts";
            break;
         case 4: suits = "Diamonds";
      }

      if ((rank==14)||(rank==11)||(rank==12)||(rank==13))
      {
         String ranks="";
         switch(rank)
         {
            case 14: ranks = "Ace";
               break;
            case 11: ranks = "Jack";
               break;
            case 12: ranks = "Queen";
               break;
            case 13: ranks = "King";
         }
         title = ranks+" of "+suits;
      }
      else
         title = rank+" of "+suits;
      
      return title;
   }
   
   /**
      equals method compares to cards and returns true if they are equal rank
      @param other card
      @return true if the ranks are the same
   */
   
   public boolean equals(Card other)
   {
      return this.getRank()==other.getRank();
   }
   
   /**
      isGreater method compares to cards and returns true if this one is greater than the other
      @param other card
      @return true of this card's rank is greater than other's
   */
   
   public boolean isGreater(Card other)
   {
      return (this.getRank()>other.getRank());
   }
}