/**
 * Representation of a Deck of cards.  
 * Initialized to a standard 52 card deck. 
 *
 * @author Jackie Horton
 * Modified by Cory Arcovitch to utilize queue reference based lists instead of array list
 */

import java.util.Random;
import java.util.ArrayList;


public class Deck 
{
   /** 
   *  Number of cards currently in deck
   **/
   private int cardsInDeck=0;

   /** The collection of Cards in order */
   private ArrayList<Card> deck1;
   
   private QueueReferenceBased deck;
   
   /**
    * Constructs a regular 52-card deck.  Initially, the cards
    * are shuffled  
    */
   public Deck()
   {
      freshShuffledDeck();
   }
   /**
    * Create a new collection of 52 cards, in shuffled order
    */
   public void freshShuffledDeck()
   {
      deck1 = new ArrayList<Card>();
      deck = new QueueReferenceBased();

      for (int r = 2; r<=Card.ACE;r++)
      {
         for (int s=Card.SPADES;s<=Card.DIAMONDS;s++)
         {
            deck1.add(new Card(s,r));
         }
      }
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < deck1.size(); i++)
      {
         randNum = r.nextInt(deck1.size());
         temp = deck1.get(i);
         deck1.set(i,deck1.get(randNum));
         deck1.set(randNum,temp);
      }      

      for (int i = 0; i < deck1.size(); i++)
      {
         deck.enqueue(deck1.get(i));
         cardsInDeck++;
      } 
   }
  
   /** 
   * Returns current number of Cards in Deck
   * @return number of Cards in Deck
   */

   public int cardsRemaining()
   {  
      return cardsInDeck;
   }
   
   /**
      Returns the top card and removes from deck
      @return card on top of pile
   */
   
   public Card draw()
   {
      cardsInDeck--;
      return (Card) deck.dequeue();
   }
   
   /** 
   * Determines if Deck is empty
   * @return true if there are no more cards, false otherwise
   */
   
   public boolean isEmpty()
   {
      return (deck.getSize() == 0);
   }
   
   /**
      Adds card to bottom of pile
      @param other Card
   */
   
   public void add(Card other)
   {
      deck.enqueue(other);
      cardsInDeck++;
   }
}
