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
   
   public static void fight(Deck player, Deck computer)
   {
      Card playersCard = player.draw();
      playerCard.setIcon(playersCard.getIcon());
      Card computersCard = computer.draw();
      computerCard.setIcon(computersCard.getIcon());

      if (playersCard.isGreater(computersCard))
      {
         player.add(playersCard);
         player.add(computersCard);
         //System.out.println("Player's "+playersCard+" beats Computer's "+computersCard);
      }
      else if (computersCard.isGreater(playersCard))
      {
         //System.out.println("Computer's "+computersCard+" beats Player's "+playersCard);
         computer.add(computersCard);
         computer.add(playersCard);
      }
      else
      {
         //System.out.println("Player's "+playersCard+" ties with Computer's "+computersCard);
         if ((player.cardsRemaining()>3)&&(computer.cardsRemaining()>3))
            war(player,computer,playersCard,computersCard);
         else if (player.cardsRemaining()<3)
         {
            //System.out.println("Player does not have enough cards to fight in War");
            endGame(player,computer);
         }
         else
         { 
            //System.out.println("Computer does not have enough cards to fight in War");
            endGame(player,computer);
         }
      }
   }
   
   public static void war(Deck player, Deck computer, Card playerCard0, Card computerCard0)
   {
      Card playerCard1 = player.draw();
            
      Card computerCard1 = computer.draw();
            
      Card playersCard = player.draw();
      playerCard.setIcon(playersCard.getIcon());
      Card computersCard = computer.draw();
      computerCard.setIcon(computersCard.getIcon());

      if (playersCard.isGreater(computersCard))
      {
         //System.out.println("Player's "+playersCard+" beats Computer's "+computersCard);
         //System.out.println("Player won War!\nPlayer gets all the cards!");
         //System.out.println("Cards won from Computer:\n"+computersCard+"\n"+computerCard1+"\n"+computerCard0);
         player.add(playersCard);
         player.add(playerCard0);
         player.add(playerCard1);
         player.add(computersCard);
         player.add(computerCard0);
         player.add(computerCard1);
      }
      else if (computersCard.isGreater(playersCard))
      {
         //System.out.println("Computer's "+computersCard+" beats Player's "+playersCard);
         //System.out.println("Computer won War!\nComputer gets all the cards!");
         //System.out.println("Cards lost to Computer:\n"+playersCard+"\n"+playerCard1+"\n"+playerCard0);
         computer.add(computersCard);
         computer.add(computerCard0);
         computer.add(computerCard1);
         computer.add(playersCard);
         computer.add(playerCard0);
         computer.add(playerCard1);
      }
      else
      {
         //System.out.println("Draw");
         player.add(playersCard);
         player.add(playerCard0);
         player.add(playerCard1);
         computer.add(computersCard);
         computer.add(computerCard0);
         computer.add(computerCard1);         
      }
   }
   
   public static void endGame(Deck player, Deck computer)
   {      
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
      panelCards.remove(continueButton);
   }
  
}