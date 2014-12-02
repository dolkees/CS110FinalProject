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
   
   public Card(int suit1, int rank1)
   {
      suit = suit1;
      rank = rank1;
      front = new ImageIcon(((rank*10+suit)+".jpg"));
   }
   public int getSuit()
   {
      return suit;
   }
   public int getRank()
   {
      return rank;
   }
   public ImageIcon getIcon()
   {
      return front;
   }
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
   
   public boolean equals(Card other)
   {
      return this.getRank()==other.getRank();
   }
   
   public boolean isGreater(Card other)
   {
      return (this.getRank()>other.getRank());
   }
}