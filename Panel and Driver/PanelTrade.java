//Max Desousa
//Dhruv Sridhar
import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.util.ArrayList;
   import java.io.*;
   import javax.swing.JPopupMenu;
   import javax.swing.event.*;
public class PanelTrade extends JPanel
{
   private Hero shop;
   private JLabel labelNumber;
   private JTextField userInputBox;
   private JLabel labelBuy;
   private JLabel labelSell;
   private int index = 0;

   public PanelTrade()
   {
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());
      add(panel);
   
      userInputBox = new JTextField("0", 5);
      userInputBox.setHorizontalAlignment(SwingConstants.RIGHT);
      panel.add(userInputBox);
      
   
      
      class Key extends KeyAdapter
      {
         
         public void keyPressed (KeyEvent e )
         {
          
            if(e.getKeyCode() == KeyEvent.VK_B)
            {
               labelBuy = new JLabel("What # item is to be bought");
               labelBuy.setFont(new Font("Serif", Font.BOLD, 20));
               labelBuy.setForeground(Color.blue);
               add(labelBuy);
            }
            else if (e.getKeyCode() == KeyEvent.VK_S)
            {
            labelSell = new JLabel("What # item is to be sold?");
            labelSell.setFont(new Font("Serif", Font.BOLD, 20));
            labelSell.setForeground(Color.blue);
            add(labelSell);

            }
         }
      }
            


   
      JButton confirmButton = new JButton("Confirm");
      confirmButton.addActionListener(new ConfirmListener());
      panel.add(confirmButton);
   }

   
   private class ConfirmListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         index = (int)Double.parseDouble(userInputBox.getText());
      }
   }
   shop = new Hero(0,0,0,0,0,0,0,0,"Transparent.png")
   //Make a "Hero" but just to store stuff
   shop.addToInv(new Armour(20, true, true, "Good Armour", 10, "TestArmour.png"));
   //add items to "shop" 
   



            
            
  /*    public void addMoney(double m)
   {
      money = money + m;
   }
   public void spendMoney(double m)
   {
      money = money - m;
   }
      public void buyItem(Items item)
   {
      if(invIndex < 25 && item.getPrice() < money)
      {
        addToInv(item);
        spendMoney(item.getPrice());
      }
   }
   //ask if when a character want to remove inventory if they 
   //would "sell" it and get money or if it just removes the item
   //(if you have to go to a store to sell items
   public void sellItem(int index)
   {
      addMoney(inventory[index].getPrice());
      inventory[index] = null;
   }
*/
}
