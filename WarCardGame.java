//Cory Arcovitch
//CS110
//Final Project
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WarCardGame extends JFrame
{
   private static JPanel panel,panelCards,panelText;
   private static JButton continueButton;
   private static JLabel playerCard, computerCard, computerPile, playerPile, playerCards, computerCards;
   private static ImageIcon back = new ImageIcon("back.jpg");
   private static final Deck player= new Deck();
   private static final Deck computer= new Deck();

   WarCardGame(String s)
   {
      //Create buttons and card piles and arrange them as desired
      super(s); 
      panel = new JPanel();
      panel.setLayout(new BorderLayout());
      panelCards = new JPanel();
      panelCards.setLayout(new FlowLayout());
      panelText = new JPanel();
      panelText.setLayout(new FlowLayout());
      continueButton = new JButton("GO");
      continueButton.addActionListener(new ButtonListener());
      
      playerCards = new JLabel("Player's Cards: 52              ");
      computerCards = new JLabel("Computer's Cards: 52");
      
      panelText.add(playerCards);
      panelText.add(computerCards);
            
      playerCard = new JLabel(back);
      computerCard = new JLabel(back);
      playerPile = new JLabel(back);
      computerPile = new JLabel(back);
      
      panelCards.add(playerPile);
      panelCards.add(playerCard);
      panelCards.add(continueButton);
      panelCards.add(computerCard);
      panelCards.add(computerPile);
      
      panel.add(panelCards,BorderLayout.CENTER);
      panel.add(panelText,BorderLayout.PAGE_END);
      
      add(panel);
   }

   //Action listener that runs ffight sequence when button is pushed
   class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
      if ((player.cardsRemaining()!=0)&&(computer.cardsRemaining()!=0))
      {
         fight(player,computer);
         playerCards.setText("Player's Cards: "+player.cardsRemaining()+"              ");
         computerCards.setText("Computer's Cards: "+computer.cardsRemaining());
      }
      else
         endGame(player,computer);
      }
   }

   public static void main(String [] args)
   {     
      JFrame frame = new WarCardGame("WAR");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
   
   /**
      fight method draws cards from each and compares them. 
      player with higher rank gets card. if tie, runs war sequence
      if not enough cards for war sequence, ends game
      @param player player's deck
      @param computer computer's deck
   */
   
   public static void fight(Deck player, Deck computer)
   {
      //draw cards to compare and display them in frame   
      Card playersCard = player.draw();
      playerCard.setIcon(playersCard.getIcon());
      Card computersCard = computer.draw();
      computerCard.setIcon(computersCard.getIcon());
      
      //compare cards and award them accordingly
      if (playersCard.isGreater(computersCard))
      {
         player.add(playersCard);
         player.add(computersCard);
      }
      else if (computersCard.isGreater(playersCard))
      {
         computer.add(computersCard);
         computer.add(playersCard);
      }
      else
      {
         //if tied, AND there's enough cards to do a war then continue to war
         if ((player.cardsRemaining()>3)&&(computer.cardsRemaining()>3))
            war(player,computer,playersCard,computersCard);           
         //if not enough cards, ends game
         else if (player.cardsRemaining()<3)
         {
            endGame(player,computer);
         }
         else
         { 
            endGame(player,computer);
         }
      }
   }
   
   /**
      war method starts war sequence when cards drawn are the same rank
      draws additional card from both players and then another one to compare
      winner takes all
      @param player players deck
      @param computer computer's deck
      @param playerCard0 player's card that tied
      @param computerCard0 computer's card that tied
   */
   
   public static void war(Deck player, Deck computer, Card playerCard0, Card computerCard0)
   {
      //draw additional cards
      Card playerCard1 = player.draw();
      Card computerCard1 = computer.draw();
      
      //draw cards to compare and display them in frame    
      Card playersCard = player.draw();
      playerCard.setIcon(playersCard.getIcon());
      Card computersCard = computer.draw();
      computerCard.setIcon(computersCard.getIcon());
      
      //whomever wins gets all cards
      if (playersCard.isGreater(computersCard))
      {
         player.add(playersCard);
         player.add(playerCard0);
         player.add(playerCard1);
         player.add(computersCard);
         player.add(computerCard0);
         player.add(computerCard1);
      }
      else if (computersCard.isGreater(playersCard))
      {
         computer.add(computersCard);
         computer.add(computerCard0);
         computer.add(computerCard1);
         computer.add(playersCard);
         computer.add(playerCard0);
         computer.add(playerCard1);
      }
      else //if tie, return all cards to players (much simpler than double war, triple war, etc.
      {
         player.add(playersCard);
         player.add(playerCard0);
         player.add(playerCard1);
         computer.add(computersCard);
         computer.add(computerCard0);
         computer.add(computerCard1);         
      }
   }
   
   /**
      endGame method determines winner and ends game
      @param player player's deck
      @param computer computer's deck
   */
   
   public static void endGame(Deck player, Deck computer)
   {      
      //give winner all remaining cards if they exist and display winning message
      if (player.cardsRemaining()>computer.cardsRemaining())
      {
         for (int i = 0; i<computer.cardsRemaining();i++)
         {
            player.add(computer.draw());
         }
         playerCards.setText("Player is WINNER          ");
         computerCards.setText("You're a WINNER!");
      }
      else
      {
         for (int i = 0; i<player.cardsRemaining();i++)
         {
            computer.add(computer.draw());
         }
         playerCards.setText("Computer is WINNER           ");
         computerCards.setText("You're a LOSER!");
      }
      //Remove go button so it can't be pushed again
      panelCards.remove(continueButton);
   }
  
}